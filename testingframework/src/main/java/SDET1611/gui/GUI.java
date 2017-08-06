package SDET1611.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;

import com.jtattoo.plaf.smart.SmartLookAndFeel;

/**
 * There is code duplicate from UploadPanel.java into this class So styling can
 * be implemented to the upload panel. It needs some refactoring but the code
 * works.
 *
 */
public class GUI extends JFrame {
	private CheckBoxPanel ckpanel;
	private GUITitle guiTitle;
	private RunPanel runPanels;
	final int WINDOW_WIDTH = 700, WINDOW_HEIGHT = 500;

	private static JLabel showHybridFileLoc = new JLabel("");

	public GUI() {
		super("Hybrid Testing App");

		this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.setBackground(new Color(201, 203, 255));
		JMenuBar menuBar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem menuItem = new JMenuItem("Exit");

		// --> File --> Exit --> Closes the window
		menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, KeyEvent.ALT_MASK));
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				System.exit(0);
			}
		});

		// load Numbus UI
		try {
			this.loadNimbus();
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {

			// load old UI if nimbus isn't available
			System.out.println("Nimbus UI not found or failed to load.");
			this.loadBackupUI();
		}

		menu.add(menuItem);
		menuBar.add(menu);
		setJMenuBar(menuBar);
		guiTitle = new GUITitle();
		ckpanel = new CheckBoxPanel();
		ckpanel.setBackground(new Color(201, 203, 255));
		runPanels = new RunPanel();
		runPanels.setBackground(new Color(201, 203, 255));
		guiTitle.setBackground(new Color(201, 203, 255));

		this.add(guiTitle, BorderLayout.NORTH);
		
		JPanel comboPanel = new JPanel();
		comboPanel.setBackground(new Color(201,203,255));

		this.add(runPanels, BorderLayout.SOUTH);

		// Add label
		this.setFont(new Font("Serif", Font.BOLD, 20));
		JLabel hybridLabel = new JLabel("Hybrid Files");

		JButton uploadHybridFilesButton = new JButton("Upload");

		JPanel inputPanel = new JPanel();

		inputPanel.setBackground(new Color(201, 203, 255));
		inputPanel.setSize(300, 300);
		GridBagLayout layout = new GridBagLayout();

		inputPanel.setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();

		gbc.gridx = 0;
		gbc.gridy = 0;
		inputPanel.add(hybridLabel, gbc);

		gbc.gridx = 1;
		gbc.gridy = 0;
		inputPanel.add(uploadHybridFilesButton, gbc);

		gbc.gridwidth = 3;
		gbc.gridx = 0;
		gbc.gridy = 1;

		inputPanel.add(showHybridFileLoc, gbc);

		uploadHybridFilesButton.addActionListener(new uploadHybridTestsListener());

		comboPanel.setLayout(new BorderLayout());
		comboPanel.add(ckpanel, BorderLayout.NORTH);
		comboPanel.add(inputPanel, BorderLayout.SOUTH);
		this.add(comboPanel,BorderLayout.WEST);
		this.add(new SysPanel(), BorderLayout.CENTER);
		this.setVisible(true);
	}

	class uploadHybridTestsListener implements ActionListener {
		final JFileChooser fc = new JFileChooser();
		final RunPanel runPanel = new RunPanel();

		@Override
		public void actionPerformed(ActionEvent e) {
			int returnVal = fc.showOpenDialog(fc);

			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File hybridFiles = fc.getSelectedFile();
				if(hybridFiles.getName().length() > 20){
					showHybridFileLoc.setText(hybridFiles.getName().substring(0, 20).concat("..."));
				}
				else {
					showHybridFileLoc.setText(hybridFiles.getName());
				}

				// Get Keyword File
				runPanel.setKeywordExcelFile(hybridFiles);
				runPanel.setKeywordSheetText("Keywords");

				// Get Data File
				runPanel.setDataExcelFile(hybridFiles);
				runPanel.setDataSheetText("Data");
			}
		}
	}

	public void loadNimbus() throws ClassNotFoundException, InstantiationException, IllegalAccessException,
			UnsupportedLookAndFeelException {
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			if ("Nimbus".equals(info.getName())) {
				UIManager.setLookAndFeel(info.getClassName());
				break;
			}
		}
	}

	public void loadBackupUI() {
		try {
			// old UI
			Properties props = new Properties();

			props.put("buttonColorLight", "50 230 227");
			props.put("buttonColorDark", "38 63 255");

			props.put("rolloverColorLight", "131 237 242");
			props.put("rolloverColorDark", "25 173 255");

			// set your theme
			SmartLookAndFeel.setCurrentTheme(props);
			// select the Look and Feel
			UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
			// com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			System.out.println("Default UI has failed to laod.");
		}
	}
}
