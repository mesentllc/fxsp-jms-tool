package com.fedex.services.jmstool.service;

import com.fedex.services.jmstool.mock.BytesMessageMock;
import com.fedex.services.jmstool.model.MessageModel;
import com.fedex.services.jmstool.thread.ConsumerThread;
import com.fedex.smartpost.common.exception.UnrecoverableException;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.SessionCallback;

import javax.jms.Message;
import javax.jms.Session;

public class ConsumerThreadTest {
	private IMocksControl control;
	private JmsTemplate consumer;
	private Message message;

	@Before
	public void setup() {
		control = EasyMock.createControl();
		consumer = control.createMock(JmsTemplate.class);
		message = new BytesMessageMock();
	}

	@Test(expected = UnrecoverableException.class)
	public void run_nullMessageConsumer_exceptionThrown() {
		new ConsumerThread(null, false, "", false);
	}

	public void run_noMessages_nullReturned() {
		consumer.setReceiveTimeout(100);
		EasyMock.expect(consumer.receive()).andReturn(null);
		control.replay();
		ConsumerThread thread = new ConsumerThread(consumer, false, null, false);
		thread.run();
		Assert.assertNull(thread.getMessageModel());
		Assert.assertTrue(thread.getMessagesRead() == 0L);
		control.verify();
	}

	public void run_readOneMessage_modelReturned() {
		consumer.setReceiveTimeout(100);
		EasyMock.expect(consumer.receive()).andReturn(message);
		control.replay();
		ConsumerThread thread = new ConsumerThread(consumer, false, null, false);
		thread.run();
		MessageModel model = thread.getMessageModel();
		Assert.assertNotNull(model);
		Assert.assertTrue(thread.getMessagesRead() == 1L);
		control.verify();
	}

	public void run_readOneMessageAndSave_modelReturned() {
		consumer.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
		EasyMock.expect(consumer.execute(EasyMock.anyObject(SessionCallback.class), EasyMock.anyBoolean())).andReturn(true);
		consumer.setReceiveTimeout(100);
		EasyMock.expect(consumer.receive()).andReturn(message);
		control.replay();
		ConsumerThread thread = new ConsumerThread(consumer, true, ".", false);
		thread.run();
		MessageModel model = thread.getMessageModel();
		Assert.assertNotNull(model);
		Assert.assertTrue(thread.getMessagesRead() == 1L);
		control.verify();
	}

	public void run_multipleMessagesLookingForAll_allReadNullReturned() {
		consumer.setReceiveTimeout(100);
		EasyMock.expectLastCall().times(11);
		EasyMock.expect(consumer.receive()).andReturn(message);
		EasyMock.expectLastCall().times(10);
		EasyMock.expect(consumer.receive()).andReturn(null);
		control.replay();
		ConsumerThread thread = new ConsumerThread(consumer, false, null, true);
		thread.run();
		Assert.assertNull(thread.getMessageModel());
		Assert.assertEquals(10L, (long)thread.getMessagesRead());
		control.verify();
	}

}
