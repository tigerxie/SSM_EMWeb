package cn.tiger.shop.category.vo;

import java.util.List;

import cn.tiger.shop.categorysecond.vo.Categorysecond;

/**
 * 自定义一级分类
 * @author Administrator
 *
 */
@SuppressWarnings("serial")
public class CategoryCustom extends Category {
   
	//注入二级分类
	private List<Categorysecond> categorysecondList;

	public List<Categorysecond> getCategorysecondList() {
		return categorysecondList;
	}

	public void setCategorysecondList(List<Categorysecond> categorysecondList) {
		this.categorysecondList = categorysecondList;
	}
}