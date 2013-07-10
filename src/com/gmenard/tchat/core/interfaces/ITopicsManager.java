package com.gmenard.tchat.core.interfaces;

import java.util.Vector;

/**
 * Interface for managing chat room topics.
 * 
 * @author Guillaume
 */
public interface ITopicsManager {

	/**
	 * Retrieve the list of all topics.
	 * 
	 * @return vector of topics
	 */
	public Vector<String> listTopics();

	/**
	 * Return a chat room linked to a topic.
	 * 
	 * @param topic chat room topic name
	 * @return chat room
	 */
	public IChatRoom joinTopic(String topic);

	/**
	 * Create a chat room for a new topic.
	 * 
	 * @param topic new topic name
	 */
	public void createTopic(String topic);

}
