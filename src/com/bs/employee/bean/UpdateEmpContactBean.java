package com.bs.employee.bean;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import com.bs.connection.MyConnection;

public class UpdateEmpContactBean {
	String empId;
	String contactNo;
	String emerContactNo;
	String eMail;

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public void setEmerContactNo(String emerContactNo) {
		this.emerContactNo = emerContactNo;
	}

	public void seteMail(String eMail) {
		this.eMail = eMail;
	}

	public boolean updateEmpContact() {
		int x = 0;
		try {
			Connection con = MyConnection.getMySQLConnection();
			Statement stmt = con.createStatement();
			String query = "UPDATE EMPLOYEE SET CONTACT_NO = '" + contactNo
					+ "',EMERGENCY_CONTACT_NO='" + emerContactNo + "',E_MAIL='"
					+ eMail + "' WHERE EMPLOYEE_ID='" + empId + "'";
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
