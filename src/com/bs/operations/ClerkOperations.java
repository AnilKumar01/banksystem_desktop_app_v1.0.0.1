/* *****************************************************************************
 * Project: Bank System
 * Purpose: To define an interface for bank clerk to do their works.
 * Author: Anil Kumar(dac11)
 * Filename: ClerkOperation.java
 * Version: 1.0
 * Start date: 17-Dec-2014
 * End date: 
 * *****************************************************************************/

package com.bs.operations;

import com.bs.bankrelated.bean.CreditCardDetails;
import com.bs.bankrelated.bean.Customer;
import com.bs.bankrelated.bean.DebitCardDetails;
import com.bs.bankrelated.bean.DepositViewBean;
import com.bs.customerrelated.bean.AccountInfo;
import com.bs.customerrelated.bean.AccountSummary;
import com.bs.customerrelated.bean.NetBankingDetails;
import com.bs.customerrelated.bean.Nominee;

public interface ClerkOperations {
	//CONSTANT SQL STRINGS
	final String ADD_CUSTOMER_DETAILS="{CALL ADD_CUSTOMER_DETAILS(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
	final String ADD_NOMINEE_DETAILS="{CALL ADD_NOMINEE_DETAILS(?, ?, ?, ?, ?, ?, ?, ?)}";
	final String OPEN_ACCOUNT="{CALL OPEN_ACCOUNT(?, ?, ?, ?)}";
	final String ACCOUNT_SUMMARY="SELECT * FROM ACCOUNT_SUMMARY WHERE ACCOUNT_NO=?";
	final String DEPOSIT_VIEW="SELECT * FROM DEPOSIT_VIEW WHERE ACCOUNT_NO=?";
	final String CASH_DEPOSIT="{CALL CASH_DEPOSIT(?, ?, ?, ?)}";
	final String CASH_WITHDRAW="{CALL WITHDRAW_AMOUNT(?, ?, ?, ?)}";
	final String NET_BK_DETAILS="SELECT * FROM NET_BK_DETAILS WHERE ACCOUNT_NO=?";
	final String DEBIT_CARD_DETAILS="SELECT * FROM DEBIT_CARD_DETAILS WHERE ACCOUNT_NO=?";
	final String CREDIT_CARD_DETAILS="SELECT * FROM CREDIT_CARD_DETAILS WHERE ACCOUNT_NO=?";
	final String ACTIVATE_NET_BANKING="{CALL ACTIVATE_NET_BANKING(?, ?, ?)}";
	final String DEACTIVATE_NET_BANKING="{CALL DEACTIVATE_NET_BANKING(?)}";
	final String ACTIVATE_DEBIT_CARD="{CALL ACTIVATE_DEBIT_CARD(?, ?, ?)}";
	final String DEACTIVATE_DEBIT_CARD="{CALL DEACTIVATE_DEBIT_CARD(?)}";
	final String ACTIVATE_CREDIT_CARD="{CALL ACTIVATE_CREDIT_CARD(?, ?, ?)}";
	final String DEACTIVATE_CREDIT_CARD="{CALL DEACTIVATE_CREDIT_CARD(?)}";
	final String ACCOUNT_DETAIL="SELECT * FROM ACCOUNT_DETAIL WHERE ACCOUNT_NO=?";
	final String ACCOUNT_STATEMENT="SELECT * FROM ACCOUNT_STATEMENTS WHERE ACCOUNT_NO=? AND TRANSACTION_DATE>=?";
	
	AccountSummary openNewAccount(Customer customer, Nominee nominee, String accountType, String branchCode);
	AccountInfo getAccountInfo(String accountNumber);
	boolean activateNetBanking(String netBankingSrNo, String accountNumber);
	boolean deactivateNetBanking(String accountNumber);
	boolean activateDebitCard(String debitCardNo, String accountNumber);
	boolean activateCreditCard(String creditCardNo, String accountNumber);
	boolean deactivateDebitCard(String accountNumber);
	boolean deactivateCreditCard(String accountNumber);
	boolean addCheckBookInfo(String accountNumber);
	DepositViewBean getDepositViewData(String accountNumber);
	int cashDeposit(String accountNumber, double amount);
	int cashWithdraw(String accountNumber, double amount);
	boolean changePassword(String newPassword, String oldPassword);
	boolean printAccountStatement(String accountNumber, String fromDate);
	NetBankingDetails getNetBankingDetails(String accountNumber);
	DebitCardDetails getDebitCardDetails(String accountNumber);
	CreditCardDetails getCreditCardDetails(String accountNumber);
}
