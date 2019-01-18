package com.fedex.services.jmstool.appender;

import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;

import java.io.Serializable;

public class LogEventAppender extends AbstractAppender {
	private StringBuilder sb = new StringBuilder();

	public LogEventAppender(String name, Filter filter, Layout<? extends Serializable> layout) {
		super(name, filter, layout);
	}

	@Override
	public void append(LogEvent logEvent) {
		sb.append("[").append(logEvent.getLevel().name()).append("]");
		String message = logEvent.getMessage().getFormattedMessage();
		if (StringUtils.isNotEmpty(message)) {
			sb.append(" - ").append(message);
		}
		Throwable throwable = logEvent.getThrown();
		if (throwable != null) {
			sb.append(" - ").append(logEvent.getThrown().getMessage());
		}
		sb.append("\n");
		if (sb.length() > 50000) {
			String temp = sb.toString();
			temp = temp.substring(temp.length() - 50000);
			sb = new StringBuilder();
			sb.append(temp);
		}
	}

	public String retrieveLogs() {
		if (sb.length() == 0) {
			return null;
		}
		return sb.toString();
	}
}
