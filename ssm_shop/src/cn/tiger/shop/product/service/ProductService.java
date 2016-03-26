package cn.tiger.shop.product.service;

import java.util.List;

import cn.tiger.shop.product.vo.Product;
import cn.tiger.shop.utils.PageBean;

/**
 * 商品模块业务层
 * @author Administrator
 *
 */
public interface ProductService {

	//查询热门商品
	public List<Product> findHot()throws Exception;
	
	//查询最新商品
	public List<Product> findNew()throws Exception;

	//商品详细
	public Product findByPid(Integer pid)throws Exception;

	//一级分类查询商品(分页)
	public PageBean<Product> findByCidPage(Integer cid, Integer page)throws Exception;

	//二级分类查询商品(分页)
	public PageBean<Product> findByCsidPage(Integer csid, Integer page)throws Exception;

	//查询商品（分页）
	public PageBean<Product> findByPage(Integer page) throws Exception;

	//添加商品
	public void save(Product product) throws Exception;

	//删除商品
	public void delete(Integer pid) throws Exception;

	//修改商品
	public void update(Product product) throws Exception;
}
