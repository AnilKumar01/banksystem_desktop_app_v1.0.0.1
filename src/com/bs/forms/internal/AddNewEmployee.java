package com.bs.forms.internal;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.bs.actions.MasterAction;
import com.bs.employee.bean.Employee;
import com.bs.employee.update.SaveEmployeeData;
import com.bs.listGeneration.BranchList;
import com.bs.listGeneration.DepartmentList;
import com.bs.operations.MasterOperations;

@SuppressWarnings("serial")
public class AddNewEmployee extends JInternalFrame implements ActionListener {

	JPanel newEmployee;
	Map<Integer, String> deptList = new HashMap<Integer, String>();
	List<String> designList = new ArrayList<String>();
	MasterOperations ma = new MasterAction();

	JTabbedPane empFormTabs;
	JPanel empJobDetailPanel, empNamePanel, salPanel, empDobGenECon,
			empAddressPanel;
	JLabel label;
	JTextField empId, empEConNo, empContact, empEmail;
	JTextField empFirstName, empMidName, empLastName;
	JComboBox<String> empDept, empJob, empDobDD, empDobMM, empDobYYYY;
	JComboBox<String> empJoinDD, empJoinMM, empJoinYYYY, branchCode;
	JPanel empDobPanel, empGenPanel, empConPanel;
	JRadioButton maleR, femaleR, transR;
	JRadioButton doctrate, postGraduate, graduate, others;
	ButtonGroup genGrp, eduGrp;
	JTextArea empPermanentAddress, empCurrentAddress;
	JPanel dummyPanel, dummyPanel2;
	JPanel departmentalInfoPanel;
	JScrollPane pane, pane1;
	JButton submitButton;

