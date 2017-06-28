package com.bs.employee.results;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import com.bs.employee.bean.SalarySlip;

@SuppressWarnings("serial")
public class EmployeeSalarySlip extends JFrame {
	JPanel salarySlipPanel, bankLogo, empDetail, salaryDetail;
	JLabel label;
	JSeparator sep;
	JButton close;

	public EmployeeSalarySlip(SalarySlip slip) {
		this.setSize(800, 700);
		this.setLayout(new BorderLayout());
		this.setTitle("Salary Slip");
		salarySlipPanel = new JPanel();
		salarySlipPanel.setLayout(new GridLayout(32, 1));
		/* ~~~~~~~~~~~~~~~~~~~~~~~ BANK LOGO ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
		label = new JLabel("Real Bank", JLabel.RIGHT);
		salarySlipPanel.add(label);
		label = new JLabel("of India", JLabel.LEFT);
		salarySlipPanel.add(label);
		/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
		label = new JLabel("Employee ID: " + slip.getEmployee_Id());
		salarySlipPanel.add(label);
		label = new JLabel("Name: " + slip.getName());
		salarySlipPanel.add(label);
		label = new JLabel("Gender: " + slip.getGender());
		salarySlipPanel.add(label);
		label = new JLabel("Date of Birth: " + slip.getDateOfBirth());
		salarySlipPanel.add(label);
		label = new JLabel("Contact No: " + slip.getContactNo());
		salarySlipPanel.add(label);
		label = new JLabel("Email: " + slip.geteMail());
		salarySlipPanel.add(label);
		label = new JLabel("Department: " + slip.getDepartmentName());
		salarySlipPanel.add(label);
		label = new JLabel("Designation: " + slip.getDesignation());
		salarySlipPanel.add(label);
		label = new JLabel("Date of Joining: " + slip.getDateOfJoining());
		salarySlipPanel.add(label);
		label = new JLabel("Branch Code: " + slip.getBranchCode());
		salarySlipPanel.add(label);
		label = new JLabel("Location: " + slip.getLocation());
		salarySlipPanel.add(label);
		label = new JLabel("");
		salarySlipPanel.add(label);
		/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
		label = new JLabel(" ");
		salarySlipPanel.add(label);
		label = new JLabel(" ");
		salarySlipPanel.add(label);
		label = new JLabel(" ");
		salarySlipPanel.add(label);
		label = new JLabel(" ");
		salarySlipPanel.add(label);
		label = new JLabel("Earning           ", JLabel.RIGHT);
		salarySlipPanel.add(label);
		label = new JLabel("  ");
		salarySlipPanel.add(label);
		sep = new JSeparator(JSeparator.HORIZONTAL);
		salarySlipPanel.add(sep);
		sep = new JSeparator(JSeparator.HORIZONTAL);
		salarySlipPanel.add(sep);
		label = new JLabel("Basic Salary:           ", JLabel.RIGHT);
		salarySlipPanel.add(label);
		label = new JLabel(slip.getBasic());
		salarySlipPanel.add(label);
		label = new JLabel("HRA:           ", JLabel.RIGHT);
		salarySlipPanel.add(label);
		label = new JLabel(slip.getHra());
		salarySlipPanel.add(label);
		label = new JLabel("TA:           ", JLabel.RIGHT);
		salarySlipPanel.add(label);
		label = new JLabel(slip.getTa());
		salarySlipPanel.add(label);
		label = new JLabel("DA:           ", JLabel.RIGHT);
		salarySlipPanel.add(label);
		label = new JLabel(slip.getDa());
		salarySlipPanel.add(label);
		label = new JLabel("OA:           ", JLabel.RIGHT);
		salarySlipPanel.add(label);
		label = new JLabel(slip.getOa());
		salarySlipPanel.add(label);
		label = new JLabel("MA:           ", JLabel.RIGHT);
		salarySlipPanel.add(label);
		label = new JLabel(slip.getMa());
		salarySlipPanel.add(label);
		label = new JLabel("PF (Deduction):           ", JLabel.RIGHT);
		salarySlipPanel.add(label);
		label = new JLabel("-" + slip.getPf());
		salarySlipPanel.add(label);
		double grossSalary = Double.parseDouble(slip.getBasic())
				+ Double.parseDouble(slip.getHra())
				+ Double.parseDouble(slip.getTa())
				+ Double.parseDouble(slip.getDa())
				+ Double.parseDouble(slip.getMa());
		label = new JLabel("Gross Earning:           ", JLabel.RIGHT);
		salarySlipPanel.add(label);
		label = new JLabel("" + grossSalary);
		salarySlipPanel.add(label);
		label = new JLabel("Net Payable Salary (Gross - PF):           ",
				JLabel.RIGHT);
		salarySlipPanel.add(label);
		label = new JLabel(slip.getTotalSalary());
		salarySlipPanel.add(label);
		sep = new JSeparator(JSeparator.HORIZONTAL);
		salarySlipPanel.add(sep);
		sep = new JSeparator(JSeparator.HORIZONTAL);
		salarySlipPanel.add(sep);
		label = new JLabel(" ");
		salarySlipPanel.add(label);
		label = new JLabel("");
		salarySlipPanel.add(label);
		label = new JLabel(" ");
		salarySlipPanel.add(label);
		label = new JLabel("Managaer [ Finance ]    ", JLabel.RIGHT);
		salarySlipPanel.add(label);
		/* ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
		this.add(salarySlipPanel);

		this.setVisible(true);
	}

}
