package com.qhit.message.bean;

import java.util.Date;

public class Message {
	private int msgid;
	private String username;
	private String title;
	private String msgcontent;
	private int state;
	private String sendto;
	private Date msg_create_date;
	public Message(int msgid, String username, String title, String msgcontent,
			int state, String sendto, Date msg_create_date) {
		super();
		this.msgid = msgid;
		this.username = username;
		this.title = title;
		this.msgcontent = msgcontent;
		this.state = state;
		this.sendto = sendto;
		this.msg_create_date = msg_create_date;
	}
	public Message() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getMsgid() {
		return msgid;
	}
	public void setMsgid(int msgid) {
		this.msgid = msgid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMsgcontent() {
		return msgcontent;
	}
	public void setMsgcontent(String msgcontent) {
		this.msgcontent = msgcontent;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getSendto() {
		return sendto;
	}
	public void setSendto(String sendto) {
		this.sendto = sendto;
	}
	public Date getMsg_create_date() {
		return msg_create_date;
	}
	public void setMsg_create_date(Date msg_create_date) {
		this.msg_create_date = msg_create_date;
	}
	
}
