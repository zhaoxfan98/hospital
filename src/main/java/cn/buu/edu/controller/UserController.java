package cn.buu.edu.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.buu.edu.bean.User;
import cn.buu.edu.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="logout",method=RequestMethod.GET)
	public String logout(HttpSession session) {
		session.removeAttribute("curUser");
		return "redirect:/login";
		
	}
	
	@RequestMapping(value="auth",method=RequestMethod.POST)
	public String validUser(User user, HttpSession session,RedirectAttributes attr) {
		User curUser = userService.login(user);
		
		if(curUser == null) {
			attr.addFlashAttribute("errorMsg","用户名或密码不正确");
			return "redirect:/login";
		}else {
			session.setAttribute("curUser", curUser);
			return "redirect:/index.jsp";
		}
	}
	
	@RequestMapping(value="/regist",method=RequestMethod.POST)
	public String regist(User user) {
		
		user.setStatus(1);
		user.setModifytime(new Date());
		
		boolean result = userService.regist(user);
		
		if(result) {
			return "redirect:/login.jsp";
		}else {
			return "redirect:/regist.jsp";
		}
	}
	
	@RequestMapping("/info")
	@ResponseBody
	public Map<String,Object> getUserInfoByUsername(@RequestParam("username") String username){
		User user = userService.getUserInfo(username);
		Map<String,Object> msg = new HashMap<>();
		
		if(user != null) {
			msg.put("statusCode", 500);
			msg.put("message", "当前用户名已存在");
		}else {
			msg.put("statusCode", 200);
			msg.put("message", "当前用户名可注册");
		}
		
		return msg;
	}
	
	@RequestMapping("/checkEmail")
	@ResponseBody
	public Map<String,Object> getUserInfoByEmail(@RequestParam("email") String email){
		User user = userService.getUserInfoByEmail(email);
		Map<String,Object> msg = new HashMap<>();
		
		System.out.println(user);
		
		if(user != null) {
			msg.put("statusCode", 500);
			msg.put("message", "当前邮箱已存在");
		}else {
			msg.put("statusCode", 200);
			msg.put("message", "当前用户名可注册");
		}
		
		return msg;
	}
}
