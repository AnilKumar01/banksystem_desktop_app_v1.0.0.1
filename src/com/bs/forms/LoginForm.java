/* *************************************************************************
 * Project: Bank System
 * Purpose: To make an account opening form.
 * Start date: Nov 28, 2014
 * Authors: Anil Kumar (DAC-11) 			
 * End Date:  
 * ***************************************************************************/

package com.bs.forms;

import java.awt.Color;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.Border;

import com.bs.actions.MasterAction;
import com.bs.bankrelated.bean.DesktopUser;
import com.bs.operations.MasterOperations;

@SuppressWarnings("serial")
public class LoginForm extends JFrame implements ActionListener, WindowListener {
	
	private JPanel contentPnl;
	private JTextField usrT;
	private JPasswordField pwdP;
	private JLabel usrL, pwdL, message, message1;
	private JButton loginB, quitB;
	private String CONNECTION_ERROR = "Error: Unable to connect database.";
	private String BLOCKED_ERROR1 = "ERROR: This system is blocked.";
	private String BLOCKED_ERROR2 = "Contact your administrator.";

	private String WRONG_USER_PASSWORD = "Invalid username or password.";
//	private String ATTEMPT_LEFT = "Login attempts left ";

	public LoginForm() {
		super("Login");
		this.setSize(400, 250);
		this.setLocation(320, 200);
		this.setResizable(false);
		
			this.doLogin();
			//this.connectionError();
		// this.systemBlockedError();
	}

	@SuppressWarnings("unused")
	private void systemBlockedError() {
		Border b = BorderFactory.createBevelBorder(1);
		contentPnl = new JPanel();
		contentPnl.setBorder(b);
		contentPnl.setBackground(new Color(255, 240, 245));
		contentPnl.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		message = new JLabel(this.BLOCKED_ERROR1);
		message.setForeground(Color.red);
		contentPnl.add(message, gbc);

		gbc.gridy = 1;
		message1 = new JLabel(this.BLOCKED_ERROR2);
		message1.setForeground(Color.red);
		contentPnl.add(message1, gbc);

		this.add(contentPnl);
	}

	@SuppressWarnings("unused")
	private void connectionError() {
		Border b = BorderFactory.createBevelBorder(1);
		contentPnl = new JPanel();
		contentPnl.setBorder(b);
		contentPnl.setBackground(new Color(255, 240, 245));
		contentPnl.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		message = new JLabel(CONNECTION_ERROR);
		message.setForeground(Color.red);
		contentPnl.add(message, gbc);

		this.add(contentPnl);
	}

	private void doLogin() {
		Border b = BorderFactory.createBevelBorder(1);
		contentPnl = new JPanel();
		contentPnl.setBackground(new Color(255, 240, 245));
		contentPnl.setLayout(new GridBagLayout());
		contentPnl.setBorder(b);

		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.ipadx = 20;
		usrL = new JLabel("USERNAME");
		contentPnl.add(usrL, gbc);

		gbc.gridx = 1;
		usrT = new JTextField(10);
		usrT.selectAll();
		usrT.setBorder(b);
		contentPnl.add(usrT, gbc);

		// dummy panel
		gbc.gridy = 1;
		JPanel pnl_1 = new JPanel();
		pnl_1.setBackground(new Color(255, 240, 245));
		contentPnl.add(pnl_1, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		pwdL = new JLabel("PASSWORD");
		contentPnl.add(pwdL, gbc);

		gbc.gridx = 1;
		pwdP = new JPasswordField(10);
		pwdP.setBorder(b);
		contentPnl.add(pwdP, gbc);

		// dummy panel
		gbc.gridy = 3;
		JPanel pnl_2 = new JPanel();
		pnl_2.setBackground(new Color(255, 240, 245));
		contentPnl.add(pnl_2, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		loginB = new JButton("Login");
		loginB.addActionListener(this);
		contentPnl.add(loginB, gbc);

		gbc.gridx = 1;
		quitB = new JButton("Quit");
		quitB.addActionListener(this);
		contentPnl.add(quitB, gbc);
		this.add(contentPnl);
		this.addWindowListener(this);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginB) {
			DesktopUser user=new DesktopUser();
			user.setUsername(usrT.getText());
			user.setPassword(pwdP.getText());
			try{
				MasterOperations ma=new MasterAction();
				ma.desktopUserLogin(user);
				if(user.getAccountLevel().equals("CLERK")){
					new BankSystemGUIForClerk(user);					
				}
				if(user.getAccountLevel().equals("ADMIN")){
					new BankSystemGUIForAdmin(user);
				}
				if(user.getAccountLevel().equals("HR")){
					new BankSystemGUIForHR(user);
				}
				this.dispose();
			}catch(NullPointerException re){
				JOptionPane.showMessageDialog(this, WRONG_USER_PASSWORD, "Error Message", JOptionPane.ERROR_MESSAGE);
			}
		}
		if(e.getSource()== quitB){
			System.exit(0);
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		System.exit(0);
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}
}
