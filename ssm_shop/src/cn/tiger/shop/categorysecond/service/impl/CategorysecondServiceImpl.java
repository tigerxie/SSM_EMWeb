package cn.tiger.shop.categorysecond.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.tiger.shop.categorysecond.mapper.CategorysecondMapperCustom;
import cn.tiger.shop.categorysecond.service.CategorysecondService;
import cn.tiger.shop.categorysecond.vo.Categorysecond;
import cn.tiger.shop.utils.PageBean;
import cn.tiger.shop.utils.PageConstants;

public class CategorysecondServiceImpl implements CategorysecondService {

	@Autowired
	private CategorysecondMapperCustom categorysecondMapperCustom;

	//根据 cid 查询二级分类数量
	@Override
	public int countByCid(Integer cid) throws Exception {
		return categorysecondMapperCustom.countByCid(cid);
	}
	
	//查询封装二级分类的PageBean对象
	@Override
	public PageBean<Categorysecond> findAll(Integer page) throws Exception {
		PageBean<Categorysecond> pageBean = new PageBean<Categorysecond>();
		pageBean.setPage(page);
		int pageSize = PageConstants.PRODUCT_PAGE_SIZE;
		pageBean.setPageSize(pageSize);
		// 设置总记录数
		int totalRecord = 0;
		totalRecord = categorysecondMapperCustom.findTotalRecord();
		pageBean.setTotalRecord(totalRecord);
		// 设置页面记录
		int startIndex = (page - 1) * pageSize;
		List<Categorysecond> beanList = categorysecondMapperCustom.findAll(startIndex, pageSize);
		pageBean.setBeanList(beanList);
		//设置总页数
		int tp = totalRecord/pageSize;
		if (totalRecord%pageSize != 0) {
			tp = tp + 1;
		}
		pageBean.setTotalPage(tp);
		return pageBean;
	}

	//添加二级分类
	@Override
	public void save(Categorysecond categorysecond) throws Exception {
		categorysecondMapperCustom.insert(categorysecond);
	}

	//删除二级分类
	@Override
	public void delete(Integer csid) throws Exception {
		categorysecondMapperCustom.deleteByPrimaryKey(csid);
	}

	//查询二级分类
	@Override
	public Categorysecond findByCsid(Integer csid) throws Exception {
		return categorysecondMapperCustom.selectByPrimaryKey(csid);
	}

	//修改二级分类
	@Override
	public void update(Categorysecond categorysecond) throws Exception {
		categorysecondMapperCustom.updateByPrimaryKey(categorysecond);
	}

	//查询所有二级分类
	@Override
	public List<Categorysecond> findAllCS() throws Exception {
		return categorysecondMapperCustom.findAllCS();
	}
	
}
