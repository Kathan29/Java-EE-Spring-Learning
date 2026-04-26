package concurrency.executorService_RealUseCase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CancelTaskDemo {
	
	public static void main(String[] args) throws InterruptedException {
		
		ExecutorService executor = Executors.newSingleThreadExecutor();
		
		Future<?> future = executor.submit(new Runnable() {
			public void run() {
				try {
					while(true) {
						System.out.println("Processing...");
						Thread.sleep(1000);
					} 
				}catch (InterruptedException e) {
						// TODO Auto-generated catch block
						System.out.println("Task interrupted");
					}
				
			}
		});
		
		Thread.sleep(3000);
		future.cancel(true);
		executor.shutdown();
	}
}
