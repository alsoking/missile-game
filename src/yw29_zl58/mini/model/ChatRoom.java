package yw29_zl58.mini.model;

import java.awt.Component;
//import java.awt.Container;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
//import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import common.*;
import common.datatype.chatroom.*;
import yw29_zl58.main.model.data.RemoveReceiverType;

/**
 * chatroom
 *
 */
public class ChatRoom implements IChatRoom {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * IReceiver
	 */
	IReceiver related;
	/**
	 * HashSet of receivers
	 */
	HashSet<IReceiver> receivers = new HashSet<IReceiver>();
	/**
	 * 
	 */
	UUID id;
	/**
	 * 
	 */
	private String name;
	/**
	 * MiniModel2ViewAdapter
	 */
	transient MiniModel2ViewAdapter model2viewAdpt;

	/**
	 * constructor
	 * @param _name the tring name
	 * @param _model2viewAdpt the adapter
	 */
	public ChatRoom(String _name, MiniModel2ViewAdapter _model2viewAdpt) {
		model2viewAdpt = _model2viewAdpt;
		setName(_name);
		id = UUID.randomUUID();
	}

	/**
	 * set related receiver
	 * @param receiver the related receiver
	 */
	public void setRelated(IReceiver receiver) {
		related = receiver;
	}

	/**
	 * get related receiver
	 * @return related receiver
	 */
	public IReceiver getRelated() {
		return related;
	}

	/**
	 * convert name to string
	 * @return name
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return getName();
	}

	/**
	 * get name
	 * @return name
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * set name
	 * @param name the string name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * append Msg
	 * @param msg the new msg
	 * @param name the name
	 */
	public void appendMsg(String msg, String name) {
		model2viewAdpt.appendMsg(msg, name);
	}

	/**
	* get receiver stubs
	* @return receivers the collection of receiver
	*/
	@Override
	public Collection<IReceiver> getIReceiverStubs() {
		// TODO Auto-generated method stub
		return receivers;
	}

	/**
	 * add IReceiverStub to local
	 * @param receiver the new receiver
	 * @return boolean
	 */
	@Override
	public boolean addIReceiverStub(IReceiver receiver) {
		// TODO Auto-generated method stub
		return receivers.add(receiver);
	}

	/**
	 * remove IReceiverStub locally
	 * @return boolean
	 */
	@Override
	public boolean removeIReceiverStub(IReceiver receiver) {
		// TODO Auto-generated method stub
		return receivers.remove(receiver);
	}

	/**
	* send Packet
	* @param data the data in the packet
	*/
	@Override
	public <T extends ICRMessageType> void sendPacket(DataPacketCR<T> data) {
		// TODO Auto-generated method stub
		for (IReceiver receiver : receivers) {
			try {
				receiver.receive(data);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * get UUID
	 * @return UUID
	 */
	@Override
	public UUID getUUID() {
		// TODO Auto-generated method stub
		return this.id;
	}

	/**
	 * addScrollableComponent
	 * @param component the component
	 * @param label the label of the component
	 */
	public void addScrollableComponent(Component component, String label) {
		model2viewAdpt.addScrollableComponent(component, label);
	}

	/**
	 * addNonScrollableComponent
	 * @param component the o=component
	 * @param label the lable of the component
	 */
	public void addNonScrollableComponent(Component component, String label) {
		model2viewAdpt.addNonScrollableComponent(component, label);
	}

	/**
	 * refresh
	 */
	public void refresh() {
		model2viewAdpt.refresh();
	}

	/**
	 * get members
	 * @return collection of members
	 */
	public Collection<String> getMembers() {
		Collection<String> result = new ArrayList<String>();
		for (IReceiver r : receivers) {
			try {
				result.add(r.getUserStub().getName());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * leave the room
	 */
	public void leave() {
		sendPacket(new DataPacketCR<IRemoveReceiverType>(IRemoveReceiverType.class,
				new RemoveReceiverType(related), related));
		model2viewAdpt.leave();
	}

}
