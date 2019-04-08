package fr.excilys.cdb.view;

//import org.springframework.security.crypto.bcrypt.BCrypt;

public class Start {
	
//	private static int workload = 12;

	
	public static void main(String[] args) {
		
//		String test_passwd = "123";
//		String hash_password = hashPassword(test_passwd);
//		System.out.println(hash_password);
		
		View.getInstance().run();
		
	}
	
//	public static String hashPassword(String password_plaintext) {
//		String salt = BCrypt.gensalt(workload);
//		String hashed_password = BCrypt.hashpw(password_plaintext, salt);
//
//		return(hashed_password);
//	}

}
