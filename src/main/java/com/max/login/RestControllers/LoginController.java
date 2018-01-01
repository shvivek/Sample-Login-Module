package com.max.login.RestControllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.max.login.Entities.LoginUser;
import com.max.login.Entities.Subject;
import com.max.login.Services.Impl.LoginServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private LoginServiceImpl loginService;

	@CrossOrigin(methods = RequestMethod.POST)
	@ApiOperation(value = "Authenticates User", notes = "Provide a valid LoginUser Object with credentials")
	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Subject login(@RequestBody LoginUser user) {

		Subject userSubject = null;
		logger.info("Login API:  Login For UserName : " + user.getUserName());

		userSubject = loginService.getLoggedInUserSubject(user);
		return userSubject;
	}

}
