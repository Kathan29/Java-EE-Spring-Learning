package concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {
	
	public static class Meetup{
		private String name;
		private AtomicInteger count = new AtomicInteger(1);
		
		public Meetup(String name) {
			this.name = name;
		}
		
		public int getCount() {
			return count.get();
		}
		
		public void attending(int people){
			if(people==1) {
				count.incrementAndGet();
			}else {
				count.addAndGet(people);
			}
			//System.out.println(count);
		}
		
		public void notAttending(int people) {
			if(people==1) {
				count.decrementAndGet();
			}else {
				
				int currentCount = count.get();
				int reducedCount = currentCount - people;
				count.compareAndSet(currentCount, reducedCount);
			}
		}
	}
	
	public static void main(String[] args) {
		AtomicDemo.Meetup nitMeetup = new AtomicDemo.Meetup("NIT T meetup");
		
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				nitMeetup.attending(4);
				System.out.println(Thread.currentThread().getName() + " is attending , so total Count "+nitMeetup.getCount());
			}
			
		});
		
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				nitMeetup.attending(3);
				System.out.println(Thread.currentThread().getName() + " is attending , so total Count "+nitMeetup.getCount());
				nitMeetup.notAttending(3);
				System.out.println(Thread.currentThread().getName() + " is not attending , so total Count "+nitMeetup.getCount());
			}
			
		});
		
		Thread t3 = new Thread(new Runnable() {

			@Override
			public void run() {
				nitMeetup.attending(1);
				System.out.println(Thread.currentThread().getName() + " is attending , so total Count "+nitMeetup.getCount());
			}
			
		});
		
		t1.setName("Guest 1");
		t2.setName("Guest 2");
		t3.setName("Guest 3");
		
		t1.start();
		sleep(1);
		t2.start();
		sleep(1);
		t3.start();
		sleep(1);
		
		System.out.println("Final Guest Count : "+nitMeetup.getCount());
		
	}

	private static void sleep(int time) {
		// TODO Auto-generated method stub
		try {
			TimeUnit.SECONDS.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
