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
public class ChangeUserNamePassword extends JInternalFrame implements
		ActionListener {
	JPanel userPwdPanel;
	JLabel label;
	JTextField newUserName;
	JPasswordField currentPwd, newPwd, confirmNewPwd;
	JButton chagePwd;
	String usr, pwd;

	public ChangeUserNamePassword(String userName) {
		this.setLayout(new FlowLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.closable = true;
		this.title = "Change UserName & Password";
		this.usr = userName;
		userPwdPanel = new JPanel();
		userPwdPanel.setLayout(new GridLayout(5, 1));
		label = new JLabel("Current Password:");
		userPwdPanel.add(label);
		currentPwd = new JPasswordField(16);
		userPwdPanel.add(currentPwd);
		label = new JLabel("New UserName");
		userPwdPanel.add(label);
		newUserName = new JTextField(16);
		userPwdPanel.add(newUserName);
		label = new JLabel("New Password:");
		userPwdPanel.add(label);
		newPwd = new JPasswordField(16);
		userPwdPanel.add(newPwd);
		label = new JLabel("Confirm Password:");
		userPwdPanel.add(label);
		confirmNewPwd = new JPasswordField(16);
		userPwdPanel.add(confirmNewPwd);
		chagePwd = new JButton("Change UserName & Password");
		chagePwd.addActionListener(this);
		userPwdPanel.add(chagePwd);
		this.add(userPwdPanel);
		this.setVisible(true);

	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == chagePwd) {
			DesktopUser user = new DesktopUser();
			user.setUsername(usr);
			DesktopLogin desktop = new DesktopLogin();
			desktop.desktopUserLogin(user);
			if (newPwd.getText().equals(confirmNewPwd.getText())) {
				if (currentPwd.getText().equals(user.getPassword())) {
					if (UpdateEmployeeAccount.changeUserNamePassword(user,
							newUserName.getText(), newPwd.getText())) {
					JOptionPane
								.showMessageDialog(
										this,
										"UserName & Password Changed, will be in use for next Login...",
										"Changed", JOptionPane.OK_OPTION);

					}
					this.dispose();

				} else {
					JOptionPane.showMessageDialog(this,
							"You have entered Wrong Password", "Error",
							JOptionPane.OK_OPTION);
				}
			} else {
				JOptionPane.showMessageDialog(this,
						"Confirm Password and Password Should be Same",
						"Error", JOptionPane.OK_OPTION);
			}
		}
	}
}
