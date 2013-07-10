package com.gmenard.tchat.core.server;

import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

import com.gmenard.tchat.TchatConstants;
import com.gmenard.tchat.authentication.exceptions.UserExistsException;
import com.gmenard.tchat.authentication.exceptions.UserUnknownException;
import com.gmenard.tchat.authentication.exceptions.WrongPasswordException;
import com.gmenard.tchat.authentication.interfaces.IAuthentication;
import com.gmenard.tchat.core.interfaces.IChatRoom;
import com.gmenard.tchat.core.interfaces.ITopicsManager;
import com.gmenard.tchat.core.net.Header;
import com.gmenard.tchat.core.net.Message;
import com.gmenard.tchat.core.net.TCPServer;
import com.gmenard.tchat.core.net.TCPTopicsManager;

/**
 * This class implements the management of topics on the server side.
 * 
 * @author Guillaume
 */
public class ServerTopicsManager extends TCPServer implements ITopicsManager {

	/** Object for authenticating users. */
	private IAuthentication authentication;
	/** TCP manager of topics. */
	private TCPTopicsManager tcpTopicsManager;

	public ServerTopicsManager(TCPTopicsManager topicsManager) {
		tcpTopicsManager = topicsManager;
		modeTreatClient = true;
		this.startServer(TchatConstants.DEFAULT_PORT);
	}

	public ServerTopicsManager(TCPTopicsManager topicsManager, IAuthentication authentication) {
		this.tcpTopicsManager = topicsManager;
		this.modeTreatClient = true;
		this.startServer(TchatConstants.DEFAULT_PORT);
		this.authentication = authentication;
	}

	/* (non-Javadoc)
	 * 
	 * @see com.gmenard.tchat.core.interfaces.ITopicsManager#createTopic(java.lang.String) */
	@Override
	public void createTopic(String topic) {
		tcpTopicsManager.createTopic(topic);

	}

	/* (non-Javadoc)
	 * 
	 * @see com.gmenard.tchat.core.interfaces.ITopicsManager#joinTopic(java.lang.String) */
	@Override
	public IChatRoom joinTopic(String topic) {
		return tcpTopicsManager.joinTopic(topic);
	}

	/* (non-Javadoc)
	 * 
	 * @see com.gmenard.tchat.core.interfaces.ITopicsManager#listTopics() */
	@Override
	public Vector<String> listTopics() {
		return tcpTopicsManager.listTopics();
	}

	/* (non-Javadoc)
	 * 
	 * @see com.gmenard.tchat.core.impl.server.TCPServer#manageClient(java.net.Socket) */
	@Override
	public void manageClient(Socket socket) {
		try {
			while (modeTreatClient) {
				Message receiptmsg = getMessage();

				switch (receiptmsg.getHead()) {
					case CREATE_TOPIC:
						tcpTopicsManager.createTopic(receiptmsg.getData().firstElement());
						break;
					case JOIN_TOPIC:
						IChatRoom cr = tcpTopicsManager.joinTopic(receiptmsg.getData().firstElement());
						sendMessage(new Message(Header.JOIN_TOPIC_REPLY, Integer.toString(((ServerChatRoom) cr).getPort())));
						break;
					case AUTHENTIFICATION:
						try {
							authentication.authentify(receiptmsg.getData().firstElement(), receiptmsg.getData().lastElement());
						} catch (UserUnknownException e) {
							try {
								authentication.addUser(receiptmsg.getData().firstElement(), receiptmsg.getData().lastElement());
								authentication.save(TchatConstants.BASE_LOGINS);
								sendMessage(new Message(Header.USER_AUTHENTIFIED, ""));
								break;
							} catch (UserExistsException oe) {
								sendMessage(new Message(Header.USER_UNKNOWN, ""));
								break;
							} catch (IOException oe) {
								sendMessage(new Message(Header.USER_UNKNOWN, ""));
								break;
							}

						} catch (WrongPasswordException e) {
							sendMessage(new Message(Header.WRONG_PASSWORD, ""));
							break;
						}
						sendMessage(new Message(Header.USER_AUTHENTIFIED, ""));
						break;
					case LIST_TOPICS:
						sendMessage(new Message(Header.LIST_TOPICS_REPLY, tcpTopicsManager.listTopics()));
						break;
					case QUIT:
						modeTreatClient = false;
						break;
					default:
						break;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
