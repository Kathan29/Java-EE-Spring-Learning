package concurrency;

public class ThreadPriorityDemo {
	
	public static void main(String[] args) {
		
		System.out.println(Thread.currentThread()); //main thread
		
		EmailCampaign task1 = new EmailCampaign();
		//DataAggregator task2 = new DataAggregator();
		
		Thread thread1 = new Thread(task1);
		Thread thread2 = new Thread(new DataAggregator());
		
		thread1.setName("Email Campaign");
		thread2.setName("Data Aggregator");
		
		//By default priority is set to 5, which is of main method thread also.
		thread1.setPriority(Thread.MAX_PRIORITY);
		thread2.setPriority(Thread.MIN_PRIORITY);
		
		thread1.start();
		thread2.start();
		
		//Also if we want that our main method ends only after any particular thread ends,
		//then we use join() and if we pass any argument then it means it will wait till that many ms
		try {
			thread2.join();
			//thread2.join(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println("In main method");
		
	}
}

class EmailCampaign implements Runnable{

	@Override
	public void run() {
		for(int times = 0;times<10;times++){
			System.out.println(Thread.currentThread().getName());
			
			//It means we are giving hint to thread scheduler , that it is ready to give up 
			// current CPU scheduled to it, so depends on thread scheduler of whether to take cpu or not
			if(times==5) {
				Thread.currentThread().yield();
			}
		}	
	}	
}

class DataAggregator implements Runnable{

	@Override
	public void run() {
		for(var times = 0;times<10;times++) {
			System.out.println(Thread.currentThread().getName());
		}
		
	}
	
}