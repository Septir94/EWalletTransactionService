package com.enigma.Transaction.Dto;

import java.math.BigDecimal;

public class PostAddBalanceDto {
	private String account;
	private String amount;
	public PostAddBalanceDto() {
		// TODO Auto-generated constructor stub
	}
	public PostAddBalanceDto(String account, String amount) {
		super();
		this.account = account;
		this.amount = amount;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
}
