package yw29_zl58.game;

import java.util.UUID;

import common.DataPacketUser;
import common.DataPacketUserAlgoCmd;
import common.IUserCmd2ModelAdapter;
import provided.mixedData.MixedDataKey;

public class ServerMessageCmd extends DataPacketUserAlgoCmd<ServerMessageType>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	transient private IUserCmd2ModelAdapter cmd2ModelAdpt;
	@Override
	public String apply(Class<?> index, DataPacketUser<ServerMessageType> host, String... params) {
		// TODO Auto-generated method stub
		GameView view=cmd2ModelAdpt.get(new MixedDataKey<GameView>(new UUID(123456789L,123456789L),"gameView",GameView.class));
		//System.out.println(view);
		view.displayServerMsg(host.getData().getMessage());
		//view.displayTeamBSore(Integer.toString(host.getData().getScoreB()));
		return null;
	}

	@Override
	public void setCmd2ModelAdpt(IUserCmd2ModelAdapter cmd2ModelAdpt) {
		// TODO Auto-generated method stub
		this.cmd2ModelAdpt=cmd2ModelAdpt;
		
	}

}
