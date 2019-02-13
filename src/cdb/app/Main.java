package cdb.app;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Scanner;

import cdb.persistance.*;

public class Main {

	public static void main(String[] args) throws SQLException, ParseException{

		
		boolean loop = true;
		
		Scanner sc = new Scanner(System.in);
		ComputorOperator compOp = new ComputorOperator();
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
							System.out.println("Cet ordinateur n'est pas référencé");
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
							System.out.println("Choix non reconnu");
						}
						break;
					case "5": 
							System.out.println("Aurevoir");
							loop = false;
						break;
					default :
						System.out.println("Selectionnez entre 1 & 5");					
						break;
				}
			
		}while(loop);
		sc.close();
	}

}
