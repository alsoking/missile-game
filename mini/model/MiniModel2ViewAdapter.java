package yw29_zl58.mini.model;

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
	 * @param msg the smsg
	 * @param name the tring name
	 */
	public void appendMsg(String msg, String name);

	/**
	 * addScrollableComponent
	 * @param c the comoenent
	 * @param label the lable of the component
	 */
	public void addScrollableComponent(Component c, String label);

	/**
	 * addNonScrollableComponent
	 * @param c the compoenent
	 * @param label the lable of the component
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
