package com.bs.bankrelated.bean;

class CreditCard {
		
	private String CreditCardNo;
	private String activationDate;
	private String accountNo;
	private String cardAmountLimit;
	private String expireDate;
	private String deactivationDate;
	public String getCreditCardNo() {
		return CreditCardNo;
	}
	public void setCreditCardNo(String creditCardNo) {
		CreditCardNo = creditCardNo;
	}
	public String getActivationDate() {
		return activationDate;
	}
	public void setActivationDate(String activationDate) {
		this.activationDate = activationDate;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getCardAmountLimit() {
		return cardAmountLimit;
	}
	public void setCardAmountLimit(String cardAmountLimit) {
		this.cardAmountLimit = cardAmountLimit;
	}
	public String getExpireDate() {
		return expireDate;
	}
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	public String getDeactivationDate() {
		return deactivationDate;
	}
	public void setDeactivationDate(String deactivationDate) {
		this.deactivationDate = deactivationDate;
	}
	
	
}
