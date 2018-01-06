package yw29_zl58.client.main.model;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;


import common.*;
import common.datatype.*;
import common.datatype.chatroom.*;
import provided.datapacket.DataPacketAlgo;
import provided.mixedData.MixedDataKey;
import yw29_zl58.client.main.model.cmds.FileCmd;
import yw29_zl58.client.main.model.cmds.ImageCommand;
import yw29_zl58.client.main.model.cmds.StringCommand;
import yw29_zl58.client.main.model.data.ExceptionType;
import yw29_zl58.client.main.model.data.FileType;
import yw29_zl58.client.main.model.data.IStringType;
import yw29_zl58.client.main.model.data.ImageData;
import yw29_zl58.client.main.model.data.InstallCmdType;
import yw29_zl58.client.main.model.data.RequestCmdType;
import yw29_zl58.client.mini.model.ChatRoom;

/**
 * @author zihanli
 *  The user in the chatroom，and the receiver of data packet
 */
public class Receiver implements IReceiver {

	/**
	 * the local receiver stub used to send packages
	 */
	private IReceiver localReceiverStub;
	
	/**
	 * the map of data packet algorithm
	 */
	private DataPacketAlgo<String, String> dataPacketAlgo = new DataPacketAlgo<>(null);
	/**
	 * the chat room of this receiver. this will not be sent to others
	 */
	transient ChatRoom room;
	
