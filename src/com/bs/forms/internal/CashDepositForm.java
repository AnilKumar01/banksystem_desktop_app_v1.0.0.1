package com.bs.forms.internal;

import java.awt.BorderLayout;
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

import com.bs.actions.ClerkActions;
import com.bs.bankrelated.bean.DepositViewBean;
import com.bs.operations.ClerkOperations;

@SuppressWarnings("serial")
public class CashDepositForm extends JInternalFrame implements ActionListener{
	/* ***************************************************************************/
	JPanel contentPanel, dummyPanel, tempPanel;
	JTextField accountNumber,amount,accountType,accountHolder,accountBalance;
	JButton getDetails,deposit,cancel;
	DepositViewBean customerDetails;
	int transNumber;
	/* ***************************************************************************/
	public CashDepositForm(){
		super("Cash DEPOSIT                            ");
		this.setLayout(new FlowLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));		
		this.closable=true;
		this.resizable=true;
		this.maximizable=true;
		/* ***********************************************************************/
		contentPanel=new JPanel();
		contentPanel.setLayout(new BorderLayout());
		this.add(contentPanel);
		
		dummyPanel=new JPanel();
		tempPanel=new JPanel();
		tempPanel.setBorder(BorderFactory.createTitledBorder("Account Number"));
		dummyPanel.add(tempPanel);
		
		accountNumber=new JTextField(15);
		accountNumber.setBorder(BorderFactory.createBevelBorder(1));
		tempPanel.add(accountNumber);
		getDetails=new JButton("Get Details");
		getDetails.addActionListener(this);
		tempPanel.add(getDetails);
		
		contentPanel.add(dummyPanel, BorderLayout.NORTH);
		
		dummyPanel=new JPanel();
		dummyPanel.setLayout(new GridLayout(7,2, 0,5));
		
		dummyPanel.add(new JLabel("Account Type"));
		accountType=new JTextField(15);
		accountType.setEditable(false);
		dummyPanel.add(accountType);
		
		dummyPanel.add(new JLabel("Account Holder"));
		accountHolder=new JTextField(15);
		accountHolder.setEditable(false);
		dummyPanel.add(accountHolder);
		
		dummyPanel.add(new JLabel("Account Balance"));
		accountBalance=new JTextField(15);
		accountBalance.setEditable(false);
		dummyPanel.add(accountBalance);
		
		dummyPanel.add(new JLabel("Deposit Amount"));
		amount=new JTextField(15);
		amount.setEditable(false);
		amount.setBorder(BorderFactory.createBevelBorder(1));
		dummyPanel.add(amount);
		contentPanel.add(dummyPanel, BorderLayout.CENTER);
		
		dummyPanel=new JPanel();
		deposit=new JButton("Deposit");
		deposit.setEnabled(false);
		deposit.addActionListener(this);
		dummyPanel.add(deposit);
		
		cancel=new JButton("Cancel");
		
		cancel.addActionListener(this);
		dummyPanel.add(cancel);
		
		contentPanel.add(dummyPanel, BorderLayout.SOUTH);
		
		/* ***********************************************************************/
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==getDetails){
			if(!(Pattern.matches("\\d{11}", accountNumber.getText()))){
				JOptionPane.showMessageDialog(this, "Account Number must be of 11 digit number.", "Error Message", JOptionPane.ERROR_MESSAGE);
			}else{	
				ClerkOperations co=new ClerkActions();
				customerDetails=co.getDepositViewData(accountNumber.getText());
				if(customerDetails!=null){
					accountBalance.setText(""+customerDetails.getAccountBalance());
					accountHolder.setText(customerDetails.getAccountHolder());
					accountType.setText(customerDetails.getAccountType());
					amount.setEditable(true);
					deposit.setEnabled(true);
				}else{
					JOptionPane.showMessageDialog(this, "Account Number not found.", "Error Message", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		if(e.getSource()==deposit){
			if(!(Pattern.matches("^[0-9.]{1,8}",amount.getText()))){
				JOptionPane.showMessageDialog(this, "Amount can not be empty or must be\n between 1 to 8 digit number.", "Error Message", JOptionPane.ERROR_MESSAGE);
			}else{
				ClerkOperations co=new ClerkActions();
				transNumber=co.cashDeposit(customerDetails.getAccountNumber(), Double.parseDouble(amount.getText()));
				if(transNumber!=-1){
					int opt=JOptionPane.showConfirmDialog(this, "Cash deposit successfully.\nTRANSACTION NUMBER: "+transNumber+"\n\nWould you like to continue?", "Message", JOptionPane.YES_NO_OPTION);
					if(opt==1){
						this.dispose();
					}else{
						customerDetails=null;
						accountNumber.setText(null);
						accountBalance.setText(null);
						accountHolder.setText(null);
						accountType.setText(null);
						amount.setText(null);
						amount.setEditable(false);
					}
				}else{
					JOptionPane.showMessageDialog(this, "Transaction failed!\nCash could not deposit.", "Error Message", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		if(e.getSource()==cancel){
			this.dispose();
		}
	}
}
