package SDET1611.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.io.File;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.UIManager;
import com.jtattoo.plaf.smart.SmartLookAndFeel;

public class GUI extends JFrame{
	private CheckBoxPanel ckpanel;
	private GUITitle guiTitle;
	private UploadPanel uploadPanel;
	private RunPanel runPanel;
	final int WINDOW_WIDTH=700,
			  WINDOW_HEIGHT=500;

	public GUI()
	{
		super("GUI APP for Hybrid Testing");
		setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setBackground(Color.white);
		guiTitle = new GUITitle();
		ckpanel=new CheckBoxPanel();
		uploadPanel =new UploadPanel();
		runPanel = new RunPanel();
		guiTitle.setBackground(Color.white);
		JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem menuItem = new JMenuItem("Exit");
        menu.add(menuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);
		add(guiTitle, BorderLayout.NORTH);
		add(ckpanel,BorderLayout.EAST);
		add(uploadPanel, BorderLayout.WEST);
		add(runPanel, BorderLayout.SOUTH);
		setVisible(true);
		
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
	}

}
