package SDET1611.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CheckBoxPanel extends JPanel {

	private JCheckBox chromeCheckBox;
	private JCheckBox ieCheckBox;
	private JCheckBox firefoxCheckBox;
	private JCheckBox operaCheckBox;
	
	public CheckBoxPanel()
	{
		
		//Set layout of panel
		setLayout(new GridLayout(4,1));
		setBorder(BorderFactory.createTitledBorder("Browsers"));
		
		setLayout(new GridLayout(4,1));
        chromeCheckBox=new JCheckBox("Chrome");
        chromeCheckBox.setFont(new Font("Serif", Font.BOLD, 20));
        chromeCheckBox.setForeground(Color.gray);
        
        ieCheckBox = new JCheckBox("IE");
        ieCheckBox.setFont(new Font("Serif", Font.BOLD, 20));
        ieCheckBox.setForeground(Color.gray);
        
        firefoxCheckBox= new JCheckBox("Firefox");
        firefoxCheckBox.setFont(new Font("Serif", Font.BOLD, 20));
        firefoxCheckBox.setForeground(Color.gray);
        
        operaCheckBox=new JCheckBox("Opera");
        operaCheckBox.setFont(new Font("Serif", Font.BOLD, 20));
        operaCheckBox.setForeground(Color.gray);
        
		//Add action listeners
		chromeCheckBox.addActionListener(new ChromeCheckBoxListener());
		ieCheckBox.addActionListener(new IECheckBoxListener());
		firefoxCheckBox.addActionListener(new FirefoxCheckBoxListener());
		operaCheckBox.addActionListener(new OperaCheckBoxListener());
		
		//Add check boxes to panel
		add(chromeCheckBox);
		add(ieCheckBox);
		add(firefoxCheckBox);
		add(operaCheckBox);
	}
}

class ChromeCheckBoxListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		boolean isSelected = false;
		RunPanel runPanel = new RunPanel();
		
		JCheckBox checkbox = (JCheckBox)e.getSource();
		if(checkbox.isSelected()) {
			isSelected = true;
			//Chrome check box is now selected
			runPanel.setChromeCheckValue(isSelected);
			
		} else {
			//Chrome check box is now unselected
			runPanel.setChromeCheckValue(isSelected);
		}
	}
	
}

class IECheckBoxListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		boolean isSelected = false;
		RunPanel runPanel = new RunPanel();
		
		JCheckBox checkbox = (JCheckBox)e.getSource();
		if(checkbox.isSelected()) {
			isSelected = true;
			//IE check box is now selected
			runPanel.setIECheckValue(isSelected);
		} else {
			//IE check box is now unselected
			runPanel.setIECheckValue(isSelected);
		}
	}
	
}

class FirefoxCheckBoxListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		boolean isSelected = false;
		RunPanel runPanel = new RunPanel();

		JCheckBox checkbox = (JCheckBox)e.getSource();
		if(checkbox.isSelected()) {
			isSelected = true;
			//Firefox check box is now selected
			runPanel.setFirefoxCheckValue(isSelected);
		} else {
			//Firefox check box is now unselected
			runPanel.setFirefoxCheckValue(isSelected);
		}
	}
}

class OperaCheckBoxListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		boolean isSelected = false;
		RunPanel runPanel = new RunPanel();
		
		JCheckBox checkbox = (JCheckBox)e.getSource();
		if(checkbox.isSelected()) {
			isSelected = true;
			//Opera check box is now selected
			runPanel.setOperaCheckValue(isSelected);
		} else {
			//Opera check box is now unselected
			runPanel.setOperaCheckValue(isSelected);
		}
	}
}
