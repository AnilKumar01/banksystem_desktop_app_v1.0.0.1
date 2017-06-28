package com.bs.employee.bean;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import com.bs.connection.MyConnection;
import java.sql.ResultSet;

public class EmployeeDetails {
	String employee_ID;
	String name;
	String gender;
	String dOB;
	String per_Addr;
	String curr_Addr;
	String contact;
	String e_Mail;
	String emer_Contact;
	String high_Qual;
	String desig;
	String dOJ;
	String dept_Name;
	String off_Address;

	public void setEmployee_ID(String employee_ID) {
		this.employee_ID = employee_ID;
	}
	public boolean setDetails() {
		try {
			Connection con = MyConnection.getMySQLConnection();
			Statement stmt = con.createStatement();
			String sql = " SELECT * FROM EMP_DETAIL WHERE EMPLOYEE_ID='"
					+ employee_ID + "'";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				employee_ID = rs.getString(1);
				name = rs.getString(2);
				gender = rs.getString(3);
				dOB = "" + rs.getDate(4);
				per_Addr = rs.getString(5);
				curr_Addr = rs.getString(6);
				contact = rs.getString(7);
				e_Mail = rs.getString(8);
				emer_Contact = rs.getString(9);
				high_Qual = rs.getString(10);
				desig = rs.getString(11);
				dOJ = "" + rs.getDate(12);
				dept_Name = rs.getString(13);
				off_Address = rs.getString(14);
				System.out.println(off_Address);
			}
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			return false;
		}
	}
	public String getEmployee_ID() {
		return employee_ID;
	}
	public String getName() {
		return name;
	}
	public String getGender() {
		return gender;
	}
	public String getdOB() {
		return dOB;
	}
	public String getPer_Addr() {
		return per_Addr;
	}
	public String getCurr_Addr() {
		return curr_Addr;
	}
	public String getContact() {
		return contact;
	}
	public String getE_Mail() {
		return e_Mail;
	}
	public String getEmer_Contact() {
		return emer_Contact;
	}
	public String getHigh_Qual() {
		return high_Qual;
	}
	public String getDesig() {
		return desig;
	}
	public String getdOJ() {
		return dOJ;
	}
	public String getDept_Name() {
		return dept_Name;
	}
	public String getOff_Address() {
		return off_Address;
	}
	
}
