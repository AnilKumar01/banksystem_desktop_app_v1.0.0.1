package com.bs.forms.internal;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.bs.employee.bean.EmployeeDetails;
import com.bs.employee.results.EmployeeDetailResult;

@SuppressWarnings("serial")
public class GetEmployeeDetails extends JInternalFrame implements
		ActionListener {

	JPanel selectEmpIDPanel;
	JLabel label;
	JTextField empId;
	JButton getDetails;

	public GetEmployeeDetails() {
		this.setLayout(new FlowLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.closable = true;
		this.title = "Fill Employee ID [EMPLOYEE DETAILS] ";
		selectEmpIDPanel = new JPanel();
		selectEmpIDPanel.setLayout(new GridLayout(2, 1));
		label = new JLabel("Employee ID:");
		selectEmpIDPanel.add(label);
		empId = new JTextField(16);
		selectEmpIDPanel.add(empId);
		getDetails = new JButton("Get Details");
		getDetails.addActionListener(this);
		selectEmpIDPanel.add(getDetails);
		this.add(selectEmpIDPanel);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == getDetails) {
			EmployeeDetails emp = new EmployeeDetails();
			emp.setEmployee_ID(empId.getText());
			System.out.println(emp.getEmployee_ID());
			boolean status = emp.setDetails();
			if (status) {
				new EmployeeDetailResult(emp);
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
