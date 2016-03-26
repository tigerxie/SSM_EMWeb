package cn.tiger.shop.order.controller;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tiger.shop.cart.vo.Cart;
import cn.tiger.shop.cart.vo.CartItem;
import cn.tiger.shop.order.service.OrderService;
import cn.tiger.shop.order.vo.OrderitemCustom;
import cn.tiger.shop.order.vo.OrdersCustom;
import cn.tiger.shop.user.vo.User;
import cn.tiger.shop.utils.PageBean;
import cn.tiger.shop.utils.PaymentUtil;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	/**
	 * 支付回调
	 * @return
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	@RequestMapping("/callBack")
	public String callBack(String r6_Order, String r3_Amt, HttpServletRequest request) throws NumberFormatException, Exception {
		// 修改订单的状态:
		OrdersCustom currOrdersCustom = orderService.findByOid(Integer.parseInt(r6_Order));
		// 修改订单状态为2:已经付款:
		currOrdersCustom.setState(2);
		orderService.update(currOrdersCustom);
		request.setAttribute("message","支付成功!订单编号为: "+r6_Order +" 付款金额为: "+r3_Amt);
		return "msg";
	}
	
	/**
	 * 订单支付
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/payOrder")
	public String payOrder(OrdersCustom ordersCustom, @Param(value="pd_FrpId")String pdFrpId, HttpServletResponse response) throws Exception {
		/*
		 * 1. 修改订单信息
		 */
		OrdersCustom exitOrder = orderService.findByOid(ordersCustom.getOid());
		exitOrder.setAddr(ordersCustom.getAddr());
		exitOrder.setName(ordersCustom.getName());
		exitOrder.setPhone(ordersCustom.getPhone());
		orderService.update(exitOrder);
		// 2.完成付款:
		// 付款需要的参数:
		String p0_Cmd = "Buy"; // 业务类型:
		String p1_MerId = "10001126856";// 商户编号:
		String p2_Order = ordersCustom.getOid().toString();// 订单编号:
		String p3_Amt = "0.01"; // 付款金额:
		String p4_Cur = "CNY"; // 交易币种:
		String p5_Pid = ""; // 商品名称:
		String p6_Pcat = ""; // 商品种类:
		String p7_Pdesc = ""; // 商品描述:
		String p8_Url = "http://localhost:8080/ssm_shop/order/callBack.action"; // 商户接收支付成功数据的地址:
		String p9_SAF = ""; // 送货地址:
		String pa_MP = ""; // 商户扩展信息:
		String pd_FrpId = pdFrpId;// 支付通道编码:
		String pr_NeedResponse = "1"; // 应答机制:
		String keyValue = "69cl522AV6q613Ii4W6u8K6XuW8vM1N6bFgyv769220IuYe9u37N4y7rI4Pl"; // 秘钥
		String hmac = PaymentUtil.buildHmac(p0_Cmd, p1_MerId, p2_Order, p3_Amt,
				p4_Cur, p5_Pid, p6_Pcat, p7_Pdesc, p8_Url, p9_SAF, pa_MP,
				pd_FrpId, pr_NeedResponse, keyValue); // hmac
		// 向易宝发送请求:
		StringBuffer sb = new StringBuffer("https://www.yeepay.com/app-merchant-proxy/node?");
		sb.append("p0_Cmd=").append(p0_Cmd).append("&");
		sb.append("p1_MerId=").append(p1_MerId).append("&");
		sb.append("p2_Order=").append(p2_Order).append("&");
		sb.append("p3_Amt=").append(p3_Amt).append("&");
		sb.append("p4_Cur=").append(p4_Cur).append("&");
		sb.append("p5_Pid=").append(p5_Pid).append("&");
		sb.append("p6_Pcat=").append(p6_Pcat).append("&");
		sb.append("p7_Pdesc=").append(p7_Pdesc).append("&");
		sb.append("p8_Url=").append(p8_Url).append("&");
		sb.append("p9_SAF=").append(p9_SAF).append("&");
		sb.append("pa_MP=").append(pa_MP).append("&");
		sb.append("pd_FrpId=").append(pd_FrpId).append("&");
		sb.append("pr_NeedResponse=").append(pr_NeedResponse).append("&");
		sb.append("hmac=").append(hmac);
		
		// 重定向:向易宝出发:
//		response.sendRedirect(sb.toString());
		return "redirect:" + sb.toString();
	}
	
	
	
	/**
	 * 预付款
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/findByOid")
	public String findByOid(Integer oid, HttpServletRequest request) throws Exception {
		/*
		 * 1. 调用service查询订单
		 */
		OrdersCustom ordersCustom = orderService.findByOid(oid);
		/*
		 * 2. 订单存入 request
		 */
		request.setAttribute("ordersCustom", ordersCustom);
		return "order";
	}
	
	/**
	 * 我的订单
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/myOrder")
	public String myOrder(HttpSession session, Integer page) throws Exception{
		User user = (User) session.getAttribute("user");
		PageBean<OrdersCustom> pageBean = orderService.myOrder(user.getUid(), page);
		session.setAttribute("pageBean", pageBean);
		return "orderList";
	}
	
	// 提交订单
	@RequestMapping("/save")
	public String save (HttpSession session, HttpServletRequest request) throws Exception {
		/*
		 * 1.生成订单：补全信息
		 */
		OrdersCustom ordersCustom = new OrdersCustom();
		ordersCustom.setOrdertime(new Date());
		ordersCustom.setState(1);
		// 得到User
		User exitUser = (User) session.getAttribute("user");
		if (exitUser == null) {
			request.setAttribute("error", "您还未登录！请先登录！");
			return "login";
		}
		ordersCustom.setUser(exitUser);
		// 得到Cart
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			request.setAttribute("error", "您还没有购物车！请先购物！");
			return "msg";
		}
		ordersCustom.setTotal(cart.getTotal());
		// 遍历购物车条目，并设置到订单条目
		for (CartItem cartItem : cart.getCartItems()) {
			OrderitemCustom orderitemCustom = new OrderitemCustom();
			orderitemCustom.setCount(cartItem.getCount());
//			orderitemCustom.setOrdersCustom(ordersCustom);
			orderitemCustom.setProduct(cartItem.getProduct());
			orderitemCustom.setSubtotal(cartItem.getSubtotal());
			// 将所有订单条目添加到订单
			ordersCustom.getOrderitemCustoms().add(orderitemCustom);
		}
		/*
		 * 2. 调用service方法完成生成订单
		 */
		orderService.save(ordersCustom);
		/*
		 * 3. 销毁cart
		 */
		cart.clearCart();
		/*
		 * 4. 保存 ordersCustom 到 request
		 */
		request.setAttribute("ordersCustom", ordersCustom);
		return "order";
	}
	
	/**
	 * 确认收货
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/updateState")
	public String updateState(OrdersCustom ordersCustom, HttpServletRequest request) throws Exception {
		ordersCustom = orderService.findByOid(ordersCustom.getOid());
		ordersCustom.setState(4);
		orderService.update(ordersCustom);
		request.setAttribute("message", "支付成功!订单编号为: "+ordersCustom.getOid() +" 付款金额为: "+ordersCustom.getTotal());
		return "msg";
	}
	
	
}
