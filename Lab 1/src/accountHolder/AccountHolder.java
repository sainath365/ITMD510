package accountHolder;

import java.util.Scanner;

public class AccountHolder {
	private static double annualInterestRate;
	private double balance;
	/**
	 * getter generated 
	 * @return
	 */
	public static double getAnnualInterestRate() {
		return annualInterestRate;
	}

	/**
	 * setter generated
	 * @param annualInterestRate
	 */
	public static void setAnnualInterestRate(double annualInterestRate) {
		AccountHolder.annualInterestRate = annualInterestRate;
	}

	/**
	 * getter generated 
	 * @return
	 */
	public double getBalance() {
		return balance;
	}

	/**
	 * setter generated
	 * @param balance
	 */
	public void setBalance(double balance) {
		this.balance = balance;
	}

	/**
	 * Constructor. 
	 * Accept initial balance 
	 * Set balance equal to value passed
	 * include error trapping for negative starting balance
	 * @param balance
	 */
	public void AccountHolder(double balance){
		
	}

	/**
	 * deposite instance method 
	 * @param balance
	 */
	public static void deposite(double balance){

	}
	
	/**
	 * withdrawal instance method
	 * @param balance
	 */
	public void withdrawal(double balance){

	}
	
	/**
	 * interest instance method
	 */
	public void monthlyInterest(){

		//update the account holders’ balance 
		balance += balance * (annualInterestRate / 12.0);
	}
	
	/**
	 *  Static monthly interest
	 * @param balance
	 */
	public static void modifyMonthlyInterest(double rate){
		
		if(rate>=0 && rate<=1.0){
			
			annualInterestRate = rate;
		}else{
			System.out.println("Rate inputted is not between 0 and 1.0");
		}
		
	}
	
	/**
	 * toString instance method
	 */
	public String toString(){
		return String.format("$%.2f", balance);

	}
	
	public double getUserInput(){
		Scanner scanner = new Scanner(System.in);
		
		double initialBal;
		do {
			System.out.println("What is your initial Balance?");
			while (!scanner.hasNextDouble()){
				String input = scanner.next();
				System.out.println("Input is not valid. Balance cannot be negative.");
			}
			initialBal = scanner.nextDouble();
		} while (initialBal < 0.0);
		return initialBal;
	}
	
	public static void menu(){
		//integer to capture what the user wants to do
		int choice = 0;
		//creates the scanner object
		Scanner scanner = new Scanner(System.in);
		//loop to keep menu displayed until the user puts in a correct input
		do {
			//output the menu choices
			System.out.println("What would you like to do?");
			System.out.println("1. Make a deposite");
			System.out.println("2. Make a withdrawal");
			System.out.println("3. See Balance");
			System.out.println("4. Exit");
			//while statement to check if the input is a int
			while(!scanner.hasNextInt()){
				String input = scanner.next();
				//error message if the input is not an int
				System.out.printf("\"%s\" is not a valid choice.\n", input);
			}
			//assign variable to input
			choice = scanner.nextInt();
		} while (choice != 0); //keeps the loop up while input is not an int
		
		//switch statement to do what the user wants to do
		switch (choice) {
		//if the option is 1
		case 1: 
			//variable for user input 
			double deposite;
			//loop to keep menu displayed until the user puts in a correct input
			do {
				//asks user the doposite amount 
				System.out.println("How much would you like to deposite?");
				//while statement to check if the input is a double
				while (!scanner.hasNextDouble()){
					//captures input in a string
					String input = scanner.next();
					//error message if the input is not a double
					System.out.println("That is not a valid amount to deposite");
				}
				//assigns user input to variable 
				deposite = scanner.nextDouble();
			} while (deposite < 0.0); //keeps the loop up while input is not an int
			/**
			 * Not sure why deposite needs to be a static method
			 */
			deposite(deposite);
			
			
				
		}//closes switch
		
		
	}
	public static void main(String[] args) {
		
	}
}
