package yw29_zl58.client.main.model.data;

import common.DataPacketCR;
import common.IReceiver;

/**
 * String Data
 *
 */
public class StringData extends DataPacketCR<IStringType> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor
	 * @param c the class
	 * @param data the data
	 * @param sender the sender receiver stub
	 */
	public StringData(Class<IStringType> c, IStringType data, IReceiver sender) {
		super(c, data, sender);
		// TODO Auto-generated constructor stub
	}

}
