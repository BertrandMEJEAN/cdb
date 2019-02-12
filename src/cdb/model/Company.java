package cdb.model;

public class Company {

	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
	}

	private int id;
	private String name;
	
	public Company(int pId, String pName) {
		this.id = pId;
		this.name = pName;
	}
}
