package cdb.persistance;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


import cdb.model.Company;

public class CompanyDAO implements IDAO<Company>{

	public static final String SELECT_QUERY = "SELECT * FROM company";
	
	private static final String ID = "id";
	private static final String NAME = "name";
	
	private Company createResult(ResultSet resultSet)throws SQLException{
		int id = resultSet.getInt(ID);
		String name = resultSet.getString(NAME);
		
		return new Company(id,name);
		
	}
	
	@Override
	public Collection<Company> getAll(){
		List<Company> result = new ArrayList<>();
		
		try {
			Connection connection = DAO.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_QUERY);
			
			while(resultSet.next()) {
				result.add(createResult(resultSet));
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public Company getId(int i) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public Company add(Company object) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Collection<Company> addAll(Collection<Company> objects) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Company update(Company object) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean delete(Company object) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean deleteById(int id) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean existentById(int id) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
}
