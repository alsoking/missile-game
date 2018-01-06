package yw29_zl58.main.model.cmds;

import java.awt.Component;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JLabel;


import common.DataPacketCR;
import common.DataPacketCRAlgoCmd;

import common.ICRCmd2ModelAdapter;
import common.ICRMessageType;
import common.ICmd2ModelAdapter;
import common.IComponentFactory;
import common.IReceiver;
import yw29_zl58.main.model.data.FileType;

/**
 * @author zihanli
 * the command when receiveing file
 *
 */
public class FileCmd extends DataPacketCRAlgoCmd<FileType> {
	/**
	 * ICmd2ModelAdapter
	 *
	 */
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ICmd2ModelAdapter<IReceiver, ICRMessageType> cmd2ModelAdpt;

	/** (non-Javadoc)
	 * 
	 * @see provided.datapacket.ADataPacketAlgoCmd#apply(java.lang.Class, provided.datapacket.ADataPacket, java.lang.Object[])
	 */
	@Override
	public String apply(Class<?> index, DataPacketCR<FileType> host, String... params) {
		// TODO Auto-generated method stub
		File dir = new File("download/");
		dir.mkdirs();
		File temp = new File("download/" + new String(host.getData().getMessage()[0]));
		if (!temp.exists()) {
			try {
				temp.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(temp);
			fos.write(host.getData().getMessage()[1]);
			fos.flush();
			fos.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
		cmd2ModelAdpt.buildScrollableComponent(new IComponentFactory() {
			@Override
			public Component makeComponent() {
				return new JLabel("File received:[download/" + temp.getName() + "]");
			}

		}, "Receive File Status");
		return null;
	}

	/**
	 * set Cmd2ModelAdpt
	 *@param cmd2ModelAdpt
	 */
	/** (non-Javadoc)
	 * @see provided.datapacket.ADataPacketAlgoCmd#setCmd2ModelAdpt(java.lang.Object)
	 * set the model2cmd adapter
	 */
	@Override
	public void setCmd2ModelAdpt(ICRCmd2ModelAdapter cmd2ModelAdpt) {
		this.cmd2ModelAdpt = cmd2ModelAdpt;
	}

}
