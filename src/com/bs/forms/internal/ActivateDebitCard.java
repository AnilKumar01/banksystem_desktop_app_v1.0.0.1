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
import com.bs.bankrelated.bean.DebitCardDetails;
import com.bs.operations.ClerkOperations;

@SuppressWarnings("serial")
public class ActivateDebitCard extends JInternalFrame implements ActionListener{
	/* ***************************************************************************/
	JPanel contentPanel, dummyPanel, tempPanel;
	JTextField accountNumber,debitStatus, debitCardNo,accountType,accountHolder;
	JButton getDetails,activate,cancel;
	DebitCardDetails debitCardDetails;
	/* ***************************************************************************/
	public ActivateDebitCard(){
		super("DEBIT CARD ACTIVATION                  ");
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
		
		dummyPanel.add(new JLabel("          Account Type"));
		accountType=new JTextField(15);
		accountType.setEditable(false);
		dummyPanel.add(accountType);
		
		dummyPanel.add(new JLabel("       Account Holder"));
		accountHolder=new JTextField(15);
		accountHolder.setEditable(false);
		dummyPanel.add(accountHolder);
		
		dummyPanel.add(new JLabel("              Debit card"));
		debitStatus=new JTextField(15);
		debitStatus.setEditable(false);
		dummyPanel.add(debitStatus);
		
		dummyPanel.add(new JLabel("        Debit card no."));
		debitCardNo=new JTextField(15);
		debitCardNo.setEditable(false);
		debitCardNo.setBorder(BorderFactory.createBevelBorder(1));
		dummyPanel.add(debitCardNo);
		contentPanel.add(dummyPanel, BorderLayout.CENTER);
		
		dummyPanel=new JPanel();
		activate=new JButton("ACTIVATE");
		activate.setEnabled(false);
		activate.addActionListener(this);
		dummyPanel.add(activate);
		
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
				debitCardDetails=co.getDebitCardDetails(accountNumber.getText());
				if(debitCardDetails.getAccountNumber()!=null){
					accountHolder.setText(debitCardDetails.getAccountHolder());
					accountType.setText(debitCardDetails.getAccountType());
					debitStatus.setText(debitCardDetails.getDebitCardStatus());
					if(debitCardDetails.getDebitCardStatus().equalsIgnoreCase("NO")){
						activate.setEnabled(true);
						debitCardNo.setEditable(true);
					}else{
						JOptionPane.showMessageDialog(this, "Debit Card already activated.", "Message", JOptionPane.WARNING_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(this, "Account Number not found.", "Error Message", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		if(e.getSource()==activate){
			if(!(Pattern.matches("\\d{16}", debitCardNo.getText()))){
				JOptionPane.showMessageDialog(this, "Debit card serial number\n must be of 16 digit number.", "Error Message", JOptionPane.ERROR_MESSAGE);
			}else{
				ClerkOperations co=new ClerkActions();
				if(co.activateDebitCard(debitCardNo.getText(), debitCardDetails.getAccountNumber())){
					int opt=JOptionPane.showConfirmDialog(this, "Debit Card activated successfully.\n\nWould you like to continue?", "Message", JOptionPane.YES_NO_OPTION);
					if(opt==0){
						accountNumber.setText(null);
						accountHolder.setText(null);
						debitStatus.setText(null);
						debitCardNo.setText(null);
						debitCardNo.setEditable(false);
						accountType.setText(null);
						activate.setEnabled(false);
					}else{
						this.dispose();
					}
					
				}else{
					JOptionPane.showMessageDialog(this, "Debit Card activation failed.", "Error Message", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		if(e.getSource()==cancel){
			this.dispose();
		}
	}
}
