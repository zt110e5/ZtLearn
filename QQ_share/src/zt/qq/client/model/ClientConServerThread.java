package zt.qq.client.model;
/**
 * 功能：客户端连接服务器线程
 */
import java.net.*;

import zt.qq.client.view.ChatGroup;
import zt.qq.client.view.ChatOneToOne;
import zt.qq.client.view.FriendList;
import zt.qq.common.Message;
import zt.qq.common.MessageType;

import java.io.*;


public class ClientConServerThread extends Thread {
	private Socket s;

	// 构造方法
	public ClientConServerThread(Socket s) {
		this.s = s;
	}

	public Socket getS() {
		return s;
	}

	public void setS(Socket s) {
		this.s = s;
	}

	public void run() {
		while (true) {
			// 不停的读取从服务器发来的消息
			try {
				ObjectInputStream ois = new ObjectInputStream(
						s.getInputStream());
				Message m = (Message) ois.readObject();
				System.out.println("读取从服务器发来的消息" + m.getSender() + "给"
						+ m.getGetter() + "内容：" + m.getContent());

				if (m.getMesType().equals(MessageType.MESSAGE_COMM)) {
					// 普通包
					// 从服务器获得的消息显示到相应的聊天界面
					ChatOneToOne qqChat = ManageQQChat.getQQChat(m.getGetter() + " "
							+ m.getSender());
					// 显示
					qqChat.showMessage(m);
				}else if(m.getMesType().equals(MessageType.MESSAGE_GROUP_MSG)){
					// 从服务器获得的消息显示到相应的聊天界面
					ChatGroup gChat = ManageQQChat.getChatGroup("QQ群1");
					// 显示
					System.out.println(m);
					gChat.showMessage(m);
					
				}else if(m.getMesType().equals(
						MessageType.MESSAGE_RET_ONLINEFRIEND)) {
					// 返回在线好友的包
					System.out.println("客户端接收到" + m.getContent());
					String con = m.getContent();
					// getter是相对于服务器的接收者，也就是自己的QQ
					String getter = m.getGetter();
					System.out.println("getter=" + getter);
					
					// 修改相应的好友列表
					FriendList qqFriendList = ManageQQFriendList
							.getQQFriendList(getter);
					ChatGroup chatGroup = ManageGroupFriendList.getChatGroup(getter);
					// 更新在线好友
					if (qqFriendList != null) {
						qqFriendList.updateFriend(m);
					}
					if(chatGroup != null){
						chatGroup.updateFriend(m);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
