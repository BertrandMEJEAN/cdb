package cdb.app;

import java.sql.SQLException;
import cdb.persistance.*;

public class Main {

	public static void main(String[] args) throws SQLException{
		
		DataBaseConnexion connection = new DataBaseConnexion();
		
		connection.connector();
		for(Company element : connection.getList()){
			System.out.println(element.getId()+" "+element.getName());
		}

	}

}
