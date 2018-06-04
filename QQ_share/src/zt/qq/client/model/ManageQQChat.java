package zt.qq.client.model;

import java.util.HashMap;

import zt.qq.client.view.ChatGroup;
import zt.qq.client.view.ChatOneToOne;



public class ManageQQChat {
	private static HashMap<String, ChatOneToOne> hm = new HashMap<String, ChatOneToOne>();
	private static HashMap<String, ChatGroup> hg = new HashMap<String,ChatGroup>();
	// 加入一个聊天界面
	public static void addQQChat(String loginIdAndFriendId, ChatOneToOne qqChat) {
		hm.put(loginIdAndFriendId, qqChat);
	}

	// 获取一个聊天界面
	public static ChatOneToOne getQQChat(String loginIdAndFriendId) {
		return (ChatOneToOne) hm.get(loginIdAndFriendId);
	}
	//加入群聊界面
	public static void addQQGroup(String group, ChatGroup ownerID){
		hg.put(group, ownerID);
	}
	//获取群聊界面
	public static ChatGroup getChatGroup(String group){
		return (ChatGroup)hg.get(group);
		
	}
}
