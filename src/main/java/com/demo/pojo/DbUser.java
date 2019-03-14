package com.demo.pojo;


import groovy.transform.ToString;
import lombok.Data;
import lombok.experimental.Accessors;

/*
 * 测试用户对象
 */

@Data
public class DbUser {
	private String username;
	private String password;
	private Integer access_level;
	private String description;
}
