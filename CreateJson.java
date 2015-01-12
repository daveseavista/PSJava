package jasonTrial;
import jasonTrial.DataClasses.Database;
import jasonTrial.DataClasses.FixedData;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.*;

public class CreateJson {
	
	public void WriteFixedData(FixedData fixeddata) {
	
	GsonBuilder builder = new GsonBuilder();
	Gson gson = builder.create();
	System.out.println(gson.toJson(fixeddata));

}
	
	public void WriteJsonFile(FixedData fixeddata, String FileName) {
		
	GsonBuilder builder = new GsonBuilder();
	Gson gson = builder.create();
		
	
	FileWriter file = null;
	
	
	    try {
	    	file = new FileWriter("C:\\Users\\cs767dj\\Desktop\\People System\\JsonFiles\\" + FileName + ".json");
	        file.write(gson.toJson(fixeddata)); 
	        System.out.println("Successfully Copied JSON Object to File...");
	
	    } catch (IOException e) {
	        e.printStackTrace();
	
	    } finally {
	        try {
				file.flush();
				file.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
	        
	    }

	}
	
	public void WriteJsonDBFile(Database database) {
		
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
					
		FileWriter file = null;
		
		
		    try {
		    	file = new FileWriter("C:\\Users\\cs767dj\\Desktop\\People System\\JsonDB\\PSDB.json");
		        file.write(gson.toJson(database)); 
		        System.out.println("Successfully Copied JSON database to File...");
		
		    } catch (IOException e) {
		        e.printStackTrace();
		
		    } finally {
		        try {
					file.flush();
					file.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		        
		    }

		}
}

