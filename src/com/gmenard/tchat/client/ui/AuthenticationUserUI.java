package com.gmenard.tchat.client.ui;

import java.awt.*;

import javax.swing.*;

import com.gmenard.tchat.TchatConstants;

/**
 * This class defines the popup's GUI for authenticating users.
 * 
 * @author Guillaume
 */
@SuppressWarnings("serial")
public class AuthenticationUserUI extends JDialog {

	/** Close popup button. */
	protected JButton cancelButton;
	/** Authentication button. */
	protected JButton logOnButton;
	/** Input containing user's login. */
	protected JTextField loginField;
	/** Input containing user's password. */
	protected JPasswordField passwordField;

	/* GUI Labels */
	protected JLabel loginLabel;
	protected JLabel passlabel;

	public AuthenticationUserUI(JFrame f, boolean modal) {
		super(f, modal);
		loginLabel = new JLabel();
		loginField = new JTextField();
		passlabel = new JLabel();
		passwordField = new JPasswordField();
		logOnButton = new JButton();
		cancelButton = new JButton();

		this.initComponent();

		this.setTitle(TchatConstants.AUTHENTICATION_POPUP_TITLE);
		logOnButton.setText(TchatConstants.LOG_ON_BUTTON_TEXT);
		cancelButton.setText(TchatConstants.CANCEL_BUTTON_TEXT);
		loginLabel.setText(TchatConstants.LOGIN_LABEL);
		passlabel.setText(TchatConstants.PASSWORD_LABEL);
		passwordField.setEchoChar(TchatConstants.PASSWORD_HIDDEN_CHAR);

	}

	private void initComponent() {

		logOnButton.setPreferredSize(new Dimension(70, 50));
		cancelButton.setPreferredSize(new Dimension(70, 50));

		BorderLayout layout = new BorderLayout();
		JPanel container = new JPanel();
		container.setLayout(layout);

		Box hBox1 = Box.createHorizontalBox();
		Box hBox2 = Box.createHorizontalBox();
		Box hBox3 = Box.createHorizontalBox();

		hBox1.add(Box.createHorizontalStrut(15));
		hBox1.add(loginLabel);
		hBox1.add(Box.createHorizontalStrut(5));
		hBox1.add(loginField);
		hBox1.add(Box.createHorizontalStrut(15));

		hBox2.add(Box.createHorizontalStrut(15));
		hBox2.add(passlabel);
		hBox2.add(Box.createHorizontalStrut(5));
		hBox2.add(passwordField);
		hBox2.add(Box.createHorizontalStrut(15));

		hBox3.add(Box.createHorizontalStrut(15));
		hBox3.add(logOnButton);
		hBox3.add(Box.createHorizontalStrut(5));
		hBox3.add(cancelButton);
		hBox3.add(Box.createHorizontalStrut(15));

		Box vBox = Box.createVerticalBox();

		vBox.add(Box.createVerticalStrut(15));
		vBox.add(hBox1);
		vBox.add(Box.createVerticalStrut(10));
		vBox.add(hBox2);
		vBox.add(Box.createVerticalStrut(10));
		vBox.add(hBox3);
		vBox.add(Box.createVerticalStrut(15));

		container.add(vBox, BorderLayout.CENTER);

		this.setContentPane(container);
		this.setSize(TchatConstants.APPLICATION_POPUP_WIDTH, TchatConstants.APPLICATION_POPUP_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setResizable(true);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
	}
}