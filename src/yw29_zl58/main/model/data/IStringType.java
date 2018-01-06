package yw29_zl58.main.model.data;
import common.ICRMessageType;
public class IStringType implements ICRMessageType{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String s;
	public IStringType(String s)
	{
	  this.s=s;
	}
	public String getMessage()
	{
		return s;
	}
}
