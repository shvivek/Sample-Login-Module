package com.max.login.Services;

import com.max.login.Entities.LoginUser;
import com.max.login.Entities.Subject;

public interface LoginService {
	
	public Subject getLoggedInUserSubject(LoginUser user);

}
