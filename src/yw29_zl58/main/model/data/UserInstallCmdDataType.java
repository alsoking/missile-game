package yw29_zl58.main.model.data;

import common.DataPacketUserAlgoCmd;
import common.datatype.user.IUserInstallCmdType;

public class UserInstallCmdDataType implements IUserInstallCmdType {

	private static final long serialVersionUID = 1L;
	Class<?> id;
	/**
	 * 
	 */
	DataPacketUserAlgoCmd<?> cmd;
	
	public UserInstallCmdDataType(Class<?> id, DataPacketUserAlgoCmd<?> cmd) {
		this.id = id;
		this.cmd = cmd;
	}
	@Override
	public DataPacketUserAlgoCmd<?> getCmd() {
		// TODO Auto-generated method stub
		return cmd;
	}

	@Override
	public Class<?> getCmdId() {
		// TODO Auto-generated method stub
		return id;
	}

}
