package com.gmenard.tchat;

import com.gmenard.tchat.TchatConstants;
import com.gmenard.tchat.authentication.impl.Authentication;
import com.gmenard.tchat.authentication.interfaces.IAuthentication;
import com.gmenard.tchat.core.net.TCPTopicsManager;
import com.gmenard.tchat.core.server.ServerTopicsManager;

/**
 * Server entry point.
 * 
 * @author Guillaume
 */
public class Server {

	public static void main(String[] args) {
		IAuthentication auth = new Authentication();
		new ServerTopicsManager(new TCPTopicsManager(TchatConstants.DEFAULT_PORT), auth);
	}
}
