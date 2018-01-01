package com.max.login.Services.Impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.max.login.Entities.LoginUser;
import com.max.login.Entities.Subject;
import com.max.login.Services.AuthenticationService;
import com.max.login.Services.AuthorizationService;
import com.max.login.Services.LoginService;
import com.max.login.Utils.Constants.AppConstants;

@Service
public class LoginServiceImpl implements LoginService {

	private static final Logger logger = LoggerFactory.getLogger(LoginService.class);

	@Autowired
	AuthenticationService authenticationService;

	@Autowired
	AuthorizationService authorizationService;

	@Override
	public Subject getLoggedInUserSubject(LoginUser user) {
		logger.info("Inside LoginService : Authenticate User : " + user.getUserName());

		Subject subject = new Subject();

		if (user != null && StringUtils.isNoneBlank(user.getUserName(), user.getPassword())) {
			
			//Prepare Token from user credentials
			UsernamePasswordToken userNamePasswordToken = new UsernamePasswordToken(user.getUserName(),	user.getPassword());
			org.apache.shiro.subject.Subject currentUser = SecurityUtils.getSubject();

			try {
				//Do Login using Shiro's UserAuthentication Realm
				currentUser.login(userNamePasswordToken);

				if (currentUser.isAuthenticated()) {
					logger.info("Authorize user after successful authentication");
					subject = authorizationService.authorizeUser(user);
					
					if(subject != null && subject.getLoginMessage().contains(AppConstants.SUCCESS)) {
						subject.setLoginMessage(AppConstants.SUCCESS_LOGIN_MESSAGE);
					} 
				}
			
			} catch (IncorrectCredentialsException ice) {
				logger.error("User Login Failed for user: " + user.getUserName()
						+ " and reason is IncorrectCredentialsException");
				logger.error(ice.getMessage(), ice);
				subject.setLoginMessage(AppConstants.INVALID_CREDENTIALS_LOGIN_FAILURE_MESSAGE);
			
			} catch (UnknownAccountException uae) {
				logger.error("User Login Failed for user: " + user.getUserName() + " and reason is UnknownAccountException");
				logger.error(uae.getMessage(), uae);
				subject.setLoginMessage(AppConstants.UNKNOWN_ACCOUNT_LOGIN_FAILURE_MESSAGE);
			
			} catch (AuthenticationException ae) {
				logger.error("User Login Failed for user: " + user.getUserName() + " and reason is AuthenticationException");
				logger.error(ae.getMessage(), ae);
				subject.setLoginMessage(AppConstants.AUTHENTICATION_LOGIN_FAILURE_MESSAGE);
			
			} catch (Exception ex) {
				logger.error("Exception Occurred While Authenticating user : " + user.getUserName());
				logger.error(ex.getMessage(), ex);
				subject.setLoginMessage(AppConstants.AUTHENTICATION_SERVICE_EXCEPTION_LOGIN_FAILURE_MESSAGE);
			}

		}

		return subject;
	}

}
