package yw29_zl58.client.main.model.cmds;

import java.rmi.RemoteException;

import common.DataPacketCR;
import common.DataPacketCRAlgoCmd;
import common.ICRCmd2ModelAdapter;
import common.ICRMessageType;
import common.ICmd2ModelAdapter;
import common.IReceiver;
import yw29_zl58.client.main.model.data.IStringType;

/**
 * StringCommand to process string
 *
 */
public class StringCommand extends DataPacketCRAlgoCmd<IStringType> {

	/**
	 * String command to process string
	 */

	private static final long serialVersionUID = -6829927260727331813L;
	/**
	 * ICmd2ModelAdapter
	 */

	transient private ICmd2ModelAdapter<IReceiver, ICRMessageType> cmd2ModelAdpt = null;


	/**
	 * add method
	 * @param index the class
	 * @param host the datapacket
	 * @param params the params
	 * @return null
	 */

	@Override
	public String apply(Class<?> index, DataPacketCR<IStringType> host, String... params) {
		try {
			cmd2ModelAdpt.appendMsg(host.getData().getMessage(), host.getSender().getUserStub().getName());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void setCmd2ModelAdpt(ICRCmd2ModelAdapter cmd2ModelAdpt) {
		this.cmd2ModelAdpt = cmd2ModelAdpt;
	}

}
