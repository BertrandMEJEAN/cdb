package fr.excilys.cdb.persistance;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import fr.excilys.cdb.exception.*;
import fr.excilys.cdb.model.Computer;

/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;*/

/**
 * classe faisant le liens entre les opération sur les computers et l'application.
 * @author Bertrand Méjean.
 */
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
	
	//Logger logger = LoggerFactory.getLogger(ComputerDAO.class);
	
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
		Timestamp dateIn = (Timestamp)resultSet.getObject(IN);
		Timestamp dateOut = (Timestamp)resultSet.getObject(OUT);
		int companyId = resultSet.getInt(COMPANY_ID);
		
		return new Computer(id,name,dateIn,dateOut,companyId);
	}
	
	/**
	 * Permet de récupérer un computer dans la base de donnée via son id.
	 * @see cdb.persistance.IDAO#getId(int).
	 * @author Bertrand Méjean.
	 * @exception SQLException Renvois une exception custom prévenant l'utilisateur que l'id renseigné n'est pas présent en base.
	 * @param objectId Demande l'id du computer de type int.
	 * @return result Retourne un objet de type Computer disposant des données relatives à l'id passé en paramètre.
	 */
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
			//logger.info("Cet ordinateur n'est pas disponible en base");
		}		
		return result;
	}
	
	/**
	 * Permet de construire une liste contenant tout les computers de la base de donnée.
	 * @author Bertrand Méjean.
	 * @return result Retourne une liste d'objet de type Computer contenant tout les computeurs de la base de donnée.
	 */
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
			//logger.info("problème lors de la récupération de la liste des ordinateur en base de donnée");
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
				System.out.println("L'ordinateur est ajouté");
				return object;
			}
			
		}catch(SQLException e){
			//logger.info("Problème lors de la creation du nouvel ordinateur en base de donnée");
		}
		
		return null;
	}
	
	@Override
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
			//logger.info("Problème lors de la mise à jour des détails de l'ordinateur");
		}
		
		return null;
	}

	/**
	 * Permet de supprimer un computer en lui passant un objet de type Computer.
	 * @see cdb.persistance.ComputerDAO#deleteByID(int id).
	 * @author Bertrand Méjean.
	 * @param object Demande un objet de type Computer.
	 */
	@Override
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
	@Override
	public boolean deleteById(int id) {
		Connection connection;
		
		try {
				connection = DAO.getConnection();
				
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
			if(count == 0) {
				//logger.info("Cet ordinateur n'est pas référencé");
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return count != 0;
	}
}
