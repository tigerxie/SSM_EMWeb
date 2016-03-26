package cn.tiger.shop.adminuser.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tiger.shop.adminuser.service.AdminuserService;
import cn.tiger.shop.adminuser.vo.Adminuser;


@Controller
@RequestMapping("/adminuser")
public class AdminuserController {

	@Autowired
	private AdminuserService adminuserService;

	/**
	 * 管理员登录功能
	 * @return
	 */
	@RequestMapping("/login")
	public String login(Adminuser adminUser, HttpServletRequest request) {
		/*
		 * 判断 session 中是否有 adminuser
		 */
		Adminuser exitAdminUser = (Adminuser) request.getSession().getAttribute("adminuser");
		if (exitAdminUser != null) {
			return "admin/home";
		}
		/*
		 * 后台校验用户名密码
		 */
		exitAdminUser = adminuserService.login(adminUser);
		if (exitAdminUser == null) {
			request.setAttribute("error","用户名或密码错误！");
			return "admin/index";
		}
		/*
		 * 校验成功，保存到session
		 */
		request.getSession().setAttribute("adminuser", exitAdminUser);
		return "admin/home";
	}
	
	//跳转到 index 页面
	@RequestMapping("/index")
	public String index() throws Exception {
		return "admin/index";
	}
	//跳转到框架页面
	@RequestMapping("/top")
	public String top() throws Exception {
		return "admin/top";
	}
	@RequestMapping("/left")
	public String left() throws Exception {
		return "admin/left";
	}
	@RequestMapping("/welcome")
	public String welcome() throws Exception {
		return "admin/welcome";
	}
	@RequestMapping("/bottom")
	public String bottom() throws Exception {
		return "admin/bottom";
	}
	
}
