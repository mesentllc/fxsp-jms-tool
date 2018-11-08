package com.fedex.services.jmstool;

import com.fedex.services.jmstool.appender.LogEventAppender;
import com.fedex.services.jmstool.model.MessageModel;
import com.fedex.services.jmstool.service.JmsProcessService;
import com.fedex.services.jmstool.thread.ConsoleThread;
import com.fedex.services.jmstool.thread.PublishThread;
import com.fedex.smartpost.common.exception.RecoverableException;

import java.awt.TrayIcon;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.UnexpectedException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import javax.naming.NamingException;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.TableModel;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

public class MainFrame extends javax.swing.JFrame implements ActionListener, ChangeListener, MouseListener {

	private static final Log LOGGER = LogFactory.getLog(MainFrame.class);
	private final LogAdviser logAdviser;
	private final ConsoleThread consoleThread;
	private final BlockingQueue<String> messageQueue = new LinkedBlockingDeque<>();

	public MainFrame() {
		logAdviser = new LogAdviser(new LogEventAppender("logEventAppender", null,
			PatternLayout.createDefaultLayout()));
		initComponents();
		consoleThread = new ConsoleThread(txtConsole, logAdviser);
		consoleThread.execute();
		rbgTQ.add(rbTopic);
		rbgTQ.add(rbQueue);
		rbgConsume.add(rbConsumeOne);
		rbgConsume.add(rbConsumeAll);
		rbgFileDir.add(rbInFile);
		rbgFileDir.add(rbInDir);
		consumerElements();
	}

	private void consumerElements() {
		txtTarget.setEnabled(cbxSaveMessages.isSelected());
		btnTarget.setEnabled(cbxSaveMessages.isSelected());
		txtTarget.setEditable(cbxSaveMessages.isSelected());
	}

	@SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rbgPubSub = new javax.swing.ButtonGroup();
        rbgConsume = new javax.swing.ButtonGroup();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        rbgTQ = new javax.swing.ButtonGroup();
        buttonGroup1 = new javax.swing.ButtonGroup();
        rbgFileDir = new javax.swing.ButtonGroup();
        lblUrl = new javax.swing.JLabel();
        lblCF = new javax.swing.JLabel();
        txtCF = new javax.swing.JTextField();
        txtTQ = new javax.swing.JTextField();
        lblUsername = new javax.swing.JLabel();
        txtUsername = new javax.swing.JTextField();
        lblPassword = new javax.swing.JLabel();
        txtPassword = new javax.swing.JTextField();
        btnExecute = new javax.swing.JButton();
        tabPane = new javax.swing.JTabbedPane();
        pnlConsume = new javax.swing.JPanel();
        lblConsume = new javax.swing.JLabel();
        rbConsumeOne = new javax.swing.JRadioButton();
        rbConsumeAll = new javax.swing.JRadioButton();
        cbxSaveMessages = new javax.swing.JCheckBox();
        lblDir = new javax.swing.JLabel();
        txtTarget = new javax.swing.JTextField();
        btnTarget = new javax.swing.JButton();
        pnlPublish = new javax.swing.JPanel();
        txtSourceDir = new javax.swing.JTextField();
        btnSource = new javax.swing.JButton();
        scrlPane = new javax.swing.JScrollPane();
        tblProperties = new javax.swing.JTable();
        rbInFile = new javax.swing.JRadioButton();
        rbInDir = new javax.swing.JRadioButton();
        pnlSubTesting = new javax.swing.JPanel();
        rbSubTestFile = new javax.swing.JRadioButton();
        rbSubTestDir = new javax.swing.JRadioButton();
        txtSubTestSrc = new javax.swing.JTextField();
        btnSubTestSource = new javax.swing.JButton();
        lblMsgCount = new javax.swing.JLabel();
        txtMsgCount = new javax.swing.JTextField();
        lblThreads = new javax.swing.JLabel();
        txtThreads = new javax.swing.JTextField();
        rbTopic = new javax.swing.JRadioButton();
        rbQueue = new javax.swing.JRadioButton();
        cbxUrl = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtConsole = new javax.swing.JTextArea();
        menuBar = new javax.swing.JMenuBar();
        mnuLoad = new javax.swing.JMenu();
        mnuSave = new javax.swing.JMenu();
        mnuReset = new javax.swing.JMenu();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JMS Pub/Sub Tool");
        setAlwaysOnTop(true);
        setForeground(java.awt.Color.white);
        setMinimumSize(new java.awt.Dimension(806, 550));
        setName("mainFrame"); // NOI18N
        setResizable(false);
        getContentPane().setLayout(null);

