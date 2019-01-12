package com.qhit.message.dao;

import java.util.List;

import com.qhit.message.bean.Message;

public interface MessageDao {
	/**
	 * 根据收件人查询信息
	 * @param sendto
	 * @return
	 */
	public List<Message> selMessageBySendto(String sendto);
	/**
	 * 根据主键查询信息
	 * @param msgid
	 * @return
	 */
	public Message selMessageByMsgid(int msgid);
	/**
	 * 添加信息
	 * @param message
	 * @return
	 */
	public int addMessage(Message message);
	/**
	 * 删除信息
	 * @param msgid
	 * @return
	 */
	public int delMessage(int msgid);
	/**
	 * 更新状态
	 * @param state
	 * @param msgid
	 * @return
	 */
	public int updMessageState(int state,int msgid);
}
