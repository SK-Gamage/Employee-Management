package com;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Employee {
	
	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/electrodb", "root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	
	//retrieve
	public String readEmployee()
	{
		String output = ""; 
		
	try   
	{    
		Connection con = connect(); 
	
		if (con == null)    
		{
			return "Error while connecting to the database for reading."; 
		} 
 
		// Prepare the HTML table to be displayed    
		output = "<table class='table' border='1'><thead class='table-dark'>"
				+ "<th>Name</th>"
				+ "<th>Age</th>"
				+ "<th>Address</th>"
				+ "<th>Email</th>"
				+ "<th>Tel No</th>"
				+ "<th>Type</th>"
				+ "<th>Update</th>"
				+ "<th>Remove</th></thead>";
 
		String query = "select * from employee"; 
		Statement stmt = con.createStatement(); 
		ResultSet rs = stmt.executeQuery(query); 
 
		// iterate through the rows in the result set    
		while (rs.next())    
		{     
			String empId = Integer.toString(rs.getInt("empId")); 
			String name = rs.getString("name");
			String age = rs.getString("age");
			String address = rs.getString("address");
			String email = rs.getString("email");
			String telno = rs.getString("telno");
			String type = rs.getString("type");

			// Add into the HTML table 
			output += "<tr><td><input id='hidEmployeeIDUpdate' "
					+ "name='hidEmployeeIDUpdate' "
					+ "type='hidden' value='" + empId + "'>" 
					+ name + "</td>"; 
			output += "<td>" + age + "</td>";
			output += "<td>" + address + "</td>";
			output += "<td>" + email + "</td>";
			output += "<td>" + telno + "</td>";
			output += "<td>" + type + "</td>";

			//buttons
			output += "<td><input name='btnUpdate' type='button' value='Update' class='btnUpdate btn btn-secondary' data-empid='" + empId + "'></td>"       
					+ "<td><input name='btnRemove' type='button' value='Remove' class='btnRemove btn btn-danger' data-empid='" + empId + "'>" + "</td></tr>"; 
	
		}
		con.close(); 
 
		// Complete the HTML table    
		output += "</table>";   
		
	}   
			catch (Exception e)   
		{    
			output = "Error while reading the Employee.";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
	
	
	//Add details about the Employee
	public String insertEmployee(String name, String age, String address, String email, String telno, String type)  
	{   
		String output = ""; 
	 
		try
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for inserting.";
			}
			
			// create a prepared statement 
			String query = " insert into employee (empId , name , age , address , email, telno, type)"+ " values (?, ?, ?, ?, ?, ?, ?)"; 
	 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			 preparedStmt.setInt(1, 0);
			 preparedStmt.setString(2, name);
			 preparedStmt.setString(3, age);
			 preparedStmt.setString(4, address);
			 preparedStmt.setString(5, email);
			 preparedStmt.setString(6, telno);
			 preparedStmt.setString(7, type);
			
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	   
			String newEmployee = readEmployee(); 
			output =  "{\"status\":\"success\", \"data\": \"" + newEmployee + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while inserting the Employee.\"}";  
			System.err.println(e.getMessage());   
		} 
		
	  return output;  
	}
	
	
	//update employee
	public String updateEmployee(String empId, String name, String age, String address, String email, String telno, String type)    
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for updating.";
			} 
	 
			// create a prepared statement    
			String query = "UPDATE employee SET name=?,age=?,address=?,email=?,telno=?,type=? WHERE empId=?"; 
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setString(1, name);
			preparedStmt.setString(2, age);
			preparedStmt.setString(3, address);
			preparedStmt.setString(4, email);
			preparedStmt.setString(5, telno);
			preparedStmt.setString(6, type);
			preparedStmt.setInt(7, Integer.parseInt(empId)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newEmployee = readEmployee();    
			output = "{\"status\":\"success\", \"data\": \"" + newEmployee + "\"}";    
		}   
		catch (Exception e)   
		{    
			output =  "{\"status\":\"error\", \"data\": \"Error while updating the Employee.\"}";   
			System.err.println(e.getMessage());   
		} 
	 
	  return output;  
	}
	
	
	//delete employee
	public String deleteEmployee(String empId)   
	{   
		String output = ""; 
	 
		try   
		{    
			Connection con = connect(); 
	 
			if (con == null)    
			{
				return "Error while connecting to the database for deleting."; 
			} 
	 
			// create a prepared statement    
			String query = "delete from employee where empId=?";  
	 
			PreparedStatement preparedStmt = con.prepareStatement(query); 
	 
			// binding values    
			preparedStmt.setInt(1, Integer.parseInt(empId)); 
	 
			// execute the statement    
			preparedStmt.execute();    
			con.close(); 
	 
			String newEmployee = readEmployee();  
			    
			output = "{\"status\":\"success\", \"data\": \"" +  newEmployee + "\"}";    
		}   
		catch (Exception e)   
		{    
			output = "{\"status\":\"error\", \"data\":\"Error while deleting the Employee.\"}";    
			System.err.println(e.getMessage());   
		} 
	 
		return output;  
	}
}
