package yw29_zl58.main.model.data;


import common.*;

import common.datatype.chatroom.*;


/**
 * Remove Receiver data
 * @author admin
 *
 */
public class RemoveReceiverType implements IRemoveReceiverType {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * receiver stub
	 */
	IReceiver stub;

	/**
	 * constructor for RemoveReceiverData
	 * @param stub the new receiver stub
	 */
	public RemoveReceiverType(IReceiver stub) {
		this.stub = stub;
	}

	/**
	 * get receiver stub
	 * @return receiver stub
	 */
	@Override
	public IReceiver getReceiverStub() {
		// TODO Auto-generated method stub
		return stub;
	}

}
