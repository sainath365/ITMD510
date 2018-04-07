package models;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ThreadLocalRandom;

import records.BankRecords;

public class DaoModel {

	//Declare DB objects 
	DBConnect connection = null;
	Statement statement = null;
	String TableName = "C_DOHE_tab1"; 

	// constructor
	public DaoModel() { //create db object instance
		connection = new DBConnect();
	}

	public void checkTableCreated() {
		//makes the variable to store the database meta data
		DatabaseMetaData meta;
		try {
			//gets the metadata from the database
			meta = connection.connect().getMetaData();

			//
			ResultSet res = meta.getTables(null, null, TableName, new String[] {"TABLE"});

			//if the result is not empty the table (with my table name) is created
			if (res.next()) {
				System.out.println("Table " + TableName + " Exists");
			}
			//else my table is not created so it calls the create table function
			else {
				//calls function
				createTable();
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// CREATE TABLE METHOD
	public void createTable() {
		try {
			// Open a connection
			System.out.println("Connecting to a selected database to create Table...");
			System.out.println("Connected database successfully...");

			// Execute create query
			System.out.println("Creating table in given database...");

			//Creates a connection to the database
			statement = connection.connect().createStatement();

			//Creates a SQL Statement
			String sql = "CREATE TABLE "+ TableName + 
					"(pid INTEGER not NULL UNIQUE, " + 
					" id VARCHAR(10), " +
					" income numeric(8,2), " + 
					" pep VARCHAR(3), " + 
					" PRIMARY KEY ( pid ))";
			statement.executeUpdate(sql);
			System.out.println("Created table in given database...");
			connection.connect().close(); //close db connection 
		}catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		}
	}

	// CREATE TABLE METHOD
	public void DropTable() {
		try {
			//Creates a connection to the database
			statement = connection.connect().createStatement();

			//Creates a SQL Statement
			String sql = "DROP TABLE " +TableName+ "";

			statement.executeUpdate(sql);
			System.out.println("Table  deleted in given database...");
			connection.connect().close(); //close db connection 
		}catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		}
	}

	// INSERT INTO METHOD
	public void insertRecords(BankRecords[] robjs) {
		try {
			//prepared statement needs this
			Connection con = DriverManager.getConnection("jdbc:mysql://www.papademas.net:3306/510labs?autoReconnect=true&useSSL=false", "db510", "510");
			// Execute a query
			System.out.print("Inserting records into the table...");
			System.out.println(" Please Wait"); 

			//instantiate the prepared statement
			PreparedStatement sql = null;

			// Include all object data to the database table
			for (int i = 0; i < robjs.length; ++i) {
				int randomNum = ThreadLocalRandom.current().nextInt(0, 99999999);

				//Creates a SQL prepared Statement  
				sql = con.prepareStatement("INSERT INTO " + TableName + "(pid, id, income, pep) VALUES (?,?,?,?)");

				//sets the prepared value to desired data from objects
				sql.setInt(1, randomNum);
				sql.setString(2,robjs[i].getId());
				sql.setDouble(3,robjs[i].getIncome());
				sql.setString(4,robjs[i].getPep());

				//executes the prepared statement 	
				sql.executeUpdate();  

			}
			//prints done once the items are inserted into database
			System.out.println("Done");

			//closes the db connection
			connection.connect().close();

		} catch (SQLException se) {
			se.printStackTrace();
		}
	}


	public ResultSet retrieveRecords() throws SQLException {
		ResultSet results = null;

		//Creates a connection to the database
		statement = connection.connect().createStatement();

		String sql = "SELECT pid, id, income, pep FROM " + TableName +" ORDER BY pep DESC";

		//sets the results object of type of ResultSet
		results = statement.executeQuery(sql);

		//Closes the connection
		connection.connect().close();

		//returns the results
		return results;
	}

	public void loananaly(String pep) throws SQLException{
		//Creates a connection to the database
		statement = connection.connect().createStatement();

		String sql = "SELECT AVG(income) FROM " + TableName +" WHERE pep='"+pep+"'";
		ResultSet rs = statement.executeQuery(sql);
		while(rs.next()) {		
			System.out.println("The Average Income where pep is " +pep +": " + rs.getObject(1));
		}

	}
	public void loananalyAll() throws SQLException{
		//Creates a connection to the database
		statement = connection.connect().createStatement();

		String sql = "SELECT AVG(income) FROM " + TableName +"";
		ResultSet rs = statement.executeQuery(sql);
		while(rs.next()) {		
			System.out.println("The Average Income Regardless of pep is: " + rs.getObject(1));
		}

	}
}
