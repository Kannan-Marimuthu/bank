package com.kannan.sample.bank.vo;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4894151942167623254L;
	private int id;
	private String userName;
	private String status;

	private List<Account> accountDetails;

	public User() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Account> getAccountDetails() {
		return accountDetails;
	}

	public void setAccountDetails(List<Account> accountDetails) {
		this.accountDetails = accountDetails;
	}

}
