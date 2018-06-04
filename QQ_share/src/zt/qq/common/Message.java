package zt.qq.common;

import java.io.Serializable;

public class Message implements Serializable {
	

	private String mesType;	//消息种类
	
	private String sender;	//发送者
	
	private String getter;	//接收者
	
	private String content;	//内容
	
	private String sendTime;//时间
	
	
	public String getSendTime() {
		return sendTime;
	}

	public void setSendTime(String sendTime) {
		this.sendTime = sendTime;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getGetter() {
		return getter;
	}

	public void setGetter(String getter) {
		this.getter = getter;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getMesType() {
		return mesType;
	}

	public void setMesType(String mesType) {
		this.mesType = mesType;
	}

	@Override
	public String toString() {
		return "Message [mesType=" + mesType + ", sender=" + sender + ", getter=" + getter + ", content=" + content
				+ ", sendTime=" + sendTime + "]";
	}
}
