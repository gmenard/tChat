package com.gmenard.tchat.authentication.exceptions;

@SuppressWarnings("serial")
public class WrongPasswordException extends AuthentificationException {

	public WrongPasswordException(String login) {
		super(login);
	}
}
