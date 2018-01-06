package yw29_zl58.game;

import common.IUserMessageType;

public class ServerMessageType implements IUserMessageType{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;
	
	
	public ServerMessageType(String s)
	{
		message=s;
	}
	public String getMessage()
	{
		return message;
	}

}
