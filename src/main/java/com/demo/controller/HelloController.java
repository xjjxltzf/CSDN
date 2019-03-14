package com.demo.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * 网络控制层：返回数据的controller。这里映射到resources目录下的templates的html页面。 * 
 */
@Controller
public class HelloController {
	/*@RequestMapping("/")
	public String index() {
		return "home";
	}
	@RequestMapping("/home")
	public String home() {
		return "home";
	}*/

	/*当我们访问这个URL的时候，Spring Security会帮我们验证当前用户是否有权限访问该地址。
	 *官方推荐的鉴权注解方式，控制权限到请求方法级别。可通过三种方式的注解：
	 *注解方式1：@Secured， spring自带的注解方法：securedEnabled = true
	 *注解方式2：@PreAuthorize，方法调用前注解：securedEnabled = true
	 *注解方式2：@RolesAllowed，非spring框架: jsr250Enabled = true
	 *注意1：角色要填全名！
	 *注意2：一定要在自定义的WebSecurityConfigurerAdapter中添加注解。@EnableGlobalMethodSecurity(axx=bxx)！axx/bxx见上
	 */
	@Secured("ROLE_USER")
	@RequestMapping("/hello")
	//@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
	public String hello(){
		return "hello";
	}

	@RequestMapping(value = "/login")
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/logout")
	public String logout() {
		return "home";
	}

	@RequestMapping(value = "/denied")
	public String denied() {
		return "denied";
	}

	@Secured("ROLE_ADMIN")
	@RequestMapping(value = "/admin")
	//@PreAuthorize("hasAnyRole('ROLE_ADMIN')")
	public String admin() {
		return "admin";
	}
}

