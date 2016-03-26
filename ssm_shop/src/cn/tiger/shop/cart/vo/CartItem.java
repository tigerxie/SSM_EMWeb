package cn.tiger.shop.cart.vo;

import java.math.BigDecimal;

import cn.tiger.shop.product.vo.Product;

/**
 * 购物车模块购物条目类
 * @author tiger
 *
 */
public class CartItem {
	
	private Product product;	//商品
	private int count;			//数量
	private double subtotal;	//小计
	
	/*
	 * 计算小计
	 *   >数量*单价
	 */
	public double getSubtotal() {
		/*
		 * 为了不损失精度--使用BigDecimals
		 *   注意：参数必须是String类型
		 */
		BigDecimal bd1 = new BigDecimal(count + "");
		BigDecimal bd2 = new BigDecimal(product.getShopPrice() + "");
		BigDecimal bd3 = bd1.multiply(bd2);
		return bd3.doubleValue();
	}
	
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}

}
