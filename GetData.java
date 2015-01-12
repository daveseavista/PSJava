package jasonTrial;

import jasonTrial.CreateJson;
import jasonTrial.DataClasses.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;



public class GetData {
	

	public static void main(String[] args) {
		
		GetDatabaseSettings set = new GetDatabaseSettings();
		DbConnect connectData = new DbConnect();
		
		connectData =set.GetSettings();
				
		String FileName =null;
		Connection con=null;
		
		Database database = new Database();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");

        try //try connection to database
        {
            //load driver
        	
        	String Login = connectData.Login;
        	String Password = connectData.Password;
        	String ServiceName = connectData.ServiceName;
        	String Port = connectData.Port;
        	String UserName = connectData.UserName;
        	
        	        	
            Class.forName("oracle.jdbc.OracleDriver");
            System.out.println("Oracle JDBC driver loaded ok.");
            con=DriverManager.getConnection("jdbc:oracle:thin:" + Login + "/" + Password +"@" + ServiceName + ":" + Port +":" + UserName);
            
            System.out.println("Connectd");
            
            //declaring statement
            Statement stmt = con.createStatement();
            Statement stmt1 = con.createStatement();
            Statement stmt2 = con.createStatement();
            
           //Get all the id's
            String selectIDs="SELECT  MODULE_SK FROM MTD_DEV.OM_ETL_MODULE";
  
            //create a result set
            ResultSet rows = stmt.executeQuery(selectIDs);
           
            CreateJson createJson = new CreateJson();
            
           
     
            while (rows.next()) {
                              
                int module_id = rows.getInt("MODULE_SK");
                
                String SelectFixedData="SELECT MODULE_SK, DATAFLOW_NAME,MODULE_NAME,MODULE_SOFTWARE_VERS, COMMIT_THRESHOLD,  MODULE_RUN_STATUS_SK,  MODULE_STOP_IND,  DWH_START_DATE,  DWH_END_DATE,  DWH_VERS FROM MTD_DEV.OM_ETL_MODULE where MODULE_SK = " + module_id;
                
                ResultSet rows2 = stmt1.executeQuery(SelectFixedData);
                
                DbData dbdata = new DbData();
                

                while (rows2.next()) {
                		FixedData fixeddata = new FixedData();
                	                               	
	                	fixeddata.MODULE_SK = rows2.getInt("MODULE_SK");
	                	fixeddata.DATAFLOW_NAME = rows2.getString("DATAFLOW_NAME");
	                	fixeddata.MODULE_NAME = rows2.getString("MODULE_NAME");
	                	fixeddata.MODULE_SOFTWARE_VERS = rows2.getString("MODULE_SOFTWARE_VERS");
	                	fixeddata.COMMIT_THRESHOLD = rows2.getInt("COMMIT_THRESHOLD");
	                	fixeddata.MODULE_RUN_STATUS_SK= rows2.getInt("MODULE_RUN_STATUS_SK");
	                	fixeddata.MODULE_STOP_IND = rows2.getString("MODULE_STOP_IND");
	                	fixeddata.DWH_START_DATE = rows2.getDate("DWH_START_DATE");
	                	fixeddata.DWH_END_DATE = rows2.getDate("DWH_END_DATE");
	                	fixeddata.DWH_VERS = rows2.getInt("DWH_VERS");
	                	
	                	Date date = new Date();
	                	FileName = rows2.getString("MODULE_NAME") + "_" + dateFormat.format(date);
	                	dbdata.FileName =FileName;
	                	dbdata.ModuleName = rows2.getString("MODULE_NAME");
	                	
                
		                String SelectrepeatedData="SELECT MODULE_RUN_SK, MODULE_SK, MODULE_RUN_STATUS_SK, START_DATE, COMPLETE_DATE, ROWS_READ, ROWS_WRITTEN, CONTEXT_VERS, UPD_DATE FROM MTD_DEV.OM_ETL_MODULE_RUN where MODULE_SK = " + module_id;
		                
		                //System.out.println(SelectrepeatedData);
		                
		                ResultSet rows3 = stmt2.executeQuery(SelectrepeatedData);
		                
		                
		
		                while (rows3.next()) {
		                	               	
		                	ChildData childData = new ChildData();
		                	
		                	childData.MODULE_RUN_SK =rows3.getInt("MODULE_RUN_SK");
		                	childData.MODULE_SK =rows3.getInt("MODULE_SK");
		                	childData.MODULE_RUN_STATUS_SK =rows3.getInt("MODULE_RUN_STATUS_SK");
		                	childData.START_DATE = rows3.getDate("START_DATE");
		                	childData.COMPLETE_DATE = rows3.getDate("COMPLETE_DATE");
		                	childData.ROWS_READ =rows3.getInt("ROWS_READ");
		                	childData.ROWS_WRITTEN =rows3.getInt("ROWS_WRITTEN");
		                	childData.CONTEXT_VERS =rows3.getString("CONTEXT_VERS");
		                	
		                	//System.out.println(rows3.getInt("MODULE_SK"));
		                            	
		                 	fixeddata.childData.add(childData);
		                	childData = null;
		                } 
		                
		                database.dbdata.add(dbdata);
		                createJson.WriteJsonFile(fixeddata, FileName);
		                fixeddata = null;	
		                dbdata =null;
                }
            }
            
            createJson.WriteJsonDBFile(database);
            con.close();

        }
                catch (Exception e)
                {
                    System.err.println("Exception:"+e.getMessage());
                }


        }

}
