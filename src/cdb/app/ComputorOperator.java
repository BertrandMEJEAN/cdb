package cdb.app;

import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cdb.model.Company;
import cdb.model.Computer;
import cdb.persistance.*;

/**
 * classe ComputorOperator, les opérations utilisateur et les transmet au package DAO. 
 * @author Bertrand Méjean.
 */
public class ComputorOperator {
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SS");
	Logger logger = LoggerFactory.getLogger(ComputorOperator.class);
	Scanner sc = new Scanner(System.in);
	
	/**
	 * Demande divers information à l'utilisateur pour créer un nouveau computer.
	 * @author Bertrand Méjean.
	 * @throws ParseException
	 */
	public void addComputer() throws ParseException{
		System.out.println("Entrer le nom de l'ordinateur:");
		String pName = sc.nextLine();
		
		System.out.println("Entrer la date d'insertion de l'ordinateur au format aaaa-MM-jj:");
		Timestamp timesTamp = null;
		do {
			String pIn = sc.nextLine();
			timesTamp = stringToDate(pIn);
		}while(timesTamp == null);
		
		System.out.println("Entrer la date d'extraction de l'ordinateur au format aaaa-MM-jj:");
		Timestamp timesTampScnd = null;
		
		do {
			String pOut = sc.nextLine();			
			timesTampScnd = stringToDate(pOut);				
		}while(timesTampScnd == null);
		
		System.out.println("Entrer l'id de la company liée à l'ordinateur:");
		CompanyDAO compyExist = new CompanyDAO();
		int pCompID = 0;
		
		do {
			pCompID = Integer.parseInt(sc.nextLine());					
		}while(compyExist.existentById(pCompID) == false);
		
		Computer cmp = new Computer(0,pName,timesTamp,timesTampScnd,pCompID);
		
		ComputerDAO addCmp = new ComputerDAO();
		addCmp.add(cmp);
	}
	
	/**
	 * Demande l'id d'un computer à l'utilisateur, vérifie sa présence en base de donnée et le supprime.
	 * @author Bertrand Méjean.
	 */
	public void deleteComputer(){
		ComputerDAO delComp = new ComputerDAO();
	
		System.out.println("Veuillez rentrer l'identifiant de l'ordinateur à supprimer:");
		int intIdComp;

		do{
			intIdComp = Integer.parseInt(sc.nextLine());
		}while((delComp.existentById(intIdComp) == false));
		
		delComp.deleteById(intIdComp);
	}
	
	/**
	 * Demande à un utilisateur de sélectionner un ordinateur afin d'y modifier les détails.
	 * @author Bertrand Méjean.
	 * @throws ParseException
	 */
	public void updateComputer() throws ParseException{
		ComputerDAO upComp = new ComputerDAO();
		
		System.out.println("Veuillez sélectionner l'ordinateur à modifier:");
		Computer compTemp = upComp.getId(Integer.parseInt(sc.nextLine()));
		
		System.out.println("Choisissez un nouveau nom :");
		compTemp.setName(sc.nextLine());
		
		System.out.println("Veuillez choisir une nouvelle date de sortie au format yyyy-MM-dd:");
		Timestamp timesTamp = null;
		do {
			String pIn = sc.nextLine();
			timesTamp = stringToDate(pIn);
		}while(timesTamp == null);
		
		System.out.println("Veuillez choisir un nouvel identifiant d'entreprise:");
		compTemp.setCompId(Integer.parseInt(sc.nextLine()));
		
		upComp.update(compTemp);
		
		if(upComp.update(compTemp) != null) {
			System.out.println("Modifications apportées");
		}		
	}
	
	/**
	 * Permet d'afficher une liste contenant touts les computers de la base de donnée.
	 * @author Bertrand Méjean.
	 */
	public void listAllComputer() {
		ComputerDAO allCpt = new ComputerDAO();
		for(Computer computer : allCpt.getAll()){
			System.out.println(computer.toString());
		}
	}
	
	/**
	 * Permet d'afficher une liste contenant toutes les entreprises de la base de donnée.
	 * @author Bertrand Méjean.
	 */
	public void listAllCompany() {
		CompanyDAO allCpy = new CompanyDAO();
		for(Company company: allCpy.getAll()){
			System.out.println(company.toString());
		}
	}
	
	/**
	 * Permet de convertir un String au format Timestamp.
	 * @author Bertrand Méjean.
	 * @param pString String à convertir.
	 * @return timestamp Valeur de type timestamp.
	 * @return null Si le parse génère une exception alors renvois null.
	 * @throws ParseException
	 */
	public Timestamp stringToDate(String pString) throws ParseException {			
			try {
				pString += " 0:0:0.0";
				Date parsedDate = (Date) dateFormat.parse(pString);
				Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
				return timestamp;
			}catch(ParseException e){
				logger.info("Le format de la date saisie est incorrect");
			}
			return null;
	}
}