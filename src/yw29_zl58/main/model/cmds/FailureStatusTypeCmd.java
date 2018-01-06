package yw29_zl58.main.model.cmds;

import common.DataPacketCR;
import common.DataPacketCRAlgoCmd;
import common.ICRCmd2ModelAdapter;
import common.ICRMessageType;
import common.ICmd2ModelAdapter;
import common.IReceiver;
import common.datatype.chatroom.ICRFailureStatusType;

/**
 * @author zihanli
 *
 */
public class FailureStatusTypeCmd extends DataPacketCRAlgoCmd<ICRFailureStatusType> {
	/**
	 * ICmd2ModelAdapter
	 *
	 */
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ICmd2ModelAdapter<IReceiver, ICRMessageType> cmd2ModelAdpt;

	/**
	 *add algorithm
	 *@param index
	 *@param host
	 *@param params
	 *@return null
	 */
	/**(non-Javadoc)
	 * @see provided.datapacket.ADataPacketAlgoCmd#apply(java.lang.Class, provided.datapacket.ADataPacket, java.lang.Object[])
	 */


	/**
	 * set ICmd2ModelAdapter
	 *@param cmd2ModelAdpt
	 */
	/** (non-Javadoc)
	 * @see provided.datapacket.ADataPacketAlgoCmd#setCmd2ModelAdpt(java.lang.Object)
	 * set the adapter
	 */
	@Override
	public void setCmd2ModelAdpt(ICRCmd2ModelAdapter cmd2ModelAdpt) {
		// TODO Auto-generated method stub
		this.cmd2ModelAdpt = cmd2ModelAdpt;

	}

	@Override
	public String apply(Class<?> index, DataPacketCR<ICRFailureStatusType> host, String... params) {
		// TODO Auto-generated method stub
		return null;
	}


}
