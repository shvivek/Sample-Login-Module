package com.max.login.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.max.login.Entities.LoginUser;
import java.lang.String;
import java.util.List;

/**
 * The LoginUserRepository for fetching the LoginUser.
 */
@Repository
public interface LoginUserRepository extends MongoRepository<LoginUser, String> {
	
	/**
	 * Find by user name.
	 *
	 * @param username - userName 
	 * @return the list of valid users
	 */
	public List<LoginUser> findByUserName(String username);
	
}
