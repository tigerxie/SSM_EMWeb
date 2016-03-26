package cn.tiger.shop.order.admincontroller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.tiger.shop.order.service.OrderService;
import cn.tiger.shop.order.vo.OrderitemCustom;
import cn.tiger.shop.order.vo.OrdersCustom;
import cn.tiger.shop.utils.PageBean;

@Controller
@RequestMapping("/adminOrder")
public class AdminOrderController {

	@Autowired
	private OrderService orderService;
	
	
	/**
	 * 按分页查询所有订单
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/findAll")
	public String findAll(Integer page, HttpServletRequest request) throws Exception {
		if (page == null) {
			page = 1;
		}
		PageBean<OrdersCustom> pageBean = orderService.findAll(page);
		request.setAttribute("pageBean", pageBean);
		return "admin/order/list";
	}
	
	/**
	 * 加载订单条目
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/findOrderItem")
	public ModelAndView findOrderItem(Integer oid) throws Exception {
		List<OrderitemCustom> oiList = orderService.findOIByOid(oid);
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("oiList", oiList);
		modelAndView.setViewName("admin/order/orderItem");
		
		return modelAndView;
	}
	/**
	 * 发货：将订单状态修改为3
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/updateState")
	public String updateState(OrdersCustom ordersCustom) throws Exception {
		ordersCustom = orderService.findByOid(ordersCustom.getOid());
		ordersCustom.setState(3);
		orderService.update(ordersCustom);
		return "redirect:/adminOrder/findAll.action";
	}
	
	
}
