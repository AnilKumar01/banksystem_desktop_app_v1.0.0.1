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
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.bs.actions.AdminActions;
import com.bs.bankrelated.bean.DesktopUser;
import com.bs.operations.AdminOperations;

@SuppressWarnings("serial")
public class AddDesktopUser extends JInternalFrame implements ActionListener{
	/* ***************************************************************************/
	JPanel contentPanel, dummyPanel, dummyPanel2, tempDummy;
	JTextField username, password;
	JScrollPane tablePane;
	JRadioButton locked, unlocked;
	ButtonGroup accountStatusGrp;
	JButton addUser, cancel;
	DefaultTableModel model;
	
	JComboBox<String>accountLevel;
	/* ***************************************************************************/
	
	public AddDesktopUser(){
		super("ADD NEW DESKTOP USER                    ");
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
		dummyPanel.setLayout(new GridLayout(2, 1));
		
		//USERNAME AND PASSWORD
		dummyPanel2=new JPanel();
		//USERNAME
		tempDummy=new JPanel();
		tempDummy.setBorder(BorderFactory.createTitledBorder("Username"));
		username=new JTextField(15);
		username.setBorder(BorderFactory.createBevelBorder(1));
		tempDummy.add(username);
		dummyPanel2.add(tempDummy);
		
		//PASSOWRD
		tempDummy=new JPanel();
		tempDummy.setBorder(BorderFactory.createTitledBorder("Password"));
		password=new JTextField(15);
		password.setBorder(BorderFactory.createBevelBorder(1));
		tempDummy.add(password);
		dummyPanel2.add(tempDummy);
		
		dummyPanel.add(dummyPanel2);

		//ACCOUNT_LEVEL AND ACCOUNT STATUS
		//ACCOUNT_LEVEL
		dummyPanel2=new JPanel();
		accountLevel=new JComboBox<String>();
		accountLevel.addItem("Select Account Type");
		accountLevel.addItem("ADMIN");
		accountLevel.addItem("CLERK");
		accountLevel.addItem("HR");
//		accountLevel.addItem("MANAGER");
//		accountLevel.addItem("OFFICER");
		dummyPanel2.add(accountLevel);
		dummyPanel.add(dummyPanel2);
		
		//ACCOUNT_SATUS
		tempDummy=new JPanel();
		tempDummy.setBorder(BorderFactory.createTitledBorder("Account Status"));
		locked=new JRadioButton("Locked");
		locked.setSelected(true);
		tempDummy.add(locked);
		unlocked=new JRadioButton("Unlocked");
		tempDummy.add(unlocked);
		accountStatusGrp=new ButtonGroup();
		accountStatusGrp.add(locked);
		accountStatusGrp.add(unlocked);
		dummyPanel2.add(tempDummy);
		
		contentPanel.add(dummyPanel);
		
		//BUTTONS
		dummyPanel2=new JPanel();
		addUser=new JButton("Add User");
		addUser.addActionListener(this);
		dummyPanel2.add(addUser);
		cancel=new JButton("Cancel");
		cancel.addActionListener(this);
		dummyPanel2.add(cancel);
		contentPanel.add(dummyPanel2, BorderLayout.SOUTH);
		//contentPanel.add(dummyPanel);
		/* ***********************************************************************/
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {		
		if(e.getSource()==addUser){
			boolean status=false;
			DesktopUser user=new DesktopUser();
			if(!(Pattern.matches("^[A-Za-z0-9]{4,15}",username.getText()))){
				JOptionPane.showMessageDialog(this, "Username must be alphanumeric \ncharacter between 4 to 15.", "Error Message", JOptionPane.ERROR_MESSAGE);			   
			}else if(!(Pattern.matches("^[A-Za-z0-9@#$]{8,15}",password.getText()))){
				JOptionPane.showMessageDialog(this, "Password can be alphanumric or special \ncharacter like @#$ and between 4 to 15.", "Error Message", JOptionPane.ERROR_MESSAGE); 
			}else if(accountLevel.getSelectedItem().equals("Select Account Type")){
				JOptionPane.showMessageDialog(this, "You must select either clerk ,hr or admin." , "Error Message", JOptionPane.ERROR_MESSAGE);
			}else{
				status =true;
			}
			if(status){
				user.setUsername(username.getText());
				user.setPassword(password.getText());
				user.setAccountLevel((String) accountLevel.getSelectedItem());
				if(locked.isSelected()){
					user.setAccountStatus("LOCKED");
				}else{
					user.setAccountStatus("UNLOCKED");
				}
				AdminOperations ao=new AdminActions();
				if(ao.addNewUserAccount(user)){
					int opt=JOptionPane.showConfirmDialog(this, "New user added successfuly.\n\nWould you like to continue?", "Message", JOptionPane.YES_NO_OPTION);
					if(opt==1){
						this.dispose();
					}else{
						username.setText(null);
						password.setText(null);
						locked.setEnabled(true);
						accountLevel.setSelectedIndex(0);
					}
				}else{
					JOptionPane.showMessageDialog(this, "Duplicate user. Username already in use.", "Error Message", JOptionPane.ERROR_MESSAGE);
				}
			}
		}
		if(e.getSource()==cancel){
			this.dispose();
		}
	}
}
