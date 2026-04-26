package concurrency.virtualThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class VT_ExecutorServiceDemo {

	private static class Task implements Callable<Integer> {

		@Override
		public Integer call() throws Exception {
			TimeUnit.SECONDS.sleep(1);
			;
			return 0;
		}

	}

	public static void main(String[] args) throws InterruptedException {

		List<Task> tasks = getAllTask();

		handleWithVirtualThreads(tasks);

		handleWithFixedThreadPool(tasks);
	}

	private static void handleWithFixedThreadPool(List<Task> tasks) throws InterruptedException {
		long startTime = System.nanoTime();
		try (ExecutorService executor = Executors.newFixedThreadPool(1000)) {
			executor.invokeAll(tasks);
		}
		long timeTaken = System.nanoTime() - startTime;
		System.out.println("Total time it took with fixed thread pool was " + (timeTaken / 1000_000_000) + " seconds");
	}

	private static void handleWithVirtualThreads(List<Task> tasks) throws InterruptedException {
		// try with resource because ExecutorService implements AutoClossable now.
		long startTime = System.nanoTime();
		try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
			executor.invokeAll(tasks);
		}
		long timeTaken = System.nanoTime() - startTime;
		System.out.println("Total time it took with virtual thread was " + (timeTaken / 1000_000_000) + " seconds");
	}

	private static List<Task> getAllTask() {
		List<Task> tasks = new ArrayList<>();
		var taskCount = 1_00_000;
		for (var count = 0; count < taskCount; count++) {
			tasks.add(new Task());
		}
		return tasks;
	}
}
