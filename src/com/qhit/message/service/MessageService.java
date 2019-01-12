package com.qhit.message.service;

import java.util.List;

import com.qhit.message.bean.Message;
import com.qhit.user.bean.User;

/**
 * 信息业务类
 * @author admin
 *
 */
public interface MessageService {
	/**
	 * 根据收件人显示主页
	 * @param sendto
	 * @return
	 */
	public List<Message> showIndex(String sendto);
	/**
	 * 查看
	 * @param msgid
	 * @return
	 */
	public Message showDetail(int msgid);
	/**
	 * 删除
	 * @param msgid
	 * @return
	 */
	public int delMessage(int msgid);
	/**
	 * 打开发送页面
	 * @param username
	 * @return
	 */
	public List<User> showSend(String username);
	/**
	 * 发送信息
	 * @param message
	 * @return
	 */
	public int sendMessage(Message message);
}
