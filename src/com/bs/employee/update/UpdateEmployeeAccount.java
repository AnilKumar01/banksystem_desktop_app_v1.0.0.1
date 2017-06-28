package com.bs.employee.update;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.bs.bankrelated.bean.DesktopUser;
import com.bs.connection.MyConnection;

public class UpdateEmployeeAccount {
	public static boolean changePassword(DesktopUser user, String newPwd) {
		try {
			Connection con = MyConnection.getMySQLConnection();
			Statement stmt = con.createStatement();
			String change_Account_Password = "UPDATE DESKTOP_APP_USERS SET PASSWORD='"
					+ newPwd + "' WHERE USERNAME='" + user.getUsername() + "'";
			int x = stmt.executeUpdate(change_Account_Password);
			if (x == 1) {
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			return false;
		}
		return false;
	}

	public static boolean changeUserName(DesktopUser user, String newPwd) {
		try {
			Connection con = MyConnection.getMySQLConnection();
			Statement stmt = con.createStatement();
			String change_Account_UserName = "UPDATE DESKTOP_APP_USERS SET USERNAME='"
					+ newPwd + "' WHERE USERNAME='" + user.getUsername() + "'";
			int x = stmt.executeUpdate(change_Account_UserName);
			if (x == 1) {
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			return false;
		}
		return false;
	}

	public static boolean changeUserNamePassword(DesktopUser user,
			String newUN, String newPwd) {
		try {
			Connection con = MyConnection.getMySQLConnection();
			Statement stmt = con.createStatement();
			String change_Account_UserName_Password = "UPDATE DESKTOP_APP_USERS SET USERNAME='"
					+ newUN
					+ "', "
					+ "PASSWORD='"
					+ newPwd
					+ "' "
					+ "WHERE USERNAME='" + user.getUsername() + "'";
			int x = stmt.executeUpdate(change_Account_UserName_Password);
			if (x == 1) {
				return true;
			}
		} catch (ClassNotFoundException | SQLException e) {
			return false;
		}
		return false;
	}
}
