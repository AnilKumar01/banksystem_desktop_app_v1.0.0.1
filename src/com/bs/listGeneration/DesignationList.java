/*Project: Bank System
 *Module: List Generation
 *Aim: Generate the Designation List
 *Author: Shashi Bhushan(DAC76)
 *Coded on: 24 Jan 2015
 *Place: CDAC Bangalore
 * 
 * */
package com.bs.listGeneration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.bs.connection.MyConnection;

public class DesignationList {
	public static List<String> getDesigList() throws ClassNotFoundException,
			SQLException {
		List<String> desiglist = new ArrayList<String>();
		Connection con = MyConnection.getMySQLConnection();
		Statement stmt = con.createStatement();

		String query = "SELECT JOB_NAME FROM JOBS";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			desiglist.add(rs.getString(1));
		}
		return desiglist;
	}

}
