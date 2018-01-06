package yw29_zl58.client.main.model;

//import java.rmi.RemoteException;
import java.util.Collection;
//import java.util.HashMap;
import java.util.UUID;

import common.DataPacketCR;
import common.ICRMessageType;
import common.IChatRoom;
import common.IReceiver;

/**
 * ProxyChatroom
 */
public class ProxyChatroom implements IChatRoom {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * chat room stub
	 */
	IChatRoom stub;

	/**
	 * constructor
	 * @param _stub the chat room stub
	 */
	public ProxyChatroom(IChatRoom _stub) {
		stub = _stub;
	}

	/**
	 * convert name to string
	 */
	@Override
	public String toString() {
		return this.getName();
	}

	/**
	 * get chatroom's name
	 */
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return stub.getName();
	}

	/**
	 * get receiverStubs
	 * @return collection of receiverStubs
	 */

	@Override
	public Collection<IReceiver> getIReceiverStubs() {
		// TODO Auto-generated method stub
		return stub.getIReceiverStubs();
	}

	/**
	 *add receiver stub to local
	 *@param receiver the receiver
	 *@return boolean
	 */
	@Override
	public boolean addIReceiverStub(IReceiver receiver) {
		// TODO Auto-generated method stub
		return stub.addIReceiverStub(receiver);
	}

	/**
	 *remove receiver stub to local
	 *@param receiver the receiver
	 *@return boolean
	 */
	@Override
	public boolean removeIReceiverStub(IReceiver receiver) {
		// TODO Auto-generated method stub
		return stub.removeIReceiverStub(receiver);
	}

	/**
	 *send DataPacket
	 *@param data the data packet
	 */
	@Override
	public <T extends ICRMessageType> void sendPacket(DataPacketCR<T> data) {
		// TODO Auto-generated method stub
		stub.sendPacket(data);

	}

	/**
	 *get UUID
	 *@return UUID
	 */
	@Override
	public UUID getUUID() {
		// TODO Auto-generated method stub
		return stub.getUUID();
	}

}
