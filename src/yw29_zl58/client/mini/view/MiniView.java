package yw29_zl58.client.mini.view;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.GridBagLayout;
//import java.awt.BorderLayout;
//import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
//import javax.swing.border.EtchedBorder;
//import javax.swing.border.TitledBorder;
//import javax.swing.filechooser.FileSystemView;

import java.awt.Insets;

import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
//import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.awt.event.ActionEvent;
//import java.awt.FlowLayout;
import javax.swing.JTabbedPane;
//import java.awt.GridLayout;
//import com.jgoodies.forms.layout.FormLayout;
//import com.jgoodies.forms.layout.ColumnSpec;
//import com.jgoodies.forms.layout.RowSpec;
//import javax.swing.GroupLayout;
//import javax.swing.GroupLayout.Alignment;
import javax.swing.JList;

/**
 * @author zihanli
 * the view of the chat room
 */
public class MiniView extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	MiniView2ModelAdapter view2modelAdpt;
	private final JPanel panel_1 = new JPanel();
	private final JTextField txtSendMessage = new JTextField();
	private final JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
	private final JPanel panel_2 = new JPanel();
	private final JButton btnSendMessage = new JButton("Send Message");
	private final JButton btnSendImage = new JButton("Send Image");
	private final JButton btnSendFile = new JButton("Send File");
	private final HashMap<String, JPanel> panelMap = new HashMap<String, JPanel>();
	private final JPanel scrollablePane = new JPanel();
	private final JScrollPane scroll = new JScrollPane(scrollablePane);

	private final JPanel fileReceiverStatus = new JPanel();
	private final JScrollPane scroll1 = new JScrollPane(fileReceiverStatus);
	private DefaultListModel<String> listModel = new DefaultListModel<String>();
	private final JList<String> list = new JList<String>(listModel);
	private final JButton btnLeave = new JButton("Leave");

	/**
	 * create the panel
	 * @param _view2modelAdpt the adapter for the view to call model's methods
	 */
	public MiniView(MiniView2ModelAdapter _view2modelAdpt) {
		txtSendMessage.setText("Send Message");
		txtSendMessage.setColumns(60);
		view2modelAdpt = _view2modelAdpt;

		initGUI();
	}

	private void initGUI() {
		//panel_1.setPreferredSize(new Dimension(800, 300) );
		//this.setPreferredSize(new Dimension(1000, 1000));
		tabbedPane.setPreferredSize(new Dimension(1000, 300));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] { 105, 387, 0 };
		gridBagLayout.rowHeights = new int[] { 316, 0 };
		gridBagLayout.columnWeights = new double[] { 1.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] { 1.0, Double.MIN_VALUE };
		setLayout(gridBagLayout);

		GridBagConstraints gbc_list = new GridBagConstraints();
		gbc_list.insets = new Insets(0, 0, 0, 5);
		gbc_list.fill = GridBagConstraints.BOTH;
		gbc_list.gridx = 0;
		gbc_list.gridy = 0;
		list.setToolTipText("name list of the receiver in this room");
		add(list, gbc_list);

		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.anchor = GridBagConstraints.NORTHWEST;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[] { 368, 0 };
		gbl_panel_1.rowHeights = new int[] { 236, 32, 0, 0, 0, 35, 0 };
		gbl_panel_1.columnWeights = new double[] { 0.0, Double.MIN_VALUE };
		gbl_panel_1.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panel_1.setLayout(gbl_panel_1);
		GridBagConstraints gbc_tabbedPane = new GridBagConstraints();
		gbc_tabbedPane.fill = GridBagConstraints.BOTH;
		gbc_tabbedPane.insets = new Insets(0, 0, 5, 0);
		gbc_tabbedPane.gridx = 0;
		gbc_tabbedPane.gridy = 0;
		panel_1.add(tabbedPane, gbc_tabbedPane);
		//scrollablePane.setLayout(new BorderLayout(scrollablePane, BorderLayout.Y_AXIS));
		this.tabbedPane.addTab("Scrolling Comps", scroll);
		this.panelMap.put("Scrolling Comps", scrollablePane);
		scrollablePane.setLayout(new BoxLayout(scrollablePane, BoxLayout.Y_AXIS));
		this.tabbedPane.addTab("Receive File Status", scroll1);
		this.panelMap.put("Receive File Status", fileReceiverStatus);
		GridBagConstraints gbc_txtSendMessage = new GridBagConstraints();
		gbc_txtSendMessage.fill = GridBagConstraints.BOTH;
		gbc_txtSendMessage.insets = new Insets(0, 0, 5, 0);
		gbc_txtSendMessage.gridx = 0;
		gbc_txtSendMessage.gridy = 1;
		panel_1.add(txtSendMessage, gbc_txtSendMessage);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.insets = new Insets(0, 0, 5, 0);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 2;
		panel_1.add(panel_2, gbc_panel_2);
		btnSendMessage.setToolTipText("click here to send message to the room");
		btnSendMessage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view2modelAdpt.SendMsg(txtSendMessage.getText());
			}
		});

		panel_2.add(btnSendMessage);
		btnSendImage.setToolTipText("click here to send image");
		btnSendImage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String path;
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setApproveButtonText("OK");
				int result = fileChooser.showOpenDialog(panel_2);
				if (JFileChooser.APPROVE_OPTION == result) {
					path = fileChooser.getSelectedFile().getAbsolutePath();
					view2modelAdpt.SendImg(path);
				}

			}
		});

		panel_2.add(btnSendImage);
		btnSendFile.setToolTipText("click here to send file");
		btnSendFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//String path;
				JFileChooser fileChooser = new JFileChooser();
				//FileSystemView fsv = FileSystemView.getFileSystemView(); 
				fileChooser.setApproveButtonText("OK");
				int result = fileChooser.showOpenDialog(panel_2);
				if (JFileChooser.APPROVE_OPTION == result) {
					File file = fileChooser.getSelectedFile();
					//File file = new File(text);
					byte[][] data = new byte[2][];
					data[0] = file.getName().getBytes();
					try {
						data[1] = Files.readAllBytes(file.toPath());
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					view2modelAdpt.SendFile(data);
				}

			}
		});

		panel_2.add(btnSendFile);
		GridBagConstraints gbc_btnLeave = new GridBagConstraints();
		gbc_btnLeave.insets = new Insets(0, 0, 5, 0);
		gbc_btnLeave.gridx = 0;
		gbc_btnLeave.gridy = 3;
		btnLeave.setToolTipText("click here to leave the room");
		panel_1.add(btnLeave, gbc_btnLeave);
		btnLeave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				view2modelAdpt.leave();
			}
		});
	}

	/**
	 * @param msg the message
	 * @param name name of the sender
	 */
	public void appendMsg(String msg, String name) {
		//JLabel label=new JLabel(msg);
		//scrollPane.setText(scrollPane.get);
		//scroll.add(label);
		//textArea.setText(textArea.getText()+msg+'\n');
		scrollablePane.add(new JLabel(name + ":"));
		scrollablePane.add(new JLabel(msg));
		scrollablePane.revalidate();
		//scroll.add(new JLabel(msg));
		//scroll.revalidate();
	}

	/**
	 * @param component the component to add
	 * @param label the name of the panel to add the component to
	 */
	public void addScrollableComponent(Component component, String label) {
		if (!panelMap.containsKey(label)) {
			JPanel p = new JPanel();
			panelMap.put(label, p);
			p.add(component);
			tabbedPane.add(label, p);

		} else {
			panelMap.get(label).add(component);
			panelMap.get(label).revalidate();
		}

	}

	/**
	 * @param component the component to add
	 * @param label the name of the panel to add the component to
	 */
	public void addNonScrollableComponent(Component component, String label) {
		if (!panelMap.containsKey(label)) {
			//JPanel p = new JPanel();
			panelMap.put(label, (JPanel) component);
			//p.add(component);
			tabbedPane.add(label, component);

		} else {
			panelMap.get(label).add(component);
			panelMap.get(label).revalidate();
		}
	}

	/**
	 * refresh the username's list
	 */
	public void refresh() {
		listModel.clear();
		for (String name : view2modelAdpt.getMembers()) {
			listModel.addElement(name);
		}
	}

}
