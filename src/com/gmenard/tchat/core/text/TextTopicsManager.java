package com.gmenard.tchat.core.text;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import com.gmenard.tchat.core.interfaces.IChatRoom;
import com.gmenard.tchat.core.interfaces.ITopicsManager;

/**
 * This class allows to manage associations between chatrooms and topics.
 * 
 * @author Guillaume
 */
public class TextTopicsManager implements ITopicsManager {

	/** Hashtable with keys as topics and values as chatroom. */
	protected Hashtable<String, IChatRoom> roomsList;

	public TextTopicsManager() {
		this.roomsList = new Hashtable<String, IChatRoom>();
	}

	/* (non-Javadoc)
	 * 
	 * @see com.gmenard.tchat.core.interfaces.ITopicsManager#createTopic(java.lang.String) */
	@Override
	public void createTopic(String topic) {
		roomsList.put(topic, new TextChatRoom(topic));
	}

	/* (non-Javadoc)
	 * 
	 * @see com.gmenard.tchat.core.interfaces.ITopicsManager#joinTopic(java.lang.String) */
	@Override
	public IChatRoom joinTopic(String topic) {
		IChatRoom chatRoom = (IChatRoom) roomsList.get(topic);
		if (null == chatRoom) {
			createTopic(topic);
			chatRoom = (IChatRoom) roomsList.get(topic);
		}

		return chatRoom;
	}

	/* (non-Javadoc)
	 * 
	 * @see com.gmenard.tchat.core.interfaces.ITopicsManager#listTopics() */
	@Override
	public Vector<String> listTopics() {
		Vector<String> List = new Vector<String>();
		Enumeration<String> Enum = roomsList.keys();

		System.out.println("The opened topics are :");

		while (Enum.hasMoreElements()) {
			String tmp = Enum.nextElement();
			System.out.println(tmp);
			List.add(tmp);
		}

		return List;
	}
}
