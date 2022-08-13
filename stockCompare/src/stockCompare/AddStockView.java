package stockCompare;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
/**
 * The AddStockView class is a frame that allows the user to add stocks and stock information to the "ListOFStocks.txt" text file
 * @author Thomas Zeller
 * 
 */
public class AddStockView extends JFrame implements ActionListener{
	
	//Set Frame width and Height 
	final int FRAME_WIDTH = 1000;
	final int FRAME_HEIGHT = 475;
	// Add stock view Instance variables:
	// Title label
	private JLabel titleLabel;
	//Labels for text fields:
	private JLabel nameLabel;
	private JLabel symbolLabel;
	private JLabel industryLabel;
	private JLabel stockPrice;
	private JLabel peRatioLabel;
	private JLabel marketCapLabel;
	private JLabel yearBeginningPriceLabel;
	private JLabel dividendLabel;
	//Text Fields for user input when adding stock
	private JTextField nameT;
	private JTextField symbolT;
	private JTextField industryT;
	private JTextField stockPriceT;
	private JTextField peT;
	private JTextField marketCapT;
	private JTextField yearStartT;
	private JTextField dividendT;
	// Frame buttons
	private JButton back;
	private JButton add;
	private JButton clear;
	private JButton update;
	// Stock that is being added
	private Stock newStock;
	//ArrayList for JLabels and JTextFields
	private ArrayList<JLabel> statisticLabelList;
	private ArrayList<JTextField> statisticTextFieldList;
	//Table variables
	private JLabel tableLabel;
	private JLabel tableLabel2;
	private final String[] COLUMNAMES = {"Name", "Symbol", "Industry", "Stock Price", "P/E Ratio", "Market Cap", "Year Start Price", "Dividend"};
	private String[][] data = {{"N/A","N/A","N/A","N/A","N/A","N/A","N/A","N/A"}};
	private JTable mainTable;
	private DefaultTableModel model;
	private JScrollPane mainSP;
	//Main variables
	private Main main;
	private ArrayList<Stock> mainList;
	
