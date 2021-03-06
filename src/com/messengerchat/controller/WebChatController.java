package com.messengerchat.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.messengerchat.model.User;
import com.messengerchat.services.ChatMessageService;
import com.messengerchat.services.UserService;

@Controller
public class WebChatController {
	@RequestMapping(value = "/chat", method = RequestMethod.GET)
	public String requestChat(HttpServletRequest req, Model theModel,
			@RequestParam(name = "usernameTo", required = false) String usernameTo)
			throws ClassNotFoundException, SQLException {
		/* get session and check username and password */
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");
		String password = (String) session.getAttribute("password");

		boolean checker = new UserService().checkUser(username, password);
		if (!checker)
			return "login";

		User user = new UserService().getFullInfo(username);
		User userTo = new User();
		List<User> listUsers = new UserService().getListUsers();
		if (usernameTo == null) {
			if (listUsers.get(0).getUsername().equals(user.getUsername())) {
				userTo.setUsername(listUsers.get(1).getUsername());
				userTo.setName(listUsers.get(1).getName());
				userTo.setPassword(listUsers.get(1).getPassword());
			} else {
				userTo.setUsername(listUsers.get(0).getUsername());
				userTo.setName(listUsers.get(0).getName());
				userTo.setPassword(listUsers.get(0).getPassword());
			}
		} else {
			for (User u : listUsers) {
				if (u.getUsername().equals(usernameTo)) {
					userTo.setName(u.getName());
					userTo.setPassword(u.getPassword());
					userTo.setUsername(u.getUsername());
					break;
				}
			}
		}

		theModel.addAttribute("user", user);
		theModel.addAttribute("listUsers", listUsers);
		theModel.addAttribute("userTo", userTo);

		// get content chat
		String chat_content = new ChatMessageService().getContentChat(user, userTo);
		theModel.addAttribute("chat_content", chat_content);

		return "index";
	}
}
