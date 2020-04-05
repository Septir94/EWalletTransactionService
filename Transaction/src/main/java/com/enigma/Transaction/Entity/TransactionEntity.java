package com.enigma.Transaction.Entity;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
@Entity
@Table(name = "transaction")
public class TransactionEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer transactionId;
	@Column
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date transactionDate;
	@Column(nullable = true,length = 100)
	private String type;
	@Column(name = "froms",nullable = false,length = 100)
	private String from;
	@Column(name="tos",nullable = false,length = 100)
	private String to;
	@Column(name = "balances",columnDefinition = "Decimal(10,2) default '0.00'")
	private BigDecimal amount;
	@Column(nullable = true,length = 200)
	private String description;
	
	public TransactionEntity() {

	}
	public TransactionEntity(Integer transactionId, Date transactionDate, String type, String from, String to,
			BigDecimal amount, String description) {
		super();
		this.transactionId = transactionId;
		this.transactionDate = transactionDate;
		this.type = type;
		this.from = from;
		this.to = to;
		this.amount = amount;
		this.description = description;
	}
	public Integer getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}
	public Date getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
