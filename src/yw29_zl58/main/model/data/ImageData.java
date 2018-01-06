package yw29_zl58.main.model.data;

import javax.swing.ImageIcon;

import common.ICRMessageType;


/**
 * ImageData
 */
public class ImageData implements ICRMessageType {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    ImageIcon s;
    public ImageData(ImageIcon s)
	{
	  this.s=s;
	}
	public ImageIcon getMessage()
	{
		return s;
	}
    

}
