package cn.tiger.shop.order.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.tiger.shop.order.mapper.OrderitemMapperCustom;
import cn.tiger.shop.order.mapper.OrdersMapperCustom;
import cn.tiger.shop.order.service.OrderService;
import cn.tiger.shop.order.vo.Orderitem;
import cn.tiger.shop.order.vo.OrderitemCustom;
import cn.tiger.shop.order.vo.OrdersCustom;
import cn.tiger.shop.product.vo.Product;
import cn.tiger.shop.utils.PageBean;
import cn.tiger.shop.utils.PageConstants;

public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrdersMapperCustom ordersMapperCustom;
	
	@Autowired
	private OrderitemMapperCustom orderitemMapperCustom;
	
	// 提交订单
	@Override
	public void save(OrdersCustom ordersCustom) throws Exception {
		//保存订单
		ordersMapperCustom.save(ordersCustom);
		//保存订单条目
		List<OrderitemCustom> orderitemCustoms = ordersCustom.getOrderitemCustoms();
		for (OrderitemCustom orderitemCustom : orderitemCustoms) {
			orderitemCustom.setOrdersCustom(ordersCustom);
			orderitemMapperCustom.save(orderitemCustom);
		}
	}

	//我的订单
	@Override
	public PageBean<OrdersCustom> myOrder(Integer uid, Integer page)
			throws Exception {
		PageBean<OrdersCustom> pageBean = new PageBean<OrdersCustom>();
		pageBean.setPage(page);
		int pageSize = PageConstants.ORDER_PAGE_SIZE;
		pageBean.setPageSize(pageSize);
		// 设置总记录数
		int totalRecord = 0;
		totalRecord = ordersMapperCustom.findTotalRecordByUid(uid);
		pageBean.setTotalRecord(totalRecord);
		// 设置当前页数据
		int startIndex = (page - 1) * pageSize;
		List<OrdersCustom> ordersCustomList = ordersMapperCustom.findByUidPage(uid, startIndex, pageSize);
		pageBean.setBeanList(ordersCustomList);
		//设置总页数
		int tp = totalRecord/pageSize;
		if (totalRecord%pageSize != 0) {
			tp = tp + 1;
		}
		pageBean.setTotalPage(tp);
		return pageBean;
	}

	//通过 oid 查询订单
	@Override
	public OrdersCustom findByOid(Integer oid) throws Exception {
		return ordersMapperCustom.findByOid(oid);
	}

	//修改订单
	@Override
	public void update(OrdersCustom exitOrder) throws Exception {
		ordersMapperCustom.update(exitOrder);
	}

	//查询所有订单（分页）
	@Override
	public PageBean<OrdersCustom> findAll(Integer page) throws Exception {
		PageBean<OrdersCustom> pageBean = new PageBean<OrdersCustom>();
		pageBean.setPage(page);
		int pageSize = PageConstants.ORDER_PAGE_SIZE;
		pageBean.setPageSize(pageSize);
		// 设置总记录数
		int totalRecord = 0;
		totalRecord = ordersMapperCustom.findTotalRecord();
		pageBean.setTotalRecord(totalRecord);
		// 设置当前页数据
		int startIndex = (page - 1) * pageSize;
		List<OrdersCustom> ordersCustomList = ordersMapperCustom.findByPage(startIndex, pageSize);
		pageBean.setBeanList(ordersCustomList);
		//设置总页数
		int tp = totalRecord/pageSize;
		if (totalRecord%pageSize != 0) {
			tp = tp + 1;
		}
		pageBean.setTotalPage(tp);
		return pageBean;
	}

	//加载订单条目
	@Override
	public List<OrderitemCustom> findOIByOid(Integer oid) throws Exception {
		return orderitemMapperCustom.findByOid(oid);
	}
}
