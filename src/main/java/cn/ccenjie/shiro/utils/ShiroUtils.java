package cn.ccenjie.shiro.utils;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;

@SuppressWarnings("deprecation")
public class ShiroUtils {
	
	public static Subject getSubject(String ini) {
		// 通过配置文件创建工厂类
		Factory<SecurityManager> factory = 
		new IniSecurityManagerFactory(ini);
		// 获取安全管理实例
		SecurityManager securityManager = factory.getInstance();
		// 绑定安全管理实例
		SecurityUtils.setSecurityManager(securityManager);
				// 获取当前用户（对象）
		return SecurityUtils.getSubject();
	}
}
