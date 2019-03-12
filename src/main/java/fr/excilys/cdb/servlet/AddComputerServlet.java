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
import fr.excilys.cdb.dto.CompanyDto;
import fr.excilys.cdb.dto.ComputerDto;
import fr.excilys.cdb.exception.ValidatorException;
import fr.excilys.cdb.mapper.CompanyMapper;
import fr.excilys.cdb.mapper.ComputerMapper;
import fr.excilys.cdb.model.Company;
import fr.excilys.cdb.model.Computer;
import fr.excilys.cdb.persistance.CompanyDAO;
import fr.excilys.cdb.service.CompanyService;
import fr.excilys.cdb.service.ComputerService;

/**
 * Servlet implementation class AddComputerServlet
 */
@WebServlet("/AddComputer")
public class AddComputerServlet extends HttpServlet {
	
	private ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
	private static final long serialVersionUID = 1L;
	
	
	private CompanyService companyService = ctx.getBean(CompanyService.class);
	private ComputerService computerService = ctx.getBean(ComputerService.class);
	private CompanyMapper companyMapper = ctx.getBean(CompanyMapper.class);
	private ComputerMapper computerMapper = ctx.getBean(ComputerMapper.class);
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddComputerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Collection<Company> companies = this.companyService.getAll();
		Collection<CompanyDto> dtoList = new ArrayList<>();
		for(Company element : companies) {
			dtoList.add(this.companyMapper.objectToDto(element));
		}
		request.setAttribute("companies", dtoList);
		this.getServletContext().getRequestDispatcher("/WEB-INF/views/addComputer.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ComputerDto dto = new ComputerDto();
		Computer computer = new Computer();
		dto.setName(request.getParameter("nameCpt"));
		dto.setIn(request.getParameter("inCpt"));
		dto.setOut(request.getParameter("outCpt"));
		dto.setCompId(request.getParameter("idCpy"));
		
		try {
			computer = this.computerMapper.dtoToObject(dto);
			this.computerService.add(computer);
		}catch(ValidatorException e) {
			e.getMessage();
		}
		
		response.sendRedirect("/cdb/");
	}

}
