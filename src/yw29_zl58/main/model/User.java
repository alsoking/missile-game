package yw29_zl58.main.model;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import javax.swing.Timer;
import common.DataPacketCR;
import common.DataPacketUser;
import common.DataPacketUserAlgoCmd;
import common.IChatRoom;
import common.IComponentFactory;
import common.IReceiver;
import common.IUser;
import common.IUserCmd2ModelAdapter;
import common.IUserMessageType;
import common.datatype.IRequestCmdType;
import common.datatype.user.IInvitationType;
import common.datatype.user.IUserInstallCmdType;
import provided.datapacket.DataPacketAlgo;
import provided.mixedData.MixedDataKey;
import yw29_zl58.main.model.data.IStringType;
import yw29_zl58.game.GameQuitCmd;
import yw29_zl58.game.GameQuitType;
import yw29_zl58.game.GameTaskCmd;
import yw29_zl58.game.GameTaskType;
import yw29_zl58.game.LaunchMissileType;
import yw29_zl58.game.ScoreCmd;
import yw29_zl58.game.ScoreType;
import yw29_zl58.game.ServerMessageCmd;
import yw29_zl58.game.ServerMessageType;
import yw29_zl58.main.model.data.GameViewCmd;
import yw29_zl58.main.model.data.GameViewType;
import yw29_zl58.main.model.data.InvitationType;
import yw29_zl58.main.model.data.RequestCmdType;
import yw29_zl58.main.model.data.UserInstallCmdDataType;

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
	HashMap<String,IChatRoom> chatRooms = new HashMap<String,IChatRoom>();
	HashMap<String,IReceiver> receivers = new HashMap<String,IReceiver>();
	boolean temp_A=false,temp_B=false;
	IUser localUserStub;
	
	/**
	 * game related fields
	 */
	int number_A=0;
	int number_B=0;
	
	int score_A = 0;
	int score_B = 0;
	
	boolean launched_A = false;
	boolean launched_B = false;
	
	Set<IUser> members_A = new HashSet<>();
	Set<IUser> members_B = new HashSet<>();
	
	List<String> targets = new ArrayList<>();
	int currentTarget = 0;
	
	Map<String, double[]> targetPosition = new HashMap<>();
	
	
	
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
	int _timeSlice = 60000; 
	Timer _timer = new Timer(_timeSlice, (e) ->{
		launched_A=false;
		launched_B=false;
		currentTarget++;
		if(currentTarget>=5)
		{
			quitGame();
		}
		else
		{
			for(IUser player:friends)
			{
				try {
					player.receive(new DataPacketUser<GameTaskType>(GameTaskType.class,new GameTaskType(targets.get(currentTarget)),localUserStub));
					player.receive(new DataPacketUser<ServerMessageType>(ServerMessageType.class,new ServerMessageType("new task sent! right click to launch missile"),localUserStub));
					
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	} ) ;
	
	/**
	 * Cmd to model adapter
	 */
	//ICmd2ModelAdapter _cmd2ModelAdpt;
	/**
	 * UUID
	 */
	UUID id;
	/**
	 * the map of data packet algorithm
	 */
	HashMap<MixedDataKey<?>, Object> dir=new HashMap<>();
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
			// TODO Auto-generated method stub
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
		targets.add("Beijing");
		targets.add("France");
		targets.add("Iraq");
		targets.add("Florida");
		targets.add("Taiwan");
		
		targetPosition.put("Beijing", new double[]{39.9042, 116.4074});
		targetPosition.put("France", new double[]{46.2276, 2.2137});
		targetPosition.put("Iraq", new double[]{33.2232, 43.6793});
		targetPosition.put("Florida", new double[]{27.6648, -81.5158});
		targetPosition.put("Taiwan", new double[]{23.6978, 120.9605});
	}

	/**
	 * add a room
	 * @param room the new room
	 * @param receiver the receiver of the room
	 */
	public void addRoom(IChatRoom room,IReceiver receiver) {
		chatRooms.put(room.getName(),room);
		receivers.put(room.getName(),receiver);
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
		//return this.chatRooms;
		return null;
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
		
		//userStub.receive(new DataPacketUser<GameViewType>(GameViewType.class, new GameViewType(), userStub));
		
		userStub.receive(new DataPacketUser<IInvitationType>(IInvitationType.class,new InvitationType(chatRooms.get("lobby")) ,localUserStub));
		if(number_A<=number_B)
		{
			number_A++;
			userStub.receive(new DataPacketUser<IInvitationType>(IInvitationType.class,new InvitationType(chatRooms.get("team_A")) ,localUserStub));
			members_A.add(userStub);
		}
		else
		{
			number_B++;
			userStub.receive(new DataPacketUser<IInvitationType>(IInvitationType.class,new InvitationType(chatRooms.get("team_B")) ,localUserStub));
			members_B.add(userStub);
		}
		

	}


	void initialDatapacketAlgo() {
		
		dataPacketAlgo.setDefaultCmd(new DataPacketUserAlgoCmd<IUserMessageType>() {
			
			private static final long serialVersionUID = 1L;

			@Override
			public void setCmd2ModelAdpt(IUserCmd2ModelAdapter cmd2ModelAdpt) {
				//_cmd2ModelAdpt = cmd2ModelAdpt;
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
		
		dataPacketAlgo.setCmd(LaunchMissileType.class, new DataPacketUserAlgoCmd<LaunchMissileType>(){
			
			private static final long serialVersionUID = 1L;

			@Override
			public String apply(Class<?> index, DataPacketUser<LaunchMissileType> host, String... params) {
				_2ModelAdpt.append("one missile received!");
				IUser sender = host.getSender();
				double[] position = host.getData().getPosition();
				calculateScore(sender, position);
				return "[server] score calculated!";
			}

			@Override
			public void setCmd2ModelAdpt(IUserCmd2ModelAdapter cmd2ModelAdpt) {
				
			}});

		
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
		GameViewCmd gameCmd=new GameViewCmd();
		gameCmd.setCmd2ModelAdpt(cmd2ModelAdpt);
		dataPacketAlgo.setCmd(GameViewType.class, gameCmd);
		GameTaskCmd gameTaskCmd=new GameTaskCmd();
		gameTaskCmd.setCmd2ModelAdpt(cmd2ModelAdpt);
		dataPacketAlgo.setCmd(GameTaskType.class, gameTaskCmd);

		GameQuitCmd gameQuitCmd=new GameQuitCmd();
		gameQuitCmd.setCmd2ModelAdpt(cmd2ModelAdpt);
		dataPacketAlgo.setCmd(GameQuitType.class, gameQuitCmd);

		ScoreCmd scoreCmd = new ScoreCmd();
		scoreCmd.setCmd2ModelAdpt(cmd2ModelAdpt);
		dataPacketAlgo.setCmd(ScoreType.class, scoreCmd);
		ServerMessageCmd serverMessageCmd = new ServerMessageCmd();
		serverMessageCmd.setCmd2ModelAdpt(cmd2ModelAdpt);
		dataPacketAlgo.setCmd(ServerMessageType.class, serverMessageCmd);

	}

	@Override
	public <T extends IUserMessageType> void receive(DataPacketUser<T> data) throws RemoteException {
		data.execute(dataPacketAlgo, "123");
	}
	
	public ArrayList<IUser> getFriends()
	{
		return friends;
	}
	
	public void gameStart()
	{
		chatRooms.get("lobby").sendPacket(new DataPacketCR<IStringType>(IStringType.class,new IStringType("Game starts and the targets are geographical names such as countries, regions, provinces, cities, etc. The string will be displayed on the client's GUI. Then the players in a team will need to chat inside the team to decide where should they click on a map location and right click to launch missile. Each team can only launch one time.  After both team launching or it is 60sec after the target being shown,the next target will be shown."),receivers.get("lobby")));
		for(IUser player:friends)
		{
			
			try {
				player.receive(new DataPacketUser<GameTaskType>(GameTaskType.class,new GameTaskType(targets.get(0)),localUserStub));
				player.receive(new DataPacketUser<ServerMessageType>(ServerMessageType.class,new ServerMessageType("game start! right click to launch missile"),localUserStub));
				
			} catch (RemoteException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		//currentTarget++;
		
		
		_timer.start();
	}
	

	/**
	 * algorithm to calculate the team score based on their launch
	 * @param sender
	 * @param position
	 */
	private void calculateScore(IUser sender, double[] position) {
		_2ModelAdpt.append("position got!" + position[0] + position[1]);
		// if the sender is from team A
		if (members_A.contains(sender)) {
			if (!launched_A) {
				if (isHit(position, currentTarget)) {score_A++;}
				launched_A = true;
				_2ModelAdpt.append("teamA hit!");
				for(IUser client:members_A)
				{try {
					client.receive(new DataPacketUser<ServerMessageType>(ServerMessageType.class,new ServerMessageType("Your team has already launched"),localUserStub));
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}}
				
			} else {
				// tell the sender that your team mate has launched the game
				//sender.receive(null);
				_2ModelAdpt.append("teamA not hit!");
			}
		}
	
		// if the sender is from team B
		if (members_B.contains(sender)){
			if (!launched_B) {
				if (isHit(position, currentTarget)) {score_B++;}
				launched_B = true;
				_2ModelAdpt.append("teamB hit!");
				for(IUser client:members_B)
				{try {
					client.receive(new DataPacketUser<ServerMessageType>(ServerMessageType.class,new ServerMessageType("Your team has already launched"),localUserStub));
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}}
			} else {
				// tell the sender that your team mate has launched the game
				_2ModelAdpt.append("teamB not hit!");
				//sender.receive(null);
			}
		}
		
		//update the two team scores
		for (IUser client : friends) {
			try {
				client.receive(new DataPacketUser<ScoreType>(ScoreType.class, new ScoreType(score_A, score_B), localUserStub));
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		// check if team A and B both have launched. If so, start a new task or end the game
		if (launched_A && launched_B) {
			currentTarget++;
			if (currentTarget >= 5) {
				// stop the game send the stop package
				_2ModelAdpt.append("game over");
				quitGame();
			} else {
				launched_A=false;
				launched_B=false;
				for (IUser client : friends) {
					try {
						client.receive(new DataPacketUser<GameTaskType>(GameTaskType.class, new GameTaskType(targets.get(currentTarget)), localUserStub));
						client.receive(new DataPacketUser<ServerMessageType>(ServerMessageType.class,new ServerMessageType("new task sent! right click to launch missile"),localUserStub));
						_2ModelAdpt.append("new task start!");
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				_timer.stop();
				_timer.start();
			}
		}		
	}
	
	
	private boolean isHit(double[] position, int currentTarget2) {
		
		String currTarget = targets.get(currentTarget2);
		double[] answer = targetPosition.get(currTarget);
		if (Math.pow(position[0] - answer[0], 2) + Math.pow(position[1] - answer[1], 2) <= 30 ) {
			return true;
		}
		return false;
	}
	
	public void quitGame()
	{
		currentTarget=0;
		_timer.stop();;
		if(score_B==score_A)
		{
			chatRooms.get("team_A").sendPacket(new DataPacketCR<IStringType>(IStringType.class,new IStringType("Game ends in a draw!"),receivers.get("team_A")));
			chatRooms.get("team_B").sendPacket(new DataPacketCR<IStringType>(IStringType.class,new IStringType("Game ends in a draw!"),receivers.get("team_B")));
			chatRooms.get("lobby").sendPacket(new DataPacketCR<IStringType>(IStringType.class,new IStringType("Game ends in a draw!"),receivers.get("lobby")));
		}
		if(score_B>score_A)
		{
			chatRooms.get("team_A").sendPacket(new DataPacketCR<IStringType>(IStringType.class,new IStringType("You loose the game!"),receivers.get("team_A")));
			chatRooms.get("team_B").sendPacket(new DataPacketCR<IStringType>(IStringType.class,new IStringType("You win the game!"),receivers.get("team_B")));
			chatRooms.get("lobby").sendPacket(new DataPacketCR<IStringType>(IStringType.class,new IStringType("Team B win the game!"),receivers.get("lobby")));
		}
		if(score_B<score_A)
		{
			chatRooms.get("team_A").sendPacket(new DataPacketCR<IStringType>(IStringType.class,new IStringType("You win the game!"),receivers.get("team_A")));
			chatRooms.get("team_B").sendPacket(new DataPacketCR<IStringType>(IStringType.class,new IStringType("You loose the game!"),receivers.get("team_B")));
			chatRooms.get("lobby").sendPacket(new DataPacketCR<IStringType>(IStringType.class,new IStringType("Team A win the game!"),receivers.get("lobby")));
		}
		
		for(IUser client : friends)
		{
			try {
				client.receive(new DataPacketUser<GameQuitType>(GameQuitType.class,new GameQuitType(),localUserStub));
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}




}
