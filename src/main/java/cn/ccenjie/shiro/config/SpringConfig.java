package cn.ccenjie.shiro.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.MethodInvokingFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;

import cn.ccenjie.shiro.realm.DaoRealm;

@Configuration
@ComponentScan(basePackages="cn.ccenjie.shiro.*", excludeFilters={@Filter(value=Controller.class)})
public class SpringConfig {
	
	@Autowired
	@Bean
	public DefaultWebSecurityManager DefaultWebSecurityManager(DaoRealm daoRealm) {
		DefaultWebSecurityManager dsm = new DefaultWebSecurityManager(daoRealm);
		return dsm;
	}
	
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}
	
	@Autowired
	@Bean
	public MethodInvokingFactoryBean methodInvokingFactoryBean(DefaultWebSecurityManager securityManager) {
		MethodInvokingFactoryBean mifb = new MethodInvokingFactoryBean();
		mifb.setStaticMethod("org.apache.shiro.SecurityUtils.setSecurityManager");
		mifb.setArguments(new Object[]{securityManager});
		return mifb;
	}
	
	@Autowired
	@Bean(name="shiroFilterFactoryBean")
	public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
		ShiroFilterFactoryBean sffb = new ShiroFilterFactoryBean();
		sffb.setSecurityManager(securityManager);
		sffb.setLoginUrl("/login.jsp");
		sffb.setUnauthorizedUrl("/error.jsp");
		Map<String,String> map = new HashMap<String,String>();
		map.put("/admin/**", "authc, roles[admin]");
		map.put("/user/select", "perms[user:select]");
		map.put("/user/edit", "perms[user:edit]");
		
		sffb.setFilterChainDefinitionMap(map);
		return sffb;
	}
}
