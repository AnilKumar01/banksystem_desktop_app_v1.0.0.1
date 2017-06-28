package com.bs.listGeneration;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bs.connection.MyConnection;

public class EmployeeList {
	public static List<EmpList> getEmployeeList() {
		try {

			List<EmpList> list = new ArrayList<EmpList>();
			Connection con = MyConnection.getMySQLConnection();
			Statement stmt = con.createStatement();
			String query = "SELECT * from EMP_LIST Order By Employee_ID";
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				EmpList emp = new EmpList();
				emp.setEmpID(rs.getString(1));
				emp.setName(rs.getString(2));
				emp.setGender(rs.getString(3));
				emp.setContact_No(rs.getString(4));
				emp.setE_Mail(rs.getString(5));
				emp.setDesig(rs.getString(6));
				emp.setDept_Name(rs.getString(7));
				emp.setLocation(rs.getString(8));
				list.add(emp);
			}
			return list;
		} catch (ClassNotFoundException | SQLException e) {
			return null;
		}
	}
}
