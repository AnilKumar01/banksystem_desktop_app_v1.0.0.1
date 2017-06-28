package com.bs.forms.internal;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.bs.actions.ClerkActions;
import com.bs.customerrelated.bean.AccountInfo;
import com.bs.operations.ClerkOperations;

public class AccountDetail extends JInternalFrame implements ActionListener{
	private static final long serialVersionUID = 1L;

	private JPanel contentPanel, dummyPanel;
	private JTextField accountNumber, accountType, branchCode, branchIfsc,
			branchLocation, branchTel, branchFax, accountHolder, contactNumber,
			netBkSt, debitSt, creditSt;
	private JTextArea branchAddress, address;
	private JButton getDetails, close;
	private AccountInfo accountInfo;
	public AccountDetail() {
		this.setLayout(new FlowLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.closable = true;
		this.title = "ACCOUNT DETAILS                        ";

		detailForm();
		
		this.setVisible(true);
	}
	private void detailForm() {
		contentPanel=new JPanel();
		contentPanel.setLayout(new GridLayout(8,2));
		
		dummyPanel=new JPanel();
		dummyPanel.setBorder(BorderFactory.createTitledBorder("Account Number"));
		accountNumber=new JTextField(15);
		accountNumber.setBorder(BorderFactory.createBevelBorder(1));
		dummyPanel.add(accountNumber);
		getDetails=new JButton("Get Details");
		getDetails.addActionListener(this);
		dummyPanel.add(getDetails);
		contentPanel.add(dummyPanel);
		
		dummyPanel=new JPanel();
		dummyPanel.setBorder(BorderFactory.createTitledBorder("Account Type"));
		accountType=new JTextField(15);
		accountType.setBorder(BorderFactory.createBevelBorder(1));
		accountType.setEditable(false);
		dummyPanel.add(accountType);
		contentPanel.add(dummyPanel);
		
		dummyPanel=new JPanel();
		dummyPanel.setBorder(BorderFactory.createTitledBorder("Branch Code"));
		branchCode=new JTextField(15);
		branchCode.setBorder(BorderFactory.createBevelBorder(1));
		branchCode.setEditable(false);
		dummyPanel.add(branchCode);
		contentPanel.add(dummyPanel);
		
		dummyPanel=new JPanel();
		dummyPanel.setBorder(BorderFactory.createTitledBorder("IFSC Code"));
		branchIfsc=new JTextField(15);
		branchIfsc.setBorder(BorderFactory.createBevelBorder(1));
		branchIfsc.setEditable(false);
		dummyPanel.add(branchIfsc);
		contentPanel.add(dummyPanel);
		
		dummyPanel=new JPanel();
		dummyPanel.setBorder(BorderFactory.createTitledBorder("Branch Location"));
		branchLocation=new JTextField(15);
		branchLocation.setBorder(BorderFactory.createBevelBorder(1));
		branchLocation.setEditable(false);
		dummyPanel.add(branchLocation);
		contentPanel.add(dummyPanel);
		
		dummyPanel=new JPanel();
		dummyPanel.setBorder(BorderFactory.createTitledBorder("Branch Telephone"));
		branchTel=new JTextField(15);
		branchTel.setBorder(BorderFactory.createBevelBorder(1));
		branchTel.setEditable(false);
		dummyPanel.add(branchTel);
		contentPanel.add(dummyPanel);
		
		dummyPanel=new JPanel();
		dummyPanel.setBorder(BorderFactory.createTitledBorder("Branch FAX"));
		branchFax=new JTextField(15);
		branchFax.setBorder(BorderFactory.createBevelBorder(1));
		branchFax.setEditable(false);
		dummyPanel.add(branchFax);
		contentPanel.add(dummyPanel);
		
		dummyPanel=new JPanel();
		dummyPanel.setBorder(BorderFactory.createTitledBorder("Branch Address"));
		branchAddress=new JTextArea(2, 15);
		branchAddress.setBorder(BorderFactory.createBevelBorder(1));
		branchAddress.setEditable(false);
		dummyPanel.add(branchAddress);
		contentPanel.add(dummyPanel);
		
		dummyPanel=new JPanel();
		dummyPanel.setBorder(BorderFactory.createTitledBorder("Account Holder"));
		accountHolder=new JTextField(15);
		accountHolder.setBorder(BorderFactory.createBevelBorder(1));
		accountHolder.setEditable(false);
		dummyPanel.add(accountHolder);
		contentPanel.add(dummyPanel);
		
		dummyPanel=new JPanel();
		dummyPanel.setBorder(BorderFactory.createTitledBorder("Contact Number"));
		contactNumber=new JTextField(15);
		contactNumber.setBorder(BorderFactory.createBevelBorder(1));
		contactNumber.setEditable(false);
		dummyPanel.add(contactNumber);
		contentPanel.add(dummyPanel);
		
		dummyPanel=new JPanel();
		dummyPanel.setBorder(BorderFactory.createTitledBorder("Net Banking"));
		netBkSt=new JTextField(15);
		netBkSt.setBorder(BorderFactory.createBevelBorder(1));
		netBkSt.setEditable(false);
		dummyPanel.add(netBkSt);
		contentPanel.add(dummyPanel);
		
		dummyPanel=new JPanel();
		dummyPanel.setBorder(BorderFactory.createTitledBorder("Debit Card"));
		debitSt=new JTextField(15);
		debitSt.setBorder(BorderFactory.createBevelBorder(1));
		debitSt.setEditable(false);
		dummyPanel.add(debitSt);
		contentPanel.add(dummyPanel);
		
		dummyPanel=new JPanel();
		dummyPanel.setBorder(BorderFactory.createTitledBorder("Credit Card"));
		creditSt=new JTextField(15);
		creditSt.setBorder(BorderFactory.createBevelBorder(1));
		creditSt.setEditable(false);
		dummyPanel.add(creditSt);
		contentPanel.add(dummyPanel);
		
		dummyPanel=new JPanel();
		dummyPanel.setBorder(BorderFactory.createTitledBorder("Customer Address"));
		address=new JTextArea(2, 15);
		address.setBorder(BorderFactory.createBevelBorder(1));
		address.setEditable(false);
		dummyPanel.add(address);
		contentPanel.add(dummyPanel);
		
		dummyPanel=new JPanel();
//		close=new JButton("CLOSE");
//		dummyPanel.add(close);
		contentPanel.add(dummyPanel);
		
		dummyPanel=new JPanel();
		close=new JButton("CLOSE");
		close.addActionListener(this);
		dummyPanel.add(close);
		contentPanel.add(dummyPanel);
		
		this.add(contentPanel);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==close){
			this.dispose();
		}
		if(e.getSource()==getDetails){
			if(!(Pattern.matches("\\d{11}", accountNumber.getText()))){
				JOptionPane.showMessageDialog(this, "Account Number must be of 11 digit number.", "Error Message", JOptionPane.ERROR_MESSAGE);
			}else{
				ClerkOperations co=new ClerkActions();
				accountInfo=co.getAccountInfo(accountNumber.getText());
				if(accountInfo.getAccounNumber()!=null){
					accountHolder.setText(accountInfo.getAccountHolder());
					accountType.setText(accountInfo.getAccountType());
					address.setText(accountInfo.getCustomerAddress());
					branchAddress.setText(accountInfo.getBranchAddress());
					branchCode.setText(accountInfo.getBranchCode());
					branchFax.setText(accountInfo.getBranchFax());
					branchIfsc.setText(accountInfo.getIfscCode());
					branchLocation.setText(accountInfo.getBranchLocation());
					branchTel.setText(accountInfo.getBranchTel());
					contactNumber.setText(accountInfo.getMobileNumber());
					creditSt.setText(accountInfo.getCreditCard());
					debitSt.setText(accountInfo.getDebitCardStatus());
					netBkSt.setText(accountInfo.getNetBankingStatus());				
				}else{
					JOptionPane.showMessageDialog(this, "Account number not found.", "Error Message", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
	}
}
