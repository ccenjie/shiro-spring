package cn.ccenjie.shiro.service;

import cn.ccenjie.shiro.entity.User;

public interface UserService {
	
	User findByUsername(String username);
	
	String findRoleByUsername(String username);
	
	String findPermByUsername(String username);
}
