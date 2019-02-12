package cdb.model;

import java.sql.*;

public class Computer {
	
	private int id;
	private String name;
	private Timestamp in;
	private Timestamp out;
	private int compId;
	
	public Computer(){
		
	}
	
	public Computer(int pId, String pName, Timestamp pIn, Timestamp pOut, int pComId) {
		this.id=pId;
		this.name=pName;
		this.in=pIn;
		this.out=pOut;
		this.compId=pComId;
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

	public Timestamp getIn() {
		return in;
	}

	public void setIn(Timestamp in) {
		this.in = in;
	}

	public Timestamp getOut() {
		return out;
	}

	public void setOut(Timestamp out) {
		this.out = out;
	}

	public int getCompId() {
		return compId;
	}

	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", in=" + in + ", out=" + out + ", compId=" + compId + "]";
	}

	public void setCompId(int compId) {
		this.compId = compId;
	}

}

/*public static class ComputerBuilder{
	private int id;
	private String name;
	private Date dateIn;
	private Date dateOut;
	private int compId;
	
	public Computer build() {
		Computer computer = new Computer();
		
		computer.setId(id);
		computer.setName(name);
		computer.setIn(dateIn);
		computer.setOut(dateOut);
		computer.setCompId(compId);
		
		return computer;
	}
	
	public ComputerBuilder setId(int id) {
		this.id = id;
		return this;
	}
	
	public ComputerBuilder setName(String name) {
		this.name = name;
		return this;
	}
	
	public ComputerBuilder setDateIn(Date dateIn) {
		this.dateIn = dateIn;
		return this;
	}
	
	public ComputerBuilder setDateOut(Date dateOut) {
		this.dateOut = dateIn;
		return this;
	}
	
	public ComputerBuilder setCompanyId(int companyId) {
		this.compId = companyId;
		return this;
	}
}*/


