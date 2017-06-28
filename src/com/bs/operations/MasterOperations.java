/* *****************************************************************************
 * Project: Bank System
 * Purpose: Defining an interface named MasterOperations for better GUI interactions.
 * Author: Anil Kumar(dac11)
 * Filename: MasterOperations.java
 * Version: 1.0
 * Start date: 20-Dec-2014
 * End date:
 * *****************************************************************************/

package com.bs.operations;

import java.util.List;
import java.util.Map;

import com.bs.bankrelated.bean.DesktopUser;

public interface MasterOperations {
	final String BRANCH_LIST="SELECT*FROM BRANCH_LOCATIONS";
	final String DEPARTMENT_LIST="SELECT DEPARTMENT_NO, DEPARTMENT_NAME FROM DEPARTMENT";
	final String DESIGNATION_LIST="SELECT JOB_NAME FROM JOBS";
	boolean desktopUserLogin(DesktopUser user);
	boolean branchList(Map<String, String>branchLocations);
	boolean departmentList(Map<Integer,String>deptList);
	boolean designationList(List<String>designList);
	boolean printStatementsToFile(String accountNumber, String statements);
}
