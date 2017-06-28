package com.bs.forms.internal;

import java.awt.BorderLayout;
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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.bs.actions.AdminActions;
import com.bs.bankrelated.bean.DesktopUser;
import com.bs.operations.AdminOperations;

@SuppressWarnings("serial")
public class ModifyDesktopUser extends JInternalFrame implements ActionListener{
	/* ***************************************************************************/
	JPanel contentPanel, dummyPanel, tempPanel;
	JTextField username,accountType,accountStatus,password;
	JButton getDetails,close,modify;
	JRadioButton locked,unlocked;
	ButtonGroup accountStatusGrp;
	/* ***************************************************************************/
	public ModifyDesktopUser(){
		super("Lock/Unlock User Details                            ");
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
		
		dummyPanel.add(new JLabel("Password"));
		password=new JTextField(15);
		password.setEditable(false);
		dummyPanel.add(password);
		
		dummyPanel.add(new JLabel("Account Type"));
		accountType=new JTextField(15);
		accountType.setEditable(false);
		dummyPanel.add(accountType);
		
		dummyPanel.add(new JLabel("Account Status"));
		locked=new JRadioButton("Locked");
		dummyPanel.add(locked);
		dummyPanel.add(new JLabel("               "));
		unlocked=new JRadioButton("Unlocked");
		dummyPanel.add(unlocked);
		accountStatusGrp=new ButtonGroup();
		accountStatusGrp.add(locked);
		accountStatusGrp.add(unlocked);
		
	
		contentPanel.add(dummyPanel, BorderLayout.CENTER);
		
		dummyPanel=new JPanel();
		modify=new JButton("Modify");
		dummyPanel.add(modify);
		modify.setEnabled(false);
		modify.addActionListener(this);
		
		close=new JButton("Close");
		dummyPanel.add(close);
		close.addActionListener(this);
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
				modify.setEnabled(true);
				AdminOperations ao=new AdminActions();
				DesktopUser du=ao.viewUser(username.getText());
				if(du.getUsername()!=null){
					password.setText(du.getPassword());
					accountType.setText(du.getAccountLevel());
						if(du.getAccountStatus().equalsIgnoreCase("Locked")){
							locked.setSelected(true);
						}
						else 
							unlocked.setSelected(true);
							
				}
				else{
					JOptionPane.showMessageDialog(this, "Username not found.", "Error Message", JOptionPane.ERROR_MESSAGE);	
				}
			}
		}
		
		if(e.getSource()==modify){
			AdminOperations aop=new AdminActions();
			String temp=null;
			if(locked.isSelected()){
				temp="LOCKED";
			}
			else{
				temp="UNLOCKED";
			}
			if(aop.modifyUserAccount(username.getText(),temp)){
				int opt =JOptionPane.showConfirmDialog(this, "Account Status Modified.\n\nWould you like to Continue ?", "Message", JOptionPane.YES_NO_OPTION);
				if(opt==1)
					this.dispose();
				else{
					username.setText(null);
					password.setText(null);
					accountType.setText(null);
//					locked.setSelected(false);
//					unlocked.setSelected(false);
					accountStatusGrp.clearSelection();
					modify.setEnabled(false);
				}
			}
			
		}

		
		if(e.getSource()==close){
			this.dispose();
		}
	}
}