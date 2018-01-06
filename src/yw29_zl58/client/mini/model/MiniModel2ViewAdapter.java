package yw29_zl58.client.mini.model;

import java.awt.Component;
import java.io.Serializable;

/**
 * 
 * MiniModel2ViewAdapter
 *
 */
public interface MiniModel2ViewAdapter extends Serializable {
	/**
	 * appendMsg to view
	 * @param msg the string message
	 * @param name the name string
	 */
	public void appendMsg(String msg, String name);

	/**
	 * addScrollableComponent
	 * @param c the compound
	 * @param label the lable of the conponent
	 */
	public void addScrollableComponent(Component c, String label);

	/**
	 * addNonScrollableComponent
	 * @param c the component
	 * @param label the label of the component
	 */
	public void addNonScrollableComponent(Component c, String label);

	/**
	 * refresh
	 */
	public void refresh();

	/**
	 * leave
	 */
	public void leave();

}
