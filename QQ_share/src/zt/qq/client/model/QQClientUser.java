package zt.qq.client.model;

import zt.qq.common.User;

public class QQClientUser {
	// 检验用户是否合法的方法
		public boolean checkUser(User u) {
			return new QQClientConServer().SendLoginInfoTOServer(u);
		}
	//用户注册
		
	//添加好友
}
