package com.gmenard.tchat.core.net;

/**
 * Headers defining the type of a message.
 * 
 * @author Guillaume
 */
public enum Header {
	AUTHENTIFICATION,
	CREATE_TOPIC,
	DEBUG,
	ACK_ERROR,
	JOIN_TOPIC,
	JOIN_TOPIC_REPLY,
	LIST_TOPICS,
	LIST_TOPICS_REPLY,
	LIST_USERS,
	POST,
	QUIT,
	RECEIVE_MESSAGE,
	USER_AUTHENTIFIED,
	USER_OK,
	USER_UNKNOWN,
	WRONG_PASSWORD
}
