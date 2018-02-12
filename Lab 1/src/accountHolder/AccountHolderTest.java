//AccountHolderTest.java
/**
 * This program is for Lab 1 of ITMD 511
 * The program is designed to simulate a banking application where 
 * A user can set their initial balance via the constructor
 * And make deposits and withdrawals and run through their interest 
 * Gain at 4%. Additional features include the ability to change their 
 * Interest rate to see if another bank's interest would be more beneficial 
 * To the user. Accounts are charged $50 fee if withdrawal drops the balance
 * Below $500, and their ban\lance cannot go below $100. 
 * @author Chris Doherty
 * Completed testing on 2/4/2018 @ 11:03am
 *
 */
package accountHolder;

import java.text.DecimalFormat;


import java.util.Scanner;

public class AccountHolderTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AccountHolder aObj = null;
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		DecimalFormat df = new DecimalFormat("####0.00");
		// Ask user for beginning balance
		double initialbalance = 0.0;
		// loop for error trapping
		do {
			System.out.println("What is your initial Balance?");
			while (!scanner.hasNextDouble()) {
				System.out.println("Input is not valid. Balance must be a number. Please try again.");
				scanner.next();
			}
			initialbalance = scanner.nextDouble();
			if (initialbalance < 0.0) {
				System.out.println("Input is not valid. Balance cannot be negative.");
			}
		} while (initialbalance < 0.0);
		// instantiate class
		aObj = new AccountHolder(initialbalance);
		int again = 0;
		// calls the menu method
		do {
			if (again == 0 || again == 1){
				aObj.menu();
			}
			//asks the user if they want to make another transaction 
			System.out.println("Your current balance is " + df.format(aObj.getBalance()));
			System.out.println("Did you want to make another transaction?");
			System.out.println("1. Yes");
			System.out.println("2. Exit");
			aObj.exitPrintout();
			// while statement to check if the input is a double
			while (!scanner.hasNextInt()) {
				// error message if the input is not a double
				System.out.println("That is not a valid choice. Please input either 2 or 1");
				scanner.next();
			}
			// assigns input to variable
			again = scanner.nextInt();
			//prints out my name and date
			if (again == 2) {
				aObj.exitPrintout();
			}else {again = 0;}
		} while (again != 1 || again != 2); // keeps the loop up while input is not 2
	}//closes main
}//closes class
