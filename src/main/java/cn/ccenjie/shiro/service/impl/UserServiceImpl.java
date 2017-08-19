package cn.ccenjie.shiro.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.ccenjie.shiro.dao.UserDao;
import cn.ccenjie.shiro.entity.User;
import cn.ccenjie.shiro.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;

	public User findByUsername(String username) {
		return userDao.findByUsername(username);
	}

	public String findRoleByUsername(String username) {
		return userDao.findRoleByUsername(username);
	}

	public String findPermByUsername(String username) {
		return userDao.findPermByUsername(username);
	}

}
