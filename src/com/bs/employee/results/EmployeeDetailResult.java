package com.bs.employee.results;

import javax.swing.JOptionPane;

import com.bs.employee.bean.EmployeeDetails;

public class EmployeeDetailResult {

	public EmployeeDetailResult(EmployeeDetails emp) {
		String str = "";
		if (emp.getName() != null) {
			str = "Employee ID: " + emp.getEmployee_ID() + "\nName: "
					+ emp.getName() + "\nGender: " + emp.getGender()
					+ "\nDate-of-Birth: " + emp.getdOB()
					+ "\nPermanent Address: " + emp.getPer_Addr()
					+ "\nCurrent Address: " + emp.getCurr_Addr() + "\nEmail: "
					+ emp.getE_Mail() + "\nEmergency Contact: "
					+ emp.getEmer_Contact() + "\nQualification: "
					+ emp.getHigh_Qual() + "\nDesignation: " + emp.getDesig()
					+ "\nDate-of-Joining: " + emp.getdOJ() + "\nDepartment: "
					+ emp.getDept_Name() + "\nOffice Location: "
					+ emp.getOff_Address();
			
		} else {
			str="Employee Id is not present !" ;
		}
		JOptionPane.showMessageDialog(null, str);
	}
}
