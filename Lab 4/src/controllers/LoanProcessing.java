package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import models.DaoModel;
import records.BankRecords;
import views.LoanView;

/**
 * This program uses the existing bank records java program while enhancing it to store the information in a database and 
 * produce a graphical retrieval set of data. It has a method to check the metadata of the database to identify if the 
 * table already exists. If the table already exists, the program will skip the creation of the table and move on to just 
 * inserting the records using a prepared statement. Also included is a method to drop the table if it exists although those 
 * permissions were denied to the user given to us. The final 2 methods give a brief analysis of the loans in the system based on the 
 * individuals Pep status. 
 * @author Chris
 *
 */
public class LoanProcessing extends BankRecords {

	public static void main(String[] args) throws SQLException {
		String pep = "yes";
		String no = "no";
		// TODO Auto-generated method stub
		BankRecords bankRecord = new BankRecords();
		bankRecord.readData();
		DaoModel daoModel = new DaoModel();
		daoModel.checkTableCreated();
		daoModel.insertRecords(objects); // perform inserts
		ResultSet results;
		results = daoModel.retrieveRecords();
		new LoanView().runView(results);
		System.out.println("");
		System.out.println("Loan Analysis Section:");
		daoModel.loananaly(pep);
		daoModel.loananaly(no);
		daoModel.loananalyAll();
	}

}
