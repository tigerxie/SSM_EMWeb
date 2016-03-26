package cn.tiger.shop.order.mapper;

import java.util.List;

import cn.tiger.shop.order.vo.OrderitemCustom;

public interface OrderitemMapperCustom {

	//保存订单条目
	void save(OrderitemCustom orderitemCustom) throws Exception;

	//通过 oid 加载订单条目
	List<OrderitemCustom> findByOid(Integer oid) throws Exception;
	
}