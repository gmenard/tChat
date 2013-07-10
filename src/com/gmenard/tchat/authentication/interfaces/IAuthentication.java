package com.gmenard.tchat.authentication.interfaces;

import java.io.IOException;
import java.util.Vector;

import com.gmenard.tchat.authentication.exceptions.UserExistsException;
import com.gmenard.tchat.authentication.exceptions.UserUnknownException;
import com.gmenard.tchat.authentication.exceptions.WrongPasswordException;

/**
 * Authentication interface.
 * 
 * @author Guillaume
 */
public interface IAuthentication {

	/**
	 * Add a new user in base.
	 * 
	 * @param login user's login
	 * @param password user's password
	 * @throws UserExistsException if user login already exists
	 */
	public void addUser(String login, String password) throws UserExistsException;

	/**
	 * Remove a user in base.
	 * 
	 * @param login user's login
	 * @throws UserUnknownException if user is not in base
	 */
	public void removeUser(String login) throws UserUnknownException;

	/**
	 * Authentication of a user.
	 * 
	 * @param login user's login
	 * @param password user's password
	 * @throws UserUnknownException if user is not in base
	 * @throws WrongPasswordException if password associated to login is not right
	 */
	public void authentify(String login, String password) throws UserUnknownException, WrongPasswordException;

	/**
	 * Load users from base.
	 * 
	 * @param path file
	 * @throws IOException if loading operation failed
	 * @throws ClassNotFoundException if loading operation failed
	 */
	public void load(String path) throws IOException, ClassNotFoundException;

	/**
	 * Save users in base.
	 * 
	 * @param path file
	 * @throws IOException if saving operation failed
	 */
	public void save(String path) throws IOException;

	/**
	 * Retrieve a list of all users.
	 * 
	 * @return vector of users
	 */
	public Vector<String> getUsers();
}
