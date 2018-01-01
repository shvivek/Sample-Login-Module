package com.max.login.Configurations;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;

import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.AnonymousFilter;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.apache.shiro.web.filter.authz.RolesAuthorizationFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.max.login.ShiroAuthRealm.UserAuthenticationRealm;

@Configuration
public class ShiroConfig {
	
	@Bean(name = "shiroFilter")
	public ShiroFilterFactoryBean shiroFilter() {
		ShiroFilterFactoryBean shiroFilter = new ShiroFilterFactoryBean();
		shiroFilter.setLoginUrl("/login");
		shiroFilter.setSuccessUrl("/home");
		shiroFilter.setUnauthorizedUrl("/login");
		Map<String, String> filterChainDefinitionMapping = new HashMap<String, String>();

		filterChainDefinitionMapping.put("/", "anon");
		filterChainDefinitionMapping.put("/admin", "authc,roles[admin]");
		shiroFilter.setFilterChainDefinitionMap(filterChainDefinitionMapping);
		shiroFilter.setSecurityManager(securityManager());

		Map<String, Filter> filters = new HashMap<String, Filter>();
		filters.put("anon", new AnonymousFilter());
		filters.put("authc", new FormAuthenticationFilter());
		filters.put("logout", new LogoutFilter());
		filters.put("roles", new RolesAuthorizationFilter());
		filters.put("user", new UserFilter());
		shiroFilter.setFilters(filters);
		return shiroFilter;
	}

	@Bean(name = "securityManager")
	public SecurityManager securityManager() {
		SecurityManager securityManager = new DefaultWebSecurityManager(realm());
		return securityManager;
	}

	@Bean(name = "realm")
	@DependsOn("lifecycleBeanPostProcessor")
	public AuthenticatingRealm realm() {
		UserAuthenticationRealm autenticationRealm = new UserAuthenticationRealm();
		autenticationRealm.init();
		return autenticationRealm;
	}

	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

}
