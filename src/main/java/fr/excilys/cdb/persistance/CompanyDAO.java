package fr.excilys.cdb.persistance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/
import java.util.Optional;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariDataSource;

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
	
	JdbcTemplate jdbc;
	
	//Logger logger = LoggerFactory.getLogger(CompanyDAO.class);
	
	public CompanyDAO(HikariDataSource hikariSource) {
		this.jdbc = new JdbcTemplate(hikariSource);
	}
	
	/**
	 * Permet d'afficher toute les companys présentes dans la base de donnée.
	 * @exception Génère une exception pour prévenir l'utilisateur d'un problème lors de la récupération des company dans la base donnée.
	 * @return result Retourne une liste contenant toute les companys présentent dans la base de donnée.
	 */
	public Collection<Company> getAll(){		
		return jdbc.query(SELECT_QUERY, this);
	}
	
	public Optional<Company> getId(int objectId) {
		Optional<Company> result = Optional.empty();
			
		return result = Optional.of(jdbc.queryForObject(SELECT_BY_ID, new Object[] {objectId}, this));
	}
	
	public int add(Company object) {
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
	
	public int delete(Company object) {		
//		try(Connection connection = dao.getConnection()) {
//			
//			PreparedStatement statementComputers = connection.prepareStatement(DELETE_QUERY);
//			PreparedStatement statementCompany = connection.prepareStatement(DELETE_COMPUTER_BY_COMPANY);
//			
//			try {
//				connection.setAutoCommit(false);
//				statementComputers.setInt(1, id);
//				statementComputers.execute();
//				System.out.println("Query "+statementComputers+" has been successfull executed");
//				statementCompany.setInt(1,id);
//				statementCompany.execute();
//				System.out.println("Company number "+ id +" successfully deleted");
//				connection.commit();
//				return(true);
//			}catch(SQLException e){
//				connection.rollback();
//				e.printStackTrace();;
//			}
//			
//		}catch(SQLException e) {
//			e.printStackTrace();
//			//logger.info("Problème lors de la supression de l'ordinateur en base de données");
//		}
//		
//	return false;
		return 1; 
	}
	
	/**
	 * Vérifie si la company renseignée par l'utilisateur est référencée en base.
	 * @param id Demande un id de type int.
	 * @return count indique le nombre d'id présent dans la base donnée
	 */
	public boolean existentById(int id) {
		return (jdbc.queryForObject(EXISTENT_BY_ID, new Object[] {id}, Integer.class) != 0 ? true : false);
	}

	@Override
	public Company mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		Company company = new Company();
		
		company.setId(resultSet.getInt(ID));
		company.setName(resultSet.getString(NAME));
		
		return company;
	}	
}
