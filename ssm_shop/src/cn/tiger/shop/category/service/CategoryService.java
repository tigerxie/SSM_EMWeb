package cn.tiger.shop.category.service;

import java.util.List;

import cn.tiger.shop.category.vo.Category;
import cn.tiger.shop.category.vo.CategoryCustom;

/**
 * 一级分类业务层接口
 * @author Administrator
 *
 */
public interface CategoryService {

	//查询所有一级分类及其二级分类
	public List<CategoryCustom> findAll()throws Exception;

	//添加一级分类
	public void save(Category category) throws Exception;

	//查询所有一级分类
	public List<Category> findCategorys() throws Exception;

	//删除一级分类
	public void delete(Integer cid) throws Exception;

	//通过 cid 查询一级分类及其二级分类
	public CategoryCustom findByCid(Integer cid) throws Exception;

	//修改一级分类
	public void update(Category category) throws Exception;

	//通过 cid 查询一级分类
	public Category selectByCid(Integer cid) throws Exception;
	
}
