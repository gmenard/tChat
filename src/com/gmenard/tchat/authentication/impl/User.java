package com.gmenard.tchat.authentication.impl;

/**
 * User POJO.
 * 
 * @author Guillaume
 */
public class User implements Comparable<User> {

	/** user's login */
	private String login;
	/** user's password */
	private String password;

	public User(String login, String password) {
		this.login = login;
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public String getLogin() {
		return this.login;
	}

	@Override
	public int compareTo(User user) {
		return (this.login.compareTo(user.getLogin()));
	}
}