package com.bs.forms.internal;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.bs.actions.ClerkActions;
import com.bs.operations.ClerkOperations;

@SuppressWarnings("serial")
public class PrintAccountStatement extends JInternalFrame implements ActionListener{
	private JPanel contentPanel, dummyPanel;
	private JTextField accountNumber;
	private JComboBox<String> date, month, year;
	private JButton generateStatement, close;
	
	public PrintAccountStatement(){
		this.setLayout(new FlowLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.closable = true;
		this.title = "PRINT ACCOUNT STATEMENT                        ";
		
		contentPanel=new JPanel();
		contentPanel.setLayout(new GridLayout(3, 1));
		
		dummyPanel=new JPanel();
		dummyPanel.setBorder(BorderFactory.createTitledBorder("Account Number"));
		accountNumber=new JTextField(15);
		accountNumber.setBorder(BorderFactory.createBevelBorder(1));
		
		dummyPanel.add(accountNumber);
		
		contentPanel.add(dummyPanel);
		this.add(contentPanel);
		
		dummyPanel=new JPanel();
		dummyPanel.setBorder(BorderFactory.createTitledBorder("Statement from date"));
		date=new JComboBox<String>();
		date.addItem("DD");
		for(int i=1;i<=31;i++)
			date.addItem(""+i);
		month=new JComboBox<String>();
		month.addItem("MM");
		for(int i=1;i<=12;i++)
			month.addItem(""+i);
		year=new JComboBox<String>();
		year.addItem("YYYY");
		
		Date d=new Date();
		@SuppressWarnings("deprecation")
		int curYear=d.getYear()+1900;
	
		for(int i=1947;i<=curYear;i++)
			year.addItem(""+i);
		dummyPanel.add(date);
		dummyPanel.add(month);
		dummyPanel.add(year);
		contentPanel.add(dummyPanel);
		
		dummyPanel=new JPanel();
		generateStatement=new JButton("GENERATE");
		generateStatement.addActionListener(this);
		dummyPanel.add(generateStatement);
		
		close=new JButton("Close");
		close.addActionListener(this);
		dummyPanel.add(close);
		contentPanel.add(dummyPanel);
		
		this.add(contentPanel);
		
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==close){
			this.dispose();
		}
		if(e.getSource()==generateStatement){
			boolean status=false;
			if(!(Pattern.matches("\\d{11}", accountNumber.getText()))){
				JOptionPane.showMessageDialog(this, "Account Number must be of 11 digit number.", "Error Message", JOptionPane.ERROR_MESSAGE);
			}else if(date.getSelectedItem().equals("DD")||month.getSelectedItem().equals("MM")||year.getSelectedItem().equals("YYYY")){
				JOptionPane.showMessageDialog(this, "You must select a valid date.", "Error Message", JOptionPane.ERROR_MESSAGE);
			}else{
				status=true;
			}
			if(status){			
				ClerkOperations co=new ClerkActions();
				String fromDate=year.getSelectedItem()+"-"+month.getSelectedItem()+"-"+date.getSelectedItem();
				if(co.printAccountStatement(accountNumber.getText(), fromDate)){
					int opt=JOptionPane.showConfirmDialog(this, "Account statement printed successfully.\n\nWould you like to continue?", "Message", JOptionPane.YES_NO_OPTION);
					if(opt==0){
						accountNumber.setText(null);
						date.setSelectedIndex(0);
						month.setSelectedIndex(0);
						year.setSelectedIndex(0);
					}else{
						this.dispose();
					}
				}else{
					JOptionPane.showMessageDialog(this, "Account Number not found.", "Error Message", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
