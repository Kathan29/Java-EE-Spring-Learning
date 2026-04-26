package concurrency.executorService_RealUseCase;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelApiCalls {
	
	private static class ApiTask implements Callable<String>{
		
		private String apiName;
		
		public ApiTask(String apiName) {
			this.apiName = apiName;
		}

		@Override
		public String call() throws Exception {
			System.out.println("Calling api :"+apiName);
			Thread.sleep(2000);
			return apiName+" response";
		}
		
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ExecutorService executor = Executors.newFixedThreadPool(2);
		
		List<Callable<String>> tasks = List.of(new ApiTask("Payment api"),new ApiTask("Movie api"),new ApiTask("order api"));
		
		try {
			//invoke all tasks in parallel
			List<Future<String>> results = executor.invokeAll(tasks);
			for(var future : results) {
				System.out.println(future.get()); //blocking
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		executor.shutdown();
		
	}

}
