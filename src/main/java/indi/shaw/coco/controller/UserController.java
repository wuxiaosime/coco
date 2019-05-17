/**
 * @Package: indi.shaw.coco.menu
 * @author: shaw
 * @date: 2019年4月25日 上午2:38:45
 */
package indi.shaw.coco.controller;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import indi.shaw.coco.model.User;

/**
 * @author shaw
 *
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public final String login(User user, HttpServletResponse response) {
		System.out.println("login : " + user);
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST,GET");
		response.setHeader("Access-Control-Allow-Headers", "Access-Control");
		response.setHeader("Allow", "POST,GET");
		/*
		 * String info = loginUser(user); if (!"SUCC".equals(info)) {
		 * model.addAttribute("failMsg", "用户不存在或密码错误！"); return "/jsp/fail"; } else {
		 * model.addAttribute("successMsg", "登陆成功！");// 返回到页面说夹带的参数
		 * model.addAttribute("name", user.getName()); return "/jsp/success";// 返回的页面 }
		 */
		return "login";
	}

	@RequestMapping(value = "isRelogin", method = RequestMethod.POST)
	@ResponseBody()
	public final void isRelogin(User user, HttpServletResponse response) {
		System.out.println("isLogin : " + user);
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "POST,GET");
		response.setHeader("Access-Control-Allow-Headers", "Access-Control");
		response.setHeader("Allow", "POST,GET");
		JSONObject res = null;
		Boolean isLogin = isRelogin(user);
		try {
			if (isLogin) {
				res = new JSONObject("{data : {isRelogin : true}}");
			} else {
				res = new JSONObject("{data : {isRelogin : false}}");
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		writeJson(response, res.toString());
	}

	@RequestMapping(value = "dologin", method = RequestMethod.POST)
	public String dologin(String name, String password) {
		String info = loginUser(new User(name, password));
		System.out.println(info);
		if (!"SUCC".equals(info)) {
			return "index";
		} else {
			return "index";// 返回的页面
		}
	}

	private String loginUser(User user) {
		if (isRelogin(user))
			return "SUCC"; // 如果已经登陆，无需重新登录
		return shiroLogin(user); // 调用shiro的登陆验证
	}

	private boolean isRelogin(User user) {
		Subject us = SecurityUtils.getSubject();
		User u = (User) us.getPrincipal();
		System.out.println("Principal user:" + u);
		if (us.isAuthenticated()) {
			return true; // 参数未改变，无需重新登录，默认为已经登录成功
		}
		return false; // 需要重新登陆
	}

	private String shiroLogin(User user) {
		// 组装token，包括客户公司名称、简称、客户编号、用户名称；密码
		UsernamePasswordToken token = new UsernamePasswordToken(user.getName(), user.getPassword().toCharArray());
		System.out.println("login user token:" + token);
		token.setRememberMe(true);
		// shiro登陆验证
		try {
			SecurityUtils.getSubject().login(token);
		} catch (UnknownAccountException ex) {
			return "用户不存在或者密码错误！";
		} catch (IncorrectCredentialsException ex) {
			return "用户不存在或者密码错误！";
		} catch (AuthenticationException ex) {
			ex.printStackTrace();
			return ex.getMessage(); // 自定义报错信息
		} catch (Exception ex) {
			ex.printStackTrace();
			return "内部错误，请重试！";
		}
		return "SUCC";
	}
}
