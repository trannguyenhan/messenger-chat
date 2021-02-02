package com.messengerchat.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.messengerchat.services.UserService;

@Controller
public class MainController {
	@RequestMapping("/")
	public String index(HttpServletResponse resp, HttpServletRequest req) 
			throws IOException, ClassNotFoundException, SQLException {
		String username = (String) req.getSession().getAttribute("username");
		
		if (username == null || username.isEmpty()) {
            return "login";
        }

		resp.sendRedirect("chat");
		return null;
	}

	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String showLoginPage(HttpServletRequest req, HttpServletResponse resp) 
			throws ClassNotFoundException, SQLException, IOException {
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");
		
		boolean checker = new UserService().checkUser(username, password);		
		if(checker) {
			resp.sendRedirect("chat");
		} 
		
		return "login";
	}
	
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest req, HttpServletResponse resp, @RequestParam Map<String, String> allInfo) 
			throws ClassNotFoundException, SQLException, IOException {
		HttpSession session = req.getSession();
		
		/* get username and password */
		String username = allInfo.get("username");
		String password = allInfo.get("password");
		
		boolean checker = new UserService().checkUser(username, password);		
		if(!checker) return "login";
		
		/* add session */
		session.setAttribute("username", username);
		session.setAttribute("password", password);
		
		resp.sendRedirect("chat");
		return null;
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
		
		if(username.length() == 0 || password.length() == 0 || name.length() == 0) {
			return "username, password and name must long than 1 characters";
		}
		
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
