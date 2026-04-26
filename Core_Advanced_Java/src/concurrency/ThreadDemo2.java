package concurrency;

public class ThreadDemo2 extends Thread{

	public void run() {
		System.out.println("In run method");
		go();
	}
	private void go() {
		// TODO Auto-generated method stub
		System.out.println("In go method");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread thread = new ThreadDemo2();
		thread.start();
		
		System.out.println("In main method");
	}

}
