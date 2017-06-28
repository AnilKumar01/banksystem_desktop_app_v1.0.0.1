package com.bs.forms.internal;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import com.bs.employee.bean.SalarySlip;
import com.bs.employee.results.EmployeeSalarySlip;
import com.bs.listGeneration.EmpList;
import com.bs.listGeneration.EmployeeList;

@SuppressWarnings("serial")
public class GenerateSalarySlip extends JInternalFrame implements
		ActionListener {
	JPanel generateSalarySlip;
	JLabel label;
	JComboBox<String> empId;
	JButton generate;

	public GenerateSalarySlip() {
		this.setLayout(new FlowLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.closable = true;
		this.title = "Genarate Salary Slip";
		generateSalarySlip = new JPanel();
		generateSalarySlip.setLayout(new GridLayout(3, 1));
		label = new JLabel(
				"Select Employee(Employee Id) to Generate Salary Slip ");
		generateSalarySlip.add(label);
		empId = new JComboBox<String>();
		empId.addItem("Select Employee Id");
		List<EmpList> empList = new ArrayList<EmpList>();
		empList = EmployeeList.getEmployeeList();
		for (Iterator<EmpList> i = empList.iterator(); i.hasNext();) {
			String item = i.next().getEmpID();
			empId.addItem(item);
		}
		generateSalarySlip.add(empId);
		generate = new JButton("Generate Salary Slip");
		generate.addActionListener(this);
		generateSalarySlip.add(generate);
		this.add(generateSalarySlip);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == generate) {
			String employeeID = (String) empId.getSelectedItem();
			if (employeeID != "Select Employee Id") {
				SalarySlip slip = new SalarySlip();
				if (slip.setDetails(employeeID)) {
					new EmployeeSalarySlip(slip);
					this.dispose();
				} else {
					JOptionPane.showMessageDialog(this,
							"Salary of this Employee is not yet Generated !",
							"Error", JOptionPane.OK_OPTION);
				}
			} else {
				JOptionPane.showMessageDialog(this,
						"Please Select an Employee !",
						"Error", JOptionPane.OK_OPTION);
			}
		}
	}
}
