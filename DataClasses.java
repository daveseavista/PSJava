package jasonTrial;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

class DataClasses {
	
	static	class FixedData {
	
		 Integer MODULE_SK;
		 String DATAFLOW_NAME;
		 String MODULE_NAME;
		 String MODULE_SOFTWARE_VERS;
		 Integer COMMIT_THRESHOLD;
		 Integer MODULE_RUN_STATUS_SK;
		 String  MODULE_STOP_IND;
		 Date    DWH_START_DATE;
		 Date    DWH_END_DATE;
		 Integer DWH_VERS;
		 List<ChildData> childData = new ArrayList<ChildData>();
	}
	
	

	static class ChildData {
	
		 Integer MODULE_RUN_SK;
		 Integer MODULE_SK;
		 Integer MODULE_RUN_STATUS_SK;
		 Date START_DATE;
		 Date COMPLETE_DATE;
		 Integer ROWS_READ;
		 Integer ROWS_WRITTEN;
		 String CONTEXT_VERS;
	
	}
	
	static class Database {
		
		List<DbData> dbdata = new ArrayList<DbData>();	
	}
	
	static class DbData {
		String FileName;
		String ModuleName;	
	}
	
	static class DbConnect {
		String Login;
    	String Password;
    	String ServiceName;
    	String Port;
    	String UserName;
		
	}
}
