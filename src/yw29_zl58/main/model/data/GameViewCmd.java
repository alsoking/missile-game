package yw29_zl58.main.model.data;

import java.util.UUID;


import common.DataPacketUser;
import common.DataPacketUserAlgoCmd;

import common.IComponentFactory;
import common.IUserCmd2ModelAdapter;
import provided.mixedData.MixedDataKey;
import yw29_zl58.game.GameView;

public class GameViewCmd extends DataPacketUserAlgoCmd<GameViewType> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	transient private IUserCmd2ModelAdapter _cmd2ModelAdpt;
	@Override
	public String apply(Class<?> index, DataPacketUser<GameViewType> host, String... params) {
		// TODO Auto-generated method stub
		
	
			
	IComponentFactory view = host.getData().getGameView();
	//_cmd2ModelAdpt.buildNonScrollableComponent(view, "game view");
	GameView game=(GameView) view.makeComponent();
	_cmd2ModelAdpt.put(new MixedDataKey<GameView>(new UUID(123456789L,123456789L),"gameView",GameView.class), game);
	game.setModelAdapter(_cmd2ModelAdpt);
	
	game.setBounds(30, 40,1000, 1000);
	game.setVisible(true);
				
			
	
		return null;
	}

	@Override
	public void setCmd2ModelAdpt(IUserCmd2ModelAdapter cmd2ModelAdpt) {
		// TODO Auto-generated method stub
		this._cmd2ModelAdpt=cmd2ModelAdpt;
		
	}

	
	
}
