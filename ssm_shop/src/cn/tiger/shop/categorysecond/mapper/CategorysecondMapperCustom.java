package cn.tiger.shop.categorysecond.mapper;

import java.util.List;

import cn.tiger.shop.categorysecond.vo.Categorysecond;

public interface CategorysecondMapperCustom extends CategorysecondMapper {

	//根据 cid 查询二级分类数量
	int countByCid(Integer cid) throws Exception;

	//查询总记录数
	int findTotalRecord() throws Exception;

	//查询总记录
	List<Categorysecond> findAll(int startIndex, int pageSize) throws Exception;

	//查询所有二级分类
	List<Categorysecond> findAllCS() throws Exception;
}