package cn.tiger.shop.order.vo;

import cn.tiger.shop.product.vo.Product;

/**
 * 自定义订单条目类
 * @author Administrator
 *
 */
public class OrderitemCustom extends Orderitem {

	//注入商品类
	private Product product;	
	//注入订单模块类
	private OrdersCustom ordersCustom;
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public OrdersCustom getOrdersCustom() {
		return ordersCustom;
	}
	public void setOrdersCustom(OrdersCustom ordersCustom) {
		this.ordersCustom = ordersCustom;
	}

}