package cn.ccenjie.shiro.action;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.ccenjie.shiro.utils.ShiroUtils;

@Controller
public class UserAction {
	
	@RequestMapping("/login")
	public String login(String username, String password) {
		Subject currentUser = ShiroUtils.getSubject("classpath:shiro.ini");
		if ( !currentUser.isAuthenticated() ) { //如果用户不是已经验证的
			try {
				UsernamePasswordToken token = 
			    		new UsernamePasswordToken(username, password);
				currentUser.login(token);
			} catch (Exception e) {		
				e.printStackTrace();
				return "login";
			}
			
		}
		return "index";
	}
}
