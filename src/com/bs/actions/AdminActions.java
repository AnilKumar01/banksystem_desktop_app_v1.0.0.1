/* *****************************************************************************
 * Project: Bank System
 * Purpose: Implementing methods of the AdminOperations interface.
 * Author: Anil Kumar(dac11)
 * Filename: AdminActions.java
 * Version: 1.0
 * Start date: 21-Dec-2014
 * End date:
 * *****************************************************************************/

package com.bs.actions;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import com.bs.bankrelated.bean.BranchList;
import com.bs.bankrelated.bean.Designation;
import com.bs.bankrelated.bean.DesktopUser;
import com.bs.bankrelated.bean.TransactionType;
import com.bs.connection.MyConnection;
import com.bs.customerrelated.bean.Account;
import com.bs.operations.AdminOperations;

public class AdminActions implements AdminOperations{

	@Override
	public boolean addNewUserAccount(DesktopUser user) {
		Connection connection = null;
		try {
			connection=MyConnection.getMySQLConnection();
			PreparedStatement preStmt=connection.prepareCall(ADD_DESKTO_USERS);
			preStmt.setString(1, user.getUsername());
			preStmt.setString(2, user.getPassword());
			preStmt.setString(3, user.getAccountLevel());
			preStmt.setString(4, user.getAccountStatus());
			preStmt.execute();
			connection.close();
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}
		
	}

	@Override
	public boolean deleteUserAccount(String username) {
		Connection connection = null;
		try{
			connection=MyConnection.getMySQLConnection();
			PreparedStatement preStmt=connection.prepareStatement(DEL_DESKTOP_USERS);
			preStmt.setString(1, username);
			preStmt.execute();
			connection.close();
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}
		
	}
	@Override
	public boolean modifyUserAccount(String username,String status) {
		Connection connection = null;
		try{
			connection=MyConnection.getMySQLConnection();
			CallableStatement callStmt=connection.prepareCall(MODIFY_DESKTOP_USERS);
			callStmt.setString(1, username);
			callStmt.setString(2, status);
			callStmt.execute();
			connection.close();
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		return false;
		}
	}

