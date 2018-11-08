package com.fedex.services.jmstool.thread;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;

import com.fedex.services.jmstool.model.MessageModel;
import com.fedex.services.jmstool.service.JmsProcessService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PublishThread extends Thread {
	private static final Log LOG = LogFactory.getLog(PublishThread.class);
	private final int threadNumber;
	private final JmsProcessService service;
	private final BlockingQueue<String> messageQueue;

	public PublishThread(int threadNumber, JmsProcessService service, BlockingQueue<String> messageQueue) {
		this.threadNumber = threadNumber;
		this.service = service;
		this.messageQueue = messageQueue;
	}

	@Override
	public void run() {
		String filename = "";
		boolean active = true;
		Map<String, Object> properties = new HashMap<>();

		while (active) {
			try {
				filename = messageQueue.take();
				if ("END".equals(filename)) {
					active = false;
				}
				else {
					LOG.info("Publishing " + filename + " on thread " + threadNumber);
					service.publish(new MessageModel(new File(filename), properties));
				}
			}
			catch (InterruptedException e) {
				LOG.info("Thread " + threadNumber + " has been interrupted.");
				active = false;
			}
			catch (IOException e) {
				LOG.info(filename + " was not found.");
				active = false;
			}
		}
	}
}
