package cn.tiger.shop.product.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.tiger.shop.product.service.ProductService;
import cn.tiger.shop.product.vo.Product;
import cn.tiger.shop.utils.PageBean;

/**
 * 商品模块控制器
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	//二级分类查询商品(分页)
	@RequestMapping("/findByCsidPage")
	public String findByCsidPage(HttpSession session, HttpServletRequest request, Integer csid, Integer page)throws Exception {
		/*
		 * 1. 得到一级分类及其关联的二级分类，显示在页面左侧（session中已存入categoryList）
		 */
		/*
		 * 2. 通过csid和page调用service得到分类下的所有商品PageBean<Product>
		 */
		PageBean<Product> pageBean = productService.findByCsidPage(csid, page);
		/*
		 * 3. 将PageBean<Product>保存到 session 中
		 */
		session.setAttribute("pageBean", pageBean);
		/*
		 * 2. 将 csid 传回页面
		 */
		request.setAttribute("csid", csid);
		
		return "productList";
	}
	
	//一级分类查询商品(分页)
	@RequestMapping("/findByCidPage")
	public String findByCidPage(HttpSession session, HttpServletRequest request, Integer cid, Integer page)throws Exception {
		/*
		 * 1. 得到一级分类及其关联的二级分类，显示在页面左侧（session中已存入categoryList）
		 */
		/*
		 * 2. 通过cid和page调用service得到分类下的所有商品PageBean<Product>
		 */
		PageBean<Product> pageBean = productService.findByCidPage(cid, page);
		/*
		 * 3. 将PageBean<Product>保存到 session 中
		 */
		session.setAttribute("pageBean", pageBean);
		/*
		 * 2. 将 cid 传回页面
		 */
		request.setAttribute("cid", cid);
		
		return "productList";
	}
	//商品详细
	@RequestMapping("/findByPid")
	public String findByPid(HttpServletRequest request, Integer pid)throws Exception {
		
		Product product = productService.findByPid(pid);
		request.setAttribute("product", product);
		
		return "product";
	}
	
	
}
