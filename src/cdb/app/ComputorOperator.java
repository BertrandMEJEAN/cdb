package cdb.app;

import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import cdb.model.Company;
import cdb.model.Computer;
import cdb.persistance.*;

public class ComputorOperator {
	
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SS");
	Scanner sc = new Scanner(System.in);
	
	public void addComputer() throws ParseException{
		System.out.println("Entrer le nom de l'ordinateur:");
		String pName = sc.nextLine();
		
		System.out.println("Entrer la date d'insertion de l'ordinateur au format aaaa-MM-jj:");
		String pIn = sc.nextLine();
		
		pIn += " 0:0:0.0";
		Date parsedDate = (Date) dateFormat.parse(pIn);
		Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		System.out.println(timestamp.toString());
		
		System.out.println("Entrer la date d'extraction de l'ordinateur au format aaaa-MM-jj:");
		String pOut = sc.nextLine();
		
		pOut += " 0:0:0.0";
		Date parsedDate2 = (Date) dateFormat.parse(pOut);
		Timestamp timestamp2 = new java.sql.Timestamp(parsedDate2.getTime());
		System.out.println(timestamp2.toString());
		
		System.out.println("Entrer l'id de la company liée à l'ordinateur:");
		int pCompID = Integer.parseInt(sc.next());
		
		Computer cmp = new Computer(0,pName,timestamp,timestamp2,pCompID);
		
		ComputerDAO addCmp = new ComputerDAO();
		addCmp.add(cmp);
		if(cmp != null) {
			System.out.println("L'ordinateur est ajouté à la base");
		}
	}
	
	public void deleteComputer(){
		ComputerDAO delComp = new ComputerDAO();
	
		System.out.println("Veuillez rentrer l'identifiant de l'ordinateur à supprimer:");
		int idComp = Integer.parseInt(sc.nextLine());
		
		if(delComp.existentById(idComp) == true){

			if(delComp.deleteById(idComp) == true){
				System.out.println("Supression de l'ordinateur éffectuée");
			}			
		}					
	}
	
	public void updateComputer() throws ParseException{
		ComputerDAO upComp = new ComputerDAO();
		
		System.out.println("Veuillez sélectionner l'ordinateur à modifier:");
		Computer compTemp = upComp.getId(Integer.parseInt(sc.nextLine()));
		
		System.out.println("Choisissez un nouveau nom :");
		compTemp.setName(sc.nextLine());
		
		System.out.println("Veuillez choisir une nouvelle date de sortie au format yyyy-MM-dd:");
		String pIn = sc.nextLine();		
		pIn += " 0:0:0.0";
		Date parsedDate = (Date) dateFormat.parse(pIn);
		Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
		compTemp.setOut(timestamp);
		
		System.out.println("Veuillez choisir un nouvel identifiant d'entreprise:");
		compTemp.setCompId(Integer.parseInt(sc.nextLine()));
		
		upComp.update(compTemp);
		
		if(upComp.update(compTemp) != null) {
			System.out.println("Modifications apportées");
		}		
	}
	
	public void listAllComputer() {
		ComputerDAO allCpt = new ComputerDAO();
		for(Computer computer : allCpt.getAll()){
			System.out.println(computer.toString());
		}
	}
	
	public void listAllCompany() {
		CompanyDAO allCpy = new CompanyDAO();
		for(Company company: allCpy.getAll()){
			System.out.println(company.toString());
		}
	}
}