/*Project: Bank System
 *Module: Update Employee Data[Contact]
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

import com.bs.employee.bean.UpdateEmpContactBean;

@SuppressWarnings("serial")
public class UpdateEmpContact extends JInternalFrame implements ActionListener {

	JPanel updatePanel;
	JLabel label;
	JTextField empId, contactNumber, emer_Contact, e_Mail;
	JButton saveBotton;

	public UpdateEmpContact() {
		this.setLayout(new FlowLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.closable = true;
		this.title = "Update EMPLOYEE Contact [ Fill employee details ]";
		updatePanel = new JPanel();
		updatePanel.setLayout(new GridLayout(5, 1));
		label = new JLabel("Employee ID:");
		updatePanel.add(label);
		empId = new JTextField(16);
		updatePanel.add(empId);
		label = new JLabel("Contact No:");
		updatePanel.add(label);
		contactNumber = new JTextField(10);
		updatePanel.add(contactNumber);
		label = new JLabel("Emergency Contact No:");
		updatePanel.add(label);
		emer_Contact = new JTextField(10);
		updatePanel.add(emer_Contact);
		label = new JLabel("Email:");
		updatePanel.add(label);
		e_Mail = new JTextField(16);
		updatePanel.add(e_Mail);
		saveBotton = new JButton("Save Details");
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
			}else if(!(Pattern.matches("\\d{8,12}",contactNumber.getText())))
			{JOptionPane.showMessageDialog(this, "contact number should be from 8 to 12 digit", "Error Message", JOptionPane.ERROR_MESSAGE);
			}else if(!(Pattern.matches("\\d{8,12}",emer_Contact.getText())))
			{JOptionPane.showMessageDialog(this, " Emergency contact number should be from 8 to 12 digit", "Error Message", JOptionPane.ERROR_MESSAGE);
			}else if((Pattern.matches("^[A-Za-z0-9]@[A-Za-z.].[A-Za-z]",e_Mail.getText())))
			{JOptionPane.showMessageDialog(this, " Emergency contact number should be from 8 to 12 digit", "Error Message", JOptionPane.ERROR_MESSAGE);
			}
			else{
				status=true;
			}
			if(status){
			UpdateEmpContactBean emp = new UpdateEmpContactBean();
			emp.setEmpId(empId.getText());
			emp.setContactNo(contactNumber.getText());
			emp.setEmerContactNo(emer_Contact.getText());
			emp.seteMail(e_Mail.getText());
			if(emp.updateEmpContact()){
				JOptionPane.showConfirmDialog(this, "Contact Updated successfuly.\n\nWould you like to continue?", "Message", JOptionPane.YES_NO_OPTION);
				new UpdateEmpContact();
			}else{
				JOptionPane.showConfirmDialog(this, "Employee ID Not Found.\n\nWould you like to continue?", "Message", JOptionPane.YES_NO_OPTION);
			}
			}
		}
	}
}