	/**
	 * Constructs and adds all components to Frame. The main components are the button panel, statistic panel, and the main panel.
	 */
	public AddStockView(Main pMain) {
		
		//initiating main variables
		main = pMain;
		mainList = main.getMainList();
		//Table Panel includes every stock in the mainList
		JPanel tablePanel = new JPanel();
		tablePanel.setLayout(new BoxLayout(tablePanel, BoxLayout.Y_AXIS));
		JPanel tableLabelPanel = new JPanel();
		tableLabelPanel.setLayout(new BoxLayout(tableLabelPanel, BoxLayout.X_AXIS));
		tableLabel = new JLabel("All stock data:");
		tableLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		tableLabel2 = new JLabel(" (Enter new stock information into the table cells and click \"Save\" to save new stock information)");
		System.out.println(tableLabel2.getFont());
		tableLabel2.setForeground(Color.GRAY);
		tableLabelPanel.add(tableLabel); tableLabelPanel.add(tableLabel2);
		model = new DefaultTableModel(data, COLUMNAMES);
		mainTable = new JTable(model);
		mainSP = new JScrollPane(mainTable);
		addListToTable(mainList);
		tablePanel.add(tableLabelPanel);
		tablePanel.add(mainSP);
		//Button Panel for back clear and add buttons
		JPanel buttonPanel = new JPanel();
		back = button("Back", 100, 30);
		back.addActionListener(this);
		add = button("Add Stock", 100, 30);
		add.addActionListener(this);
		clear = button("Clear", 100, 30);
		clear.addActionListener(this);
		update = button("Save", 100, 30);
		update.addActionListener(this);
		buttonPanel.add(back);
		buttonPanel.add(clear);
		buttonPanel.add(update);
		buttonPanel.add(add);
		//Statistic Panel (Allows user to enter a new stock's Information)
		JPanel statisticPanel = new JPanel();
		statisticPanel.setLayout(new GridLayout(0, 2));
		statisticLabelList = new ArrayList<JLabel>();
		statisticTextFieldList = new ArrayList<JTextField>();
		nameLabel = statisticLabel("Name:");
		symbolLabel = statisticLabel("Symbol:");
		industryLabel = statisticLabel("Industry:");
		stockPrice = statisticLabel("Stock Price:");
		peRatioLabel = statisticLabel("P/E Ratio:");
		marketCapLabel = statisticLabel("Market Cap:");
		yearBeginningPriceLabel = statisticLabel("Year Start Price:");
		dividendLabel = statisticLabel("Dividend:");
		nameT = statisticTextField("Enter name (Use dashes instead of spaces EX: United-Airlines)");
		symbolT = statisticTextField("Enter symbol");
		industryT = statisticTextField("Enter industry (Use dashes instead of spaces)");
		stockPriceT = statisticTextField("Enter stock price");
		peT = statisticTextField("Enter P/E");
		marketCapT = statisticTextField("Enter amount in billions");
		yearStartT = statisticTextField("Enter start price");
		dividendT = statisticTextField("Enter dividend");

		for (int i = 0; i < statisticLabelList.size(); i++) {
			JPanel newPanel = new JPanel();
			newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
			newPanel.add(statisticLabelList.get(i), SwingConstants.CENTER);
			newPanel.add(statisticTextFieldList.get(i));
			statisticPanel.add(newPanel);
		}
		
		// Main panel includes all other panels
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		JPanel labelPanel = new JPanel();
		labelPanel.setLayout(new BoxLayout(labelPanel, BoxLayout.X_AXIS));
		titleLabel = new JLabel("Enter new stock statistics:", SwingConstants.LEFT);
		titleLabel.setFont(new Font("Lucida Grande", Font.BOLD, 14));
		labelPanel.add(titleLabel);
		mainPanel.add(labelPanel);
		mainPanel.add(statisticPanel);
		mainPanel.add(tablePanel);
		mainPanel.add(buttonPanel);
		
		//Default frame settings: 
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		
		add(mainPanel);
		
		setVisible(true);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setTitle("Add Stock Tool");
		
		setResizable(false);
		
		setDefaultLookAndFeelDecorated(true);
		
		getContentPane().setBackground(new Color(0, 0 , 0));
	}
	
