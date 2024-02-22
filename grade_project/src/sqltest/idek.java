package sqltest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
public class idek {
	static int id=0;
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con=DriverManager 
				.getConnection("jdbc:mysql://localhost:3306/grade_info","admin","admin");
		
		
		
        
		int initial;
		
		 
		Scanner in = new Scanner(System.in);
		System.out.println("If you are a new user, Type '1', if you are a existing user type '2'");
		initial = in.nextInt();
		if (initial == 1) {
		newUser();
		}
		else if(initial == 2) {
		login();
		}
		}
		static void newUser() throws ClassNotFoundException, SQLException {
			Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con=DriverManager
				.getConnection("jdbc:mysql://localhost:3306/grade_info","admin","admin");
		Statement stmt = con.createStatement();
		String q3 = ("SELECT * FROM id");
		ResultSet rs = stmt.executeQuery(q3); 
		if (rs.next()) {
		id = rs.getInt("id");
		} 
		
		int id2 = id+1;
		String q2 = ("UPDATE id SET id = '" + id2 + "'");
		int y = stmt.executeUpdate(q2);
		
		if (y > 0)            
            System.out.println("ID BASE RESTORED"+"id2:"+id2+"id:"+id);            
		else           
            System.out.println("ERROR OCCURRED :(");
		
		String username, password;
		Scanner in = new Scanner(System.in);
		System.out.println("Insert a username");
		username = in.next();
		System.out.println("Insert a password");
		password = in.next();
		System.out.println("Insert a first name");
		String fname = in.next();
		System.out.println("Insert a last name");
		String lname = in.next();
		
		
		String q1 = "insert into student_info values('" +fname+ "', '" +lname+ 
                "','"+ username+ "', '" +password+ "', '" +id+ "')";
		 int x = stmt.executeUpdate(q1);
         if (x > 0)            
             System.out.println("User Created");            
         else           
             System.out.println("user Creation failed");
          
         con.close();
		main(null);
	      
		}
		static void login() throws ClassNotFoundException, SQLException {
			Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con=DriverManager
				.getConnection("jdbc:mysql://localhost:3306/grade_info","admin","admin");
		
		Statement stmt = con.createStatement();
		
		//counter for student i
		String username, password,usertest = "k",passtest= "k";
		int stuid = 0;
		Scanner in = new Scanner(System.in);
		System.out.println("Insert a username");
		username = in.next();
		System.out.println("Insert a password");
		password = in.next();
		int i=0;
		String q1 = ("SELECT * FROM student_info;");
		ResultSet rs = stmt.executeQuery(q1);
		
    		while(rs.next() && i==0) {
    		 usertest = rs.getString("username");
    		 passtest = rs.getString("password");
    		 stuid = rs.getInt("studentid");
		
    		
		if (password.equals(passtest) && username.equals(usertest)) {
			System.out.println("Succesfull Login");
			i=1;
			
		}
		else if (!password.equals(passtest) && !username.equals(usertest)) {
			System.out.println("Searching...");
		}
		
		
    		}
    	if (i==1) {
    		nextStep(stuid);
    	}
    	else if (i==0) {
    		login();
    		System.out.println("Wrong Username or Password");
    	}
    		
		
         con.close();
		
         
	      
		}
		static void nextStep(int stuid) throws ClassNotFoundException, SQLException {
		System.out.println("Would you like to 1:view your grades, 2:add a class grade or 3:log out");
		int initial;
		Scanner in = new Scanner(System.in);
		initial = in.nextInt();
		if (initial == 1) {
			viewGrade(stuid);
		}
		else if (initial == 2) {
			newClass(stuid);
		}
		else if (initial == 3) {
			main(null);
		}
	
}
		static void newClass(int stuid) throws ClassNotFoundException, SQLException {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con=DriverManager
					.getConnection("jdbc:mysql://localhost:3306/grade_info","admin","admin");
			Statement stmt = con.createStatement(); 
			Scanner in = new Scanner(System.in);
			String classname, initial;
			System.out.println("input Class name and grade with the syntax so as 'classname = grade'");
			classname = in.next();
			
			
			String q1 = "insert into grades values('" +classname+ "','"+ stuid + "')";
			 int x = stmt.executeUpdate(q1);
	         if (x > 0)            
	             System.out.println("Class Created");            
	         else           
	             System.out.println("Class Creation failed");
	         System.out.println("would you like to logout or go back to the main menu"
	         		+ "please say 'menu' or 'logout'");
	         initial = in.next();
	         if (initial.equals("menu")) {
	        	 nextStep(stuid);
	         }
	         else if (initial.equals("logout")) {
	        	 main(null);
	         }
			
			con.close();
	}
		static void viewGrade(int stuid) throws ClassNotFoundException, SQLException {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con=DriverManager
					.getConnection("jdbc:mysql://localhost:3306/grade_info","admin","admin");
			Statement stmt = con.createStatement(); 
			String classname = "k";
			
			String q1 = ("SELECT * FROM grades WHERE stuid ="+stuid+";");
			ResultSet rs = stmt.executeQuery(q1);
	    		while(rs.next()) {
	    		 classname = rs.getString("classname");
	    		
	    		System.out.println("Classes :"+classname);}
				
			nextStep(stuid);
			
			
			con.close();
	}
		
}


