package fr.excilys.cdb.servlet;

import java.util.ArrayList;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.excilys.cdb.dto.CompanyDto;
import fr.excilys.cdb.dto.ComputerDto;
import fr.excilys.cdb.exception.ValidatorException;
import fr.excilys.cdb.mapper.CompanyMapper;
import fr.excilys.cdb.mapper.ComputerMapper;
import fr.excilys.cdb.model.Company;
import fr.excilys.cdb.model.Computer;
import fr.excilys.cdb.service.CompanyService;
import fr.excilys.cdb.service.ComputerService;
import fr.excilys.cdb.service.Pagination;
import fr.excilys.cdb.service.Pagination.PaginationBuilder;

@Controller
@RequestMapping(value = "/")
public class ComputerController {

	ComputerService computerService;
	ComputerMapper computerMapper;
	CompanyService companyService;
	CompanyMapper companyMapper;
	Pagination pageBuilder;
	private static Logger logger = LoggerFactory.getLogger(ComputerController.class);

	public ComputerController(ComputerService computerService, ComputerMapper computerMapper, CompanyService companyService, CompanyMapper companyMapper) {
		this.computerService = computerService;
		this.computerMapper = computerMapper;
		this.companyService = companyService;
		this.companyMapper = companyMapper;
	}

	@GetMapping
	public ModelAndView getPage(@RequestParam(required = true, defaultValue = "1") Integer pageNbr, 
								@RequestParam(required = true, defaultValue = "10") Integer pageSize,
								@RequestParam(required = false) String search,
								@RequestParam(required = false) String order,
								@RequestParam(required = false) String sort){
		
		int totalComputer = (search == null ? this.computerService.countComputer() : this.computerService.countComputer(search));
		
		pageBuilder = new PaginationBuilder().setPage(pageNbr)
			.setPageSize(pageSize)
			.setMaxPage(defineMaxPage(totalComputer, Float.valueOf(pageSize)))
			.setOffSet(defineOffSet(pageNbr,pageSize))
			.setSearch(search)
			.setOrder(order)
			.setSort(sort)
			.build();
		
		Collection<ComputerDto> dtoList = new ArrayList<>();		
		Collection<Computer> page = this.computerService.getPageComputer(pageBuilder);
		
		for(Computer element : page) {
			dtoList.add(this.computerMapper.objectToDto(element));
		}
					
		return dashboardInit(totalComputer, pageBuilder, dtoList);
	}
	
	@GetMapping(value="/add")
	public ModelAndView add() {
		Collection<Company> companies = this.companyService.getAll();
		Collection<CompanyDto> dtoList = new ArrayList<>();
		
		for(Company element : companies) {
			dtoList.add(this.companyMapper.objectToDto(element));
		}
		return addInit(dtoList);
	}
	
	@PostMapping(value="/add")
	public ModelAndView addPost(@RequestParam(required = true, name="nameCpt") String nameComputer,
								@RequestParam(required = false, name="inCpt") String introduced,
								@RequestParam(required = false, name="outCpt") String discontinued,
								@RequestParam(required = true, name="idCpy") String companyId,
								ModelAndView modelView){
		
		ComputerDto dto = new ComputerDto();
		Computer computer = new Computer();
		
		dto.setName(nameComputer);
		dto.setIn(introduced);
		dto.setOut(discontinued);
		dto.setCompId(companyId);
		
		try {
			computer = this.computerMapper.dtoToObject(dto);
			this.computerService.add(computer);
			logger.info("computer added");
			modelView.setViewName("redirect:/");
		}catch(ValidatorException e){
			logger.error(e.getMessage());
			modelView.setViewName("500");
		}
		
		return modelView;
	}
	
	private ModelAndView dashboardInit(int totalComputer, Pagination page, Collection<ComputerDto> dtoList) {
		ModelAndView modelView = new ModelAndView();
		
		modelView.addObject("allComputer",totalComputer);
		modelView.addObject("page", page);
		modelView.addObject("computers", dtoList);
		modelView.setViewName("dashboard");
		
		return modelView;
	}
	
	private ModelAndView addInit(Collection<CompanyDto> companies) {
		ModelAndView modelView = new ModelAndView();
		
		modelView.addObject("companies", companies);
		modelView.setViewName("addComputer");
		
		return modelView;
	}

	private int defineMaxPage(float count, float pPageSize) {

		float tmp = (count / pPageSize);
		int newMaxPage = (int) Math.ceil(tmp);

		return newMaxPage;
	}
	
	private int defineOffSet(int pPage, int pPageSize) {
		
		int newOffSet = (pPage-1)*pPageSize;		
		
		return newOffSet;
	}
}
