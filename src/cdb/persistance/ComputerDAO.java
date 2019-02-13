package cdb.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import cdb.exception.*;

import cdb.model.Computer;

public class ComputerDAO implements IDAO<Computer>{
	
	public static final String SELECT_BY_ID = "SELECT * FROM computer WHERE id = ?";
	public static final String SELECT_QUERY = "SELECT * FROM computer";
	public static final String INSERT_QUERY = "INSERT INTO computer(name,introduced,discontinued,company_id) VALUES (?,?,?,?)";
	public static final String DELETE_QUERY = "DELETE FROM computer WHERE id = ?";
	public static final String EXISTENT_QUERY = "SELECT count(id) AS count FROM computer WHERE id = ?";
	public static final String UPDATE_QUERY = "UPDATE computer SET name = ?, company_id = ?, discontinued = ? WHERE id = ?";
	
	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String IN = "introduced";
	private static final String OUT = "discontinued";
	private static final String COMPANY_ID = "company_id";
	private static final String COUNT = "count";
	
	private Computer createResult(ResultSet resultSet)throws SQLException{
		int id = resultSet.getInt(ID);
		String name = resultSet.getString(NAME);
		Timestamp dateIn = (Timestamp)resultSet.getObject(IN);
		Timestamp dateOut = (Timestamp)resultSet.getObject(OUT);
		int companyId = resultSet.getInt(COMPANY_ID);
		
		return new Computer(id,name,dateIn,dateOut,companyId);
	}
	
	@Override
	public Computer getId(int objectId){
		Computer result = null;
		
		try {
			Connection connection = DAO.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
			statement.setInt(1, objectId);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				result = createResult(resultSet);				
			}
		}catch(SQLException e){
			e.printStackTrace();			
		}		
		return result;
	}
	
	@Override
	public Collection<Computer> getAll(){
		List<Computer> result = new ArrayList<>();
		
		try {
			
			Connection connection = DAO.getConnection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_QUERY);
			
			while(resultSet.next()) {
				result.add(createResult(resultSet));
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return result;
	}
	
	@Override
	public Computer add(Computer object) {
		Connection connection;
		
		try {
			connection = DAO.getConnection();
			PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, object.getName());
			statement.setObject(2, object.getIn());
			statement.setObject(3, object.getOut());
			statement.setInt(4, object.getCompId());
			
			statement.executeUpdate();
			
			ResultSet resultSet= statement.getGeneratedKeys();
			if(resultSet.next()) {
				int insertId = resultSet.getInt(1);
				object.setId(insertId);
				return object;
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	public Collection<Computer> addAll(Collection<Computer> objects) {
		throw new CustomException();
	}

	@Override
	public Computer update(Computer object) {
		Connection connection;
	
		try {
			connection = DAO.getConnection();
			
			PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
			statement.setString(1, object.getName());
			statement.setInt(2, object.getCompId());
			statement.setObject(3, object.getOut());
			statement.setInt(4, object.getId());			
			statement.executeUpdate();
			
			return object;
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean delete(Computer object) {
		return deleteById(object.getId());
	}
	
	@Override
	public boolean deleteById(int id) {
		Connection connection;
		
		try {
				connection = DAO.getConnection();
				
				PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
				statement.setInt(1, id);
				statement.executeUpdate();
				
				return(true);
				
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean existentById(int id) {
		int count= 0;
	
		try {
			Connection connection;
			
			connection = DAO.getConnection();
			PreparedStatement statement = connection.prepareStatement(EXISTENT_QUERY);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				count = resultSet.getInt(COUNT);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return count != 0;
	}
}
