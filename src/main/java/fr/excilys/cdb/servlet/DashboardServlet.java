package fr.excilys.cdb.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.excilys.cdb.dto.ComputerDto;
import fr.excilys.cdb.mapper.ComputerMapper;
import fr.excilys.cdb.model.Computer;
import fr.excilys.cdb.service.ComputerService;

/**
 * Servlet implementation class DashboardServlet
 */
@WebServlet("")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	private int pageNbr = 1;
	private int pageSize = 10;
	private int pageMax;
	private int allComputer;
	private String search;
	
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
		
		setPage(request.getParameter("pageNbr"), request.getParameter("pageSize"), request.getParameter("search"));
		
		Collection<Computer> page = (getSearch() == null ? ComputerService.getInstance().getPageComputer(getPageSize(),getPageNbr()) : ComputerService.getInstance().getPageComputer(getPageSize(), getPageNbr(), getSearch()));		
		Collection<ComputerDto> dtoList = new ArrayList<>();
		
		for(Computer element : page) {
			dtoList.add(ComputerMapper.getInstance().objectToDto(element));
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
	
	private void setPage(String pPageNbr, String pPageSize, String pSearch) {
		
		int viewPageNbr = (pPageNbr == null ? getPageNbr() : Integer.valueOf(pPageNbr));
		int viewPageSize = (pPageSize == null ? getPageSize() : Integer.valueOf(pPageSize));

		if(pSearch != null) {
			setSearch(pSearch);
			setAllComputer(ComputerService.getInstance().countComputer(getSearch()));
		}else {
			setAllComputer(ComputerService.getInstance().countComputer());
		}
		
		setPageMax(defineMaxPage(getAllComputer()));
		
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

}
