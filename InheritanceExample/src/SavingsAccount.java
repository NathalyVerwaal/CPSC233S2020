public class SavingsAccount extends BankAccount {
	private double annualInterestRate = 0.05;
	private double minimumBalance = 1000.0;
	
	public double getAnnualInterestRate() {
		return annualInterestRate;
	}
	
	public void setAnnualInterestRate(double newRate) {
		if (newRate >= 0.0 && newRate <= 1.0) {
			annualInterestRate = newRate;
		}
	}
	
	public void depositMonthlyInterest() {
		deposit(getBalance() * (annualInterestRate / 12) );
	}
	
	protected boolean sufficientFunds(double amount) {
		System.out.println("In SavingsAccount ");
		return amount > 0 && getBalance() - amount >= minimumBalance;
	}
	
	public static void main(String[] args) {
		BankAccount sa = new SavingsAccount();
		BankAccount ba = new BankAccount(1000);
		sa.deposit(2000);
		sa.withdraw(50);
		System.out.println("Balance: " + sa.getBalance());
		sa.transfer(50, ba);
		System.out.println("Balance: " + sa.getBalance());
		
		sa.transfer(1000, ba);
		System.out.println("Balance: " + sa.getBalance());
	}
}