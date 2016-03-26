package cn.tiger.shop.order.service;

import java.util.List;

import cn.tiger.shop.order.vo.OrderitemCustom;
import cn.tiger.shop.order.vo.OrdersCustom;
import cn.tiger.shop.utils.PageBean;

public interface OrderService {

	// 提交订单
	void save(OrdersCustom ordersCustom)throws Exception;

	//我的订单
	PageBean<OrdersCustom> myOrder(Integer uid, Integer page) throws Exception;

	//通过 oid 查询订单
	OrdersCustom findByOid(Integer oid) throws Exception;

	//修改订单
	void update(OrdersCustom exitOrder) throws Exception;

	//查询所有订单（分页）
	PageBean<OrdersCustom> findAll(Integer page) throws Exception;

	//加载订单条目
	List<OrderitemCustom> findOIByOid(Integer oid) throws Exception;
}
