package yw29_zl58.client.main.model.data;


import common.datatype.chatroom.*;
import common.IReceiver;

/**
 * 
 * Add receiver type
 *
 */
public class AddReceiverType implements IAddReceiverType {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 
	 * receiver
	 *
	 */
	IReceiver receiver;

	/**
	 * constructor
	 * @param receiver the receiver
	 */
	public AddReceiverType(IReceiver receiver) {
		this.receiver = receiver;
	}

	/**
	 * get receiver stub
	 *@return IReceiver
	 */
	@Override
	public IReceiver getReceiverStub() {
		// TODO Auto-generated method stub
		return receiver;
	}

}
