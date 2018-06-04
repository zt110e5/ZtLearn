package zt.qq.client.model;

import java.util.HashMap;

import zt.qq.client.view.FriendList;



public class ManageQQFriendList {
	private static HashMap<String, FriendList> hm = new HashMap<String, FriendList>();

	// 将界面添加到集合中
	public static void addQQFriendList(String uid, FriendList qqFriendList) {
		hm.put(uid, qqFriendList);
	}

	// 从集合中获取界面
	public static FriendList getQQFriendList(String uid) {
		return (FriendList) hm.get(uid);
	}
}
