package yw29_zl58.game;

import common.IUserMessageType;

public class GameTaskType implements IUserMessageType{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String task;
	
	public GameTaskType(String s)
	{
		task=s;
	}
	
	public String getTask()
	{
		return task;
	}

}
