package fr.excilys.cdb.mapper;

import fr.excilys.cdb.exception.ValidatorException;

public interface IMapper<O, D> {
	
	public O dtoToObject(D dto) throws ValidatorException;
	public D objectToDto(O object);

}
