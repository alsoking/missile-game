package yw29_zl58.client.main.model;

import java.awt.Component;

import common.IChatRoom;
import yw29_zl58.client.main.model.MainModel.ProxyUser;

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
	 * @param friend the proxy user
	 */
	public void addFriend(ProxyUser friend);

	//public void createRoom(String room);
	/**
	 * append info to view
	 * @param info the information stirng
	 */
	public void appendInfo(String info);

	/**
	 * add a tab panel in the big tab pane
	 * @param component the compoenet
	 * @param label the lable of the compoenet
	 */
	public void addPanel(Component component, String label);
	
	public void joinRoom(IChatRoom room);

}
