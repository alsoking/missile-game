package yw29_zl58.main.model.cmds;

import java.awt.Component;

import java.awt.Image;
import java.rmi.RemoteException;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;


import common.DataPacketCR;
import common.DataPacketCRAlgoCmd;

import common.ICRCmd2ModelAdapter;
import common.ICRMessageType;
import common.ICmd2ModelAdapter;
import common.IComponentFactory;
import common.IReceiver;
import yw29_zl58.main.model.data.ImageData;

/**
 * image cmd to tackle image msg
 *
 */
public class ImageCommand extends DataPacketCRAlgoCmd<ImageData> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * ICmd2ModelAdapter
	 *
	 */
	ICmd2ModelAdapter<IReceiver, ICRMessageType> cmd2ModelAdpt;

	/**
	 * add the method
	 *@param index the class
	 *@param host the datapacket
	 *@param params the params
	 *@return null
	 */
	@Override
	public String apply(Class<?> index, DataPacketCR<ImageData> host, String... params) {
		// TODO Auto-generated method stub
		cmd2ModelAdpt.buildScrollableComponent(new IComponentFactory() {

			@Override
			public Component makeComponent() {
				// TODO Auto-generated method stub

				JPanel panel = new JPanel();
				JLabel label = new JLabel();
				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				try {
					JLabel label1 = new JLabel(host.getSender().getUserStub().getName() + ":");
					JLabel label2 = new JLabel();
					ImageIcon img = host.getData().getMessage();
					img.setImage(img.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT));
					label2.setIcon(img);

					label.setIcon(img);
					label.setText(host.getSender().getUserStub().getName() + ":");
					panel.add(label1);
					panel.add(label2);
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return panel;
			}
		}, "Scrolling Comps");
		return null;
	}

	/**
	 * set Cmd2ModelAdpt
	 *@param cmd2ModelAdpt the cmd to model adapter
	 */
	@Override
	public void setCmd2ModelAdpt(ICRCmd2ModelAdapter cmd2ModelAdpt) {
		// TODO Auto-generated method stub
		this.cmd2ModelAdpt = cmd2ModelAdpt;

	}

}
