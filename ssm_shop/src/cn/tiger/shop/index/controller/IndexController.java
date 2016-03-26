package cn.tiger.shop.index.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import cn.tiger.shop.category.service.CategoryService;
import cn.tiger.shop.category.vo.Category;
import cn.tiger.shop.category.vo.CategoryCustom;
import cn.tiger.shop.product.service.ProductService;
import cn.tiger.shop.product.vo.Product;

@Controller
public class IndexController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	//跳转到 index 页面
	@RequestMapping("/index")
	public String index(HttpServletRequest request)throws Exception {
		
		//1.查询所有一级分类，保存到 session
		List<CategoryCustom> categoryList =  categoryService.findAll();
		request.getSession().setAttribute("categoryList", categoryList);
		
		//2.查询热门商品
		List<Product> pHotList = productService.findHot();
		request.getSession().setAttribute("pHotList", pHotList);
		
		//3.查询最新商品
		List<Product> pNewList = productService.findNew();
		// 相当于request的setAttribute属性
		request.getSession().setAttribute("pNewList", pNewList);
		
		return "index";
		
	}
	
}
