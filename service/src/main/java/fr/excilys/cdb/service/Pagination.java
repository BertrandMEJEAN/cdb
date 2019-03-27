package fr.excilys.cdb.service;

import org.springframework.stereotype.Service;

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
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + maxPage;
		result = prime * result + offSet;
		result = prime * result + ((order == null) ? 0 : order.hashCode());
		result = prime * result + page;
		result = prime * result + pageSize;
		result = prime * result + ((search == null) ? 0 : search.hashCode());
		result = prime * result + ((sort == null) ? 0 : sort.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pagination other = (Pagination) obj;
		if (maxPage != other.maxPage)
			return false;
		if (offSet != other.offSet)
			return false;
		if (order == null) {
			if (other.order != null)
				return false;
		} else if (!order.equals(other.order))
			return false;
		if (page != other.page)
			return false;
		if (pageSize != other.pageSize)
			return false;
		if (search == null) {
			if (other.search != null)
				return false;
		} else if (!search.equals(other.search))
			return false;
		if (sort == null) {
			if (other.sort != null)
				return false;
		} else if (!sort.equals(other.sort))
			return false;
		return true;
	}



	@Service
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
		
		public PaginationBuilder setOffSet(int offSet) {
			this.offSet = offSet;
			return this;
		}
		
		public PaginationBuilder setMaxPage(int maxPage) { 
			this.maxPage = maxPage;
			
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