	@Override
	public DesktopUser viewUser(String username) {
		Connection connection = null;
		DesktopUser du=new DesktopUser();
		try {
			connection=MyConnection.getMySQLConnection();
			PreparedStatement preStmt=connection.prepareCall(VIEW_DESKTOP_USER);
			preStmt.setString(1, username);
			ResultSet rset=preStmt.executeQuery();
			if(rset.next()){
				du.setUsername(rset.getString(1));
				du.setPassword(rset.getString(2));
				du.setAccountLevel(rset.getString(3));
				du.setAccountStatus(rset.getString(4));
			}
			connection.close();
			return du;
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
	public List<DesktopUser> listAllUser() {
		List<DesktopUser>temp=new LinkedList<DesktopUser>();
		DesktopUser user=new DesktopUser();
		Connection conn=null;
		try {
			conn=MyConnection.getMySQLConnection();
			PreparedStatement pre=conn.prepareStatement(LIST_ALL_DESKTOP_USERS);
			ResultSet rs=pre.executeQuery();
			while(rs.next()){
				user.setUsername(rs.getString(1));
				temp.add(user);
			}
			conn.close();
			return temp;
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
	public boolean addNewAccountNumber(Account account) {
		Connection connection = null;
		try {
			connection = MyConnection.getMySQLConnection();
			PreparedStatement preStmt=connection.prepareCall(ADD_ACCOUNT_NUMBER);
			preStmt.setString(1, account.getAccountNo());
			preStmt.setString(2, account.getAccountType());
			preStmt.setString(3, account.getNetBanking());
			preStmt.setString(4, account.getDebitCard());
			preStmt.setString(5, account.getCreditCard());
			preStmt.setString(6, account.getCheckBook());
			preStmt.execute();
			connection.close();
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				connection.close();
			} catch (SQLException e1) {				
				e1.printStackTrace();
			}
			return false;
		}
		
	}

	@Override
	public boolean addNewBranchDetails(BranchList branchList) {
		Connection connection = null;
		try {
			connection = MyConnection.getMySQLConnection();
			PreparedStatement preStmt=connection.prepareCall(ADD_NEW_BRANCH);
			preStmt.setString(1, branchList.getBranchCode());
			preStmt.setString(2, branchList.getIfscCode());
			preStmt.setString(3, branchList.getLocation());
			preStmt.setString(4, branchList.getAddress());
			preStmt.setString(5, branchList.getTelephone());
			preStmt.setString(6, branchList.getFaxNumber());
			preStmt.execute();
			connection.close();
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}
		
	}

	@Override
	public BranchList viewBranchDetails(String branchCode) {
		Connection connection = null;
		BranchList branchDet=new BranchList();
		try {
			connection=MyConnection.getMySQLConnection();
			PreparedStatement preStmt=connection.prepareStatement(VIEW_BRANCH_DETAILS);
			preStmt.setString(1, branchCode);
			ResultSet rset=preStmt.executeQuery();
			if(rset.next()){
				branchDet.setBranchCode(rset.getString(1));
				branchDet.setIfscCode(rset.getString(2));
				branchDet.setLocation(rset.getString(3));
				branchDet.setAddress(rset.getString(4));
				branchDet.setTelephone(rset.getString(5));
				branchDet.setFaxNumber(rset.getString(6));
				
			}
			connection.close();
			return branchDet;
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
	public boolean updateBranchDetails(BranchList branchList) {
		
		Connection connection = null;
		try {
			connection=MyConnection.getMySQLConnection();
			CallableStatement callStmt=connection.prepareCall(UPDATE_BRANCH_DETAILS);
			callStmt.setString(1,branchList.getBranchCode());
			callStmt.setString(2,branchList.getIfscCode());
			callStmt.setString(3, branchList.getLocation());
			callStmt.setString(4, branchList.getAddress());
			callStmt.setString(5,branchList.getTelephone());
			callStmt.setString(6, branchList.getFaxNumber());
			callStmt.executeQuery();

			connection.close();
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		return false;
		}
	}

	@Override
	public List<BranchList> listAllBranch() {
		List<BranchList> temp=new LinkedList<BranchList>();
		BranchList bl=new BranchList();
		Connection conn=null;
		try {
			conn=MyConnection.getMySQLConnection();
			PreparedStatement pre=conn.prepareStatement(BRANCH_LIST);
			ResultSet rs=pre.executeQuery();
			while(rs.next()){
				bl.setBranchCode(rs.getString(1));
				bl.setLocation(rs.getString(3));
				temp.add(bl);
			}
			conn.close();
			return temp;
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
	public boolean addNewDesignation(Designation desig) {
		Connection connection=null;
		try {
			connection = MyConnection.getMySQLConnection();
			CallableStatement call=connection.prepareCall(ADD_DESIGNATION);
			call.setString(1, desig.getDesignationName());
			call.setString(2, desig.getDesignationDescription());
			call.execute();
			connection.close();
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}
	}

	@Override
	public List<String> listAllDesignation() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addNewTransactionType(TransactionType transactionType) {
		Connection connection=null;
		try {
			connection = MyConnection.getMySQLConnection();
			CallableStatement call=connection.prepareCall(ADD_TRANS_TYPE);
			call.setString(1, transactionType.getTransactionType());
			call.setString(2, transactionType.getTransDescription());
			call.execute();
			connection.close();
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			return false;
		}
	}

	@Override
	public boolean changePassword(DesktopUser user) {
		Connection connection = null;
		try {
			connection=MyConnection.getMySQLConnection();
			CallableStatement callStmt=connection.prepareCall(CHANGE_PASSWORD);
			callStmt.setString(1,user.getUsername());
			callStmt.setString(2,user.getPassword());
			callStmt.executeQuery();
			connection.close();
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			try {
				connection.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		return false;
		}
	}
}
