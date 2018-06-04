package zt.qq.client.model;

import java.util.HashMap;

import zt.qq.client.view.ChatGroup;

public class ManageGroupFriendList {
	private static HashMap<String, ChatGroup> hg = new HashMap<String, ChatGroup>();

	// 加入群聊界面
	public static void addQQGroup(String group, ChatGroup ownerID) {
		hg.put(group, ownerID);
	}

	// 获取群聊界面
	public static ChatGroup getChatGroup(String group) {
		return (ChatGroup) hg.get(group);

	}
}
