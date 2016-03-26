package cn.tiger.shop.category.admincontroller;

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

@Controller
@RequestMapping("/adminCategory")
public class AdminCategoryController {

	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CategorysecondService categorysecondService;
	
	/**
	 * 删除一级分类
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/delete")
	public String delete(Integer cid, HttpServletRequest request) throws Exception {
		/*
		 * 判断是否存在二级分类，存在则报错
		 */
		int csCount = categorysecondService.countByCid(cid);
		if (csCount > 1) {
			request.setAttribute("error", "该分类下存在二级分类，不能删除！");
			return "admin/category/list";
		}
		categoryService.delete(cid);
		return "redirect:/adminCategory/findAll.action";
	}
	/**
	 * 查询所有一级分类
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/findAll")
	public String findAll(HttpSession session) throws Exception {
		List<Category> cList = categoryService.findCategorys();
		session.setAttribute("cList", cList);
		return "admin/category/list";
	}
	@RequestMapping("/add")
	public String add() throws Exception {
		return "admin/category/add";
	}
	
	/**
	 * 添加一级分类
	 * @throws Exception 
	 */
	@RequestMapping("/save")
	public String save(Category category) throws Exception {
		categoryService.save(category);
		return "redirect:/adminCategory/findAll.action";
	}
	
	/**
	 * 预编辑一级分类
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/edit")
	public String edit(Integer cid, HttpServletRequest request) throws Exception {
		Category category = categoryService.selectByCid(cid);
		request.setAttribute("category", category);
		return "admin/category/edit";
	}
	/**
	 * 修改一级分类（分类名称）
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/update")
	public String update(Category category) throws Exception {
		categoryService.update(category);
		return "redirect:/adminCategory/findAll.action";
	}
}
