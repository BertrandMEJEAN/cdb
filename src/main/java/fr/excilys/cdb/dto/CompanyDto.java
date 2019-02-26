package fr.excilys.cdb.dto;

public class CompanyDto {

	private int id;	
	private String name;
	
	public CompanyDto() {
		
	}
	
	public CompanyDto(int pId,String pName) {
		this.id = pId;
		this.name = pName;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
