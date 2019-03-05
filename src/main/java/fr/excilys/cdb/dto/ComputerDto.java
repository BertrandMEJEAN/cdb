package fr.excilys.cdb.dto;

public class ComputerDto {
	
	private String id;
	private String name;
	private String in;
	private String out;
	private String compId;
	private String compName;
	
	public ComputerDto(){
		
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
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

	@Override
	public String toString() {
		return "ComputerDto [id=" + id + ", name=" + name + ", in=" + in + ", out=" + out + ", compId=" + compId
				+ ", compName=" + compName + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((compId == null) ? 0 : compId.hashCode());
		result = prime * result + ((compName == null) ? 0 : compName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((in == null) ? 0 : in.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((out == null) ? 0 : out.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ComputerDto other = (ComputerDto) obj;
		if (compId == null) {
			if (other.compId != null)
				return false;
		} else if (!compId.equals(other.compId))
			return false;
		if (compName == null) {
			if (other.compName != null)
				return false;
		} else if (!compName.equals(other.compName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (in == null) {
			if (other.in != null)
				return false;
		} else if (!in.equals(other.in))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (out == null) {
			if (other.out != null)
				return false;
		} else if (!out.equals(other.out))
			return false;
		return true;
	}

	
}
