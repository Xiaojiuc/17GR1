package com.qhit.user.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.qhit.common.dao.BaseDao;
import com.qhit.user.bean.User;
import com.qhit.user.dao.UserDao;
/**
 * 用户DAO实现类
 * @author Administrator
 *
 */
public class UserDaoImpl extends BaseDao implements UserDao {

	@Override
	public int addUser(User user) {
		String sql = "insert into msg_userinfo values(?,?,?)";
		int count = this.executeUpdate(sql, user.getUsername(),user.getPassword(),user.getEmail());
		return count;
	}

	@Override
	public User selUserByUsername(String username) {
		String sql = "select * from msg_userinfo where username=?";
		ResultSet rs = null;
		User user = null;
		rs = this.executeQuery(sql, username);
		try {
			while(rs!=null&&rs.next()){
				String password = rs.getString("password");
				String email = rs.getString("email");
				user = new User(username, password, email);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			closeAll(null, null, rs);
		}
		return user;
	}

	@Override
	public List<User> selUserRemoveUsername(String username) {
		String sql = "select * from msg_userinfo where username != ?";
		ResultSet rs = null;
		List<User> list = new ArrayList<User>();
		rs = this.executeQuery(sql, username);
		try {
			while(rs!=null&&rs.next()){
				String password = rs.getString("password");
				String email = rs.getString("email");
				String selUsername = rs.getString("username");
				User user = new User(selUsername, password, email);
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			closeAll(null, null, rs);
		}
		return list;
	}

}
