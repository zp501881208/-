package com.magict.magic.entity.dto;

/**
 * （简单的）分页对象
 * Title: Page
 * Description: 
 * @author yzh
 * @date 2017年12月15日下午3:49:25
 */
public class Page {

	/**
	 * 每页大小
	 */
	public static Integer PAGE_SIZE = 10;
	
	/**
	 * 当前页数
	 */
	private Integer currentPage;
	/**
	 * 总页数
	 */
	private Integer totalPage;

	public Integer getCurrentPage() {
		return this.currentPage == null ? 1 : this.currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}

	public Integer getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	
}
