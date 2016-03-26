package cn.tiger.shop.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
/**
 * 全局异常处理
 * @author tiger
 *
 */
public class ShopExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		ShopException shopException = null;
		if (ex instanceof ShopException) {
			shopException = (ShopException)ex;
		} else {
			shopException = new ShopException("位置错误！");
		}
		
		String message = shopException.getMessage();
		
		ModelAndView modelAndView = new ModelAndView();
		
		modelAndView.addObject("message", message);
		
		modelAndView.setViewName("error");
		
		return modelAndView;
	}

}
