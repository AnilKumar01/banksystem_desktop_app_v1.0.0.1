package com.bs.operations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;

import com.bs.bankrelated.bean.DesktopUser;
import com.bs.connection.MyConnection;

public class DesktopLogin implements MasterOperations {

	@Override
	public boolean desktopUserLogin(DesktopUser user) {
		try {
			Connection con = MyConnection.getMySQLConnection();
			Statement stmt = con.createStatement();
			String query = "select * from desktop_app_users WHERE USERNAME='"
					+ user.getUsername() + "'";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				user.setPassword(rs.getString("PASSWORD"));
				user.setAccountStatus(rs.getString("ACCOUNT_STATUS"));
				user.setAccountLevel(rs.getString("ACCOUNT_LEVEL"));
			}
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			return false;
		}
	}

	@Override
	public boolean branchList(Map<String, String> branchLocations) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean departmentList(Map<Integer, String> deptList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean designationList(List<String> designList) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean printStatementsToFile(String accountNumber, String statements) {
		// TODO Auto-generated method stub
		return false;
	}

}
