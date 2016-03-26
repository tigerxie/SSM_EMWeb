package cn.tiger.shop.cart.vo;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 购物车模块购物车类
 * @author tiger
 *
 */
public class Cart {
	
	// 条目集合：map集合，便于移除，key：pid，value：CartItem
	private Map<Integer,CartItem> map = new LinkedHashMap<Integer,CartItem>();
	
	// 得到CartItem集合：方便显示
	public Collection<CartItem> getCartItems() {
		return map.values();
	}
	
	//总计
	private double total;
	
	public double getTotal() {
		return total;
	}
	
	//购物车功能
	//1. 将购物条目添加到购物车
	public void addCart(CartItem cartItem) {
		// 判断该类商品是否已存在
		// 存在，数量，和小计累加(小计自动累加),累加总计
		// 不存在，添加新条目，累加总计
		Integer pid = cartItem.getProduct().getPid();
		if (map.containsKey(pid)) {
			CartItem _cartItem = map.get(pid);							// 得到已存在条目
			int count = cartItem.getCount() + _cartItem.getCount();  			// 累加数量
			_cartItem.setCount(count);
		} else {
			map.put(pid, cartItem);
		}
		total += cartItem.getSubtotal();
	}
	
	//2. 删除购物条目
	public void removeCartItem(Integer pid) {
		CartItem cartItem = map.remove(pid);	// 删除条目
		total -= cartItem.getSubtotal();			// 减去删除条目的小计
	}
	
	//3. 清除购物车
	public void clearCart() {
		map.clear();							// 清除购物车
		total = 0;									//总计设置为0
	}
}
