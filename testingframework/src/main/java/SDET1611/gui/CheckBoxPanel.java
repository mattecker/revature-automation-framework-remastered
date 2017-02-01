package SDET1611.gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class CheckBoxPanel extends JPanel {
	public String CHROME= "Chrome";
	public String IE="IE";
	public String FIREFOX= "FireFox";
	public String OPERA="Opera";
	
	private JCheckBox chromeCheckBox;
	private JCheckBox ieCheckBox;
	private JCheckBox firefoxCheckBox;
	private JCheckBox operaCheckBox;
	
	public CheckBoxPanel()
	{
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
		
		
		setBorder(BorderFactory.createTitledBorder("Browsers"));
		setBackground(Color.white);
		add(chromeCheckBox);
		add(ieCheckBox);
		add(firefoxCheckBox);
		add(operaCheckBox);
		
		chromeCheckBox.addActionListener(new ChromeCheckBoxListener());
		ieCheckBox.addActionListener(new IECheckBoxListener());
		firefoxCheckBox.addActionListener(new FirefoxCheckBoxListener());
		operaCheckBox.addActionListener(new OperaCheckBoxListener());
		
	}
	
}

class ChromeCheckBoxListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		boolean isSelected = false;
		RunPanel runPanel = new RunPanel();
		
		// TODO Auto-generated method stub
		JCheckBox checkbox = (JCheckBox)e.getSource();
		if(checkbox.isSelected()) {
			System.out.println("DEBUG--Chrome checkbox is selected");
			isSelected = true;
			runPanel.setChromeCheckValue(isSelected);
			
		} else {
			System.out.println("DEBUG--Chrome checkbox is  not selected");
			runPanel.setChromeCheckValue(isSelected);
		}
	}
	
}

class IECheckBoxListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		boolean isSelected;
		
		// TODO Auto-generated method stub
		JCheckBox checkbox = (JCheckBox)e.getSource();
		if(checkbox.isSelected()) {
			System.out.println("DEBUG--IE checkbox is selected");
			isSelected = true;
			RunPanel runPanel = new RunPanel();
			runPanel.setIECheckValue(isSelected);
			
		} else {
			System.out.println("DEBUG--IE checkbox is not selected");
		}
	}
	
}

class FirefoxCheckBoxListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		boolean isSelected;
		
		// TODO Auto-generated method stub
		JCheckBox checkbox = (JCheckBox)e.getSource();
		if(checkbox.isSelected()) {
			System.out.println("DEBUG--Firefox checkbox is selected");
			isSelected = true;
			RunPanel runPanel = new RunPanel();
			runPanel.setFirefoxCheckValue(isSelected);
			
		} else {
			System.out.println("DEBUG--Firefox checkbox is not selected");
		}
	}
}

class OperaCheckBoxListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		
		boolean isSelected;
		
		// TODO Auto-generated method stub
		JCheckBox checkbox = (JCheckBox)e.getSource();
		if(checkbox.isSelected()) {
			System.out.println("DEBUG--Opera checkbox is selected");
			isSelected = true;
			RunPanel runPanel = new RunPanel();
			runPanel.setOperaCheckValue(isSelected);
			
		} else {
			System.out.println("DEBUG--Opera checkbox is not selected");
		}
	}
	
}
