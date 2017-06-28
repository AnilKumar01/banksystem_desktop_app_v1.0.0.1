/* *************************************************************************
 * Project: Bank System
 * Purpose: To make an account opening form.
 * Start date: Nov 28, 2014
 * Authors: Anil Kumar (DAC-11) 			
 * End Date:  
 * ***************************************************************************/

package com.bs.forms;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import com.bs.bankrelated.bean.DesktopUser;
import com.bs.forms.internal.AccountDetail;
import com.bs.forms.internal.AccountOpeningForm;
import com.bs.forms.internal.ActivateCreditCard;
import com.bs.forms.internal.ActivateDebitCard;
import com.bs.forms.internal.ActivateNetBanking;
import com.bs.forms.internal.CashDepositForm;
import com.bs.forms.internal.CashWithdrawForm;
import com.bs.forms.internal.ChangePassword;
import com.bs.forms.internal.DeactivateCreditCard;
import com.bs.forms.internal.DeactivateDebitCard;
import com.bs.forms.internal.DeactivateNetBanking;
import com.bs.forms.internal.PrintAccountStatement;
import com.bs.init.ExecuteNotepad;


@SuppressWarnings("serial")
public class BankSystemGUIForClerk extends JFrame implements ActionListener, WindowListener{
	private JPanel contentP;
	private JMenuBar menuBar;
	private JMenu transactionM, updateAccountServices, accountM, helpM, settingM;
	private JMenuItem de_activateNetBank,de_activateCreditCard, de_activateDebitCard/*, checkBook*/;
	private JMenuItem activateNetBank,activateCreditCard, activateDebitCard;
	private JMenuItem cashWithdraw, cashDeposit;
	private JMenuItem changePassword, printAccountStatement, logOut;
	private JMenuItem openNewAccount,  viewAccountDetail;
	private JPanel internalFrameHolder, dummyPanel;
	JMenuItem aboutItem;
	DesktopUser user;
	
	public BankSystemGUIForClerk(DesktopUser user) {
		super("BANK SYSTEM [GENERAL LOGIN]");
		
		this.user=user;
		
		this.setSize(1024, 740);
		
		this.setLayout(new BorderLayout());
		this.clerkGUI();
		internalFrameHolder=new JPanel();
		internalFrameHolder.setBackground(new Color(250, 235, 215));
		this.add(internalFrameHolder, BorderLayout.CENTER);
		dummyPanel=new JPanel();
		dummyPanel.setBackground(new Color(250, 235, 215));
		this.add(dummyPanel, BorderLayout.SOUTH);
		
		this.setResizable(false);
		this.addWindowListener(this);
		this.setVisible(true);
	}
	
	private void clerkGUI(){
		// ACCOUNT RELATED ACTIVITIES
		openNewAccount=new JMenuItem("Open New Account");
			openNewAccount.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
			openNewAccount.addActionListener(this);
		updateAccountServices=new JMenu("Update Account Services");
		
			activateNetBank=new JMenuItem("Activate Net Banking");
			activateNetBank.addActionListener(this);
			activateNetBank.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, InputEvent.CTRL_DOWN_MASK));
			de_activateNetBank=new JMenuItem("Deactivate Net Banking");
			de_activateNetBank.addActionListener(this);
			de_activateNetBank.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, InputEvent.CTRL_DOWN_MASK));
			
			activateDebitCard=new JMenuItem("Activate Debit Card");
			activateDebitCard.addActionListener(this);
			activateDebitCard.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
			de_activateDebitCard=new JMenuItem("Deactivate Debit Card");
			de_activateDebitCard.addActionListener(this);
			de_activateDebitCard.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, InputEvent.CTRL_DOWN_MASK));
				
			activateCreditCard=new JMenuItem("Activate Credit Card");
			activateCreditCard.addActionListener(this);
			activateCreditCard.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK));
			de_activateCreditCard=new JMenuItem("Deactivate Credit Card");
			de_activateCreditCard.addActionListener(this);
			de_activateCreditCard.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK));
				
//			checkBook=new JMenuItem("Add Check Book Detail");
//			checkBook.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, InputEvent.CTRL_DOWN_MASK));
			
			updateAccountServices.add(activateNetBank);
			updateAccountServices.add(de_activateNetBank);
			
			updateAccountServices.add(activateDebitCard);
			updateAccountServices.add(de_activateDebitCard);
			
			updateAccountServices.add(activateCreditCard);
			updateAccountServices.add(de_activateCreditCard);
			
