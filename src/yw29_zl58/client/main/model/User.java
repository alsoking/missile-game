package yw29_zl58.client.main.model;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import common.DataPacketUser;
import common.DataPacketUserAlgoCmd;

import common.IChatRoom;
import common.ICmd2ModelAdapter;
import common.IComponentFactory;
import common.IUser;
import common.IUserCmd2ModelAdapter;
import common.IUserMessageType;
import common.datatype.IRequestCmdType;
import common.datatype.user.IInvitationType;
import common.datatype.user.IUserInstallCmdType;
import provided.datapacket.DataPacketAlgo;
import provided.mixedData.MixedDataKey;
import yw29_zl58.client.main.model.data.RequestCmdType;
import yw29_zl58.client.main.model.data.UserInstallCmdDataType;

/**
 * 
 * User implement IUser interface
 *
 */
/**
 * @author zihanli
 * the User
 */
public class User implements IUser {
	/**
	 * chatrooms
	 */
	HashSet<IChatRoom> chatRooms = new HashSet<IChatRoom>();
	
	IUser localUserStub;
	
	
	/**
	 * list of friends
	 */
	ArrayList<IUser> friends = new ArrayList<IUser>();
	/**
	 * BOUND_NAME
	 */
	final String BOUND_NAME1;
	/**
	 * IP
	 */
	String IP;
	/**
	 * BOUND_PORT;
	 */
	final int BOUND_PORT;
	/**
	 * User2ModelAdapter
	 */
	User2ModelAdapter _2ModelAdpt;
	
	/**
	 * Cmd to model adapter
	 */
	ICmd2ModelAdapter<IUser, IUserMessageType> _cmd2ModelAdpt;
	/**
	 * UUID
	 */
	UUID id;
	
	HashMap<MixedDataKey<?>, Object> dir=new HashMap<>();
	/**
	 * the map of data packet algorithm
	 */
	private DataPacketAlgo<String, String> dataPacketAlgo = new DataPacketAlgo<>(null);
	transient HashMap<Class<?>, ArrayList<DataPacketUser<?>>> unknownPacket = new HashMap<Class<?>, ArrayList<DataPacketUser<?>>>();
	transient IUserCmd2ModelAdapter cmd2ModelAdpt = new IUserCmd2ModelAdapter() {

		@Override
		public void appendMsg(String text, String name) {
			// TODO Auto-generated method stub
			
			System.out.println(name+":"+text);

		}

		@Override
		public void buildScrollableComponent(IComponentFactory fac, String label) {
			// TODO Auto-generated method stub

			_2ModelAdpt.addPanel(fac.makeComponent(), label);

		}

		@Override
		public void buildNonScrollableComponent(IComponentFactory fac, String label) {
			// TODO Auto-generated method stub
			//MapPanel map=(MapPanel)fac.makeComponent();
			//map.start();
			_2ModelAdpt.addPanel(fac.makeComponent(), label);

		}

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return getName();
		}

		@Override
		public <T> T put(MixedDataKey<T> key, T value) {
			// TODO Auto-generated method stub
			dir.put(key, value);
			return null;
		}

		@SuppressWarnings("unchecked")
		@Override
		public <T> T get(MixedDataKey<T> key) {
			// TODO Auto-generated method stub
			return (T) dir.get(key);
		}

		

