package bankclasses;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Account {

	protected double balance;
	protected Lock balanceLock;
	protected Condition enoughFundsCondition;
	protected int accountNumber;

	public Account(int acc, double bal) {
		this.accountNumber = acc;
		this.balance = bal;
	
		balanceLock = new ReentrantLock();
		enoughFundsCondition = balanceLock.newCondition();
	}


	public void getBalance() {
		balanceLock.lock();
		try {
			
			System.out.println("Thread with ID "+ Thread.currentThread().getId()+ " getting Balance...Balance £" + balance);
			enoughFundsCondition.signalAll();
		} finally {
			balanceLock.unlock();
		}
	}

	public void withdraw(double amount) throws InterruptedException {

		boolean stillWaiting = true;
		balanceLock.lock();

		if(balance < amount){
			System.out.println("Thread with ID " + Thread.currentThread().getId() +" Not enough money...waiting for deposit");

		}
		try {

			while (balance < amount) {
				if (!stillWaiting){
					Thread.currentThread().interrupt();
				}
				stillWaiting = enoughFundsCondition.await(5, TimeUnit.SECONDS);
			}
			
			System.out.println("Thread with ID " + Thread.currentThread().getId() + " Withdraw Amount £" + amount);
			balance = balance - amount;
			System.out.println("Thread with ID " + Thread.currentThread().getId() + " New balance £" + balance);
		} finally {
			balanceLock.unlock();
		}
	}

	
	public void deposit(double amount) {
		balanceLock.lock();
		try {
			if (amount > 0) {
				System.out.println("Thread with ID " + Thread.currentThread().getId() + " Deposit amount £" + amount);
				balance = balance + amount;
			}
			System.out.println("Thread with ID " + Thread.currentThread().getId() + " New balance £" + balance);
			enoughFundsCondition.signalAll();
		} finally {
			balanceLock.unlock();
		}
	}

}