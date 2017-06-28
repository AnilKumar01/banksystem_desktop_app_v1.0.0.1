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
public class ChangeUserName extends JInternalFrame implements ActionListener {

	JPanel usrNamePanel;
	JLabel label;
	JTextField currentUN, newUN;
	JPasswordField pwdField;
	JButton changeUN;
	String usr, pwd;

	public ChangeUserName(String userName, String password) {
		this.setLayout(new FlowLayout());
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
		this.closable = true;
		this.title = "Change UserName [ Fill all details ]";
		this.usr = userName;
		this.pwd = password;
		usrNamePanel = new JPanel();
		usrNamePanel.setLayout(new GridLayout(4, 1));
		label = new JLabel("Current UserName:");
		usrNamePanel.add(label);
		currentUN = new JTextField(16);
		usrNamePanel.add(currentUN);
		label = new JLabel("New UserName:");
		usrNamePanel.add(label);
		newUN = new JTextField(16);
		usrNamePanel.add(newUN);
		label = new JLabel("Password:");
		usrNamePanel.add(label);
		pwdField = new JPasswordField(16);
		usrNamePanel.add(pwdField);
		changeUN = new JButton("Change UserName");
		changeUN.addActionListener(this);
		usrNamePanel.add(changeUN);
		this.add(usrNamePanel);
		this.setVisible(true);

	}

	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == changeUN) {
			if (currentUN.getText().equals(usr)) {
				DesktopUser user = new DesktopUser();
				user.setUsername(usr);
				DesktopLogin desktop = new DesktopLogin();
				desktop.desktopUserLogin(user);
				if (pwdField.getText().equals(user.getPassword())) {

					if (UpdateEmployeeAccount.changeUserName(user,
							newUN.getText())) {
						JOptionPane
								.showMessageDialog(
										this,
										"UserName Changed, will be in use for next Login...",
										"Changed", JOptionPane.OK_OPTION);
						this.dispose();
					} else {
						JOptionPane.showMessageDialog(this,
								"UserName already Exists", "Changed",
								JOptionPane.ERROR_MESSAGE);

					}
				} else {
					JOptionPane.showMessageDialog(this,
							"You Have entered incorrect details !",
							"Error Message", JOptionPane.ERROR_MESSAGE);
				}
			} else {
				JOptionPane
						.showMessageDialog(
								this,
								"You Have entered incorrect details !",
								"Error Message", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
}
