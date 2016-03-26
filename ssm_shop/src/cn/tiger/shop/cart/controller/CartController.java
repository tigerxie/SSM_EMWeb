package cn.tiger.shop.cart.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tiger.shop.cart.vo.Cart;
import cn.tiger.shop.cart.vo.CartItem;
import cn.tiger.shop.product.service.ProductService;

/**
 * 购物车模块控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private ProductService productService;
	
	
	// 我的购物车
	@RequestMapping("/myCart")
	public String myCart()throws Exception {
		return "cart";
	}
	
	// 删除购物车条目
	@RequestMapping("/removeCartItem")
	public String removeCartItem(HttpSession session, Integer pid)throws Exception {
		// 通过CartItem,调用cart的removeCartItem完成购物车添加操作
		// 购物车存在session中
		Cart cart = getCart(session);
		cart.removeCartItem(pid);
		return "cart";
	}
	
	// 清空购物车
	@RequestMapping("/clearCart")
	public String clearCart(HttpSession session)throws Exception {
		Cart cart = getCart(session);
		cart.clearCart();
		return "cart";
	}
	
	// 将商品添加到购物车:添加购物车
	@RequestMapping("/addCart")
	public String addCart(HttpSession session, Integer count, Integer pid)throws Exception {
		// 创建CartItem
		CartItem cartItem = new CartItem();
		cartItem.setCount(count);
		cartItem.setProduct(productService.findByPid(pid));
		// 通过CartItem,调用cart的addCart完成购物车添加操作
		// 购物车存在session中
		Cart cart = getCart(session);
		cart.addCart(cartItem);
		return "cart";
	}
	
	// 从session中获取购物车
	private Cart getCart(HttpSession session) {
		Cart cart = (Cart) session.getAttribute("cart");
		if (cart == null) {
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		return cart;
	}
	
}
