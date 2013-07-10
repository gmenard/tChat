package com.gmenard.tchat;

import javax.swing.UnsupportedLookAndFeelException;

import com.gmenard.tchat.client.ui.HomeUI;

/**
 * Client entry point.
 * 
 * @author Guillaume
 */
public class Client {

	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		new HomeUI().setVisible(true);
	}
}
