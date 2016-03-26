package cn.tiger.shop.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.tiger.shop.product.mapper.ProductMapperCustom;
import cn.tiger.shop.product.service.ProductService;
import cn.tiger.shop.product.vo.Product;
import cn.tiger.shop.utils.PageBean;
import cn.tiger.shop.utils.PageConstants;

/**
 * 商品业务层对象
 * @author Administrator
 *
 */
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductMapperCustom productMapperCustom;

	//查询热门商品
	@Override
	public List<Product> findHot() throws Exception {
		return productMapperCustom.findHot();
	}
	
	//查询热门商品
	@Override
	public List<Product> findNew() throws Exception {
		return productMapperCustom.findNew();
	}

	//商品详细
	@Override
	public Product findByPid(Integer pid) throws Exception {
		return productMapperCustom.selectByPrimaryKey(pid);
	}

	//一级分类查询商品(分页)
	@Override
	public PageBean<Product> findByCidPage(Integer cid, Integer page)
			throws Exception {
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPage(page);
		int pageSize = PageConstants.PRODUCT_PAGE_SIZE;
		pageBean.setPageSize(pageSize);
		// 设置总记录数
		int totalRecord = 0;
		totalRecord = productMapperCustom.findTotalRecordByCid(cid);
		pageBean.setTotalRecord(totalRecord);
		// 设置当前页数据
		int startIndex = (page - 1) * pageSize;
		List<Product> productList = productMapperCustom.findByCidPage(cid, startIndex, pageSize);
		pageBean.setBeanList(productList);
		//设置总页数
		int tp = totalRecord/pageSize;
		if (totalRecord%pageSize != 0) {
			tp = tp + 1;
		}
		pageBean.setTotalPage(tp);
		return pageBean;
	}

	//二级分类查询商品(分页)
	@Override
	public PageBean<Product> findByCsidPage(Integer csid, Integer page)
			throws Exception {
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPage(page);
		int pageSize = PageConstants.PRODUCT_PAGE_SIZE;
		pageBean.setPageSize(pageSize);
		// 设置总记录数
		int totalRecord = 0;
		totalRecord = productMapperCustom.findTotalRecordByCsid(csid);
		pageBean.setTotalRecord(totalRecord);
		// 设置当前页数据
		int startIndex = (page - 1) * pageSize;
		List<Product> productList = productMapperCustom.findByCsidPage(csid, startIndex, pageSize);
		pageBean.setBeanList(productList);
		//设置总页数
		int tp = totalRecord/pageSize;
		if (totalRecord%pageSize != 0) {
			tp = tp + 1;
		}
		pageBean.setTotalPage(tp);
		return pageBean;
	}

	//查询商品（分页）
	@Override
	public PageBean<Product> findByPage(Integer page) throws Exception {
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPage(page);
		int pageSize = PageConstants.PRODUCT_PAGE_SIZE;
		pageBean.setPageSize(pageSize);
		// 设置总记录数
		int totalRecord = 0;
		totalRecord = productMapperCustom.findTotalRecord();
		pageBean.setTotalRecord(totalRecord);
		// 设置当前页数据
		int startIndex = (page - 1) * pageSize;
		List<Product> beanList = productMapperCustom.findByPage(startIndex, pageSize);
		pageBean.setBeanList(beanList);
		//设置总页数
		int tp = totalRecord/pageSize;
		if (totalRecord%pageSize != 0) {
			tp = tp + 1;
		}
		pageBean.setTotalPage(tp);
		return pageBean;
	}

	//添加商品
	@Override
	public void save(Product product) throws Exception {
		productMapperCustom.insert(product);
	}

	//删除商品
	@Override
	public void delete(Integer pid) throws Exception {
		productMapperCustom.deleteByPrimaryKey(pid);
	}

	//修改商品
	@Override
	public void update(Product product) throws Exception {
		productMapperCustom.updateByPrimaryKey(product);
	}
}
