package com.fedex.services.jmstool.mock;

import java.util.Properties;

import com.fedex.services.jmstool.utils.JmsMessageCreator;
import org.springframework.jms.JmsException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class JmsTemplateMock extends JmsTemplate {
	private Properties properties;
	private String xml;

	public Properties getProperties() {
		return properties;
	}

	public String getXml() {
		return xml;
	}

	@Override
	public void send(MessageCreator messageCreator) throws JmsException {
		JmsMessageCreator creator = (JmsMessageCreator)messageCreator;
		xml = creator.getMessage();
		properties = creator.getMessageProperties();
	}
}
