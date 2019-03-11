package fr.excilys.cdb.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import fr.excilys.cdb.exception.*;
import fr.excilys.cdb.model.Company;
import fr.excilys.cdb.model.Computer;
import fr.excilys.cdb.persistance.CompanyDAO;
import fr.excilys.cdb.service.Pagination;

/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/


/**
 * classe faisant le liens entre les opération sur les computers et l'application.
 * @author Bertrand Méjean.
 */
public class ComputerDAO implements IDAO<Computer>{
	
	private static ComputerDAO INSTANCE;
	
	public static final String SELECT_BY_ID = "SELECT computer.id,computer.name,introduced,discontinued,company_id,company.name FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.id = ?";
	public static final String SELECT_QUERY = "SELECT computer.id,computer.name,introduced,discontinued,company_id,company.name FROM computer LEFT JOIN company ON computer.company_id = company.id";
	public static final String INSERT_QUERY = "INSERT INTO computer(name,introduced,discontinued,company_id) VALUES (?,?,?,?)";
	public static final String DELETE_QUERY = "DELETE FROM computer WHERE id = ?";
	public static final String EXISTENT_QUERY = "SELECT count(id) AS count FROM computer WHERE id = ?";
	public static final String UPDATE_QUERY = "UPDATE computer SET name = ?, company_id = ?, introduced = ?, discontinued = ? WHERE id = ?";
	public static final String COUNT_COMPUTER = "SELECT count(id) AS count FROM computer";
	public static final String COUNT_SEARCHED = "SELECT count(computer.id) AS count FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.name LIKE ? OR company.name LIKE ?";
	public static final String PAGE_QUERY = "SELECT computer.id,computer.name,introduced,discontinued,company_id,company.name FROM computer LEFT JOIN company ON computer.company_id = company.id LIMIT ? OFFSET ?";
	public static final String SEARCH_QUERY = "SELECT computer.id,computer.name,introduced,discontinued,company_id,company.name FROM computer LEFT JOIN company ON computer.company_id = company.id WHERE computer.name LIKE ? OR company.name LIKE ? LIMIT ? OFFSET ?";
	
	
	private static final String ID = "computer.id";
	private static final String NAME = "computer.name";
	private static final String IN = "introduced";
	private static final String OUT = "discontinued";
	private static final String COMPANY_ID = "company_id";
	private static final String COMPANY_NAME = "company.name";
	private static final String COUNT = "count";
	
	/*Logger logger = LoggerFactory.getLogger(ComputerDAO.class);*/
	
	public static ComputerDAO getInstance() {
		if(INSTANCE == null) {
			return INSTANCE = new ComputerDAO();
		}
		return INSTANCE;
	}
	
	/**
	 * Créer un objet de type Computer avec les informations du ResulSet retournées par la base de donnée.
	 * @author Bertrand Méjean.
	 * @param resultSet Demande un objet de type ResultSet.
	 * @exception Peut levé une exception de type SQLException.
	 * @return new Computer(id,name,dateIn,dateOut,companyId) Retourne un objet de type Computer construit avec les données présentes dans resultSet.
	 */
	private Computer createResult(ResultSet resultSet)throws SQLException{
		
		
		int id = resultSet.getInt(ID);
		String name = resultSet.getString(NAME);
		Timestamp timesTamp = resultSet.getTimestamp(IN);
		LocalDate dateIn = convertTimestampToLocalDate(timesTamp);
		timesTamp = resultSet.getTimestamp(OUT);
		LocalDate dateOut = convertTimestampToLocalDate(timesTamp);
		Optional<Company> company = Optional.of(new Company(resultSet.getInt(COMPANY_ID),resultSet.getString(COMPANY_NAME)));
		
		return new Computer(id,name,dateIn,dateOut,company);
	}
	
