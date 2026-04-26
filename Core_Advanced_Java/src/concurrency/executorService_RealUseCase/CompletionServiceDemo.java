package concurrency.executorService_RealUseCase;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CompletionServiceDemo {
	private static class Task implements Callable<String>{
		
		private int id;
		
		public Task(int id) {
			this.id = id;
		}
		
		@Override
		public String call() throws InterruptedException {
			
			Thread.sleep((int)(Math.random()*3000));
			return "Task Completed "+id;
		}
		
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService executor = Executors.newFixedThreadPool(3);
		CompletionService<String> service = new ExecutorCompletionService<String>(executor);
		
		//Submit all tasks
		for(int i = 0;i<5;i++) {
			service.submit(new Task(i));
		}
		
		//Based on completion it will show output
		for(int i=0;i<5;i++) {
			Future<String> future = service.take();
			System.out.println(future.get());
		}
		
		executor.shutdown();
	}
}
