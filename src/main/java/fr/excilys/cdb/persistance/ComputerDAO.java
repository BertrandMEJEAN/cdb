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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.zaxxer.hikari.HikariDataSource;

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
@Component
public class ComputerDAO implements IDAO<Computer>{
	
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
	
	@Autowired
	private DAO dao;
	
	JdbcTemplate jdbc;
	/*Logger logger = LoggerFactory.getLogger(ComputerDAO.class);*/

	public ComputerDAO(HikariDataSource hikariSource) {
		jdbc = new JdbcTemplate(hikariSource);
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
	 * @see cdb.persistance.IDAO#getId(int)..
	 * @param objectId Demande l'id du computer de type int.
	 * @return result Retourne un objet de type Computer disposant des données relatives à l'id passé en paramètre.
	 */
	public Optional<Computer> getId(int objectId){
		Optional<Computer> result = Optional.empty();
		
		return result = Optional.of(jdbc.queryForObject(SELECT_BY_ID, new Object[] {objectId}, this));
	}
	
	/**
	 * Permet de construire une liste contenant tout les computers de la base de donnée.
	 * @return result Retourne une liste d'objet de type Computer contenant tout les computeurs de la base de donnée.
	 */
	public Collection<Computer> getAll(){		
		return jdbc.query(SELECT_QUERY, this);
	}
	
	/**
	 *Récupère une key auto-incrementée de la base de donnée, puis construit une requete avec les element renseignés par l'utilisateur pour en ensuite insérer un nouveau computer..
	 *@param object Demande un objet de type Computer.
	 *@return int
	 */
	public int add(Computer object) {
		KeyHolder key = new GeneratedKeyHolder();
		
		return jdbc.update(
				connection ->{
					PreparedStatement ps = connection.prepareStatement(INSERT_QUERY, new String[]{ID});
					ps.setString(1, object.getName());
					ps.setObject(2, convertLocalDateToTimestamp(object.getIn()));
					ps.setObject(3, convertLocalDateToTimestamp(object.getOut()));
					ps.setObject(4, (object.getCompany().getId() != 0 ? object.getCompany().getId() : null));
					return ps;
				},key);
	}
	
	public Collection<Computer> addAll(Collection<Computer> objects) {
		throw new CustomException();
	}

	/**
	 * Permet de modifier les détails d'un computer et de les appliquer en base de donnée.
	 * @param object Demande un objet de type Computer.
	 * @return boolean to be sure about query execution
	 */
	public boolean update(Computer object) {		
		return jdbc.update(UPDATE_QUERY, object.getName(), (object.getCompany().getId() != 0 ? object.getCompany().getId() : null), convertLocalDateToTimestamp(object.getIn()), convertLocalDateToTimestamp(object.getOut()), object.getId()) > 0;
	}

	/**
	 * Permet de supprimer un computer en lui passant un objet de type Computer.
	 * @param object Demande un objet de type Computer.
	 */
	public int delete(Computer object) {
		return jdbc.update(DELETE_QUERY, new Object[] {object.getId()});
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
	
		try(Connection connection = dao.getConnection()) {

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
		
		try(Connection connection = dao.getConnection()) {

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
		
		try(Connection connection = dao.getConnection()) {

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
		
		try(Connection connection = dao.getConnection()) {
			PreparedStatement statement;
			
			if(page.getSearch() == null && page.getOrder() == null) {
				statement = connection.prepareStatement(PAGE_QUERY);				
				statement.setInt(1, page.getPageSize());
				statement.setInt(2, page.getOffSet());
			}else if(page.getOrder() != null){
				statement = connection.prepareStatement(requestSortBuilder(page).toString());
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
	
	private StringBuilder requestSortBuilder(Pagination page) {
		StringBuilder sortRequest =  new StringBuilder();
		if(page.getSearch() == null) {
			sortRequest.append("SELECT computer.id,computer.name,introduced,discontinued,company_id,company.name FROM computer "
					+ "LEFT JOIN company ON computer.company_id = company.id ORDER BY ");
			sortRequest.append(page.getOrder());
			sortRequest.append(" ");
			sortRequest.append(page.getSort());
			sortRequest.append(" LIMIT ");
			sortRequest.append(page.getPageSize());
			sortRequest.append(" OFFSET ");
			sortRequest.append(page.getOffSet());
		}else {
			sortRequest.append("SELECT computer.id,computer.name,introduced,discontinued,company_id,company.name FROM computer "
					+ "LEFT JOIN company ON computer.company_id = company.id WHERE computer.name LIKE '");
			sortRequest.append(page.getSearch());
			sortRequest.append("%' OR company.name LIKE '");
			sortRequest.append(page.getSearch());
			sortRequest.append("%' ORDER BY ");
			sortRequest.append(page.getOrder());
			sortRequest.append(" ");
			sortRequest.append(page.getSort());
			sortRequest.append(" LIMIT ");
			sortRequest.append(page.getPageSize());
			sortRequest.append(" OFFSET ");
			sortRequest.append(+page.getOffSet());
		}
		
		return sortRequest;
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

	@Override
	public Computer mapRow(ResultSet resultSet, int rowNum) throws SQLException {
		
		Computer computer = new Computer();
		
		computer.setId(resultSet.getInt(ID));
		computer.setName(resultSet.getString(NAME));
		
		Timestamp timesTamp = resultSet.getTimestamp(IN);
		
		computer.setIn(convertTimestampToLocalDate(timesTamp));
		
		timesTamp = resultSet.getTimestamp(OUT);
		
		computer.setOut(convertTimestampToLocalDate(timesTamp));
		computer.setCompany(Optional.of(new Company(resultSet.getInt(COMPANY_ID),resultSet.getString(COMPANY_NAME))));
		
		return computer;
	}
}
