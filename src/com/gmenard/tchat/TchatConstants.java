package com.gmenard.tchat;

/**
 * Application constants.
 * 
 * @author Guillaume
 */
public class TchatConstants {

	public static final String APPLICATION_TITLE = "Chi't'Chat";

	/* Application Settings */
	public static final int DEFAULT_PORT = 1664;
	public static final String DEFAULT_ADDRESS = "localhost";
	public static final String BASE_LOGINS = "logins.db";
	public static final int APPLICATION_POPUP_WIDTH = 300;
	public static final int APPLICATION_POPUP_HEIGHT = 150;
	public static final String DEFAULT_TIME_FORMAT = "HH:mm:ss";

	/* Popup titles */
	public static final String AUTHENTICATION_POPUP_TITLE = "Authentification User";
	public static final String SERVER_CONNECTION_POPUP_TITLE = "Server Connection";
	public static final String JOIN_TOPIC_POPUP_TITLE = "Join Topic";

	/* Error Dialogs */
	public static final String USER_UNKNOWN_DIALOG_MSG = "User not registered";
	public static final String USER_UNKNOWN_DIALOG_TITLE = "User Unknown";
	public static final String WRONG_PWD_DIALOG_MSG = "Wrong Password";
	public static final String WRONG_PWD_DIALOG_TITLE = "Wrong Password";

	/* Buttons Text */
	public static final String JOIN_TOPIC_BUTTON_TEXT = "Join Topic";
	public static final String POST_MESSAGE_BUTTON_TEXT = "Post";
	public static final String SERVER_CONNECTION_BUTTON_TEXT = "Connect Server";
	public static final String AUTHENTICATION_BUTTON_TEXT = "User Authentification";
	public static final String LOG_ON_BUTTON_TEXT = "Log on";
	public static final String CANCEL_BUTTON_TEXT = "Cancel";
	public static final String CONNECT_BUTTON_TEXT = "Connect";

	/* Labels Text */
	public static final String LOGIN_LABEL = "Login: ";
	public static final String PASSWORD_LABEL = "Password: ";
	public static final String LIST_USERS_LABEL = "// Chatters List \\";
	public static final String PORT_LABEL = "Port : ";
	public static final String ADDRESS_LABEL = "Adress : ";
	public static final String TOPICS_LABEL = "List of Topics : ";
	public static final String NEW_TOPIC_LABEL = "New Topic";

	public static final String AVAILABLE_TOPICS_DATA = "Available Topics";
	public static final String LIST_TOPICS_DATA = "List of topics";

	public static final String WELCOME_MESSAGE = ":: Welcome ! \n";

	public static final char PASSWORD_HIDDEN_CHAR = '*';
}
