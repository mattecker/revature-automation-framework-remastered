import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class testerClass extends JFrame{
	private CheckBoxPanel ckpanel;
	private GreetingGui greeting;
	private testPanel testpanels;
	private JPanel lastpanel;
	final int WINDOW_WIDTH=700,
			  WINDOW_HEIGHT=500;

	
	
	
	public testerClass()
	{
		super("GUI APP for Hybrid Testing");
		setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(new BorderLayout());
		setBackground(Color.white);
		greeting = new GreetingGui();
		ckpanel=new CheckBoxPanel();
		testpanels=new testPanel();
		greeting.setBackground(Color.white);
		
		buildRunPanel();
		
		add(greeting, BorderLayout.NORTH);
		add(ckpanel,BorderLayout.EAST);
		add(testpanels, BorderLayout.WEST);
		add(lastpanel, BorderLayout.SOUTH);
		setVisible(true);
	
		
		
	}
	private void buildRunPanel()
	{
		lastpanel = new JPanel();
		JButton runTestButton = new JButton("Run Test");
		lastpanel.add(runTestButton);
		
		
	}
	public static void main(String[] args)
	{
		testerClass a = new testerClass();
	}
}
