package com.gmenard.tchat.client.controllers;

import java.awt.event.*;
import javax.swing.*;

import com.gmenard.tchat.TchatConstants;
import com.gmenard.tchat.client.ui.JoinTopicUI;
import com.gmenard.tchat.core.client.*;

/**
 * This class manages user interactions of the popup for joining a topic.
 * 
 * @author Guillaume
 */
@SuppressWarnings("serial")
public class JoinTopicController extends JoinTopicUI implements ActionListener, ItemListener {

	/** Selected topic. */
	private String selectedTopic;
	/** Topics manager. */
	private ClientTopicsManager clientTopicsManager;

	public JoinTopicController(JFrame frame, boolean modal) {
		super(frame, modal);
		clientTopicsManager = new ClientTopicsManager();
		listTopicsComboBox.addItemListener(this);
		joinButton.addActionListener(this);
		cancelButton.addActionListener(this);
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == joinButton) {
			if (selectedTopic.equals(TchatConstants.AVAILABLE_TOPICS_DATA)) {
				selectedTopic = createTopicTextField.getText();
			}
			this.setVisible(false);
		} else if (event.getSource() == cancelButton) {
			selectedTopic = "";
			this.setVisible(false);
		}
	}

	public String getTopic() {
		return selectedTopic;
	}

	/* (non-Javadoc)
	 * @see java.awt.event.ItemListener#itemStateChanged(java.awt.event.ItemEvent)
	 */
	@Override
	public void itemStateChanged(ItemEvent e) {
		selectedTopic = (String) e.getItem();
		if (selectedTopic.equals(TchatConstants.AVAILABLE_TOPICS_DATA)) {
			createTopicTextField.setEditable(true);
		} else {
			createTopicTextField.setEditable(false);
		}
	}

	/**
	 * Set the list of available topics.
	 * 
	 * @param topicsManager topics manager
	 */
	public void setAvailableTopics(ClientTopicsManager topicsManager) {
		listTopicsComboBox.removeAllItems();
		clientTopicsManager = topicsManager;
		listTopicsComboBox.addItem(TchatConstants.AVAILABLE_TOPICS_DATA);
		for (String s : topicsManager.listTopics()) {
			listTopicsComboBox.addItem(s);
		}
	}
}