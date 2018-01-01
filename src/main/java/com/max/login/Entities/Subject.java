package com.max.login.Entities;

public class Subject {

	private String userName;

	private String accountType;

	private String email;

	private String loginMessage;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getLoginMessage() {
		return loginMessage;
	}

	public void setLoginMessage(String loginMessage) {
		this.loginMessage = loginMessage;
	}

	@Override
	public String toString() {
		return "Subject [userName=" + userName + ", accountType=" + accountType + ", email=" + email + ", loginMessage="
				+ loginMessage + "]";
	}

}
