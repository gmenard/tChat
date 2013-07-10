package com.gmenard.tchat.authentication.exceptions;

@SuppressWarnings("serial")
public class AuthentificationException extends Exception {

	public String login;

	public AuthentificationException(String login) {
		super("Authentification Exception with the user: " + login);
		this.login = login;
	}
}
