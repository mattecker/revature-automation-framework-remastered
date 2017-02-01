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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import com.jtattoo.plaf.smart.SmartLookAndFeel;


public class GUI extends JFrame{
	private CheckBoxPanel ckpanel;
	private GUITitle greeting;
	private JPanel lastpanel;
	private RunPanel runPanels;
	final int WINDOW_WIDTH=700,
			  WINDOW_HEIGHT=500;
	//JMenuItem menuItem = new JMenuItem("Exit");
	
	
	
	
	public GUI()
	{
		super("Hybrid Testing App");
		
		setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setBackground(Color.white);
		JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem menuItem = new JMenuItem("Exit");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_MASK));
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });
        
        
        try
        {
        Properties props = new Properties();

       
        props.put("buttonColorLight", "50 230 227");
        props.put("buttonColorDark", "14 158 156");

 
        props.put("rolloverColorLight", "131 237 242"); 
        props.put("rolloverColorDark", "51 177 184"); 

        
        // set your theme
        SmartLookAndFeel.setCurrentTheme(props);
        // select the Look and Feel
        UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
        }
        catch(Exception ex)
        {
        	ex.printStackTrace();
        }
        menu.add(menuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
		greeting = new GUITitle();
		ckpanel=new CheckBoxPanel();
		ckpanel.setBackground(Color.white);
		runPanels=new RunPanel();
		runPanels.setBackground(Color.white);
		//td = new testPanel();
		greeting.setBackground(Color.white);
		
		
	
		add(greeting, BorderLayout.NORTH);
		add(ckpanel,BorderLayout.WEST);
		//add(td, BorderLayout.WEST);
		add(runPanels, BorderLayout.SOUTH);
		JLabel keywordLabel = new JLabel("Keyword driven File");
		JLabel empty = new JLabel("aaa");
		
		
		setFont(new Font("Serif", Font.BOLD, 20));
		JLabel dataLabel = new JLabel("Data Driven File");
		JLabel propertiesLabel = new JLabel("Properties File");
		
		JButton uploadKeywordExcelButton = new JButton("Upload");
		JButton uploadDataExcelButton = new JButton("Upload");
		JButton uploadPropertiesButton = new JButton("Upload");
		
		JLabel keywordSheet = new JLabel("Keyword Sheet");
        JLabel dataSheet = new JLabel("Data Sheet");
        JTextField keywordSheetInput = new JTextField(10);
        JTextField dataSheetInput = new JTextField(10);
        
		JPanel panel = new JPanel();
	      panel.setBackground(Color.white);
	      panel.setSize(300,300);
	      GridBagLayout layout = new GridBagLayout();

	      panel.setLayout(layout);        
	      GridBagConstraints gbc = new GridBagConstraints();

	      gbc.fill = GridBagConstraints.HORIZONTAL;
	      gbc.gridx = 0;
	      gbc.gridy = 0;
	      panel.add(keywordLabel,gbc);
	      

	    
	      
	      gbc.fill = GridBagConstraints.HORIZONTAL;
	      gbc.gridx = 2;
	      gbc.gridy = 0;
	      panel.add(empty,gbc);
	      empty.setForeground(Color.white);
	      
	      gbc.gridx = 3;
	      gbc.gridy = 0;
	      panel.add(uploadKeywordExcelButton,gbc); 

	      gbc.fill = GridBagConstraints.HORIZONTAL;
	      //gbc.ipady = 20;   
	      gbc.gridx = 0;
	      gbc.gridy = 1;
	      panel.add(dataLabel,gbc); 

	      gbc.gridx = 3;
	      gbc.gridy =1;       
	      panel.add(uploadDataExcelButton,gbc);  

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
	      panel.add(keywordSheetInput,gbc);  
	      
	      //dataDriven Sheet
	      gbc.gridx = 0;
	      gbc.gridy = 4;      
	      gbc.fill = GridBagConstraints.HORIZONTAL;
	      panel.add(dataSheet,gbc);  
	      
	      gbc.gridx = 3;
	      gbc.gridy = 4;   
	      gbc.gridheight = 2;
	      gbc.fill = GridBagConstraints.HORIZONTAL;
	      panel.add(dataSheetInput,gbc);  
	      
	    
	      uploadKeywordExcelButton.addActionListener(new UploadKeywordExcelListener());
		  uploadDataExcelButton.addActionListener(new UploadDataExcelListener());
		  uploadPropertiesButton.addActionListener(new UploadPropertiesListener());
	
	      add(panel);
		  setVisible(true);

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



	
	private void buildRunPanel()
	{
		lastpanel = new JPanel();
		JButton runTestButton = new JButton("Run Test");
		lastpanel.add(runTestButton);	
		
	}
	
	public static void main(String[] args)
	{
		GUI a = new GUI();
	}
}
