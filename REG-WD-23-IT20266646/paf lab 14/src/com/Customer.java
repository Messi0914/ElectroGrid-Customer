package com;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Customer {
	
	private Connection connect() {
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/electriproject?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
			
			//For testing
			System.out.print("Successfully connected");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	
	
	
	
	//Read function
	public String readcustomer() 
	
	{
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			
			output = "<table border='1'><tr><th>customerName</th>" +"<th>customerAddress</th><th>customerNIC</th>"+ "<th>customerEmail</th><th>customerPNO</th>" + "<th>Update</th><th>Remove</th></tr>";
			
			String query = "select * from customerm";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
		
			while (rs.next()) {
				
				 String cID  = Integer.toString(rs.getInt("cID"));
				String customerName = rs.getString("customerName");
				String customerAddress = rs.getString("customerAddress");
				String customerNIC = rs.getString("customerNIC");
				String customerEmail = rs.getString("customerEmail");
				String customerPNO = rs.getString("customerPNO");

		
				 // Add a row into the html table
		
				output += "<td>" + customerName + "</td>";
				output += "<td>" + customerAddress + "</td>";
				output += "<td>" + customerNIC + "</td>";
				output += "<td>" + customerEmail + "</td>";
				output += "<td>" + customerPNO + "</td>";
				
				// Buttons
				 output += 
				   "<td><input name='btnUpdate' type='button' value='Update' " + "class='btnUpdate btn btn-secondary' data-cid='" + cID + "'></td>"
				 + "<td><input name='btnRemove' type='button' value='Remove'class='btnRemove btn btn-danger' data-cid='" + cID + "'>"+"</td>"
				 + "</form></td></tr>";	
				
			}
			con.close();
			// Complete the html table
			output += "</table>";
			
		} catch (Exception e) {
			output = "Error while reading the customer.";
			System.err.println(e.getMessage());
		}
		return output;
	}	
	
	
	
	
	
	//Insert function
	public String insertCustomer(String CName, String Address, String NIC, String Email, String Phone) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			
			String query = " insert into customerm(`cID`,`customerName`,`customerAddress`,`customerNIC`,`customerEmail`,`customerPNO`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
		
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, CName);
			preparedStmt.setString(3, Address);
			preparedStmt.setString(4, NIC);
			preparedStmt.setString(5, Email);
			preparedStmt.setString(6, Phone);
			 //execute the statement
			 preparedStmt.execute(); 
			 con.close(); 
		
			 
			 
			 String newCustomer = readcustomer();
			 output = "{\"status\":\"success\", \"data\": \"" + newCustomer + "\"}";
		 } 
		catch (Exception e) 
		 { 
			output = "{\"status\":\"error\", \"data\": \"Error while inserting the Customer.\"}";
			 System.err.println(e.getMessage()); 
		 } 
		return output; 
	}	
	
	
	//Delete function
	public String deleteCustomer(String cID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

		
			String query = "delete from customerm where cID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			
			preparedStmt.setInt(1, Integer.parseInt(cID));

			// execute the statement
			preparedStmt.execute();
			con.close();
			
			String newCustomer = readcustomer();
			output = "{\"status\":\"success\", \"data\": \"" + newCustomer + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the customer.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	
	//Update function
	public String updateCustomer(String cID, String CName, String Address, String NIC, String Email, String Phone) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			
			String query = "UPDATE customerm SET customerName=?,customerAddress=?,customerNIC=?,customerEmail=?,customerPNO=?" + "WHERE cID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

		
			preparedStmt.setString(1, CName);
			preparedStmt.setString(2, Address);
			preparedStmt.setString(3, NIC);
			preparedStmt.setString(4, Email);
			preparedStmt.setString(5, Phone);
			preparedStmt.setInt(6, Integer.parseInt(cID));

			// execute the statement
					preparedStmt.execute();
					con.close();

					String newCustomer = readcustomer();
					output = "{\"status\":\"success\", \"data\": \"" + newCustomer + "\"}";
		}
		catch (Exception e)
		{
			output = "{\"status\":\"error\", \"data\": \"Error while updating the customer.\"}";
			System.err.println(e.getMessage());
		}
		return output;
	}
}
