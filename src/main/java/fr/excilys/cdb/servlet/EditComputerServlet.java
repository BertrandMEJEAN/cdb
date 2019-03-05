package fr.excilys.cdb.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.excilys.cdb.dto.CompanyDto;
import fr.excilys.cdb.dto.ComputerDto;
import fr.excilys.cdb.mapper.CompanyMapper;
import fr.excilys.cdb.mapper.ComputerMapper;
import fr.excilys.cdb.model.Company;
import fr.excilys.cdb.model.Computer;
import fr.excilys.cdb.service.CompanyService;
import fr.excilys.cdb.service.ComputerService;
import fr.excilys.cdb.exception.ValidatorException;

/**
 * Servlet implementation class EditComputerServlet
 */
@WebServlet("/EditComputer")
public class EditComputerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditComputerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int cptId = Integer.valueOf(request.getParameter("cptId"));
		request.setAttribute("cptId", cptId);
		
		Collection<Company> companies = CompanyService.getInstance().getAll();
		Collection<CompanyDto> dtoList = new ArrayList<>();
		Optional<Computer> computer = ComputerService.getInstance().getId(cptId);
		ComputerDto dtoCpt = ComputerMapper.getInstance().objectToDto(computer.get());
		
		for(Company company : companies) {
			dtoList.add(CompanyMapper.getInstance().objectToDto(company));
		}
		
		request.setAttribute("cptName", dtoCpt.getName());
		request.setAttribute("cptIn", dtoCpt.getIn());
		request.setAttribute("cptOut", dtoCpt.getOut());
		request.setAttribute("cptCompId", dtoCpt.getCompId());
		request.setAttribute("companies", dtoList);
		
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/editComputer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ComputerDto dto = new ComputerDto();
		Computer computerUpdated = new Computer();
		
		dto.setId(request.getParameter("cptId"));
		dto.setName(request.getParameter("nameCpt"));
		dto.setIn(request.getParameter("introCpt"));
		dto.setOut(request.getParameter("discontCpt"));
		dto.setCompId(request.getParameter("idCpy"));
		
		System.out.println(dto.toString());
		
		try {
			computerUpdated = ComputerMapper.getInstance().dtoToObject(dto);
			ComputerService.getInstance().update(computerUpdated);
		}catch(ValidatorException e) {
			e.getMessage();
		}
		
		this.getServletContext().getRequestDispatcher("/").forward(request, response);
	}

}
