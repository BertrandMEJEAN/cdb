package fr.excilys.cdb.mapper;

public interface IMapper<O, D> {
	
	public O dtoToObject(D dto);
	public D objectToDto(O object);

}
