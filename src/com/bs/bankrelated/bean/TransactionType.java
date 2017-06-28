/* *****************************************************************************
 * Project: Bank System
 * Purpose: To define a POJO class for transaction type
 * Author: Anil Kumar(dac11)
 * Filename: TransactionType.java
 * Version: 1.0
 * Start date: 27-Dec-2014
 * End date: 27-Dec-2014
 * *****************************************************************************/

package com.bs.bankrelated.bean;

public class TransactionType {
	private String transactionType;
	private String transDescription;
	
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public String getTransDescription() {
		return transDescription;
	}
	public void setTransDescription(String transDescription) {
		this.transDescription = transDescription;
	}
}
