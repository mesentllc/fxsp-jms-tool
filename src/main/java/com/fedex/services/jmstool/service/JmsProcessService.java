package com.fedex.services.jmstool.service;

import com.fedex.services.jmstool.model.MessageModel;
import com.fedex.services.jmstool.thread.ConsumerThread;
import com.fedex.services.jmstool.utils.JmsMessageCreator;
import com.fedex.services.jmstool.utils.JmsUtils;
import com.fedex.smartpost.common.exception.RecoverableException;
import com.fedex.smartpost.common.exception.UnrecoverableException;
import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.connection.UserCredentialsConnectionFactoryAdapter;
import org.springframework.jms.core.JmsTemplate;

import javax.naming.NamingException;

public class JmsProcessService {
	private static final Log LOGGER = LogFactory.getLog(JmsProcessService.class);
	private final JmsUtils jmsUtils;
	private CachingConnectionFactory cfAdapter;
	private JmsTemplate jmsTemplate;

	public JmsProcessService(String url) throws NamingException {
		if (url == null) {
			throw new UnrecoverableException("LDAP URL MUST NOT be NULL!", false);
		}
		jmsUtils = new JmsUtils(url);
	}

	private MessageModel consumeOne(boolean saveMessage, String fileRoot) {
		ConsumerThread thread = new ConsumerThread(jmsTemplate, saveMessage, fileRoot, false);
		thread.start();
		try {
			thread.join();
		}
		catch (InterruptedException ex) {
			LOGGER.info("Consumer interrupted.");
		}
		return thread.getMessageModel();
	}

	public void setCF(String cfName, String username, String password, int threadCount) throws NamingException {
		if (cfName == null) {
			throw new UnrecoverableException("Connection Factory Name MUST NOT be NULL!", false);
		}
		LOGGER.debug(String.format("Initializing the Connection Factory - %s - [%s/%s]", cfName, username, password));
		if (!cfName.startsWith("fxClientUID=")) {
			cfAdapter = jmsUtils.getConnectionFactory("fxClientUID=" + cfName, username, password, threadCount);

		}
		else {
			cfAdapter = jmsUtils.getConnectionFactory(cfName, username, password, threadCount);
		}
		LOGGER.debug(String.format("Connection Factory - %s - created", cfName));
	}

	public void setJmsTemplate(String name, boolean isTopic) {
		if (name == null) {
			throw new UnrecoverableException("Destination Name MUST NOT be NULL!", false);
		}
		LOGGER.debug(String.format("Setting up %s - %s", isTopic ? "Topic" : "Queue", name));
		if (cfAdapter == null) {
			throw new UnrecoverableException("Must setup the connection factory first.", false);
		}
//		if (!name.startsWith("fxClientDestinationUID=")) {
//			jmsTemplate = jmsUtils.getJmsTemplate(cfAdapter, "fxClientDestinationUID=" + name, isTopic);
//		}
//		else {
			jmsTemplate = jmsUtils.getJmsTemplate(cfAdapter, name, isTopic);
//		}
		LOGGER.debug(name + " created");
	}

	public void publish(MessageModel model) {
		if (jmsTemplate == null) {
			throw new UnrecoverableException("Must setup the JmsTemplate first.", false);
		}
		if (model == null) {
			throw new RecoverableException("Nothing to send - skipping the publish.", false);
		}
		LOGGER.debug("Publishing: " + ReflectionToStringBuilder.toString(model, ToStringStyle.MULTI_LINE_STYLE));
		jmsTemplate.send(new JmsMessageCreator(model.getXml(), model.getProperties()));
		LOGGER.debug("Message Sent");
	}

	public MessageModel consume(boolean saveMessage, String fileRoot, boolean readAll) {
		if (!readAll) {
			return consumeOne(saveMessage, fileRoot);
		}
		ConsumerThread[] threads = new ConsumerThread[10];
		for (int i = 0; i < 10; i++) {
			threads[i] = new ConsumerThread(jmsTemplate, saveMessage, fileRoot, true);
			threads[i].start();
		}
		for (int i = 0; i < 10; i++) {
			try {
				threads[i].join();
			}
			catch (InterruptedException ex) {
				LOGGER.info("Consumer interrupted.");
			}
		}
		return threads[0].getMessageModel();
	}
}
