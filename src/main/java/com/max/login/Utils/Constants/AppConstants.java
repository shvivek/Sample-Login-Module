package com.max.login.Utils.Constants;

/**
 * The Interface AppConstants.
 */
public interface AppConstants {

	// Login Message Constants
	/** The Constant SUCCESS_LOGIN_MESSAGE. */
	public static final String SUCCESS_LOGIN_MESSAGE = "Success : User Login Successful.";
	
	/** The Constant INVALID_LOGIN_AUTHORIZATION_FAILURE_MESSAGE. */
	public static final String INVALID_LOGIN_AUTHORIZATION_FAILURE_MESSAGE = "Failure : User Authorization Failed. Please Contact Your Administrator.";

	/** The Constant INVALID_CREDENTIALS_LOGIN_FAILURE_MESSAGE. */
	public static final String INVALID_CREDENTIALS_LOGIN_FAILURE_MESSAGE = "Invalid Login : Failed to Login with the given credentials.";
	
	/** The Constant UNKNOWN_ACCOUNT_LOGIN_FAILURE_MESSAGE. */
	public static final String UNKNOWN_ACCOUNT_LOGIN_FAILURE_MESSAGE = "Invalid Login : Failed to Login for Unknown User Account.";
	
	/** The Constant AUTHENTICATION_LOGIN_FAILURE_MESSAGE. */
	public static final String AUTHENTICATION_LOGIN_FAILURE_MESSAGE = "Invalid Login : Authentication Exception Occurred.";
	
	/** The Constant AUTHENTICATION_SERVICE_EXCEPTION_LOGIN_FAILURE_MESSAGE. */
	public static final String AUTHENTICATION_SERVICE_EXCEPTION_LOGIN_FAILURE_MESSAGE = "Invalid Login : Authentication Service Failure.";
	
	/** The Constant SUCCESS. */
	public static final String SUCCESS = "Success";
	
	/** The Constant Failure. */
	public static final String FAILURE = "Failure";
	
	// Encryption Type
	/** The Constant ENCRYPTION_TYPE_AES. */
	public static final String ENCRYPTION_TYPE_AES = "AES";
	
	/** The Constant COLLECTION_USER_SUBJECT. */
	// Collection Constants
	public static final String COLLECTION_USER_SUBJECT = "subject";
	
	/** The Constant COLLECTION_LOGIN_USER. */
	public static final String COLLECTION_LOGIN_USER = "login_user";
	
	/** The Constant USER_ACCOUNT_TYPE_PREMIUM. */
	public static final String USER_ACCOUNT_TYPE_PREMIUM = "premium";
	
	// Package Constants
	public static final String BASE_PACKAGE = "com.max";
	
	public static final String ENTITY_PACKAGE = "com.max.login.Entities";
	
	public static final String REPOSITORY_PACKAGE = "com.max.login.Repositories";
	
	public static final String CONTROLLER_PACKAGE = "com.max.login.RestControllers";
	
	/* ===================MongoDB configuration Constants=================== */

	/** The Constant MONGODB_HOST_IP. */
	public static final String MONGODB_HOST_IP = "max.mongo.host";

	/** The Constant MONGODB_HOST_PORT. */
	public static final String MONGODB_HOST_PORT = "max.mongo.port";

	/** The Constant MONGODB_DATABASE_NAME. */
	public static final String MONGODB_DATABASE_NAME = "max.mongo.dbName";

	/** The Constant MONGODB_USER_NAME. */
	public static final String MONGODB_USER_NAME = "max.mongo.username";

	/** The Constant MONGODB_USER_PWD. */
	public static final String MONGODB_USER_PWD = "max.mongo.password";

}
