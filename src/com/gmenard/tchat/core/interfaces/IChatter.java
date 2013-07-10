package com.gmenard.tchat.core.interfaces;

/**
 * Interface for a chatter.
 * 
 * @author Guillaume
 */
public interface IChatter {

	/**
	 * Chatter receive a message from another chatter.
	 * 
	 * @param msg message to receive
	 * @param chatter sender
	 */
	public void receiveAMessage(String msg, IChatter chatter);

	/**
	 * Get chatter alias.
	 * 
	 * @return alias name
	 */
	public String getAlias();

}