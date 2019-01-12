package com.qhit.message.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.qhit.common.dao.BaseDao;
import com.qhit.message.bean.Message;
import com.qhit.message.dao.MessageDao;

public class MessageDaoImpl extends BaseDao implements MessageDao {
	
	@Override
	public List<Message> selMessageBySendto(String sendto) {
		
		String sql = "select * from msg where sendto = ?";
		List<Message> list = new ArrayList<Message>();
		ResultSet rs = null;
		rs = this.executeQuery(sql, sendto);
		try {
			while(rs!=null && rs.next()){
				int msgid = rs.getInt("msgid");
				String username = rs.getString("username");
				String title = rs.getString("title");
				String msgcontent = rs.getString("msgcontent");
				int state = rs.getInt("state");
				Date msg_create_date = rs.getDate("msg_create_date");
				Message message = new Message(msgid, username, title, msgcontent, state, sendto, msg_create_date);
				list.add(message);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			closeAll(null, null, rs);
		}
		return list;
	}

	@Override
	public Message selMessageByMsgid(int msgid) {
		String sql = "select * from msg where msgid = ?";
		Message message = null;
		ResultSet rs = null;
		rs = this.executeQuery(sql, msgid);
		try {
			while(rs!=null && rs.next()){
				String username = rs.getString("username");
				String title = rs.getString("title");
				String msgcontent = rs.getString("msgcontent");
				int state = rs.getInt("state");
				String sendto = rs.getString("sendto");
				Date msg_create_date = rs.getDate("msg_create_date");
				message = new Message(msgid, username, title, msgcontent, state, sendto, msg_create_date);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			closeAll(null, null, rs);
		}
		return message;
	}

	@Override
	public int addMessage(Message message) {
		String sql = "insert into msg(username,title,msgcontent,state,sendto,msg_create_date) values(?,?,?,0,?,now())";
		int count = 0;
		count = this.executeUpdate(sql, message.getUsername(),message.getTitle(),message.getMsgcontent(),message.getSendto());
		return count;
	}

	@Override
	public int delMessage(int msgid) {
		String sql = "delete from msg where msgid = ?";
		int count = 0;
		count = this.executeUpdate(sql, msgid);
		return count;
	}

	@Override
	public int updMessageState(int state, int msgid) {
		String sql = "update msg set state = ? where msgid = ?";
		int count = 0;
		count = this.executeUpdate(sql,state,msgid);
		return count;
	}

}
