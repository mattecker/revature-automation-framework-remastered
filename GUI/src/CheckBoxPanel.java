import java.awt.*;
import javax.swing.*;
public class CheckBoxPanel extends JPanel {
	public String CHROME= "Chrome";
	public String IE="IE";
	public String FIREFOX= "FireFox";
	public String OPERA="Opera";
	
	private JCheckBox chrome;
	private JCheckBox ie;
	private JCheckBox firefox;
	private JCheckBox opera;
	
	public CheckBoxPanel()
	{
		setLayout(new GridLayout(4,1));
		chrome=new JCheckBox("Chrome");
		ie = new JCheckBox("IE");
		firefox= new JCheckBox("Firefox");
		opera=new JCheckBox("opera");
		setBorder(BorderFactory.createTitledBorder("Browsers"));
		
		add(chrome);
		add(ie);
		add(firefox);
		add(opera);
		
	}
	
}
