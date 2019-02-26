package fr.excilys.cdb.dto;

public class ComputerDto {
	
	private int id;
	private String name;
	private String in;
	private String out;
	private String compId;
	private String compName;
	
	public ComputerDto(){
		
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
	
	public String getIn() {
		return in;
	}
	
	public void setIn(String in) {
		this.in = in;
	}
	
	public String getOut() {
		return out;
	}
	
	public void setOut(String out) {
		this.out = out;
	}
	
	public String getCompId() {
		return compId;
	}
	
	public void setCompId(String compId) {
		this.compId = compId;
	}
	
	public String getCompName() {
		return compName;
	}
	
	public void setCompName(String compName) {
		this.compName = compName;
	}
}
