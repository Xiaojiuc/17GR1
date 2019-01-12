package com.qhit.user.dao;

import java.util.List;

import com.qhit.user.bean.User;

/**
 * 用户DAO
 * @author Administrator
 *
 */
public interface UserDao {
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public int addUser(User user);
	/**
	 * 根据用户名查询
	 * @param username
	 * @return
	 */
	public User selUserByUsername(String username);
	/**
	 * 查询除该用户之外的用户
	 * @param username
	 * @return
	 */
	public List<User> selUserRemoveUsername(String username);
	
}
