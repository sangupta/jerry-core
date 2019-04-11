/**
 *
 * jerry - Common Java Functionality
 * Copyright (c) 2012-present, Sandeep Gupta
 *
 * http://sangupta.com/projects/jerry-core
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * 		http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.sangupta.jerry;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;

import com.sangupta.jerry.util.AssertUtils;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.encoder.PatternLayoutEncoder;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.Appender;
import ch.qos.logback.core.ConsoleAppender;
import ch.qos.logback.core.encoder.Encoder;
import ch.qos.logback.core.rolling.RollingFileAppender;
import ch.qos.logback.core.rolling.TimeBasedRollingPolicy;

/**
 * Centralized logback based application logger that helps us initialize
 * logging via Java. It also helps in changing log-levels dynamically as
 * and when needed.
 * 
 * @author sangupta
 *
 */
public class ApplicationLogger {
	
	private static final LoggerContext loggerContext = (LoggerContext) LoggerFactory.getILoggerFactory();

	private static final Map<String, Appender<ILoggingEvent>> registeredAppenders = new HashMap<>();
	
	private static volatile boolean loggerInitialized = false;
	
	private static Level defaultLogLevel = Level.DEBUG;
	
	public static void setDefaultLogLevel(Level level) {
		if(level == null) {
			throw new IllegalArgumentException("Log level cannot be null");
		}
		
		defaultLogLevel = level;
	}
	
	private static Encoder<ILoggingEvent> getPatternLayoutEncoder(String logPattern) {
        PatternLayoutEncoder patternLayoutEncoder = new PatternLayoutEncoder();
        patternLayoutEncoder.setPattern(logPattern);
        patternLayoutEncoder.setContext(loggerContext);
        patternLayoutEncoder.setOutputPatternAsHeader(true);
        patternLayoutEncoder.start();

        return patternLayoutEncoder;
	}
	
	public static void addConsoleAppender(String name, String logPattern) {
		ConsoleAppender<ILoggingEvent> consoleAppender = new ConsoleAppender<>();
        consoleAppender.setEncoder(getPatternLayoutEncoder(logPattern));
        consoleAppender.setContext(loggerContext);
        consoleAppender.setName(name);
        consoleAppender.start();
        
        registeredAppenders.put(name, consoleAppender);
	}
	
	public static void addFileAppender(String name, String logPattern, String filePath, int maxHistory) {
		RollingFileAppender<ILoggingEvent> fileAppender = new RollingFileAppender<ILoggingEvent>();
        fileAppender.setFile(filePath);
        fileAppender.setEncoder(getPatternLayoutEncoder(logPattern));
        fileAppender.setContext(loggerContext);
        fileAppender.setName(name);
        fileAppender.setAppend(true);
        
        // time based rolling policy
        TimeBasedRollingPolicy<ILoggingEvent> logFilePolicy = new TimeBasedRollingPolicy<>();
	    logFilePolicy.setContext(loggerContext);
	    logFilePolicy.setParent(fileAppender);
	    logFilePolicy.setFileNamePattern(getDateBasedFileName(filePath));
	    logFilePolicy.setMaxHistory(50);
	    logFilePolicy.start();
	    
	    fileAppender.setRollingPolicy(logFilePolicy);
        fileAppender.start();

        registeredAppenders.put(name, fileAppender);
	}
	
	public static void registerRootLogger() {
		registerRootLogger(Level.INFO);
	}
	
	public static synchronized void registerRootLogger(Level rootLoggerLevel) {
		if(loggerInitialized) {
			return;
		}
		
		loggerInitialized = true;
		
        Logger rootLogger = (Logger) LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
        rootLogger.setLevel(rootLoggerLevel);
        addLogger(rootLogger, rootLoggerLevel, null);
	}
	
    public static Logger addLogger(String loggingClass) {
        return addLogger(loggingClass, (String) null);
    }
    
    public static Logger addLogger(String loggingClass, String appenderName) {
        Logger logger = (Logger) LoggerFactory.getLogger(loggingClass);
        return addLogger(logger, defaultLogLevel, appenderName);
    }
    
    public static Logger addLogger(String loggingClass, Level logLevel) {
    	return addLogger(loggingClass, logLevel, null);
    }
    
    public static Logger addLogger(String loggingClass, Level logLevel, String appenderName) {
    	if(AssertUtils.isEmpty(loggingClass)) {
    		return null;
    	}
    	
    	Logger logger = (Logger) LoggerFactory.getLogger(loggingClass);
    	return addLogger(logger, logLevel, null);
    }
    
    private static Logger addLogger(Logger logger, Level logLevel, String appenderName) {
    	if(AssertUtils.isNotEmpty(appenderName)) {
    		Appender<ILoggingEvent> appender = registeredAppenders.get(appenderName);
    		if(appender == null) {
    			return null;
    		}
    		
    		logger.addAppender(appender);
    	} else {
    		for(Appender<ILoggingEvent> appender : registeredAppenders.values()) {
    			logger.addAppender(appender);
    		}
    	}
    	
    	logger.setLevel(logLevel);
        logger.setAdditive(false);
        
        return logger;
	}
	
	/**
	 * @param filePath
	 * @return
	 */
	private static String getDateBasedFileName(String filePath) {
		return filePath + "-%d{yyyy-MM-dd_HH}.log";
	}
	
}
