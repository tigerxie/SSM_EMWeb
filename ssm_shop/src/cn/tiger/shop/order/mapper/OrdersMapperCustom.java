package cn.tiger.shop.order.mapper;

import java.util.List;

import cn.tiger.shop.order.vo.OrdersCustom;


public interface OrdersMapperCustom extends OrdersMapper {

	//保存订单
	void save(OrdersCustom ordersCustom) throws Exception;

	//查询记录数
	int findTotalRecordByUid(Integer uid) throws Exception;

	//查询当前页记录
	List<OrdersCustom> findByUidPage(Integer uid, int startIndex, int pageSize) throws Exception;

	//通过 oid 查询订单
	OrdersCustom findByOid(Integer oid) throws Exception;

	//修改订单
	void update(OrdersCustom exitOrder) throws Exception;

	//查询总记录数
	int findTotalRecord() throws Exception;
	
	//查询当前页记录
	List<OrdersCustom> findByPage(int startIndex, int pageSize) throws Exception;
  
}