package SDET1611.testingframework;

/**
 * Holds properties for each thread to run the right drivers.
 * Method getDriver is synchronized to prevent multi-thread errors
 *
 */
public class PropObj { 
	public static String dataFilePath;
	public static String keywordFilePath;
	public static String propertiesFilePath;
	public static String keywordSheetNames;
	public static String dataSheetNames;
	public static String OS;
	public static String bit;
	
	public static String[] drivers;
	private static int count = 0;
	public synchronized static String getDriver(){
		return drivers[count++].toUpperCase();
	}
}