	/**
	 * called when one of the buttons are clicked and performs desired action of the button clicked
	 */
	@Override
	public void actionPerformed(ActionEvent pEvent) {
		if (pEvent.getActionCommand().equals("Back")) {
			setVisible(false);
		} else if(pEvent.getActionCommand().equals("Add Stock")) {
			addNewStock();
			
		} else if(pEvent.getActionCommand().equals("Clear")) {
			clearAllTF();
		} else if (pEvent.getActionCommand().equals("Save")) {
			updateList();
		}
	}
	/**
	 * Constructs JButton while simultaneously setting preferred size
	 * @param name
	 * @param width
	 * @param hieght
	 * @return newButton (Returns the JButton that was constructed)
	 */
	public JButton button(String name, int width, int hieght) {
		
		JButton newButton = new JButton(name);
		newButton.setPreferredSize(new Dimension(width, hieght));
		return newButton;
	}
	/**
	 * Constructs new label and adds it to statisticLabelList
	 * @param text
	 * @return newLabel
	 */
	public JLabel statisticLabel(String text) { 
		
		JLabel newLabel = new JLabel(text);
		statisticLabelList.add(newLabel);
		return newLabel;
	}
	/**
	 * Constructs new Text Field and adds it to statisticTextFieldList
	 * @param text
	 * @return newTextField
	 */
	public JTextField statisticTextField(String text) { 
		
		JTextField newTextField = new JTextField(text);
		newTextField.setForeground(Color.GRAY);
		statisticTextFieldList.add(newTextField);
		return newTextField;
	}
	/**
	 * Constructs newStock by using Text Field information then writes it to the "ListOfStocks.txt" text document
	 */
	private void addNewStock() { 
		try {
			newStock = new Stock(nameT.getText(), symbolT.getText(), industryT.getText(), Double.parseDouble(stockPriceT.getText()),
			Double.parseDouble(peT.getText()), Double.parseDouble(marketCapT.getText()), Double.parseDouble(yearStartT.getText()), Double.parseDouble(dividendT.getText()));
			mainList = main.getMainList();
			mainList.add(newStock);
			Writer writer = new Writer("ListOfStocks.txt");
			writer.writeFile(mainList);
			addStockToTable(newStock);
		} catch (NumberFormatException e) {
			System.out.println("A double was not entered in JTextField");
			JOptionPane.showMessageDialog(this, "You did not enter a number in one or more text fields", "ERROR", JOptionPane.ERROR_MESSAGE);
		}  catch (FileNotFoundException e) {
			System.out.println("File not Found");
		} catch (IOException e) {
			System.out.println("Input Output Exception");
		}
		main.setList();
		model.fireTableDataChanged();
	}
	/**
	 * Adds a stock to the bottom of the table
	 */
	private void addStockToTable(Stock newStock) {
		String[] addedData = new String[8];
		addedData[0] = newStock.getName();
		addedData[1] = newStock.getSymbol();
		addedData[2] = newStock.getIndustry();
		addedData[3] = Double.toString(newStock.getStockPrice());
		addedData[4] = Double.toString(newStock.getPERatio());
		addedData[5] = Double.toString(newStock.getMarketCap());
		addedData[6] = Double.toString(newStock.getBeginningPrice());
		addedData[7] = Double.toString(newStock.getDividend());
		model.addRow(addedData);
	}
	/**
	 * Takes the cell data from the table and writes it to the text file and refreshes the list in "main"
	 */
	private void updateList() {
		try {
			readCellsAndRefreshList();
			Writer writer = new Writer("ListOfStocks.txt");
			writer.writeFile(mainList);	
			JOptionPane.showMessageDialog(this, "Stock data was updated", "Update Successful!", JOptionPane.PLAIN_MESSAGE);
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Input Output Exception");
		} catch (NumberFormatException e) {
			System.out.println("A double was not entered in the statistic field");
			JOptionPane.showMessageDialog(this, "You did not enter a number in one or more of the table cells that require numbers", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		main.setList();
	}
	
	/**
	 * reads cell data and sets the main list
	 */
	private void readCellsAndRefreshList() {
		Stock currentStock;
		mainList = main.getMainList();
		for (int i = 0; i < model.getRowCount(); i++) {
			currentStock = new Stock( (String) model.getValueAt(i, 0), (String) model.getValueAt(i, 1), (String) model.getValueAt(i, 2),
					Double.parseDouble((String) model.getValueAt(i, 3)),Double.parseDouble((String) model.getValueAt(i, 4)),Double.parseDouble((String) model.getValueAt(i, 5)),
					Double.parseDouble((String) model.getValueAt(i, 6)), Double.parseDouble((String) model.getValueAt(i, 7)));
			mainList.set(i, currentStock);
		}
	}
	/**
	 * Inserts all of the stock data from an ArrayList to the table
	 * @param mainList
	 */
	private void addListToTable(ArrayList<Stock> mainList) {
		model.setRowCount(0);
		String[] newData = new String[8];
		for (Stock stock : mainList) {
			newData[0] = stock.getName();
			newData[1] = stock.getSymbol();
			newData[2] = stock.getIndustry();
			newData[3] = Double.toString(stock.getStockPrice());
			newData[4] = Double.toString(stock.getPERatio());
			newData[5] = Double.toString(stock.getMarketCap());
			newData[6] = Double.toString(stock.getBeginningPrice());
			newData[7] = Double.toString(stock.getDividend());
			model.addRow(newData);
		}
	}
	/**
	 * clears all textFields
	 */
	private void clearAllTF() { 
		
		for (int i = 0; i < statisticTextFieldList.size(); i++) {
			statisticTextFieldList.get(i).setText("");
		}
	}
}


