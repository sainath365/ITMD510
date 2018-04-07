package records;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

/**
 * @author Chris
 * @param Type <BankRecords>
 * 
 * This program is the third lab for ITMD 510.
 * It is designed to perform comparisons on the data read in from bankrecords.txt
 * It extend the BankRecords class
 * It uses 3 comparator classes implementing java.util.Comparator to perform data analysis 
 * 
 *
 */
public class Records<R> extends BankRecords{

	//This creates File Writer object to output results to a file
	static File fout = null;
	static FileOutputStream fos = null;
	//Wrapper to write to file
	static BufferedWriter filewriter = null;
	//Object to format output 
	static DecimalFormat df = new DecimalFormat("##.##");
	//constructor
	public Records(){

		//Try catch block to make/open file
		try {
			fout = new File("bankrecords.txt");
			fos = new FileOutputStream(fout);
			filewriter = new BufferedWriter(new OutputStreamWriter(fos));

		} catch (IOException e) {
			e.printStackTrace();
		} 
	}

	//Main method to perform actions
	public static void main(String[] args)  {
		//Creates br object with type BankRecord 
		Records<BankRecords> br = new Records<BankRecords>();
		//Calls readData method form bankrecord.java file
		br.readData();
		//call LocatComp to analyze average income for each location
		LocatCompair();
		//call LocatComp to compare max/min incomes for each location
		MinMaxCompair(); 
		//call LocatComp to analyze females with mortgage and savings accounts for each location
		FwMaSCompair();
		//call LocatComp to analyze number of males with a car and 1 child for each location 
		MwCaCCompair();
		//prints out my name and current date
		br.exitPrintout();

		//Writes my name and current date to the file bankrecords.txt
		try {
			DateFormat tf = new SimpleDateFormat("dd/MM/yy HH:mm:ss");
			Date date = new Date();
			filewriter.newLine();
			filewriter.write("Current Date " + tf.format(date));
			filewriter.newLine();
			filewriter.write("Created By Chris Doherty \n");
			
		} catch (IOException e) {
			e.printStackTrace();
		} 

		//close out the buffer reader wrapper object after everything has been written
		try {
			filewriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to compare each region's average income
	 */
	private static void LocatCompair() {

		//Sorts the array of objects names objects for Location
		Arrays.sort(objects, new LocCompar());
		//initialize variables for region and income for location 
		double numOfTownPeople = 0, numOfInnerPeople = 0, numOfSuburbanPeople = 0, numOfRuralPeople = 0,
				townIncTotal = 0, innerIncTotal=0, suburbanIncTotal=0, ruralIncTotal=0;

		//increments the number of people per region and retrieves their averages
		for (int i=0; i<objects.length; i++)
			if (objects[i].getRegion().equals("RURAL")) { 
				ruralIncTotal += objects[i].getIncome();
				++numOfRuralPeople;
			}
			else if (objects[i].getRegion().equals("INNER_CITY")) 
			{ 
				innerIncTotal += objects[i].getIncome();
				++numOfInnerPeople;
			}
			else if (objects[i].getRegion().equals("SUBURBAN")) 
			{ 
				suburbanIncTotal += objects[i].getIncome();
				++numOfSuburbanPeople;
			}
			else if (objects[i].getRegion().equals("TOWN")) 
			{ 
				townIncTotal += objects[i].getIncome();
				++numOfTownPeople;
			}
			else {}


		//setup resulting averages
		double ruralAvg = ruralIncTotal/(numOfRuralPeople);
		double innerAvg = innerIncTotal/(numOfInnerPeople);
		double suburbanAvg = suburbanIncTotal/(numOfSuburbanPeople);
		double townAvg = townIncTotal/(numOfTownPeople);

		//formats the double averages to 2 decimals 
		String rA = df.format(ruralAvg);
		String iA = df.format(innerAvg);
		String sA = df.format(suburbanAvg);
		String tA = df.format(townAvg);

		//Output to the console
		System.out.println("Avg income for Inner City region $" + iA);
		System.out.println("Avg income for Rural region $" + rA);
		System.out.println("Avg income for Suburban region $" + sA);
		System.out.println("Avg income for Town region $" + tA);
		System.out.println("\n");

		//Writes the information to the file bankrecords.txt
		try {
			filewriter.write("Avg income for Rural region $" + rA);
			filewriter.newLine();
			filewriter.write("Avg income for Inner City region $" + iA);
			filewriter.newLine();
			filewriter.write("Avg income for Suburban region $" + sA);
			filewriter.newLine();
			filewriter.write("Avg income for Town region $" + tA);
			filewriter.newLine();
			filewriter.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}

	/**
	 * This method is used to analyze the data and return the maximum and minimum 
	 * incomes per location
	 */
	private static void MinMaxCompair() {

		//Sorts the array of objects names objects for Location
		Arrays.sort(objects, new minMaxIncome());

		/*
		 Created an ArrayLists to add the ordered value
		 I will take first and last element's value for the minimum and maximum 
		 this is possible because the compare method ordered the information
		 */
		ArrayList<Double> ruralArray = new ArrayList<Double>();
		ArrayList<Double> cityArray = new ArrayList<Double>();
		ArrayList<Double> suburbanArray = new ArrayList<Double>();
		ArrayList<Double> townArray = new ArrayList<Double>();

		//loop to add the values to the correct array list 
		for (int i=0; i<objects.length; i++)
			if (objects[i].getRegion().equals("RURAL")) { 
				ruralArray.add(objects[i].getIncome());
			}
			else if (objects[i].getRegion().equals("INNER_CITY")) 
			{ 
				cityArray.add(objects[i].getIncome());
			}
			else if (objects[i].getRegion().equals("SUBURBAN")) 
			{ 
				suburbanArray.add(objects[i].getIncome());
			}
			else if (objects[i].getRegion().equals("TOWN")) 
			{ 
				townArray.add(objects[i].getIncome());
			}
			else {}


		//Output to the console
		System.out.println("Inner City region Minimum income $" + cityArray.get(0));
		System.out.println("Rural region Minimum income $" + ruralArray.get(0));		
		System.out.println("Surburban region Minimum income $" + suburbanArray.get(0));
		System.out.println("Town region Minimum income $" + townArray.get(0));
		System.out.println("\n");
		System.out.println("Inner City region Max income $" + cityArray.get(cityArray.size()-1));
		System.out.println("Rural region Max income $" + ruralArray.get(ruralArray.size()-1));
		System.out.println("Surburban region Max income $" + suburbanArray.get(suburbanArray.size()-1));
		System.out.println("Town region Max income $" + townArray.get(townArray.size()-1));
		System.out.println("\n");

		//Writes the information to the file bankrecords.txt
		try {
			filewriter.write("Minimum income for Rural region " + ruralArray.get(0));
			filewriter.newLine();
			filewriter.write("Minimum income for Inner City region " + cityArray.get(0));
			filewriter.newLine();
			filewriter.write("Minimum income for Surburban region " + suburbanArray.get(0));
			filewriter.newLine();
			filewriter.write("Minimum income for Town region " + townArray.get(0));
			filewriter.newLine();
			filewriter.newLine();
			filewriter.write("Max income for Rural region " + ruralArray.get(ruralArray.size()-1));
			filewriter.newLine();
			filewriter.write("Max income for Inner City region " + cityArray.get(cityArray.size()-1));
			filewriter.newLine();
			filewriter.write("Max income for Surburban region " + suburbanArray.get(suburbanArray.size()-1));
			filewriter.newLine();
			filewriter.write("Max income for Town region " + townArray.get(townArray.size()-1));
			filewriter.newLine();
			filewriter.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}

	/**
	 * This method is used to analyze the data and return the
	 * number of females with both a mortgage and savings account per location
	 * (F females w with M mortgage a and S savings
	 */
	private static void FwMaSCompair() {

		//Sorts the array of objects names objects for Location not really needed
		Arrays.sort(objects, new FwMaSLocation());
		//initialize variables for region and income for location 
		int numOfTownPeople = 0, numOfInnerPeople = 0, numOfSuburbanPeople = 0, numOfRuralPeople = 0;

		//loop to increment the number of people per location 
		for (int i=0; i<objects.length; i++)
			if (objects[i].getRegion().equals("RURAL") && objects[i].getSex().equals("FEMALE")
					&& objects[i].getSave_act().equals("YES") && objects[i].getMortgage().equals("YES")) { 
				++numOfRuralPeople;
			}
			else if (objects[i].getRegion().equals("INNER_CITY") && objects[i].getSex().equals("FEMALE")
					&& objects[i].getSave_act().equals("YES") && objects[i].getMortgage().equals("YES")) 
			{ 
				++numOfInnerPeople;
			}
			else if (objects[i].getRegion().equals("SUBURBAN") && objects[i].getSex().equals("FEMALE")
					&& objects[i].getSave_act().equals("YES") && objects[i].getMortgage().equals("YES")) 
			{
				++numOfSuburbanPeople;
			}
			else if (objects[i].getRegion().equals("TOWN") && objects[i].getSex().equals("FEMALE")
					&& objects[i].getSave_act().equals("YES") && objects[i].getMortgage().equals("YES")) 
			{ 
				++numOfTownPeople;
			}
			else {}

		//Output to the console
		System.out.println("Inner City region Females with Mortgage and Savings Account " + numOfInnerPeople);
		System.out.println("Rural region Females with Mortgage and Savings Account " + numOfRuralPeople);
		System.out.println("Suburban region Females with Mortgage and Savings Account " + numOfSuburbanPeople);
		System.out.println("Town region Females with Mortgage and Savings Account " + numOfTownPeople);
		System.out.println("\n");

		//Writes the information to the file bankrecords.txt
		try {
			filewriter.write("Rural region Females with Mortgage and Savings Account " + numOfRuralPeople);
			filewriter.newLine();
			filewriter.write("Inner City region Females with Mortgage and Savings Account " + numOfInnerPeople);
			filewriter.newLine();
			filewriter.write("Suburban region Females with Mortgage and Savings Account " + numOfSuburbanPeople);
			filewriter.newLine();
			filewriter.write("Town region Females with Mortgage and Savings Account " + numOfTownPeople);
			filewriter.newLine();
			filewriter.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}  
	}

	/**
	 * This method is used to analyze the data and return the
	 * number of males with both a car and 1 child per location
	 * (M males w with C cars and C children)
	 */
	private static void MwCaCCompair() {

		//initialize variables for region and income for location 
		int numOfTownPeople = 0, numOfInnerPeople = 0, numOfSuburbanPeople = 0, numOfRuralPeople = 0;

		//loop to increment the number of people per location
		for (int i=0; i<objects.length; i++)
			if (objects[i].getRegion().equals("RURAL") && objects[i].getSex().equals("MALE")
					&& objects[i].getChildren()==1 && objects[i].getCar().equals("YES")) { 
				++numOfRuralPeople;
			}
			else if (objects[i].getRegion().equals("INNER_CITY") && objects[i].getSex().equals("MALE")
					&& objects[i].getChildren()==1 && objects[i].getCar().equals("YES")) 
			{ 
				++numOfInnerPeople;
			}
			else if (objects[i].getRegion().equals("SUBURBAN") && objects[i].getSex().equals("MALE")
					&& objects[i].getChildren()==1 && objects[i].getCar().equals("YES")) 
			{
				++numOfSuburbanPeople;
			}
			else if (objects[i].getRegion().equals("TOWN") && objects[i].getSex().equals("MALE")
					&& objects[i].getChildren()==1 && objects[i].getCar().equals("YES")) 
			{ 
				++numOfTownPeople;
			}
			else {}

		//Output to the console
		System.out.println("Inner City region Males with 1 Child and a Car " + numOfInnerPeople);
		System.out.println("Rural region Males with 1 Child and a Car " + numOfRuralPeople);
		System.out.println("Suburban region Males with 1 Child and a Car " + numOfSuburbanPeople);
		System.out.println("Town region Males with 1 Child and a Car " + numOfTownPeople);
		System.out.println("\n");

		//Writes the information to the file bankrecords.txt
		try {
			filewriter.write("Rural region Males with 1 Child and a Car " + numOfRuralPeople);
			filewriter.newLine();
			filewriter.write("Inner City region Males with 1 Child and a Car " + numOfInnerPeople);
			filewriter.newLine();
			filewriter.write("Suburban region Males with 1 Child and a Car " + numOfSuburbanPeople);
			filewriter.newLine();
			filewriter.write("Town region Males with 1 Child and a Car " + numOfTownPeople);
			filewriter.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

/**
 * Sorts the regions
 */
class LocCompar implements Comparator<BankRecords>{

	@Override
	public int compare(BankRecords x, BankRecords y) {
		// TODO Auto-generated method stub
		int result = x.getRegion().compareTo(y.getRegion()); 
		return result;
	}

}

/**
 * Sorts the array to order from minimum to maximum
 */
class minMaxIncome implements Comparator<BankRecords>{

	@Override
	public int compare(BankRecords o1, BankRecords o2) {
		// TODO Auto-generated method stub
		int result = o1.getIncome().compareTo(o2.getIncome());
		return result;
	}

}

/**
 * 
 * Sorts the array based on sex, savings account, mortgage, and region 
 * even though it is not really needed
 * I could have split the minimum and maximum sort but choose to do it all at once
 * added this compare to fulfill requirements of 3 comparator classes set forth in instructions
 *
 */
class FwMaSLocation implements Comparator<BankRecords>{

	@Override
	public int compare(BankRecords x, BankRecords y) {
		// TODO Auto-generated method stub
		int result = x.getSex().compareTo(y.getSex());
		if (result != 0) return result;
		//Second sort
		int result1 = x.getSave_act().compareTo(y.getSave_act());
		if (result1 != 0) return result1;
		//Third Sort
		int result2 = x.getMortgage().compareTo(y.getMortgage());
		if (result2 !=0) return result2;
		int result3 = x.getRegion().compareTo(y.getRegion());
		return result3;
	}

}