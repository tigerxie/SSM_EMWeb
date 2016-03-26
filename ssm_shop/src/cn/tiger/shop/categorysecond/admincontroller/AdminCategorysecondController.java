package cn.tiger.shop.categorysecond.admincontroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


import cn.tiger.shop.category.service.CategoryService;
import cn.tiger.shop.category.vo.Category;
import cn.tiger.shop.category.vo.CategoryCustom;
import cn.tiger.shop.categorysecond.service.CategorysecondService;
import cn.tiger.shop.categorysecond.vo.Categorysecond;
import cn.tiger.shop.product.service.ProductService;
import cn.tiger.shop.product.vo.Product;
import cn.tiger.shop.utils.PageBean;

@Controller
@RequestMapping("/adminCategorysecond")
public class AdminCategorysecondController {

	@Autowired
	private CategorysecondService categorysecondService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	/**
	 * 按分页查询所有二级分类
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/findAll")
	public String findAll(Integer page, HttpServletRequest request) throws Exception {
		if (page == null) {
			page = 1;
		}
		/*
		 * 1. 调用service方法查询封装二级分类的PageBean对象
		 */
		PageBean<Categorysecond> pageBean = categorysecondService.findAll(page);
		/*
		 * 2. 将PageBean保存到值栈
		 */
		request.getSession().setAttribute("csPageBean", pageBean);
		return "admin/categorysecond/list";
	}
	/**
	 * 预添加
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/preAdd")
	public String preAdd(HttpServletRequest request) throws Exception {
		List<Category> cList = categoryService.findCategorys();
		request.setAttribute("cList", cList);
		return "admin/categorysecond/add";
	}
	/**
	 * 添加二级分类
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	public String save(Categorysecond categorysecond) throws Exception {
		categorysecondService.save(categorysecond);
		return "redirect:/adminCategorysecond/findAll.action";
	}
	/**
	 * 删除二级分类
	 * @throws Exception 
	 */
	@RequestMapping("/delete")
	public String delete(Integer csid, HttpServletRequest request) throws Exception {
		/*
		 * 判断二级分类下是否有商品
		 */
		PageBean<Product> pb = productService.findByCsidPage(csid, 1);
		if (pb.getBeanList().size() >= 1) {
			request.setAttribute("error", "该分类下存在二级分类，不能删除！");
			return "admin/categorysecond/list";
		}
		/*
		 * 删除二级分类
		 */
		categorysecondService.delete(csid);
		return "redirect:/adminCategorysecond/findAll.action";
	}
	/**
	 * 预修改二级分类
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/edit")
	public String edit(HttpServletRequest request, Integer csid) throws Exception {
		// 到的所有一级分类
		List<Category> cList = categoryService.findCategorys();
		request.setAttribute("cList", cList);
		// 将此二级分类存入 request
		Categorysecond categorysecond = categorysecondService.findByCsid(csid);
		request.setAttribute("categorysecond", categorysecond);
		return "admin/categorysecond/edit";
	}
	/**
	 * 修改二级分类
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/update")
	public String update(Categorysecond categorysecond) throws Exception {
		categorysecondService.update(categorysecond);
		return "redirect:/adminCategorysecond/findAll.action";
	}
}
