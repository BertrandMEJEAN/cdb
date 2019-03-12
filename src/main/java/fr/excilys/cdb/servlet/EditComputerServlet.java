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

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.excilys.cdb.configuration.SpringConfig;
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
	private ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
	private static final long serialVersionUID = 1L;
    
	private CompanyService companyService = ctx.getBean(CompanyService.class);
	private ComputerService computerService = ctx.getBean(ComputerService.class);
	private ComputerMapper computerMapper = ctx.getBean(ComputerMapper.class);
	private CompanyMapper companyMapper = ctx.getBean(CompanyMapper.class);
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
		
		Collection<Company> companies = this.companyService.getAll();
		Collection<CompanyDto> dtoList = new ArrayList<>();
		Optional<Computer> computer = this.computerService.getId(cptId);
		ComputerDto dtoCpt = this.computerMapper.objectToDto(computer.get());
		
		for(Company company : companies) {
			dtoList.add(this.companyMapper.objectToDto(company));
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
		
		try {
			computerUpdated = this.computerMapper.dtoToObject(dto);
			this.computerService.update(computerUpdated);
		}catch(ValidatorException e) {
			e.getMessage();
		}
		
		response.sendRedirect("/cdb/");
	}

}
