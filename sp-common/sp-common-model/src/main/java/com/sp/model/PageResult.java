package com.sp.model;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sp.core.constants.SystemConstants;
import com.sp.model.domain.SysBusiness;

import java.util.List;

/**
 * 分页
 *
 * @author 罗汉
 * @date 2023/04/02
 */// T表示泛型，因为将来分页模型对应的数据类型是不确定的
public class PageResult<T> {
	private PageResult<T> pageResult;
	public PageResult(){};


	public static final Long PAGE_SIZE = SystemConstants.PAGE_SIZE;

	// 表示显示当前页[显示第几页]
	// 前端页面来的
	private Long pageNo;
	// 表示每页显示几条记录
	private Long pageSize = PAGE_SIZE;
	// 表示共有多少页, 他是通过totalRow和pageSize计算得到
	private Long pageTotalCount;
	// 表示的是共有多少条记录
	// 计算得到pageTotalCount
	// 是可以同数据库DB来的->DAO
	private Long totalRow;
	// 表示当前页，要显示的数据
	// 从DB来的->DAO
	private List<T> items;

	/**
	 * 上一页
	 */
	private boolean hasPrevious;
	/**
	 * 下一页
	 */
	private boolean hasNext;

	public boolean isHasPrevious() {
		return hasPrevious;
	}

	public void setHasPrevious(boolean hasPrevious) {
		this.hasPrevious = hasPrevious;
	}

	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public Long getPageNo() {
		return pageNo;
	}

	public void setPageNo(Long pageNo) {
		this.pageNo = pageNo;
	}

	public Long getPageSize() {
		return pageSize;
	}

	public void setPageSize(Long pageSize) {
		this.pageSize = pageSize;
	}

	public Long getPageTotalCount() {
		return pageTotalCount;
	}

	public void setPageTotalCount(Long pageTotalCount) {
		this.pageTotalCount = pageTotalCount;
	}

	public Long getTotalRow() {
		return totalRow;
	}

	public void setTotalRow(Long totalRow) {
		this.totalRow = totalRow;
	}

	public List<T> getItems() {
		return items;
	}

	public void setItems(List<T> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "PageResult{" +
				"pageNo=" + pageNo +
				", pageSize=" + pageSize +
				", pageTotalCount=" + pageTotalCount +
				", totalRow=" + totalRow +
				", items=" + items +
				'}';
	}

	public boolean isEmpty() {
		return pageNo == null || pageNo == 0 || items == null || items.size() == 0;
    }
}
