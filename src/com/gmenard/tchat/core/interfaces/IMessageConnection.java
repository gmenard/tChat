package com.gmenard.tchat.core.interfaces;

import java.io.*;

import com.gmenard.tchat.core.net.Message;

/**
 * Interface for managing network activities.
 * 
 * @author Guillaume
 */
public interface IMessageConnection {

	/**
	 * Receive a message.
	 * 
	 * @return message
	 * @throws IOException if retrieving message failed
	 */
	public Message getMessage() throws IOException;

	/**
	 * Send a message.
	 * 
	 * @param msg message
	 * @throws IOException if sending message failed
	 */
	public void sendMessage(Message msg) throws IOException;
}