//			updateAccountServices.add(checkBook);
						
		viewAccountDetail=new JMenuItem("View Account Detail");
		viewAccountDetail.addActionListener(this);
			viewAccountDetail.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_J, InputEvent.CTRL_DOWN_MASK));
		
		accountM=new JMenu("Account");
			accountM.setMnemonic(KeyEvent.VK_A);
			accountM.add(openNewAccount);
			accountM.add(updateAccountServices);
			accountM.add(viewAccountDetail);
		
		// TRANSACTION RELATED ACTIVITIES
			cashDeposit=new JMenuItem("Cash Deposit");
				cashDeposit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, InputEvent.CTRL_DOWN_MASK));
				cashDeposit.addActionListener(this);
			cashWithdraw=new JMenuItem("Cash Withdraw");
				cashWithdraw.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, InputEvent.CTRL_DOWN_MASK));
				cashWithdraw.addActionListener(this);
			printAccountStatement=new JMenuItem("Print Account Statement");
			printAccountStatement.addActionListener(this);
				printAccountStatement.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, InputEvent.CTRL_DOWN_MASK));
					
		transactionM=new JMenu("Transaction");
			transactionM.setMnemonic(KeyEvent.VK_T);
			transactionM.add(cashDeposit);
			transactionM.add(cashWithdraw);
			transactionM.add(printAccountStatement);

		// SETTING MENU
			changePassword =new JMenuItem("Change Password");
			changePassword.addActionListener(this);
			changePassword.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK));
			logOut=new JMenuItem("LogOut");
			logOut.addActionListener(this);
			logOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U, InputEvent.CTRL_DOWN_MASK));
		//settingM=new JMenu("Settings");
			//settingM.setMnemonic(KeyEvent.VK_S);
			settingM=new JMenu(user.getUsername().toUpperCase());
			settingM.add(changePassword);
			settingM.add(logOut);
			
			
			
		// HELP MENU
		helpM=new JMenu("Help");
			helpM.setMnemonic(KeyEvent.VK_H);
			aboutItem=new JMenuItem("About Bank System");
			aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK));
			aboutItem.addActionListener(this);
			helpM.add(aboutItem);
			
			
		// MENU BAR
		menuBar =new JMenuBar();
		menuBar.setBackground(Color.WHITE);
			menuBar.add(accountM);
			menuBar.add(transactionM);
			menuBar.add(settingM);			
			menuBar.add(helpM);
			
		contentP =new JPanel();
		this.add(menuBar, BorderLayout.NORTH);
		this.add(contentP, BorderLayout.SOUTH);		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==openNewAccount){
			internalFrameHolder.add(new AccountOpeningForm());
		}
		if(e.getSource()==cashDeposit){
			internalFrameHolder.add(new CashDepositForm());
		}
		if(e.getSource()==cashWithdraw){
			internalFrameHolder.add(new CashWithdrawForm());
		}
		if(e.getSource()==activateNetBank){
			internalFrameHolder.add(new ActivateNetBanking());
		}
		if(e.getSource()==de_activateNetBank){
			internalFrameHolder.add(new DeactivateNetBanking());
		}
		if(e.getSource()==activateDebitCard){
			internalFrameHolder.add(new ActivateDebitCard());
		}
		if(e.getSource()==de_activateDebitCard){
			internalFrameHolder.add(new DeactivateDebitCard());
		}
		if(e.getSource()==activateCreditCard){
			internalFrameHolder.add(new ActivateCreditCard());
		}
		if(e.getSource()==de_activateCreditCard){
			internalFrameHolder.add(new DeactivateCreditCard());
		}
		if(e.getSource()==viewAccountDetail){
			internalFrameHolder.add(new AccountDetail());
		}
		if(e.getSource()==printAccountStatement){
			internalFrameHolder.add(new PrintAccountStatement());
		}
		if(e.getSource()==logOut){
			this.dispose();
			new LoginForm().setVisible(true);
		}
		if(e.getSource()==changePassword){
			internalFrameHolder.add(new ChangePassword(user));
		}
		if(e.getSource()==aboutItem){
			new ExecuteNotepad("BANK_SYSTEM_HELP");
		}
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
		//System.out.println("CLOSING EVENT");
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
