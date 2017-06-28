/*Project: Bank System
 *Module: Update Employee Data[Salary]
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
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.bs.employee.bean.UpdateEmpSalaryBean;

@SuppressWarnings("serial")
public class UpdateEmpSalary extends JInternalFrame implements ActionListener {

	JPanel updatePanel;
	JLabel label;
	JTextField empId, empSalary;
	JButton saveBotton;

	public UpdateEmpSalary() {
		this.setLayout(new FlowLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.closable = true;
		this.title = "Update EMPLOYEE Salary [ Fill employee details ]";
		updatePanel = new JPanel();
		updatePanel.setLayout(new GridLayout(3, 1));
		label = new JLabel("Employee ID:");
		updatePanel.add(label);
		empId = new JTextField(16);
		updatePanel.add(empId);

		label = new JLabel("New Salary:");
		updatePanel.add(label);
		empSalary = new JTextField(16);
		updatePanel.add(empSalary);
		saveBotton = new JButton("Save Details");
		updatePanel.add(saveBotton);
		saveBotton.addActionListener(this);
		this.add(updatePanel);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == saveBotton) {
			boolean status=false;
			if(!(Pattern.matches("\\d{1-10}",empId.getText())))
			{JOptionPane.showMessageDialog(this, "EmployeeId should be numeric digit.", "Error Message", JOptionPane.ERROR_MESSAGE);
			}else if((Pattern.matches("^[1-9]{1,10}",empSalary.getText()))){
				JOptionPane.showMessageDialog(this, "Salary should be numeric digit\n from 1 to 10 digit.", "Error Message", JOptionPane.ERROR_MESSAGE);	
			}else{
				status=true;
			}
			if(status){
			UpdateEmpSalaryBean emp = new UpdateEmpSalaryBean();
			emp.setEmpId(empId.getText());
			emp.setSalary(empSalary.getText());
			if (emp.updateEmpSalary()) {
				JOptionPane
						.showConfirmDialog(
								this,
								"Salary Updated successfuly.\n\nWould you like to continue?",
								"Message", JOptionPane.YES_NO_OPTION);
				new UpdateEmpContact();
			} else {
				JOptionPane
						.showConfirmDialog(
								this,
								"Employee ID Not Found.\n\nWould you like to continue?",
								"Message", JOptionPane.YES_NO_OPTION);
			}
		}
		}
	}
}
