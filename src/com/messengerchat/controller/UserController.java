package com.messengerchat.controller;

import java.sql.SQLException;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.messengerchat.services.UserService;

@Controller
public class UserController {
	@RequestMapping("/")
	public String index() {
		return "login";
	}
	
	@RequestMapping("/chat")
	public String login(@RequestParam Map<String, String> allInfo) throws ClassNotFoundException, SQLException {
		String username = allInfo.get("username");
		String password = allInfo.get("password");
		
		boolean checker = new UserService().checkUser(username, password);
		if(!checker) return "login";
		
		return "index";
	}
	
	@RequestMapping("/signup")
	public String signup() {
		return "signup";
	}
	
	@RequestMapping("/create-account")
	@ResponseBody
	public String createAccount(@RequestParam Map<String, String> allInfo) throws ClassNotFoundException, SQLException {
		String username = allInfo.get("username");
		String password = allInfo.get("password");
		String re_password = allInfo.get("re-password");
		String name = allInfo.get("name");
		
		if(!password.equals(re_password)) {
			return "re-password donn't match!";
		}
		
		if(username.length() > 10 || password.length() > 10 || name.length() > 50) {
			return "username, password or name too long\n username and password maximum is 10 character, "
					+ "name maximum is 50 character";
		}
		
		new UserService().addUser(name, username, re_password);
		return "successfull create account!";
	}
}
