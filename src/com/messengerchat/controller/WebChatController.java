package com.messengerchat.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.messengerchat.model.User;
import com.messengerchat.services.UserService;

@Controller
public class WebChatController {
	@RequestMapping("/chat")
	public String login(HttpServletRequest req, Model theModel) 
			throws ClassNotFoundException, SQLException {
		/* get session and check username and password */
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
		
		boolean checker = new UserService().checkUser(username, password);
		if(!checker) return "login";
		
		User user = new UserService().getFullInfo(username);
		List<User> listUsers = new UserService().getListUsers();
		
		theModel.addAttribute("user", user);
		theModel.addAttribute("listUsers", listUsers);
		return "index";
	}
}
