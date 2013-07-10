package com.gmenard.tchat.core.client;

import java.io.IOException;
import java.util.Vector;

import com.gmenard.tchat.core.interfaces.IChatRoom;
import com.gmenard.tchat.core.interfaces.IChatter;
import com.gmenard.tchat.core.net.Header;
import com.gmenard.tchat.core.net.Message;
import com.gmenard.tchat.core.net.TCPClient;
import com.gmenard.tchat.core.text.TextChatter;

/**
 * This class implements a chat room behavior on the client side.
 * 
 * @author Guillaume
 */
public class ClientChatRoom extends TCPClient implements IChatRoom, Runnable {

	/** Chatter authenticated as the client user. */
	private IChatter clientChatter;
	/** Current topic selected by client user. */
	private String topic;
	/** Is user connected to the chat room ? */
	private Boolean doRun;

	public ClientChatRoom(String topic) {
		this.topic = topic;
		this.doRun = true;
		this.isClientConnected = false;
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
	 * @see
	 * com.gmenard.tchat.core.interfaces.IChatRoom#join(com.gmenard.tchat.core.interfaces.IChatter) */
	@Override
	public void join(IChatter chatter) {
		try {
			this.clientChatter = chatter;
			sendMessage(new Message(Header.JOIN_TOPIC, chatter.getAlias()));
			new Thread(this).start();
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * 
	 * @see com.gmenard.tchat.core.interfaces.IChatRoom#post(java.lang.String,
	 * com.gmenard.tchat.core.interfaces.IChatter) */
	@Override
	public void post(String msg, IChatter chatter) {
		try {
			Vector<String> data = new Vector<String>();
			data.add(msg);
			data.add(chatter.getAlias());
			sendMessage(new Message(Header.POST, data));
		} catch (Exception e) {
			System.err.println(e);
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * 
	 * @see
	 * com.gmenard.tchat.core.interfaces.IChatRoom#quit(com.gmenard.tchat.core.interfaces.IChatter) */
	@Override
	public void quit(IChatter chatter) {
		try {
			Vector<String> data = new Vector<String>();
			data.add(chatter.getAlias());
			Message msg = new Message(Header.QUIT, data);
			sendMessage(msg);
			doRun = false;
			isClientConnected = false;
			this.disconnect();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run() */
	@Override
	public void run() {
		while (doRun) {
			Message msg;
			try {
				// Client receives an event
				msg = getMessage();
				// Case receive message
				if (Header.RECEIVE_MESSAGE == msg.getHead()) {
					String message = msg.getData().firstElement();
					String alias = msg.getData().lastElement();
					TextChatter sendingChatter = new TextChatter(alias);
					clientChatter.receiveAMessage(message, sendingChatter);
				}
				// Case refresh chatroom list of users
				else if (Header.LIST_USERS == msg.getHead()) {
					sendMessage(new Message(Header.LIST_USERS, msg.getData()));
				} else {
					clientChatter.receiveAMessage(msg.getData().firstElement(), new TextChatter(msg.getData().lastElement()));
				}
			} catch (Exception e) {
				System.err.println(e);
				e.printStackTrace();
			}
		}
	}

	/* (non-Javadoc)
	 * 
	 * @see com.gmenard.tchat.core.interfaces.IChatRoom#getParticipants() */
	@Override
	public Vector<String> getParticipants() {
		// TODO Auto-generated method stub
		return null;
	}
}
