package application;

public class TraderAccount extends Account implements TraderOperations {
	
	public int userID;
	public String firstName;
	public int lastName;	
	public char gender;
	public String address;
	public String city;
	public String state;
	public int zipcode;
	public String email;
	public int phoneNumber;
	public String bankAccountName;
	public int accountNumber;
	public int routingNumber;
	
	public Transaction transaction;

	@Override
	public void viewTransactionHistory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void viewCurrencyHistory() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void buy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sell() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deposit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void withdraw() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addBankAccount() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void alterPaymentDetails() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAccountInformation() {
		// TODO Auto-generated method stub
		
	}
}
