package cdb.persistance;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import cdb.app.Company;

public class DataBaseConnexion {
	
	String url = "jdbc:mysql://localhost:3306/computer-database-db";
	String username = "admincdb";
	String password = "qwerty1234";
	List<Company> list = new ArrayList<>();
	
	
	
	public void connector() throws SQLException{
	
		try(Connection connection = DriverManager.getConnection(url,username,password)){
			
			System.out.println("Databased connected !");
			Statement myState = connection.createStatement();
			ResultSet myResult = myState.executeQuery("SELECT * FROM company");
			
			while(myResult.next()){
				Company com = new Company();
				com.setId(myResult.getLong("id"));
				com.setName(myResult.getString("name"));
				list.add(com);
				//System.out.println(com.getId()+" "+com.getName());				
			}
			
		}catch(SQLException e){
			throw new IllegalStateException("Cannot connect the database !", e);
		}		
	}
	
	public List<Company> getList(){
		return this.list;
	}
	
	public void setList(List<Company> pList){
		this.list = pList;
	}
}
