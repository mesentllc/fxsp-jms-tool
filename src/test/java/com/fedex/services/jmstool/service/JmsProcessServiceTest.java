package com.fedex.services.jmstool.service;

import com.fedex.services.jmstool.mock.BytesMessageMock;
import com.fedex.services.jmstool.mock.JmsTemplateMock;
import com.fedex.services.jmstool.model.MessageModel;
import com.fedex.smartpost.common.exception.RecoverableException;
import com.fedex.smartpost.common.exception.UnrecoverableException;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jms.JmsSecurityException;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.SessionCallback;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.naming.NameNotFoundException;
import javax.naming.NamingException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class JmsProcessServiceTest {
	private static JmsProcessService service;
	private static JmsTemplateMock template;
	private IMocksControl control;

	private void resetPublisherForTesting(JmsTemplate template) throws NoSuchFieldException, IllegalAccessException, IOException {
		Class<?> clazz = service.getClass();
		Field field = clazz.getDeclaredField("jmsTemplate");
		field.setAccessible(true);
		field.set(service, template);
		BufferedWriter bw = new BufferedWriter(new FileWriter("/blank.xml"));
		bw.close();
		bw = new BufferedWriter(new FileWriter("/test.xml"));
		bw.write("<Test>This is a test message</Test>");
		bw.close();
	}

	private void mockConsumer(JmsTemplate consumer) throws NoSuchFieldException, IllegalAccessException {
		Class<?> clazz = service.getClass();
		Field field = clazz.getDeclaredField("jmsTemplate");
		field.setAccessible(true);
		field.set(service, consumer);
	}

	@BeforeClass
	public static void setup() throws NamingException {
		service = new JmsProcessService("ldap://apptstldap.corp.fedex.com/ou=messaging,dc=corp,dc=fedex,dc=com");
		service.setCF("S.3534800.FXSPSHIP.EDD.PGH.CF.L1.01", null, null, 1);
	}

	@Test(expected = UnrecoverableException.class)
	public void constructor_nullPassed_exceptionThrown() throws NamingException {
		new JmsProcessService(null);
	}

	@Test(expected = UnrecoverableException.class)
	public void setCF_nullCFName_exceptionThrown() throws NamingException {
		service.setCF(null, null, null, 1);
	}

	@Test(expected = NameNotFoundException.class)
	public void setCF_badCFName_exceptionThrown() throws NamingException {
		service.setCF("ACF", null, null, 1);
	}

	@Test
	public void setCF_goodCFName_success() throws NamingException {
		service.setCF("fxClientUID=S.3534800.FXSPSHIP.EDD.PGH.CF.L1.01", null, null, 1);
	}

	@Test(expected = UnrecoverableException.class)
	public void setJmsTemplate_nullName_exceptionThrown() {
		service.setJmsTemplate(null, true);
	}

	@Test
	public void setJmsTemplate_goodPubName_success() {
		service.setJmsTemplate("fxClientDestinationUID=D.FXSPSHIP.EDD", true);
	}

	@Test
	public void setJmsTemplate_goodSubName_success() {
		service.setJmsTemplate("D.FXSPSHIP.EDD.FPA_3534888", false);
	}

	@Test(expected = UnrecoverableException.class)
	public void publish_nullPublisher_exceptionThrown() throws NoSuchFieldException, IllegalAccessException, IOException {
		resetPublisherForTesting(null);
		service.publish(new MessageModel(new File("/test.xml"), null));
	}

	@Test
	public void publish_testMessageNoProperties_successful() throws IllegalAccessException, NoSuchFieldException, IOException {
		template = new JmsTemplateMock();
		resetPublisherForTesting(template);
		service.publish(new MessageModel(new File("/test.xml"), null));
		Assert.assertEquals("<Test>This is a test message</Test>\n", template.getXml());
		Assert.assertEquals(new Properties(), template.getProperties());
	}

	@Test
	public void publish_testMessageHasProperties_successful() throws IllegalAccessException, NoSuchFieldException, IOException {
		template = new JmsTemplateMock();
		Map<String, Object> propMap = new HashMap<>();
		propMap.put("TestProp", "TestValue");
		Properties properties = new Properties();
		properties.put("TestProp", "TestValue");
		resetPublisherForTesting(template);
		service.publish(new MessageModel(new File("/test.xml"), propMap));
		Assert.assertEquals("<Test>This is a test message</Test>\n", template.getXml());
		Assert.assertEquals(properties, template.getProperties());
	}

	@Test(expected = RecoverableException.class)
	public void publish_nullMessage_exceptionThrown() {
		service.setJmsTemplate("D.FXSPSHIP.EDD", true);
		service.publish(null);
	}

	@Test(expected = JmsSecurityException.class)
	public void publish_cfAuthNotSet_exceptionThrown() throws IOException, NamingException {
		service.setCF("S.3534800.FXSPSHIP.EDD.PGH.CF.L1.01", null, null, 1);
		service.setJmsTemplate("D.FXSPSHIP.EDD", true);
		service.publish(new MessageModel(new File("/test.xml"), null));
	}

	@Test(expected = FileNotFoundException.class)
	public void publish_cfAuthSetNoXmlFile_exceptionThrown() throws IOException, NamingException {
		service.setCF("S.3534800.FXSPSHIP.EDD.PGH.CF.L1.01", "FXG-CONTAI-3534800", "77WKiWXF5iyennqTm5PO2bXGeA", 1);
		service.setJmsTemplate("D.FXSPSHIP.EDD", true);
		service.publish(new MessageModel(new File("/badFile.xml"), null));
	}

	@Test(expected = UnrecoverableException.class)
	public void consume_nullConsumer_exceptionThrown() throws NoSuchFieldException, IllegalAccessException {
		mockConsumer(null);
		service.consume(false, null, false);
	}

	@Test
	public void consume_noMessages_successful() throws NoSuchFieldException, IllegalAccessException {
		control = EasyMock.createControl();
		template = control.createMock(JmsTemplateMock.class);
		template.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
		EasyMock.expect(template.execute(EasyMock.anyObject(SessionCallback.class), EasyMock.anyBoolean())).andReturn(true);
		mockConsumer(template);
		control.replay();
		Assert.assertNull(service.consume(false, "", false));
		control.verify();
	}

	public void consume_messages_successful() throws NoSuchFieldException, IllegalAccessException, JMSException {
		control = EasyMock.createControl();
		template = control.createMock(JmsTemplateMock.class);
		template.setReceiveTimeout(100);
		EasyMock.expect(template.receive()).andReturn(new BytesMessageMock());
		template.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
		EasyMock.expect(template.execute(EasyMock.anyObject(SessionCallback.class), EasyMock.anyBoolean())).andReturn(true);
		mockConsumer(template);
		control.replay();
		Assert.assertNotNull(service.consume(false, "", false));
		control.verify();
	}


	//  This will actually publish a test message -- so I commented it so not to flood the TOPIC while running test several times.
//	@Test
	public void publish_cfAuthSetHappyPath_success() throws Exception {
		service.setCF("S.3534800.FXSPSHIP.EDD.PGH.CF.L1.01", "FXG-CONTAI-3534800", "77WKiWXF5iyennqTm5PO2bXGeA", 1);
		service.setJmsTemplate("D.FXSPSHIP.EDD", true);
		service.publish(new MessageModel(new File("/test.xml"), null));
	}

//  This will actually publish a test message -- so I commented it so not to flood the TOPIC while running test several times.
//	@Test
	public void publish_cfAuthSetBlankXml_success() throws NamingException, IOException {
		service.setCF("S.3534800.FXSPSHIP.EDD.PGH.CF.L1.01", "FXG-CONTAI-3534800", "77WKiWXF5iyennqTm5PO2bXGeA", 1);
		service.setJmsTemplate("D.FXSPSHIP.EDD", true);
		service.publish(new MessageModel(new File("/blank.xml"), null));
	}
}
