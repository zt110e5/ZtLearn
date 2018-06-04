package zt.qq.server;

/**
 * QQ服务器
 * @author zt
 *
 */
import java.io.*;
import java.net.*;
import java.sql.ResultSet;
import java.util.*;

import zt.qq.common.*;
import zt.qq.server.db.DBConnection;
import zt.qq.server.model.ManageClientThread;
import zt.qq.server.model.ServerConClientThread;

public class QQServerOpen {
	DBConnection db = null;

	public void close() {
		if (db != null) {
			db.close();
		}
	}

	public QQServerOpen() throws Exception {
		ServerSocket ss = new ServerSocket(9999);
		while(true){
		System.out.println("服务器已经启动，正在等待连接...");
		Socket s = ss.accept();

		ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
		User user = (User) ois.readObject();
		// System.out.println(user.getPasswd()+user.getUserId());
		System.out.println("账号：" + user.getUserId() + "密码:" + user.getPasswd());
		
		/**
		 * QQ登录数据库验证 ͨ�����ݿ���֤��Ϣ
		 */
		db = new DBConnection();
		//sql語句
		String sql = "select passwd from qq_user where userId=?";
		//未知参数赋值
		String[] param = { user.getUserId() };
		//执行查询
		ResultSet rs = db.queryExecute(sql, param);
		Message msg = new Message();
		ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
		String password = null;
		if (rs.next()) {
		
			password = rs.getString("passwd").trim();
			//System.out.println(password);
		}
		//if (user.getUserId().equals(userId)) {
			if (user.getPasswd().equals(password)) {
				//将消息类型设置为登录成功
				msg.setMesType(MessageType.MESSAGE_SUCCEED);
				oos.writeObject(msg);
				// 单开一个线程，让该线程与该客户端保持通讯
				ServerConClientThread scct = new ServerConClientThread(s);
				// 加入通讯线程集合
				ManageClientThread.addClientThread(user.getUserId(), scct);
				// 线程启动������߳�
				scct.start();
				// 并通知其它在线用户.
				scct.notifyOther(user.getUserId());
			} else {
				// 返回一个失败的信息包
				msg.setMesType(MessageType.MESSAGE_LOGIN_FAIL);
				// 向客户端发送Message对象
				oos.writeObject(msg);
				System.out.println("密码错误");
				s.close();// 关闭连接
			}
		}
	}

	public static void main(String[] args) throws Exception {
		new QQServerOpen();
	}
}
