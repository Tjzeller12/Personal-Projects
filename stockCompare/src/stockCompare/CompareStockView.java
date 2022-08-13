package stockCompare;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
/**
 * The CompareStockView allows the user to compare 5 stocks of their choice
 * @author Thomas Zeller
 *
 */
public class CompareStockView extends JFrame implements ActionListener {
	
	// Frame preset dimensions
	final int FRAME_WIDTH = 780;
	final int FRAME_HIEGHT = 175; 
	
	//Lists of tables and text fields
	private JLabel[] stockLabelList;
	private JTextField[] stockTFList;
	
	// Button Panel instance variables
	private JButton enter;
	private JButton back;
	
	// Main Panel instance variable
	private JLabel prompt;
	
	// Other methods instance variables
	private ArrayList<Stock> mainList;
	private Searcher searcher;
	private Stock currentStock;
	
	// Table Panel instance variables
	private JLabel blankSpace;
	private JLabel blankSpace2;
	private String[] columNames = {"Rating", "Value", "Safety", "Price", "P/E Ratio", "Dividen"};
	private String[][] data = {{"N/A", "N/A", "N/A", "N/A", "N/A", "N/A"},{"N/A", "N/A", "N/A", "N/A", "N/A", "N/A"},
	{"N/A", "N/A", "N/A", "N/A", "N/A", "N/A"}, {"N/A", "N/A", "N/A", "N/A", "N/A", "N/A"}, {"N/A", "N/A", "N/A", "N/A", "N/A", "N/A"}};
	private JTable statisticTable;
	
	private Main main;
	/**
	 * Constructs frame and adds all of the needed components to the frame. The main components are the button panel, enter stock panel, table panel, and the main panel.
	 */
	public CompareStockView(Main pMain) {
		
		main = pMain;
		// Enter Stock Panel includes the user inputs and is located to the left of the Table Panel
		JPanel enterStockPanel = new JPanel();
		enterStockPanel.setLayout(new GridLayout(6, 2));
		stockLabelList = new JLabel[5];
		stockTFList = new JTextField[5];
		blankSpace = new JLabel(" "); // blank space is used for anesthetic purposes (Adds blank space above labels and text fields so they are properly aligned with the Table Panel
		blankSpace2 = new JLabel(" ");
		enterStockPanel.add(blankSpace);
		enterStockPanel.add(blankSpace2);
		for (int i = 0; i < 5; i++) { // Adds every  every label and text field than adds them into Stock Panel
			stockLabelList[i] = new JLabel("Enter Stock " + (i + 1) + " Symbol: ");
			stockTFList[i] = new JTextField();
			addEnterStock(enterStockPanel, stockLabelList[i], stockTFList[i]);
		}
		
		// Table Panel is located to the right of Enter Stock Panel and displays the information of all the stocks entered in the Enter Stock Panel text fields
		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.X_AXIS));
		statisticTable = new JTable(data, columNames);
		JScrollPane table = new JScrollPane(statisticTable);
		tablePanel.add(enterStockPanel);
		tablePanel.add(table);
		
		// Button Panel includes three buttons at the bottom of the page
		JPanel buttonPanel = new JPanel ();
		enter = button("Enter", 60, 20);
		enter.addActionListener(this);
		back = button("Back", 60, 20);
		back.addActionListener(this);
		buttonPanel.add(back);
		buttonPanel.add(enter);
		
		// Main Panel includes all other panels
		JPanel mainPanel = new JPanel ();
		mainPanel.setLayout(new BorderLayout());
		prompt = new JLabel("Compare 5 stocks by entering their symbols: ", SwingConstants.CENTER);
		prompt.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		mainPanel.add(prompt, BorderLayout.NORTH);
		mainPanel.add(tablePanel, BorderLayout.CENTER);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		
		// Frame default settings
		add(mainPanel);
		
		setSize(FRAME_WIDTH, FRAME_HIEGHT);
		
		setResizable(false);
		
		setTitle("Stock Complare");
		
		setVisible(true);
		
		setDefaultLookAndFeelDecorated(true);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
	}
	/**
	 * called when one of the buttons are clicked and performs desired action of the button clicked
	 */
	public void actionPerformed(ActionEvent pEvent) {
		
		if (pEvent.getActionCommand().equals("Back")) {
			
			setVisible(false);
			
		} else if(pEvent.getActionCommand().equals("Enter")) {
			updateTable();
		}
		
	}
	
	/**
	 * adds label and Text field at the same time since they are used one can not exist without the other on the Enter Stock Panel
	 * @param panel
	 * @param label
	 * @param textField
	 */
	public void addEnterStock(JPanel panel, JLabel label, JTextField textField) {
		panel.add(label);
		panel.add(textField);
	}
	
	/**
	 * Constructs JButton while simultaneously setting preferred size
	 * @param name
	 * @param width
	 * @param hieght
	 * @return newButton
	 */
	public JButton button(String name, int width, int hieght) {
		JButton newButton = new JButton(name);
		newButton.setPreferredSize(new Dimension(width, hieght));
		return newButton;
	}
	
	/**
	 * reads the text file creates a list of stocks. Search for the stocks provided in the texts fields and displays them in the table
	 */
	private void updateTable() {
		
		mainList = main.getMainList();
			
		for (int i = 0; i < 5; i++) {	
			searcher = new Searcher();
			currentStock = searcher.linearSymbolSearch(stockTFList[i].getText(), mainList);
	
			if (currentStock.getSymbol().equals("NONE")) {	
				for (int j = 0; j < 6; j++) {
					data[i][j] = "N/A";
				}	
			} else {		
				data[i][0] = Double.toString(currentStock.getRating());
				data[i][1] = Double.toString(currentStock.getValue());
				data[i][2] = Double.toString(currentStock.getSafety());
				data[i][3] = Double.toString(currentStock.getStockPrice());
				data[i][4] = Double.toString(currentStock.getPERatio());
				data[i][5] = Double.toString(currentStock.getDividend());		
			}
			statisticTable.repaint();
		}
		
	}

}
