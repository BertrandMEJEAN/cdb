package fr.excilys.cdb.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import fr.excilys.cdb.dto.ComputerDto;
import fr.excilys.cdb.model.Company;
import fr.excilys.cdb.model.Computer;

public class ComputerMapper {

		private static ComputerMapper INSTANCE;
		private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		private ComputerMapper() {
			
		}
		
		public static ComputerMapper getInstance() {
			if(INSTANCE == null) {
				INSTANCE = new ComputerMapper();
			}
			
			return INSTANCE;
		}
		
		public Computer dtoToComputer(ComputerDto object) {
			
			Computer computer = new Computer();
			Company company = new Company(Integer.valueOf(object.getCompId()),object.getCompName());
			Optional<Company> companyOpt = Optional.of(company);
			
			computer.setId(object.getId());
			computer.setName(object.getName());
			computer.setIn(convertStringToLocalDate(object.getIn()));
			computer.setOut(convertStringToLocalDate(object.getIn()));
			computer.setCompany(companyOpt);
			
			return computer;
		}
		
		public ComputerDto computerToDto(Computer object) {
			ComputerDto dto = new ComputerDto();
			
			dto.setId(object.getId());
			dto.setName(object.getName());
			dto.setIn(convertLocalDateToString(object.getIn()));
			dto.setOut(convertLocalDateToString(object.getOut()));
			dto.setCompName(object.getCompany().getName());
			dto.setCompId(String.valueOf(object.getCompany().getId()));
			
			return dto;
			
		}
		
		private LocalDate convertStringToLocalDate(String date) {
			Optional<LocalDate> lDate = Optional.empty();
			
			if(date != null) {
				lDate= Optional.of(LocalDate.parse(date, FORMATTER));
			}
			return (lDate.isPresent() ? lDate.get() : null);
		}
		
		private String convertLocalDateToString(LocalDate date) {
			
			if(date != null) {
				String formattedString = date.format(FORMATTER);
				return formattedString;
			}
			
			return null;
		}
}
