package com.gmenard.tchat.core.text;

import com.gmenard.tchat.core.interfaces.IChatter;

/**
 * This class implements a chatter.
 * 
 * @author Guillaume
 */
public class TextChatter implements IChatter {

	/** Chatter pseudonym. */
	private String pseudo;

	public TextChatter(String pseudo) {
		this.pseudo = pseudo;
	}

	/* (non-Javadoc)
	 * 
	 * @see com.gmenard.tchat.core.interfaces.IChatter#receiveAMessage(java.lang.String,
	 * com.gmenard.tchat.core.interfaces.IChatter) */
	@Override
	public void receiveAMessage(String msg, IChatter chatter) {
		System.out.println("(To " + pseudo + ") : " + chatter.getAlias() + " : " + msg);
	}

	/* (non-Javadoc)
	 * 
	 * @see com.gmenard.tchat.core.interfaces.IChatter#getAlias() */
	@Override
	public String getAlias() {
		return this.pseudo;
	}

}
