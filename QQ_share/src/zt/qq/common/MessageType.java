/**
 * 功能：定义消息的种类。，
 */
package zt.qq.common;

public interface MessageType {
	
	String MESSAGE_SUCCEED = "1"; // 表明登录成功
	String MESSAGE_LOGIN_FAIL = "2";// 表明登录失败
	String MESSAGE_COMM = "3"; // 普通信息包
	String MESSAGE_GET_ONLINEFRIEND = "4";// 要求遍历在线好友
	String MESSAGE_RET_ONLINEFRIEND = "5";// 返回在线好友，格式：按空格划分
	String MESSAGE_GROUP_MSG = "6";//群聊信息包
}
