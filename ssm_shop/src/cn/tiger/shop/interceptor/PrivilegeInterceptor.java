package cn.tiger.shop.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import cn.tiger.shop.adminuser.vo.Adminuser;

/**
 * 后台权限拦截器
 * @author Administrator
 *
 */
public class PrivilegeInterceptor implements HandlerInterceptor {


	//后台权限拦截
	@Override
	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		
		//获取请求的url
		String url = arg0.getRequestURI();
		//判断url是否是公开 地址（实际使用时将公开 地址配置配置文件中）
		//这里公开地址是登陆提交的地址
		if(url.indexOf("index.action") >= 0 || url.indexOf("login.action") >= 0 ){
			//如果进行登陆提交，放行
		return true;
		}
		
		//判断 session 中adminuser
		Adminuser adminuser = (Adminuser) arg0.getSession().getAttribute("adminuser");
		if (adminuser != null) {
			return true;
		}
		arg0.setAttribute("error", "您还没有登录！没有访问权限！");
		arg0.getRequestDispatcher("/adminuser/index.action").forward(arg0, arg1);
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}


}
