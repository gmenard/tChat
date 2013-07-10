package com.gmenard.tchat.client.ui;

import java.awt.Color;
import java.awt.TextArea;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.WindowConstants;

import com.gmenard.tchat.TchatConstants;

/**
 * This class defines the home GUI for the client.
 * 
 * @author Guillaume
 */
@SuppressWarnings("serial")
public class HomeUI extends JFrame {

	/** Button to open the popup for joining a chat roon topic. */
	protected JButton joinTopicButton;
	/** Button to open the popup to set server settings and connect. */
	protected JButton serverConnectionButton;
	/** Button to open the popup for authenticating user. */
	protected JButton authentifyUserButton;
	/** Button for user to post a new message in chat. */
	protected JButton postButton;
	/** Input containing message to post. */
	protected JTextField postTextField;
	/** Area containing the list of chatters in the selected chat room. */
	protected TextArea leftTextArea;
	/** Area containing posted messages in the selected chat room. */
	protected TextArea mainTextArea;

	protected JLabel usersListLabel;
	protected JPanel panel1;
	protected JPanel panel2;

	public HomeUI() {
		super();
		panel1 = new JPanel();
		panel2 = new JPanel();
		leftTextArea = new TextArea();
		mainTextArea = new TextArea();
		usersListLabel = new JLabel();
		postTextField = new JTextField();
		joinTopicButton = new JButton();
		postButton = new JButton();
		serverConnectionButton = new JButton();
		authentifyUserButton = new JButton();

		initComponents();

		setTitle(TchatConstants.APPLICATION_TITLE);
		usersListLabel.setText(TchatConstants.LIST_USERS_LABEL);
		joinTopicButton.setText(TchatConstants.JOIN_TOPIC_BUTTON_TEXT);
		postButton.setText(TchatConstants.POST_MESSAGE_BUTTON_TEXT);
		serverConnectionButton.setText(TchatConstants.SERVER_CONNECTION_BUTTON_TEXT);
		authentifyUserButton.setText(TchatConstants.AUTHENTICATION_BUTTON_TEXT);
	}

	private void initComponents() {

		joinTopicButton.setEnabled(false);
		postTextField.setEnabled(false);
		mainTextArea.setEnabled(false);
		leftTextArea.setEnabled(false);
		postButton.setEnabled(false);
		serverConnectionButton.setEnabled(true);
		authentifyUserButton.setEnabled(false);

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		usersListLabel.setForeground(new Color(255, 0, 0));

		GroupLayout jPanel1Layout = new GroupLayout(panel1);
		panel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				jPanel1Layout
						.createSequentialGroup()
						.addGroup(
								jPanel1Layout
										.createParallelGroup(GroupLayout.Alignment.LEADING)
										.addGroup(jPanel1Layout.createSequentialGroup().addGap(26, 26, 26).addComponent(usersListLabel))
										.addGroup(
												jPanel1Layout
														.createSequentialGroup()
														.addContainerGap()
														.addGroup(
																jPanel1Layout
																		.createParallelGroup(GroupLayout.Alignment.LEADING)
																		.addGroup(
																				jPanel1Layout
																						.createSequentialGroup()
																						.addGap(10, 10, 10)
																						.addComponent(joinTopicButton, GroupLayout.PREFERRED_SIZE,
																								99, GroupLayout.PREFERRED_SIZE))
																		.addComponent(leftTextArea, GroupLayout.PREFERRED_SIZE, 120,
																				GroupLayout.PREFERRED_SIZE)))).addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				jPanel1Layout.createSequentialGroup().addGap(21, 21, 21).addComponent(usersListLabel)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(leftTextArea, GroupLayout.PREFERRED_SIZE, 441, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(joinTopicButton).addContainerGap(16, Short.MAX_VALUE)));

		GroupLayout jPanel2Layout = new GroupLayout(panel2);
		panel2.setLayout(jPanel2Layout);
		jPanel2Layout
				.setHorizontalGroup(jPanel2Layout
						.createParallelGroup(GroupLayout.Alignment.LEADING)
						.addGroup(
								jPanel2Layout
										.createSequentialGroup()
										.addGroup(
												jPanel2Layout
														.createParallelGroup(GroupLayout.Alignment.LEADING)
														.addComponent(mainTextArea, GroupLayout.DEFAULT_SIZE, 868, Short.MAX_VALUE)
														.addGroup(
																GroupLayout.Alignment.TRAILING,
																jPanel2Layout
																		.createSequentialGroup()
																		.addContainerGap()
																		.addComponent(postTextField, GroupLayout.DEFAULT_SIZE, 740, Short.MAX_VALUE)
																		.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
																		.addComponent(postButton, GroupLayout.PREFERRED_SIZE, 102,
																				GroupLayout.PREFERRED_SIZE).addGap(10, 10, 10))).addContainerGap())
						.addGroup(
								GroupLayout.Alignment.TRAILING,
								jPanel2Layout.createSequentialGroup().addContainerGap(208, Short.MAX_VALUE)
										.addComponent(serverConnectionButton, GroupLayout.PREFERRED_SIZE, 198, GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addComponent(authentifyUserButton, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
										.addGap(248, 248, 248)));
		jPanel2Layout.setVerticalGroup(jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						jPanel2Layout
								.createSequentialGroup()
								.addContainerGap()
								.addGroup(
										jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(serverConnectionButton)
												.addComponent(authentifyUserButton))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(mainTextArea, GroupLayout.PREFERRED_SIZE, 444, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										jPanel2Layout
												.createParallelGroup(GroupLayout.Alignment.BASELINE)
												.addComponent(postTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
														GroupLayout.PREFERRED_SIZE).addComponent(postButton))));

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(
				layout.createSequentialGroup().addContainerGap().addComponent(panel1, GroupLayout.PREFERRED_SIZE, 138, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(panel2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addContainerGap()));
		layout.setVerticalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(panel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(panel2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addContainerGap()));

		pack();
	}

}