package com.max.login.ShiroAuthRealm;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.max.login.Services.AuthenticationService;

public class UserAuthenticationRealm extends AuthorizingRealm {

	private static final Logger logger = LoggerFactory.getLogger(UserAuthenticationRealm.class);

	@Autowired
	private AuthenticationService authenticationService;

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authToken) throws AuthenticationException {

		logger.info("Inside UserAuthenticationRealm");
		if (authToken instanceof UsernamePasswordToken) {
			UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authToken;
			String username = usernamePasswordToken.getUsername();
			char[] password = usernamePasswordToken.getPassword();

			if (StringUtils.isBlank(username)) {
				throw new IncorrectCredentialsException("Null usernames are not allowed by this realm!");
			}
			// Null password is invalid
			if (password == null || password.length == 0) {
				throw new IncorrectCredentialsException("Null passwords are not allowed by this realm!");
			}

			boolean isAuthenticated = authenticationService.doAuthenticate(usernamePasswordToken);

			if (!isAuthenticated) {
				throw new UnknownAccountException("Could not authenticate with given credentials");
			}

			SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(username, password,"userAuthenticationRealm");

			return simpleAuthenticationInfo;

		} else {
			return null;
		}

	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

		return null;
	}

}