        lblUrl.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblUrl.setText("LDAP URL:");
        lblUrl.setFocusable(false);
        getContentPane().add(lblUrl);
        lblUrl.setBounds(12, 14, 60, 16);
        lblUrl.getAccessibleContext().setAccessibleName("lblUrl");

        lblCF.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblCF.setText("Connection Factory:");
        lblCF.setFocusable(false);
        getContentPane().add(lblCF);
        lblCF.setBounds(12, 40, 112, 16);

        txtCF.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        getContentPane().add(txtCF);
        txtCF.setBounds(170, 40, 600, 23);

        txtTQ.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        getContentPane().add(txtTQ);
        txtTQ.setBounds(170, 70, 600, 23);

        lblUsername.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblUsername.setText("Username:");
        getContentPane().add(lblUsername);
        lblUsername.setBounds(100, 110, 62, 16);

        txtUsername.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        getContentPane().add(txtUsername);
        txtUsername.setBounds(180, 110, 200, 23);

        lblPassword.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblPassword.setText("Password:");
        getContentPane().add(lblPassword);
        lblPassword.setBounds(400, 110, 61, 16);

        txtPassword.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        getContentPane().add(txtPassword);
        txtPassword.setBounds(470, 110, 200, 23);

        btnExecute.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnExecute.setText("Execute");
        btnExecute.addActionListener(this);
        getContentPane().add(btnExecute);
        btnExecute.setBounds(700, 200, 80, 26);

        tabPane.setFocusable(false);
        tabPane.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        tabPane.setMaximumSize(new java.awt.Dimension(607, 149));
        tabPane.setMinimumSize(new java.awt.Dimension(607, 149));
        tabPane.setPreferredSize(new java.awt.Dimension(607, 149));

        pnlConsume.setFocusable(false);
        pnlConsume.setMaximumSize(new java.awt.Dimension(607, 149));
        pnlConsume.setMinimumSize(new java.awt.Dimension(607, 149));
        pnlConsume.setName("pnlConsume"); // NOI18N
        pnlConsume.setPreferredSize(new java.awt.Dimension(607, 149));

        lblConsume.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblConsume.setText("Comsume:");
        lblConsume.setFocusable(false);

        rbConsumeOne.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        rbConsumeOne.setSelected(true);
        rbConsumeOne.setText("One Message");

        rbConsumeAll.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        rbConsumeAll.setText("All Messages");

        cbxSaveMessages.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        cbxSaveMessages.setText("Save Messages");
        cbxSaveMessages.addChangeListener(this);

        lblDir.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        lblDir.setText("Directory:");
        lblDir.setFocusable(false);

        txtTarget.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtTarget.setAutoscrolls(false);
        txtTarget.setFocusable(false);
        txtTarget.setRequestFocusEnabled(false);

        btnTarget.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnTarget.setText("...");
        btnTarget.addActionListener(this);

