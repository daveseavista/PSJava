package jasonTrial;


import java.io.BufferedReader;
import jasonTrial.DataClasses.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;


public class GetDatabaseSettings {
	
	
	public DbConnect GetSettings() {
		
		DbConnect dbConnect = new DbConnect();
	 
		

	    try {
	    	FileInputStream  file = new FileInputStream ("C:\\CONTEXT\\PSSettings.txt");
		 	
		 	BufferedReader br = new BufferedReader(new InputStreamReader(file));

	    	      
		 	String Rdline =null;
	      
	      // dis.available() returns 0 if the file does not have more lines.
	      while ((Rdline = br.readLine()) != null) {
	    	  String[] PramVal = Rdline.split(",");
	    	  
	    	  if (PramVal[0].equals("Login")) {
	    		  dbConnect.Login = PramVal[1];
	    	  } else if (PramVal[0].equals("Password")) {
	    		  dbConnect.Password = PramVal[1];
	    	  } else if (PramVal[0].equals("ServiceName")) {
	    		  dbConnect.ServiceName = PramVal[1];
	    	  } else if (PramVal[0].equals("Port")) {
	    		  dbConnect.Port = PramVal[1];
	    	  } else if (PramVal[0].equals("UserName")) {
	    		  dbConnect.UserName = PramVal[1];
	    	  } 
	      }
	      br.close();
	      return dbConnect;

	    } catch (FileNotFoundException e) {
	      e.printStackTrace();
	      return null;
	    } catch (IOException e) {
	      e.printStackTrace();
	      return null;
	    }
	  }

}
