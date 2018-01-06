package yw29_zl58.mini.controller;

import java.awt.Component;

import java.util.Collection;

import javax.swing.ImageIcon;

import common.DataPacketCR;
import yw29_zl58.main.model.data.FileType;
import yw29_zl58.main.model.data.IStringType;
import yw29_zl58.main.model.data.ImageData;
import yw29_zl58.mini.model.ChatRoom;
import yw29_zl58.mini.model.MiniModel2ViewAdapter;
import yw29_zl58.mini.view.MiniView;
import yw29_zl58.mini.view.MiniView2ModelAdapter;

/**
 * 
 * MiniController
 *
 */
public class MiniController {
	/**
	 * Mini view
	 */
	MiniView view;
	/**
	 * model
	 */
	ChatRoom model;
	/**
	 * Mini2Main adpt
	 */
	Mini2MainAdapter adpt;

	/**
	 * constructor
	 * @param name the name of the mini mvc
	 * @param adpt the mini to main adapter
	 */
	public MiniController(String name, Mini2MainAdapter adpt) {
		/**
		 * view of the mini MVC structure
		 */
		view = new MiniView(new MiniView2ModelAdapter() {

			@Override
			public void SendMsg(String msg) {
				model.sendPacket(new DataPacketCR<IStringType>(IStringType.class, new IStringType(msg), model.getRelated()));
			}

			@Override
			public void SendImg(String path) {
				// TODO Auto-generated method stub
				ImageIcon img = new ImageIcon(path);
				System.out.println(path);
				model.sendPacket(new DataPacketCR<ImageData>(ImageData.class, new ImageData(img), model.getRelated()));

			}

			@Override
			public Collection<String> getMembers() {
				// TODO Auto-generated method stub
				return model.getMembers();
			}

			@Override
			public void leave() {
				// TODO Auto-generated method stub
				model.leave();

			}

			@Override
			public void SendFile(byte[][] data) {
				// TODO Auto-generated method stub
				model.sendPacket(new DataPacketCR<FileType>(FileType.class, new FileType(data), model.getRelated()));

			}

		});
		/**
		 * model of the mini MVC structure
		 */
		model = new ChatRoom(name, new MiniModel2ViewAdapter() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void appendMsg(String msg, String name) {
				// TODO Auto-generated method stub
				view.appendMsg(msg, name);

			}

			@Override
			public void addScrollableComponent(Component c, String label) {
				// TODO Auto-generated method stub
				view.addScrollableComponent(c, label);

			}

			@Override
			public void addNonScrollableComponent(Component c, String label) {
				// TODO Auto-generated method stub
				view.addNonScrollableComponent(c, label);

			}

			@Override
			public void refresh() {
				// TODO Auto-generated method stub
				view.refresh();

			}

			@Override
			public void leave() {
				// TODO Auto-generated method stub
				adpt.leave(model.getUUID());

			}
		});
		this.adpt = adpt;

	}

	/**
	 * get model
	 * @return model
	 */
	public ChatRoom getModel() {
		return model;
	}

	/**
	 * get view
	 * @return view
	 */
	public MiniView getView() {
		return view;
	}

}
