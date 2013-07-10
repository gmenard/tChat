package com.gmenard.tchat.client.controllers;

import java.awt.event.*;
import java.net.*;
import javax.swing.*;

import com.gmenard.tchat.TchatConstants;
import com.gmenard.tchat.client.ui.ServerConnectionUI;
import com.gmenard.tchat.core.client.*;

/**
 * This class manages user interactions of the server connection popup.
 * 
 * @author Guillaume
 */
@SuppressWarnings("serial")
public class ServerConnectionController extends ServerConnectionUI implements ActionListener {

	/** Server port. */
	private String port;
	/** Server address. */
	private String address;
	/** Manager for topics. */
	private ClientTopicsManager clientTopicsManager;

	public ServerConnectionController(JFrame frame, boolean modal) {
		super(frame, modal);
		clientTopicsManager = new ClientTopicsManager();
		cancelButton.addActionListener(this);
		connectButton.addActionListener(this);
		/* Set default server values */
		port = String.valueOf(TchatConstants.DEFAULT_PORT);
		address = TchatConstants.DEFAULT_ADDRESS;
		INetAddressField.setText(address);
		portField.setText(port);
	}

	/* (non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent) */
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == connectButton) {
			address = INetAddressField.getText();
			port = portField.getText();
			try {
				clientTopicsManager.setServer(InetAddress.getByName(address), Integer.parseInt(port));
			} catch (NumberFormatException e1) {
				e1.printStackTrace();
			} catch (UnknownHostException e1) {
				e1.printStackTrace();
			}
			clientTopicsManager.connect();
			this.setVisible(false);
		} else if (event.getSource() == cancelButton) {
			this.setVisible(false);
		}
	}

	public ClientTopicsManager getClientTopicsManager() {
		return clientTopicsManager;
	}

	public void setClientTopicsManager(ClientTopicsManager topicsManager) {
		this.clientTopicsManager = topicsManager;
	}
}
