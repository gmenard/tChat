package com.gmenard.tchat.client.controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import javax.swing.JOptionPane;

import com.gmenard.tchat.TchatConstants;
import com.gmenard.tchat.authentication.exceptions.UserUnknownException;
import com.gmenard.tchat.authentication.exceptions.WrongPasswordException;
import com.gmenard.tchat.client.ui.HomeUI;
import com.gmenard.tchat.core.client.ClientChatRoom;
import com.gmenard.tchat.core.client.ClientTopicsManager;
import com.gmenard.tchat.core.interfaces.IChatter;

/**
 * This class manages user interactions of the home screen.
 * 
 * @author Guillaume
 */
@SuppressWarnings("serial")
public class HomeController extends HomeUI implements ActionListener, WindowListener, IChatter {

	/** Controller for authentication popup. */
	private AuthenticationUserController authenticationController;
	/** Controller for topic joining popup. */
	private JoinTopicController joinTopicController;
	/** Controller for server connection popup. */
	private ServerConnectionController serverConnectionController;
	/** Current chat room. */
	private ClientChatRoom currentChatroom;
	/** Alias of the user in the current chat room. */
	private String pseudo;
	/** Name of the selected topic. */
	private String selectedTopic;
	/** Manager of chat room topics. */
	private ClientTopicsManager clientTopicsManager;

	public HomeController() {
		super();
		authenticationController = new AuthenticationUserController(this, true);
		serverConnectionController = new ServerConnectionController(this, true);
		joinTopicController = new JoinTopicController(this, true);
		clientTopicsManager = new ClientTopicsManager();
		authenticationController.setModal(true);
		currentChatroom = new ClientChatRoom("");
		joinTopicButton.addActionListener(this);
		postButton.addActionListener(this);
		serverConnectionButton.addActionListener(this);
		authentifyUserButton.addActionListener(this);
	}

	/**
	 * Return the user alias.
	 */
	public String getAlias() {
		return pseudo;
	}

	public void usersList(Vector<String> usersList) {
		for (String user : usersList) {
			leftTextArea.append("<" + user + ">\n");
			leftTextArea.setCaretPosition(leftTextArea.getText().length());
		}
	}

	/* (non-Javadoc)
	 * 
	 * @see com.gmenard.tchat.core.interfaces.IChatter#receiveAMessage(java.lang.String,
	 * com.gmenard.tchat.core.interfaces.IChatter) */
	@Override
	public void receiveAMessage(String message, IChatter chatter) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(TchatConstants.DEFAULT_TIME_FORMAT);
		String time = dateFormat.format(new Date());
		mainTextArea.append("\n(" + time + ") <" + chatter.getAlias() + "> : " + message + "\n");
		mainTextArea.setCaretPosition(mainTextArea.getText().length());
	}

	/* (non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent) */
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == serverConnectionButton) {
			serverConnectionController.setClientTopicsManager(clientTopicsManager);
			serverConnectionController.setVisible(true);
			clientTopicsManager = serverConnectionController.getClientTopicsManager();
			if (clientTopicsManager.isClientConnected()) {
				authentifyUserButton.setEnabled(true);
				serverConnectionButton.setEnabled(false);
			}
		} else if (event.getSource() == joinTopicButton) {
			boolean alreadyconnect = false;
			joinTopicController.setAvailableTopics(clientTopicsManager);
			joinTopicController.setVisible(true);
			selectedTopic = joinTopicController.getTopic();

			if (currentChatroom.isClientConnected()) {
				if (!currentChatroom.getTopic().equals(selectedTopic))
					currentChatroom.quit(this);
				else
					alreadyconnect = true;
			}
			if (!selectedTopic.isEmpty() && !alreadyconnect) {
				currentChatroom = (ClientChatRoom) clientTopicsManager.joinTopic(selectedTopic);
				currentChatroom.join(this);

				mainTextArea.setText(TchatConstants.WELCOME_MESSAGE);
				leftTextArea.setText("");

				postTextField.setEnabled(true);
				postTextField.setEditable(true);
				mainTextArea.setEnabled(true);
				mainTextArea.setEditable(false);
				leftTextArea.setEnabled(true);
				leftTextArea.setEditable(false);
				postButton.setEnabled(true);
			}
		} else if (event.getSource() == authentifyUserButton) {
			authenticationController.setVisible(true);

			String user = authenticationController.getUser().get("Login");
			String pass = authenticationController.getUser().get("Password");

			if (null != user && null != pass) {
				try {
					clientTopicsManager.authentify(user, pass);
					pseudo = user;
					joinTopicButton.setEnabled(true);
					authentifyUserButton.setEnabled(false);
				} catch (UserUnknownException e1) {
					JOptionPane.showMessageDialog(this, TchatConstants.USER_UNKNOWN_DIALOG_MSG, TchatConstants.USER_UNKNOWN_DIALOG_TITLE,
							JOptionPane.ERROR_MESSAGE);
				} catch (WrongPasswordException e1) {
					JOptionPane.showMessageDialog(this, TchatConstants.WRONG_PWD_DIALOG_MSG, TchatConstants.WRONG_PWD_DIALOG_TITLE,
							JOptionPane.ERROR_MESSAGE);
				}
			}
		} else if (event.getSource() == postButton || event.getSource() == postTextField) {
			if (!postTextField.getText().isEmpty()) {
				currentChatroom.post(postTextField.getText(), this);
				postTextField.setText("");
			}
		}
	}

	/* (non-Javadoc)
	 * 
	 * @see java.awt.event.WindowListener#windowActivated(java.awt.event.WindowEvent) */
	@Override
	public void windowActivated(WindowEvent arg0) {
	}

	/* (non-Javadoc)
	 * 
	 * @see java.awt.event.WindowListener#windowClosed(java.awt.event.WindowEvent) */
	@Override
	public void windowClosed(WindowEvent arg0) {
	}

	/* (non-Javadoc)
	 * 
	 * @see java.awt.event.WindowListener#windowClosing(java.awt.event.WindowEvent) */
	@Override
	public void windowClosing(WindowEvent arg0) {
		if (currentChatroom.isClientConnected()) {
			currentChatroom.quit(this);
		}
		if (clientTopicsManager.isClientConnected()) {
			clientTopicsManager.disconnect();
		}
		System.exit(EXIT_ON_CLOSE);
	}

	/* (non-Javadoc)
	 * 
	 * @see java.awt.event.WindowListener#windowDeactivated(java.awt.event.WindowEvent) */
	@Override
	public void windowDeactivated(WindowEvent arg0) {
	}

	/* (non-Javadoc)
	 * 
	 * @see java.awt.event.WindowListener#windowDeiconified(java.awt.event.WindowEvent) */
	@Override
	public void windowDeiconified(WindowEvent arg0) {
	}

	/* (non-Javadoc)
	 * 
	 * @see java.awt.event.WindowListener#windowIconified(java.awt.event.WindowEvent) */
	@Override
	public void windowIconified(WindowEvent arg0) {
	}

	/* (non-Javadoc)
	 * 
	 * @see java.awt.event.WindowListener#windowOpened(java.awt.event.WindowEvent) */
	@Override
	public void windowOpened(WindowEvent arg0) {
	}
}