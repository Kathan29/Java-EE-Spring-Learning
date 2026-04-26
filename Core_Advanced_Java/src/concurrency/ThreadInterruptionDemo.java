package concurrency;

import java.util.concurrent.TimeUnit;

public class ThreadInterruptionDemo {
	
	public static void main(String[] args) throws InterruptedException {
		
//Real world mapping :  Download file → user clicks cancel → interrupt thread
		Task1 task1 = new Task1();
		Thread thread1 = new Thread(task1);
		thread1.start();
		Thread.sleep(3000);  //let it run
		thread1.interrupt(); //cancel request of processing
	
//Real World Mapping: API call timeout ,Database query timeout : Stop a task if it takes too long
		
		Thread thread2 = new Thread(new Runnable() {

			@Override
			public void run() {
				
				try {
					System.out.println("Working");
					TimeUnit.SECONDS.sleep(5);
					System.out.println("Completed");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println("Task 2 is interrupted(sleep interrupted) because of timeout");
					Thread.currentThread().interrupt(); //reset 
				}
			}
			
		});
		
		thread2.start();
		Thread.sleep(3000); //timeout
		thread2.interrupt(); //cancel task
	}

}

class Task1 implements Runnable{

	@Override
	public void run() {
		while(!Thread.currentThread().isInterrupted()) {
			System.out.println("Processing ....");
		}
		System.out.println("Task 1 stopped gracefully..");
	}
	
}