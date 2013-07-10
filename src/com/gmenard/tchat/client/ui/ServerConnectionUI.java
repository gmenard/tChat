package com.gmenard.tchat.client.ui;

import java.awt.*;
import javax.swing.*;

import com.gmenard.tchat.TchatConstants;

/**
 * This class defines the popup's GUI allowing user to set server settings and connect.
 * 
 * @author Guillaume
 */
@SuppressWarnings("serial")
public class ServerConnectionUI extends JDialog {

	/** Close popup button. */
	protected JButton cancelButton;
	/** Connect to server button. */
	protected JButton connectButton;
	/** Input containing the server address. */
	protected JTextField INetAddressField;
	/** Input containing the server port */
	protected JTextField portField;

	/* GUI labels */
	protected JLabel portLabel;
	protected JLabel addressLabel;

	public ServerConnectionUI(JFrame f, boolean modal) {
		super(f, modal);
		INetAddressField = new JTextField();
		portField = new JTextField();
		portLabel = new JLabel();
		addressLabel = new JLabel();
		connectButton = new JButton();
		cancelButton = new JButton();

		this.initComponents();

		cancelButton.setText(TchatConstants.CANCEL_BUTTON_TEXT);
		connectButton.setText(TchatConstants.CONNECT_BUTTON_TEXT);
		portLabel = new JLabel(TchatConstants.PORT_LABEL);
		addressLabel = new JLabel(TchatConstants.ADDRESS_LABEL);

		this.setTitle(TchatConstants.SERVER_CONNECTION_POPUP_TITLE);
		this.setSize(TchatConstants.APPLICATION_POPUP_WIDTH, TchatConstants.APPLICATION_POPUP_HEIGHT);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setDefaultCloseOperation(JDialog.HIDE_ON_CLOSE);
	}

	private void initComponents() {

		cancelButton.setPreferredSize(new Dimension(70, 25));
		connectButton.setPreferredSize(new Dimension(70, 25));

		BorderLayout layout = new BorderLayout();
		JPanel container = new JPanel();
		container.setLayout(layout);

		Box hBox1 = Box.createHorizontalBox();
		Box hBox2 = Box.createHorizontalBox();
		Box hBox3 = Box.createHorizontalBox();

		hBox1.add(Box.createHorizontalStrut(15));
		hBox1.add(addressLabel);
		hBox1.add(Box.createHorizontalStrut(5));
		hBox1.add(INetAddressField);
		hBox1.add(Box.createHorizontalStrut(15));

		hBox2.add(Box.createHorizontalStrut(15));
		hBox2.add(portLabel);
		hBox2.add(Box.createHorizontalStrut(5));
		hBox2.add(portField);
		hBox2.add(Box.createHorizontalStrut(15));

		hBox3.add(Box.createHorizontalStrut(15));
		hBox3.add(connectButton);
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
	}
}
