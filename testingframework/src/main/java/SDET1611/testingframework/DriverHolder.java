package SDET1611.testingframework;

import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

/**
 * 
 * Class that allows other classes to get browser drivers from the drivers
 * folder.
 *
 */
public class DriverHolder {

	final private static String dir = "src" + File.separator + "main" + File.separator + "resources" + File.separator
			+ "Drivers" + File.separator;

	// Get Linux x32 Google Chrome Driver
	private static WebDriver getChromeDriverLinux32() {
		System.setProperty("webdriver.chrome.driver", dir + "Linux32chromedriver");
		WebDriver driver = new ChromeDriver();
		return driver;
	}

	// Get Windows Google Chrome Driver
	private static WebDriver getChromeDriverWindows() {
		System.setProperty("webdriver.chrome.driver", dir + "Windowschromedriver.exe");
		WebDriver driver = new ChromeDriver();
		return driver;
	}

	// Get Mac Google Chrome Driver
	private static WebDriver getChromeDriverMac() {
		System.setProperty("webdriver.chrome.driver", dir + "Macchromedriver");
		WebDriver driver = new ChromeDriver();
		return driver;
	}

	// Get Linux x64 Google Chrome Driver
	private static WebDriver getChromeDriverLinux64() {
		System.setProperty("webdriver.chrome.driver", dir + "Linux64chromedriver");
		WebDriver driver = new ChromeDriver();
		return driver;
	}

	// Get Linux x32 Mozilla Firefox Driver
	private static WebDriver getFirefoxDriverLinux32() {
		System.setProperty("webdriver.gecko.driver", dir + "Linux32geckodriver");
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	// Get Windows x32 Mozilla Firefox Driver
	private static WebDriver getFirefoxDriverWindows32() {
		System.setProperty("webdriver.gecko.driver", dir + "Windows32geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	// Get Windows x64 Mozilla Firefox Driver
	private static WebDriver getFirefoxDriverWindows64() {
		System.setProperty("webdriver.gecko.driver", dir + "Windows64geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	// Get Mac Mozilla Firefox Driver
	private static WebDriver getFirefoxDriverMac() {
		System.setProperty("webdriver.gecko.driver", dir + "Macgeckodriver");
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	// Get Linux X64 Mozilla Firefox Driver
	private static WebDriver getFirefoxDriverLinux64() {
		System.setProperty("webdriver.gecko.driver", dir + "Linux64geckodriver");
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	// Get x32 Microsoft Internet Explorer Driver
	private static WebDriver getIEDriver32() {
		System.setProperty("webdriver.ie.driver", dir + "IEDriverServer_32.exe");
		InternetExplorerDriver driver = new InternetExplorerDriver();
		return driver;
	}

	// Get x64 Microsoft Internet Explorer Driver
	private static WebDriver getIEDriver64() {
		System.setProperty("webdriver.ie.driver", dir + "IEDriverServer_64.exe");
		InternetExplorerDriver driver = new InternetExplorerDriver();
		return driver;
	}

	// Get the only Microsoft Edge Driver I could find
	private static WebDriver getEdgeDriver() {
		System.setProperty("webdriver.edge.driver", dir + "MicrosoftWebDriver.exe");
		EdgeDriver driver = new EdgeDriver();
		return driver;
	}

	// The Safari driver should already come installed with safari
	private static WebDriver getSafariDriver() {
		System.setProperty("webdriver.safari.driver", "/usr/bin/safaridriver");
		SafariDriver driver = new SafariDriver();
		return driver;
	}

	/**
	 * Allows a user to retrieve a driver determined by passed parameters
	 * 
	 * @param OS
	 *            The operating system
	 * @param driverString
	 *            The name of the requested driver
	 * @param bit
	 *            Either 32 or 64
	 * @return The requested driver
	 */
	public static WebDriver getDriver(String OS, String driverString, String bit) {
		switch (driverString) {
		case "CHROME":
			if (OS.equals("MAC")) {
				return DriverHolder.getChromeDriverMac();
			} else if (OS.equals("WINDOWS")) {
				return DriverHolder.getChromeDriverWindows();
			} else {// Linux
				if (bit.equals("64"))
					return DriverHolder.getChromeDriverLinux64();
				else // 32 bit
					return DriverHolder.getChromeDriverLinux32();
			}
		case "FIREFOX":
			if (OS.equals("MAC")) {
				return DriverHolder.getFirefoxDriverMac();
			} else if (OS.equals("WINDOWS")) {
				if (bit.equals("64"))
					return DriverHolder.getFirefoxDriverWindows64();
				else // 32 bit
					return DriverHolder.getFirefoxDriverWindows32();
			} else {// Linux
				if (bit.equals("64"))
					return DriverHolder.getFirefoxDriverLinux64();
				else // 32 bit
					return DriverHolder.getFirefoxDriverLinux32();
			}
		case "IE":
		case "INTERNET EXPLORER":
		case "INTERNETEXPLORER":
		case "INTERNET_EXPLORER":
			if (bit.equals("64"))
				return DriverHolder.getIEDriver64();
			else
				return DriverHolder.getIEDriver32();
		case "EDGE":
			return DriverHolder.getEdgeDriver();
		case "SAFARI":
			return getSafariDriver();
		default:
			return null;
		}
	}

}
