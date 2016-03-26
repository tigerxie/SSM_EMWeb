package cn.tiger.shop.utils;

import java.util.List;

/**
 * 分页工具类
 * @author tiger
 * @param <T>
 *
 */
public class PageBean<T> {

	private int page;			//当前页数
	private int totalRecord;	//总记录数
	private int pageSize;		//每页记录数
	private int totalPage;		//总页数
	private List<T> beanList;	//当前页数据
	
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalPage() {
		return totalPage;
		/*int tp = totalRecord/pageSize;
		return totalRecord%pageSize == 0 ? tp : tp + 1;*/
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<T> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}
	public PageBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PageBean(int page, int totalRecord, int pageSize, List<T> beanList) {
		super();
		this.page = page;
		this.totalRecord = totalRecord;
		this.pageSize = pageSize;
		this.beanList = beanList;
	}
}