	HashMap<MixedDataKey<?>, Object> dir=new HashMap<>();
	/**
	 * the packet which the receiver do not know how to deal with
	 */
	transient HashMap<Class<?>, ArrayList<DataPacketCR<?>>> unknownPacket = new HashMap<Class<?>, ArrayList<DataPacketCR<?>>>();
	/**
	 * the adapter the mini model
	 */
	transient ICRCmd2ModelAdapter _cmd2ModelAdpt = new ICRCmd2ModelAdapter() {

		@Override
		public void appendMsg(String text, String name) {
			// TODO Auto-generated method stub
			room.appendMsg(text, name);

		}

		@Override
		public void buildScrollableComponent(IComponentFactory fac, String label) {
			// TODO Auto-generated method stub
			room.addScrollableComponent(fac.makeComponent(), label);

		}

		@Override
		public void buildNonScrollableComponent(IComponentFactory fac, String label) {
			// TODO Auto-generated method stub
			room.addNonScrollableComponent(fac.makeComponent(), label);

		}


		@Override
		public String getName() {
			// TODO Auto-generated method stub
			try {
				return user.getName();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
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
		public <T extends ICRMessageType> void sendTo(IReceiver target, Class<T> id, T data) {
			// TODO Auto-generated method stub
			try {
				target.receive(new DataPacketCR<T>(id,data,localReceiverStub));
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		@Override
		public <T extends ICRMessageType> void sendToChatRoom(Class<T> id, T data) {
			// TODO Auto-generated method stub
			room.sendPacket(new DataPacketCR<T>(id,data,localReceiverStub));
			
		}

		

	};
	/**
	 * the user of the receiver
	 */
	IUser user;
	/**
	 * the id of this receiver
	 */
	UUID id;

	/**
	 * constructor
	 * @param _user
	 * @param room
	 */
	Receiver(IUser _user, ChatRoom room) {
		this.room = room;
		user = _user;
		id = UUID.randomUUID();
		initialDatapacketAlgo();
	}

	/**
	 * initialize the default datapacketAlgo
	 */
	void initialDatapacketAlgo() {
		dataPacketAlgo.setDefaultCmd(new DataPacketCRAlgoCmd<ICRMessageType>() {

			
			private static final long serialVersionUID = 1L;

			@Override
			public void setCmd2ModelAdpt(ICRCmd2ModelAdapter cmd2ModelAdpt) {
				_cmd2ModelAdpt = cmd2ModelAdpt;
			}

			@Override
			public String apply(Class<?> index, DataPacketCR<ICRMessageType> host, String... params) {
				String name = "unknown host";
				try {
					name = host.getSender().getUserStub().getName();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				_cmd2ModelAdpt.appendMsg("[Receiver] unknown datatype received, default case called", name);
				ArrayList<DataPacketCR<?>> cache = unknownPacket.getOrDefault(index, new ArrayList<>());
				cache.add(host);
				unknownPacket.put(index, cache);
				try {
					host.getSender().receive(new DataPacketCR<IRequestCmdType>(IRequestCmdType.class, new RequestCmdType(index), localReceiverStub));
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return "unknown datatype received";
			}


		});
		dataPacketAlgo.setCmd(IAddReceiverType.class, new DataPacketCRAlgoCmd<IAddReceiverType>() {

			private static final long serialVersionUID = 1L;

			@Override
			public String apply(Class<?> index, DataPacketCR<IAddReceiverType> host, String... params) {
				// TODO Auto-generated method stub

				try {
					room.addIReceiverStub(host.getData().getReceiverStub());
					_cmd2ModelAdpt.appendMsg("Hello everyone!",
							host.getData().getReceiverStub().getUserStub().getName());
					room.refresh();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return null;
			}

			@Override
			public void setCmd2ModelAdpt(ICRCmd2ModelAdapter cmd2ModelAdpt) {
				// TODO Auto-generated method stub

			}

		});
		dataPacketAlgo.setCmd(IRemoveReceiverType.class, new DataPacketCRAlgoCmd<IRemoveReceiverType>() {

			private static final long serialVersionUID = 1L;

			@Override
			public String apply(Class<?> index, DataPacketCR<IRemoveReceiverType> host, String... params) {
				// TODO Auto-generated method stub

				try {
					room.removeIReceiverStub(host.getData().getReceiverStub());
					_cmd2ModelAdpt.appendMsg(" I leave the room. Bye!",
							host.getData().getReceiverStub().getUserStub().getName());
					room.refresh();
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return null;
			}

			@Override
			public void setCmd2ModelAdpt(ICRCmd2ModelAdapter cmd2ModelAdpt) {
				
				
			}
		});
		dataPacketAlgo.setCmd(IRequestCmdType.class, new DataPacketCRAlgoCmd<IRequestCmdType>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String apply(Class<?> index, DataPacketCR<IRequestCmdType> host, String... params) {
				IReceiver sender = host.getSender();
				Class<?> id = host.getData().getCmdId();
				DataPacketCRAlgoCmd<?> cmd = (DataPacketCRAlgoCmd<?>)dataPacketAlgo.getCmd(id);
				if (cmd == null) {
					System.out.println("[Cmd Requested] cmd not found!");
					DataPacketCR<ICRExceptionStatusType> packet = new DataPacketCR<ICRExceptionStatusType>(ICRExceptionStatusType.class, new ExceptionType(host, "[Cmd Requested] cmd not found!"), localReceiverStub);
					try {
						sender.receive(packet);
					} catch (RemoteException e) {
						System.out.println("Exception when sending datapacket to the sender");
						e.printStackTrace();
					}

					return "error";
				}
				try {
					System.out.println("[Cmd Requested] "+ id.getName() +" cmd for " + id + " prepared to be sent to " + sender.getUserStub().getName());
					sender.receive(new DataPacketCR<ICRInstallCmdType>(ICRInstallCmdType.class,new InstallCmdType(host.getData().getCmdId(),(DataPacketCRAlgoCmd<?>)dataPacketAlgo.getCmd(host.getData().getCmdId())),localReceiverStub));
				} catch (RemoteException e) {
					System.out.println("Exception when sending datapacket to the sender");
					e.printStackTrace();
				}
				return "[Cmd Requested] cmd for " + id + " sent to " + sender;
			}

			@Override
			public void setCmd2ModelAdpt(ICRCmd2ModelAdapter cmd2ModelAdpt) {
				// TODO Auto-generated method stub
				
			}


		});
		dataPacketAlgo.setCmd(ICRInstallCmdType.class, new DataPacketCRAlgoCmd<ICRInstallCmdType>(){

			private static final long serialVersionUID = 7941056296706610737L;

			@Override
			public String apply(Class<?> index, DataPacketCR<ICRInstallCmdType> host, String... params) {
				Class<?> id = host.getData().getCmdId();
				DataPacketCRAlgoCmd<?> cmd = host.getData().getCmd();
				cmd.setCmd2ModelAdpt(_cmd2ModelAdpt);
				dataPacketAlgo.setCmd(id, cmd);
				for (DataPacketCR<?> packet : unknownPacket.get(id)) {
					packet.execute(dataPacketAlgo);
				}
				return "[Install Cmd] Cmd for data " + id + "installed and cached packet executed"; 
			}

			@Override
			public void setCmd2ModelAdpt(ICRCmd2ModelAdapter cmd2ModelAdpt) {
				// TODO Auto-generated method stub
				
			}

			});
		
		StringCommand stringCommand = new StringCommand();
		stringCommand.setCmd2ModelAdpt(_cmd2ModelAdpt);
		dataPacketAlgo.setCmd(IStringType.class, stringCommand);
		ImageCommand imageCommand = new ImageCommand();
		imageCommand.setCmd2ModelAdpt(_cmd2ModelAdpt);
		dataPacketAlgo.setCmd(ImageData.class, imageCommand);
		FileCmd fileCmd = new FileCmd();
		fileCmd.setCmd2ModelAdpt(_cmd2ModelAdpt);
		dataPacketAlgo.setCmd(FileType.class, fileCmd);
		//dataPacketAlgo.setCmd(idx, cmd);
	}

	/**(non-Javadoc)
	 * @see common.IReceiver#getUserStub()
	 * get the userStub of this receiver
	 */
	@Override
	public IUser getUserStub() throws RemoteException {
		// TODO Auto-generated method stub
		return user;
	}

	/** (non-Javadoc)
	 * @see common.IReceiver#getUUID()
	 * get the id og this receiver
	 */
	@Override
	public UUID getUUID() throws RemoteException {
		// TODO Auto-generated method stub
		return id;
	}

	/** (non-Javadoc)
	 * other's call to let this receiver to receive data packet
	 */
	@Override
	public <T extends ICRMessageType> void receive(DataPacketCR<T> data) throws RemoteException {
		data.execute(dataPacketAlgo);
		
	}

	/**
	 * set the receiver stub of the receiver
	 * @param stub new receiver
	 */
	public void setReceiverStub(IReceiver stub) {
		this.localReceiverStub = stub;
	}
}
