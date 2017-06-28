package com.bs.customerrelated.bean;

public class LoanPayementDetail {
   
	private String loanId;
	private String payDate;
	private String installmentNo;
	private String amountLeft;
	private String nextInstallment;
	public String getLoanId() {
		return loanId;
	}
	public void setLoanId(String loanId) {
		this.loanId = loanId;
	}
	public String getPayDate() {
		return payDate;
	}
	public void setPayDate(String payDate) {
		this.payDate = payDate;
	}
	public String getInstallmentNo() {
		return installmentNo;
	}
	public void setInstallmentNo(String installmentNo) {
		this.installmentNo = installmentNo;
	}
	public String getAmountLeft() {
		return amountLeft;
	}
	public void setAmountLeft(String amountLeft) {
		this.amountLeft = amountLeft;
	}
	public String getNextInstallment() {
		return nextInstallment;
	}
	public void setNextInstallment(String nextInstallment) {
		this.nextInstallment = nextInstallment;
	}
	
}
