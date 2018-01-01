package com.max.login.Services;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.max.login.Entities.LoginUser;
import com.max.login.Repositories.LoginUserRepository;
import com.max.login.Utils.AESEncryption;

@Service
public class AuthenticationService {

	private static final Logger logger = LoggerFactory.getLogger(AuthenticationService.class);

	@Autowired
	private LoginUserRepository loginUserRepo;

	public boolean doAuthenticate(UsernamePasswordToken usernamePasswordToken) {

		boolean isAuthenticated = false;

		if (usernamePasswordToken != null && StringUtils.isNotEmpty(usernamePasswordToken.getUsername())
				&& usernamePasswordToken.getPassword().length > 0) {
			String userName = usernamePasswordToken.getPrincipal().toString();

			// Fetch LoginUser by userName
			List<LoginUser> user = loginUserRepo.findByUserName(userName);
			if (user != null && !user.isEmpty()) {
				isAuthenticated = authenticateUser(user.get(0), usernamePasswordToken);
			}
		}
		return isAuthenticated;
	}

	private boolean authenticateUser(LoginUser user, UsernamePasswordToken usernamePasswordToken) {
		logger.info("Inside authenticateUser: For userName : " + user.getUserName());

		final String userName = usernamePasswordToken.getPrincipal().toString();
		final String password = String.valueOf(usernamePasswordToken.getPassword());

		if (user != null && StringUtils.isNoneBlank(user.getUserName(), user.getPassword())) {

			try {
				if (userName.equals(user.getUserName())
						&& password.equals(AESEncryption.decryptText(user.getPassword()))) {
					return true;
				}
			} catch (Exception e) {
				logger.error("Exception occurred while authenticating user : " + userName, e.getMessage());
				e.printStackTrace();
			}
		}
		return false;
	}

}
