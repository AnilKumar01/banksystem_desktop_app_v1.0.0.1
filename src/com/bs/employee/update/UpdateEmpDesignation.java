/*Project: Bank System
 *Module: Update Employee Data[Designation]
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

import com.bs.employee.bean.UpdateEmpDesignationBean;
import com.bs.listGeneration.DesignationList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class UpdateEmpDesignation extends JInternalFrame implements ActionListener {

	JPanel updatePanel;
	JLabel label;
	JTextField empId;
	JComboBox<String> empDesig;
	JButton saveBotton;

	public UpdateEmpDesignation() {
		this.setLayout(new FlowLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.closable = true;
		this.title = "Update EMPLOYEE Designation [ Fill employee details ]";
		updatePanel = new JPanel();
		updatePanel.setLayout(new GridLayout(3, 1));
		label = new JLabel("Employee ID:");
		updatePanel.add(label);
		empId = new JTextField(16);
		updatePanel.add(empId);

		label = new JLabel("New Designation:");
		updatePanel.add(label);
		empDesig = new JComboBox<String>();
		empDesig.addItem("Select Designation");
		List<String> desigList = new ArrayList<String>();
		try {
			desigList = DesignationList.getDesigList();
		} catch (ClassNotFoundException | SQLException e) {
			empDesig.addItem("Error IN Fetching List");
		}
		for (Iterator<String> i = desigList.iterator(); i.hasNext();) {
			String item = i.next();
			empDesig.addItem(item);
		}
		updatePanel.add(empDesig);
		saveBotton = new JButton("Save Details");
		updatePanel.add(saveBotton);
		saveBotton.addActionListener(this);
		this.add(updatePanel);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==saveBotton){
			boolean status=false;
			if(!(Pattern.matches("\\d{1,10}",empId.getText())))
			{JOptionPane.showMessageDialog(this, "EmployeeId should be numeric digit\n from 1 to 10", "Error Message", JOptionPane.ERROR_MESSAGE);
			}else if(empDesig.getSelectedItem().equals("Select Designation"))
			{JOptionPane.showMessageDialog(this, "Select Designation from\n select box", "Error Message", JOptionPane.ERROR_MESSAGE);
			}
			else{
				status=true;
			}
			if(status){
			UpdateEmpDesignationBean emp = new UpdateEmpDesignationBean();
			emp.setEmpID(empId.getText());
			emp.setEmpDesignation((String)empDesig.getSelectedItem());
			if(emp.updateDesignation()){
				JOptionPane.showConfirmDialog(this, "New Designation Updated successfuly.\n\nWould you like to continue?", "Message", JOptionPane.YES_NO_OPTION);
				new UpdateEmpContact();
			}else{
				JOptionPane.showConfirmDialog(this, "Employee ID Not Found.\n\nWould you like to continue?", "Message", JOptionPane.YES_NO_OPTION);
			}
		}
		}
		
	}
}
