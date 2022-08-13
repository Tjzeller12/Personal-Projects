package stockCompare;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * View is the main menu with three buttons that lead the user to different frames and one that exits the program
 * @author thomaszeller
 *
 */
public class View extends JFrame implements ActionListener {
	
	private Main main;
	
	//Frame default dimensions
	final int FRAME_WIDTH = 500;
	final int FRAME_HIEGHT = 100;
	
	//Main Panel instance variables
	private JButton industryCompare;
	private JButton stockCompare;
	private JButton addStock;
	private JButton exit;
	
	/**
	 * Constructs the Frame and adds all components
	 * @param pMain
	 */
	public View( Main pMain) {
		
		main = pMain;
		
		// Main Panel includes four buttons, three buttons open other frames, the last closes the program
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		industryCompare = button("Industry Compare", 150, 100);
		industryCompare.addActionListener(this);
		stockCompare = button("Stock Compare", 150, 100);
		stockCompare.addActionListener(this);
		addStock = button("Add Stock", 150, 100);
		addStock.addActionListener(this);
		exit = button ("EXIT", 150, 100);
		exit.addActionListener(this);
		mainPanel.add(industryCompare);
		mainPanel.add(stockCompare);
		mainPanel.add(addStock);
		mainPanel.add(exit);
		
		// Frame default settings
		add(mainPanel);
		
		setTitle("Main Menu");
		
		setDefaultLookAndFeelDecorated(true);
		
		setSize(FRAME_WIDTH, FRAME_HIEGHT);
		
		setResizable(false);
		
		setVisible(true);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
	
	/**
	 * called when one of the buttons are clicked and performs desired action of the button clicked
	 */
	public void actionPerformed (ActionEvent pEvent) {
		if (pEvent.getActionCommand().equals("EXIT")) {
			System.exit(-100);
		} else if (pEvent.getActionCommand().equals("Add Stock")) {
			AddStockView newAddStock = new AddStockView(main);
		} else if (pEvent.getActionCommand().equals("Industry Compare")) {
			CompareIndustryView newIndustryView = new CompareIndustryView(main);
		} else if (pEvent.getActionCommand().equals("Stock Compare")) {
			CompareStockView newStockView = new CompareStockView(main);
		}
	}
	
	/**
	 * Constructs JButton while simultaneously setting preferred size
	 * @param name
	 * @param width
	 * @param hieght
	 * @return newButton
	 */
	public JButton button (String name, int width, int hieght) {
		JButton newButton = new JButton(name);
		newButton.setPreferredSize(new Dimension(width, hieght));
		return newButton;
	}
	
	
}
