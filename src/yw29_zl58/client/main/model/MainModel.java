package yw29_zl58.client.main.model;

import java.awt.Component;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;
import java.util.function.Consumer;

import common.*;

import common.datatype.chatroom.*;

import common.DataPacketUser;
import common.IChatRoom;
import common.IReceiver;
import common.IUser;
import common.IUserMessageType;
//import provided.compute.ICompute;
//import provided.rmiUtils.IRMI_Defs;
import provided.rmiUtils.RMIUtils;
import yw29_zl58.client.main.model.data.AddReceiverType;
import yw29_zl58.client.main.model.data.RemoveReceiverType;
import yw29_zl58.client.mini.model.ChatRoom;

/**
 * Main model for Model-View-Controller structure 
 */
public class MainModel {
	/**
	 * model adapter object for communicating with the view
	 */
	MainModel2ViewAdapter model2viewAdpt;
	/**
	 * userstub
	 */
	IUser userStub;
	/**
	 * user
	 */
	User user;
	/**
	 * Registry
	 */
	Registry registry;
	//HashMap<UUID,IChatRoom> roomStubs=new HashMap<UUID,IChatRoom>();
	/**
	 * Hashmap to preserve the chatroom list
	 */
	HashMap<UUID, ChatRoom> chatRooms = new HashMap<UUID, ChatRoom>();
	/**
	 * RMI system
	 */
	private RMIUtils rmiUtils;
	/**
	 * cmd for output info to view
	 */
	private Consumer<String> outputCmd = new Consumer<String>() {

		@Override
		public void accept(String t) {
			model2viewAdpt.appendInfo(t + "\n");
			//System.out.println(t + "\n");
		}
	};

	/**
	 * proxy user 
	 */
	public class ProxyUser implements IUser {
		/**
		 * User stub
		 */

		IUser stub;

		/**
		 * Constructor
		 * @param user stub
		 */
		ProxyUser(IUser _stub) {
			this.stub = _stub;
		}

		/**
		 * get user name
		 * @return the name of the user
		 */
		@Override
		public String getName() throws RemoteException {
			// TODO Auto-generated method stub
			return stub.getName();
		}

		/**
		 * converting the user name into a String 
		 * @return the name of the user
		 */
		@Override
		public String toString() {
			try {
				return this.getName();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return "wrong";
		}

		/**
		 * get chat rooms 
		 * @return the list of chat rooms
		 */
		@Override
		public Collection<IChatRoom> getChatRooms() throws RemoteException {
			// TODO Auto-generated method stub
			return stub.getChatRooms();
		}

		/**
		 * get user's UUID  
		 * @return the UUID of the user
		 */
		@Override
		public UUID getUUID() throws RemoteException {
			// TODO Auto-generated method stub
			return stub.getUUID();
		}

		/**
		 * connnect to the user 
		 * @param userStub the remote user stub
		 */
		@Override
		public void connect(IUser userStub) throws RemoteException {
			// TODO Auto-generated method stub
			stub.connect(userStub);

		}


		/** (non-Javadoc)
		 * receiver dataPacket
		 */

		@Override
		public <T extends IUserMessageType> void receive(DataPacketUser<T> data) throws RemoteException {
			stub.receive(data);
		}

	}

	/**
	 * Constructor for main model 
	 * @param _model2viewAdpt the adapter to the view
	 */
	public MainModel(MainModel2ViewAdapter _model2viewAdpt) {
		model2viewAdpt = _model2viewAdpt;
	}

