package yw29_zl58.client.mini.view;

import java.util.Collection;

/**
 * @author zihanli
 * the adapter to mini model
 *
 */
public interface MiniView2ModelAdapter {
	//public void setMembers(Collection<String> value);

	/**
	 * @param msg the message
	 * send the message
	 */
	public void SendMsg(String msg);

	/**
	 * @param path
	 * send the image
	 */
	public void SendImg(String path);

	public Collection<String> getMembers();

	/**
	 * leave a room
	 */
	public void leave();

	/**
	 * @param data
	 * send file
	 */
	public void SendFile(byte[][] data);
}