        javax.swing.GroupLayout pnlConsumeLayout = new javax.swing.GroupLayout(pnlConsume);
        pnlConsume.setLayout(pnlConsumeLayout);
        pnlConsumeLayout.setHorizontalGroup(
            pnlConsumeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConsumeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlConsumeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlConsumeLayout.createSequentialGroup()
                        .addComponent(lblConsume)
                        .addGap(18, 18, 18)
                        .addComponent(rbConsumeOne)
                        .addGap(18, 18, 18)
                        .addComponent(rbConsumeAll))
                    .addGroup(pnlConsumeLayout.createSequentialGroup()
                        .addComponent(cbxSaveMessages)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblDir)
                        .addGap(6, 6, 6)
                        .addComponent(txtTarget, javax.swing.GroupLayout.PREFERRED_SIZE, 358, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTarget, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(48, Short.MAX_VALUE))
        );
        pnlConsumeLayout.setVerticalGroup(
            pnlConsumeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlConsumeLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlConsumeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblConsume)
                    .addComponent(rbConsumeOne)
                    .addComponent(rbConsumeAll))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlConsumeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxSaveMessages)
                    .addComponent(lblDir)
                    .addComponent(txtTarget, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTarget))
                .addContainerGap(89, Short.MAX_VALUE))
        );

        tabPane.addTab("Consumer", pnlConsume);

        pnlPublish.setDoubleBuffered(false);
        pnlPublish.setFocusable(false);
        pnlPublish.setMaximumSize(new java.awt.Dimension(607, 149));
        pnlPublish.setMinimumSize(new java.awt.Dimension(607, 149));
        pnlPublish.setName(""); // NOI18N

        txtSourceDir.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtSourceDir.setAutoscrolls(false);

        btnSource.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnSource.setText("...");
        btnSource.addActionListener(this);

        scrlPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrlPane.setHorizontalScrollBar(null);

        tblProperties.setDefaultRenderer(Object.class, new EvenOddRenderer());
        tblProperties.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        tblProperties.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Property", "Value"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblProperties.setFocusable(false);
        scrlPane.setViewportView(tblProperties);

        rbInFile.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        rbInFile.setSelected(true);
        rbInFile.setText("File");

        rbInDir.setText("Directory");

        javax.swing.GroupLayout pnlPublishLayout = new javax.swing.GroupLayout(pnlPublish);
        pnlPublish.setLayout(pnlPublishLayout);
        pnlPublishLayout.setHorizontalGroup(
            pnlPublishLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPublishLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPublishLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrlPane, javax.swing.GroupLayout.DEFAULT_SIZE, 621, Short.MAX_VALUE)
                    .addGroup(pnlPublishLayout.createSequentialGroup()
                        .addComponent(rbInFile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbInDir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSourceDir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSource, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        pnlPublishLayout.setVerticalGroup(
            pnlPublishLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPublishLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlPublishLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSourceDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSource)
                    .addComponent(rbInFile)
                    .addComponent(rbInDir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrlPane, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        tabPane.addTab("Publisher", pnlPublish);

        rbSubTestFile.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        rbSubTestFile.setSelected(true);
        rbSubTestFile.setText("File");

        rbSubTestDir.setText("Directory");

        txtSubTestSrc.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtSubTestSrc.setAutoscrolls(false);

        btnSubTestSource.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        btnSubTestSource.setText("...");
        btnSubTestSource.addActionListener(this);

        lblMsgCount.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        lblMsgCount.setText("Message Count:");
        lblMsgCount.setToolTipText("");

        txtMsgCount.setText("1000");

        lblThreads.setFont(new java.awt.Font("Dialog", 1, 11)); // NOI18N
        lblThreads.setText("Thread Count:");

        txtThreads.setText("10");

        javax.swing.GroupLayout pnlSubTestingLayout = new javax.swing.GroupLayout(pnlSubTesting);
        pnlSubTesting.setLayout(pnlSubTestingLayout);
        pnlSubTestingLayout.setHorizontalGroup(
            pnlSubTestingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSubTestingLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlSubTestingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlSubTestingLayout.createSequentialGroup()
                        .addComponent(rbSubTestFile)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rbSubTestDir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtSubTestSrc, javax.swing.GroupLayout.DEFAULT_SIZE, 459, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnSubTestSource, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(pnlSubTestingLayout.createSequentialGroup()
                        .addComponent(lblMsgCount)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtMsgCount, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lblThreads)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtThreads, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        pnlSubTestingLayout.setVerticalGroup(
            pnlSubTestingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlSubTestingLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(pnlSubTestingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtSubTestSrc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSubTestSource)
                    .addComponent(rbSubTestFile)
                    .addComponent(rbSubTestDir))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlSubTestingLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblMsgCount)
                    .addComponent(txtMsgCount, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblThreads)
                    .addComponent(txtThreads, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        tabPane.addTab("Sub Testing", pnlSubTesting);

        getContentPane().add(tabPane);
        tabPane.setBounds(30, 140, 650, 170);

        rbTopic.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        rbTopic.setText("Topic");
        getContentPane().add(rbTopic);
        rbTopic.setBounds(20, 70, 56, 24);

        rbQueue.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        rbQueue.setSelected(true);
        rbQueue.setText("Queue");
        getContentPane().add(rbQueue);
        rbQueue.setBounds(80, 70, 91, 23);

        cbxUrl.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        cbxUrl.setMaximumRowCount(2);
        cbxUrl.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ldap://apptstldap.corp.fedex.com/ou=messaging,dc=corp,dc=fedex,dc=com", "ldap://appldap.corp.fedex.com/ou=messaging,dc=prod,dc=fedex,dc=com" }));
        getContentPane().add(cbxUrl);
        cbxUrl.setBounds(170, 10, 600, 25);

        txtConsole.setEditable(false);
        txtConsole.setColumns(20);
        txtConsole.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        txtConsole.setRows(5);
        jScrollPane1.setViewportView(txtConsole);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(30, 320, 750, 140);

        mnuLoad.setText("Load File");
        mnuLoad.addMouseListener(this);
        menuBar.add(mnuLoad);

        mnuSave.setText("Save File");
        mnuSave.addMouseListener(this);
        menuBar.add(mnuSave);

        mnuReset.setText("Reset Screen");
        mnuReset.addMouseListener(this);
        menuBar.add(mnuReset);

        setJMenuBar(menuBar);

        pack();
    }

    // Code for dispatching events from components to event handlers.

    public void actionPerformed(java.awt.event.ActionEvent evt) {
        if (evt.getSource() == btnExecute) {
            MainFrame.this.btnExecuteActionPerformed(evt);
        }
        else if (evt.getSource() == btnTarget) {
            MainFrame.this.btnTargetActionPerformed(evt);
        }
        else if (evt.getSource() == btnSource) {
            MainFrame.this.btnSourceActionPerformed(evt);
        }
        else if (evt.getSource() == btnSubTestSource) {
            MainFrame.this.btnSubTestSourceActionPerformed(evt);
        }
    }

    public void mouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getSource() == mnuLoad) {
            MainFrame.this.mnuLoadMouseClicked(evt);
        }
        else if (evt.getSource() == mnuSave) {
            MainFrame.this.mnuSaveMouseClicked(evt);
        }
        else if (evt.getSource() == mnuReset) {
            MainFrame.this.mnuResetMouseClicked(evt);
        }
    }

    public void mouseEntered(java.awt.event.MouseEvent evt) {
    }

    public void mouseExited(java.awt.event.MouseEvent evt) {
    }

    public void mousePressed(java.awt.event.MouseEvent evt) {
    }

    public void mouseReleased(java.awt.event.MouseEvent evt) {
    }

    public void stateChanged(javax.swing.event.ChangeEvent evt) {
        if (evt.getSource() == cbxSaveMessages) {
            MainFrame.this.cbxSaveMessagesStateChanged(evt);
        }
    }// </editor-fold>//GEN-END:initComponents

    private void btnExecuteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExecuteActionPerformed
		try {
			JmsProcessService service = new JmsProcessService(cbxUrl.getSelectedItem().toString());
			service.setCF(txtCF.getText(), txtUsername.getText(), txtPassword.getText());
			service.setJmsTemplate(txtTQ.getText(), rbTopic.isSelected());
			if (tabPane.getSelectedIndex() == 0) {
				MessageModel model = service.consume(cbxSaveMessages.isSelected(), txtTarget.getText(),
					rbConsumeAll.isSelected());
				if (model != null || rbConsumeAll.isSelected()) {
					JOptionPane.showMessageDialog(this, "Completed.");
				}
				else {
					JOptionPane.showMessageDialog(this, "No messages found.");
				}
			}
			if (tabPane.getSelectedIndex() == 1) {
				if (StringUtils.isNotEmpty(txtSourceDir.getText())) {
					processPublish(service, txtSourceDir.getText());
					JOptionPane.showMessageDialog(this, "Completed.");
				}
			}
			if (tabPane.getSelectedIndex() == 2) {
				if (StringUtils.isEmpty(txtSubTestSrc.getText())) {
					JOptionPane.showMessageDialog(this, "Message source MUST be identified.", "Error!",
					                              JOptionPane.ERROR_MESSAGE);
					return;
				}
				processTestingPublish(service, txtSubTestSrc.getText());
				JOptionPane.showMessageDialog(this, "Completed.");
			}
		}
		catch (NamingException ex) {
			LOGGER.error("Exception Caught: ", ex);
		}
    }//GEN-LAST:event_btnExecuteActionPerformed

	private void processTestingPublish(JmsProcessService service, String source) {
		if (service == null) {
			throw new RecoverableException("The publisher must be established first, before message publish is attempted.", false);
		}
		int messageSent = 0;
		int ptr = 0;
		File[] fileList = buildFileList(source);
		int threadCount = Integer.parseInt(txtThreads.getText());
		PublishThread threads[] = new PublishThread[threadCount];
		for (int cntr = 0; cntr < threadCount; cntr++) {
			threads[cntr] = new PublishThread(cntr, service, messageQueue);
			threads[cntr].run();
		}
		while (messageSent < Integer.parseInt(txtMsgCount.getText())) {
			try {
				messageQueue.put(fileList[ptr].getAbsolutePath());
				messageSent++;
				if (++ptr >= fileList.length) {
					ptr = 0;
				}
			}
			catch (InterruptedException e) {
				LOGGER.trace("Unable to find " + fileList[ptr].getAbsolutePath());
				if (fileList.length == 1) {
					LOGGER.info(fileList[0].getAbsolutePath() + " not found and is the only message source - Aborting.");
					messageSent = Integer.parseInt(txtMsgCount.getText()) + 1;
				}
			}
		}
		for (int cntr = 0; cntr < threadCount; cntr++) {
			try {
				messageQueue.put("END");
				threads[cntr].join();
			}
			catch (InterruptedException e) {
				LOGGER.trace("Unable to send termination marker to thread queue.");
			}
		}
	}

	private File[] buildFileList(String source) {
		File[] fileList;
		File file = new File(source);
		if (file.isDirectory()) {
			fileList = file.listFiles();
		}
		else {
			fileList = new File[1];
			fileList[0] = file;
		}
		return fileList;
	}

	private void cbxSaveMessagesStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cbxSaveMessagesStateChanged
		consumerElements();
    }//GEN-LAST:event_cbxSaveMessagesStateChanged

    private void btnTargetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTargetActionPerformed
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.removeChoosableFileFilter(fileChooser.getAcceptAllFileFilter());
		fileChooser.setCurrentDirectory(new File(txtTarget.getText()));
		fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			txtTarget.setText(selectedFile.getAbsolutePath());
		}
    }//GEN-LAST:event_btnTargetActionPerformed

    private void btnSourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSourceActionPerformed
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.removeChoosableFileFilter(fileChooser.getAcceptAllFileFilter());
		fileChooser.setCurrentDirectory(new File(txtSourceDir.getText()));
		if (rbInFile.isSelected()) {
			fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		}
		else {
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		}
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			File selectedFile = fileChooser.getSelectedFile();
			txtSourceDir.setText(selectedFile.getAbsolutePath());
		}
    }//GEN-LAST:event_btnSourceActionPerformed

    private void mnuLoadMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuLoadMouseClicked
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.removeChoosableFileFilter(fileChooser.getAcceptAllFileFilter());
		fileChooser.setFileFilter(new FileNameExtensionFilter("JMS Profiles", "jms"));
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			loadProfile(fileChooser.getSelectedFile());
		}
    }//GEN-LAST:event_mnuLoadMouseClicked

    private void mnuSaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuSaveMouseClicked
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.removeChoosableFileFilter(fileChooser.getAcceptAllFileFilter());
		fileChooser.setFileFilter(new FileNameExtensionFilter("JMS Profiles", "jms"));
		fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		if (fileChooser.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
			saveProfile(fileChooser.getSelectedFile());
		}
    }//GEN-LAST:event_mnuSaveMouseClicked

    private void mnuResetMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mnuResetMouseClicked
		cbxUrl.setSelectedIndex(0);
		tabPane.setSelectedIndex(0);
		txtCF.setText(null);
		txtTQ.setText(null);
		rbTopic.setSelected(false);
		rbQueue.setSelected(true);
		txtUsername.setText(null);
		txtPassword.setText(null);
		rbConsumeAll.setSelected(false);
		rbConsumeOne.setSelected(true);
		cbxSaveMessages.setSelected(false);
		txtTarget.setText(null);
		rbInFile.setSelected(false);
		rbInDir.setSelected(true);
		txtSourceDir.setText(null);
		TableModel model = tblProperties.getModel();
		for (int cntr = 0; cntr < 8; cntr++) {
			model.setValueAt(null, cntr, 0);
			model.setValueAt(null, cntr, 1);
		}
    }//GEN-LAST:event_mnuResetMouseClicked

    private void btnSubTestSourceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubTestSourceActionPerformed
		// TODO add your handling code here:
    }//GEN-LAST:event_btnSubTestSourceActionPerformed

	public static void main(String args[]) {
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		}
		catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
			LOGGER.fatal(ex);
		}
		java.awt.EventQueue.invokeLater(() -> new MainFrame().setVisible(true));
	}

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExecute;
    private javax.swing.JButton btnSource;
    private javax.swing.JButton btnSubTestSource;
    private javax.swing.JButton btnTarget;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox cbxSaveMessages;
    private javax.swing.JComboBox<String> cbxUrl;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblCF;
    private javax.swing.JLabel lblConsume;
    private javax.swing.JLabel lblDir;
    private javax.swing.JLabel lblMsgCount;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JLabel lblThreads;
    private javax.swing.JLabel lblUrl;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu mnuLoad;
    private javax.swing.JMenu mnuReset;
    private javax.swing.JMenu mnuSave;
    private javax.swing.JPanel pnlConsume;
    private javax.swing.JPanel pnlPublish;
    private javax.swing.JPanel pnlSubTesting;
    private javax.swing.JRadioButton rbConsumeAll;
    private javax.swing.JRadioButton rbConsumeOne;
    private javax.swing.JRadioButton rbInDir;
    private javax.swing.JRadioButton rbInFile;
    private javax.swing.JRadioButton rbQueue;
    private javax.swing.JRadioButton rbSubTestDir;
    private javax.swing.JRadioButton rbSubTestFile;
    private javax.swing.JRadioButton rbTopic;
    private javax.swing.ButtonGroup rbgConsume;
    private javax.swing.ButtonGroup rbgFileDir;
    private javax.swing.ButtonGroup rbgPubSub;
    private javax.swing.ButtonGroup rbgTQ;
    private javax.swing.JScrollPane scrlPane;
    private javax.swing.JTabbedPane tabPane;
    private javax.swing.JTable tblProperties;
    private javax.swing.JTextField txtCF;
    private javax.swing.JTextArea txtConsole;
    private javax.swing.JTextField txtMsgCount;
    private javax.swing.JTextField txtPassword;
    private javax.swing.JTextField txtSourceDir;
    private javax.swing.JTextField txtSubTestSrc;
    private javax.swing.JTextField txtTQ;
    private javax.swing.JTextField txtTarget;
    private javax.swing.JTextField txtThreads;
    private javax.swing.JTextField txtUsername;
    // End of variables declaration//GEN-END:variables

	private void processPublish(JmsProcessService service, String source) {
		if (service == null) {
			throw new RecoverableException("The publisher must be established first, before message publish is attempted.", false);
		}
		TableModel tblModel = tblProperties.getModel();
		int rows = tblModel.getRowCount();
		Map<String, Object> properties = new HashMap<>();
		for (int row = 0; row < rows; row++) {
			String property = (String) tblModel.getValueAt(row, 0);
			if (StringUtils.isNotEmpty(property)) {
				properties.put(property, tblModel.getValueAt(row, 1));
			}
		}
		File file = new File(source);
		try {
			if (file.isDirectory()) {
				File[] fileList = file.listFiles();
				for (File subFile : fileList) {
					if (subFile.isFile()) {
						service.publish(new MessageModel(file, properties));
					}
				}
			}
			else {
				service.publish(new MessageModel(file, properties));
			}
		}
		catch (Exception e) {
			LOGGER.error("", e);
		}
	}

	private void loadProfile(File selectedFile) {
		try {
			BufferedReader br = new BufferedReader(new FileReader(selectedFile));
			setWindows(br.readLine().split("\\|"));
			setTQ(br.readLine().split("\\|"));
			setAuth(br.readLine().split("\\|"));
			setConsumerPanel(br.readLine().split("\\|"));
			setPublisherPanel(br.readLine().split("\\|"));
			readProperties(br);
			br.close();
		}
		catch (Exception ex) {
			LOGGER.error("Error processing " + selectedFile.getAbsolutePath(), ex);
		}
	}

	private void saveProfile(File selectedFile) {
		if (!selectedFile.getName().endsWith(".jms")) {
			selectedFile = new File(selectedFile.getAbsolutePath() + ".jms");
		}
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter(selectedFile));
			StringBuilder sb = new StringBuilder();
			sb.append(cbxUrl.getSelectedIndex()).append("|").append(tabPane.getSelectedIndex() == 0 ? "C" : "P")
				.append("|").append(txtCF.getText()).append("\n");
			sb.append(rbTopic.isSelected() ? "T" : "Q").append("|").append(txtTQ.getText()).append("\n");
			sb.append(txtUsername.getText()).append("|").append(txtPassword.getText()).append("\n");
			sb.append(rbConsumeAll.isSelected() ? "A" : "O").append("|")
				.append(cbxSaveMessages.isSelected() ? "X" : "").append("|").append(txtTarget.getText()).append("\n");
			sb.append(rbInFile.isSelected() ? "F" : "D").append("|").append(txtSourceDir.getText()).append("\n");
			TableModel tableModel = tblProperties.getModel();
			for (int cntr = 0; cntr < 8; cntr++) {
				if (StringUtils.isNotEmpty((String) tableModel.getValueAt(cntr, 0))) {
					sb.append(tableModel.getValueAt(cntr, 0)).append("|")
						.append(tableModel.getValueAt(cntr, 1)).append("\n");
				}
			}
			bw.write(sb.toString());
			bw.close();
			JOptionPane.showMessageDialog(this, "Successfully Saved.");
		}
		catch (Exception e) {
			LOGGER.error("Error processing " + selectedFile.getAbsolutePath(), e);
		}
	}

	private void setWindows(String[] selections) {
		cbxUrl.setSelectedIndex(Integer.parseInt(selections[0]));
		tabPane.setSelectedIndex(0);
		if ("P".equals(selections[1])) {
			tabPane.setSelectedIndex(1);
		}
		txtCF.setText(selections[2]);
	}

	private void setTQ(String[] selections) throws UnexpectedException {
		if (selections.length != 2) {
			throw new UnexpectedException("Line 2 - Topic/Queue setup corrupt.");
		}
		txtTQ.setText(selections[1]);
		if ("T".equals(selections[0])) {
			rbTopic.setSelected(true);
			rbQueue.setSelected(false);
		}
		else {
			rbTopic.setSelected(false);
			rbQueue.setSelected(true);
		}
	}

	private void setAuth(String[] selections) {
		txtUsername.setText(selections[0]);
		if (selections.length > 1) {
			txtPassword.setText(selections[1]);
		}
	}

	private void setConsumerPanel(String[] selections) {
		rbConsumeAll.setSelected(false);
		rbConsumeOne.setSelected(true);
		cbxSaveMessages.setSelected(false);
		txtTarget.setText(null);
		if ("A".equals(selections[0])) {
			rbConsumeAll.setSelected(true);
			rbConsumeOne.setSelected(false);
		}
		if (selections.length > 1) {
			cbxSaveMessages.setSelected("X".equals(selections[1]));
		}
		if (selections.length > 2) {
			txtTarget.setText(selections[2]);
		}
	}

	private void setPublisherPanel(String[] selections) {
		rbInFile.setSelected(false);
		rbInDir.setSelected(true);
		txtSourceDir.setText(null);
		if ("F".equals(selections[0])) {
			rbInFile.setSelected(true);
			rbInDir.setSelected(false);
		}
		if (selections.length > 1) {
			txtSourceDir.setText(selections[1]);
		}
	}

	private void readProperties(BufferedReader br) throws IOException {
		int propertyCount = 0;
		while (propertyCount < 9 && br.ready()) {
			String[] props = br.readLine().split("\\|");
			if (StringUtils.isNotEmpty(props[0])) {
				tblProperties.getModel().setValueAt(props[0].trim(), propertyCount, 0);
				tblProperties.getModel().setValueAt(props[1].trim(), propertyCount, 1);
				propertyCount++;
			}
		}
	}
}
