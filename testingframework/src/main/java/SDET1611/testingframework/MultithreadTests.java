package SDET1611.testingframework;

public class MultithreadTests {
    
	public static void runDrivers(String[] drivers) {
		for(int i = 0; i < drivers.length; i++){
	    	TestThread T = new TestThread( drivers[i]+" Thread" );
			T.start();
    	}
		System.gc();
	}
    
}
