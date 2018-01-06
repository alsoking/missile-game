package yw29_zl58.main.model;

import java.awt.Component;

import common.IChatRoom;
import common.IUser;

/**
 * 
 * User2ModelAdapter
 *
 */
public interface User2ModelAdapter {
	/**
	 * add friend
	 * @param friend the friend stub
	 */
	public void addfriend(IUser friend);

	/**
	 * tell the main model to add a panel
	 * @param makeComponent the new component
	 * @param label the label of the component
	 */
	public void addPanel(Component makeComponent, String label);

	public void joinRoom(IChatRoom room);
	
	public void append(String msg);

}
