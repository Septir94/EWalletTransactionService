package com.enigma.Transaction.Dto;

import java.math.BigDecimal;

public class PostPulsaDto {
	private String account;
	private String amount;
	private String hp;
	public PostPulsaDto() {
		super();

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
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	
}
