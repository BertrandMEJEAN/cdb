package fr.excilys.cdb.mapper;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.excilys.cdb.dto.ComputerDto;
import fr.excilys.cdb.exception.ValidatorException;
import fr.excilys.cdb.model.Company;
import fr.excilys.cdb.model.Computer;
import fr.excilys.cdb.validator.ComputerValidator;

@Component
public class ComputerMapper implements IMapper<Computer, ComputerDto> {

		private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");
		
		@Autowired
		private ComputerValidator computorValidator;
		
		public ComputerMapper() {
			
		}
		
		public Computer dtoToObject(ComputerDto object) throws ValidatorException {
			
			Computer computer = new Computer();
			Company company = ( object.getCompId() == "" || object.getCompId() == null ? new Company() : new Company(Integer.valueOf(object.getCompId()),object.getCompName()));
			Optional<Company> companyOpt = Optional.of(company);
			
			computer.setId((object.getId() == null ? 0 : Integer.valueOf(object.getId())));
			computer.setName(object.getName());
			computer.setIn(convertStringToLocalDate(object.getIn()));
			computer.setOut(convertStringToLocalDate(object.getOut()));
			computer.setCompany(companyOpt);
			this.computorValidator.validateComputer(computer);
			
			return computer;
		}
		
		public ComputerDto objectToDto(Computer object) {
			ComputerDto dto = new ComputerDto();
			
			dto.setId(String.valueOf(object.getId()));
			dto.setName(object.getName());
			dto.setIn(convertLocalDateToString(object.getIn()));
			dto.setOut(convertLocalDateToString(object.getOut()));
			
			if(object.getCompany() != null) {
				dto.setCompName(object.getCompany().getName());
				dto.setCompId(String.valueOf(object.getCompany().getId()));
			}else {
				dto.setCompName("");
				dto.setCompId(null);
			}
			
			return dto;
			
		}
		
		private LocalDate convertStringToLocalDate(String date) {
			Optional<LocalDate> lDate = Optional.empty();
			
			if(date != "" && date != null) {
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
