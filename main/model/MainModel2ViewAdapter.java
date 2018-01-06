package yw29_zl58.main.model;

import java.awt.Component;

import common.IChatRoom;
//import yw29_zl58.common.IUser;
import yw29_zl58.main.model.MainModel.ProxyUser;
//import yw29_zl58.mini.model.ChatRoom;

/**
 * The adapter to allow communication from the model to the view.
 * Lets the model say what functionality it wants from the view.
 */
public interface MainModel2ViewAdapter {

	/**
	 * set IP
	 * @param IP the ip address
	 */
	public void setIP(String IP);

	/**
	 * add friend
	 * @param friend the friend
	 */
	public void addFriend(ProxyUser friend);

	//public void createRoom(String room);
	/**
	 * append info to view
	 * @param info the information string
	 */
	public void appendInfo(String info);

	/**
	 * add a tab panel in the big tab pane
	 * @param component the component
	 * @param label the label of the compoenet
	 */
	public void addPanel(Component component, String label);

	public void joinRoom(IChatRoom room);

}
