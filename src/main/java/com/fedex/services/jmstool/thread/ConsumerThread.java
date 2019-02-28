package com.fedex.services.jmstool.thread;

import com.fedex.services.jmstool.model.MessageModel;
import com.fedex.smartpost.common.exception.UnrecoverableException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class ConsumerThread extends Thread {
	private static final Log LOGGER = LogFactory.getLog(ConsumerThread.class);
	private final String fileRoot;
	private final Boolean readAll;
	private final Boolean saveMessage;
	private final JmsTemplate jmsTemplate;
	private MessageModel model;
	private Long messagesRead;

	public ConsumerThread(JmsTemplate jmsTemplate, boolean saveMessage, String fileRoot, boolean readAll) {
		if (jmsTemplate == null) {
			throw new UnrecoverableException("A message consumer MUST be declared.", false);
		}
		this.jmsTemplate = jmsTemplate;
		this.saveMessage = saveMessage;
		this.fileRoot = fileRoot;
		this.readAll = readAll;
	}

	private void getMessageFromJms() {
		jmsTemplate.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
		model = null;
		// Need to do it this way, (Creating a MessageConsumer attaching it to the session, if you want it to acknowledge
		// the messages -- otherwise it just reads the first message and keeps it on the JMS queue.
		// Wait for 1 second (max) to see if there are any messages
		jmsTemplate.execute(session -> {
			MessageConsumer consumer = session.createConsumer(
					jmsTemplate.getDestinationResolver().resolveDestinationName(session, jmsTemplate.getDefaultDestinationName(),
					                                                            jmsTemplate.isPubSubDomain()));
			try {
				Message message = consumer.receive(1000);
				if (message != null) {
					model = new MessageModel(message);
					message.acknowledge();
					messagesRead++;
					if (saveMessage) {
						saveToFile(fileRoot, model);
					}
					return true;
				}
			}
			catch (Exception e) {
				LOGGER.error("Error attempting to received JMS message:", e);
				return false;
			}
			finally {
				consumer.close();
			}
			return true;
		}, true);
	}

	private void saveToFile(String fileRoot, MessageModel model) {
		String filename = fileRoot;
		if (!filename.endsWith("/") && !filename.endsWith("\\")) {
			filename += "/";
		}
		String messageId = model.getJmsHeaderFields().get("JMSMessageID").toString();
		messageId = messageId.replaceAll(":", "_");
		filename += messageId + ".txt";
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(filename));
			bw.write("Headers:\n");
			for (String key : model.getJmsHeaderFields().keySet()) {
				bw.write(key + " - " + model.getJmsHeaderFields().get(key) + "\n");
			}
			bw.write("Properties:\n");
			for (String key : model.getProperties().keySet()) {
				bw.write(key + " - " + model.getProperties().get(key) + "\n");
			}
			bw.write("Payload:\n");
			bw.write(model.getXml());
			bw.close();
		}
		catch (IOException ex) {
			LOGGER.error(ex);
		}
	}

	public MessageModel getMessageModel() {
		return model;
	}

	public Long getMessagesRead() {
		return messagesRead;
	}

	@Override
	public void run() {
		messagesRead = 0L;
		getMessageFromJms();
		if (readAll) {
			while (model != null) {
				getMessageFromJms();
			}
		}
	}
}
