package com.excilys.cdb.model;

public class Company {

	private int id;
	private String name;
	
	/**
	 * Constructeur de la classe Company.
	 * @param pId Id de la company.
	 * @param pName Nom de la company.
	 */
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
