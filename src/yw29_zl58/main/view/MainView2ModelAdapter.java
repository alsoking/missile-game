package yw29_zl58.main.view;

import java.util.ArrayList;

import common.IChatRoom;
import yw29_zl58.main.model.ProxyChatroom;
import yw29_zl58.main.model.MainModel.ProxyUser;

/**
 * Adapter for allowing communication from the view to the model
 *
 */
public interface MainView2ModelAdapter {
	/**
	* start the application
	* @param name the name 
	* @param port the port
	*/
	public void start(String name, int port);

	/**
	 * connect to user
	 * @param name the ip
	 * @param IP the name
	 */
	public void connectTo(String name, String IP);

	/**
	 * create a room
	 * @param room the room
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

	public void startGame();
}
