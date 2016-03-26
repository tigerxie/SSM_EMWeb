package cn.tiger.shop.user.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.tiger.shop.cart.vo.Cart;
import cn.tiger.shop.user.service.UserService;
import cn.tiger.shop.user.vo.User;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;
	
	/**
	 * 用户退出功能
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("quit")
	public String quit(HttpServletRequest request)throws Exception {
		//销毁 session
		request.getSession().removeAttribute("user");
		return "index";
	}
	
	/**
	 * 用户登录功能
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login")
	public String login(User user,HttpServletRequest request)throws Exception {
		/*
		 * 1. 通过用户登录信息调用service获得已存在用户
		 */
		User exitUser = userService.login(user);
		/*
		 * 2. 判断用户
		 * 	>null，保存错误信息，回显，返回login（转回login.jsp）
		 *  >判断激活状态，1.代表激活 0.代表未激活
		 * 	>保存已存在用户到session，返回index
		 */
		if (exitUser == null) {
			request.setAttribute("error","用户名或密码错误！");
			request.setAttribute("form",user);
			return "login";
		} else if (exitUser.getState().intValue() == 0) {
			request.setAttribute("error","用户未被激活！");
			request.setAttribute("form",user);
			return "login";
		}
		request.getSession().setAttribute("user", exitUser);
		//登录成功创建购物车
		request.getSession().setAttribute("cart", new Cart());
		return "index";
	}
	
	/**
	 * 邮件激活用户
	 * @param request
	 * @param code
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/active")
	public String active(HttpServletRequest request, String code)throws Exception {
	
		User exitUser = userService.findByCode(code);
		if (exitUser == null) {
			request.setAttribute("error","激活失败：激活码无效！");
		} else {
			exitUser.setState(1);
			exitUser.setCode(null);
			userService.update(exitUser);
			request.setAttribute("message","用户激活成功！");
		}
		return "msg";
	}
	
	
	/**
	 * 注册提交
	 * @param request
	 * @param user
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/regist")
	public String regist(HttpServletRequest request, User user)throws Exception {
		
		//1. 调用service方法完成数据保存
		userService.save(user);
		
		//2. 保存成功信息输出到页面
		request.setAttribute("message", "邮件发送成功，请完成激活！");
		
		return "msg";
	}
	
	/**
	 * 异步校验验证码
	 * @param email
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/ajaxValidateVerifyCode",method=RequestMethod.POST)
	public String ajaxValidateVerifyCode(@RequestParam(value="verifyCode") String verifyCode, HttpServletRequest request)throws Exception {
		/*
		 * 1. 获得表单参数,和sesion中的验证码文本
		 * 2. 将其做比较
		 * 3. 相同，则将true输出到页面
		 */
		String checkcode = (String) request.getSession().getAttribute("checkcode");
		if (!verifyCode.equalsIgnoreCase(checkcode)) {
			return "false";
		}
		return "true";
	}
	
	
	// @ResponseBody 表示将返回字符串转化成 json 串
	@ResponseBody
	@RequestMapping(value="/ajaxFindByEmail",method=RequestMethod.POST)
	public String ajaxFindByEmail(@RequestParam(value="email") String email)throws Exception {
		/*
		 * 使用 email 调用service方法完成校验，得到已存在用户数量
		 * 	》判断，>=1表示已被注册，返回“1”
		 * 	》否则返回“0”
		 */
		int exitUserCount = userService.ajaxFindByEmail(email);
		if (exitUserCount >= 1) {
			return "1";
		}
		return "0";
	}
	
	/**
	 * 异步校验用户名
	 * @param username
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value="/ajaxFindByName",method=RequestMethod.POST)
	public String ajaxFindByName(String username)throws Exception {
		/*
		 * 使用用户名调用service方法完成校验，得到已存在用户数量
		 * 	》判断，>=1表示已被注册，返回“1”
		 * 	》否则返回“0”
		 */
		int exitUserCount = userService.ajaxFindByName(username);
		if (exitUserCount >= 1) {
			return "1";
		}
		return "0";
	}
	
	
	//跳转到登录页面
	@RequestMapping("/loginPage")
	public String loginPage()throws Exception {
		return "login";
	}
	
	//跳转到注册页面
	@RequestMapping("/registPage")
	public String registPage()throws Exception {
		return "regist";
	}
}
