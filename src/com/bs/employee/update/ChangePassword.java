package com.bs.employee.update;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.bs.bankrelated.bean.DesktopUser;
import com.bs.operations.DesktopLogin;

@SuppressWarnings("serial")
public class ChangePassword extends JInternalFrame implements ActionListener {

	JPanel pwdPanel;
	JLabel label;
	JTextField empId, empSalary;
	JPasswordField currentPwd, newPwd, confirmNewPwd;
	JButton chagePwd;
	String usr, pwd;

	public ChangePassword(String userName, String password) {
		this.setLayout(new FlowLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.closable = true;
		this.title = "Change Password [ Fill all details ]";
		this.usr = userName;
		this.pwd = password;
		pwdPanel = new JPanel();
		pwdPanel.setLayout(new GridLayout(4, 1));
		label = new JLabel("Current Password:");
		pwdPanel.add(label);
		currentPwd = new JPasswordField(16);
		pwdPanel.add(currentPwd);
		label = new JLabel("New Password:");
		pwdPanel.add(label);
		newPwd = new JPasswordField(16);
		pwdPanel.add(newPwd);
		label = new JLabel("Confirm Password:");
		pwdPanel.add(label);
		confirmNewPwd = new JPasswordField(16);
		pwdPanel.add(confirmNewPwd);
		chagePwd = new JButton("Change Password");
		chagePwd.addActionListener(this);
		pwdPanel.add(chagePwd);
		this.add(pwdPanel);
		this.setVisible(true);

	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == chagePwd) {
			if (newPwd.getText().equals(confirmNewPwd.getText())) {
				DesktopUser user = new DesktopUser();
				user.setUsername(usr);
				DesktopLogin desktop = new DesktopLogin();
				desktop.desktopUserLogin(user);
				if (currentPwd.getText().equals(user.getPassword())) {
					if (UpdateEmployeeAccount.changePassword(user,newPwd.getText())) {
						JOptionPane.showMessageDialog(this, "Password Changed",
								"Changed", JOptionPane.OK_OPTION);
					}
					this.dispose();
				} else {
					JOptionPane.showMessageDialog(this,
							"You Have entered wrong Current Password",
							"Error Message", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane.showMessageDialog(this,
						"New password & confirm Password should be same",
						"Error Message", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
