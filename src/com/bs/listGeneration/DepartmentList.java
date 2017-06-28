/*Project: Bank System
 *Module: List Generation
 *Aim: Generate the DepartmentList
 *Author: Shashi Bhushan(DAC76)
 *Coded on: 19 Jan 2015
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

public class DepartmentList {
	public static List<String> getDeptList() throws ClassNotFoundException,
			SQLException {
		List<String> deptlist = new ArrayList<String>();
		Connection con = MyConnection.getMySQLConnection();
		Statement stmt = con.createStatement();

		String query = "SELECT Department_Name FROM department";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			deptlist.add(rs.getString(1));
		}
		return deptlist;
	}
}
