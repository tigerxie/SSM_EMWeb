package cn.tiger.shop.product.mapper;

import java.util.List;

import cn.tiger.shop.product.vo.Product;

/**
 * 二级分类持久层包装类
 * @author Administrator
 *
 */
public interface ProductMapperCustom extends ProductMapper {
   
	//查询热门商品
	public List<Product> findHot()throws Exception;
	
	//查询最新商品
	public List<Product> findNew()throws Exception;

	//通过 cid 查询总记录数
	public int findTotalRecordByCid(Integer cid)throws Exception;

	//通过 cid 设置当前页数据
	public List<Product> findByCidPage(Integer cid,Integer startIndex, Integer pageSize)throws Exception;

	//通过 csid 查询总记录数
	public int findTotalRecordByCsid(Integer csid) throws Exception;

	//通过 csid 设置当前页数据
	public List<Product> findByCsidPage(Integer csid, int startIndex,int pageSize)throws Exception;

	//通过查询总记录数
	public int findTotalRecord() throws Exception;

	//通过设置当前页数据
	public List<Product> findByPage(int startIndex, int pageSize) throws Exception;

}