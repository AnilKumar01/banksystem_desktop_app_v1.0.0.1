package com.bs.employee.bean;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.bs.connection.MyConnection;

public class UpdateEmpDesignationBean {
	String empID;
	String empDesignation;

	public void setEmpID(String empID) {
		this.empID = empID;
	}

	public void setEmpDesignation(String empDesignation) {
		this.empDesignation = empDesignation;
	}

	public boolean updateDesignation() {
		int x = 0;
		try {
			Connection con = MyConnection.getMySQLConnection();
			Statement stmt = con.createStatement();
			String query = "UPDATE EMPLOYEE SET DESIGNATION = '"
					+ empDesignation + "' WHERE EMPLOYEE_ID ='" + empID + "'";
			x = stmt.executeUpdate(query);
			if (x != 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}
}
