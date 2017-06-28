package com.bs.forms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

import com.bs.bankrelated.bean.DesktopUser;
import com.bs.forms.internal.AddDesktopUser;
import com.bs.forms.internal.AddNewAccountNumber;
import com.bs.forms.internal.AddNewBranchDetails;
import com.bs.forms.internal.AddNewDesignation;
import com.bs.forms.internal.AddNewTranactionType;
import com.bs.forms.internal.ChangePassword;
import com.bs.forms.internal.DeleteDesktopUser;
import com.bs.forms.internal.ModifyDesktopUser;
import com.bs.forms.internal.Update_Branch_Details;
import com.bs.forms.internal.ViewBranchDetails;
import com.bs.forms.internal.ViewUser;
import com.bs.init.ExecuteNotepad;

@SuppressWarnings("serial")
public class BankSystemGUIForAdmin extends JFrame implements ActionListener{
	/* ****************************************************************************/
	JMenuBar menuBar;
	JMenu userMenu;
	JMenuItem addNewUserAccount, deleteUserAccount,	unlockUserAccount, lockUserAccount,
				viewUser, listAllUser;
	JMenu bankMenu;
	JMenuItem addNewAccountNo, addNewBranch, viewBranchDetails, listAllBranch,
		updateBranch, addDesignation, listAllDesignation, addNewTransType, listAllTransType; 
	JMenu settingMenu;
	JMenuItem logOut, changePassword;
	JMenu helpMenu;
	JMenuItem aboutItem;
	JPanel internalFrameHolder, dummyPanel;
	
	private DesktopUser user; 
	/* ****************************************************************************/
	
	public BankSystemGUIForAdmin(DesktopUser user){
		super("BANK SYSTEM [ADMINSTRATIVE LOGIN]");
		
		this.user=user;
		
		this.setSize(1024, 740);
		this.setLayout(new BorderLayout());
				
		menuBar=new JMenuBar();
		menuBar.setBackground(Color.WHITE);
		this.add(menuBar, BorderLayout.NORTH);
		/* ************************************************************************/
		userMenuItems();
		/* *************************************************************************/
		bankInternalMenuItems();
		/* *************************************************************************/
		settingMenuItems();
		/* *************************************************************************/
		//HELP MENU
		helpMenu=new JMenu("Help");
		helpMenu.setMnemonic(KeyEvent.VK_H);
		aboutItem=new JMenuItem("About Bank System");
		aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
		aboutItem.addActionListener(this);
		helpMenu.add(aboutItem);
		menuBar.add(helpMenu);
		//HELP MENU ITEMS		
		/* *************************************************************************/
		internalFrameHolder=new JPanel();
		internalFrameHolder.setBackground(new Color(250, 235, 215));
		this.add(internalFrameHolder, BorderLayout.CENTER);
		dummyPanel=new JPanel();
		dummyPanel.setBackground(new Color(250, 235, 215));
		this.add(dummyPanel, BorderLayout.SOUTH);
		/* *************************************************************************/
		this.setResizable(false);		
		this.setVisible(true);
	}
	private void settingMenuItems() {
		//SETTING MENU
		settingMenu=new JMenu(user.getUsername().toUpperCase());
		settingMenu.setMnemonic(KeyEvent.VK_S);
		menuBar.add(settingMenu);
		//SETTING MENU ITEMS
		
		changePassword=new JMenuItem("Change Password");
		changePassword.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
		changePassword.addActionListener(this);
		settingMenu.add(changePassword);
		
		logOut=new JMenuItem("LogOut");
		logOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_DOWN_MASK));
		logOut.addActionListener(this);
		settingMenu.add(logOut);
		
	}
	private void bankInternalMenuItems() {
		//BANK MENU
		bankMenu=new JMenu("Bank-Internals");
		bankMenu.setMnemonic(KeyEvent.VK_B);
		menuBar.add(bankMenu);
		
		//BANK-INTERNAL MENU ITEMS
		addNewAccountNo=new JMenuItem("Add New Account Number");
		addNewAccountNo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK));
		addNewAccountNo.addActionListener(this);
		bankMenu.add(addNewAccountNo);
		
		bankMenu.add(new JSeparator());
		
		addNewBranch=new JMenuItem("Add New Branch Details");
		addNewBranch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK));
		addNewBranch.addActionListener(this);
		bankMenu.add(addNewBranch);
		
		
		viewBranchDetails=new JMenuItem("View Branch Details");
		viewBranchDetails.addActionListener(this);
		viewBranchDetails.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, InputEvent.CTRL_DOWN_MASK));
		bankMenu.add(viewBranchDetails);
		
		
		updateBranch=new JMenuItem("Update Branch Details");
		updateBranch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.CTRL_DOWN_MASK));
		updateBranch.addActionListener(this);
		bankMenu.add(updateBranch);
		
