package com.bs.forms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import com.bs.bankrelated.bean.DesktopUser;
import com.bs.employee.update.UpdateEmpContact;
import com.bs.employee.update.UpdateEmpDepartment;
import com.bs.employee.update.UpdateEmpDesignation;
import com.bs.employee.update.UpdateEmpSalary;
import com.bs.forms.internal.AddNewEmployee;
import com.bs.forms.internal.ChangePassword;
import com.bs.forms.internal.EmployeeListResult;
import com.bs.forms.internal.GenerateSalarySlip;
import com.bs.forms.internal.GetEmployeeDetails;
import com.bs.forms.internal.ListEmpDept;
import com.bs.init.ExecuteNotepad;

@SuppressWarnings("serial")
public class BankSystemGUIForHR extends JFrame implements ActionListener {
	/* ************************************************************************* */
	JMenuBar menuBar;
	JMenu employee;
	JMenuItem addNewEmployee, viewEmployeeDetails, listAllEmployee,
			listEmployeeByDept;
	JMenu updateEmployeeDetails;
	JMenuItem empContacts, empSalary, empDepartment, empDesignation;
	JMenu payments;
	JMenuItem viewSalSlipOfEmp;
	JMenu settings;
	JMenuItem changeUsername, changePassword, changeUsernameAndPassword, logOut;
	JMenu help;
	JMenuItem aboutItem;
	JPanel internalFrameHolder, dummyPanel;
	//String userName, password;
	DesktopUser user;
	/* ************************************************************************* */
	public BankSystemGUIForHR(DesktopUser user) {
		super("BANK SYSTEM [HR LOGIN]");
		
		this.user=user;
		
		this.setSize(1024, 740);
		this.setLayout(new BorderLayout());
		// MENU BAR
//		this.userName = user.getUsername();
//		this.password = user.getPassword();
		menuBar = new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		this.add(menuBar, BorderLayout.NORTH);
		/* ********************************************************************* */
		employeeMenuItems();
		/* ********************************************************************* */
		paymentMenuItems();
		/* ********************************************************************* */
		settingsMenuItems();
		/* ********************************************************************* */
		// HELP MENU
		help = new JMenu("Help");
		help.setMnemonic(KeyEvent.VK_H);
		aboutItem=new JMenuItem("About Bank System");
		aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
		aboutItem.addActionListener(this);
		help.add(aboutItem);
		menuBar.add(help);
		/* ********************************************************************* */
		internalFrameHolder = new JPanel();
		internalFrameHolder.setBackground(new Color(250, 235, 215));
		this.add(internalFrameHolder, BorderLayout.CENTER);
		dummyPanel = new JPanel();
		dummyPanel.setBackground(new Color(250, 235, 215));
		this.add(dummyPanel, BorderLayout.SOUTH);
		/* ********************************************************************* */
		this.setResizable(false);
		this.setVisible(true);
	}

	private void settingsMenuItems() {
		// SETTINGS MENU
		settings = new JMenu(user.getUsername().toUpperCase());
		//settings.setMnemonic(KeyEvent.VK_S);
		menuBar.add(settings);
		// SETTINGS MENU ITEMS
//		changeUsername = new JMenuItem("Change Username");
//		changeUsername.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,
//				InputEvent.CTRL_DOWN_MASK));
//		changeUsername.addActionListener(this);
//		settings.add(changeUsername);

		changePassword = new JMenuItem("Change Password");
		changePassword.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				InputEvent.CTRL_DOWN_MASK));
		changePassword.addActionListener(this);
		settings.add(changePassword);

