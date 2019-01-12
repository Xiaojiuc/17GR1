package com.qhit.user.service.impl;

import com.qhit.user.bean.User;
import com.qhit.user.dao.UserDao;
import com.qhit.user.dao.impl.UserDaoImpl;
import com.qhit.user.service.UserService;

public class UserServiceImpl implements UserService {
	private UserDao  userDao = new UserDaoImpl();
	@Override
	public User login(String username, String password) {
		User user = userDao.selUserByUsername(username);
		if(user!=null){
			if(user.getPassword().equals(password)){
				return user;
			}
		}
		return null;
	}

	@Override
	public int register(User user) {
		//通过用户名查询用户
		User selUser = userDao.selUserByUsername(user.getUsername());
		if(selUser!=null){//用户名已存在，不能注册
			return 0;
		}
		int count = userDao.addUser(user);
		return count;
	}

}
