package com.bs.customerrelated.bean;

public class CheckBook {
   
	private String checkBookId;
	private String  noOfCheck;
	private String startingCheckNo;
	private String endCheckNo;
	private String issueDate;
	private String accountNo;
	public String getCheckBookId() {
		return checkBookId;
	}
	public void setCheckBookId(String checkBookId) {
		this.checkBookId = checkBookId;
	}
	public String getNoOfCheck() {
		return noOfCheck;
	}
	public void setNoOfCheck(String noOfCheck) {
		this.noOfCheck = noOfCheck;
	}
	public String getStartingCheckNo() {
		return startingCheckNo;
	}
	public void setStartingCheckNo(String startingCheckNo) {
		this.startingCheckNo = startingCheckNo;
	}
	public String getEndCheckNo() {
		return endCheckNo;
	}
	public void setEndCheckNo(String endCheckNo) {
		this.endCheckNo = endCheckNo;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	
}
