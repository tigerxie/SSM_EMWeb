package cn.tiger.shop.category.mapper;

import java.util.List;

import cn.tiger.shop.category.vo.Category;
import cn.tiger.shop.category.vo.CategoryCustom;

/**
 * 一级分类持久层
 * @author Administrator
 *
 */
public interface CategoryMapperCustom extends CategoryMapper {

	//查询所有一级分类及其二级分类
	public List<CategoryCustom> findAll() throws Exception;

	//查询所有一级分类
	public List<Category> findCategorys() throws Exception;

	//通过 cid 查询一级分类及其二级分类
	public CategoryCustom findByCid(Integer cid) throws Exception;
	
}