		@Override
		public <T extends IUserMessageType> void sendTo(IUser target, Class<T> id, T data) {
			try {
				target.receive(new DataPacketUser<T>(id, data, localUserStub));
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	};

	/**
	 * constructor
	 * @param name
	 * @param port
	 * @param _modelAdpt
	 */
	User(String name, int port, User2ModelAdapter _modelAdpt) {
		_2ModelAdpt = _modelAdpt;
		BOUND_NAME1 = name;
		BOUND_PORT = port;
		id = UUID.randomUUID();
	}

	/**
	 * add room
	 * @param room
	 */
	/**
	 * @param room 
	 * add a room to the nao
	 */
	public void addRoom(IChatRoom room) {
		chatRooms.add(room);
	}
	
	public void setUserStub(IUser stub)
	{
		this.localUserStub=stub;
	}

	/**
	 * @param _IP
	 * set the IP of the user
	 */
	public void setIP(String _IP) {
		IP = _IP;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return BOUND_NAME1;
	}

	/**
	 * join room
	 * @param room
	 * @return
	 * @throws RemoteException
	 */

	/**
	 * quit room
	 * @param room
	 * @return
	 * @throws RemoteException
	 */

	/**
	 * get name
	 * @return BOUND_NAME1
	 */
	/** (non-Javadoc)
	 * @see common.IUser#getName()
	 * get the name of the user
	 */
	@Override
	public String getName() throws RemoteException {
		// TODO Auto-generated method stub
		return BOUND_NAME1;
	}

	/**
	 * get chat room
	 * @return chat rooms
	 * @throws RemoteException
	 */
	/** (non-Javadoc)
	 * @see common.IUser#getChatRooms()
	 * get the chatrooms of the user
	 */
	@Override
	public Collection<IChatRoom> getChatRooms() throws RemoteException {
		// TODO Auto-generated method stub
		return this.chatRooms;
	}

	/**
	 * get UUID
	 * @return UUID
	 * @throws RemoteException
	 */
	/** (non-Javadoc)
	 * @see common.IUser#getUUID()
	 * get the id of the suer
	 */
	@Override
	public UUID getUUID() throws RemoteException {
		// TODO Auto-generated method stub
		return id;
	}

	/**
	 * connect to user
	 * @param userstub
	 * @throws RemoteException
	 */
	/** (non-Javadoc)
	 * @see common.IUser#connect(common.IUser)
	 * connect to a user
	 */
	@Override
	public void connect(IUser userStub) throws RemoteException {
		// TODO Auto-generated method stub
		friends.add(userStub);
		_2ModelAdpt.addfriend(userStub);

	}


	void initialDatapacketAlgo() {
		
		dataPacketAlgo.setDefaultCmd(new DataPacketUserAlgoCmd<IUserMessageType>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void setCmd2ModelAdpt(IUserCmd2ModelAdapter cmd2ModelAdpt) {
				_cmd2ModelAdpt = cmd2ModelAdpt;
			}

			@Override
			public String apply(Class<?> index, DataPacketUser<IUserMessageType> host, String... params) {
				String name = "unknown host";
				try {
					name = host.getSender().getName();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				cmd2ModelAdpt.appendMsg("[Receiver] unknown datatype received, default case called", name);
				ArrayList<DataPacketUser<?>> cache = unknownPacket.getOrDefault(index, new ArrayList<>());
				cache.add(host);
				unknownPacket.put(index, cache);
				try {
					host.getSender().receive(new DataPacketUser<IRequestCmdType>(IRequestCmdType.class, new RequestCmdType(index), localUserStub));
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return "unknown datatype received";
			}


		});
		
	
		
		dataPacketAlgo.setCmd(IInvitationType.class, new DataPacketUserAlgoCmd<IInvitationType>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String apply(Class<?> index, DataPacketUser<IInvitationType> host, String... params) {
				// TODO Auto-generated method stub
				new Thread() {
					public void run() {
						super.run();
						IChatRoom room = host.getData().getChatRoom();
						//cmd2ModelAdpt.buildNonScrollableComponent(view, "game view");
						_2ModelAdpt.joinRoom(room);
					}
				}.start();
				
				return null;
			}

			@Override
			public void setCmd2ModelAdpt(IUserCmd2ModelAdapter cmd2ModelAdpt) {
				// TODO Auto-generated method stub
				
			}
		});
		dataPacketAlgo.setCmd(IUserInstallCmdType.class, new DataPacketUserAlgoCmd<IUserInstallCmdType>(){

			private static final long serialVersionUID = 7941056296706610737L;

			@Override
			public String apply(Class<?> index, DataPacketUser<IUserInstallCmdType> host, String... params) {
				Class<?> id = host.getData().getCmdId();
				DataPacketUserAlgoCmd<?> cmd = host.getData().getCmd();
				cmd.setCmd2ModelAdpt(cmd2ModelAdpt);
				dataPacketAlgo.setCmd(id, cmd);
				for (DataPacketUser<?> packet : unknownPacket.get(id)) {
					packet.execute(dataPacketAlgo);
				}
				return "[Install Cmd] Cmd for data " + id + "installed and cached packet executed"; 
			}

			@Override
			public void setCmd2ModelAdpt(IUserCmd2ModelAdapter cmd2ModelAdpt) {
				// TODO Auto-generated method stub
				
			}

			});
		
		dataPacketAlgo.setCmd(IRequestCmdType.class, new DataPacketUserAlgoCmd<IRequestCmdType>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String apply(Class<?> index, DataPacketUser<IRequestCmdType> host, String... params) {
				IUser sender = host.getSender();
				Class<?> id = host.getData().getCmdId();
				DataPacketUserAlgoCmd<?> cmd = (DataPacketUserAlgoCmd<?>)dataPacketAlgo.getCmd(id);
				if (cmd == null) {
					System.out.println("[Cmd Requested] cmd not found!");

					return "error";
				}
				new Thread() {
					public void run() {
						super.run();
						try {
							System.out.println("[Cmd Requested] "+ id.getName() +" cmd for " + id + " prepared to be sent to " + sender.getName());
						} catch (RemoteException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						IUserInstallCmdType cmdData = new UserInstallCmdDataType(id, cmd);
						DataPacketUser<IUserInstallCmdType> packet = new DataPacketUser<IUserInstallCmdType>(IUserInstallCmdType.class, cmdData, localUserStub);
						try {
							sender.receive(packet);
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}.start();
				return "[Cmd Requested] cmd for " + id + " sent to " + sender;
			}

			@Override
			public void setCmd2ModelAdpt(IUserCmd2ModelAdapter cmd2ModelAdpt) {
				// TODO Auto-generated method stub
				
			}


		});
	}
	
	

	@Override
	public <T extends IUserMessageType> void receive(DataPacketUser<T> data) throws RemoteException {
		data.execute(dataPacketAlgo, "123");
	}



}
