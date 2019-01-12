package com.qhit.message.service.impl;

import java.util.List;

import com.qhit.message.bean.Message;
import com.qhit.message.dao.MessageDao;
import com.qhit.message.dao.impl.MessageDaoImpl;
import com.qhit.message.service.MessageService;
import com.qhit.user.bean.User;
import com.qhit.user.dao.UserDao;
import com.qhit.user.dao.impl.UserDaoImpl;

public class MessageServiceImpl implements MessageService {
	private MessageDao messageDao = new MessageDaoImpl();
	private UserDao userDao = new UserDaoImpl();
	@Override
	public List<Message> showIndex(String sendto) {
		// TODO Auto-generated method stub
		return messageDao.selMessageBySendto(sendto);
	}

	@Override
	public Message showDetail(int msgid) {
		//更新信息状态
		messageDao.updMessageState(1, msgid); //更新为已读
		return messageDao.selMessageByMsgid(msgid);
	}

	@Override
	public int delMessage(int msgid) {
		// TODO Auto-generated method stub
		return messageDao.delMessage(msgid);
	}

	@Override
	public List<User> showSend(String username) {
		// TODO Auto-generated method stub
		return userDao.selUserRemoveUsername(username);
	}

	@Override
	public int sendMessage(Message message) {
		// TODO Auto-generated method stub
		return messageDao.addMessage(message);
	}

}
