package cdb.app;

public class Company {
	
	private String name;
	private long id;
	
	public long getId() {
		return this.id;
	}

	public String getName() {
		return this.name;
	}
	
	public void setId(long pId) {
		this.id = pId;
	}
	
	public void setName(String pName) {
		this.name = pName;
	}
}
