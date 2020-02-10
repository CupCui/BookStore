package pers.garre.bookstore.bean;

import java.util.List;

public class Page<T> {
	private int pageNum;// 当前的页码
	private int pageSize;// 一页多少条
	private int totalPage;// 总页数 
	private int totalSize;// 总条数

	private List<T> list;

	public Page() {
	}


	public Page(int pageNum, int pageSize, int totalSize) {
		super();
		this.pageNum = pageNum;
		this.pageSize = pageSize;
		this.totalSize = totalSize;
		this.totalPage = totalSize % pageSize == 0 ? totalSize / pageSize : totalSize / pageSize + 1;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		return "Page [pageNum=" + pageNum + ", pageSize=" + pageSize + ", totalPage=" + totalPage + ", totalSize="
				+ totalSize + ", list=" + list + "]";
	}

}
