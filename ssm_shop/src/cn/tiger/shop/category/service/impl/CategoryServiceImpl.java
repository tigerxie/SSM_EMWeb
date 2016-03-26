package cn.tiger.shop.category.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.tiger.shop.category.mapper.CategoryMapperCustom;
import cn.tiger.shop.category.service.CategoryService;
import cn.tiger.shop.category.vo.Category;
import cn.tiger.shop.category.vo.CategoryCustom;

/**
 * 一级分类业务层对象
 * @author Administrator
 *
 */
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryMapperCustom categoryMapperCustom;

	//查询所有一级分类及其二级分类
	@Override
	public List<CategoryCustom> findAll() throws Exception {
		return categoryMapperCustom.findAll();
	}

	//添加一级分类
	@Override
	public void save(Category category) throws Exception {
		categoryMapperCustom.insert(category);
	}

	//查询所有一级分类
	@Override
	public List<Category> findCategorys() throws Exception {
		return categoryMapperCustom.findCategorys();
	}

	//删除一级分类
	@Override
	public void delete(Integer cid) throws Exception {
		categoryMapperCustom.deleteByPrimaryKey(cid);
	}

	//通过 cid 查询一级分类及其二级分类
	@Override
	public CategoryCustom findByCid(Integer cid) throws Exception {
		return categoryMapperCustom.findByCid(cid);
	}

	//修改一级分类
	@Override
	public void update(Category category) throws Exception {
		categoryMapperCustom.updateByPrimaryKey(category);
	}

	//通过 cid 查询一级分类
	@Override
	public Category selectByCid(Integer cid) throws Exception {
		return categoryMapperCustom.selectByPrimaryKey(cid);
	}
	
}