	public AddNewEmployee() {
		this.setLayout(new FlowLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.closable = true;
		this.title = "Add New Employee Contact [ Fill employee details ]";
		newEmployee = new JPanel();
		newEmployee.setBorder(BorderFactory.createBevelBorder(1));
		newEmployee.setLayout(new GridLayout(6, 1));
		/* ****************************************************************** */
		// EMPLOYEE JOB DETAILS
		empJobDetailPanel = new JPanel();
		empJobDetailPanel.setBorder(BorderFactory
				.createTitledBorder("Departmental Information"));

		// DEPARTMENT OF EMPLOYEE
		empDept = new JComboBox<String>();
		empDept.addItem("Select Department");
		List<String> deptlist = new ArrayList<String>();
		try {
			deptlist = DepartmentList.getDeptList();
		} catch (ClassNotFoundException | SQLException e) {
			empDept.addItem("Error IN Fetching List");
		}
		for (Iterator<String> i = deptlist.iterator(); i.hasNext();) {
			String item = i.next();
			empDept.addItem(item);
		}

		empJobDetailPanel.add(empDept);

		// DESIGNATION OF EMPLOYEE
		empJob = new JComboBox<String>();
		empJob.addItem("Select Designation");
		if (ma.designationList(designList)) {
			for (String design : designList) {
				empJob.addItem(design);
			}
		}
		empJobDetailPanel.add(empJob);
		// EMPLOYEE JOINING DATE
		dummyPanel = new JPanel();
		dummyPanel.setBorder(BorderFactory
				.createTitledBorder("Date of Joining"));
		// DATE
		empJoinDD = new JComboBox<String>();
		empJoinDD.addItem("DD");
		for (int i = 1; i <= 31; i++)
			empJoinDD.addItem("" + i);
		dummyPanel.add(empJoinDD);
		// MONTH
		empJoinMM = new JComboBox<String>();
		empJoinMM.addItem("MM");
		for (int i = 1; i <= 12; i++)
			empJoinMM.addItem("" + i);
		dummyPanel.add(empJoinMM);
		// YEAR
		empJoinYYYY = new JComboBox<String>();
		empJoinYYYY.addItem("YYYY");
		for (int i = 1947; i <= 2014; i++)
			empJoinYYYY.addItem("" + i);
		dummyPanel.add(empJoinYYYY);
		empJobDetailPanel.add(dummyPanel);

		dummyPanel = new JPanel();
		dummyPanel.setBorder(BorderFactory.createTitledBorder("Branch Code"));
		branchCode = new JComboBox<String>();
		branchCode.addItem("Select Branch");
		List<String> branchList = new ArrayList<String>();
		try {
			branchList = BranchList.getBranchList();
		} catch (ClassNotFoundException | SQLException e) {
			branchCode.addItem("Error IN Fetching List");
		}
		for (Iterator<String> i = branchList.iterator(); i.hasNext();) {
			String item = i.next();
			branchCode.addItem(item);
		}

		branchCode.setBorder(BorderFactory.createBevelBorder(1));
		dummyPanel.add(branchCode);
		empJobDetailPanel.add(dummyPanel);
		newEmployee.add(empJobDetailPanel);
		/*
		 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		 */
		// NAME OF THE EMPLOYEE
		empNamePanel = new JPanel();
		empNamePanel.setBorder(BorderFactory
				.createTitledBorder("Employee Name"));
		label = new JLabel("First Name");
		empNamePanel.add(label);
		empFirstName = new JTextField(15);
		empFirstName.setBorder(BorderFactory.createBevelBorder(1));
		empNamePanel.add(empFirstName);

		label = new JLabel("     Middle Name");
		empNamePanel.add(label);
		empMidName = new JTextField(15);
		empMidName.setBorder(BorderFactory.createBevelBorder(1));
		empNamePanel.add(empMidName);

		label = new JLabel("     Last Name");
		empNamePanel.add(label);
		empLastName = new JTextField(15);
		empLastName.setBorder(BorderFactory.createBevelBorder(1));
		empNamePanel.add(empLastName);
		newEmployee.add(empNamePanel);
		/*
		 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		 */
		// DATE OF BIRTH, GENDER, AND EMERGENCY CONTACT NUMBER
		empDobGenECon = new JPanel();
		empDobGenECon.setBorder(BorderFactory.createTitledBorder(""));
		// DATE OF BIRTH
		empDobPanel = new JPanel();
		empDobPanel
				.setBorder(BorderFactory.createTitledBorder("Date of Birth"));
		// DATE
		empDobDD = new JComboBox<String>();
		empDobDD.addItem("DD");
		for (int i = 1; i <= 31; i++)
			empDobDD.addItem("" + i);
		empDobPanel.add(empDobDD);
		// MONTH
		empDobMM = new JComboBox<String>();
		empDobMM.addItem("MM");
		for (int i = 1; i <= 12; i++)
			empDobMM.addItem("" + i);
		empDobPanel.add(empDobMM);
		// YEAR
		empDobYYYY = new JComboBox<String>();
		empDobYYYY.addItem("YYYY");
		for (int i = 1947; i <= 2014; i++)
			empDobYYYY.addItem("" + i);
		empDobPanel.add(empDobYYYY);
		empDobGenECon.add(empDobPanel);
		/* ************************************************************ */
		// GENDER OF EMPLOYEE
		empGenPanel = new JPanel();
		empGenPanel.setBorder(BorderFactory.createTitledBorder("Gender"));
		maleR = new JRadioButton("Male");
		empGenPanel.add(maleR);
		femaleR = new JRadioButton("Female");
		empGenPanel.add(femaleR);
		transR = new JRadioButton("Transgender");
		empGenPanel.add(transR);
		genGrp = new ButtonGroup();
		genGrp.add(maleR);
		genGrp.add(femaleR);
		genGrp.add(transR);
		empDobGenECon.add(empGenPanel);
		/* ************************************************************* */
		// EMERGENCY CONTACT NUMBER
		empConPanel = new JPanel();
		empConPanel.setBorder(BorderFactory.createTitledBorder("Em. Contact"));
		empEConNo = new JTextField(10);
		empEConNo.setBorder(BorderFactory.createBevelBorder(1));
		empConPanel.add(empEConNo);
		empDobGenECon.add(empConPanel);

		// CONTACT NUMBER
		empConPanel = new JPanel();
		empConPanel.setBorder(BorderFactory.createTitledBorder("Contact"));
		empContact = new JTextField(10);
		empContact.setBorder(BorderFactory.createBevelBorder(1));
		empConPanel.add(empContact);
		empDobGenECon.add(empConPanel);
		newEmployee.add(empDobGenECon);
		/* ************************************************************** */
		// EMPLOYEE ADDRESS INFORMATION
		empAddressPanel = new JPanel();
		dummyPanel = new JPanel();
		dummyPanel.setBorder(BorderFactory
				.createTitledBorder("Permanent Address"));
		empAddressPanel.add(dummyPanel);
		empPermanentAddress = new JTextArea(2, 20);

		// empPermanentAddress.setBorder(BorderFactory.createBevelBorder(1));
		pane = new JScrollPane();
		pane.setBorder(BorderFactory.createBevelBorder(1));
		empPermanentAddress.setWrapStyleWord(true);
		empPermanentAddress.setLineWrap(true);
		pane.getViewport().add(empPermanentAddress);
		dummyPanel.add(pane);

		dummyPanel = new JPanel();
		dummyPanel.setBorder(BorderFactory
				.createTitledBorder("Current Address"));
		empCurrentAddress = new JTextArea(2, 20);
		// empCurrentAddress.setBorder(BorderFactory.createBevelBorder(1));
		pane = new JScrollPane();
		pane.setBorder(BorderFactory.createBevelBorder(1));
		empCurrentAddress.setWrapStyleWord(true);
		empCurrentAddress.setLineWrap(true);
		pane.getViewport().add(empCurrentAddress);
		dummyPanel.add(pane);
		empAddressPanel.add(dummyPanel);

		dummyPanel = new JPanel();
		dummyPanel.setBorder(BorderFactory.createTitledBorder("E-mail"));
		empEmail = new JTextField(15);
		empEmail.setBorder(BorderFactory.createBevelBorder(1));
		dummyPanel.add(empEmail);
		empAddressPanel.add(dummyPanel);
		empAddressPanel.setBorder(BorderFactory.createTitledBorder(""));

		newEmployee.add(empAddressPanel);
		/* *********************************************************************** */
		// EDUCATION QUALIFICATION
		dummyPanel2 = new JPanel();
		dummyPanel = new JPanel();
		dummyPanel.setBorder(BorderFactory
				.createTitledBorder("Education Qualifications"));
		doctrate = new JRadioButton("Doctrate");
		dummyPanel.add(doctrate);
		postGraduate = new JRadioButton("Post Graduate");
		dummyPanel.add(postGraduate);
		graduate = new JRadioButton("Graduate");
		dummyPanel.add(graduate);
		others = new JRadioButton("Others");
		dummyPanel.add(others);
		eduGrp = new ButtonGroup();
		eduGrp.add(doctrate);
		eduGrp.add(postGraduate);
		eduGrp.add(graduate);
		eduGrp.add(others);
		dummyPanel2.add(dummyPanel);
		dummyPanel = new JPanel();
		dummyPanel2.add(dummyPanel);
		newEmployee.add(dummyPanel2);
		submitButton = new JButton("Add Employee Details");
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(submitButton);
		submitButton.addActionListener(this);
		newEmployee.add(buttonPanel);
		this.add(newEmployee);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == submitButton) {
			String qualification = "";
			if (doctrate.isSelected()) {
				qualification = "Doctrate";
			} else if (postGraduate.isSelected()) {
				qualification = "Post Graduate";
			} else if (graduate.isSelected()) {
				qualification = "Graduate";
			} else if (others.isSelected()) {
				qualification = "Others";
			}
			String gender = "";
			if (maleR.isSelected()) {
				gender = "MALE";
			} else if (femaleR.isSelected()) {
				gender = "FEMALE";
			} else if (transR.isSelected()) {
				gender = "Trans";
			}
			String DOJ = (String) empJoinYYYY.getSelectedItem() + "/"
					+ (String) empJoinMM.getSelectedItem() + "/"
					+ (String) empJoinDD.getSelectedItem();
			String DOB = (String) empDobYYYY.getSelectedItem() + "/"
					+ (String) empDobMM.getSelectedItem() + "/"
					+ (String) empDobDD.getSelectedItem();
			Employee emp = new Employee();
			emp.setFirstName(empFirstName.getText());
			emp.setMiddleName(empMidName.getText());
			emp.setLastName(empLastName.getText());
			emp.setGender(gender);
			emp.setDateOfBirth(DOB);
			emp.setDateOfJoining(DOJ);
			emp.setEmail(empEmail.getText());
			emp.setContactNo(empContact.getText());
			emp.setEmergencyContact(empEConNo.getText());
			emp.setHighestQualification(qualification);
			emp.setDeparmentNo((String) empDept.getSelectedItem());
			emp.setDesignation((String) empJob.getSelectedItem());
			emp.setBranchCode((String) branchCode.getSelectedItem());
			emp.setPermanentAddress(empPermanentAddress.getText());
			emp.setCurrentAddress(empCurrentAddress.getText());
			if (SaveEmployeeData.saveEmpData(emp)) {
				this.dispose();
				JOptionPane.showMessageDialog(null,
						"Employee Added Successfully");
			} else {
				JOptionPane.showMessageDialog(null,
						"Fill all the fields Corrrectly");
			}
		}
	}
}