	/**
	 * configure the application
	 * @param name the name  string
	 * @param port the port between 2000 and 2099
	 */
	public void start(String name, int port) {
		rmiUtils = new RMIUtils(outputCmd);
		rmiUtils.startRMI(port - 10);
		try {
			user = new User(name, port, new User2ModelAdapter() {

				@Override
				public void addfriend(IUser friend) {
					// TODO Auto-generated method stub
					model2viewAdpt.addFriend(new ProxyUser(friend));
				}

				@Override
				public void addPanel(Component component, String label) {
					// TODO Auto-generated method stub
					model2viewAdpt.addPanel(component, label);

				}

				@Override
				public void joinRoom(IChatRoom room) {
					// TODO Auto-generated method stub
					model2viewAdpt.joinRoom(room);
					
				}
			});

			user.setIP(rmiUtils.getLocalAddress());
			model2viewAdpt.setIP(rmiUtils.getLocalAddress() + ":" + port);

			userStub = (IUser) UnicastRemoteObject.exportObject(user, port);
			registry = rmiUtils.getLocalRegistry();
			registry.rebind(user.BOUND_NAME1, userStub);
			user.setUserStub(userStub);
			user.initialDatapacketAlgo();

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * connect to user
	 * @param name the name
	 * @param IP the ip of the user
	 */
	public void connectTo(String name, String IP) {
		Registry registry = rmiUtils.getRemoteRegistry(IP);
		try {
			IUser friendStub = (IUser) registry.lookup(name);
			user.connect(friendStub);
			friendStub.connect(userStub);
		} catch (AccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//return user;

	}

	/**
	 * add room to local
	 * @param newRoom the new chat room
	 */
	public void addLocalRoom(ChatRoom newRoom) {
		//ChatRoom newRoom=new ChatRoom(name);
		IReceiver receiverStub;
		Receiver receiver = new Receiver(userStub, newRoom);
		try {
			receiverStub = (IReceiver) UnicastRemoteObject.exportObject(receiver, user.BOUND_PORT);
			receiver.setReceiverStub(receiverStub);
			//Registry registry = rmiUtils.getLocalRegistry();
			registry.rebind(newRoom.getName(), receiver);
			//roomStubs.put(newRoom.getId(), roomStub);
			newRoom.addIReceiverStub(receiverStub);
			newRoom.setRelated(receiverStub);
			chatRooms.put(newRoom.getUUID(), newRoom);
			user.addRoom(newRoom);
			newRoom.refresh();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	/**
	 * get remote room
	 * @param friend the friend user
	 * @return a list of proxy chat room
	 */
	public ArrayList<ProxyChatroom> getRemoteRoom(ProxyUser friend) {
		ArrayList<ProxyChatroom> res = new ArrayList<ProxyChatroom>();
		try {
			Iterable<IChatRoom> rooms = friend.getChatRooms();
			for (IChatRoom room : rooms) {
				res.add(new ProxyChatroom(room));
			}
			return res;

		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * join room
	 * @param newRoom the new chatroom
	 * @param remoteRoom the remote chatroom
	 */
	public void joinRoom(ChatRoom newRoom, IChatRoom remoteRoom) {
		// TODO Auto-generated method stub
		IReceiver receiverStub;
		Receiver receiver = new Receiver(user, newRoom);
		try {
			receiverStub = (IReceiver) UnicastRemoteObject.exportObject(receiver, user.BOUND_PORT);
			//Registry registry = rmiUtils.getLocalRegistry();
			registry.rebind(newRoom.getName(), receiverStub);
			//roomStubs.put(newRoom.getId(), roomStub);
			//newRoom.addIReceiverStub(receiverStub);
			DataPacketCR<IAddReceiverType> data = new DataPacketCR<IAddReceiverType>(IAddReceiverType.class,
					new AddReceiverType(receiverStub), receiverStub);
			for (IReceiver r : remoteRoom.getIReceiverStubs()) {
				System.out.println(r.getUserStub().getName());
				r.receive(data);
				newRoom.addIReceiverStub(r);
			}
			newRoom.addIReceiverStub(receiverStub);
			newRoom.setRelated(receiverStub);
			chatRooms.put(newRoom.getUUID(), newRoom);
			user.addRoom(newRoom);
			receiver.setReceiverStub(receiverStub);
			newRoom.refresh();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//model2viewAdpt.addLocalRoom(newRoom);

	}

	/**
	 * quit the room 
	 * 
	 */
	public void quit() {
		for (ChatRoom room : chatRooms.values()) {
			IReceiver sender = room.getRelated();
			room.sendPacket(new DataPacketCR<IRemoveReceiverType>(IRemoveReceiverType.class,
					new RemoveReceiverType(sender), sender));
		}
		rmiUtils.stopRMI();
		System.exit(0);
	}

	/**
	 * leave the room
	 * @param id the uuid of the room
	 */
	public void leave(UUID id) {
		//model2viewAdpt.leave(chatRooms.get(id).getName());
		chatRooms.remove(id);

	}

	/**
	 * send game view to user2
	 * @param user2 the user2
	 */
	public void sendGameView(ProxyUser user2) {

	}

}
