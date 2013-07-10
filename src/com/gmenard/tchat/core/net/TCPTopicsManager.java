package com.gmenard.tchat.core.net;

import java.util.Hashtable;

import com.gmenard.tchat.core.interfaces.IChatRoom;
import com.gmenard.tchat.core.server.ServerChatRoom;
import com.gmenard.tchat.core.text.TextTopicsManager;

/**
 * This class defines the chatroom/topic association.
 * 
 * @author Guillaume
 */
public class TCPTopicsManager extends TextTopicsManager {

	/** Next port available for a potential new topic. */
	private static int nextPort;

	public TCPTopicsManager(int port) {
		nextPort = port;
		roomsList = new Hashtable<String, IChatRoom>();
	}

	/* (non-Javadoc)
	 * 
	 * @see com.gmenard.tchat.core.impl.text.TextTopicsManager#createTopic(java.lang.String) */
	@Override
	public void createTopic(String topic) {
		ServerChatRoom serverChatroom = new ServerChatRoom(topic);
		// Increments the port for the new topic to create
		nextPort++;
		// Starts the topic
		serverChatroom.startServer(nextPort);
		// Add the topic in the list of topics
		roomsList.put(topic, serverChatroom);
	}
}