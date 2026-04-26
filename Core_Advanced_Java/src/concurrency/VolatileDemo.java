package concurrency;

import java.util.concurrent.TimeUnit;

public class VolatileDemo {
	
	public static volatile boolean stop;
	
	public static void main(String[] args) {
		
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(!stop) System.out.println("In while ...");
			}
			
		}).start();
		
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stop = true;
	}
}
