import java.util.Random;

public class BankAccount implements Measurable {
	private double balance;
	private String accountNumber;
	
	public BankAccount(){
		accountNumber = new Random().nextInt(9999) + 1 + "";
		while (accountNumber.length() < 4) {
			accountNumber = '0' + accountNumber;
		}
	}	
	
	public BankAccount(double startBalance){
		this();
		setBalance(startBalance);
	}
	
	public BankAccount(double startBalance, String accountNumber) {
		this(startBalance);
		this.accountNumber = accountNumber;
	}
	
	public BankAccount(BankAccount accountToCopy){
		this.balance = accountToCopy.balance;
		this.accountNumber = accountToCopy.accountNumber;
	}
	
	private void setBalance(double amount) {
		if (amount > 0) balance = amount;
	}
	
	public double getBalance() {
		return balance;
	}

	public void deposit(double amount) {
		if (amount > 0) {
			balance += amount;
		}
	}
	
	protected boolean sufficientFunds(double amount) {
		System.out.println("In BankAccount");
		return amount > 0 && balance - amount >= 0.0; 
	}
	
	public void withdraw(double amount) {
		if (sufficientFunds(amount)) {
			balance -= amount;
		}
	}
	
	public void transfer(double amount, BankAccount toAccount) {
		if (sufficientFunds(amount)) {
			withdraw(amount);
			toAccount.deposit(amount);
		}
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	public String toString() {
		return accountNumber + ": " + balance;
	}

	@Override
	public double getMeasure() {
		return balance;
	}
}