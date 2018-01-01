package com.max.login.Configurations;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationProperties {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationProperties.class);

	public static final String mongoDialect = "mongodb";

	private static ApplicationProperties instance = null;

	private Properties properties;

	public static final String PROPERTIES_FILE_PATH = "src/main/resources/application.properties";

	public ApplicationProperties() {
		
		File file = new File(PROPERTIES_FILE_PATH);
		String applicationFilePath = file.getAbsolutePath();
		logger.info("Properties file location = " + applicationFilePath);

		properties = new Properties();
		InputStream inputStream;
		try {
			inputStream = new FileInputStream(applicationFilePath);
			if (inputStream != null) {
				try {
					properties.load(inputStream);
				} catch (IOException ioex) {
					logger.error("IO Exception occured while reading Application Properties" + ioex.getMessage());
				} catch (Exception ex) {
					logger.error("Exception occured while reading Application Properties" + ex.getMessage());
				}
			}
		} catch (Exception fnex) {
			logger.error("Application properties file is not found " + fnex.getMessage());
		}
	}

	public static ApplicationProperties getInstance() {
		if (instance == null) {
			instance = new ApplicationProperties();
		}
		return instance;
	}

	public String getValue(String key, String value) {
		String paramValue = properties.getProperty(key);
		if (paramValue == null) {
			
			paramValue = value;
		}
		return paramValue;
	}

/*	public static void main(String[] args) {
		ApplicationProperties applicationProperties = ApplicationProperties.getInstance();
		System.out.println(applicationProperties.getValue("sample.property", null));
	}*/	
		
}
