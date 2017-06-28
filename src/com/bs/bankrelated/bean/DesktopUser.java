/* *****************************************************************************
 * Project: Bank System
 * Purpose: Creating a POJO class for desktop application users.
 * Author: Anil Kumar(dac11)
 * Filename: DesktopUsers.java
 * Version: 1.0
 * Start date: 20-Dec-2014
 * End date: 21-Dec-2014 
 * *****************************************************************************/

package com.bs.bankrelated.bean;

public class DesktopUser {
	private String username;
	private String password;
	private String accountLevel;
	private String accountStatus;
	
	public String getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(String accountStatus) {
		this.accountStatus = accountStatus;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAccountLevel() {
		return accountLevel;
	}
	public void setAccountLevel(String accountLevel) {
		this.accountLevel = accountLevel;
	} 
}
