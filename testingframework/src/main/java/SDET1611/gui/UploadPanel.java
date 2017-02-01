package SDET1611.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class UploadPanel extends JPanel {
	
	private String keywordFile="Keyword Driven File";
	private String dataFile="Data Driven File";
	private String propertiesFile="Properties File";
	
	private JLabel keywordLabel;
	private JLabel dataLabel;
	private JLabel propertiesLabel;
	
	private JButton uploadKeywordExcelButton;
	private JButton uploadDataExcelButton;
	private JButton uploadPropertiesButton;
	
	private ButtonGroup bg;
	
	 private JLabel keywordSheet;
	 private JLabel dataSheet;
	 private JTextField keywordSheetInput;
	 private JTextField dataSheetInput;
	
	
	public UploadPanel()
	{
		setLayout(new GridLayout(3,2));
		keywordLabel=new JLabel("Keyword driven File");
		dataLabel=new JLabel("Data Driven File");
		propertiesLabel=new JLabel("Properties File");
		
		uploadKeywordExcelButton = new JButton("Upload");
		uploadDataExcelButton=new JButton("Upload");
		uploadPropertiesButton=new JButton("Upload");
		
		keywordSheet = new JLabel("Keyword Sheet");
        dataSheet= new JLabel("Data Sheet");
        keywordSheetInput= new JTextField();
        dataSheetInput=new JTextField();
        
		add(keywordLabel);
		add(uploadKeywordExcelButton);
		add(dataLabel);
		add(uploadDataExcelButton);
		add(propertiesLabel);
		add(uploadPropertiesButton);
		add(keywordSheet);
        add(keywordSheetInput);
        add(dataSheet);
        add(dataSheetInput);
		
		// Add an action listeners to the buttons.
	    uploadKeywordExcelButton.addActionListener(new UploadKeywordExcelListener());
	    uploadDataExcelButton.addActionListener(new UploadDataExcelListener());
	    uploadPropertiesButton.addActionListener(new UploadPropertiesListener());
	}

}

class UploadKeywordExcelListener implements ActionListener {
	
	final JFileChooser fc = new JFileChooser();
	final RunPanel runPanel = new RunPanel();
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Get return value
		int returnVal = fc.showOpenDialog(fc);
		//If success then get file
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			//Should be keyword excel file
			File keywordExcelFile = fc.getSelectedFile();
			runPanel.setKeywordExcelFile(keywordExcelFile);
		}
		
		
	}
}

class UploadDataExcelListener implements ActionListener {

	final JFileChooser fc = new JFileChooser();
	final RunPanel runPanel = new RunPanel();
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Get return value
		int returnVal = fc.showOpenDialog(fc);
		//If success then get file
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			//Should be keyword excel file
			File dataExcelFile = fc.getSelectedFile();
			runPanel.setDataExcelFile(dataExcelFile);
		}	
	}
	
}

class UploadPropertiesListener implements ActionListener {

	final JFileChooser fc = new JFileChooser();
	final RunPanel runPanel = new RunPanel();
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Get return value
		int returnVal = fc.showOpenDialog(fc);
		//If success then get file
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			//Should be keyword excel file
			File propertiesFile = fc.getSelectedFile();
			runPanel.setPropertiesFile(propertiesFile);
		}	
	}
	
}


