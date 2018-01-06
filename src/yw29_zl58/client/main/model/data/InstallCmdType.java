package yw29_zl58.client.main.model.data;

import common.DataPacketCRAlgoCmd;
import common.datatype.chatroom.ICRInstallCmdType;

/**
 * Install CmdType
 *
 */
/**
 * @author zihanli
 * the data type of installing cmd
 *
 */
public class InstallCmdType implements ICRInstallCmdType {
	/**
	* 
	*/
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Class<?> id;
	/**
	 * 
	 */
	DataPacketCRAlgoCmd<?> cmd;

	/**
	 * @param id the id
	 * @param cmd the cmd
	 * constructor of this class
	 */
	public InstallCmdType(Class<?> id, DataPacketCRAlgoCmd<?> cmd) {
		this.id = id;
		this.cmd = cmd;
	}

	/** (non-Javadoc)
	 * return the cmd
	 */
	@Override
	/**
	 * get Cmd
	 * @return cmd
	 */
	public DataPacketCRAlgoCmd<?> getCmd() {
		// TODO Auto-generated method stub
		return cmd;
	}

	/**
	 * get CmdId
	 * @return id
	 */
	/** (non-Javadoc)
	 */
	@Override
	public Class<?> getCmdId() {
		// TODO Auto-generated method stub
		return id;
	}

}
