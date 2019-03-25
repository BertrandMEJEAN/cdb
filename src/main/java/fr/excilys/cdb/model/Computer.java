package fr.excilys.cdb.model;

import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.ForeignKey;


/**
 * Classe computer
 * @author Bertrand Méjean
 */
@Entity
@Table(name="computer")
public class Computer {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "introduced")
	private LocalDate in;
	
	@Column(name = "discontinued")
	private LocalDate out;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "company_id",
				table = "computer",
				referencedColumnName = "id",
				foreignKey = @ForeignKey(name="fk_computer_company_1"))
	private Company company;
	
	/*
	 * Constructeur par default de la classe Computer.
	 */
	public Computer(){
		
	}
	
	/**
	 * Constructeur de la classe Computer.
	 * @param pId Id du computer.
	 * @param pName Nom du computer.
	 * @param dateIn Date de mise en circulation.
	 * @param dateOut Date de retour.
	 * @param pComId Id de l'entreprise liée à ce computer.
	 */
	public Computer(int pId, String pName, LocalDate dateIn, LocalDate dateOut, Optional<Company> company) {
		this.id = pId;
		this.name = pName;
		this.in = dateIn;
		this.out = dateOut;
		this.company = (company.isPresent()? company.get() : new Company());
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

	public LocalDate getIn() {
		return in;
	}

	public void setIn(LocalDate in) {
		this.in = in;
	}

	public LocalDate getOut() {
		return out;
	}

	public void setOut(LocalDate out) {
		this.out = out;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Optional<Company> company) {
		this.company = company.get();
	}
	
	
	
	@Override
	public String toString() {
		return "Computer [id=" + id + ", name=" + name + ", in=" + in + ", out=" + out + ", company=" + company.getName() + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + id;
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
		Computer other = (Computer) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (id != other.id)
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


	public static class ComputerBuilder{
		private int id;
		private String name;
		private LocalDate in;
		private LocalDate out;
		private Optional<Company> company;
		
		public Computer build() {
			Computer computer = new Computer();
			
			computer.setId(this.id);
			computer.setName(this.name);
			computer.setIn(this.in);
			computer.setOut(this.out);
			computer.setCompany(this.company);
			
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
		
		public ComputerBuilder setIn(LocalDate in) {
			this.in = in;
			return this;
		}
		
		public ComputerBuilder setOut(LocalDate out) {
			this.out = out;
			return this;
		}
		
		public ComputerBuilder setCompany(Optional<Company> company) {
			this.company = company;
			return this;
		}
	}

}