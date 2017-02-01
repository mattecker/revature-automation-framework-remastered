import java.awt.GridLayout;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class testPanel extends JPanel {
	
	public String keywordFile="Keyword Driven File";
	public String dataFile="Data Driven File";
	public String propertiesFile="Properties File";
	
	private JLabel keywordLabel;
	private JLabel dataLabel;
	private JLabel propertiesLabel;
	
	private JButton keywordButton;
	private JButton dataButton;
	private JButton propertiesButton;
	
	private ButtonGroup bg;
	
	public testPanel()
	{
		setLayout(new GridLayout(3,2));
		keywordLabel=new JLabel("Keyword driven File");
		dataLabel=new JLabel("Data Driven File");
		propertiesLabel=new JLabel("Properties File");
		
		keywordButton = new JButton("Upload");
		dataButton=new JButton("Upload");
		propertiesButton=new JButton("Upload");
		add(keywordLabel);
		add(keywordButton);
		add(dataLabel);
		add(dataButton);
		add(propertiesLabel);
		add(propertiesButton);
		
		
		
	}

}
