/* *****************************************************************************
 * Project: Bank System
 * Purpose: Creating a connection class
 * Author: Anil Kumar(dac11)
 * Filename: MyConnection.java
 * Version: 1.0
 * Start date: 1-Dec-2014
 * End date: 
 * *****************************************************************************/

package com.bs.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
	public static Connection getMySQLConnection() throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver");
		return DriverManager.getConnection("jdbc:mysql://localhost:3306/bank_system", "root", "cdacacts");
	}
}
