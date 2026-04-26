package concurrency;

public class RaceConditionDemo {

	public static void main(String[] args) {
		
		BankAccount task = new BankAccount();
		task.setBalance(100);
		Thread john = new Thread(task);
		Thread anita = new Thread(task);
		
		john.setName("john");
		anita.setName("anita");
		
		john.start();
		anita.start();
	}

}

class BankAccount implements Runnable{

	private int balance;
	public synchronized void setBalance(int balance) {
		this.balance = balance;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		makeWithDrawl(75);
		if(balance<0) {
			System.out.println("Money overdrawn");
		}
	}

	private synchronized void makeWithDrawl(int amount) {

		if(balance>=amount) {
			System.out.println("Money will be withdrawn by "+Thread.currentThread().getName());
			balance -= amount;
			System.out.println("Money successful withdrawn by : "+Thread.currentThread().getName());
		}else {
			System.out.println("Balance is not sufficient to make transaction for " + Thread.currentThread().getName());
		}
		
	}
	
}