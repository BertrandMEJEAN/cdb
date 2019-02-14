package cdb.app;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cdb.persistance.*;

public class Main {

	public static void main(String[] args) throws SQLException, ParseException{

		
		boolean loop = true;

		Scanner sc = new Scanner(System.in);
		ComputorOperator compOp = new ComputorOperator();
		Logger logger = LoggerFactory.getLogger(Main.class);
		String str;
		
		do{	
			
			System.out.println("Veuillez faire votre choix:\n"
					+ "1.Lister les ordinateurs\n"
					+ "2.Lister les entreprises\n"
					+ "3.Details d'un ordinateur\n"
					+ "4.Opération sur un ordinateur\n"
					+ "5.Sortir");
		
			str = sc.nextLine();
			
			switch (str) {
					case "1": 
						compOp.listAllComputer();
						break;
					case "2": 
						compOp.listAllCompany();
						break;
					case "3": 
						System.out.println("Veuillez rentrez l'identifiant de l'ordinateur voulu");
						str = sc.nextLine();
						ComputerDAO cmp = new ComputerDAO();
						if(cmp.existentById(Integer.parseInt(str)) == true) {
							System.out.println(cmp.getId(Integer.parseInt(str)).toString());
						}else {
							logger.info("L'ordinateur n'est pas présent en base\n");
						}
						break;
					case "4": 
						System.out.println("Quelles opérations souhaitez vous effectuez ?\n"
								+ "Ajouter un ordinateur(add)\n"
								+ "Mettre à jour un ordinateur(update)\n"
								+ "Supprimer un ordinateur(delete)\n");
						str = sc.nextLine();					
						
						if(str.equals("add")){
							compOp.addComputer();						
						}else if(str.equals("update")){
							compOp.updateComputer();
						}else if(str.equals("delete")) {
							compOp.deleteComputer();
						}else {
							logger.info("Operateur non reconnue");
						}
						break;
					case "5": 
							System.out.println("Aurevoir");
							loop = false;
						break;
					default :
						logger.info("Item non reconnu (1 à 5)");
						break;
				}
			
		}while(loop);
		sc.close();
	}

}
