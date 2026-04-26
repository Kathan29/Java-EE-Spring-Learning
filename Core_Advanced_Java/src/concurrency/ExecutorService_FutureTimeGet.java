package concurrency;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;



public class ExecutorService_FutureTimeGet {
	ExecutorService downloaderExecutor = Executors.newFixedThreadPool(2);
	ExecutorService indexerExecutor = Executors.newFixedThreadPool(2);
	private static final long TIME_FRAME = 3000000000L;

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

	public ArrayDeque<Weblink> queue = new ArrayDeque<>();

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

	public static class Downloader<T extends Weblink> implements Callable<T> {
		private T weblink;

		public Downloader(T weblink) {
			this.weblink = weblink;
		}

		@Override
		public T call() {
			String htmlPage = HttpConnect.download(weblink.getUrl());
			weblink.setHtmlPage(htmlPage);
			return weblink;
		}
	}

	public static class Indexer implements Runnable {
		private long endTime;
		private Future<Weblink> future;

		public Indexer(long endTime,Future<Weblink> future) {
			this.endTime = endTime;
			this.future = future;
		}
		
		private Weblink weblink;
		@Override
		public void run() {
			long remainingTime = endTime-System.nanoTime();
			try {
				this.weblink = future.get(remainingTime,TimeUnit.NANOSECONDS);
				String htmlPage = weblink.getHtmlPage();
				index(htmlPage);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TimeoutException e) {
				// TODO Auto-generated catch block
				future.cancel(true);
				System.out.println("\n\nTask is cancelled --> " + Thread.currentThread());
			}
			
		}

		private void index(String htmlPage) {
			if (htmlPage != null)
				System.out.println("Indexed successful : " + htmlPage + " " + weblink.getId());
		}
	}

	public void go() {
		
		List<Future<Weblink>>  futures = new ArrayList<>();
		
		while (queue.size() > 0) {
			Weblink weblink = queue.remove();
			futures.add(downloaderExecutor.submit(new Downloader<Weblink>(weblink)));
		}	
		long endTime = System.nanoTime()+TIME_FRAME;	
		for(var future : futures) {
			indexerExecutor.submit(new Indexer(endTime,future));
		}
		
		downloaderExecutor.shutdown();
		indexerExecutor.shutdown();
		
	}

	public static void main(String[] args) {
		ExecutorService_FutureTimeGet demo = new ExecutorService_FutureTimeGet();
		demo.add(new ExecutorService_FutureTimeGet.Weblink(2000, "Nested Classes",
				"https://docs.oracle.com/javase/tutorial/java/javaOO/nested.html", "httpz://docs.oracle.com"));
		demo.add(new ExecutorService_FutureTimeGet.Weblink(2001, "Java SE Downloads",
				"https://www.oracle.com/technetwork/java/javase/downloads/index.html", "http://www.oracle.com"));
		demo.add(new ExecutorService_FutureTimeGet.Weblink(2002, "Interface vs Abstract Class",
				"https://mindprod.com/jgloss/interfacevsabstract.html", "http://mindprod.com"));
		demo.add(new ExecutorService_FutureTimeGet.Weblink(2004, "Virtual Hosting and Tomcat",
				"https://tomcat.apache.org/tomcat-6.0-doc/virtual-hosting-howto.html", "http://tomcat.apache.org"));

		demo.go();
	}
}
