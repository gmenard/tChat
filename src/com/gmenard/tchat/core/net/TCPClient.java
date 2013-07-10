package com.gmenard.tchat.core.net;

import java.net.*;
import java.io.*;

import com.gmenard.tchat.core.interfaces.IMessageConnection;

/**
 * This class manages the network activity on client side.
 * 
 * @author Guillaume
 */
public class TCPClient implements IMessageConnection {

	private int port;
	protected InetAddress address;
	private Socket socket;

	protected boolean isClientConnected;

	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;

	/**
	 * Connect to server.
	 */
	public void connect() {
		try {
			socket = new Socket(address, port);
			inputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			outputStream = new ObjectOutputStream(socket.getOutputStream());
			isClientConnected = true;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Disconnect from server.
	 */
	public void disconnect() {
		try {
			sendMessage(new Message(Header.QUIT, ""));
			outputStream.close();
			// Close the socket
			socket.close();
			isClientConnected = false;
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retrieve a message.
	 */
	public Message getMessage() throws IOException {
		Message msg = null;
		try {
			msg = (Message) inputStream.readObject();
			return msg;
		} catch (ClassNotFoundException e) {
			throw new IOException();
		}
	}

	/**
	 * @return is client connected to server ?
	 */
	public boolean isClientConnected() {
		return isClientConnected;
	}

	/**
	 * Send message.
	 */
	public void sendMessage(Message msg) throws IOException {
		outputStream.writeObject(msg);
	}

	/**
	 * Set server settings.
	 * 
	 * @param adr server address
	 * @param port server port
	 */
	public void setServer(InetAddress adr, int port) {
		this.address = adr;
		this.port = port;
	}
}
