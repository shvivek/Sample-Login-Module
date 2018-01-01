package com.max.login.Services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.max.login.Entities.LoginUser;
import com.max.login.Entities.Subject;
import com.max.login.Utils.Constants.AppConstants;

@Service
public class AuthorizationService {

	private static final Logger logger = LoggerFactory.getLogger(AuthorizationService.class);

	public Subject authorizeUser(LoginUser user) {
		logger.info("Inside authorizeUser : For userName : " + user.getUserName());
		Subject authSubject = new Subject();
		if(user != null) {
			authSubject.setEmail(user.getUserName());
			authSubject.setUserName(user.getUserName());
			authSubject.setAccountType(AppConstants.USER_ACCOUNT_TYPE_PREMIUM);
			authSubject.setLoginMessage(AppConstants.SUCCESS_LOGIN_MESSAGE);
		} 
		return authSubject;
	}

}
