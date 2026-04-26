package concurrency;

import java.util.ArrayDeque;

public class WaitNotifyDemo {

	public static class Weblink {
		private long id;
		private String title;
		private String url;
		private String host;
		private String htmlPage;

		public Weblink(long id, String title, String url, String host) {
			super();
			this.id = id;
			this.title = title;
			this.url = url;
			this.host = host;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getHost() {
			return host;
		}

		public void setHost(String host) {
			this.host = host;
		}

		public String getHtmlPage() {
			return htmlPage;
		}

		public void setHtmlPage(String htmlPage) {
			this.htmlPage = htmlPage;
		}
	}

	public ArrayDeque<Weblink> queue = new ArrayDeque<WaitNotifyDemo.Weblink>();

	public void add(Weblink link) {
		queue.add(link);
	}

	public static class HttpConnect {
		public static String download(String url) {
			try {
				Thread.sleep(2000); // simulate delay
			} catch (Exception e) {
			}

			return "<html>Dummy content from " + url + "</html>";
		}
	}

	public static class Downloader implements Runnable {
		private Weblink weblink;

		public Downloader(Weblink weblink) {
			this.weblink = weblink;
		}

		@Override
		public void run() {

			synchronized (weblink) {
				String htmlPage = HttpConnect.download(weblink.getUrl());
				weblink.setHtmlPage(htmlPage);
				weblink.notifyAll(); // notify() wakes up single thread (chosen arbitrarily if multiple threads are
										// waiting). Moves waiting threads to BLOCKED
			}
		}
	}

	public static class Indexer implements Runnable {
		private Weblink weblink;

		public Indexer(Weblink weblink) {
			this.weblink = weblink;
		}

		@Override
		public void run() {
			// Without synchronized block, wait/notify calls will throw
			// IllegalMonitorStateException
			synchronized (weblink) {
				String htmlPage = weblink.getHtmlPage();
				// Standard idiom for using wait method
				// + while condition is critical as some other thread could have acquired the
				// lock and changed the state of the variable or
				// + Due to "spurious wakeup": A waiting thread can rarely wake up in the
				// absence of notify.
				while (htmlPage == null) {
					try {

						System.out.println(weblink.getId() + " is not downloaded yet...");
						weblink.wait();
						System.out.println(weblink.getId() + " is awakend...");
						htmlPage = weblink.getHtmlPage();

					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				index(htmlPage);
			}

		}

		private void index(String htmlPage) {
			if (htmlPage != null)
				System.out.println("Indexed successful : " + htmlPage + " " + weblink.getId());
		}
	}

	public void go() {
		while (queue.size() > 0) {
			Weblink weblink = queue.remove();
			Thread downloader = new Thread(new Downloader(weblink));
			Thread indexer = new Thread(new Indexer(weblink));

			downloader.start();
			indexer.start();
		}
	}

	public static void main(String[] args) {
		WaitNotifyDemo demo = new WaitNotifyDemo();
		demo.add(new WaitNotifyDemo.Weblink(2000, "Nested Classes",
				"https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html", "httpz://docs.oracle.com"));
		demo.add(new WaitNotifyDemo.Weblink(2001, "Java SE Downloads",
				"https://www.oracle.com/technetwork/java/javase/downloads/index.html", "http://www.oracle.com"));
		demo.add(new WaitNotifyDemo.Weblink(2002, "Interface vs Abstract Class",
				"https://mindprod.com/jgloss/interfacevsabstract.html", "http://mindprod.com"));
		demo.add(new WaitNotifyDemo.Weblink(2004, "Virtual Hosting and Tomcat",
				"https://tomcat.apache.org/tomcat-6.0-doc/virtual-hosting-howto.html", "http://tomcat.apache.org"));

		demo.go();
	}
}
