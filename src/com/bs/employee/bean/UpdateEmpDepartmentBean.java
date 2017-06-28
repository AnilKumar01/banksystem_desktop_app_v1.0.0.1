package com.bs.employee.bean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bs.connection.MyConnection;

public class UpdateEmpDepartmentBean {
	String empId;
	String empDept;

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public void setEmpDept(String empDept) {
		this.empDept = empDept;
	}

	public boolean updateEmpDepartment() {
		int x = 0;
		String deptNo = "";
		try {
			Connection con = MyConnection.getMySQLConnection();
			Statement stmt = con.createStatement();
			String query1 = "SELECT DEPARTMENT_NO FROM DEPARTMENT WHERE DEPARTMENT_NAME='"
					+ empDept + "'";
			ResultSet rs = stmt.executeQuery(query1);
			while (rs.next()) {
				deptNo = rs.getString(1);
			}
			String query = "UPDATE EMPLOYEE SET Department_No = '" + deptNo
					+ "'WHERE EMPLOYEE_ID='" + empId + "'";
			x = stmt.executeUpdate(query);
			if (x != 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException | ClassNotFoundException e) {
			return false;
		}
	}
}
