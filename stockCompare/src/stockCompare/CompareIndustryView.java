package stockCompare;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 * Creates a Frame that allows the user to compare every stock within a selected industry
 * @author Thomas Zeller
 *
 */
public class CompareIndustryView extends JFrame implements ActionListener{
	
	// Frame preset dimensions
	final int FRAME_WIDTH = 1000;
	final int FRAME_HIEGHT = 700;
	// Select Industry Panel instance variables
	private JLabel selectIndustryLabel;
	private JComboBox selectIndustryCombo;
	private JButton compare;
	private String[] choices;
	// Button Panel instance variable
	private JButton back;
	// instance variables used for other methods
	private ArrayList<Stock> mainList;
	private ArrayList<Stock> industryStocksList;
	private ArrayList<String> industryList;
	private Searcher industrySearcher;
	private String[] newData;
	// Industry Table Panel instance variables
	private JLabel industrySelected = new JLabel("");
	private JTable industryStocksT;
	private JScrollPane stocksSP;
	private String[] columNames = {"Name", "Rating", "Value", "Safety", "Price", "P/E Ratio", "Dividen"};
	private String[][] data = {{"N/A", "N/A", "N/A","N/A","N/A","N/A", "N/A"} };
	private DefaultTableModel model;
	
	private Main main;
	
	/**
	 * Constructs frame and adds all of the needed components to the frame. The main components are the button panel, select industry panel, industry table panel, and the main panel.
	 */
	public CompareIndustryView(Main pMain) {
		
		main = pMain;
		
		// Select Industry Panel is at the top of the frame and allows a user to select an industry and select the compare button
		JPanel selectIndustryPanel = new JPanel();
		selectIndustryPanel.setLayout(new BoxLayout(selectIndustryPanel, BoxLayout.X_AXIS));
		selectIndustryLabel = new JLabel(" Select an Industry: ");
		createIndustryList();
		createComboBoxChoices();
		selectIndustryCombo = new JComboBox(choices);
		compare = new JButton("Compare!");
		compare.addActionListener(this);
		compare.setPreferredSize(new Dimension(150,100));
		selectIndustryPanel.add(selectIndustryLabel);
		selectIndustryPanel.add(selectIndustryCombo);
		selectIndustryPanel.add(compare);
		
		// Industry Table Panel is mainly for displaying the stocks information within an industry and sorting them by rating
		JPanel industryTablePanel = new JPanel();
		industryTablePanel.setLayout(new BoxLayout(industryTablePanel, BoxLayout.Y_AXIS));
		JPanel industryLabelPanel = new JPanel();
		industryLabelPanel.setLayout(new BoxLayout(industryLabelPanel, BoxLayout.X_AXIS));
		model = new DefaultTableModel(data, columNames);
		industryStocksT = new JTable( model);
		stocksSP = new JScrollPane(industryStocksT);
		industryLabelPanel.add(industrySelected);
		industryTablePanel.add(industryLabelPanel);
		industryTablePanel.add(stocksSP);
		
		// Button Panel is at the bottom of the frame and includes the back button allowing the user to go back to the previous frame
		JPanel buttonPanel= new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		back = new JButton("Back");
		back.setPreferredSize(new Dimension(150,100));
		back.addActionListener(this);
		buttonPanel.add(back);
		
		// Main Panel includes all other panels
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(selectIndustryPanel, BorderLayout.NORTH);
		mainPanel.add(buttonPanel, BorderLayout.SOUTH);
		mainPanel.add(industryTablePanel, BorderLayout.CENTER);
		
		
		// Frame default settings
		add(mainPanel);
		
		setTitle("Compare Stocks Within Industry Tool");
		
		setResizable(true);
		
		setDefaultLookAndFeelDecorated(true);
		
		setSize(FRAME_WIDTH, FRAME_HIEGHT);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setVisible(true);
	}
	/**
	 * called when one of the buttons are clicked and performs desired action of the button clicked
	 */
	@Override
	public void actionPerformed(ActionEvent pEvent) {
		if (pEvent.getActionCommand().equals("Back")) { // Sets Frame to invisible so user can return to home page
			setVisible(false);
		} else if (pEvent.getActionCommand().equals("Compare!")) {	// Updates the table so it displays stock information for the current industry selected
			updateTable();
		}
	}
	/**
	 * Creates a list of all the industries on the text file without adding them twice
	 */
	private void createIndustryList() {
		mainList = main.getMainList();
		industryList = new ArrayList<String>();
		boolean hasDuplicateIndustry;
		for (int i = 0; i < mainList.size(); i++) {
			hasDuplicateIndustry = false;
			for (int j = 0; j < industryList.size(); j++) {
				if (industryList.get(j).equals(mainList.get(i).getIndustry())) {
					hasDuplicateIndustry = true;
				}
			}
			if (hasDuplicateIndustry == false) {
				industryList.add(mainList.get(i).getIndustry());
			}
				
		}
	}
	
	/**
	 * Adds the industries from the industry list to an Array of Strings called "Choices"
	 */
	private void createComboBoxChoices() {
		choices = new String[industryList.size()];
		for (int i = 0; i < industryList.size(); i++) {
			choices[i] = industryList.get(i);
			
		}
	}

	/**
	 * Searches for stocks in the industry selected, adds them to a list, Sorts them by rating, adds the new data into the table
	 */
	private void updateTable() { 
		model.setRowCount(0);
		industrySearcher = new Searcher();
		industryStocksList = industrySearcher.linearIndustrySearch(selectIndustryCombo.getSelectedItem().toString(), mainList);
		Sorter.sort(industryStocksList, 0, industryStocksList.size() - 1, Sorter.BY_RATING);
		industrySelected.setText(selectIndustryCombo.getSelectedItem().toString() + ":");
		industrySelected.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		newData = new String[7];
		for (int i = 0; i < industryStocksList.size(); i++) {
			newData[0] = industryStocksList.get(i).getName();
			newData[1] = Double.toString(industryStocksList.get(i).getRating());
			newData[2] = Double.toString(industryStocksList.get(i).getValue());
			newData[3] = Double.toString(industryStocksList.get(i).getSafety());
			newData[4] = Double.toString(industryStocksList.get(i).getStockPrice());
			newData[5] = Double.toString(industryStocksList.get(i).getPERatio());
			newData[6] = Double.toString(industryStocksList.get(i).getDividend());
			
			model.addRow(newData);
		}
	}

}
