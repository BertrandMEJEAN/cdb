package fr.excilys.cdb.service;

public class Pagination {
	
	private int page;
	private int pageSize;
	private int offSet;
	private int maxPage;
	private String search;
	private String order;
	private String sort;
	
	private Pagination() {
		
	}
	
	public Pagination(int pPageSize, int pPage) {
		this.pageSize = pPageSize;
		this.page = pPage;
		this.maxPage = defineMaxPage();
		this.offSet = defineOffSet();
	}
	
	private int defineMaxPage() {
		
		float count = (getSearch() == null ? ComputerService.getInstance().countComputer() : ComputerService.getInstance().countComputer(getSearch()));
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
	
	public String getSearch() {
		return search;
	}
	
	public void setSearch(String search) {
		this.search = search;
	}
	
	public String getOrder() {
		return order;
	}
	
	public void setOrder(String order) {
		this.order = order;
	}
	
	public String getSort() {
		return this.sort;
	}
	
	public void setSort(String pSort) {
		this.sort = pSort;
	}

	public static class PaginationBuilder{
		
		private int page;
		private int pageSize;
		private int offSet;
		private int maxPage;
		private String search;
		private String order;
		private String sort;
		
		public Pagination build() {
			Pagination page = new Pagination();
			
			page.setPage(this.page);
			page.setPageSize(this.pageSize);
			page.setSearch(this.search);
			page.setSort(this.sort);
			page.setOrder(this.order);
			page.setOffSet(this.offSet);
			page.setMaxPage(this.maxPage);
						
			return page;
		}
		
		public PaginationBuilder setPage(int page) {
			this.page = page;
			return this;
		}
		
		public PaginationBuilder setPageSize(int pageSize) {
			this.pageSize = pageSize;
			return this;
		}
		
		public PaginationBuilder setOffSet() {
			this.offSet = (this.page-1)*this.pageSize;
			return this;
		}
		
		public PaginationBuilder setMaxPage() {
			float count = (this.search == null ? ComputerService.getInstance().countComputer() : ComputerService.getInstance().countComputer(this.search));
			float tmp = (count / this.pageSize);
			this.maxPage = (int) Math.ceil(tmp); 
			
			return this;
		}
		
		public PaginationBuilder setSearch(String search) {
			this.search = search;
			return this;
		}
		
		public PaginationBuilder setOrder(String order) {
			this.order = order;
			return this;
		}
		
		public PaginationBuilder setSort(String sort) {
			this.sort = sort;
			return this;
		}
	}
	
}
