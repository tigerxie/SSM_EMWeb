package cn.tiger.shop.product.admincontroller;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;


import cn.tiger.shop.categorysecond.service.CategorysecondService;
import cn.tiger.shop.categorysecond.vo.Categorysecond;
import cn.tiger.shop.product.service.ProductService;
import cn.tiger.shop.product.vo.Product;
import cn.tiger.shop.utils.PageBean;

@Controller
@RequestMapping("/adminProduct")
public class AdminProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CategorysecondService categorysecondService;
	
	// 带分页查询所有商品
	@RequestMapping("/findAll")
	public String findAll(Integer page, HttpServletRequest request) throws Exception {
		if (page == null) {
			page = 1;
		}
		PageBean<Product> pageBean = productService.findByPage(page);
		request.setAttribute("pageBean", pageBean);
		return "admin/product/list";
	}
	// 预添加商品
	@RequestMapping("/addProduct")
	public String addProduct(HttpServletRequest request) throws Exception {
		// 得到所有二级分类
		List<Categorysecond> csList = categorysecondService.findAllCS();
		request.setAttribute("csList", csList);
		return "admin/product/add";
	}
	
	// 添加商品
	@RequestMapping("/save")
	public String save(Product product,MultipartFile upload, HttpSession session) throws Exception {
		product.setPdate(new Date());
		if (upload != null) {
			// 原始名称
			String originalFilename = upload.getOriginalFilename();
			// 图片上传
			if (originalFilename != null && originalFilename.length() > 0) {
				
				// 存储图片的物理路径
				//String pic_path = "F:\\develop\\upload\\temp\\";
				String realPath = session.getServletContext().getRealPath("/image");
				
				// 新图片的名称
				String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
				
				// 新图片
				File newFile = new File(realPath + "//" + newFileName);
				
				// 将内存中的数据写入磁盘
				upload.transferTo(newFile);
				
				//设置图片
				product.setImage("image/" + newFileName);
				
			}
		}
		/*
		 *  调用service保存商品
		 */
		productService.save(product);
		return "redirect:/adminProduct/findAll.action";
	}
	/**
	 * 删除商品
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/delete")
	public String delete(Integer pid, HttpSession session) throws Exception {
		/*
		 * 得到图片路径，创建图片对象，删除图片
		 */
		Product product = productService.findByPid(pid);
//		String pic_path = "F:\\develop\\upload\\temp\\";
		String realPath = session.getServletContext().getRealPath("/");
		File descFile = new File(realPath + "//" + product.getImage());
		descFile.delete();
		/*
		 * 通过pid调用service方法从数据库中删除
		 */
		productService.delete(pid);
		return "redirect:/adminProduct/findAll.action";
	}
	/**
	 * 预修改
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/edit")
	public String edit(Integer pid, HttpServletRequest request) throws Exception {
		/*
		 * 通过pid得到商品,保存到模型驱动
		 */
		Product product = productService.findByPid(pid);
		request.setAttribute("product", product);
		/*
		 * 得到二级分类，存到值栈
		 */
		List<Categorysecond> csList = categorysecondService.findAllCS();
		request.setAttribute("csList", csList);
		return "admin/product/edit";
	}
	/**
	 * 修改商品
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/update")
	public String update(Product product, MultipartFile upload, HttpSession session) throws Exception {
		if (upload != null) {
			/*
			 * 删除旧文件
			 */
			String delPath = session.getServletContext().getRealPath(
					"/" + product.getImage());
			File file = new File(delPath);
			file.delete();
			
			/*
			 * 新建文件
			 */
			// 原始名称
			String originalFilename = upload.getOriginalFilename();
			// 图片上传
			if (originalFilename != null && originalFilename.length() > 0) {
				product.setPdate(new Date());
				// 存储图片的物理路径
				//String pic_path = "F:\\develop\\upload\\temp\\";
				String realPath = session.getServletContext().getRealPath("/image");
				
				// 新图片的名称
				String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
				
				// 新图片
				File newFile = new File(realPath + "//" + newFileName);
				
				// 将内存中的数据写入磁盘
				upload.transferTo(newFile);
				
				//设置图片
				product.setImage("image/" + newFileName);
				/*
				 *  调用service修改商品
				 */
				productService.update(product);
			}
		}
		return "redirect:/adminProduct/findAll.action";
	}
}
