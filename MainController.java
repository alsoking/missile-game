package yw29_zl58.client.main.controller;

import java.awt.Component;
//import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.UUID;

import common.IChatRoom;
import yw29_zl58.client.main.model.MainModel;
import yw29_zl58.client.main.model.MainModel2ViewAdapter;
import yw29_zl58.client.main.model.ProxyChatroom;
import yw29_zl58.client.main.model.MainModel.ProxyUser;
import yw29_zl58.client.main.view.MainView;
import yw29_zl58.client.main.view.MainView2ModelAdapter;
import yw29_zl58.client.mini.controller.Mini2MainAdapter;
import yw29_zl58.client.mini.controller.MiniController;

/**
 * Main controller for Model-View-Controller structure 
 * The controller allows communication between the model and view by way of their adapters.
 * It will setup both the model's and the view's methods, as well as starting them.
 */
public class MainController {
	/**
	 * The view object of the MVC structure
	 */

	MainView view = new MainView(new MainView2ModelAdapter() {

		@Override
		public void start(String name, int port) {
			// TODO Auto-generated method stub
			model.start(name, port);

		}

		@Override
		public void createRoom(String room) {
			// TODO Auto-generated method stub
			MiniController mini = new MiniController(room, new Mini2MainAdapter() {

				@Override
				public void leave(UUID id) {
					// TODO Auto-generated method stub
					model.leave(id);
					view.leave(room);

				}
			});
			model.addLocalRoom(mini.getModel());
			view.addPane(room, mini.getView());
			//return mini.getModel();
		}

		@Override
		public void connectTo(String name, String IP) {
			// TODO Auto-generated method stub
			model.connectTo(name, IP);

		}

		@Override
		public ArrayList<ProxyChatroom> getRemoteRoom(ProxyUser friend) {
			// TODO Auto-generated method stub
			return model.getRemoteRoom(friend);
		}

		@Override
		public void joinRoom(IChatRoom room) {
			MiniController mini = new MiniController(room.getName(), new Mini2MainAdapter() {

				@Override
				public void leave(UUID id) {
					// TODO Auto-generated method stub
					model.leave(id);
					view.leave(room.getName());

				}
			});
			model.joinRoom(mini.getModel(), room);
			view.addPane(room.getName(), mini.getView());

		}

		@Override
		public void quit() {
			// TODO Auto-generated method stub
			model.quit();

		}

		@Override
		public void sendGameView(ProxyUser user) {
			// TODO Auto-generated method stub
			model.sendGameView(user);

		}
	});
	/**
	 * The model object of the MVC structure
	 */
	MainModel model = new MainModel(new MainModel2ViewAdapter() {

		@Override
		public void setIP(String IP) {
			// TODO Auto-generated method stub
			view.setIP(IP);

		}

		@Override
		public void addFriend(ProxyUser friend) {
			// TODO Auto-generated method stub
			view.addFriend(friend);
		}

		@Override
		public void appendInfo(String info) {
			// TODO Auto-generated method stub
			view.appendInfo(info);

		}

		@Override
		public void addPanel(Component component, String label) {
			// TODO Auto-generated method stub
			view.addPanel(component, label);

		}

		@Override
		public void joinRoom(IChatRoom room) {
			// TODO Auto-generated method stub
			view.joinRoom(room);
			
		}

	});

	/**
	 * main method for start the whole system
	 * @param args the aruments from commond line
	 */

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MainController controller = new MainController();
		controller.view.start();

	}

}
