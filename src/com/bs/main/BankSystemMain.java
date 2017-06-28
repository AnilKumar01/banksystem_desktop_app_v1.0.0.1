/* *****************************************************************************
 * Project: Bank System
 * Purpose: Starting point of the application.
 * Author: Anil Kumar(dac11)
 * Filename: BankSystemMain.java
 * Version: 1.0
 * Start date: 19-Dec-2014
 * End date: 
 * *****************************************************************************/

package com.bs.main;

import java.awt.EventQueue;

import com.bs.forms.LoginForm;

public class BankSystemMain {

	public static void main(String[] args) {
		Runnable runner=new Runnable() {
			
			@Override
			public void run() {
				LoginForm login=new LoginForm();
				login.setVisible(true);
				
			}
		};
		EventQueue.invokeLater(runner);
	}

}
