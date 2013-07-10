package com.gmenard.tchat.core.client;

import java.io.IOException;
import java.util.Vector;

import com.gmenard.tchat.TchatConstants;
import com.gmenard.tchat.authentication.exceptions.UserUnknownException;
import com.gmenard.tchat.authentication.exceptions.WrongPasswordException;
import com.gmenard.tchat.core.interfaces.IChatRoom;
import com.gmenard.tchat.core.interfaces.ITopicsManager;
import com.gmenard.tchat.core.net.Header;
import com.gmenard.tchat.core.net.Message;
import com.gmenard.tchat.core.net.TCPClient;

/**
 * This class implements the management of topics on the client side.
 * 
 * @author Guillaume
 */
public class ClientTopicsManager extends TCPClient implements ITopicsManager {

	public ClientTopicsManager() {
		isClientConnected = false;
	}

	/**
	 * Authenticate a user.
	 * 
	 * @param login user's login
	 * @param password password's login
	 * @throws UserUnknownException if user is unknown in base
	 * @throws WrongPasswordException if password is wrong
	 */
	public void authentify(String login, String password) throws UserUnknownException, WrongPasswordException {
		try {
			Vector<String> request = new Vector<String>();
			request.add(login);
			request.add(password);

			sendMessage(new Message(Header.AUTHENTIFICATION, request));

			Message msg = getMessage();
			switch (msg.getHead()) {
				case USER_UNKNOWN:
					throw new UserUnknownException(login);
				case WRONG_PASSWORD:
					throw new WrongPasswordException(login);
				case USER_OK:
					break;
				default:
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * 
	 * @see com.gmenard.tchat.core.interfaces.ITopicsManager#createTopic(java.lang.String) */
	@Override
	public void createTopic(String topic) {
		try {
			Message request = new Message(Header.CREATE_TOPIC, topic);
			sendMessage(request);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @return list of chatters in the chatroom
	 */
	public Vector<String> getUsers() {
		try {
			sendMessage(new Message(Header.LIST_USERS, "List of Users"));
			Message msg = getMessage();
			if (msg.getHead().equals(Header.LIST_USERS)) {
				return msg.getData();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new Vector<String>();
	}

	/* (non-Javadoc)
	 * 
	 * @see com.gmenard.tchat.core.interfaces.ITopicsManager#joinTopic(java.lang.String) */
	@Override
	public IChatRoom joinTopic(String topic) {
		try {
			// Check if topic exists
			boolean topicExists = false;
			for (String topic1 : listTopics()) {
				if (topic.equals(topic1)) {
					topicExists = true;
				}
			}

			// If topic does not exist
			if (!topicExists) {
				// create it
				sendMessage(new Message(Header.CREATE_TOPIC, topic));
			}

			// join topic
			sendMessage(new Message(Header.JOIN_TOPIC, topic));

			Message rcv_msg = getMessage();

			// join linked ChatRoom
			int port = Integer.parseInt(rcv_msg.getData().firstElement());
			ClientChatRoom javaCRproxy = new ClientChatRoom(topic);
			javaCRproxy.setServer(this.address, port);
			javaCRproxy.connect();

			System.out.println("You are joining the topic " + javaCRproxy.getTopic());

			return javaCRproxy;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	/* (non-Javadoc)
	 * 
	 * @see com.gmenard.tchat.core.interfaces.ITopicsManager#listTopics() */
	@Override
	public Vector<String> listTopics() {
		try {
			Message request = new Message(Header.LIST_TOPICS, TchatConstants.LIST_TOPICS_DATA);
			sendMessage(request);

			Message reply = getMessage();
			if (Header.LIST_TOPICS_REPLY == reply.getHead()) {
				return reply.getData();
			}
		} catch (IOException e) {
			e.printStackTrace();
			Vector<String> error = new Vector<String>();
			error.add("Error at listTopics() (ClientTopicsManager)");
			return error;
		}
		return null;
	}

}