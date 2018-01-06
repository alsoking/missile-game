package yw29_zl58.game;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import common.ICmd2ModelAdapter;
import common.IUser;
import common.IUserMessageType;
import gov.nasa.worldwind.geom.Position;
import map.IRightClickAction;
import map.MapPanel;

import javax.swing.JLabel;
import javax.swing.JFrame;


public class GameView extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2861872648742003732L;
	
	/**
	 * the adapter for the view to call and send data package
	 */
	// give it a default value first
	private ICmd2ModelAdapter<IUser, IUserMessageType> _2ModelAdpt;
	
	/**
	 * the user stub of the server
	 */
	private IUser serverStub;
	
	private final JPanel pnlInfo = new JPanel();
	private final JTextField txtTask = new JTextField();
	private final JPanel pnlTask = new JPanel();
	private final JPanel pnlScore = new JPanel();
	private final JTextField txtTeamBScore = new JTextField();
	private final JTextField txtTeamAScore = new JTextField();
	private final JLabel lblTeamA = new JLabel("Team_A");
	private final JPanel pnlTeamA = new JPanel();
	private final JPanel pnlTeamB = new JPanel();
	private final JLabel lblTeamB = new JLabel("Team_B");
	private final MapPanel pnlMap = new MapPanel();
	private final JPanel pnlServerMsg = new JPanel();
	private final JTextField txtServerMsg = new JTextField();

	/**
	 * create the panel
	 * @param serverStub the user stub of the server
	 * @param _2ModelAdpt the cmd2model adapter
	 */
	public GameView(IUser serverStub, ICmd2ModelAdapter<IUser, IUserMessageType> _2ModelAdpt) {
		txtServerMsg.setColumns(10);
		
		this.serverStub = serverStub;
		this._2ModelAdpt = _2ModelAdpt;
		
		setLayout(new BorderLayout(0, 0));
		
		add(pnlInfo, BorderLayout.NORTH);
		pnlTask.setBorder(new TitledBorder(null, "Current Task", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		pnlInfo.add(pnlTask);
		pnlTask.add(txtTask);
		txtTask.setColumns(10);
		pnlScore.setBorder(new TitledBorder(null, "Scores", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		pnlInfo.add(pnlScore);
		pnlScore.setLayout(new BorderLayout(0, 0));
		pnlScore.add(pnlTeamA, BorderLayout.NORTH);
		pnlTeamA.add(lblTeamA);
		txtTeamAScore.setText("0");
		pnlTeamA.add(txtTeamAScore);
		txtTeamAScore.setColumns(10);
		pnlScore.add(pnlTeamB, BorderLayout.SOUTH);
		
		pnlTeamB.add(lblTeamB);
		pnlTeamB.add(txtTeamBScore);
		txtTeamBScore.setText("0");
		txtTeamBScore.setColumns(10);
		
		add(pnlMap, BorderLayout.CENTER);
		pnlServerMsg.setBorder(new TitledBorder(null, "Server's message", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		add(pnlServerMsg, BorderLayout.SOUTH);
		pnlServerMsg.setLayout(new BorderLayout(0, 0));
		
		pnlServerMsg.add(txtServerMsg);
		pnlMap.start();

	}
	
	/**
	 * display the new task the server gives to the clients
	 * @param task the new task
	 */
	public void displayTask(String task) {
		txtTask.setText(task);
	}
	
	/**
	 * display the updated current score of B team
	 * @param score the new score of team A
	 */
	public void displayTeamASore(String score) {
		txtTeamAScore.setText(score);
	}
	
	/**
	 * display the updated current score of the B team
	 * @param score the new score of team B
	 */
	public void displayTeamBSore(String score) {
		txtTeamBScore.setText(score);
	}
	
	/**
	 * display the message from server
	 * @param msg the tring msg
	 */
	public void displayServerMsg(String msg) {
		txtServerMsg.setText(msg);
	}
	
	/**
	 * set the adapter
	 * @param adpt the cmd to model adapter
	 */
	public void setModelAdapter(ICmd2ModelAdapter<IUser, IUserMessageType> adpt) {
		this._2ModelAdpt = adpt;
		pnlMap.addRightClickAction(new IRightClickAction(){

			@Override
			public void apply(Position p) {
				double lon = p.getLongitude().degrees;
				double lat = p.getLatitude().degrees;
				double[] coor = new double[]{lat, lon};
				_2ModelAdpt.appendMsg("You Launched a Missile!", "Server");
				_2ModelAdpt.sendTo(serverStub, LaunchMissileType.class, new LaunchMissileType(coor));
			}});
	}

}

