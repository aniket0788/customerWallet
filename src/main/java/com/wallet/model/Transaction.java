package com.wallet.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Transaction {
	
	@Id
    @GeneratedValue(strategy=GenerationType.AUTO)
	private Long transactionId;
	@NotNull(message="Please enter a CustomerId")
	private Integer customerId;
	@NotNull(message="Please enter an amount")
	private Double amount;
	private String transactionType;
	private Timestamp timeStamp;
	private String status;
	
	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public Timestamp getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[transactionId=");
		builder.append(transactionId);
		builder.append(", customerId=");
		builder.append(customerId);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", transactionType=");
		builder.append(transactionType);
		builder.append(", timeStamp=");
		builder.append(timeStamp);
		builder.append(", status=");
		builder.append(status);
		builder.append("]");
		return builder.toString();
	
	}
}
