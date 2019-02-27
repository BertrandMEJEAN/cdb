package fr.excilys.cdb.service;

public class Pagination {
	
	private int page;
	private int pageSize;
	private int offSet;
	private int maxPage;
	
	public Pagination(int pPageSize, int pPage) {
		this.pageSize = pPageSize;
		this.page = pPage;
		this.maxPage = defineMaxPage();
		this.offSet = defineOffSet();
	}
	
	private int defineMaxPage() {
		
		float count = ComputerService.getInstance().countComputer();
		float tmp = (count / getPageSize());
		int newMaxPage = (int) Math.ceil(tmp);
			
		return newMaxPage;
	}
	
	private int defineOffSet() {
		
		int newOffSet = (getPage()-1)*getPageSize();		
		
		return newOffSet;
	}

	public int getOffSet() {
		return offSet;
	}

	public void setOffSet(int offSet) {
		this.offSet = offSet;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getMaxPage() {
		return maxPage;
	}

	public void setMaxPage(int maxPage) {
		this.maxPage = maxPage;
	}

	
}
