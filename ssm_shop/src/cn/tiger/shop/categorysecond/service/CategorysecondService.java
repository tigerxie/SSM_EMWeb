package cn.tiger.shop.categorysecond.service;

import java.util.List;

import cn.tiger.shop.categorysecond.vo.Categorysecond;
import cn.tiger.shop.utils.PageBean;


public interface CategorysecondService {
	
	//根据 cid 查询二级分类数量
	int countByCid(Integer cid) throws Exception;

	//查询封装二级分类的PageBean对象
	PageBean<Categorysecond> findAll(Integer page) throws Exception;

	//添加二级分类
	void save(Categorysecond categorysecond) throws Exception;

	//删除二级分类
	void delete(Integer csid) throws Exception;

	//查询二级分类
	Categorysecond findByCsid(Integer csid) throws Exception;

	//修改二级分类
	void update(Categorysecond categorysecond) throws Exception;

	//查询所有二级分类
	List<Categorysecond> findAllCS() throws Exception;

}
