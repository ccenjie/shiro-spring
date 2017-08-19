package cn.ccenjie.shiro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import cn.ccenjie.shiro.entity.User;
import cn.ccenjie.shiro.utils.DBUtils;

@Repository
public class UserDao {
	
	/**
	 * 查询用户名对应的用户
	 * @param username
	 * @return
	 */
	public User findByUsername(String username) {
		Connection connection = DBUtils.getConnection();
		String sql = "select username,password from user where username = ?";
		try {
			PreparedStatement prep = connection.prepareStatement(sql);
			prep.setString(1, username);
			ResultSet re = prep.executeQuery();
			if(re.next()) {
				User user = new User();
				user.setUsername(re.getString(1));
				user.setPassword(re.getString(2));
				return user;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据用户名获取对应的角色
	 * @param username
	 * @return
	 */
	public String findRoleByUsername(String username) {
		Connection connection = DBUtils.getConnection();
		String sql = "select rolename from user u,role r "
				+ "where u.roleId=r.id and username = ?";
		try {
			PreparedStatement prep = connection.prepareStatement(sql);
			prep.setString(1, username);
			ResultSet re = prep.executeQuery();
			if(re.next()) {
				return re.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据用户名获取对应的权限
	 * @param username
	 * @return
	 */
	public String findPermByUsername(String username) {
		Connection connection = DBUtils.getConnection();
		String sql = "select permissionname from user u,role r,permission p "
				+ "where u.roleId=r.id and r.id=p.roleId and username = ?";
		try {
			PreparedStatement prep = connection.prepareStatement(sql);
			prep.setString(1, username);
			ResultSet re = prep.executeQuery();
			if(re.next()) {
				return re.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
