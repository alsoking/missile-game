package yw29_zl58.client.main.view;

import java.util.ArrayList;

import common.IChatRoom;
import yw29_zl58.client.main.model.ProxyChatroom;
import yw29_zl58.client.main.model.MainModel.ProxyUser;

/**
 * Adapter for allowing communication from the view to the model
 *
 */
public interface MainView2ModelAdapter {
	/**
	* start the application
	* @param name the input
	* @param port the port
	*/
	public void start(String name, int port);

	/**
	 * connect to user
	 * @param name the name
	 * @param IP the ip address
	 */
	public void connectTo(String name, String IP);

	/**
	 * create a room
	 * @param room the room stirng
	 */
	public void createRoom(String room);

	/**
	 * join a room
	 * @param room the room
	 */
	public void joinRoom(IChatRoom room);

	/**
	 * get remote room
	 * @param friend the friend user
	 * @return list of remote room
	 */
	public ArrayList<ProxyChatroom> getRemoteRoom(ProxyUser friend);

	/**
	 * quit the room
	 */
	public void quit();

	/**
	 * send game view to user
	 * @param user the user who will receive the view
	 */
	public void sendGameView(ProxyUser user);
}
