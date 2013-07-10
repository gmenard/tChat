package com.gmenard.tchat.client.ui;

import java.awt.*;
import javax.swing.*;

import com.gmenard.tchat.TchatConstants;

/**
 * This class defines the popup's GUI allowing user to join a chat room topic.
 * 
 * @author Guillaume
 */
@SuppressWarnings("serial")
public class JoinTopicUI extends JDialog {

	/** Close popup button. */
	protected JButton cancelButton;
	/** Join topic button. */
	protected JButton joinButton;
	/** Input allowing user to create new topic. */
	protected JTextField createTopicTextField;
	/** List of all topics. */
	protected JComboBox<String> listTopicsComboBox;

	/* GUI labels */
	protected JLabel topicsLabel;
	protected JLabel newTopicLabel;

	public JoinTopicUI(JFrame f, boolean modal) {
		super(f, modal);
		listTopicsComboBox = new JComboBox<String>();
		topicsLabel = new JLabel();
		newTopicLabel = new JLabel();
		createTopicTextField = new JTextField();
		cancelButton = new JButton();
		joinButton = new JButton();

		this.initComponents();

		joinButton.setText(TchatConstants.JOIN_TOPIC_BUTTON_TEXT);
		cancelButton.setText(TchatConstants.CANCEL_BUTTON_TEXT);
		topicsLabel.setText(TchatConstants.TOPICS_LABEL);
		newTopicLabel.setText(TchatConstants.NEW_TOPIC_LABEL);

		this.setTitle(TchatConstants.JOIN_TOPIC_POPUP_TITLE);
		this.setSize(TchatConstants.APPLICATION_POPUP_WIDTH, TchatConstants.APPLICATION_POPUP_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
	}

	private void initComponents() {

		joinButton.setPreferredSize(new Dimension(70, 25));
		cancelButton.setPreferredSize(new Dimension(70, 25));

		BorderLayout layout = new BorderLayout();
		JPanel container = new JPanel();
		container.setLayout(layout);

		Box hBox1 = Box.createHorizontalBox();
		Box hBox2 = Box.createHorizontalBox();
		Box hBox3 = Box.createHorizontalBox();

		hBox1.add(Box.createHorizontalStrut(15));
		hBox1.add(topicsLabel);
		hBox1.add(Box.createHorizontalStrut(5));
		hBox1.add(listTopicsComboBox);
		hBox1.add(Box.createHorizontalStrut(15));

		hBox3.add(Box.createHorizontalStrut(15));
		hBox3.add(newTopicLabel);
		hBox3.add(Box.createHorizontalStrut(5));
		hBox3.add(createTopicTextField);
		hBox3.add(Box.createHorizontalStrut(15));

		hBox2.add(Box.createHorizontalStrut(15));
		hBox2.add(Box.createHorizontalStrut(5));
		hBox2.add(joinButton);
		hBox2.add(Box.createHorizontalStrut(5));
		hBox2.add(cancelButton);
		hBox2.add(Box.createHorizontalStrut(15));

		Box vBox = Box.createVerticalBox();
		vBox.add(Box.createVerticalStrut(15));
		vBox.add(hBox1);
		vBox.add(Box.createVerticalStrut(10));
		vBox.add(hBox3);
		vBox.add(Box.createVerticalStrut(10));
		vBox.add(hBox2);
		vBox.add(Box.createVerticalStrut(15));

		container.add(vBox, BorderLayout.CENTER);

		this.setContentPane(container);
	}
}