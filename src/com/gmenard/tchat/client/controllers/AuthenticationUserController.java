package com.gmenard.tchat.client.controllers;

import java.awt.event.*;
import java.util.*;

import javax.swing.*;

import com.gmenard.tchat.client.ui.AuthenticationUserUI;

/**
 * This class manages user interactions of the authentication popup.
 * 
 * @author Guillaume
 */
@SuppressWarnings("serial")
public class AuthenticationUserController extends AuthenticationUserUI implements ActionListener {

	/** Map containing the user selected "Login" and "Password". */
	private Map<String, String> user;

	public AuthenticationUserController(JFrame f, boolean modal) {
		super(f, modal);
		user = new HashMap<String, String>();
		logOnButton.addActionListener(this);
		cancelButton.addActionListener(this);
	}

	/* (non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent) */
	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getSource() == logOnButton) {
			user.put("Login", loginField.getText());
			user.put("Password", new String(passwordField.getPassword()));
		} else if (event.getSource() == cancelButton) {
			user.put("Login", null);
			user.put("Password", null);
		}
		this.setVisible(false);
	}

	/**
	 * @return a map containing the user "Login" and "Password"
	 */
	public Map<String, String> getUser() {
		return user;
	}
}