//		listAllBranch=new JMenuItem("List All Branch");
//		listAllBranch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK));
//		bankMenu.add(listAllBranch);
		
		bankMenu.add(new JSeparator());
		
		addDesignation=new JMenuItem("Add New Designation");
		addDesignation.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK));
		addDesignation.addActionListener(this);
		bankMenu.add(addDesignation);
		
//		listAllDesignation=new JMenuItem("List All Designation");
//		listAllDesignation.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
//		bankMenu.add(listAllDesignation);
		
		bankMenu.add(new JSeparator());
		
		addNewTransType=new JMenuItem("Add New Transaction Type");
		addNewTransType.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
		addNewTransType.addActionListener(this);
		bankMenu.add(addNewTransType);
		
//		listAllTransType=new JMenuItem("List All Transaction Types");
//		listAllTransType.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK));
//		bankMenu.add(listAllTransType);
	}
	private void userMenuItems() {
		//USER MENU
		userMenu=new JMenu("Users");
		userMenu.setMnemonic(KeyEvent.VK_U);
		menuBar.add(userMenu);
		
		//USER MENU ITEMS
		addNewUserAccount=new JMenuItem("Add New User Account");
		addNewUserAccount.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
		addNewUserAccount.addActionListener(this);
		userMenu.add(addNewUserAccount);
		
		deleteUserAccount=new JMenuItem("Delete User Account");
		deleteUserAccount.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_DOWN_MASK));
		deleteUserAccount.addActionListener(this);
		userMenu.add(deleteUserAccount);
		userMenu.add(new JSeparator());
		
		unlockUserAccount=new JMenuItem("Lock/Unlock User Account");
		unlockUserAccount.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK));
		unlockUserAccount.addActionListener(this);
		userMenu.add(unlockUserAccount);
		
//		lockUserAccount=new JMenuItem("Lock User Account");
//		lockUserAccount.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
//		userMenu.add(lockUserAccount);
		
		userMenu.add(new JSeparator());
		
		viewUser=new JMenuItem("View User");
		viewUser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK));
		viewUser.addActionListener(this);
		userMenu.add(viewUser);
//		listAllUser=new JMenuItem("List All Users");
//		listAllUser.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK));
//		userMenu.add(listAllUser);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==addNewUserAccount){
			internalFrameHolder.add(new AddDesktopUser());
		}
		if(e.getSource()==addNewAccountNo){
			internalFrameHolder.add(new AddNewAccountNumber());
		}
		if(e.getSource()==addNewBranch){
			internalFrameHolder.add(new AddNewBranchDetails());
		}
		if(e.getSource()==addNewTransType){
			internalFrameHolder.add(new AddNewTranactionType());
		}
		if(e.getSource()==viewUser){
			internalFrameHolder.add(new ViewUser());
		}
		if(e.getSource()==deleteUserAccount){
			internalFrameHolder.add(new DeleteDesktopUser());
		}
		if(e.getSource()==unlockUserAccount){
			internalFrameHolder.add(new ModifyDesktopUser());
		}
		if(e.getSource()==viewBranchDetails){
			internalFrameHolder.add(new ViewBranchDetails());
		}
		if(e.getSource()==updateBranch){
			internalFrameHolder.add(new Update_Branch_Details());
		}
		if(e.getSource()==addDesignation){
			internalFrameHolder.add(new AddNewDesignation());
		}
		if(e.getSource()==changePassword){
			internalFrameHolder.add(new ChangePassword(this.user));
		}
		if(e.getSource()==logOut){
			this.dispose();
			new LoginForm().setVisible(true);
		}
		if(e.getSource()==aboutItem){
			new ExecuteNotepad("BANK_SYSTEM_HELP");
		}
	}
}
