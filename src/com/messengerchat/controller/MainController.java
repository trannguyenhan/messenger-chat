package com.messengerchat.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.messengerchat.model.User;
import com.messengerchat.services.UserService;

@Controller
public class MainController {
	private User user;
	private List<User> listUsers;
	
	@RequestMapping("/")
	public void index(HttpServletResponse resp) throws IOException {
		resp.sendRedirect("login");
	}
	
	@RequestMapping("/login")
	public String login(HttpServletRequest req, HttpServletResponse resp, @RequestParam Map<String, String> allInfo) 
			throws ClassNotFoundException, SQLException, IOException {
		HttpSession session = req.getSession();
		
		/* get username and password */
		String username = allInfo.get("username");
		String password = allInfo.get("password");
		
		/* check username and password
		 * if false -> return login page
		 * else -> return chat page
		 * */
		boolean checker = new UserService().checkUser(username, password);		
		if(!checker) return "login";
		
		/* add session */
		session.setAttribute("username", username);
		session.setAttribute("password", password);
		
		user = new UserService().getFullInfo(username);
		listUsers = new UserService().getListUsers();
		
		resp.sendRedirect("chat");
		return null;
	}
	
	@RequestMapping("/chat")
	public String login(HttpServletRequest req, Model theModel) 
			throws ClassNotFoundException, SQLException {
		/* get session and check username and password */
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
		
		boolean checker = new UserService().checkUser(username, password);
		if(!checker) return "login";
		
		theModel.addAttribute("user", user);
		theModel.addAttribute("listUsers", listUsers);
		return "index";
	}
	
	@RequestMapping("signup")
	public String riderectSignup(HttpServletResponse resp) throws IOException {
		return "signup";
	}
	
	@RequestMapping("/handle-signup")
	@ResponseBody
	public String signup(@RequestParam Map<String, String> allInfo) throws ClassNotFoundException, SQLException {
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
	
	@RequestMapping("/signout")
	public void signout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		HttpSession session = req.getSession();
		session.invalidate();
		
		resp.sendRedirect("chat");
	}
}
