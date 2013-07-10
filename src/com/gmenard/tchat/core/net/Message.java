package com.gmenard.tchat.core.net;

import java.io.Serializable;
import java.util.*;

/**
 * Structure of a message.
 * 
 * @author Guillaume
 */
public class Message implements Serializable {

	private static final long serialVersionUID = 6099003136017616775L;
	/** Message body. */
	private Vector<String> data;
	/** Message header. */
	private Header head;

	public Message(Header header, String message) {
		this.head = header;
		this.data = new Vector<String>();
		this.data.add(message);
	}

	public Message(Header head, Vector<String> data) {
		this.head = head;
		this.data = data;
	}

	public Message(String data) {
		this.head = Header.DEBUG;
		this.data = new Vector<String>();
		this.data.add(data);
	}

	public Message(Vector<String> data) {
		this.head = Header.DEBUG;
		this.data = data;
	}

	public Vector<String> getData() {
		return data;
	}

	public Header getHead() {
		return head;
	}

	public String toString() {
		return "Header : " + head + " -- Data : " + data;
	}
}
