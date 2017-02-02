package SDET1611.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import SDET1611.testingframework.prepareTests;

public class RunPanel extends JPanel implements ActionListener {
	
	static private File keywordExcelFile;
	static private File dataExcelFile;
	static private File propertiesFile;
	
	static private boolean chromeCheckboxValue;
	static private boolean ieCheckboxValue;
	static private boolean firefoxCheckboxValue;
//	static private boolean operaCheckboxValue;
	
	static private String keywordSheetText;
	static private String dataSheetText;
	
	public RunPanel() {
		
		JButton runTestButton = new JButton("Run Test");
		add(runTestButton);
		runTestButton.addActionListener(this);
	}
	
	//Keyword file and sheet methods
	public void setKeywordExcelFile(File file) {
		keywordExcelFile = file;
	}
	public File getKeywordExcelFile() {
		return keywordExcelFile;
	}
	public void setKeywordSheetText(String kst) {
		keywordSheetText = kst;
	}
	public String getKeywordSheetText() {
		return keywordSheetText;
	}
	
	// Data file and sheet methods
	public void setDataExcelFile(File file) {
		dataExcelFile = file;
	}
	public File getDataExcelFile() {
		return dataExcelFile;
	}
	public void setDataSheetText(String dst) {
		dataSheetText = dst;
	}
	public String getDataSheetText() {
		return dataSheetText;
	}
	
	//Property file methods
	public void setPropertiesFile(File file) {
		propertiesFile = file;
	}
	public File getPropertiesFile() {
		return propertiesFile;
	}
	
	//Check box methods
	public void setChromeCheckValue(boolean isChecked) {
		chromeCheckboxValue = isChecked;
	}
	public boolean getChromeCheckboxValue() {
		return chromeCheckboxValue;
	}
	public void setIECheckValue(boolean isChecked) {
		ieCheckboxValue = isChecked; 
	}
	public boolean getIECheckboxValue() {
		return ieCheckboxValue;
	}
	public void setFirefoxCheckValue(boolean isChecked) {
		firefoxCheckboxValue = isChecked; 
	}
	public boolean getFirefoxCheckboxValue() {
		return firefoxCheckboxValue;
	}
//	public void setOperaCheckValue(boolean isChecked) {
//		operaCheckboxValue = isChecked; 
//	}
//	public boolean getOperaCheckValue() {
//		return operaCheckboxValue;
//	}
	
	// Run Test action
	public void actionPerformed(ActionEvent e) {
		
		String OS;
		keywordExcelFile = getKeywordExcelFile();
		dataExcelFile = getDataExcelFile();
		propertiesFile = getPropertiesFile();
		
		String keywordSheet = getKeywordSheetText();
		String dataSheet = getDataSheetText();
		
		String temp = System.getProperty("os.name");
		
		if(temp.contains("Windows")){
			OS = "WINDOWS";
		}else if(temp.contains("Mac")){
			OS = "MAC";
		}else{
			OS = "LINUX";
		}
		
		String bit = "64";
		
		chromeCheckboxValue = getChromeCheckboxValue();
		ieCheckboxValue = getIECheckboxValue();
		firefoxCheckboxValue = getFirefoxCheckboxValue();
//		operaCheckboxValue = getOperaCheckValue();
		
		String drivers = "";
		
		if(chromeCheckboxValue)
			drivers+= "Chrome,";
		
		if(ieCheckboxValue)
			drivers+="IE,";
		
		if(firefoxCheckboxValue)
			drivers+="Firefox,";
		
//		if(operaCheckboxValue)
//			drivers+="Opera,";
		
		
		System.out.println(chromeCheckboxValue);
		System.out.println(ieCheckboxValue);
		System.out.println(firefoxCheckboxValue);
//		System.out.println(operaCheckboxValue);
		
		System.out.println(drivers);
		
		drivers=drivers.substring(0, drivers.lastIndexOf(","));
		
		System.out.println(drivers);
		
		prepareTests.main(new String[] {
					dataExcelFile.toString().replace("\\", "/"),
					keywordExcelFile.toString().replace("\\", "/"),
					propertiesFile.toString().replace("\\", "/"),
					keywordSheet,
					dataSheet,
					OS,
					bit,
					drivers.toString()
		});
	}
}


	
	

