package com.fedex.services.jmstool.thread;

import com.fedex.services.jmstool.LogAdviser;

import javax.swing.JTextArea;
import javax.swing.SwingWorker;
import java.util.List;

public class ConsoleThread extends SwingWorker<Void, String> {
	private LogAdviser logAdviser;
	private JTextArea console;

	public ConsoleThread(JTextArea console, LogAdviser logAdviser) {
		this.console = console;
		this.logAdviser = logAdviser;
	}

	@Override
	protected Void doInBackground() {
		String lastPublished = null;

		while (!isCancelled()) {
			String consoleString = logAdviser.retrieveLogs();
			if (consoleString != null && !consoleString.equals(lastPublished)) {
				lastPublished = consoleString;
				publish(consoleString);
			}
		}
		return null;
	}

	@Override
	protected void process(List<String> chunks) {
		if (console == null) {
			return;
		}
		String consoleString = chunks.get(chunks.size() - 1);
		console.setText(consoleString);
		chunks.clear();
	}
}
