package com.gmenard.tchat.core.server;

import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

import com.gmenard.tchat.core.interfaces.IChatRoom;
import com.gmenard.tchat.core.interfaces.IChatter;
import com.gmenard.tchat.core.net.Header;
import com.gmenard.tchat.core.net.Message;
import com.gmenard.tchat.core.net.TCPServer;
import com.gmenard.tchat.core.text.TextChatRoom;

/**
 * This class implements a chat room behavior on the server side.
 * 
 * @author Guillaume
 */
public class ServerChatRoom extends TCPServer implements IChatter, IChatRoom {

	private TextChatRoom concretChatroom;
	private String pseudo;

	public ServerChatRoom(String t) {
		this.concretChatroom = new TextChatRoom(t);
		this.pseudo = "Default";
		this.modeTreatClient = true;
	}

	/* (non-Javadoc)
	 * 
	 * @see com.gmenard.tchat.core.interfaces.IChatRoom#getParticipants() */
	@Override
	public Vector<String> getParticipants() {
		// TODO
		return null;
	}

	/* (non-Javadoc)
	 * 
	 * @see com.gmenard.tchat.core.interfaces.IChatter#getAlias() */
	@Override
	public String getAlias() {
		return pseudo;
	}

	/* (non-Javadoc)
	 * 
	 * @see com.gmenard.tchat.core.interfaces.IChatRoom#getTopic() */
	@Override
	public String getTopic() {
		return concretChatroom.getTopic();
	}

	/* (non-Javadoc)
	 * 
	 * @see
	 * com.gmenard.tchat.core.interfaces.IChatRoom#join(com.gmenard.tchat.core.interfaces.IChatter) */
	@Override
	public void join(IChatter chatter) {
		concretChatroom.join(chatter);
	}

	/* (non-Javadoc)
	 * 
	 * @see com.gmenard.tchat.core.impl.server.TCPServer#manageClient(java.net.Socket) */
	@Override
	public void manageClient(Socket socket) {
		// While server is coupled with client
		while (modeTreatClient) {
			Message msg = null;
			try {
				msg = getMessage();
				// If client wants to join a topic
				if (msg.getHead() == Header.JOIN_TOPIC) {
					this.pseudo = msg.getData().firstElement();
					this.concretChatroom.join(this);
					// if client wants to post a message
				} else if (msg.getHead() == Header.POST) {
					String header = msg.getData().firstElement();
					concretChatroom.post(header, this);
					// if client wants to quit the topic
				} else if (msg.getHead() == Header.QUIT) {
					concretChatroom.quit(this);
					modeTreatClient = false;
				}
			} catch (Exception e) {
				System.err.println(e);
				e.printStackTrace();
			}
		}
	}

	/* (non-Javadoc)
	 * 
	 * @see com.gmenard.tchat.core.interfaces.IChatRoom#post(java.lang.String,
	 * com.gmenard.tchat.core.interfaces.IChatter) */
	@Override
	public void post(String msg, IChatter chatter) {
		concretChatroom.post(msg, chatter);
	}

	/* (non-Javadoc)
	 * 
	 * @see
	 * com.gmenard.tchat.core.interfaces.IChatRoom#quit(com.gmenard.tchat.core.interfaces.IChatter) */
	@Override
	public void quit(IChatter chatter) {
		concretChatroom.quit(chatter);
	}

	/* (non-Javadoc)
	 * 
	 * @see com.gmenard.tchat.core.interfaces.IChatter#receiveAMessage(java.lang.String,
	 * com.gmenard.tchat.core.interfaces.IChatter) */
	@Override
	public void receiveAMessage(String msg, IChatter chatter) {
		Vector<String> data = new Vector<String>();
		data.add(msg);
		data.add(chatter.getAlias());
		Message message = new Message(Header.RECEIVE_MESSAGE, data);
		try {
			sendMessage(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}