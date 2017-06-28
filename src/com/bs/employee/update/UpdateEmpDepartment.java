/*Project: Bank System
 *Module: Update Employee Data[Department]
 *Author: Shashi Bhushan(DAC76)
 *Coded on: 23 Jan 2015
 *Place: CDAC Bangalore
 * 
 * */
package com.bs.employee.update;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.bs.employee.bean.UpdateEmpDepartmentBean;
import com.bs.listGeneration.DepartmentList;

@SuppressWarnings("serial")
public class UpdateEmpDepartment extends JInternalFrame implements ActionListener {

	JPanel updatePanel;
	JLabel label;
	JTextField empId;
	JComboBox<String> empDept;
	JButton saveBotton;

	public UpdateEmpDepartment() {
		this.setLayout(new FlowLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.closable = true;
		this.title = "Update EMPLOYEE Department [ Fill employee details ]";
		updatePanel = new JPanel();
		updatePanel.setLayout(new GridLayout(3,1));
		label = new JLabel("Employee ID:");
		updatePanel.add(label);
		empId = new JTextField(16);
		updatePanel.add(empId);
		label = new JLabel("New Department:");
		updatePanel.add(label);
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
		updatePanel.add(empDept);
		saveBotton =  new JButton("Save Details");
		updatePanel.add(saveBotton);
		saveBotton.addActionListener(this);
		this.add(updatePanel);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == saveBotton) {
			boolean status=false;
			if(!(Pattern.matches("\\d{1,10}",empId.getText())))
			{JOptionPane.showMessageDialog(this, "EmployeeId should be numeric digit\n from 1 to 10", "Error Message", JOptionPane.ERROR_MESSAGE);
			}else if(empDept.getSelectedItem().equals("Select Department"))
			{JOptionPane.showMessageDialog(this, "Select any department from\n select box", "Error Message", JOptionPane.ERROR_MESSAGE);
			}
			else{
				status=true;
			}
			if(status){
			UpdateEmpDepartmentBean emp = new UpdateEmpDepartmentBean();
			emp.setEmpId(empId.getText());
			emp.setEmpDept((String)empDept.getSelectedItem());
			if(emp.updateEmpDepartment()){
				JOptionPane.showConfirmDialog(this, "Department Updated successfuly.\n\nWould you like to continue?", "Message", JOptionPane.YES_NO_OPTION);
				new UpdateEmpContact();
			}else{
				JOptionPane.showConfirmDialog(this, "Employee ID Not Found.\n\nWould you like to continue?", "Message", JOptionPane.YES_NO_OPTION);
			}
		}
		}
	}
}
