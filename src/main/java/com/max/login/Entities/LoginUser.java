package com.max.login.Entities;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.mongodb.core.mapping.Document;

import com.max.login.Utils.Constants.AppConstants;

@Document(collection = AppConstants.COLLECTION_LOGIN_USER)
public class LoginUser {
	
	/** The user name. */
	@NotEmpty
	private String userName;
	
	/** The password. */
	@NotEmpty
	private String password;

	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the user name.
	 *
	 * @param userName the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginUser [userName=" + userName + ", password=" + password + "]";
	}

}
