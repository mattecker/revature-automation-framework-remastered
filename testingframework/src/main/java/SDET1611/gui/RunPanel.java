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
	static private boolean operaCheckboxValue;
	
	public RunPanel() {
		JButton runTestButton = new JButton("Run Test");
		add(runTestButton);
		//runTestButton.addActionListener(new runTestListener());
		runTestButton.addActionListener(this);
	}
	
	public void setKeywordExcelFile(File file) {
		keywordExcelFile = file;
	}
	
	public void setDataExcelFile(File file) {
		dataExcelFile = file;
	}
	
	public void setPropertiesFile(File file) {
		propertiesFile = file;
	}
	
	public File getKeywordExcelFile() {
		return keywordExcelFile;
	}
	
	public File getDataExcelFile() {
		return dataExcelFile;
	}
	
	public File getPropertiesFile() {
		return propertiesFile;
	}
	
	public void setChromeCheckValue(boolean isChecked) {
		System.out.println("DEBUG--setCheckboxValue " + isChecked);
		chromeCheckboxValue = isChecked;
	}
	
	public void setIECheckValue(boolean isChecked) {
		chromeCheckboxValue = isChecked; 
	}
	
	public void setFirefoxCheckValue(boolean isChecked) {
		chromeCheckboxValue = isChecked; 
	}
	
	public void setOperaCheckValue(boolean isChecked) {
		chromeCheckboxValue = isChecked; 
	}
	
	public boolean getChromeCheckboxValue() {
		return chromeCheckboxValue;
	}
	
	public boolean getIECheckboxValue() {
		return ieCheckboxValue;
	}
	
	public boolean getFirefoxCheckboxValue() {
		return firefoxCheckboxValue;
	}
	
	public boolean getOperaCheckValue() {
		return operaCheckboxValue;
	}
	
	// Run Test action
	public void actionPerformed(ActionEvent e) {
		
		keywordExcelFile = getKeywordExcelFile();
		dataExcelFile = getDataExcelFile();
		propertiesFile = getPropertiesFile();
		
		String keywordSheet = "Sheet2";
		String dataSheet = "TestSheet";
		String OS = "WINDOWS";
		String bit = "64";
		
		chromeCheckboxValue = getChromeCheckboxValue();
		ieCheckboxValue = getIECheckboxValue();
		firefoxCheckboxValue = getFirefoxCheckboxValue();
		operaCheckboxValue = getOperaCheckValue();
		
		String drivers = "";
		
		if(chromeCheckboxValue)
			drivers+= "Chrome,";
		
		if(ieCheckboxValue)
			drivers+="IE,";
		
		if(firefoxCheckboxValue)
			drivers+="Firefox,";
		
		if(operaCheckboxValue)
			drivers+="Opera,";
		
		
		drivers=drivers.substring(0, drivers.lastIndexOf(","));
		
		prepareTests.main(new String[] {
					dataExcelFile.toString().replace("\\", "/"),
					//"C:/Users/Joe/Documents/Newfolder/testData.xlsx",
					//"C:/Users/Joe/Documents/Newfolder/testKeyword.xlsx",
					//"C:/Users/Joe/Documents/Newfolder/test.properties",
					keywordExcelFile.toString().replace("\\", "/"),
					propertiesFile.toString().replace("\\", "/"),
					keywordSheet,
					dataSheet,
					OS,
					bit,
					drivers.toString()
		});
		
		System.out.println("DEBUG--chromeCheckboxValue " + chromeCheckboxValue);
		
	}
}


	
	

