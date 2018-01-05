package com.exercise.domain.vo;

import java.util.List;

public class PageBean<T> {
	private int currentPage;//当前页面
	private int productCount;//页面显示条数
	private int productTotalCount;//记录总数
	private int totalPage;//总页数
	private List<T> PageData;//
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	public int getProductTotalCount() {
		return productTotalCount;
	}
	public void setProductTotalCount(int productTotalCount) {
		this.productTotalCount = productTotalCount;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public List<T> getPageData() {
		return PageData;
	}
	public void setPageData(List<T> pageData) {
		PageData = pageData;
	}
	public PageBean() {
		super();
	}
	
}
