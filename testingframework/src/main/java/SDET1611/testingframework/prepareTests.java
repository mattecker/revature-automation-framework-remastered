package SDET1611.testingframework;

public class prepareTests {
	
    public static void main(String[] args) throws IllegalArgumentException{
    	String[] drivers = args[7].split(",");

    	if(drivers != null){
    		PropObj.dataFilePath = args[0];
    		PropObj.keywordFilePath = args[1];
    		PropObj.propertiesFilePath = args[2];
    		PropObj.keywordSheetNames = args[3];
    		PropObj.dataSheetNames = args[4];
    		PropObj.OS = args[5];
    		PropObj.bit = args[6];
    		PropObj.drivers = drivers;
    		for(int i = 0; i < drivers.length; i++){
		    	TestThread T = new TestThread( drivers[i]+" Thread" );
				T.start();
	    	}
	    	System.gc();
    	}else{
    		System.out.println("Incorrect Arguments for propObj");
    		throw new IllegalArgumentException();
    	}
      
    	
    }
}
