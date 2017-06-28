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
import com.bs.customerrelated.bean.NetBankingDetails;
import com.bs.operations.ClerkOperations;

@SuppressWarnings("serial")
public class ActivateNetBanking extends JInternalFrame implements ActionListener{
	/* ***************************************************************************/
	JPanel contentPanel, dummyPanel, tempPanel;
	JTextField accountNumber,netBkStatus, netBkSrNo,accountType,accountHolder;
	JButton getDetails,activate,cancel;
	NetBankingDetails netBankingDetails;
	/* ***************************************************************************/
	public ActivateNetBanking(){
		super("NET BANKING ACTIVATION                  ");
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
		
		dummyPanel.add(new JLabel("              Account Type"));
		accountType=new JTextField(15);
		accountType.setEditable(false);
		dummyPanel.add(accountType);
		
		dummyPanel.add(new JLabel("            Account Holder"));
		accountHolder=new JTextField(15);
		accountHolder.setEditable(false);
		dummyPanel.add(accountHolder);
		
		dummyPanel.add(new JLabel("    Net Banking Status"));
		netBkStatus=new JTextField(15);
		netBkStatus.setEditable(false);
		dummyPanel.add(netBkStatus);
		
		dummyPanel.add(new JLabel("Net Banking Serial No."));
		netBkSrNo=new JTextField(15);
		netBkSrNo.setEditable(false);
		netBkSrNo.setBorder(BorderFactory.createBevelBorder(1));
		dummyPanel.add(netBkSrNo);
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
				netBankingDetails=co.getNetBankingDetails(accountNumber.getText());
				if(netBankingDetails.getAccountNumber()!=null){
					accountHolder.setText(netBankingDetails.getAccountHolder());
					accountType.setText(netBankingDetails.getAccountType());
					netBkStatus.setText(netBankingDetails.getNetBkStatus());
					if(netBankingDetails.getNetBkStatus().equalsIgnoreCase("NO")){
						activate.setEnabled(true);
						netBkSrNo.setEditable(true);
					}else{
						JOptionPane.showMessageDialog(this, "Net banking already activated.", "Message", JOptionPane.WARNING_MESSAGE);
					}
				}else{
					JOptionPane.showMessageDialog(this, "Account Number not found.", "Error Message", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		if(e.getSource()==activate){
			if(!(Pattern.matches("\\d{7}", netBkSrNo.getText()))){
				JOptionPane.showMessageDialog(this, "Net banking serial number\n must be of 7 digit number.", "Error Message", JOptionPane.ERROR_MESSAGE);
			}else{
				ClerkOperations co=new ClerkActions();
				if(co.activateNetBanking(netBkSrNo.getText(), netBankingDetails.getAccountNumber())){
					int opt=JOptionPane.showConfirmDialog(this, "Net banking activated successfully.\n\nWould you like to continue?", "Message", JOptionPane.YES_NO_OPTION);
					if(opt==0){
						accountNumber.setText(null);
						accountHolder.setText(null);
						netBkStatus.setText(null);
						netBkSrNo.setText(null);
						netBkSrNo.setEditable(false);
						accountType.setText(null);
						activate.setEnabled(false);
					}else{
						this.dispose();
					}
					
				}else{
					JOptionPane.showMessageDialog(this, "Net banking activation failed.", "Error Message", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		if(e.getSource()==cancel){
			this.dispose();
		}
	}
}
