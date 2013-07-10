package com.gmenard.tchat.authentication.exceptions;

@SuppressWarnings("serial")
public class UserUnknownException extends AuthentificationException {

	public UserUnknownException(String login) {
		super(login);
	}
}
