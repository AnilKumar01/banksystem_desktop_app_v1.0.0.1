package com.bs.listGeneration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bs.connection.MyConnection;

public class BranchList {
	public static List<String> getBranchList() throws ClassNotFoundException,
			SQLException {
		List<String> branchlist = new ArrayList<String>();
		Connection con = MyConnection.getMySQLConnection();
		Statement stmt = con.createStatement();
		String query = "SELECT Branch_COde FROM Branch_List";
		ResultSet rs = stmt.executeQuery(query);
		while (rs.next()) {
			branchlist.add(rs.getString(1));
		}
		return branchlist;
	}
}
