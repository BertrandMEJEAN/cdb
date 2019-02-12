package cdb.persistance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import cdb.app.Company;
import cdb.app.Computer;

public class DataBaseConnexion {
	
	String url = "jdbc:mysql://localhost:3306/computer-database-db";
	String username = "admincdb";
	String password = "qwerty1234";
	List<Company> listComp = new ArrayList<>();
	List<Company> litCpt = new ArrayList<>();
	
	
	
	public void connector() throws SQLException{
	
		try(Connection connection = DriverManager.getConnection(url,username,password)){
			
			System.out.println("Databased connected !");
			Statement myState = connection.createStatement();
			ResultSet myResult = myState.executeQuery("SELECT * FROM company");
			ResultSet myResult2 = myState.executeQuery("SELECT * FROM computer");
			
			
			while(myResult.next()){
				Company com = new Company();
				com.setId(myResult.getLong("id"));
				com.setName(myResult.getString("name"));
				listComp.add(com);							
			}
			
			while(myResult2.next()) {
				Computer comp = new Computer();
				comp.setId(myResult.getLong("id"));
				comp.setName(myResult.getString("name"));
				System.out.println(comp.getId()+" "+comp.getName());
			}
			
		}catch(SQLException e){
			throw new IllegalStateException("Cannot connect the database !", e);
		}		
	}
	
	public List<Company> getList(){
		return this.listComp;
	}
	
	public void setList(List<Company> pList){
		this.listComp = pList;
	}
}
