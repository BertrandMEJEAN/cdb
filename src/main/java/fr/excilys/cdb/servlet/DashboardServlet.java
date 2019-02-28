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
		int viewPageNbr = (request.getParameter("pageNbr") == null ? getPageNbr() : Integer.valueOf(request.getParameter("pageNbr")));
		int viewPageSize = (request.getParameter("pageSize") == null ? getPageSize() : Integer.valueOf(request.getParameter("pageSize")));
		
		setAllComputer(ComputerService.getInstance().countComputer());
		setPageMax(defineMaxPage(getAllComputer()));
		
		if( viewPageSize != getPageSize()){
			setPageSize(viewPageSize);
		}else if(viewPageNbr != getPageNbr()){
			setPageNbr(viewPageNbr);
		}
		
		Collection<Computer> page = ComputerService.getInstance().getPageComputer(getPageSize(),getPageNbr());
		Collection<ComputerDto> dtoList = new ArrayList<>();
		
		for(Computer element : page) {
			dtoList.add(ComputerMapper.getInstance().computerToDto(element));
		}
		
		request.setAttribute("allComputer", getAllComputer());
		request.setAttribute("pageMax", getPageMax());
		request.setAttribute("pageSize", getPageSize());
		request.setAttribute("pageNbr", getPageNbr());
		request.setAttribute("computers", dtoList);
	
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
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

}
