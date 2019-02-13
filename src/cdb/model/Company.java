package cdb.model;

public class Company {

	private int id;
	private String name;
	
	public Company(int pId, String pName) {
		this.id = pId;
		this.name = pName;
	}
	
	@Override
	public String toString() {
		return "Company [id=" + id + ", name=" + name + "]";
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
