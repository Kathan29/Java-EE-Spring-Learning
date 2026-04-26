package concurrency;

import java.util.concurrent.TimeUnit;

public class ThreadDemo1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Task task = new Task();
		Thread thread = new Thread(task);
		thread.start();
		
		try {
			// Thread.sleep(100); //It is 100 ms
			
			TimeUnit.SECONDS.sleep(1); // It is one second
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("In main method");
	}

}

class Task implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("In run method");
		go();
	}

	private void go() {
		// TODO Auto-generated method stub
		System.out.println("In Go method");
		more();
	}

	private void more() {
		// TODO Auto-generated method stub
		System.out.println("In more method");
	}
	
}
