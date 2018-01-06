package yw29_zl58.main.model.data;

import java.awt.Component;

import common.IComponentFactory;
import common.IUser;
import common.IUserMessageType;

import yw29_zl58.game.GameView;

/**
 * @author zihanli
 * the type of game view
 *
 */
public class GameViewType implements IUserMessageType{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private IUser serverStub;
	
	public GameViewType(IUser serverStub) {
		this.serverStub = serverStub;
	}

	/** (non-Javadoc)
	 * @return a factory that can make the view of the game
	 */
	public IComponentFactory getGameView() {
		// TODO Auto-generated method stub
		return new IComponentFactory() {

			@Override
			public Component makeComponent() {
				// TODO Auto-generated method stub
				return new GameView(serverStub, null);
			}
		};
	}

}
