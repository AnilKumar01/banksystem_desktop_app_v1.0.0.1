/* *****************************************************************************
 * Project: Bank System
 * Purpose: To define an interface for bank administrator
 * Author: Anil Kumar(dac11)
 * Filename: AdminOperation.java
 * Version: 1.0
 * Start date: 21-Dec-2014
 * End date: 
 * *****************************************************************************/

package com.bs.operations;

import java.util.List;

import com.bs.bankrelated.bean.BranchList;
import com.bs.bankrelated.bean.Designation;
import com.bs.bankrelated.bean.DesktopUser;
import com.bs.bankrelated.bean.TransactionType;
import com.bs.customerrelated.bean.Account;

public interface AdminOperations {
	//CONSTANT SQL STRINGS FOR USER OPERATIONS
	final String ADD_DESKTO_USERS="{CALL ADD_DESKTOP_USER(?, ?, ?, ?)}";
	final String DEL_DESKTOP_USERS="DELETE FROM DESKTOP_APP_USERS WHERE USERNAME=?";
	final String MODIFY_DESKTOP_USERS="{CALL MODIFY_DESKTOP_USERS(?,?)}";
	final String VIEW_DESKTOP_USER="SELECT * FROM DESKTOP_USERS WHERE USERNAME=?";
	final String LIST_ALL_DESKTOP_USERS="SELECT * FROM DESKTOP_USERS";
	final String BRANCH_LIST="SELECT * FROM BRANCH_LIST";
	//CONSTANT SQL STRINGS FOR BANK_INTERNAL OPERATIONS
	
	//USER OPERATIONS
	boolean addNewUserAccount(DesktopUser user);
	boolean deleteUserAccount(String username);
	boolean modifyUserAccount(String username,String status);
	DesktopUser viewUser(String username);
	List<DesktopUser>listAllUser();
	
	
	
	final String ADD_ACCOUNT_NUMBER="{CALL ADD_ACCOUNT_NUMBER(?, ?, ?, ?, ?, ?)}";
	final String ADD_NEW_BRANCH="{CALL ADD_BRANCH(?, ?, ?, ?, ?, ?)}";
	final String ADD_TRANS_TYPE="{CALL ADD_TRANS_TYPE(?, ?)}";
	final String VIEW_BRANCH_DETAILS="SELECT * FROM BRANCH_LIST  WHERE BRANCH_CODE=?";
	final String UPDATE_BRANCH_DETAILS="{CALL UPDATE_BRANCH_DETAILS(?,?,?,?,?,?)}";
	final String ADD_DESIGNATION="{CALL ADD_DESIGNATION(?,?)}";
	//BANK_INTERNAL OPERATIONS
	boolean addNewAccountNumber(Account account);
	boolean addNewBranchDetails(BranchList branchList);
	BranchList viewBranchDetails(String branchCode);
	boolean updateBranchDetails(BranchList branchList);
	List<BranchList>listAllBranch();
	boolean addNewDesignation(Designation desig);
	List<String>listAllDesignation();
	boolean addNewTransactionType(TransactionType transactionType);
	
	
	//Settings//
	
	final String CHANGE_PASSWORD="{CALL CHANGE_PASSWORD(?,?)}";
	boolean changePassword(DesktopUser user);
}
