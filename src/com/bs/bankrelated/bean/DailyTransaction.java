package com.bs.bankrelated.bean;

public class DailyTransaction {

			private String transactionId;
			private String transactionAmount;
			private String transactionDateTime;
			private String transctionDetail;
			private String fromAccount;
			private String toAccount;
			public String getTransactionId() {
				return transactionId;
			}
			public void setTransactionId(String transactionId) {
				this.transactionId = transactionId;
			}
			public String getTransactionAmount() {
				return transactionAmount;
			}
			public void setTransactionAmount(String transactionAmount) {
				this.transactionAmount = transactionAmount;
			}
			public String getTransactionDateTime() {
				return transactionDateTime;
			}
			public void setTransactionDateTime(String transactionDateTime) {
				this.transactionDateTime = transactionDateTime;
			}
			public String getTransctionDetail() {
				return transctionDetail;
			}
			public void setTransctionDetail(String transctionDetail) {
				this.transctionDetail = transctionDetail;
			}
			public String getFromAccount() {
				return fromAccount;
			}
			public void setFromAccount(String fromAccount) {
				this.fromAccount = fromAccount;
			}
			public String getToAccount() {
				return toAccount;
			}
			public void setToAccount(String toAccount) {
				this.toAccount = toAccount;
			}
			
}
