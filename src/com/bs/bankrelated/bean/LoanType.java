package com.bs.bankrelated.bean;

public class LoanType {
    private String loanType;
    private String interestType;
    private String maxAmountOfLoan;
    private String description;
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public String getInterestType() {
		return interestType;
	}
	public void setInterestType(String interestType) {
		this.interestType = interestType;
	}
	public String getMaxAmountOfLoan() {
		return maxAmountOfLoan;
	}
	public void setMaxAmountOfLoan(String maxAmountOfLoan) {
		this.maxAmountOfLoan = maxAmountOfLoan;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
    
}
