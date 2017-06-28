package com.bs.customerrelated.bean;

public class NetBankingDetails {
	private String accountNumber;
	private String accountHolder;
	private String accountType;
	private String netBkStatus;
	private String netBkSrNo;
	
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getAccountHolder() {
		return accountHolder;
	}
	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getNetBkStatus() {
		return netBkStatus;
	}
	public void setNetBkStatus(String netBkStatus) {
		this.netBkStatus = netBkStatus;
	}
	public String getNetBkSrNo() {
		return netBkSrNo;
	}
	public void setNetBkSrNo(String netBkSrNo) {
		this.netBkSrNo = netBkSrNo;
	}
}
