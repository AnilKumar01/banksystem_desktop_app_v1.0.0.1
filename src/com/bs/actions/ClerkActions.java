/* *****************************************************************************
 * Project: Bank System
 * Purpose: Implementing methods of the ClerkOperations interface.
 * Author: Anil Kumar(dac11)
 * Filename: ClerkActions.java
 * Version: 1.0
 * Start date: 18-Dec-2014
 * End date:
 * *****************************************************************************/

package com.bs.actions;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.bs.bankrelated.bean.CreditCardDetails;
import com.bs.bankrelated.bean.Customer;
import com.bs.bankrelated.bean.DebitCardDetails;
import com.bs.bankrelated.bean.DepositViewBean;
import com.bs.connection.MyConnection;
import com.bs.customerrelated.bean.AccountInfo;
import com.bs.customerrelated.bean.AccountSummary;
import com.bs.customerrelated.bean.NetBankingDetails;
import com.bs.customerrelated.bean.Nominee;
import com.bs.init.ExecuteNotepad;
import com.bs.operations.ClerkOperations;
import com.bs.operations.MasterOperations;

public class ClerkActions implements ClerkOperations{
	@Override
	public AccountSummary openNewAccount(Customer customer, Nominee nominee,
			String accountType, String branchCode) {
		AccountSummary accountSummary=new AccountSummary();
		Connection connection = null;
		try {
			connection = MyConnection.getMySQLConnection();
			//ADDING CUSTOMER DETAILS TO THE DATABASE
			addCustomerDetails(customer, connection);
			//ADDING NOMINEE DETAILS
			nominee.setCustomerId(customer.getCustomerId());
			addNomineeDetails(nominee, connection);
			//OPENING ACCOUNT
			openAccount(customer, accountType, branchCode, accountSummary,
					connection);
			//GETTING ACCOUNT SUMMARY
			getAccountSummary(accountSummary, connection);
			//CLOSING CONNECTION
			connection.close();
			return accountSummary;
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				connection.close();
			} catch (SQLException e1) {				
				e1.printStackTrace();
			}
			return null;
		}
	}

	private void getAccountSummary(AccountSummary accountSummary,
			Connection connection) throws SQLException {
		PreparedStatement preStmt=connection.prepareStatement(ACCOUNT_SUMMARY);
		preStmt.setString(1, accountSummary.getAccountNumber());
		ResultSet rs=preStmt.executeQuery();
		if(rs.next()){
			accountSummary.setAccountType(rs.getString(2));
			accountSummary.setOpenAccountDate(rs.getDate(3).toString());
			accountSummary.setBranchCode(rs.getString(4));
			accountSummary.setBranchLocation(rs.getString(5));
			accountSummary.setIFSCCode(rs.getString(6));
			accountSummary.setBranchAddress(rs.getString(7));
			accountSummary.setBranchTel(rs.getString(8));
			accountSummary.setBranchFAX(rs.getString(9));
			accountSummary.setAccountHolder(rs.getString(10));
			accountSummary.setAddress(rs.getString(11));
			accountSummary.setCustomerMobile(rs.getString(12));
		}
		rs.close();
	}

	private void openAccount(Customer customer, String accountType,
			String branchCode, AccountSummary accountSummary,
			Connection connection) throws SQLException {
		CallableStatement call=connection.prepareCall(OPEN_ACCOUNT);
		call.setInt(1, customer.getCustomerId());
		call.setString(2, accountType);
		call.setString(3, branchCode);
		call.registerOutParameter(4, Types.VARCHAR);
		call.execute();
		accountSummary.setAccountNumber(call.getString(4));
		call.close();
	}

	private void addNomineeDetails(Nominee nominee,
			Connection connection) throws SQLException {
		CallableStatement preCall=connection.prepareCall(ADD_NOMINEE_DETAILS);
		preCall.setInt(1, nominee.getCustomerId());
		preCall.setString(2, nominee.getFirstName());
		preCall.setString(3, nominee.getMiddleName());
		preCall.setString(4, nominee.getLastName());
		preCall.setString(5, nominee.getDateOfBirth());
		preCall.setString(6, nominee.getRelation());
		preCall.setString(7, nominee.getAddress());
		preCall.setString(8, nominee.getMobileNo());
		preCall.execute();
		preCall.close();
	}

	private void addCustomerDetails(Customer customer, Connection connection)
			throws SQLException {
		CallableStatement callStmt=connection.prepareCall(ADD_CUSTOMER_DETAILS);
		callStmt.setString(1, customer.getFirstName());
		callStmt.setString(2, customer.getMiddleName());
		callStmt.setString(3, customer.getLastName());
		callStmt.setString(4, customer.getGender());
		callStmt.setString(5, customer.getDateOfBirth());
		callStmt.setString(6, customer.getMartialStatus());
		callStmt.setString(7, customer.getCurrentAddress());
		callStmt.setString(8, customer.getPermananetAddress());
		callStmt.setString(9, customer.getEduQualification());
		callStmt.setString(10, customer.getOccupation());
		callStmt.setString(11, customer.getAnnulIncome());
		callStmt.setString(12, customer.getPancard());
		callStmt.setString(13, customer.getEmail());
		callStmt.setString(14, customer.getMobile());
		callStmt.setString(15, customer.getPhotoGraph());
		callStmt.setString(16, customer.getSignature());
		callStmt.registerOutParameter(17, Types.INTEGER);
		callStmt.execute();
		customer.setCustomerId(callStmt.getInt(17));
		callStmt.close();
	}

	@Override
	public boolean activateNetBanking(String netBankingSrNo,
			String accountNumber) {
		Connection conn=null;
		try {
			conn=MyConnection.getMySQLConnection();
			CallableStatement call=conn.prepareCall(ACTIVATE_NET_BANKING);
			call.registerOutParameter(3, Types.INTEGER);
			call.setString(1, netBankingSrNo);
			call.setString(2, accountNumber);
			call.execute();
			int status=call.getInt(3);
			conn.close();
			if(status==1)
				return true;
			else
				return false;	
			
		} catch (ClassNotFoundException | SQLException e) {
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deactivateNetBanking(String accountNumber){
		Connection conn=null;
		try {
			conn=MyConnection.getMySQLConnection();
			CallableStatement call=conn.prepareCall(DEACTIVATE_NET_BANKING);
			call.setString(1, accountNumber);
			call.execute();
			conn.close();
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}		
	}

	@Override
	public boolean activateDebitCard(String debitCardNo, String accountNumber) {
		Connection conn=null;
		try {
			conn=MyConnection.getMySQLConnection();
			CallableStatement call=conn.prepareCall(ACTIVATE_DEBIT_CARD);
			call.registerOutParameter(3, Types.INTEGER);
			call.setString(1, debitCardNo);
			call.setString(2, accountNumber);
			call.execute();
			int opt=call.getInt(3);
			conn.close();
			if(opt==1){
				return true;
			}
			return false;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}
		//return false;
	}

	@Override
	public boolean deactivateDebitCard(String accountNumber) {
		Connection conn=null;
		try {
			conn=MyConnection.getMySQLConnection();
			CallableStatement call=conn.prepareCall(DEACTIVATE_DEBIT_CARD);
			call.setString(1, accountNumber);
			call.execute();
			conn.close();
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}
	}

	@Override
	public boolean addCheckBookInfo(String accountNumber) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int cashDeposit(String accountNumber, double amount) {
		Connection conn=null;
		try {
			int transactionNo;
			conn=MyConnection.getMySQLConnection();
			CallableStatement call=conn.prepareCall(CASH_DEPOSIT);
			call.setString(1, accountNumber);
			call.setDouble(2, amount);
			call.setString(3, "CASHDEPOSIT");
			call.registerOutParameter(4, Types.INTEGER);
			call.execute();
			transactionNo=call.getInt(4);
			conn.close();
			return transactionNo;
		} catch (ClassNotFoundException | SQLException e) {
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
				
			}
			e.printStackTrace();
			return -1;
		}
		
	}

	@Override
	public int cashWithdraw(String accountNumber, double amount) {
		Connection conn=null;
		try {
			conn=MyConnection.getMySQLConnection();
			CallableStatement call=conn.prepareCall(CASH_WITHDRAW);
			call.setString(1, accountNumber);
			call.setDouble(2, amount);
			call.setString(3, "CASHWITHDRAW");
			call.registerOutParameter(4, Types.INTEGER);
			call.execute();
			int transNo=call.getInt(4);
			conn.close();
			return transNo;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return -1;
		}
		//return false;
	}

	@Override
	public boolean changePassword(String newPassword, String oldPassword) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean printAccountStatement(String accountNumber, String fromDate) {
		Connection conn=null;
		try {
			conn=MyConnection.getMySQLConnection();
			PreparedStatement pre=conn.prepareStatement(ACCOUNT_STATEMENT);
			pre.setString(1, accountNumber);
			pre.setString(2, fromDate);
			ResultSet rs=pre.executeQuery();
			String statements="ACCOUNT NO.:"+accountNumber+"\n\rDATE\t\tDESCRIPTION\tWITHDRAW\tDEPOSIT\t\tBALANCE\n\r";
			while(rs.next()){
				statements+=rs.getString(2)+"\t"+rs.getString(3)+"\t";
				if(rs.getDouble(5)!=0)
					statements+=rs.getDouble(5)+"\t";
				else
					statements+="          \t";
				if(rs.getDouble(4)!=0)
					statements+=rs.getDouble(4)+"\t";
				else
					statements+="          \t";
				
				statements+="\t"+rs.getDouble(6)+"\n\r";
			}
			conn.close();
			MasterOperations mo=new MasterAction();
			if(mo.printStatementsToFile(accountNumber, statements)){
				new ExecuteNotepad(accountNumber);
				return true;
			}else{
				return false;
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}
	}

	@Override
	public DepositViewBean getDepositViewData(String accountNumber) {
		DepositViewBean dvb=new DepositViewBean();
		Connection connection=null;
		try {
			connection = MyConnection.getMySQLConnection();
			PreparedStatement preStmt=connection.prepareStatement(DEPOSIT_VIEW);
			preStmt.setString(1, accountNumber);
			ResultSet rs=preStmt.executeQuery();
			if(rs.next()){
				dvb.setAccountNumber(rs.getString(1));
				dvb.setAccountType(rs.getString(2));
				dvb.setAccountBalance(rs.getDouble(3));
				dvb.setAccountHolder(rs.getString(4));
			}else{
				dvb=null;
			}
			connection.close();
			return dvb;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return null;
		}
	}

	@Override
	public NetBankingDetails getNetBankingDetails(String accountNumber) {
		Connection conn=null;
		NetBankingDetails nbd=new NetBankingDetails();
		try {
			conn=MyConnection.getMySQLConnection();
			PreparedStatement pre=conn.prepareStatement(NET_BK_DETAILS);
			pre.setString(1, accountNumber);
			ResultSet rs=pre.executeQuery();
			if(rs.next()){
				nbd.setAccountNumber(rs.getString(1));
				nbd.setAccountHolder(rs.getString(3));
				nbd.setAccountType(rs.getString(2));
				nbd.setNetBkStatus(rs.getString(4));				
			}
			conn.close();
			return nbd;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return null;
		}		
	}

	@Override
	public DebitCardDetails getDebitCardDetails(String accountNumber) {
		DebitCardDetails dcd=new DebitCardDetails();
		Connection conn=null;
		try {
			conn=MyConnection.getMySQLConnection();
			PreparedStatement pre=conn.prepareStatement(DEBIT_CARD_DETAILS);
			pre.setString(1, accountNumber);
			ResultSet rs=pre.executeQuery();
			if(rs.next()){
				dcd.setAccountNumber(rs.getString(1));
				dcd.setAccountType(rs.getString(2));
				dcd.setAccountHolder(rs.getString(3));
				dcd.setDebitCardStatus(rs.getString(4));
			}
			conn.close();
			return dcd;
		} catch (ClassNotFoundException | SQLException e) {
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public CreditCardDetails getCreditCardDetails(String accountNumber) {
		Connection conn=null;
		CreditCardDetails ccd=new CreditCardDetails();
		try {
			conn=MyConnection.getMySQLConnection();
			PreparedStatement pre=conn.prepareStatement(CREDIT_CARD_DETAILS);
			pre.setString(1, accountNumber);
			ResultSet rs=pre.executeQuery();
			if(rs.next()){
				ccd.setAccountNumber(rs.getString(1));
				ccd.setAccountType(rs.getString(2));
				ccd.setAccountHolder(rs.getString(3));
				ccd.setCreditCardStatus(rs.getString(4));
			}
			conn.close();
			return ccd;
		} catch (ClassNotFoundException | SQLException e) {
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean activateCreditCard(String creditCardNo, String accountNumber) {
		Connection conn=null;
		try {
			conn=MyConnection.getMySQLConnection();
			CallableStatement call=conn.prepareCall(ACTIVATE_CREDIT_CARD);
			call.setString(1, creditCardNo);
			call.setString(2, accountNumber);
			call.registerOutParameter(3, Types.INTEGER);
			call.execute();
			int opt=call.getInt(3);
			conn.close();
			if(opt==1)
				return true;
			else
				return false;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}
	}

	@Override
	public boolean deactivateCreditCard(String accountNumber) {
		Connection conn=null;
		try {
			conn=MyConnection.getMySQLConnection();
			CallableStatement call=conn.prepareCall(DEACTIVATE_CREDIT_CARD);
			call.setString(1, accountNumber);
			call.execute();
			conn.close();
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}
	}

	@Override
	public AccountInfo getAccountInfo(String accountNumber) {
		Connection conn=null;
		AccountInfo ai=new AccountInfo();
		try {
			conn=MyConnection.getMySQLConnection();
			PreparedStatement pre=conn.prepareStatement(ACCOUNT_DETAIL);
			pre.setString(1, accountNumber);
			ResultSet rs=pre.executeQuery();
			if(rs.next()){
				ai.setAccounNumber(rs.getString(1));
				ai.setAccountType(rs.getString(2));
				ai.setNetBankingStatus(rs.getString(3));
				ai.setDebitCardStatus(rs.getString(4));
				ai.setCreditCard(rs.getString(5));
				ai.setAccountHolder(rs.getString(6));
				ai.setMobileNumber(rs.getString(7));
				ai.setCustomerAddress(rs.getString(8));
				ai.setBranchCode(rs.getString(9));
				ai.setIfscCode(rs.getString(10));
				ai.setBranchLocation(rs.getString(11));
				ai.setBranchAddress(rs.getString(12));
				ai.setBranchTel(rs.getString(13));
				ai.setBranchFax(rs.getString(14));
			}
			conn.close();
			return ai;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				conn.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return null;
		}
	}
}
