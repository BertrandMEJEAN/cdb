package fr.excilys.cdb.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import fr.excilys.cdb.model.Company;

/**
 * classe faisant le liens entre les opération sur les companys et l'application.
 * @author Bertrand Méjean.
 */
@Component
public class CompanyDAO implements IDAO<Company>{

	public static final String SELECT_QUERY = "SELECT * FROM company";
	public static final String EXISTENT_BY_ID = "SELECT count(id) AS count FROM company WHERE id = ?";
	public static final String SELECT_BY_ID = "SELECT id,name FROM company WHERE id = ?";
	public static final String DELETE_QUERY = "DELETE FROM company WHERE id = ?";
	public static final String DELETE_COMPUTER_BY_COMPANY = "DELETE FROM computer WHERE company_id = ?";
	
	private static final String ID = "id";
	private static final String NAME = "name";
	private static final String COUNT = "count";
	
	@Autowired
	private DAO dao;
	
	//Logger logger = LoggerFactory.getLogger(CompanyDAO.class);
	
	/**
	 * Créer un objet de type Computer avec les informations du ResulSet retournées par la base de donnée.
	 * @author Bertrand Méjean.
	 * @param resultSet Demande un objet de type ResultSet.
	 * @exception Peut levé une exception de type SQLException.
	 * @return new Computer(id,name,dateIn,dateOut,companyId) Retourne un objet de type Computer construit avec les données présentes dans resultSet.
	 */
	public CompanyDAO() {
		
	}
	
	private Company createResult(ResultSet resultSet)throws SQLException{
		int id = resultSet.getInt(ID);
		String name = resultSet.getString(NAME);
		
		return new Company(id,name);		
	}
	
	/**
	 * Permet d'afficher toute les companys présentes dans la base de donnée.
	 * @exception Génère une exception pour prévenir l'utilisateur d'un problème lors de la récupération des company dans la base donnée.
	 * @return result Retourne une liste contenant toute les companys présentent dans la base de donnée.
	 */
	public Collection<Company> getAll(){
		List<Company> result = new ArrayList<Company>();
		
		try(Connection connection = dao.getConnection()) {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(SELECT_QUERY);
			
			while(resultSet.next()) {
				result.add(createResult(resultSet));
			}
			
		}catch(SQLException e) {
			//logger.info("Problème lors de la récupération de la liste des entreprises en base de donnée");
		}
		
		return result;
	}
	
	public Optional<Company> getId(int objectId) {
	Optional<Company> result = Optional.empty();
		
		try(Connection connection = dao.getConnection()) {
			
			PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
			statement.setInt(1, objectId);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				result= Optional.of(createResult(resultSet));				
			}
		}catch(SQLException e){
			//logger.info("The computer does not exist");
		}		
		return result;
	}
	
	public /*Optional<Company>*/ int add(Company object) {
		// TODO Auto-generated method stub
		return 0;
	}
	public Collection<Company> addAll(Collection<Company> objects) {
		// TODO Auto-generated method stub
		return null;
	}
	public boolean update(Company object) {
		// TODO Auto-generated method stub
		return true;
	}
	public boolean delete(Company object) {
		return deleteById(object.getId());
	}
	public boolean deleteById(int id){
		
		try(Connection connection = dao.getConnection()) {
			
			PreparedStatement statementComputers = connection.prepareStatement(DELETE_QUERY);
			PreparedStatement statementCompany = connection.prepareStatement(DELETE_COMPUTER_BY_COMPANY);
			
			try {
				connection.setAutoCommit(false);
				statementComputers.setInt(1, id);
				statementComputers.execute();
				System.out.println("Query "+statementComputers+" has been successfull executed");
				statementCompany.setInt(1,id);
				statementCompany.execute();
				System.out.println("Company number "+ id +" successfully deleted");
				connection.commit();
				return(true);
			}catch(SQLException e){
				connection.rollback();
				e.printStackTrace();;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			//logger.info("Problème lors de la supression de l'ordinateur en base de données");
		}
		
	return false;
	}
	
	/**
	 * Vérifie si la company renseignée par l'utilisateur est référencée en base.
	 * @param id Demande un id de type int.
	 * @return count indique le nombre d'id présent dans la base donnée
	 */
	public boolean existentById(int id) {
		int count= 0;
		
		try {
			Connection connection;
			
			connection = dao.getConnection();
			PreparedStatement statement = connection.prepareStatement(EXISTENT_BY_ID);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				count = resultSet.getInt(COUNT);
			}			
			if(count == 0) {
				//logger.info("Cette compagnie n'est pas référencée");
			}
		}catch(SQLException e) {
			//logger.info("Problème lors de l'intérogation de la base de donnée");
		}
		return count != 0;
	}

	@Override
	public Company mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}	
}
