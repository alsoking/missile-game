package yw29_zl58.client.main.view;

import java.awt.Component;
//import java.awt.BorderLayout;
//import java.awt.Component;
import java.awt.Dimension;
//import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import common.IChatRoom;
//import mini.model.ChatRoom;
import map.MapPanel;
import yw29_zl58.client.main.model.ProxyChatroom;
import yw29_zl58.client.main.model.MainModel.ProxyUser;

import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
//import java.rmi.RemoteException;
import java.util.ArrayList;
//import java.util.HashSet;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;

/**
 * @author zihanli
 * the view of the chat app
 *
 */
public class MainView extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final JPanel panel = new JPanel();
	private final JPanel panel_1 = new JPanel();
	private final JLabel lblUserNameLabel = new JLabel("UserName");
	private final JLabel lblServeName = new JLabel("Serve Name");
	private final JTextField txtUserName = new JTextField();
	private final JTextField txtServerIp = new JTextField();
	private final JButton btnStart = new JButton("Start");
	private final JPanel panel_2 = new JPanel();
	private final JTextField txtChatroomName = new JTextField();
	private final JButton btnCreateARoom = new JButton("Create a Room");
	private final JPanel panel_3 = new JPanel();
	private final JPanel panel_4 = new JPanel();
	private final JTextField txtFriendName = new JTextField();
	private final JButton btnConnect = new JButton("Connect");
	private final JTextField txtFriendIp = new JTextField();
	private final JPanel panel_5 = new JPanel();
	private final JComboBox<ProxyUser> FriendsComboBox1 = new JComboBox<ProxyUser>();
	private final JPanel panel_6 = new JPanel();
	private final JButton btnRequest = new JButton("Request");
	private final JComboBox<ProxyChatroom> RemoteRoomComboBox = new JComboBox<ProxyChatroom>();
	private final JButton btnExistAndQuit = new JButton("Exist and Quit");
	private final JPanel panel_8 = new JPanel();
	private final JTabbedPane ChatroomPane = new JTabbedPane(JTabbedPane.TOP);
	private final JTabbedPane tabPane = new JTabbedPane(JTabbedPane.TOP);
	private final JTextArea info = new JTextArea();
	private final JScrollPane scroll_info = new JScrollPane(info);
	private MainView2ModelAdapter view2modelAdpt;
	private final JButton btnGetRoom = new JButton("Get Room");

	/**
	 * show the frame
	 */
	public void start() {
		setVisible(true);
	}

	/**
	 * create the view
	 * @param _view2modelAdpt the view to model adapter
	 */
	public MainView(MainView2ModelAdapter _view2modelAdpt) {
		view2modelAdpt = _view2modelAdpt;
		txtFriendIp.setToolTipText("IP of the friend");
		txtFriendIp.setText("Friend IP");
		txtFriendIp.setColumns(10);
		txtFriendName.setToolTipText("name of the friend");
		txtFriendName.setText("Friend Name");
		txtFriendName.setColumns(10);
		txtChatroomName.setToolTipText("the name of the chatroom");
		txtChatroomName.setText("Chatroom Name");
		txtChatroomName.setColumns(10);
		txtServerIp.setToolTipText("just input the port of the client");
		txtServerIp.setText("Server port");
		txtServerIp.setColumns(10);
		txtUserName.setToolTipText("input the user name");
		txtUserName.setText("User Name");
		txtUserName.setColumns(10);
		initGUI();
	}

	/**
	 * initialize all of the GUI components
	 */
	private void initGUI() {
		info.setToolTipText("the information of the system");
		info.setColumns(75);
		info.setRows(25);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1014, 300);
		this.setSize(1200, 700);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 1004, 0 };
		gbl_contentPane.rowHeights = new int[] { 111, 0, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 5, 0);
		gbc_panel.anchor = GridBagConstraints.NORTH;
		gbc_panel.fill = GridBagConstraints.HORIZONTAL;
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		contentPane.add(panel, gbc_panel);

		panel.add(panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 0, 64, 0 };
		gbl_panel_1.rowHeights = new int[] { 0, 16, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, 1.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);

		GridBagConstraints gbc_lblUserNameLabel = new GridBagConstraints();
		gbc_lblUserNameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblUserNameLabel.anchor = GridBagConstraints.EAST;
		gbc_lblUserNameLabel.gridx = 0;
		gbc_lblUserNameLabel.gridy = 0;
		panel_1.add(lblUserNameLabel, gbc_lblUserNameLabel);

		GridBagConstraints gbc_txtUserName = new GridBagConstraints();
		gbc_txtUserName.insets = new Insets(0, 0, 5, 0);
		gbc_txtUserName.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtUserName.gridx = 1;
		gbc_txtUserName.gridy = 0;
		panel_1.add(txtUserName, gbc_txtUserName);
		GridBagConstraints gbc_lblServeName = new GridBagConstraints();
		gbc_lblServeName.anchor = GridBagConstraints.EAST;
		gbc_lblServeName.insets = new Insets(0, 0, 0, 5);
		gbc_lblServeName.gridx = 0;
		gbc_lblServeName.gridy = 1;
		panel_1.add(lblServeName, gbc_lblServeName);

		GridBagConstraints gbc_txtServerIp = new GridBagConstraints();
		gbc_txtServerIp.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtServerIp.gridx = 1;
		gbc_txtServerIp.gridy = 1;
		panel_1.add(txtServerIp, gbc_txtServerIp);
		btnStart.setToolTipText("click here to start the system");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txtUserName.getText();
				int port = Integer.parseInt(txtServerIp.getText());
				view2modelAdpt.start(name, port);

			}
		});

		panel.add(btnStart);

		panel.add(panel_2);
		GridBagLayout gbl_panel_2 = new GridBagLayout();
		gbl_panel_2.columnWidths = new int[] { 130, 0 };
		gbl_panel_2.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel_2.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panel_2.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel_2.setLayout(gbl_panel_2);

		GridBagConstraints gbc_txtChatroomName = new GridBagConstraints();
		gbc_txtChatroomName.insets = new Insets(0, 0, 5, 0);
		gbc_txtChatroomName.anchor = GridBagConstraints.NORTHWEST;
		gbc_txtChatroomName.gridx = 0;
		gbc_txtChatroomName.gridy = 0;
		panel_2.add(txtChatroomName, gbc_txtChatroomName);

		GridBagConstraints gbc_btnCreateARoom = new GridBagConstraints();
		gbc_btnCreateARoom.gridx = 0;
		gbc_btnCreateARoom.gridy = 1;
		btnCreateARoom.setToolTipText("click here to create a room");
		btnCreateARoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view2modelAdpt.createRoom(txtChatroomName.getText());
			}
		});
		panel_2.add(btnCreateARoom, gbc_btnCreateARoom);

		panel.add(panel_3);

		panel_3.add(panel_4);
		GridBagLayout gbl_panel_4 = new GridBagLayout();
		gbl_panel_4.columnWidths = new int[] { 130, 0 };
		gbl_panel_4.rowHeights = new int[] { 0, 0, 26, 0 };
		gbl_panel_4.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_4.rowWeights = new double[] { 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_4.setLayout(gbl_panel_4);

		GridBagConstraints gbc_btnConnect = new GridBagConstraints();
		gbc_btnConnect.insets = new Insets(0, 0, 5, 0);
		gbc_btnConnect.gridx = 0;
		gbc_btnConnect.gridy = 0;
		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view2modelAdpt.connectTo(txtFriendName.getText(), txtFriendIp.getText());
			}
		});
		panel_4.add(btnConnect, gbc_btnConnect);

		GridBagConstraints gbc_txtFriendIp = new GridBagConstraints();
		gbc_txtFriendIp.insets = new Insets(0, 0, 5, 0);
		gbc_txtFriendIp.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtFriendIp.gridx = 0;
		gbc_txtFriendIp.gridy = 1;
		panel_4.add(txtFriendIp, gbc_txtFriendIp);

		GridBagConstraints gbc_txtFriendName = new GridBagConstraints();
		gbc_txtFriendName.anchor = GridBagConstraints.NORTHWEST;
		gbc_txtFriendName.gridx = 0;
		gbc_txtFriendName.gridy = 2;
		panel_4.add(txtFriendName, gbc_txtFriendName);

		panel_3.add(panel_5);
		GridBagLayout gbl_panel_5 = new GridBagLayout();
		gbl_panel_5.columnWidths = new int[] { 52, 0 };
		gbl_panel_5.rowHeights = new int[] { 0, 0, 0 };
		gbl_panel_5.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_5.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel_5.setLayout(gbl_panel_5);

		GridBagConstraints gbc_FriendsComboBox1 = new GridBagConstraints();
		gbc_FriendsComboBox1.insets = new Insets(0, 0, 5, 0);
		gbc_FriendsComboBox1.fill = GridBagConstraints.HORIZONTAL;
		gbc_FriendsComboBox1.gridx = 0;
		gbc_FriendsComboBox1.gridy = 0;
		FriendsComboBox1.setToolTipText("the list of your friend");
		panel_5.add(FriendsComboBox1, gbc_FriendsComboBox1);

		GridBagConstraints gbc_btnGetRoom = new GridBagConstraints();
		gbc_btnGetRoom.gridx = 0;
		gbc_btnGetRoom.gridy = 1;
		btnGetRoom.setToolTipText("click here to get the room of the friend you choose");
		btnGetRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProxyUser friend = (ProxyUser) FriendsComboBox1.getItemAt(FriendsComboBox1.getSelectedIndex());
				ArrayList<ProxyChatroom> rooms = view2modelAdpt.getRemoteRoom(friend);
				RemoteRoomComboBox.removeAllItems();
				for (int i = 0; i < rooms.size(); i++) {
					RemoteRoomComboBox.addItem(rooms.get(i));

				}
			}
		});
		panel_5.add(btnGetRoom, gbc_btnGetRoom);

		panel_3.add(panel_6);
		GridBagLayout gbl_panel_6 = new GridBagLayout();
		gbl_panel_6.columnWidths = new int[] { 94, 0 };
		gbl_panel_6.rowHeights = new int[] { 0, 29, 0 };
		gbl_panel_6.columnWeights = new double[] { 1.0, Double.MIN_VALUE };
		gbl_panel_6.rowWeights = new double[] { 0.0, 0.0, Double.MIN_VALUE };
		panel_6.setLayout(gbl_panel_6);

		GridBagConstraints gbc_RemoteRoomComboBox = new GridBagConstraints();
		gbc_RemoteRoomComboBox.insets = new Insets(0, 0, 5, 0);
		gbc_RemoteRoomComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_RemoteRoomComboBox.gridx = 0;
		gbc_RemoteRoomComboBox.gridy = 0;
		RemoteRoomComboBox.setToolTipText("the room list you just get from the friend");
		panel_6.add(RemoteRoomComboBox, gbc_RemoteRoomComboBox);

		GridBagConstraints gbc_btnRequest = new GridBagConstraints();
		gbc_btnRequest.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnRequest.gridx = 0;
		gbc_btnRequest.gridy = 1;
		btnRequest.setToolTipText("click here to join the room");
		btnRequest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view2modelAdpt.joinRoom(RemoteRoomComboBox.getItemAt(RemoteRoomComboBox.getSelectedIndex()));
			}

		});
		panel_6.add(btnRequest, gbc_btnRequest);
		btnExistAndQuit.setToolTipText("leave all room and quit the system");
		btnExistAndQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view2modelAdpt.quit();
			}
		});

		panel.add(btnExistAndQuit);

		GridBagConstraints gbc_panel_8 = new GridBagConstraints();
		gbc_panel_8.fill = GridBagConstraints.BOTH;
		gbc_panel_8.gridx = 0;
		gbc_panel_8.gridy = 1;
		contentPane.add(panel_8, gbc_panel_8);
		tabPane.setSize(new Dimension(1100, 350));
		tabPane.add("info", scroll_info);
		tabPane.add("chatrooms", ChatroomPane);
		panel_8.add(tabPane);
	}

	/**
	 * set IP
 	 * @param IP the ip address
	 */
	public void setIP(String IP) {
		txtServerIp.setText(IP);
	}

	/**
	 * the friend to add
	 * @param friend the froend user
	 */
	public void addFriend(ProxyUser friend) {
		FriendsComboBox1.addItem(friend);
	}

	/**
	 * new a chat room tab
	 * @param name the name of the chatroom
	 * @param p the new panel
	 */
	public void addPane(String name, JPanel p) {
		ChatroomPane.add(name, p);

	}

	/**
	 * append the system information to the panel named info
	 * @param txt the info string
	 */
	public void appendInfo(String txt)

	{
		info.setText(info.getText() + txt);
	}

	/**
	 * leave the room, delete the panel
	 * @param room the old room
	 */
	public void leave(String room) {
		for (int i = 0; i < ChatroomPane.getTabCount(); i++) {
			if (ChatroomPane.getTitleAt(i) == room) {
				ChatroomPane.remove(i);
				break;
			}
		}

	}

	/**
	 * add panel to the gig tab pane
	 * @param component the panel to add
	 * @param label the name of the panel
	 */
	public void addPanel(Component component, String label) {
		// TODO Auto-generated method stub
		tabPane.addTab(label, component);
		((MapPanel) component).start();

	}
	public void joinRoom(IChatRoom room)
	{
		view2modelAdpt.joinRoom(room);
	}
}
