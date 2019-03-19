package fr.excilys.cdb.servlet;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import fr.excilys.cdb.dto.ComputerDto;
import fr.excilys.cdb.mapper.ComputerMapper;
import fr.excilys.cdb.model.Computer;
import fr.excilys.cdb.service.ComputerService;
import fr.excilys.cdb.service.Pagination;
import fr.excilys.cdb.service.Pagination.PaginationBuilder;

@Controller
@RequestMapping(value = "/")
public class ComputerController {

	ComputerService computerService;
	ComputerMapper computerMapper;
	Pagination pageBuilder;

	public ComputerController(ComputerService computerService, ComputerMapper computerMapper) {
		this.computerService = computerService;
		this.computerMapper = computerMapper;
	}

	@GetMapping
	public ModelAndView getPage(@RequestParam(required = true, defaultValue = "1") Integer pageNbr, 
								@RequestParam(required = true, defaultValue = "10") Integer pageSize,
								@RequestParam(required = false) String search,
								@RequestParam(required = false) String order,
								@RequestParam(required = false) String sort) {
		
		int totalComputer = (search == null ? this.computerService.countComputer() : this.computerService.countComputer(search));
		
		pageBuilder = new PaginationBuilder().setPage(pageNbr)
			.setPageSize(pageSize)
			.setMaxPage(defineMaxPage(totalComputer, Float.valueOf(pageSize)))
			.setOffSet(defineOffSet(pageNbr,pageSize))
			.setSearch(search)
			.setOrder(order)
			.setSort(sort)
			.build();
		
		Collection<Computer> page = this.computerService.getPageComputer(pageBuilder);
		Collection<ComputerDto> dtoList = new ArrayList<>();
		
		for(Computer element : page) {
			dtoList.add(this.computerMapper.objectToDto(element));
		}
		
		ModelAndView modelAndView = dashboardInit(totalComputer, pageBuilder, dtoList);
		
		
		return modelAndView;
	}
	
	private ModelAndView dashboardInit(int totalComputer, Pagination page, Collection<ComputerDto> dtoList) {
		ModelAndView modelView = new ModelAndView();
		
		modelView.addObject("allComputer",totalComputer);
		modelView.addObject("page", page);
		modelView.addObject("computers", dtoList);
		modelView.setViewName("dashboard");
		
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
