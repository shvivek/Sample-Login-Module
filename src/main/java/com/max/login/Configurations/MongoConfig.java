package com.max.login.Configurations;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.max.login.Utils.AESEncryption;
import com.max.login.Utils.Constants.AppConstants;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoDatabase;

/**
 * The Class MongoConfig.
 */
@Configuration
public class MongoConfig extends AbstractMongoConfiguration {

	/** The application properties. */
	private static ApplicationProperties applicationProperties = ApplicationProperties.getInstance();
	
	/** The Constant logger. */
	private static final Logger logger = LoggerFactory.getLogger(MongoConfig.class);

	@Override
	protected String getDatabaseName() {
		return applicationProperties.getValue(AppConstants.MONGODB_DATABASE_NAME, "");
	}

	@Override
	public Mongo mongo() throws Exception {
		final int mongoDbPort = Integer.valueOf(applicationProperties.getValue(AppConstants.MONGODB_HOST_PORT, ""));
		final MongoClientOptions options = MongoClientOptions.builder().sslEnabled(false).build();
		final MongoClient mongoClient = new MongoClient(
				new ServerAddress(applicationProperties.getValue(AppConstants.MONGODB_HOST_IP, ""), mongoDbPort),
				options);
		return mongoClient;
	}

	/**
	 * Gets the mongo database.
	 *
	 * @return the mongo database
	 */
	@Bean
	public MongoDatabase getMongoDatabase() {
		final MongoClient mongoClient = getMongoClient();
		return mongoClient.getDatabase(applicationProperties.getValue(AppConstants.MONGODB_DATABASE_NAME, ""));
	}

	/**
	 * Gets the mongo client.
	 *
	 * @return the mongo client
	 */
	@Bean
	public MongoClient getMongoClient() {
		final Integer mongoDbPort = Integer.valueOf(applicationProperties.getValue(AppConstants.MONGODB_HOST_PORT, ""));
		String mongoPassword = "";

		try {
			mongoPassword = AESEncryption
					.decryptText(applicationProperties.getValue(AppConstants.MONGODB_USER_PWD, ""));
		} catch (Exception e) {
			logger.error("Error occured while decrypting the mongo password");
			e.printStackTrace();
		}

		final String mongoUserName = applicationProperties.getValue(AppConstants.MONGODB_USER_NAME, "");
		final String mongoHostIp = applicationProperties.getValue(AppConstants.MONGODB_HOST_IP, "");
		final String mongoDataBaseName = applicationProperties.getValue(AppConstants.MONGODB_DATABASE_NAME, "");
		final MongoCredential credential = MongoCredential.createCredential(mongoUserName, mongoDataBaseName,
				mongoPassword.toCharArray());
		final MongoClientOptions options = MongoClientOptions.builder().sslEnabled(false).build();
		final MongoClient mongoClient = new MongoClient(new ServerAddress(mongoHostIp, mongoDbPort),
				Arrays.asList(credential), options);
		return mongoClient;
	}

	@Bean
	public MongoTemplate mongoTemplate() {
		final MongoTemplate mongoTemplate = new MongoTemplate(getMongoClient(),
				applicationProperties.getValue(AppConstants.MONGODB_DATABASE_NAME, ""));
		return mongoTemplate;
	}
}
