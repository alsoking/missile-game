package yw29_zl58.client.main.model.data;

import common.ICRMessageType;

public class FileType implements ICRMessageType{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	byte[][] s;
	public FileType(byte[][] s)
	{
	  this.s=s;
	}
	public byte[][] getMessage()
	{
		return s;
	}

}
