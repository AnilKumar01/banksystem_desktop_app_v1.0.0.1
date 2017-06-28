package com.bs.employee.bean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bs.connection.MyConnection;

public class SalarySlip {
	private String employee_Id;
	private String Name = null;
	private String gender;
	private String dateOfBirth;
	private String contactNo;
	private String eMail;
	private String designation;
	private String dateOfJoining;
	private String departmentName;
	private String branchCode;
	private String location;
	private String basic;
	private String hra;
	private String da;
	private String ta;
	private String oa;
	private String pf;
	private String ma;
	private String totalSalary;

	public String getEmployee_Id() {
		return employee_Id;
	}

	public void setEmployee_Id(String employee_ID) {
		this.employee_Id = employee_ID;
	}

	public String getName() {
		return Name;
	}

	public String getGender() {
		return gender;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public String getContactNo() {
		return contactNo;
	}

	public String geteMail() {
		return eMail;
	}

	public String getDesignation() {
		return designation;
	}

	public String getDateOfJoining() {
		return dateOfJoining;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public String getBranchCode() {
		return branchCode;
	}

	public String getLocation() {
		return location;
	}

	public String getBasic() {
		return basic;
	}

	public String getHra() {
		return hra;
	}

	public String getDa() {
		return da;
	}

	public String getTa() {
		return ta;
	}

	public String getOa() {
		return oa;
	}

	public String getPf() {
		return pf;
	}

	public String getMa() {
		return ma;
	}

	public String getTotalSalary() {
		return totalSalary;
	}

	public boolean setDetails(String empID) {
		try {
			Connection con = MyConnection.getMySQLConnection();
			Statement stmt = con.createStatement();
			String sql = "SELECT * FROM EMPLOYEE_SALARY_SLIP WHERE EMPLOYEE_ID='"
					+ empID + "'";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				employee_Id = rs.getString("Employee_id");
				Name = rs.getString("NAME");
				gender = rs.getString("GENDER");
				dateOfBirth = "" + rs.getDate("DATE_OF_BIRTH");
				contactNo = rs.getString("CONTACT_NO");
				eMail = rs.getString("E_MAIL");
				designation = rs.getString("DESIGNATION");
				dateOfJoining = "" + rs.getDate("DATE_OF_JOINING");
				departmentName = rs.getString("DEPARTMENT_NAME");
				branchCode = rs.getString("BRANCH_CODE");
				location = rs.getString("LOCATION");
				basic = "" + rs.getFloat("BASIC");
				hra = "" + rs.getFloat("HRA");
				da = "" + rs.getFloat("DA");
				ta = "" + rs.getFloat("TA");
				oa = "" + rs.getFloat("OA");
				pf = "" + rs.getFloat("PF");
				ma = "" + rs.getFloat("MA");
				totalSalary = "" + rs.getFloat("TOTAL_SALARY");
			}

			if (Name == null) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException | ClassNotFoundException e) {
			return false;
		}
	}
}
