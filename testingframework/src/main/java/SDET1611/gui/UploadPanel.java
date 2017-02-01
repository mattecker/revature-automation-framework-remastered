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
	
	private JLabel keywordLabel;
	private JLabel dataLabel;
	private JLabel propertiesLabel;
	private JLabel keywordSheetLabel;
	private JLabel dataSheetLabel;
	
	private JButton uploadKeywordExcelButton;
	private JButton uploadDataExcelButton;
	private JButton uploadPropertiesButton;
	
	static JTextField keywordSheetTextField;
	private JTextField dataSheetTextField;
	
	public UploadPanel()
	{
		//Set grid layout
		setLayout(new GridLayout(3,2));
		
		//Create labels
		keywordLabel = new JLabel("Keyword driven File");
		dataLabel = new JLabel("Data Driven File");
		propertiesLabel = new JLabel("Properties File");
		keywordSheetLabel = new JLabel("Keyword Sheet");
        dataSheetLabel = new JLabel("Data Sheet");
		
		//Create buttons
		uploadKeywordExcelButton = new JButton("Upload");
		uploadDataExcelButton = new JButton("Upload");
		uploadPropertiesButton = new JButton("Upload");
		
		//Create text fields
        keywordSheetTextField = new JTextField();
        dataSheetTextField = new JTextField();
        
        //Add to panel
		add(keywordLabel);
		add(uploadKeywordExcelButton);
		add(dataLabel);
		add(uploadDataExcelButton);
		add(propertiesLabel);
		add(uploadPropertiesButton);
		add(keywordSheetLabel);
        add(keywordSheetTextField);
        add(dataSheetLabel);
        add(dataSheetTextField);
		
		// Add an action listeners to the buttons.
	    uploadKeywordExcelButton.addActionListener(new UploadKeywordExcelListener());
	    uploadDataExcelButton.addActionListener(new UploadDataExcelListener());
	    uploadPropertiesButton.addActionListener(new UploadPropertiesListener());
	    keywordSheetTextField.addActionListener(new KeywordSheetTextListener());
	    dataSheetTextField.addActionListener(new DataSheetTextListener());
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
			//Should be data excel file
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
			//Should be properties file
			File propertiesFile = fc.getSelectedFile();
			runPanel.setPropertiesFile(propertiesFile);
		}	
	}
}

class KeywordSheetTextListener implements ActionListener {
	
	final RunPanel runPanel = new RunPanel();
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JTextField input = (JTextField)e.getSource();		
		runPanel.setKeywordSheetText(input.getText());
		System.out.println("DEBUG--- input text : "+input.getText());
	}
	
	
}

class DataSheetTextListener implements ActionListener {
	
	final RunPanel runPanel = new RunPanel();
	
	@Override
	public void actionPerformed(ActionEvent e) {
		JTextField input = (JTextField)e.getSource();		
		runPanel.setDataSheetText(input.getText());
	}
}

