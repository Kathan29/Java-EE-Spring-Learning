package concurrency;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class ReentrantLockDemo {
	
	private static class SharedCache{
		private final Queue<Integer> queue = new LinkedList<Integer>();
		private final int MAX_SIZE = 5;
		
		//Reentrant Lock
		private final ReentrantLock lock = new ReentrantLock();
		private final Condition notEmpty = lock.newCondition();
		private final Condition notFull = lock.newCondition();
		
		//Reentrant read write lock
		private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
		
		//Producer
		public void produce(int value) {
			lock.lock();
			try {
				while(queue.size()==MAX_SIZE) {
					System.out.println("Queue full, waiting to consume data");
					notFull.await();
				}
				queue.add(value);
				System.out.println("Produce : "+value);
				notEmpty.signal();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
		
		//Consumer
		public int consume() {
			lock.lock();
			int data = 0;
			try {
				while(queue.isEmpty()) {
					System.out.println("Queue Empty, waiting to produce data");
					notEmpty.await();
				}
				data = queue.remove();
				System.out.println("Consume value : "+data);
				notFull.signal();
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				lock.unlock();
			}
			return data;
		}
		
		public void readData() {
			rwLock.readLock().lock();
			try {
				System.out.println(Thread.currentThread().getName()+" Reading cache value "+queue);
			}finally {
				rwLock.readLock().unlock();
			}
		}
		
		public void writeData(int data) {
			rwLock.writeLock().lock();
			try {
				queue.add(data);
				System.out.println(Thread.currentThread().getName()+" Writing cache value "+data);
			}finally {
				rwLock.writeLock().unlock();
			}
		}
		
	}
	
	
	public static void main(String[] args) throws InterruptedException {
		
		ReentrantLockDemo.SharedCache cache = new ReentrantLockDemo.SharedCache();
		
		Thread producer = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i = 0;i<10;i++) {
					try {
						cache.produce(i);
						Thread.sleep(500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		Thread consumer = new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				for(int i = 0;i<10;i++) {
					try {
						cache.consume();
						Thread.sleep(700);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		
		Thread reader1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0;i<5;i++) {
					cache.readData();
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});
		reader1.setName("Reader 1");
		
		Thread reader2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=0;i<5;i++) {
					cache.readData();
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});
		reader2.setName("Reader 2");
		
		Thread writer = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i=50;i<55;i++) {
					cache.writeData(i);
					try {
						Thread.sleep(700);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				
			}
		});
		writer.setName("Writer");
		
		producer.start();
		consumer.start();
		reader1.start();
		reader2.start();
		writer.start();
		
	}
}


