package com.fedex.services.jmstool;

import com.fedex.services.jmstool.appender.LogEventAppender;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Appender;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;

public class LogAdviser {

	private LogEventAppender logEventAppender;

	public LogAdviser(Appender appender) {
		logEventAppender = (LogEventAppender) appender;
		LoggerContext ctx = (LoggerContext) LogManager.getContext(false);
		Configuration config = ctx.getConfiguration();
		LoggerConfig lc = config.getRootLogger();
		lc.removeAppender(appender.getName());
		appender.start();
		lc.addAppender(appender, Level.ALL, null);
	}

	public String retrieveLogs() {
		return logEventAppender.retrieveLogs();
	}
}
