package SDET1611.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.jtattoo.plaf.smart.SmartLookAndFeel;

import SDET1611.testingframework.ReadExcelFile;

/**
 * There is code duplicate from UploadPanel.java into this class So styling can
 * be implemented to the upload panel. It needs some refactoring but the code
 * works.
 *
 */
public class GUI extends JFrame{
	private CheckBoxPanel ckpanel;
	private GUITitle guiTitle;
	//private JPanel lastpanel;
	private RunPanel runPanels;
	//private JLabel keywordSheetLabel;
	//private JLabel dataSheetLabel;
	final int WINDOW_WIDTH=700,
			  WINDOW_HEIGHT=500;
	
	/*private static JLabel showKeywordFileLoc = new JLabel("");
	private static JLabel showDataDrivenFileLoc = new JLabel("");
    private static JLabel showPropertiesFileLoc = new JLabel("");*/
	private static JLabel showHybridFileLoc = new JLabel("");

	private static JLabel keySheetExists = new JLabel("");
	private static JLabel dataSheetExists = new JLabel("");

	public GUI() {
		super("Hybrid Testing App");

		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setBackground(new Color(201, 203, 255));
		JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem menuItem = new JMenuItem("Exit");
        
        // --> File --> Exit --> Closes the window
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_MASK));
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        
		// load Numbus UI
		try {
			loadNimbus();
		} catch (ClassNotFoundException |
				 InstantiationException |
				 IllegalAccessException |
				 UnsupportedLookAndFeelException e) {
			// e.printStackTrace();

			// load old UI if nimbus isn't available
			System.out.println("Nimbus UI not found or failed to load.");
			loadBackupUI();
		}
		
		menu.add(menuItem);
		menuBar.add(menu);
		setJMenuBar(menuBar);
		guiTitle = new GUITitle();
		ckpanel = new CheckBoxPanel();
		ckpanel.setBackground(new Color(201, 203, 255));
		runPanels = new RunPanel();
		runPanels.setBackground(new Color(201, 203, 255));
		guiTitle.setBackground(new Color(201, 203, 255));

		add(guiTitle, BorderLayout.NORTH);
		add(ckpanel, BorderLayout.WEST);
		// add(td, BorderLayout.WEST);
		add(runPanels, BorderLayout.SOUTH);
		//JLabel keywordLabel = new JLabel("Keyword driven File");
		
		//Add labels
		setFont(new Font("Serif", Font.BOLD, 20));
		/*JLabel dataLabel = new JLabel("Data Driven File");
		JLabel propertiesLabel = new JLabel("Properties File");
		JLabel keywordSheet = new JLabel("Keyword Sheet");
        JLabel dataSheet = new JLabel("Data Sheet");*/
		JLabel hybridLabel = new JLabel("Hybrid Files");
		
		//Add buttons
		/*JButton uploadKeywordExcelButton = new JButton("Upload");
		JButton uploadDataExcelButton = new JButton("Upload");
		JButton uploadPropertiesButton = new JButton("Upload");*/
		JButton uploadHybridFilesButton = new JButton("Upload");
	
		//Add textfields
        /*JTextField keywordSheetTextField = new JTextField(10);
        JTextField dataSheetTextField  = new JTextField(10);*/
        
		JPanel panel = new JPanel();
		
	    panel.setBackground(new Color(201,203, 255));
	    panel.setSize(300,300);
	    GridBagLayout layout = new GridBagLayout();
	
	    panel.setLayout(layout);        
	    GridBagConstraints gbc = new GridBagConstraints();
	
	    /*gbc.fill = GridBagConstraints.HORIZONTAL;//add
	    gbc.gridx = 4;
	    gbc.gridy = 0;
	    panel.add(showKeywordFileLoc,gbc);
	      
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    panel.add(keywordLabel,gbc);
	
	    gbc.gridx = 3;
	    gbc.gridy = 0;
	    panel.add(uploadKeywordExcelButton,gbc); 
	      
	    gbc.fill = GridBagConstraints.HORIZONTAL;//add
	    gbc.gridx = 4;
	    gbc.gridy = 1;
	    panel.add(showDataDrivenFileLoc,gbc);
	
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    //gbc.ipady = 20;   
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    panel.add(dataLabel,gbc); 
	
	    gbc.gridx = 3;
	    gbc.gridy =1;       
	    panel.add(uploadDataExcelButton,gbc);  
	      
	    gbc.fill = GridBagConstraints.HORIZONTAL;//add
	    gbc.gridx = 4;
	    gbc.gridy = 2;
	    panel.add(showPropertiesFileLoc,gbc);
	
	    gbc.gridx = 0;
	    gbc.gridy = 3;      
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    //gbc.gridwidth = 2;
	    panel.add(keywordSheet,gbc);  
	      
	    gbc.gridx = 3;
	    gbc.gridy = 2;      
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    //gbc.gridwidth = 2;
	    panel.add(uploadPropertiesButton,gbc);  
	      
	    //Keyword Sheet
	    gbc.gridx = 0;
	    gbc.gridy = 2;      
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    //gbc.gridwidth = 2;
	    panel.add(propertiesLabel,gbc);  
	     
	    gbc.gridx = 3;
	    gbc.gridy = 3;      
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	
	    //gbc.gridwidth = 2;
	    panel.add(keywordSheetTextField,gbc);  
	      
	    //dataDriven Sheet
	    gbc.gridx = 0;
	    gbc.gridy = 4;      
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    panel.add(dataSheet,gbc);  
	      
	    gbc.gridx = 3;
	    gbc.gridy = 4;   
	    gbc.gridheight = 2;
	    gbc.fill = GridBagConstraints.HORIZONTAL;
	    panel.add(dataSheetTextField,gbc); */
	    
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    panel.add(hybridLabel,gbc);
	    
	    gbc.gridx = 3;
	    gbc.gridy = 0;
	    panel.add(uploadHybridFilesButton,gbc);
	    
	    gbc.gridx = 4;
	    gbc.gridy = 0;
	    panel.add(showHybridFileLoc,gbc);
	      
		/*keywordSheetLabel = new JLabel("Keyword Sheet");
	    dataSheetLabel = new JLabel("Data Sheet");
	    add(keywordSheetLabel);
	    add(dataSheetLabel);*/
	      
	    /*uploadKeywordExcelButton.addActionListener(new UploadKeywordExcelListener());
		uploadDataExcelButton.addActionListener(new UploadDataExcelListener());
		uploadPropertiesButton.addActionListener(new UploadPropertiesListener());
		keywordSheetTextField.addFocusListener(new KeywordSheetTextListener());
		dataSheetTextField.addFocusListener(new DataSheetTextListener());*/
	    uploadHybridFilesButton.addActionListener(new uploadHybridTestsListener());
		    
	    add(panel);
		setVisible(true);
	}
	
	/*class UploadKeywordExcelListener implements ActionListener {
		final JFileChooser fc = new JFileChooser();
		final RunPanel runPanel = new RunPanel();

		@Override
		public void actionPerformed(ActionEvent e) {
			//Get return value
			int returnVal = fc.showOpenDialog(fc);
			// If success then get file
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				// Should be keyword excel file
				File keywordExcelFile = fc.getSelectedFile();
				runPanel.setKeywordExcelFile(keywordExcelFile);
				showKeywordFileLoc.setText(runPanel.getKeywordPath());
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
			// If success then get file
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				// Should be keyword excel file
				File dataExcelFile = fc.getSelectedFile();
				runPanel.setDataExcelFile(dataExcelFile);
				showDataDrivenFileLoc.setText(runPanel.getDataDrivenPath());
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
			// If success then get file
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				// Should be keyword excel file
				File propertiesFile = fc.getSelectedFile();
				runPanel.setPropertiesFile(propertiesFile);
				showPropertiesFileLoc.setText(runPanel.getPropertiesFilePath());
			}
		}
	}

	class KeywordSheetTextListener implements FocusListener {
		final RunPanel runPanel = new RunPanel();
	
		@Override
		public void focusGained(FocusEvent e) {
			
		}
	
		@Override
		public void focusLost(FocusEvent e) {
			JTextField input = (JTextField)e.getSource();		
			runPanel.setKeywordSheetText(input.getText());
			System.out.println("DEBUG--- input text : "+input.getText());
		}
	}

	class DataSheetTextListener implements FocusListener {
		final RunPanel runPanel = new RunPanel();
		
		@Override
		public void focusGained(FocusEvent arg0) {
			
		}
	
		@Override
		public void focusLost(FocusEvent e) {
			JTextField input = (JTextField)e.getSource();		
			runPanel.setDataSheetText(input.getText());
		}
	}*/
	
	class uploadHybridTestsListener implements ActionListener {
		final JFileChooser fc = new JFileChooser();
		final RunPanel runPanel = new RunPanel();
		
		@Override
		public void actionPerformed(ActionEvent e) {
			//Get return value
			int returnVal = fc.showOpenDialog(fc);
			//If success then get file
			if(returnVal == JFileChooser.APPROVE_OPTION) {
				//Should be keyword excel file
				File hybridFiles = fc.getSelectedFile();
				showHybridFileLoc.setText(hybridFiles.getName());
				
				//Get Keyword File
				runPanel.setKeywordExcelFile(hybridFiles);
				runPanel.setKeywordSheetText("Keywords");
				//showKeywordFileLoc.setText(runPanel.getKeywordPath());
				
				//Get Data File
				runPanel.setDataExcelFile(hybridFiles);
				runPanel.setDataSheetText("Data");
				//showDataDrivenFileLoc.setText(runPanel.getDataDrivenPath());
				
				//Get Properties File
				try{
					Sheet propertiesSheet = ReadExcelFile.readExcel(hybridFiles.getAbsolutePath(), hybridFiles.getName(), "Properties");
					Row hybridRow = propertiesSheet.getRow(0);
					String propertiesPath = hybridRow.getCell(0).toString();
					File propertiesFile = new File(propertiesPath);
					runPanel.setPropertiesFile(propertiesFile);
					//showPropertiesFileLoc.setText(runPanel.getPropertiesFilePath());
				} catch(Exception exception){
					exception.printStackTrace();
				}
			}
		}
	}

	public void loadNimbus() throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName())) {
//				UIManager.put("nimbusBlueGrey", new Color(0, 0, 255));
				UIManager.setLookAndFeel(info.getClassName());
				break;
			}
		}
	}
	public void loadBackupUI() {
		try {
			// old UI
			Properties props = new Properties();
	
			props.put("buttonColorLight", "50 230 227");
			props.put("buttonColorDark", "38 63 255");
	
			props.put("rolloverColorLight", "131 237 242");
			props.put("rolloverColorDark", "25 173 255");
	
			// set your theme
			SmartLookAndFeel.setCurrentTheme(props);
			// select the Look and Feel
			UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
			// com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel
		} catch (ClassNotFoundException |
				 InstantiationException |
				 IllegalAccessException	|
				 UnsupportedLookAndFeelException e) {
			//e.printStackTrace();
			System.out.println("Default UI has failed to laod.");
		}
	}
}
