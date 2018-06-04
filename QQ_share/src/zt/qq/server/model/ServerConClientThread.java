package zt.qq.server.model;

/**
 * 功能：服务器和某个客户端的通讯线程
 */
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import zt.qq.common.Message;
import zt.qq.common.MessageType;

public class ServerConClientThread extends Thread {
	Socket s;

	// 构造方法
	public ServerConClientThread(Socket s) {
		// 把服务器和该客户端的连接赋给s
		this.s = s;
	}

	// 让该线程去通知其它用户
	public void notifyOther(String iam) {
		// 得到所有在线的人的线程
		HashMap<String, ServerConClientThread> hm = ManageClientThread.hm;
		Iterator<String> it = hm.keySet().iterator();

		while (it.hasNext()) {
			Message m = new Message();
			m.setContent(iam);
			m.setMesType(MessageType.MESSAGE_RET_ONLINEFRIEND);
			// 取出在线人的id
			String onLineUserId = it.next().toString();
			try {
				ObjectOutputStream oos = new ObjectOutputStream(
						ManageClientThread.getClientThread(onLineUserId).s.getOutputStream());
				m.setGetter(onLineUserId);
				oos.writeObject(m);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	// 让该线程去通知其它用户
	public void notifyAll(String iam) {
		// 得到所有在线的人的线程
		Set<String> getter = ManageClientThread.publish();
		Iterator<String> it = getter.iterator();
		String uid;
		Message m = new Message();
		while(it.hasNext()){
			uid = it.next();
			System.out.println(uid);
			if (!m.getSender().equals(uid)) {
				m.setGetter(uid);
				m.setContent(iam);
				m.setMesType(MessageType.MESSAGE_GROUP_MSG);
			// 取出在线人的id
			String onLineUserId = uid.toString();
			try {
				ObjectOutputStream oos = new ObjectOutputStream(
						ManageClientThread.getClientThread(onLineUserId).s.getOutputStream());
				m.setGetter(onLineUserId);
				oos.writeObject(m);
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
			}
	}

	public void run() {
		while (true) {
			// 该线程可以接收客户端的信息
			try {
				ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
				Message m = (Message) ois.readObject();

				System.out.println(m.getSender() + "给" + m.getGetter() + "内容为：" + m.getContent() + "\t" + m.getMesType());

				// 对从客户端取得的消息进行类型判断，然后做相应的处理
				if (m.getMesType().equals(MessageType.MESSAGE_COMM)) {
					// 服务器转发给客户端B
					// 取得接收人的通讯线程
					ServerConClientThread sc = ManageClientThread.getClientThread(m.getGetter());

					// System.out.println("接收人是：" + m.getGetter());
					// System.out.println(ManageClientThread.hm.toString());

					ObjectOutputStream oos = new ObjectOutputStream(sc.s.getOutputStream());
					// 服务器发送给接收人
					oos.writeObject(m);
				} else if (m.getMesType().equals(MessageType.MESSAGE_GROUP_MSG)) {
					Set<String> getter = ManageClientThread.publish();
					Iterator<String> it = getter.iterator();
					String uid;
					while(it.hasNext()){
						uid = it.next();
						System.out.println(uid);
						if (!m.getSender().equals(uid)) {
							m.setGetter(uid);
							ServerConClientThread sc = ManageClientThread.getClientThread(m.getGetter());
							System.out.println(getter);
							ObjectOutputStream oos = new ObjectOutputStream(sc.s.getOutputStream());
							// 服务器发送给接收人
							oos.writeObject(m);
							System.out.println(m.getSender() + "给" + m.getGetter() + "内容为：" + m.getContent() + "\t" + m.getMesType());
							System.out.println(m);
						}

					}
				} else if (m.getMesType().equals(MessageType.MESSAGE_GET_ONLINEFRIEND)) {
					System.out.println(m.getSender() + " 要他的好友");
					// 把在服务器的好友返回给客户端
					String res = ManageClientThread.getAllOnLineUserId();
					// String res1 = ManageClientThread.getAllOnLineUserId1();

					System.out.println(m.getSender() + "的好友有" + res);
					Message m2 = new Message();
					m2.setMesType(MessageType.MESSAGE_RET_ONLINEFRIEND);
					m2.setContent(res);
					m2.setGetter(m.getSender());
					ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
					oos.writeObject(m2);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
