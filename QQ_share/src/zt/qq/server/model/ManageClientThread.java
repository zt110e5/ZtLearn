package zt.qq.server.model;
/**
 * 功能：管理客户端线程
 * */
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;

import zt.qq.client.view.ChatGroup;


public class ManageClientThread {
	// 客户端通信线程集合
		public static HashMap<String, ServerConClientThread> hm = new HashMap<String, ServerConClientThread>();
		
		// 向hm添加一个客户端通讯线程
		public static void addClientThread(String uid, ServerConClientThread ct) {
			hm.put(uid, ct);
		}

		// 获取一个客户端通讯线程
		public static ServerConClientThread getClientThread(String uid) {
			return (ServerConClientThread) hm.get(uid);
		}
		//加入群聊
		public static void addQQGroup(String uid, ServerConClientThread ct){
			hm.put(uid, ct);
		}
		//获取群聊客户端通讯线程
		public static ServerConClientThread getChatGroup(String uid){
			return (ServerConClientThread)hm.get(uid);
			
		}
		
		@SuppressWarnings("unchecked")
		public static Set<String> publish(){
			Set<String> set =  hm.keySet();
			
			return set;
			
		}
		// 返回当前在线的人的情况
		public static String getAllOnLineUserId() {
			// 使用迭代器进行遍历
			Iterator<String> it = hm.keySet().iterator();
			String res = "";
			while (it.hasNext()) {
				res += it.next().toString() + " ";
			}
			return res;
		}
}