//		changeUsernameAndPassword = new JMenuItem(
//				"Change Username and Password");
//		changeUsernameAndPassword.setAccelerator(KeyStroke.getKeyStroke(
//				KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
//		changeUsernameAndPassword.addActionListener(this);
//		settings.add(changeUsernameAndPassword);
		logOut=new JMenuItem("LogOut");
		logOut.addActionListener(this);
		logOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,	InputEvent.CTRL_DOWN_MASK));
		settings.add(logOut);
	}

	private void paymentMenuItems() {
		// PAYMENT MENU
		payments = new JMenu("Payments");
		payments.setMnemonic(KeyEvent.VK_P);
		menuBar.add(payments);
		// PAYMENTS MENU ITEMS
		viewSalSlipOfEmp = new JMenuItem("View Salary Slip of an Employee");
		viewSalSlipOfEmp.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L,
				InputEvent.CTRL_DOWN_MASK));
		viewSalSlipOfEmp.addActionListener(this);
		payments.add(viewSalSlipOfEmp);
	}

	private void employeeMenuItems() {
		// EMPLOYEE MENU
		employee = new JMenu("Employee");
		employee.setMnemonic(KeyEvent.VK_E);
		menuBar.add(employee);
		// EMPLOYEE MENU ITEMS
		addNewEmployee = new JMenuItem("Add New Employee");
		addNewEmployee.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,
				InputEvent.CTRL_DOWN_MASK));
		addNewEmployee.addActionListener(this);
		employee.add(addNewEmployee);

		employee.add(new JSeparator());

		updateEmployeeDetails = new JMenu("Update Employee Details");
		employee.add(updateEmployeeDetails);
		// UPDATE EMPLOYEE MENU ITEMS
		/* Update Contacts */
		empContacts = new JMenuItem("Contacts");
		empContacts.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B,
				InputEvent.CTRL_DOWN_MASK));
		empContacts.addActionListener(this);
		updateEmployeeDetails.add(empContacts);
		/* Update Salary */
		empSalary = new JMenuItem("Salary");
		empSalary.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D,
				InputEvent.CTRL_DOWN_MASK));
		empSalary.addActionListener(this);
		updateEmployeeDetails.add(empSalary);
		/* Update Department */
		empDepartment = new JMenuItem("Department");
		empDepartment.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
				InputEvent.CTRL_DOWN_MASK));
		empDepartment.addActionListener(this);
		updateEmployeeDetails.add(empDepartment);
		/* Update Designation */
		empDesignation = new JMenuItem("Designation");
		empDesignation.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F,
				InputEvent.CTRL_DOWN_MASK));
		empDesignation.addActionListener(this);
		updateEmployeeDetails.add(empDesignation);

		employee.add(new JSeparator());

		viewEmployeeDetails = new JMenuItem("View Employee Details");
		viewEmployeeDetails.setAccelerator(KeyStroke.getKeyStroke(
				KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK));
		viewEmployeeDetails.addActionListener(this);
		employee.add(viewEmployeeDetails);

		listAllEmployee = new JMenuItem("List All Employees");
		listAllEmployee.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,
				InputEvent.CTRL_DOWN_MASK));
		listAllEmployee.addActionListener(this);
		employee.add(listAllEmployee);

		listEmployeeByDept = new JMenuItem("List Employees by Department");
		listEmployeeByDept.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I,
				InputEvent.CTRL_DOWN_MASK));
		listEmployeeByDept.addActionListener(this);
		employee.add(listEmployeeByDept);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == addNewEmployee) {
			internalFrameHolder.add(new AddNewEmployee());
		}
		if (e.getSource() == empContacts) {
			internalFrameHolder.add(new UpdateEmpContact());
		}
		if (e.getSource() == empSalary) {
			internalFrameHolder.add(new UpdateEmpSalary());
		}
		if (e.getSource() == empDepartment) {
			internalFrameHolder.add(new UpdateEmpDepartment());
		}
		if (e.getSource() == empDesignation) {
			internalFrameHolder.add(new UpdateEmpDesignation());
		}
		if (e.getSource() == viewEmployeeDetails) {
			internalFrameHolder.add(new GetEmployeeDetails());
		}
		if (e.getSource() == listAllEmployee) {
			internalFrameHolder.add(new EmployeeListResult());
		}
		if (e.getSource() == listEmployeeByDept) {
			internalFrameHolder.add(new ListEmpDept());
		}
//		if (e.getSource() == changeUsername) {
//			internalFrameHolder.add(new ChangeUserName(userName, password));
//		}
//		if (e.getSource() == changePassword) {
//			internalFrameHolder.add(new ChangePassword(userName, password));
//		}
		if(e.getSource()==changePassword){
			internalFrameHolder.add(new ChangePassword(user));
		}
		if (e.getSource() == viewSalSlipOfEmp) {
			internalFrameHolder.add(new GenerateSalarySlip());
		}
		if(e.getSource()==logOut){
			this.dispose();
			new LoginForm().setVisible(true);
		}
		if(e.getSource()==aboutItem){
			new ExecuteNotepad("BANK_SYSTEM_HELP");
		}
	}
}
