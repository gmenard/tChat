package com.gmenard.tchat.authentication.exceptions;

@SuppressWarnings("serial")
public class UserExistsException extends AuthentificationException {

	public UserExistsException(String login) {
		super(login);
	}
}
