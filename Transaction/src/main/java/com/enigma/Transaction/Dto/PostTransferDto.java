package com.enigma.Transaction.Dto;

import java.math.BigDecimal;

public class PostTransferDto {
	private String account;
	private String to;
	private String amount;
	private String description;
	
	public PostTransferDto() {
		// TODO Auto-generated constructor stub
	}
	
	public PostTransferDto(String account, String to, String amount, String description) {
		super();
		this.account = account;
		this.to = to;
		this.amount = amount;
		this.description = description;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	
	
}
