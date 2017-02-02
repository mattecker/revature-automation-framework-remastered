package SDET1611.testingframework;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Properties;

public class prepareTests {
	
    public static void main(String[] args){
    	String dataFilePath = args[0];
    	String keywordFilePath = args[1];
    	String propertiesFilePath = args[2];
    	String keywordSheetNames = args[3];
    	String dataSheetNames = args[4];
    	String OS = args[5];
    	String bit = args[6];
    	String[] drivers = args[7].split(",");
    	
    	System.out.println(args[0]);
    	System.out.println(args[1]);
    	System.out.println(args[2]);
    	System.out.println(args[3]);
    	System.out.println(args[4]);
    	System.out.println(args[5]);
    	System.out.println(args[6]);
    	System.out.println(args[7]);
    	
    	if(drivers != null){
	    	Properties p = new Properties();
	    	File f = new File(System.getProperty("user.dir")+"/src/main/resources/tests.properties");
	    	
	    	for(int i = 0; i < drivers.length; i++){
	    		try{
	    	    	p.setProperty("OS", OS);
	    	    	p.setProperty("bit", bit);
	    			p.setProperty("dataFilePath", dataFilePath);
	    			p.setProperty("keywordFilePath", keywordFilePath);
	    			p.setProperty("propertiesFilePath", propertiesFilePath);
	    			p.setProperty("keywordSheetNames", keywordSheetNames);
	    			p.setProperty("dataSheetNames", dataSheetNames);
		    		p.setProperty("driver", drivers[i].toUpperCase());
		    		
		    		p.store(new PrintWriter(f), null);
		    		p.clear();
		    		
		    		TestThread T = new TestThread( drivers[i]+" Thread" );
					T.start();
		    		try{
		    			Thread.sleep(800);
		    		}catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    		}catch(Exception e){
	    			System.out.println(drivers[i]+" is not installed or cannot be found.");
	    		}
	    	} 
	    	
	    	System.gc();
    	}else{
    		System.out.println("Incorrect Arguments");
    		throw new IllegalArgumentException();
    	}
      
    	
    }
}
