package com.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import com.demo.impl.UserDetailInfo;

/*
 * 配置类：
 * 重写它的方法来设置一些web安全的细节,如配置security的登录页面和传递的参数，公共路径权限属性等
 */
@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)  //控制权限到请求方法级别
//@EnableGlobalMethodSecurity(prePostEnabled = true)//方法调用前鉴权
@EnableWebSecurity //禁用Boot的默认Security配置，配合@Configuration启用自定义配置（需要扩展WebSecurityConfigurerAdapter）
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	

	//自定义认证对象
	@Autowired
	private UserDetailInfo urlUserService;

	//HTTP请求安全处理
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		System.out.println("这里程序运行第二个进入");
		//请求权限配置
		http.authorizeRequests()
		//指定了/和/home不需要任何认证就可以访问，
		.antMatchers("/", "/login","/**").permitAll()
		//任何请求，登录后方可访问。
		.anyRequest().authenticated() 
		//登陆界面参数
		.and().formLogin().loginPage("/login").defaultSuccessUrl("/hello")/*.usernameParameter("username").passwordParameter("password")*/.permitAll()
		//设置注销成功后跳转页面，默认是跳转到登录页面
		.and().logout().logoutSuccessUrl("/login").permitAll()
		//权限访问失败界面，关键，如果不定义的话会抛出异常
		.and().exceptionHandling().accessDeniedPage("/denied")
		;
		http.csrf().disable();
	}

	/*
	 * 身份验证管理生成器。一定要重载！！！不然自定义的登陆校验不生效
	 * */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		System.out.println("不用测试了这里程序运行第一个进入");
		auth.userDetailsService(urlUserService)
	
		/*.passwordEncoder(new PasswordEncoder() {
			//可以自己定义密码匹配规则
			@Override
			public String encode(CharSequence rawPassword) {
				return (String)rawPassword;//MD5Util.encode((String) rawPassword);
			}

			@Override
			public boolean matches(CharSequence rawPassword, String encodedPassword) {
				System.out.println(encodedPassword + "---" + (String)rawPassword);
				return encodedPassword.equals((String) rawPassword);
			}
		})*/;
	}
	
}
