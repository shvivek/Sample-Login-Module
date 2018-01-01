package com.max.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.max.login.Utils.Constants.AppConstants;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EntityScan(basePackages = { AppConstants.BASE_PACKAGE })
@EnableMongoRepositories({ AppConstants.REPOSITORY_PACKAGE })
@ComponentScan(basePackages = { AppConstants.BASE_PACKAGE })
@EnableTransactionManagement
@EnableSwagger2
public class LoginModuleApplication extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(LoginModuleApplication.class, args);
	}
}
