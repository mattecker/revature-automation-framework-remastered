package SDET1611.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;

public class GUITitle extends JPanel {
	
	public GUITitle()
	{
		JLabel TestTitle= new JLabel("Hybrid Testing App");
        TestTitle.setFont(new Font("Serif", Font.BOLD, 35));
        TestTitle.setForeground(Color.GRAY);
        add(TestTitle);
	}
}