	/**
	 * Permet de récupérer un computer dans la base de donnée via son id.
	 * @see cdb.persistance.IDAO#getId(int).
	 * @author Bertrand Méjean.
	 * @exception SQLException Renvois une exception custom prévenant l'utilisateur que l'id renseigné n'est pas présent en base.
	 * @param objectId Demande l'id du computer de type int.
	 * @return result Retourne un objet de type Computer disposant des données relatives à l'id passé en paramètre.
	 */
	public Optional<Computer> getId(int objectId){
		Optional<Computer> result = Optional.empty();
		
		try(Connection connection = DAO.getConnection()) {
			
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
	
	/**
	 * Permet de construire une liste contenant tout les computers de la base de donnée.
	 * @author Bertrand Méjean.
	 * @return result Retourne une liste d'objet de type Computer contenant tout les computeurs de la base de donnée.
	 */
	public Collection<Computer> getAll(){
		List<Computer> result = new ArrayList<Computer>();
		
		try(Connection connection = DAO.getConnection()) {
			
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
	
	/**
	 *Récupère une key auto-incrementée de la base de donnée, puis construit une requete avec les element renseignés par l'utilisateur pour en ensuite insérer un nouveau computer.
	 *@author Bertrand Méjean.
	 *@exception Génère une exception custom pour prévenir l'utilisateur que le computer n'a pas été rajouter en base de donnée.
	 *@param object Demande un objet de type Computer.
	 *@return object 
	 *@param null Si l'objet passé en param est null ou echec de la requète SQL.
	 */
	public Optional<Computer> add(Computer object) {
		Optional<Computer> optional = Optional.empty();
		
		try(Connection connection = DAO.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, object.getName());
			statement.setObject(2, convertLocalDateToTimestamp(object.getIn()));
			statement.setObject(3, convertLocalDateToTimestamp(object.getOut()));
			statement.setObject(4, (object.getCompany().getId() != 0 ? object.getCompany().getId() : null));
			
			statement.executeUpdate();
			
			ResultSet resultSet= statement.getGeneratedKeys();
			if(resultSet.next()) {
				int insertId = resultSet.getInt(1);
				object.setId(insertId);
				optional = Optional.of(object);
			}
			
		}catch(SQLException e){
			//logger.info("Problème lors de la creation du nouvel ordinateur en base de donnée");
		}
		
		return optional;
	}
	
	public Collection<Computer> addAll(Collection<Computer> objects) {
		throw new CustomException();
	}

	/**
	 * Permet de modifier les détails d'un computer et de les appliquer en base de donnée.
	 * @author Bertrand Méjean
	 * @exception Génère une exception prévenant l'utilisateur que ses mise à jours ne sont pas appliquées.
	 * @param object Demande un objet de type Computer.
	 * @return object
	 * @return null Si l'objet passé en paramètre est null ou echec de la requète SQL.
	 */
	public Optional<Computer> update(Computer object) {
		Optional<Computer> opt = Optional.empty();
	
		try(Connection connection = DAO.getConnection()) {
			
			PreparedStatement statement = connection.prepareStatement(UPDATE_QUERY);
			statement.setString(1, object.getName());
			statement.setInt(2, (object.getCompany().getId() != 0 ? object.getCompany().getId() : null));
			statement.setObject(3, convertLocalDateToTimestamp(object.getIn()));
			statement.setObject(4, convertLocalDateToTimestamp(object.getOut()));
			statement.setInt(5, object.getId());			
			statement.executeUpdate();
			
			return opt = Optional.of(object);
			
		}catch(Exception e){
			//logger.info("Problème lors de la mise à jour des détails de l'ordinateur");AE
		}
		
		return opt;
	}

	/**
	 * Permet de supprimer un computer en lui passant un objet de type Computer.
	 * @see cdb.persistance.ComputerDAO#deleteByID(int id).
	 * @author Bertrand Méjean.
	 * @param object Demande un objet de type Computer.
	 */
	public boolean delete(Computer object) {
		return deleteById(object.getId());
	}
	
	/**
	 * Permet de supprimer un computer dans la base de donnée selon la saisie de l'utilisateur.
	 * @author Bertrand Méjean.
	 * @exception Génère une exception custom pour prévenir l'utilisateur que la supression du computer ne s'est pas éxécuter correctement.
	 * @param id Demande un id de type int.
	 * @return true Si le computer est correctement supprimé.
	 * @return false Si aucun computer n'est retrouvé en base ou echec de la requète.
	 */
	public boolean deleteById(int id) {
		
		try(Connection connection = DAO.getConnection()) {
				
				PreparedStatement statement = connection.prepareStatement(DELETE_QUERY);
				statement.setInt(1, id);
				statement.executeUpdate();
				System.out.println("L'ordinateur est bien supprimé");
				
				return(true);
				
		}catch(SQLException e) {
			//logger.info("Problème lors de la supression de l'ordinateur en base de données");
		}
		return false;
		
	}
	
	/**
	 * Permet de vérifier si un computer est présent dans la base de donnée via son id.
	 * @author Bertrand Méjean.
	 * @exception Génère une exception custom pour prévenir l'utilisateur que ce computer n'est pas présent en base.
	 * @param id Demande un id de computer de type int.
	 * @return count Retourne le nombre de computer trouvé corresopndant à l'id renseigné par l'utilisateur.
	 */
	public boolean existentById(int id) {
		int count= 0;
	
		try(Connection connection = DAO.getConnection()) {

			PreparedStatement statement = connection.prepareStatement(EXISTENT_QUERY);
			statement.setInt(1, id);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				count = resultSet.getInt(COUNT);
			}
			if(count == 0) {
				//logger.info("Cet ordinateur n'est pas référencé");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return count != 0;
	}
	
	public int countComputer() {
		
		int count = 0;
		
		try(Connection connection = DAO.getConnection()) {

			PreparedStatement statement = connection.prepareStatement(COUNT_COMPUTER);
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				count = resultSet.getInt(COUNT);
			}
			if(count == 0) {
				//logger.info("Cet ordinateur n'est pas référencé");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	public int countComputer(String pSearch) {
		
		int count = 0;
		
		try(Connection connection = DAO.getConnection()) {

			PreparedStatement statement = connection.prepareStatement(COUNT_SEARCHED);
			statement.setString(1, pSearch+"%");
			statement.setString(2, pSearch+"%");
			ResultSet resultSet = statement.executeQuery();
			
			while(resultSet.next()) {
				count = resultSet.getInt(COUNT);
			}
			if(count == 0) {
				//logger.info("Cet ordinateur n'est pas référencé");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return count;
	}
	
	public Collection<Computer> getPageComputer(Pagination page){
		List<Computer> computerPage = new ArrayList<>();
		
		try(Connection connection = DAO.getConnection()) {
			PreparedStatement statement;
			
			if(page.getSearch() == null) {
				statement = connection.prepareStatement(PAGE_QUERY);
				statement.setInt(1, page.getPageSize());
				statement.setInt(2, page.getOffSet());
			}else {
				statement = connection.prepareStatement(SEARCH_QUERY);
				statement.setString(1, page.getSearch()+"%");
				statement.setString(2, page.getSearch()+"%");
				statement.setInt(3, page.getPageSize());
				statement.setInt(4, page.getOffSet());
			}
			
			ResultSet resultSet = statement.executeQuery();

			while(resultSet.next()) {

				computerPage.add(createResult(resultSet));

			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return computerPage;
	}
	
	private Timestamp convertLocalDateToTimestamp(LocalDate date) {
		Optional<Timestamp> ts = Optional.empty();
		
		if(date != null) {
			ts= Optional.of(Timestamp.valueOf(date.atStartOfDay()));
		}
		return (ts.isPresent() ? ts.get() : null);
	}
	
	private LocalDate convertTimestampToLocalDate(Timestamp date) {
		Optional<LocalDate> lDate = Optional.empty();
		
		if(date != null) {
			lDate= Optional.of(date.toLocalDateTime().toLocalDate());
		}
		return (lDate.isPresent() ? lDate.get() : null);
	}
}
