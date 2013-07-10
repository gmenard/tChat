package com.gmenard.tchat.core.interfaces;

import java.util.Vector;

/**
 * Interface for a chat room.
 * 
 * @author Guillaume
 */
public interface IChatRoom {

	/**
	 * Post a message in the chat room.
	 * 
	 * @param message
	 * @param chatter
	 */
	public void post(String message, IChatter chatter);

	/**
	 * Remove a chatter from the chat room.
	 * 
	 * @param chatter
	 */
	public void quit(IChatter chatter);

	/**
	 * Add a chatter in the chat room.
	 * 
	 * @param chatter
	 */
	public void join(IChatter chatter);

	/**
	 * Get the topic assigned to the chat room.
	 * 
	 * @return topic name
	 */
	public String getTopic();

	/**
	 * Get the list of chat room participants.
	 * 
	 * @return
	 */
	public Vector<String> getParticipants();

}