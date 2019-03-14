package com.demo.impl;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.demo.dao.DataMapper;
import com.demo.pojo.DbUser;

/*
 * 自定义用户名密码校验实现，一定要@Service注解，然后在配置类中加载（重载configure）
 */
@Service
public class UserDetailInfo implements UserDetailsService{

	//数据库操作
	@Autowired
	private DataMapper dbDataMapper;

	//必须重写，自己来实现登陆验证
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("这里点击登录后进");
		System.out.println("user["+username+"] is logining...");
		DbUser dbUser = dbDataMapper.getUserLoginInfo(username);
		if(dbUser==null)
		{
			System.out.println("user["+username+"] is not exist!");
			throw new UsernameNotFoundException(username + " do not exist!");  
		}
		System.out.println("Get user info from db: "+ dbUser.toString());

		UserDetails	user = new User(dbUser.getUsername(), dbUser.getPassword(), true, true, true, true,
				getAuthorities(dbUser.getAccess_level())); 

		return user;
	}

	/**
	 * 获得访问角色权限
	 */
	public Collection<GrantedAuthority> getAuthorities(Integer access) {

		Collection<GrantedAuthority> authorities = new ArrayList<>();      

		//所有的用户默认拥有ROLE_USER权限	
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		if (access.compareTo(0) == 0) {
			// 如果参数access为0.则拥有ROLE_ADMIN权限
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
		}else if(access.compareTo(2) == 0) {
			// 如果参数access为2.则拥有ROLE_TAO权限
			authorities.add(new SimpleGrantedAuthority("ROLE_TAO"));
		}
		
		//最终这里返回的是用户权限集合
		return authorities;
	}

}
