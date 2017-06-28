package com.bs.employee.bean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bs.connection.MyConnection;

public class UpdateEmpSalaryBean {
	String empId;
	String salary;

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public void setSalary(String salary) {
		this.salary = salary;
	}

	public boolean updateEmpSalary() {
		int x = 0;
		double basic_Salary = Double.parseDouble(salary);
		double hra = (basic_Salary * 8.5) / 100;
		double ta = (basic_Salary * 9.9) / 100;
		double da = (basic_Salary * 99.9) / 100;
		double ma = (basic_Salary * 2.6) / 100;
		double oa = (basic_Salary * 4.8) / 100;
		double pf = (basic_Salary * 12) / 100;
		double total_Salary = basic_Salary + hra + ta + da + ma + oa - pf;
		try {
			Connection con = MyConnection.getMySQLConnection();
			Statement stmt = con.createStatement();
			String query = "SELECT EMPLOYEE_ID FROM EMPLOYEE WHERE EMPLOYEE_ID='"
					+ empId + "'";
			String query1 = "UPDATE EMPLOYEE_SALARY SET BASIC = "
					+ basic_Salary + ", HRA = " + hra + ", TA = " + ta
					+ ", DA =" + da + ", MA =" + ma + ", OA =" + oa + ", PF ="
					+ pf + ", TOTAL_SALARY=" + total_Salary
					+ " WHERE EMPLOYEE_ID = '" + empId + "'";
			String insertQ = "INSERT INTO EMPLOYEE_SALARY VALUES('" + empId
					+ "', '" + basic_Salary + "','" + hra + "','" + ta + "','"
					+ da + "','" + ma + "','" + oa + "','" + pf + "','"
					+ total_Salary + "')";
			ResultSet rs = stmt.executeQuery(query);
			String employeeID = "";
			while (rs.next()) {
				employeeID = rs.getString("EMPLOYEE_ID");
			}
			if (employeeID != "") {
				x = stmt.executeUpdate(query1);
				if (x != 0) {
					return true;
				} else {
					int i = stmt.executeUpdate(insertQ);
					if (i == 1) {
						return true;
					} else {
						return false;
					}
				}
			} else {
				return false;
			}
		} catch (SQLException | ClassNotFoundException e) {
			return false;
		}
	}
}
