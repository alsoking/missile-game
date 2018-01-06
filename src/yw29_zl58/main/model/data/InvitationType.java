package yw29_zl58.main.model.data;

import common.IChatRoom;
import common.datatype.user.IInvitationType;

public class InvitationType implements IInvitationType {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	IChatRoom room;
	public InvitationType(IChatRoom room)
	{
		this.room=room;
	}
	@Override
	public IChatRoom getChatRoom() {
		// TODO Auto-generated method stub
		return room;
	}

}
