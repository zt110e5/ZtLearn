package zt.qq.client.model;
/**
 * 管理客户端连接服务器线程
 */
import java.util.HashMap;

public class ManageClientConServerThread {
	// 客户端与服务器连接的集合
		private static HashMap<String, ClientConServerThread> hm = new HashMap<String, ClientConServerThread>();
		
		// 把创建好的ClientConServerThread对象放入hm
		public static void addClientConServerThread(String uid, ClientConServerThread ccst) {
			hm.put(uid, ccst);
		}

		// 通过uid取得线程
		public static ClientConServerThread getClientServerThread(String uid) {
			return (ClientConServerThread) hm.get(uid);
		}
		
}
