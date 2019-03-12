package fr.excilys.cdb.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.excilys.cdb.configuration.SpringConfig;
import fr.excilys.cdb.dto.ComputerDto;
import fr.excilys.cdb.mapper.ComputerMapper;
import fr.excilys.cdb.model.Computer;
import fr.excilys.cdb.service.ComputerService;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet("")
public class DashboardServlet extends HttpServlet {
	
	private ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
	private static final long serialVersionUID = 1L;
    
	private ComputerService computerService = ctx.getBean(ComputerService.class);
	private ComputerMapper computerMapper = ctx.getBean(ComputerMapper.class);
	
	private int pageNbr = 1;
	private int pageSize = 10;
	private int pageMax;
	private int allComputer;
	private String search;
	private String order;
	private String sort;
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DashboardServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		setPage(request.getParameter("pageNbr"), request.getParameter("pageSize"), request.getParameter("search"), request.getParameter("order"), request.getParameter("sort"));
		
		Collection<Computer> page = this.computerService.getPageComputer(getPageSize(), getPageNbr(), getSearch(), getOrder(), getSort());		
		Collection<ComputerDto> dtoList = new ArrayList<>();
		
		for(Computer element : page) {
			dtoList.add(this.computerMapper.objectToDto(element));
		}
		
		request.setAttribute("allComputer", getAllComputer());
		request.setAttribute("pageMax", getPageMax());
		request.setAttribute("pageSize", getPageSize());
		request.setAttribute("pageNbr", getPageNbr());
		request.setAttribute("search", getSearch());
		request.setAttribute("computers", dtoList);
	
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}
	
	private void setPage(String pPageNbr, String pPageSize, String pSearch, String pOrder, String pSort) {
		
		int viewPageNbr = (pPageNbr == null ? getPageNbr() : Integer.valueOf(pPageNbr));
		int viewPageSize = (pPageSize == null ? getPageSize() : Integer.valueOf(pPageSize));

		if(pSearch != null) {
			setSearch(pSearch);
			setAllComputer(this.computerService.countComputer(getSearch()));
		}else {
			setAllComputer(this.computerService.countComputer());
		}
		
		setPageMax(defineMaxPage(getAllComputer()));
		
		if(pOrder != null) {
			setOrder(pOrder);
			setSort(pSort);
		}
		
		if( viewPageSize != getPageSize()){
			setPageSize(viewPageSize);
		}else if(viewPageNbr != getPageNbr()){
			setPageNbr(viewPageNbr);
		}
	}
	
	private int defineMaxPage(float count) {
		
		float tmp = (count / getPageSize());
		int newMaxPage = (int) Math.ceil(tmp);
			
		return newMaxPage;
	}

	public int getPageNbr() {
		return pageNbr;
	}

	public void setPageNbr(int pageNbr) {
		this.pageNbr = pageNbr;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageMax() {
		return pageMax;
	}

	public void setPageMax(int pageMax) {
		this.pageMax = pageMax;
	}

	public int getAllComputer() {
		return allComputer;
	}

	public void setAllComputer(int allComputer) {
		this.allComputer = allComputer;
	}
	
	public String getSearch() {
		return search;
	}
	
	public void setSearch(String pSearch) {
		this.search = pSearch;
	}
	
	public String getOrder() {
		return this.order;
	}
	
	public void setOrder(String pOrder) {
		this.order = pOrder;
	}
	
	public String getSort() {
		return this.sort;
	}

	public void setSort(String pSort) {
		this.sort = pSort;
	}
}
