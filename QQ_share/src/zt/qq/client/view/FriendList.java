/**
 * 功能：好友界面(包括我的好友，黑名单，陌生人)
 */
package zt.qq.client.view;

import java.awt.*;
import java.awt.event.*;
import java.io.ObjectOutputStream;

import javax.swing.*;

import zt.qq.client.model.ManageClientConServerThread;
import zt.qq.client.model.ManageGroupFriendList;
import zt.qq.client.model.ManageQQChat;
import zt.qq.common.Message;
import zt.qq.common.User;

public class FriendList extends JFrame implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	// 卡片布局
	CardLayout c1;

	// 第一张卡片
	JPanel jphy1, jphy2, jphy3, jhy4;
	JButton jphy_jb1, jphy_jb2, jphy_jb3, jphy_jb4;
	JScrollPane jphy_jsp;

	// 第二张卡片
	JPanel jpmsr1, jpmsr2, jpmsr3, jpmsr4;
	JButton jpmsr_jb1, jpmsr_jb2, jpmsr_jb3, jpmsr_jb4;
	JScrollPane jpmsr_jsp;

	// 第三张卡片
	JPanel jphmd1, jphmd2, jphmd3, jphmd4;
	JButton jphmd_jb1, jphmd_jb2, jphmd_jb3, jphmd_jb4;
	JScrollPane jphmd_jsp;
	// 第四张卡片
	JPanel jpbk1, jpbk2, jpbk3, jpbk4;
	JButton jpbk_jb1, jpbk_jb2, jpbk_jb3, jpbk_jb4;
	JScrollPane jpbk_jsp;

	String ownerId;

	JLabel[] jbls1, jbls2, jbls3,jbls4;
	// 标记好友是否在线
	Boolean[] jbls1Flag;

	 //更新在线好友
	 public void updateFriend(Message m) {
	 String onLineFriend[] = m.getContent().split(" ");
	
	 for (int i = 0; i < onLineFriend.length; i++) {
	
	 jbls1[Integer.parseInt(onLineFriend[i]) - 1].setEnabled(true);
	 jbls1Flag[Integer.parseInt(onLineFriend[i]) - 1] = true;
	 }
	 }

	// 构造方法
	public FriendList(String ownerId) {
		this.ownerId = ownerId;
		// 处理第一张卡片(好友列表)
		jphy_jb1 = new JButton("我的好友");
		jphy_jb2 = new JButton("陌生人");
		jphy_jb2.addActionListener(this);
		jphy_jb3 = new JButton("QQ群");
		jphy_jb3.addActionListener(this);
		jphy_jb4 = new JButton("黑名单");
		jphy_jb4.addActionListener(this);
		jphy1 = new JPanel(new BorderLayout());
		// 假定有20个好友
		jphy2 = new JPanel(new GridLayout(20, 1, 4, 4));

		// 初始化好友
		jbls1 = new JLabel[20];
		jbls1Flag = new Boolean[20];
		for (int i = 0; i < jbls1.length; i++) {
			jbls1[i] = new JLabel(i + 1 + "", new ImageIcon("image/mm.jpg"), JLabel.LEFT);
			jbls1[i].setEnabled(false);
			jbls1Flag[i] = false;
			if (jbls1[i].getText().equals(ownerId)) {
				jbls1[i].setEnabled(true);
				jbls1Flag[i] = true;
			}
			jbls1[i].addMouseListener(this);
			// 给jphy2初始化20个好友
			jphy2.add(jbls1[i]);
		}
		jphy_jsp = new JScrollPane(jphy2);
		jphy3 = new JPanel(new GridLayout(3, 1));

		// 按钮加入jphy3
		jphy3.add(jphy_jb2);
		jphy3.add(jphy_jb3);
		jphy3.add(jphy_jb4);
		// 加入jphy1,对jphy1初始化
		jphy1.add(jphy_jb1, "North");
		jphy1.add(jphy_jsp, "Center");
		jphy1.add(jphy3, "South");

		// 处理第二张卡片
		jpmsr_jb1 = new JButton("我的好友");
		jpmsr_jb1.addActionListener(this);
		jpmsr_jb2 = new JButton("陌生人");
		jpmsr_jb3 = new JButton("QQ群");
		jpmsr_jb3.addActionListener(this);
		jpmsr_jb4 = new JButton("黑名单");
		jpmsr_jb4.addActionListener(this);
		jpmsr1 = new JPanel(new BorderLayout());
		// 假定有20个陌生人
		jpmsr2 = new JPanel(new GridLayout(20, 1, 4, 4));

		// 初始化陌生人
		jbls2 = new JLabel[20];
		for (int i = 0; i < jbls2.length; i++) {
			jbls2[i] = new JLabel("陌生人" + (i + 1), new ImageIcon("images/mm.jpg"), JLabel.LEFT);
			jbls2[i].setEnabled(false);
			jbls2[i].addMouseListener(this);
			jpmsr2.add(jbls2[i]);
		}
		jpmsr_jsp = new JScrollPane(jpmsr2);
		// jpmsr2初始化20个陌生人
		jpmsr3 = new JPanel(new GridLayout(2, 1));
		jpmsr4 = new JPanel(new GridLayout(2, 1));
		// 按钮加入jpmsr3
		jpmsr3.add(jpmsr_jb1);
		jpmsr3.add(jpmsr_jb2);
		jpmsr4.add(jpmsr_jb3);
		jpmsr4.add(jpmsr_jb4);
		// 加入jpmsr1,对jpmsr1初始化
		jpmsr1.add(jpmsr3, "North");
		jpmsr1.add(jpmsr_jsp, "Center");
		jpmsr1.add(jpmsr4, "South");
		// jpmsr1.add(jpmsr_jb4, "South");
		// 处理第三张卡片
		jphmd_jb1 = new JButton("我的好友");
		jphmd_jb1.addActionListener(this);
		jphmd_jb2 = new JButton("陌生人");
		jphmd_jb2.addActionListener(this);
		jphmd_jb3 = new JButton("QQ群");
		jphmd_jb4 = new JButton("黑名单");
		jphmd_jb4.addActionListener(this);
		jphmd1 = new JPanel(new BorderLayout());
		// 假定QQ群有4个人
		jphmd2 = new JPanel(new GridLayout(4, 1, 5, 5));

		// 初始化QQ群中的人
		jbls3 = new JLabel[4];
		for (int i = 0; i < jbls3.length; i++) {
			jbls3[i] = new JLabel(  "QQ群"+(i + 1), new ImageIcon("image/mm.jpg"), JLabel.LEFT);
			jbls3[i].setEnabled(true);
			jbls3[i].addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mousePressed(MouseEvent e) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void mouseExited(MouseEvent e) {
					// TODO Auto-generated method stub
					JLabel jl = (JLabel) e.getSource();
					jl.setForeground(Color.black);
				}
				
				@Override
				public void mouseEntered(MouseEvent e) {
					// TODO Auto-generated method stub
					JLabel jl = (JLabel) e.getSource();
					jl.setForeground(Color.red);
				}
				
				@Override
				public void mouseClicked(MouseEvent e) {
					// 相应用户双击的事件，并得到QQ群號
					if(e.getClickCount() == 2){
						// 得到QQ群號
						// 创建用户信息对象
						User u = new User();
						String qqGroup = ((JLabel) e.getSource()).getText();
						ChatGroup myownerId;
						try {// 把聊天界面加入到管理类
							myownerId = new ChatGroup(ownerId);
							ManageQQChat.addQQGroup(qqGroup, myownerId);
							
							
//							// 发送一个要求返回在线好友的包
//							@SuppressWarnings("unused")
//							ObjectOutputStream oos = new ObjectOutputStream(
//									ManageClientConServerThread
//											.getClientServerThread(u.getUserId())
//											.getS().getOutputStream());
							//ChatGroup qqGroup1 = new ChatGroup(u.getUserId());
							//ManageGroupFriendList.addQQGroup(u.getUserId(), qqGroup1);
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
					
				}
			});
			jphmd2.add(jbls3[i]);
		}
		jphmd_jsp = new JScrollPane(jphmd2);
		// 将jphmd2初始化4个QQ群
		jphmd3 = new JPanel(new GridLayout(3, 1));
		//jphmd4 = new JPanel(new GridLayout(2, 1));
		// 按钮加入jphmd3
		jphmd3.add(jphmd_jb1);
		jphmd3.add(jphmd_jb2);
		jphmd3.add(jphmd_jb3);
		//jphmd4.add(jphmd_jb4);
		
		jphmd1.add(jphmd3, "North");
		jphmd1.add(jphmd_jsp, "Center");
		jphmd1.add(jphmd_jb4, "South");
		// 处理第四张卡片
		jpbk_jb1 = new JButton("我的好友");
		jpbk_jb1.addActionListener(this);
		jpbk_jb2 = new JButton("陌生人");
		jpbk_jb2.addActionListener(this);
		jpbk_jb3 = new JButton("QQ群");
		jpbk_jb3.addActionListener(this);
		jpbk_jb4 = new JButton("黑名单");

		jpbk1 = new JPanel(new BorderLayout());
		// 假定黑名单有4个人
		jpbk2 = new JPanel(new GridLayout(4, 1, 5, 5));

		// 初始化黑名单中的人
		jbls4 = new JLabel[4];
		for (int i = 0; i < jbls4.length; i++) {
			jbls4[i] = new JLabel("黑名单" + (i + 1), new ImageIcon("image/mm.jpg"), JLabel.LEFT);
			jbls4[i].setEnabled(false);
			jbls4[i].addMouseListener(this);
			jpbk2.add(jbls4[i]);
		}
		jpbk_jsp = new JScrollPane(jpbk2);
		// 将jphmd2初始化4个QQ群
		jpbk3 = new JPanel(new GridLayout(4, 1));
		// 按钮加入jphmd3
		jpbk3.add(jpbk_jb1);
		jpbk3.add(jpbk_jb2);
		jpbk3.add(jpbk_jb3);
		jpbk3.add(jpbk_jb4);

		// 加入jphmd1,对jphmd1初始化
		jpbk1.add(jpbk3, "North");
		jpbk1.add(jpbk_jsp, "Center");
		//jpbk1.add(jphmd4, "South");
		// 把JFrame设置为CardLayout布局
		c1 = new CardLayout();
		setLayout(c1);
		// 加入JFrame
		add(jphy1, "1");
		add(jpmsr1, "2");
		add(jphmd1, "3");
		add(jpbk1, "4");
		// 设置窗体
		setTitle("QQ" + ownerId);
		setIconImage(new ImageIcon("image/qq.gif").getImage());
		setSize(250, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	// 测试
	// public static void main(String[] args) {
	// QQFriendList qqfl = new QQFriendList();
	//
	// }

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jphy_jb2 || e.getSource() == jphmd_jb2 ||e.getSource() == jpbk_jb2) {
			// 点击了陌生人按钮，显示第二张卡片
			c1.show(this.getContentPane(), "2");
		} else if (e.getSource() == jpmsr_jb1 || e.getSource() == jphmd_jb1 || e.getSource() == jpbk_jb1) {
			// 点击了好友按钮，显示第一张卡片
			c1.show(this.getContentPane(), "1");
		} else if (e.getSource() == jphy_jb3 || e.getSource() == jpmsr_jb3 || e.getSource() == jpbk_jb3) {
			// 点击了黑名单按钮，显示第三张卡片
			c1.show(this.getContentPane(), "3");
		}else if (e.getSource() == jphy_jb4 || e.getSource() == jpmsr_jb4 || e.getSource() == jphmd_jb4) {
			// 点击了黑名单按钮，显示第四张卡片
			c1.show(this.getContentPane(), "4");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		// 相应用户双击的事件，并得到好友的编号
		 if (e.getClickCount() == 2) {
		 // 得到该好友的编号
		 String friendNo = ((JLabel) e.getSource()).getText();
		 // 如果聊天的人不在线
		 if (!jbls1Flag[Integer.parseInt(friendNo) - 1]) {
		 JOptionPane.showMessageDialog(this, "不能和不在线的人聊天");
		 } else if (!friendNo.equals(ownerId)
		 && jbls1Flag[Integer.parseInt(friendNo) - 1]) {
		 // 如果不是自己并且在线
		 ChatOneToOne qqChat = new ChatOneToOne(friendNo, ownerId);
		 // 把聊天界面加入到管理类
		 ManageQQChat.addQQChat(this.ownerId + " " + friendNo, qqChat);
		 } else {
		 //如果是自己
		 JOptionPane.showMessageDialog(this, "不能和自己聊天");
		 }
		
		 }
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

	public static void main(String[] args) {
		FriendList f = new FriendList("1");
	}
}
