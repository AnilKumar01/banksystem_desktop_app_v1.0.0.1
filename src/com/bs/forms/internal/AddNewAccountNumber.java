/* *****************************************************************************
 * Project: Bank System
 * Purpose: Form for adding new account numbers in the bank database.
 * Author: Anil Kumar(dac11)
 * Filename: AddNewAccountNumber.java
 * Version: 1.0
 * Start date: 21-Dec-2014
 * End date:
 * *****************************************************************************/

package com.bs.forms.internal;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.bs.actions.AdminActions;
import com.bs.customerrelated.bean.Account;
import com.bs.operations.AdminOperations;

@SuppressWarnings("serial")
public class AddNewAccountNumber extends JInternalFrame implements ActionListener{
	/* **********************************************************************/
	JTextField accountNumber;
	JRadioButton netYes, netNo, checkYes, checkNo;
	JRadioButton debitYes, debitNo, creditYes, creditNo;
	JRadioButton saving, current;
	ButtonGroup btnGrp;
	JButton addAccount, cancel;
	JPanel contentPanel, dummyPanel, tempPanel;
	/* **********************************************************************/
	
	public AddNewAccountNumber(){
		super("ADD NEW ACCOUNT NUMBER                  ");
		this.setLayout(new FlowLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));		
		this.closable=true;
		this.resizable=true;
		this.maximizable=true;
		/* ******************************************************************/
		addAccountNumberForm();
		/* ******************************************************************/
		
		this.setVisible(true);
	}

	private void addAccountNumberForm() {
		contentPanel=new JPanel();
		contentPanel.setLayout(new GridLayout(4, 2));
		this.add(contentPanel);
		//ACCOUNT NUMBER AND ACCOUNT TYPE
		//ACCONU NUMBER
		dummyPanel=new JPanel();
		tempPanel=new JPanel();
		tempPanel.setBorder(BorderFactory.createTitledBorder("Account Number"));
		accountNumber=new JTextField(15);
		accountNumber.setBorder(BorderFactory.createBevelBorder(1));
		tempPanel.add(accountNumber);
		dummyPanel.add(tempPanel);
		
		//ACCOUNT TYPE
		tempPanel=new JPanel();
		tempPanel.setBorder(BorderFactory.createTitledBorder("Account Type"));
		saving=new JRadioButton("Saving");
		saving.setSelected(true);
		tempPanel.add(saving);
		current=new JRadioButton("Current");
		btnGrp=new ButtonGroup();
		btnGrp.add(saving);
		btnGrp.add(current);
		tempPanel.add(current);
		dummyPanel.add(tempPanel);
		contentPanel.add(dummyPanel);
		
		//NET BANKING AND CHECK BOOK
		//NET BANKING
		dummyPanel=new JPanel();
		tempPanel=new JPanel();
		tempPanel.setBorder(BorderFactory.createTitledBorder("Net Banking"));
		netYes=new JRadioButton("Yes");
		tempPanel.add(netYes);
		netNo=new JRadioButton("No");
		netNo.setSelected(true);
		tempPanel.add(netNo);
		btnGrp=new ButtonGroup();
		btnGrp.add(netYes);
		btnGrp.add(netNo);
		dummyPanel.add(tempPanel);

		//CHECK BOOK
		tempPanel=new JPanel();
		tempPanel.setBorder(BorderFactory.createTitledBorder("Check Book"));
		checkYes=new JRadioButton("Yes");
		tempPanel.add(checkYes);
		checkNo=new JRadioButton("No");
		checkNo.setSelected(true);
		tempPanel.add(checkNo);
		btnGrp=new ButtonGroup();
		btnGrp.add(checkYes);
		btnGrp.add(checkNo);
		dummyPanel.add(tempPanel);
		
		contentPanel.add(dummyPanel);
		//CREDIT CARD AND DEBIT CARD
		//CREDIT CARD
		dummyPanel=new JPanel();
		tempPanel=new JPanel();
		tempPanel.setBorder(BorderFactory.createTitledBorder("Credit Card"));
		creditYes=new JRadioButton("Yes");
		tempPanel.add(creditYes);
		creditNo=new JRadioButton("No");
		creditNo.setSelected(true);
		tempPanel.add(creditNo);
		btnGrp=new ButtonGroup();
		btnGrp.add(creditYes);
		btnGrp.add(creditNo);
		dummyPanel.add(tempPanel);
		//DEBIT CARD
		tempPanel=new JPanel();
		tempPanel.setBorder(BorderFactory.createTitledBorder("Debit Card"));
		debitYes=new JRadioButton("Yes");
		tempPanel.add(debitYes);
		debitNo=new JRadioButton("No");
		debitNo.setSelected(true);
		tempPanel.add(debitNo);
		dummyPanel.add(tempPanel);
		btnGrp=new ButtonGroup();
		btnGrp.add(debitYes);
		btnGrp.add(debitNo);;
		contentPanel.add(dummyPanel);
		
		//BUTTONS
		dummyPanel=new JPanel();
		tempPanel=new JPanel();
		addAccount=new JButton("Add Account");
		addAccount.addActionListener(this);
		tempPanel.add(addAccount);
		cancel=new JButton("Cancel");
		cancel.addActionListener(this);
		tempPanel.add(cancel);
		dummyPanel.add(tempPanel);
		contentPanel.add(dummyPanel);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==addAccount){
			if(accountNumber.getText().length()==0){
				JOptionPane.showMessageDialog(this, "Account number field cannot be empty.", "Error Message", JOptionPane.ERROR_MESSAGE);
			}else{
				if(accountNumber.getText().length()==11){
					if(Pattern.matches("\\d*", accountNumber.getText()))
					{
						addAccountNumber();
					}else{
						JOptionPane.showMessageDialog(this, "Account number must be numeric.", "Error Message", JOptionPane.ERROR_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(this, "Account number must be of 11 digits.", "Error Message", JOptionPane.ERROR_MESSAGE);
				}
				
			}
			
		}
		if(e.getSource()==cancel){
			this.dispose();
		}
		
	}

	private void addAccountNumber() {
		Account account=new Account();
		account.setAccountNo(accountNumber.getText());
		if(saving.isSelected()){
			account.setAccountType("SAVING");
		}else{
			account.setAccountType("CURRENT");
		}
		if(netNo.isSelected()){
			account.setNetBanking("NO");
		}else{
			account.setNetBanking("YES");
		}
		if(checkNo.isSelected()){
			account.setCheckBook("NO");
		}else{
			account.setCheckBook("Yes");
		}
		if(creditNo.isSelected()){
			account.setCreditCard("NO");
		}else{
			account.setCreditCard("YES");
		}
		if(debitNo.isSelected()){
			account.setDebitCard("NO");
		}else{
			account.setDebitCard("YES");
		}
		
		AdminOperations ao=new AdminActions();
		if(ao.addNewAccountNumber(account)){
			int opt=JOptionPane.showConfirmDialog(this, "Account number added successfuly.\n\nWould like to continue?", "Message", JOptionPane.YES_NO_OPTION);
			if(opt==1){
				this.dispose();
			}else{
				accountNumber.setText(null);
				saving.setSelected(true);
				debitNo.setSelected(true);
				creditNo.setSelected(true);
				netNo.setSelected(true);
				checkNo.setSelected(true);
			}
			
		}else{
			JOptionPane.showMessageDialog(this, "Duplicate account number. It is already exist.", "Error Message", JOptionPane.ERROR_MESSAGE);
		}
	}
}
