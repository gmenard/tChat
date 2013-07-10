package com.gmenard.tchat.authentication.impl;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Vector;

import com.gmenard.tchat.authentication.exceptions.UserExistsException;
import com.gmenard.tchat.authentication.exceptions.UserUnknownException;
import com.gmenard.tchat.authentication.exceptions.WrongPasswordException;
import com.gmenard.tchat.authentication.interfaces.IAuthentication;

/**
 * @author Guillaume
 */
public class Authentication implements IAuthentication {

	/** List of registered users. */
	private SortedSet<User> usersList = new TreeSet<User>();

	public Authentication() {
		usersList = new TreeSet<User>();
	}

	/* (non-Javadoc)
	 * 
	 * @see com.gmenard.tchat.authentication.interfaces.IAuthentication#addUser(java.lang.String,
	 * java.lang.String) */
	@Override
	public void addUser(String login, String password) throws UserExistsException {
		User newUser = new User(login, password);
		if (usersList.contains(newUser)) {
			throw new UserExistsException(login);
		} else {
			usersList.add(newUser);
		}
	}

	/* (non-Javadoc)
	 * 
	 * @see
	 * com.gmenard.tchat.authentication.interfaces.IAuthentication#authentify(java.lang.String,
	 * java.lang.String) */
	@Override
	public void authentify(String login, String password) throws WrongPasswordException, UserUnknownException {
		User authUser = null;
		Boolean loginKnown = false;

		for (User user : usersList) {
			if (user.getLogin().equals(login)) {
				loginKnown = true;
				authUser = user;
			}
		}

		if (loginKnown) {
			if (!authUser.getPassword().equals(password)) {
				System.out.println("Authentication : Wrong Password");
				throw new WrongPasswordException(login);
			}
		} else {
			System.out.println("Authentication : User Unknown");
			throw new UserUnknownException(login);
		}
	}

	/* (non-Javadoc)
	 * 
	 * @see com.gmenard.tchat.authentication.interfaces.IAuthentication#getUsers() */
	@Override
	public Vector<String> getUsers() {
		Vector<String> users = new Vector<String>();

		for (User u : usersList) {
			users.add(u.getLogin());
		}
		return users;
	}

	/* (non-Javadoc)
	 * 
	 * @see com.gmenard.tchat.authentication.interfaces.IAuthentication#load(java.lang.String) */
	@Override
	@SuppressWarnings("unchecked")
	public void load(String path) throws IOException, ClassNotFoundException {
		FileInputStream file = new FileInputStream(path);
		ObjectInputStream flow = new ObjectInputStream(file);
		this.usersList = (SortedSet<User>) flow.readObject();
		flow.close();
		file.close();
	}

	/* (non-Javadoc)
	 * 
	 * @see
	 * com.gmenard.tchat.authentication.interfaces.IAuthentication#removeUser(java.lang.String) */
	@Override
	public void removeUser(String login) throws UserUnknownException {
		User rmvUser = new User(login, null);
		if (usersList.contains(rmvUser)) {
			usersList.remove(rmvUser);
		} else {
			throw new UserUnknownException(login);
		}
	}

	/* (non-Javadoc)
	 * 
	 * @see com.gmenard.tchat.authentication.interfaces.IAuthentication#save(java.lang.String) */
	@Override
	public void save(String path) throws IOException {
		FileOutputStream file = new FileOutputStream(path);
		ObjectOutputStream flow = new ObjectOutputStream(file);
		flow.writeObject(usersList);
		flow.flush();
		flow.close();
		file.close();
	}
}
