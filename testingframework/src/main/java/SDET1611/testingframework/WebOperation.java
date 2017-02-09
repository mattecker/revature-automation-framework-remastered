package SDET1611.testingframework;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class WebOperation {

	WebDriver driver;

	public WebOperation(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * Performs a single action determined by passed parameters
	 * 
	 * @param operation
	 *            The name of the operation to be performed
	 * @param objectName
	 *            The name of the web element being interacted with
	 * @param objectType
	 *            The type of the web element being interacted with
	 * @param value
	 *            Additional data to be used depending on the operation / object
	 *            type
	 * @throws InvalidObjectSelectorException
	 */
	public boolean action(String operation, String objectName, String objectType, String value)
			throws InvalidObjectSelectorException {
		boolean testbool = false;
		WebElement element;

		if (operation == null) {
			operation = "";
		} // protect from null pointers
		else {
			/*
			 * This switch statement executes a webDriver operation depending on
			 * the keyword column in the keyword file or Gherkin feature file.
			 */
			switch (operation.toUpperCase()) {
			case "CLICK":
				element = driver.findElement(this.getObject(objectType, objectName));
				if (element != null) {
					element.click();
					testbool = true;
				}
				break;
			case "SELECTBUTTON":
			case "SELECT BUTTON":
			case "SELECT_BUTTON":
			case "SELECT DROPDOWN":
			case "SELECTDROPDOWN":
			case "SELECT_DROPDOWN":
				element = driver.findElement(this.getObject(objectType, objectName));
				if (element != null) {
					Select select = new Select(element);
					select.selectByValue(value);
					testbool = true;
				}
				break;
			case "DESELECT DROPDOWN":
			case "DESELECTDROPDOWN":
			case "DESELECT_DROPDOWN":
				element = driver.findElement(this.getObject(objectType, objectName));
				if (element != null) {
					Select select = new Select(element);
					select.deselectByValue(value);
					testbool = true;
				}
				break;
			case "DESELECT ALL":
			case "DESELECTALL":
			case "DESELECT_ALL":
				element = driver.findElement(this.getObject(objectType, objectName));
				if (element != null) {
					Select select = new Select(element);
					select.deselectAll();
					testbool = true;
				}
				break;
			case "SELECT RADIO":
			case "SELECTRADIO":
			case "SELECT_RADIO":
			case "SELECT CHECKBOX":
			case "SELECTCHECKBOX":
			case "SELECT_CHECKBOX":
				ArrayList<WebElement> list = (ArrayList<WebElement>) driver
						.findElements(this.getObject(objectType, objectName));
				if (list != null) {
					for (WebElement w : list) {
						if (w.getAttribute("value") == value) {
							w.click();
						}
					}
					testbool = true;
				}
				break;
			case "DESELECT CHECKBOX":
			case "DESELECTCHECKBOX":
			case "DESELECT_CHECKBOX":
				ArrayList<WebElement> olist = (ArrayList<WebElement>) driver
						.findElements(this.getObject(objectType, objectName));
				if (olist != null) {
					if (olist.get(olist.indexOf(value)).isSelected())
						olist.get(olist.indexOf(value)).click();
					// else already not selected
					testbool = true;
				}
				break;
			case "INPUT TEXT":
			case "INPUT_TEXT":
			case "INPUTTEXT":
			case "INPUTS":
			case "TYPES":
			case "WRITES":
				element = driver.findElement(this.getObject(objectType, objectName));
				if (element != null) {
					element.sendKeys(value);
					testbool = true;
				}
				break;
			case "GO TO URL":
			case "GO_TO_URL":
			case "GOTOURL":
				if (driver != null) {
					driver.get(value);
					testbool = true;
				}
				break;
			case "CHECK TITLE":
			case "CHECK_TITLE":
			case "CHECKTITLE":
			case "GET TITLE":
			case "GET_TITLE":
			case "GETTITLE":
				if (driver != null) {
					String txt = driver.getTitle().trim();
					if (txt.equals(value.trim())) {
						testbool = true;
					}
				}
				break;
			case "CHECK URL":
			case "CHECK_URL":
			case "CHECKURL":
			case "GET URL":
			case "GET_URL":
			case "GETURL":
				if (driver != null) {
					String txt = driver.getCurrentUrl().trim();
					if (txt.contains(objectName.trim())) {
						testbool = true;
					}
				}
				break;
			case "GET TEXT":
			case "GET_TEXT":
			case "GETTEXT":
				element = driver.findElement(this.getObject(objectType, objectName));
				if (element != null) {
					String txt = element.getText();
					if ((txt.trim()).equals(value.trim())) {
						testbool = true;
					}
				} else {
					System.out.println("DEBUG---cant find dat object : " + objectType + " " + objectName);
				}
				break;
			default:
				// TODO : Unrecognised / unimplemented keyword
			}
		}
		return testbool;
	}

	/**
	 * Gets a web element determined by passed parameters
	 * 
	 * @param p
	 *            A properties object
	 * @param type
	 *            The type of selector to be used to find the element
	 * @param value
	 *            The name or other identifying aspect of the element
	 * @return The requested web element
	 * @throws InvalidObjectSelectorException
	 */
	private By getObject(String type, String value) throws InvalidObjectSelectorException {
		By toBeReturned = null;
		String str = type.toLowerCase();
		switch (str) {

		case "classname":
			toBeReturned = By.className(value);
			break;
		case "css":
			toBeReturned = By.cssSelector(value);
			break;
		case "id":
			toBeReturned = By.id(value);
			break;
		case "linktext":
			toBeReturned = By.linkText(value);
			break;
		case "name":
			toBeReturned = By.name(value);
			break;
		case "partiallink":
			toBeReturned = By.partialLinkText(value);
			break;
		case "tagname":
			toBeReturned = By.tagName(value);
			break;
		case "xpath":
			toBeReturned = By.xpath(value);
			break;
		case "csslocator":
			toBeReturned = By.cssSelector(value);
			break;
		default:
			throw (new InvalidObjectSelectorException(value));
		}

		return toBeReturned;
	}
}
