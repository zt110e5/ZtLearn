package zt.qq.client.view;

import javax.swing.*;

import zt.qq.client.view.FriendList;
import zt.qq.common.Message;
import zt.qq.common.MessageType;
import zt.qq.common.User;
import zt.qq.client.model.ManageClientConServerThread;
import zt.qq.client.model.ManageQQFriendList;
import zt.qq.client.model.QQClientUser;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectOutputStream;

public class ClientloginLogin extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5300200341114673960L;

	// 定义上部组件
	JLabel jbl1;

	// 定义中部组件
	JTabbedPane jtp;
	JPanel jp2;
	JLabel jp2_jbl1, jp2_jbl2, jp2_jbl3, jp2_jbl4;
	JTextField jp2_jtf1;
	JPasswordField jp2_jpf1;
	JButton jp2_jb1;
	JCheckBox jp2_jcb1, jp2_jcb2;

	// 定义下部组件
	JPanel jp1;
	JButton jp1_jb1, jp1_jb2, jp1_jb3;

	// 构造方法
	public ClientloginLogin() {
		// 处理上部
		jbl1 = new JLabel(new ImageIcon("image/family.PNG"));

		// 处理中部
		jtp = new JTabbedPane();
		// jp3 = new JPanel();
		// jp4 = new JPanel();
		// jp2设置为网格布局
		jp2 = new JPanel(new GridLayout(3, 3));
		jp2_jbl1 = new JLabel("QQ号码", JLabel.CENTER);
		jp2_jbl2 = new JLabel("注册账号", JLabel.CENTER);
		jp2_jbl2.setForeground(Color.blue);
		jp2_jbl3 = new JLabel("QQ密码", JLabel.CENTER);
		jp2_jbl4 = new JLabel("忘记密码", JLabel.CENTER);
		jp2_jbl4.setForeground(Color.blue);
		// jp2_jbl4 = new JLabel("申请密码保护", JLabel.CENTER);

		// jp2_jb1 = new JButton(new ImageIcon("image/clear.gif"));
		jp2_jcb1 = new JCheckBox("隐身登陆");
		jp2_jcb2 = new JCheckBox("记住密码");
		jp2_jtf1 = new JTextField(20);
		jp2_jpf1 = new JPasswordField(20);
		// 添加到jp2
		jp2.add(jp2_jbl1);
		jp2.add(jp2_jtf1);
		jp2.add(jp2_jbl2);
		// jp2.add(jp2_jb1);
		jp2.add(jp2_jbl3);
		jp2.add(jp2_jpf1);
		jp2.add(jp2_jbl4);
		jp2.add(jp2_jcb1);
		jp2.add(jp2_jcb2);
		// jp2.add(jp2_jbl4);

		// 添加到jtp
		jtp.add(jp2, "在此相识.在此相遇.在此相恋.在此相携.在此相伴.在此相守");
		// jtp.add(jp3, "手机号码");
		// jtp.add(jp4, "电子邮件");
		// 处理下部
		jp1 = new JPanel();
		jp1_jb1 = new JButton(new ImageIcon("image/denglu.gif"));
		// 响应用户点击登录
		jp1_jb1.addActionListener(this);
		jp1_jb2 = new JButton(new ImageIcon("image/quxiao.gif"));
		// jp1_jb3 = new JButton(new ImageIcon("image/xiangdao.gif"));

		// 添加到jp1
		jp1.add(jp1_jb1);
		jp1.add(jp1_jb2);
		// jp1.add(jp1_jb3);

		// 添加到JFrame
		add(jbl1, "North");
		add(jp1, "South");
		add(jtp, "Center");

		// 设置窗体
		ImageIcon tp1 = new ImageIcon("image/qq.gif");
		setIconImage(tp1.getImage());
		setTitle("用户登录");
		setSize(351, 240);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {

		new ClientloginLogin();
	}

	// 点击事件
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == jp1_jb1) {
			// 点击登录按钮
			// 验证
			// 创建客户端用户类对象
			QQClientUser qqClientUser = new QQClientUser();
			// 创建用户信息对象
			User u = new User();
			u.setUserId(jp2_jtf1.getText().trim());
			u.setPasswd(new String(jp2_jpf1.getPassword()));
			System.out.println("验证用户密码：" + u.getPasswd() + "用户名" + u.getUserId());
			// 验证用户是否合法
			if (qqClientUser.checkUser(u)) {
				try {
					// 创建好友列表
					FriendList qqList = new FriendList(u.getUserId());
					ManageQQFriendList.addQQFriendList(u.getUserId(), qqList);
					// 发送一个要求返回在线好友的包
					@SuppressWarnings("unused")
					ObjectOutputStream oos = new ObjectOutputStream(
							ManageClientConServerThread.getClientServerThread(u.getUserId()).getS().getOutputStream());
					// Message对象
					Message m = new Message();
					m.setMesType(MessageType.MESSAGE_GET_ONLINEFRIEND);
					// 指明获取的是哪个qq号的好友信息
					m.setSender(u.getUserId());
					oos.writeObject(m);

				} catch (Exception e1) {
					e1.printStackTrace();
				}

				// 关闭登录界面
				dispose();
			} else {
				// 错误处理
				JOptionPane.showMessageDialog(this, "用户名或者密码错误");
			}
		}
	}
}
