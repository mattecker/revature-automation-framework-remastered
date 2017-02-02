package SDET1611.testingframework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

public class DriverHolder {
	
	final private static String dir = "src/main/resources/Drivers/";
	
	private static WebDriver getChromeDriverLinux32(){
		System.setProperty("webdriver.chrome.driver",dir+"Linux32chromedriver");
		WebDriver driver = new ChromeDriver();
		return driver;
	}
	
	private static WebDriver getChromeDriverWindows(){
		System.setProperty("webdriver.chrome.driver",dir+"Windowschromedriver.exe");
		WebDriver driver = new ChromeDriver();
		return driver;
	}
	
	private static WebDriver getChromeDriverMac(){
		System.setProperty("webdriver.chrome.driver",dir+"Macchromedriver");
		WebDriver driver = new ChromeDriver();
		return driver;
	}
	
	private static WebDriver getChromeDriverLinux64(){
		System.setProperty("webdriver.chrome.driver",dir+"Linux64chromedriver");
		WebDriver driver = new ChromeDriver();
		return driver;
	}
	
	
	private static WebDriver getFirefoxDriverLinux32(){
		System.setProperty("webdriver.gecko.driver",dir+"Linux32geckodriver");
		WebDriver driver = new FirefoxDriver();
		return driver;
	}
	
	private static WebDriver getFirefoxDriverWindows32(){
		System.setProperty("webdriver.gecko.driver",dir+"Windows32geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		return driver;
	}
	
	private static WebDriver getFirefoxDriverWindows64(){
		System.setProperty("webdriver.gecko.driver",dir+"Windows64geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		return driver;
	}
	
	private static WebDriver getFirefoxDriverMac(){
		System.setProperty("webdriver.gecko.driver",dir+"Macgeckodriver");
		WebDriver driver = new FirefoxDriver();
		return driver;
	}
	
	private static WebDriver getFirefoxDriverLinux64(){
		System.setProperty("webdriver.gecko.driver",dir+"Linux64geckodriver");
		WebDriver driver = new FirefoxDriver();
		return driver;
	}
	
	private static WebDriver getOperaDriverLinux32(){
		System.setProperty("webdriver.Opera.driver",dir+"Linux32Operadriver");
		WebDriver driver = new OperaDriver();
		return driver;
	}
	
	private static WebDriver getOperaDriverWindows32(){
		System.setProperty("webdriver.Opera.driver",dir+"Windows32Operadriver.exe");
		WebDriver driver = new OperaDriver();
		return driver;
	}
	
	
	private static WebDriver getOperaDriverLinux64(){
		System.setProperty("webdriver.Opera.driver",dir+"Linux64Operadriver");
		WebDriver driver = new OperaDriver();
		return driver;
	}
	
	private static WebDriver getOperaDriverWindows64(){
		System.setProperty("webdriver.Opera.driver",dir+"Windows64Operadriver.exe");
		WebDriver driver = new OperaDriver();
		return driver;
	}
	
	private static WebDriver getOperaDriverMac(){
		System.setProperty("webdriver.Opera.driver",dir+"MacOperadriver");
		WebDriver driver = new OperaDriver();
		return driver;
	}
	
	private static WebDriver getIEDriver32(){
		System.setProperty("webdriver.ie.driver",dir+"IEDriverServer_32.exe");
		InternetExplorerDriver driver = new InternetExplorerDriver();
		return driver;
	}
	
	private static WebDriver getIEDriver64(){
		System.setProperty("webdriver.ie.driver",dir+"IEDriverServer_64.exe");
		InternetExplorerDriver driver = new InternetExplorerDriver();
		return driver;
	}
	
	private static WebDriver getEdgeDriver(){
		System.setProperty("webdriver.edge.driver",dir+"MicrosoftWebDriver.exe");
		EdgeDriver driver = new EdgeDriver();
		return driver;
	}
	private static WebDriver getSafariDriver(){
		System.setProperty("webdriver.safari.driver","/usr/bin/safaridriver");
		SafariDriver driver = new SafariDriver();
		return driver;
	}
	
	public static WebDriver getDriver(String OS, String driverString, String bit){
		switch(driverString){
	      case "CHROME":
	    	  if(OS.equals("MAC")){
	    		  return DriverHolder.getChromeDriverMac();
	    	  }
	    	  else if(OS.equals("WINDOWS")){
	    		  return DriverHolder.getChromeDriverWindows();
	    	  }
	    	  else{//Linux
	    		  if(bit.equals("64"))
	    			  return DriverHolder.getChromeDriverLinux64();
	    		  else // 32 bit
	    			  return DriverHolder.getChromeDriverLinux32();
	    	  }
	      case "FIREFOX":
	    	  if(OS.equals("MAC")){
	    		  return DriverHolder.getFirefoxDriverMac();
	    	  }
	    	  else if(OS.equals("WINDOWS")){
	    		  if(bit.equals("64"))
	    			  return DriverHolder.getFirefoxDriverWindows64();
	    		  else // 32 bit
	    			  return DriverHolder.getFirefoxDriverWindows32();
	    	  }
	    	  else{//Linux
	    		  if(bit.equals("64"))
	    			  return DriverHolder.getFirefoxDriverLinux64();
	    		  else // 32 bit
	    			  return DriverHolder.getFirefoxDriverLinux32();
	    	  }
	      case "IE":
	      case "INTERNET EXPLORER":
	      case "INTERNETEXPLORER":
	      case "INTERNET_EXPLORER":
	    	  if(bit.equals("64"))
	    		  return DriverHolder.getIEDriver64();
	    	  else
	    		  return DriverHolder.getIEDriver32();
	      case "EDGE":
	    	  return DriverHolder.getEdgeDriver();
	      case "OPERA":
	    	  if(OS.equals("MAC")){
	    		  return DriverHolder.getOperaDriverMac();
	    	  }
	    	  else if(OS.equals("WINDOWS")){
	    		  if(bit.equals("64"))
	    			  return DriverHolder.getOperaDriverWindows64();
	    		  else // 32 bit
	    			  return DriverHolder.getOperaDriverWindows32();
	    	  }
	    	  else{//Linux
	    		  if(bit.equals("64"))
	    			  return DriverHolder.getOperaDriverLinux64();
	    		  else // 32 bit
	    			  return DriverHolder.getOperaDriverLinux32();
	    	  }
	      case "SAFARI":
	    	  	return getSafariDriver();
	      default:
	    	  return null;  
	      }
	}
	
}
