package SDET1611.gui;

import java.io.PrintStream;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * @author cavan
 * JPanel for displaying the console output.
 */
public class SysPanel extends JScrollPane {
	
	static JTextArea resultArea = new JTextArea();
	
	/**
	 * Default / only constructor.
	 */
	public SysPanel(){
		super(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		resultArea = new JTextArea();
		resultArea.setEditable(false);
		PrintStream console = new PrintStream(new TextAreaOutputStream(resultArea));
		System.setOut(console);
		System.setErr(console);
		
		this.setViewportView(resultArea);
		this.setVisible(true);
	}
	
	
}
