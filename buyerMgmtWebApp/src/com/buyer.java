package com;
import java.sql.*; 

import java.awt.PageAttributes.MediaType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class buyer {

// Sql connection
private Connection connect() 
{ 
Connection con = null; 
try
{ 
Class.forName("com.mysql.jdbc.Driver"); 
con = 
DriverManager.getConnection( 
"jdbc:mysql://127.0.0.1:3306/store_db", "root", ""); 
} 
catch (Exception e) 
{ 
e.printStackTrace(); 
} 
return con; 
}

	//read method
	public String readBuyer() 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con =connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for reading."; } 
	 // Prepare the html table to be displayed
	 output = "<table border='1'><tr><th>Buyer Name</th><th>Buyer Email</th>" 
	 +"<th>Buyer Phone</th>" 
	 + "<th>Update</th><th>Remove</th></tr>"; 
	 
	 String query = "select * from buyer"; 
	 
	 Statement stmt = con.createStatement(); 
	 ResultSet rs = stmt.executeQuery(query); 
	 // iterate through the rows in the result set
	 while (rs.next()) 
	 { 
	 String id= Integer.toString(rs.getInt("id")); 
	 String name = rs.getString("name"); 
	 String email = rs.getString("email"); 
	 String phone =  Integer.toString(rs.getInt("phone")); 

	
	 // Add into the html table
	 output += "<tr><td><input id='hidItemIDUpdate'  name='hidItemIDUpdate'  type='hidden' value='" + id
			 + "'>" + name + "</td>";
	 output += "<td>" + email + "</td>"; 
	 output +="<td>" + phone + "</td>"; 
	 
	 // buttons
	 output += "<td><input name='btnUpdate'  type='button' value='Update'  class='btnUpdate btn btn-secondary'></td>"+ "<td><input name='btnRemove' type='button' value='Remove'" +"class='btnRemove btn btn-danger' data-id='" + id + "'></td></tr>"; 
	
	
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	 catch (Exception e) 
	 { 
	 output = "Error while reading the buyers."; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
	//inset method
	public String insertBuyer(String name, String email, String phone) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con = connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for inserting."; } 
	 // create a prepared statement
	 String query = " insert into buyer (`id`,`name`,`email`,`phone`)"
	 + " values (?, ?, ?, ?)"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, 0); 
	 preparedStmt.setString(2, name); 
	 preparedStmt.setString(3, email); 
	 preparedStmt.setInt(4, Integer.parseInt(phone));
	 preparedStmt.execute(); 
	 con.close(); 
	 String newBuyers = readBuyer(); 
	 output = "{\"status\":\"success\", \"data\": \"" + 
			 newBuyers + "\"}"; 
	 } 
	  
	 catch (Exception e) 
	 { 
	 output = "{\"status\":\"error\", \"data\": \"Error while inserting the item.\"}"; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 } 
	
	
	//Update method
	
	public String updateBuyer(String id, String name, String email, String phone)
	{ 
		 String output = ""; 
		 try
		 { 
		 Connection con =connect(); 
		 if (con == null) 
		 {return "Error while connecting to the database for updating."; } 
		 // create a prepared statement
		 String query = "UPDATE buyer SET name=?,email=?,phone=? WHERE id=?"; 
		 PreparedStatement preparedStmt = con.prepareStatement(query); 
		 // binding values
		 preparedStmt.setString(1, name); 
		 preparedStmt.setString(2, email); 
		 preparedStmt.setInt(3,Integer.parseInt(phone)); 
		 preparedStmt.setInt(4, Integer.parseInt(id)); 

		 // execute the statement
		 preparedStmt.execute(); 
		 con.close(); 
		 String newBuyers = readBuyer(); 
		 output = "{\"status\":\"success\", \"data\": \"" + 
				 newBuyers + "\"}";  
		 } 
		 catch (Exception e) 
		 { 
		 output = "{\"status\":\"error\", \"data\": \"Error while updating the buyer.\"}"; 
		 System.err.println(e.getMessage()); 
		 } 
		 return output; 
		 } 
	
	//delete method
	public String deleteBuyer(String id) 
	 { 
	 String output = ""; 
	 try
	 { 
	 Connection con =connect(); 
	 if (con == null) 
	 {return "Error while connecting to the database for deleting."; } 
	 // create a prepared statement
	 String query = "delete from buyer where id=?"; 
	 PreparedStatement preparedStmt = con.prepareStatement(query); 
	 // binding values
	 preparedStmt.setInt(1, Integer.parseInt(id)); 
	 System.out.println(id);
	 // execute the statement
	 preparedStmt.execute(); 
	 con.close(); 
	 String newBuyers = readBuyer(); 
	 output = "{\"status\":\"success\", \"data\": \"" + 
			 newBuyers + "\"}";  
	 } 	 
	 catch (Exception e) 
	 { 
	 output = "{\"status\":\"error\", \"data\": \"Error while deleting the buyer\"}"; 
	 System.err.println(e.getMessage()); 
	 } 
	 return output; 
	 }
	

	
}
