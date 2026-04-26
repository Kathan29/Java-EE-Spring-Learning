package lambda_streams;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class LamdasWithExecutorService {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Runnable using lamda
        executor.submit(() -> {
            System.out.println("Runnable task 1");
        });

        // Callable using lambda
        Future<Integer> future = executor.submit(() -> {
            return 10 + 20;
        });

        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        executor.shutdown();
    }
}
