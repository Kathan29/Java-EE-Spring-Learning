package concurrency.executorService_RealUseCase;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MiniOrderProcessingSystem {

	private static class OrderSystem {
		private AtomicInteger orderCounter = new AtomicInteger(0);
		private volatile boolean systemRunning = true;

		private final ReentrantLock lock = new ReentrantLock();
		private final Condition stockAvailable = lock.newCondition();

		private int stock = 5;

		private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
		private final Map<Integer, String> orderCache = new HashMap<>();

		public void processOrder(int orderId) {
			if (!systemRunning)
				return;

			System.out.println("Current Total Order Count : " + orderCounter.incrementAndGet());

			lock.lock();
			try {
				while (stock == 0) {
					System.out.println("Order " + orderId + " is waiting for stock....");
					stockAvailable.await();
				}

				stock--;
				System.out.println("Order " + orderId + " is processed..");

			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			} finally {
				lock.unlock();
			}

			// Now write it in the orderCache also
			rwLock.writeLock().lock();
			try {
				orderCache.put(orderId, "Processed");
			} finally {
				rwLock.writeLock().unlock();
			}
		}

		public void reStock(int amount) {
			lock.lock();
			try {
				stock += amount;
				System.out.println("Restocked : " + amount);
				stockAvailable.signalAll();
			} finally {
				lock.unlock();
			}
		}

		public void readCache(int orderId) {
			rwLock.readLock().lock();
			try {
				System.out.println("Reading order " + orderId + ": " + orderCache.get(orderId));
			} finally {
				rwLock.readLock().unlock();
			}
		}

		public void shutdown() {
			systemRunning = false;
		}

	}

	public static void main(String[] args) {
		OrderSystem order = new OrderSystem();
		ExecutorService executor = Executors.newFixedThreadPool(4);

		executor.submit(new Runnable() {

			@Override
			public void run() {
				for (int i = 1; i <= 10; i++) {
					order.processOrder(i);
				}
			}

		});

		executor.submit(new Runnable() {
			public void run() {
				try {
					Thread.sleep(3000);
					order.reStock(5);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});

		executor.submit(new Runnable() {
			public void run() {
				order.readCache(1);
				order.readCache(2);
			}
		});

		try {
			Thread.sleep(6000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		order.shutdown();

		executor.shutdown();
	}

}
