/* *****************************************************************************
 * Project: Bank System
 * Purpose: Implementation of MasterOperations interface.
 * Author: Anil Kumar(dac11)
 * Filename: MasterAction.java
 * Version: 1.0
 * Start date: 20-Dec-2014
 * End date:
 * *****************************************************************************/

package com.bs.actions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.List;
import java.util.Map;

import com.bs.bankrelated.bean.DesktopUser;
import com.bs.connection.MyConnection;
import com.bs.operations.MasterOperations;

public class MasterAction implements MasterOperations{
	
	@Override
	public boolean desktopUserLogin(DesktopUser user) {
		
		try {
			Connection connection = MyConnection.getMySQLConnection();
			CallableStatement callableStmt=connection.prepareCall("{CALL DESKTOP_USER_LOGIN(?, ?, ?)}");
			callableStmt.registerOutParameter(3, Types.VARCHAR);
			callableStmt.setString(1, user.getUsername());
			callableStmt.setString(2, user.getPassword());			
			callableStmt.execute();
			user.setAccountLevel(callableStmt.getString(3));
			connection.close();
			return true;
			
		} catch (ClassNotFoundException | SQLException e) {
			return false;
		}
	}

	@Override
	public boolean branchList(Map<String, String>branchLocations) {
		
		try {
			Connection connection = MyConnection.getMySQLConnection();
			Statement stmt=connection.createStatement();
			ResultSet rs=stmt.executeQuery(BRANCH_LIST);
			while(rs.next()){
				branchLocations.put(rs.getString(1), rs.getString(2));
			}
			connection.close();
			return true;
		} catch (ClassNotFoundException | SQLException e) {			
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean departmentList(Map<Integer, String> deptList) {
		
		try {
			Connection connection = MyConnection.getMySQLConnection();
			Statement stmt=connection.createStatement();
			ResultSet rs=stmt.executeQuery(DEPARTMENT_LIST);
			while(rs.next()){
				deptList.put(rs.getInt(1), rs.getString(2));
			}
			connection.close();
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return false;
		}
		
		
	}

	
	@Override
	public boolean designationList(List<String> designList) {
		
		try {
			Connection connection = MyConnection.getMySQLConnection();
			Statement stmt=connection.createStatement();
			ResultSet rs=stmt.executeQuery(DESIGNATION_LIST);
			while(rs.next()){
				designList.add(rs.getString(1));
			}
			connection.close();
			return true;
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean printStatementsToFile(String accountNumber, String statements) {
		File f=new File(accountNumber+".txt");
		if(f.exists()){
			try {
				FileOutputStream fos=new FileOutputStream(f);
				fos.write(statements.getBytes());
				fos.close();
				return true;				
			} catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}else{
			try{
				if(f.createNewFile()){
					FileOutputStream fos=new FileOutputStream(f);
					fos.write(statements.getBytes());
					fos.close();
					return true;	
				}
				else{
					return false;
				}
			}catch (IOException e) {
				e.printStackTrace();
				return false;
			}
		}		
	}
}
