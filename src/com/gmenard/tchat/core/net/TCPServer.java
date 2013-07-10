package com.gmenard.tchat.core.net;

import java.net.*;
import java.io.*;

import com.gmenard.tchat.core.interfaces.IMessageConnection;

/**
 * This class manages the network activity on server side.
 * 
 * @author Guillaume
 */
public abstract class TCPServer implements Runnable, Cloneable, IMessageConnection {

	private Socket communicationSocket;
	private int port;
	private ServerSocket waitSocket;
	
	private ObjectInputStream inputStream;
	private ObjectOutputStream outputStream;
	
	protected boolean modeTreatClient;

	/**
	 * Manages the communication between the client and the server.
	 * 
	 * @param socket
	 */
	abstract public void manageClient(Socket socket);

	/* (non-Javadoc)
	 * 
	 * @see com.gmenard.tchat.core.interfaces.IMessageConnection#getMessage() */
	@Override
	public Message getMessage() throws IOException {
		Message msg = null;
		try {
			// Reads the message in the inflow
			msg = (Message) inputStream.readObject();
		} catch (ClassNotFoundException e) {
			System.out.println("(TCPServer) Error in getting the Message : " + e);
			System.exit(1);
		}
		return msg;
	}

	public int getPort() {
		return port;
	}

	public void run() {

		// If server connected
		if (modeTreatClient) {
			try {
				outputStream = new ObjectOutputStream(communicationSocket.getOutputStream());
				inputStream = new ObjectInputStream(new BufferedInputStream(communicationSocket.getInputStream()));
				manageClient(communicationSocket);
				inputStream.close();
				outputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// if server waiting a connection
		} else {
			while (true) {
				try {
					communicationSocket = waitSocket.accept();
					try {
						TCPServer NewThreadServer = (TCPServer) clone();
						NewThreadServer.modeTreatClient = true;
						new Thread(NewThreadServer).start();

					} catch (CloneNotSupportedException e) {
						System.out.println("(TCPServer) Erreur in cloning the server thread : " + e);
						System.exit(1);
					}
				} catch (IOException e) {
					System.out.println("(TCPServer) Erreur de connection du client : " + e);
					System.exit(1);
				}
			}
		}
	}

	/* (non-Javadoc)
	 * 
	 * @see
	 * com.gmenard.tchat.core.interfaces.IMessageConnection#sendMessage(com.gmenard.tchat.core.
	 * net.Message) */
	@Override
	public void sendMessage(Message msg) throws IOException {
		outputStream.writeObject(msg);
	}

	public void startServer(int port) {
		this.port = port;
		this.modeTreatClient = false;
		try {
			this.waitSocket = new ServerSocket(port);
			new Thread(this).start();
		} catch (IOException e) {
			System.err.println("(TCPServer) Error in starting the Server :" + e);
			System.exit(1);
		}
	}

	public void stopServer() {
		try {
			this.waitSocket.close();
		} catch (IOException e) {
			System.out.println("(TCPServer) Error in stopping the serveur : " + e);
			System.exit(1);
		}
	}
}
