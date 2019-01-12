package com.qhit.user.service;

import com.qhit.user.bean.User;

public interface UserService {
	
	/**
	 * 登录
	 * @return
	 */
	public User login(String username,String password);
	/**
	 * 注册
	 * @param user
	 */
	public int register(User user);
}
