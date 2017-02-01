package SDET1611.gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class GUITitle extends JPanel {
	public GUITitle()
	{
		JLabel TestTitle= new JLabel("Hybrid Testing App");
		TestTitle.setFont(new Font("Serif", Font.BOLD, 35));
		TestTitle.setForeground(Color.GRAY);
		add(TestTitle);
	}
}
