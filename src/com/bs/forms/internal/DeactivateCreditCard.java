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
import com.bs.bankrelated.bean.CreditCardDetails;
import com.bs.operations.ClerkOperations;

@SuppressWarnings("serial")
public class DeactivateCreditCard extends JInternalFrame implements ActionListener{
	/* ***************************************************************************/
	JPanel contentPanel, dummyPanel, tempPanel;
	JTextField accountNumber,creditStatus,accountType,accountHolder;
	JButton getDetails,deactivate,cancel;
	CreditCardDetails creditCardDetails;
	/* ***************************************************************************/
	public DeactivateCreditCard(){
		super("CREDIT CARD DE-ACTIVATION                  ");
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
		dummyPanel.setLayout(new GridLayout(5,2, 0,5));
		
		dummyPanel.add(new JLabel("          Account Type"));
		accountType=new JTextField(15);
		accountType.setEditable(false);
		dummyPanel.add(accountType);
		
		dummyPanel.add(new JLabel("       Account Holder"));
		accountHolder=new JTextField(15);
		accountHolder.setEditable(false);
		dummyPanel.add(accountHolder);
		
		dummyPanel.add(new JLabel("              Credit card"));
		creditStatus=new JTextField(15);
		creditStatus.setEditable(false);
		dummyPanel.add(creditStatus);
		
		contentPanel.add(dummyPanel, BorderLayout.CENTER);
		
		dummyPanel=new JPanel();
		deactivate=new JButton("DEACTIVATE");
		deactivate.setEnabled(false);
		deactivate.addActionListener(this);
		dummyPanel.add(deactivate);
		
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
				creditCardDetails=co.getCreditCardDetails(accountNumber.getText());
				if(creditCardDetails.getAccountNumber()!=null){
					accountHolder.setText(creditCardDetails.getAccountHolder());
					accountType.setText(creditCardDetails.getAccountType());
					creditStatus.setText(creditCardDetails.getCreditCardStatus());
					if(creditCardDetails.getCreditCardStatus().equalsIgnoreCase("NO")){
						JOptionPane.showMessageDialog(this, "Credit Card not activated.", "Message", JOptionPane.WARNING_MESSAGE);
					}else{
						deactivate.setEnabled(true);
					}
				}else{
					JOptionPane.showMessageDialog(this, "Account Number not found.", "Error Message", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		if(e.getSource()==deactivate){
			ClerkOperations co=new ClerkActions();
			if(co.deactivateCreditCard(creditCardDetails.getAccountNumber())){
				int opt=JOptionPane.showConfirmDialog(this, "Credit Card deactivated successfully.\n\nWould you like to continue?", "Message", JOptionPane.YES_NO_OPTION);
				if(opt==0){
					accountNumber.setText(null);
					accountHolder.setText(null);
					creditStatus.setText(null);
					accountType.setText(null);
					deactivate.setEnabled(false);
				}else{
					this.dispose();
				}
				
			}else{
				JOptionPane.showMessageDialog(this, "Credit Card deactivation failed.", "Error Message", JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource()==cancel){
			this.dispose();
		}
	}
}
