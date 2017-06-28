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

import com.bs.actions.AdminActions;
import com.bs.bankrelated.bean.DepositViewBean;
import com.bs.bankrelated.bean.DesktopUser;
import com.bs.operations.AdminOperations;

@SuppressWarnings("serial")
public class DeleteDesktopUser extends JInternalFrame implements ActionListener{
	/* ***************************************************************************/
	JPanel contentPanel, dummyPanel, tempPanel;
	JTextField username,accountType,accountStatus;
	JButton getDetails,deleteUser,cancel;
	DepositViewBean customerDetails;
	/* ***************************************************************************/
	public DeleteDesktopUser(){
		super("Delete User                            ");
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
		tempPanel.setBorder(BorderFactory.createTitledBorder("Username"));
		dummyPanel.add(tempPanel);
		
		username=new JTextField(15);
		username.setBorder(BorderFactory.createBevelBorder(1));
		tempPanel.add(username);
		getDetails=new JButton("Get Details");
		getDetails.addActionListener(this);
		tempPanel.add(getDetails);
		
		contentPanel.add(dummyPanel, BorderLayout.NORTH);
		
		dummyPanel=new JPanel();
		dummyPanel.setLayout(new GridLayout(5,2, 0,5));
		
		dummyPanel.add(new JLabel("Account Type"));
		accountType=new JTextField(15);
		accountType.setEditable(false);
		dummyPanel.add(accountType);
		
		dummyPanel.add(new JLabel("Account Status"));
		accountStatus=new JTextField(15);
		accountStatus.setEditable(false);
		dummyPanel.add(accountStatus);
	
		contentPanel.add(dummyPanel, BorderLayout.CENTER);
		
		dummyPanel=new JPanel();
		deleteUser=new JButton("Delete User");
		deleteUser.setEnabled(false);
		deleteUser.addActionListener(this);
		dummyPanel.add(deleteUser);
		
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
			if(!(Pattern.matches("^[A-Za-z0-9]{4,15}",username.getText()))){
				JOptionPane.showMessageDialog(this, "Username must be alphanumeric \ncharacter between 4 to 15.", "Error Message", JOptionPane.ERROR_MESSAGE);
			}else{
				AdminOperations ao=new AdminActions();
				DesktopUser du=ao.viewUser(username.getText());
				if(du.getUsername()!=null){
					accountType.setText(du.getAccountLevel());
					accountStatus.setText(du.getAccountStatus());
					deleteUser.setEnabled(true);
				}
				else{
					JOptionPane.showMessageDialog(this, "Username not found.", "Error Message", JOptionPane.ERROR_MESSAGE);	
				}
				
			}
		}
		if(e.getSource()==deleteUser){
			AdminOperations ao=new AdminActions();
			if(ao.deleteUserAccount(username.getText())){
				int opt =JOptionPane.showConfirmDialog(this, "User deleted successfully.\n\nWould you like to Continue ?", "Message", JOptionPane.YES_NO_OPTION);
				if(opt==1)
					this.dispose();
				else{
					username.setText(null);
					accountType.setText(null);
					accountStatus.setText(null);
					deleteUser.setEnabled(false);
				}
			}
			
		}
		if(e.getSource()==cancel){
			this.dispose();
		}
	}
}