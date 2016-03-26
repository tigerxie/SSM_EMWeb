package cn.tiger.shop.order.vo;

import java.util.ArrayList;
import java.util.List;

import cn.tiger.shop.user.vo.User;

/**
 * 自定义订单类
 * @author Administrator
 *
 */
public class OrdersCustom extends Orders {

	//注入用户  User 
	private User user;
	
	//注入订单条目  OrderitemCustom
	private List<OrderitemCustom> orderitemCustoms = new ArrayList<OrderitemCustom>();

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<OrderitemCustom> getOrderitemCustoms() {
		return orderitemCustoms;
	}

	public void setOrderitemCustoms(List<OrderitemCustom> orderitemCustoms) {
		this.orderitemCustoms = orderitemCustoms;
	}

	
}