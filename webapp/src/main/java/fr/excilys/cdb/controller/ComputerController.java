package fr.excilys.cdb.controller;

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
	public ModelAndView getPage(@RequestParam(required = true, defaultValue = "1", name = "page") Integer pageNbr, 
								@RequestParam(required = true, defaultValue = "10", name = "limit") Integer pageSize,
								@RequestParam(required = false) String search,
								@RequestParam(required = false) String order,
								@RequestParam(required = false) String sort){
		
		
		
		int totalComputer = (int)(search == null ? this.computerService.count() : this.computerService.countSearch(search));
		
		pageBuilder = new PaginationBuilder().setPage(pageNbr)
			.setPageSize(pageSize)
			.setMaxPage(defineMaxPage(totalComputer, Float.valueOf(pageSize)))
			.setOffSet(defineOffSet(pageNbr,pageSize))
			.setSearch(search)
			.setOrder(order)
			.setSort(sort)
			.build();
		
		Collection<ComputerDto> computersDto = new ArrayList<>();		
				
		if(pageBuilder.getSearch() == null && pageBuilder.getOrder() == null) {
			
			computersDto = this.computerService.getPage(pageBuilder);
			
		}else if(pageBuilder.getSearch() != null & pageBuilder.getOrder() == null) {
			
			computersDto = this.computerService.getPageContains(pageBuilder);
			
		}else if(pageBuilder.getOrder() != null){
			
			if(pageBuilder.getSearch() == null) {
				computersDto = this.computerService.getPageOrderBy(pageBuilder);
			}else{
				computersDto = this.computerService.getPageContainsAndOrderBy(pageBuilder);						
			}
		}
					
		return dashboardInit(totalComputer, pageBuilder, computersDto);
	}
	
	@GetMapping(value="/add")
	public ModelAndView add() {
		Collection<CompanyDto> companiesDto = this.companyService.getAll();
	
		return addInit(companiesDto);
	}
	
	@PostMapping(value="/add")
	public ModelAndView addPost(@RequestParam(required = true, name="nameCpt") String nameComputer,
								@RequestParam(required = false, name="inCpt") String introduced,
								@RequestParam(required = false, name="outCpt") String discontinued,
								@RequestParam(required = false, name="idCpy") String companyId,
								ModelAndView modelView){
		
		ComputerDto computerDto = new ComputerDto();
		
		computerDto.setName(nameComputer);
		computerDto.setIn(introduced);
		computerDto.setOut(discontinued);
		computerDto.setCompId(companyId);
		
		try {
			this.computerService.add(computerDto);
			logger.info("computer added");
			modelView.setViewName("redirect:/");
		}catch(ValidatorException e){
			logger.error(e.getMessage());
			modelView.setViewName("500");
		}
		
		return modelView;
	}
	
	@GetMapping(value="/edit")
	public ModelAndView edit(@RequestParam(required = true, name="cptId")String computerId) {
		
		Collection<CompanyDto> companiesDto = this.companyService.getAll();
		ComputerDto computerDto = this.computerService.getId(Integer.valueOf(computerId));
		
		return editInit(computerDto, companiesDto);
	}
	
	@PostMapping(value="/edit")
	public ModelAndView editPost(@RequestParam(required = true, name = "cptId") String computerId,
								 @RequestParam(required = true, name = "nameCpt") String computerName,
								 @RequestParam(required = false, name = "introCpt") String computerIn,
								 @RequestParam(required = false, name = "discontCpt") String computerOut,
								 @RequestParam(required = false, name = "idCpy") String companyId,
								 ModelAndView modelView) {
		
		ComputerDto computerDto = new ComputerDto();
		
		computerDto.setId(computerId);
		computerDto.setName(computerName);
		computerDto.setIn(computerIn);
		computerDto.setOut(computerOut);
		computerDto.setCompId(companyId);
		
		try {
			
			this.computerService.update(computerDto);
			logger.info("computer successfully updated");
			modelView.setViewName("redirect:/");
			
		}catch(ValidatorException e) {
			
			logger.error(e.getMessage());
			modelView.setViewName("500");
			
		}
		
		return modelView;
	}
	
	@PostMapping(value="/delete")
	public ModelAndView delete(@RequestParam(required = true, name = "selection") String[] computerSelected, ModelAndView modelView) {
		
		ComputerDto computerDto = new ComputerDto();
		
		try {
			
			for(String computerId : computerSelected) {
				computerDto.setId(computerId);
				this.computerService.delete(computerDto);
				logger.info("Computer correctly deleted");
				modelView.setViewName("redirect:/");
			}
			
		}catch(ValidatorException e) {
			
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
	
	private ModelAndView editInit(ComputerDto computerDto, Collection<CompanyDto> companiesDto) {
		ModelAndView modelView = new ModelAndView();
		
		modelView.addObject("computer", computerDto);
		modelView.addObject("companies", companiesDto);
		modelView.setViewName("editComputer");
		
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
