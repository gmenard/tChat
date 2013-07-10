package com.gmenard.tchat.core.text;

import java.util.Vector;

import com.gmenard.tchat.core.interfaces.IChatRoom;
import com.gmenard.tchat.core.interfaces.IChatter;

/**
 * Implementation of a chat room.
 * 
 * @author Guillaume
 */
public class TextChatRoom implements IChatRoom {

	/** Chat room topic. */
	private String topic;
	/** List of chatters in the topic. */
	private Vector<IChatter> chattersList;

	public TextChatRoom(String topic) {
		this.topic = topic;
		chattersList = new Vector<IChatter>();
	}

	/* (non-Javadoc)
	 * 
	 * @see com.gmenard.tchat.core.interfaces.IChatRoom#post(java.lang.String,
	 * com.gmenard.tchat.core.interfaces.IChatter) */
	@Override
	public void post(String msg, IChatter chatter) {
		/* Sends the message to all chatters of the chatroom */
		for (int i = 0; i < chattersList.size(); i++) {
			this.chattersList.get(i).receiveAMessage(msg, chatter);
		}
	}

	/* (non-Javadoc)
	 * 
	 * @see
	 * com.gmenard.tchat.core.interfaces.IChatRoom#quit(com.gmenard.tchat.core.interfaces.IChatter) */
	@Override
	public void quit(IChatter chatter) {
		/* Removes the user of the chatters list */
		this.chattersList.remove(chatter);

		/* Posts the event into the chat room */
		post("(Message from ChatRoom: " + this.topic + ") " + chatter.getAlias() + " has left the room.", new TextChatter("ChatRoom"));
	}

	/* (non-Javadoc)
	 * 
	 * @see
	 * com.gmenard.tchat.core.interfaces.IChatRoom#join(com.gmenard.tchat.core.interfaces.IChatter) */
	@Override
	public void join(IChatter chatter) {
		/* Add user in the chatters list */
		this.chattersList.add(chatter);
		/* Posts the event into the chat room */
		post("(Message from ChatRoom: " + topic + ") " + chatter.getAlias() + " has joined the room.", new TextChatter("ChatRoom"));
	}

	/* (non-Javadoc)
	 * 
	 * @see com.gmenard.tchat.core.interfaces.IChatRoom#getTopic() */
	@Override
	public String getTopic() {
		return this.topic;
	}

	/* (non-Javadoc)
	 * 
	 * @see com.gmenard.tchat.core.interfaces.IChatRoom#getParticipants() */
	@Override
	public Vector<String> getParticipants() {
		Vector<String> participants = new Vector<String>();
		// For each chatters in the chatroom
		for (IChatter chatter : chattersList) {
			// save the pseudo of the chatter
			participants.add(chatter.getAlias());
		}
		return participants;
	}
}
