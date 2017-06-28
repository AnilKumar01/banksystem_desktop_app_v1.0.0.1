package com.bs.forms.internal;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.bs.actions.AdminActions;
import com.bs.bankrelated.bean.DesktopUser;
import com.bs.operations.AdminOperations;

@SuppressWarnings("serial")
public class ChangePassword extends JInternalFrame implements ActionListener,FocusListener{
	/* ***************************************************************************/
	JPanel contentPanel, dummyPanel, tempPanel;
	JTextField username;
	JPasswordField oldPassword,newPassword, confirmPassword;
	JButton cancel,changePassword;
	
	DesktopUser user;
	/* ***************************************************************************/
	public ChangePassword(DesktopUser user){
		super("Change Password                            ");
		
		this.user=user;
		
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
		username.setText(user.getUsername());
		username.setEditable(false);
		username.setBorder(BorderFactory.createBevelBorder(1));
		tempPanel.add(username);
		contentPanel.add(dummyPanel, BorderLayout.NORTH);
		
		dummyPanel=new JPanel();
		dummyPanel.setLayout(new GridLayout(8,2, 0,5));
		
		dummyPanel.add(new JLabel("Old Password"));
		oldPassword=new JPasswordField(15);
		oldPassword.addFocusListener(this);
		dummyPanel.add(oldPassword);
		
		dummyPanel.add(new JLabel("New Password"));
		newPassword=new JPasswordField(15);
		dummyPanel.add(newPassword);
		
		dummyPanel.add(new JLabel("Confirm New Password"));
		confirmPassword=new JPasswordField(15);
		dummyPanel.add(confirmPassword);
	
		contentPanel.add(dummyPanel, BorderLayout.CENTER);
		
		dummyPanel=new JPanel();
		changePassword=new JButton("Change Password");
		changePassword.setEnabled(false);
		changePassword.addActionListener(this);
		dummyPanel.add(changePassword);
		
		cancel=new JButton("Cancel");
		
		cancel.addActionListener(this);
		dummyPanel.add(cancel);	
		contentPanel.add(dummyPanel, BorderLayout.SOUTH);
		
		/* ***********************************************************************/
		this.setVisible(true);
	}
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==changePassword){
			if(!(Pattern.matches("^[A-Za-z0-9@#$]{8,15}",newPassword.getText()))){
				JOptionPane.showMessageDialog(this, "Password can be alphanumric or special \ncharacter like @#$ and between 4 to 15.", "Error Message", JOptionPane.ERROR_MESSAGE); 
			}else{
				AdminOperations ao=new AdminActions();
				if((newPassword.getText().equals(confirmPassword.getText()))){
					user.setPassword(newPassword.getText());
					if(ao.changePassword(user)){
						JOptionPane.showMessageDialog(this, "Password Updated successfully.", "Message", JOptionPane.PLAIN_MESSAGE);
						this.dispose();
					}
				}else{
					JOptionPane.showMessageDialog(this, "Passwords do not match", "Error Message", JOptionPane.ERROR_MESSAGE);	
				}
			}
		}
		if(e.getSource()==cancel){
			this.dispose();
		}
	}
	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}
	@SuppressWarnings("deprecation")
	@Override
	public void focusLost(FocusEvent e) {
		if(e.getSource()==oldPassword){
			if(user.getPassword().equals(oldPassword.getText())){
				changePassword.setEnabled(true);
			}
			else{
				JOptionPane.showMessageDialog(this, "Wrong old password.", "Error Message", JOptionPane.ERROR_MESSAGE);	
			}
		}
		
		
	}
}