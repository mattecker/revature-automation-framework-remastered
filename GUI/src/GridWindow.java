import java.awt.*;
import javax.swing.*;
public class GridWindow extends JFrame {
	private final int Window_width =800;
	private final int Window_height=500;
	
	public GridWindow()
	{
		super("Grid Layout");
		setSize(Window_width,Window_height);
		setLayout(new GridLayout(2,3));
		JLabel keyLabel = new JLabel("Keyword driven file");
		JButton keyButton = new JButton("Upload");
		JLabel dataLabel= new JLabel("Data driven file");
		JButton dataButton = new JButton("Upload");
		JLabel propertiesLabel = new JLabel("Properties file");
		JButton propertiesButton = new JButton("Upload");
		add(keyLabel);
		add(keyButton);
		add(dataLabel);
		add(dataButton);
		add(propertiesLabel);
		add(propertiesButton);
		setBackground(Color.white);
		setVisible(true);
		
		
	}
	public static void main(String[] args)
	{
		new GridWindow();
	}

}
