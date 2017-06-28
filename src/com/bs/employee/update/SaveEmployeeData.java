package com.bs.employee.update;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bs.connection.MyConnection;
import com.bs.employee.bean.Employee;

public class SaveEmployeeData {

	public static boolean saveEmpData(Employee emp) {
		try {
			Connection con = MyConnection.getMySQLConnection();
			Statement stmt = con.createStatement();
			String query = "SELECT Count(EMPLOYEE_ID) FROM EMPLOYEE";
			String q = "SELECT DEPARTMENT_NO FROM DEPARTMENT WHERE DEPARTMENT_NAME='"
					+ emp.getDeparmentNo() + "'";
			ResultSet rs = stmt.executeQuery(query);
			int pre_employee_id = 12000;
			while (rs.next()) {
				pre_employee_id = Integer.parseInt(rs.getString(1)) + 1;
			}
			String emp_ID = "" + pre_employee_id;
			rs = stmt.executeQuery(q);
			String dept = "";
			while (rs.next()) {
				dept = rs.getString(1);
			}
			query = "INSERT INTO EMPLOYEE VALUES('" + emp_ID + "','"
					+ emp.getFirstName() + "','" + emp.getMiddleName() + "','"
					+ emp.getLastName() + "','" + emp.getGender() + "','"
					+ emp.getDateOfBirth() + "','" + emp.getPermanentAddress()
					+ "','" + emp.getCurrentAddress() + "','"
					+ emp.getContactNo() + "','" + emp.getEmail() + "','"
					+ emp.getEmergencyContact() + "','"
					+ emp.getHighestQualification() + "','"
					+ emp.getDesignation() + "','" + emp.getDateOfJoining()
					+ "','" + dept + "','" + emp.getBranchCode() + "')";
			int x = stmt.executeUpdate(query);
			if (x == 1) {
				return true;
			} else {
				return false;
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
}
