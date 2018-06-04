package zt.qq.client.view;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.*;

import zt.qq.client.model.ManageClientConServerThread;
import zt.qq.common.Message;
import zt.qq.common.MessageType;
import zt.qq.common.Time;

@SuppressWarnings("serial")
public class ChatGroup extends JFrame implements MouseListener, ActionListener, WindowListener,Runnable{
	JLabel[] jbls1;
	// 标记好友是否在线
	Boolean[] jbls1Flag;
	JPanel jphy1, jphy2, jphy3, jhy4;
	private JTextArea ta;
	private JTextField tf;
	private JButton sendBtn;
	private JPanel mainPan;
	private JPanel sidePan;
	private JScrollPane jsp;
	private Dimension screenSize, frameSize;
	private String ownerId;
	private String group;

	// 更新在线好友
	public void updateFriend(Message m) {
		String onLineFriend[] = m.getContent().split(" ");

		for (int i = 0; i < onLineFriend.length; i++) {

			jbls1[Integer.parseInt(onLineFriend[i]) - 1].setEnabled(true);
			jbls1Flag[Integer.parseInt(onLineFriend[i]) - 1] = true;
		}
	}

	// 显示消息
	public void showMessage(Message m) {
		String info = m.getSender() + "说：" + m.getContent() + "\t" + m.getSendTime() + "\r\n";
		System.out.println("QQGroup:" + info);
		ta.append(info);
	}

	public ChatGroup(String ownerId) throws Exception {
		// this.group = group;
		this.ownerId = ownerId;
		ta = new JTextArea(10, 15);
		tf = new JTextField(15);
		mainPan = new JPanel(new BorderLayout());
		// sidePan = new JPanel(new FlowLayout());
		// jsp=new JScrollPane(sidePan);
		// sidePan.setPreferredSize(new Dimension(70, 300));
		sendBtn = new JButton("send");
		// 假定QQ群有5个好友
		jphy1 = new JPanel(new GridLayout(5, 1, 10, 10));
		// 初始化好友
		jbls1 = new JLabel[5];
		jbls1Flag = new Boolean[5];
		for (int i = 0; i < jbls1.length; i++) {
			jbls1[i] = new JLabel(i + 1 + "", new ImageIcon("image/mm.jpg"), JLabel.LEFT);
			jbls1[i].setEnabled(true);
			jbls1Flag[i] = true;
			if (jbls1[i].getText().equals(ownerId)) {
				jbls1[i].setEnabled(true);
				jbls1Flag[i] = true;
			}
			jbls1[i].addMouseListener(this);
			// 给jphy2初始化20个好友
			jphy1.add(jbls1[i]);
		}
		sendBtn.addActionListener(this);
		jsp = new JScrollPane(jphy1);
		mainPan.add(ta, BorderLayout.NORTH);
		mainPan.add(tf, BorderLayout.CENTER);
		mainPan.add(sendBtn, BorderLayout.SOUTH);
		this.add(mainPan, BorderLayout.CENTER);
		this.add(jsp, BorderLayout.EAST);
		group = "QQ群聊1";
		// 获取当前屏幕大小
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// 获取当前窗口大小
		frameSize = this.getPreferredSize();
		// 保持弹出窗口居中
		this.setLocation((screenSize.width - frameSize.width) / 5, (screenSize.height - frameSize.height) / 6);
		this.setTitle(this.group);
		this.setSize(600, 300);
		this.setLocationRelativeTo(null);

		this.setVisible(true);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == sendBtn) {
			// 点击了发送按钮
			// 填入信息
			Message m = new Message();
			m.setSender(this.ownerId);
			m.setContent(tf.getText());
			m.setMesType(MessageType.MESSAGE_GROUP_MSG);
			m.setSendTime(Time.gettime());
			String info = m.getSender() + "说：" + m.getContent() + "\t" + m.getSendTime() + "\r\n";
			ta.append(info);
			System.out.println("hello ...." + info);
			tf.setText("");
			// 客户端A发送给服务器
			try {
				ObjectOutputStream oos = new ObjectOutputStream(
						ManageClientConServerThread.getClientServerThread(this.ownerId).getS().getOutputStream());
				oos.writeObject(m);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jl = (JLabel) e.getSource();
		jl.setForeground(Color.red);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		JLabel jl = (JLabel) e.getSource();
		jl.setForeground(Color.black);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	public void run() {
//		while(true){
//			ObjectInputStream ois = new ObjectInputStream(ClientConServerThread)
//			Message m = 
//			String info = m.getSender() + "说：" + m.getContent() + "\t" + m.getSendTime() + "\r\n";
//			System.out.println("QQGroup:" + info);
//			ta.append(info);
//		}
//		
//	}

}
