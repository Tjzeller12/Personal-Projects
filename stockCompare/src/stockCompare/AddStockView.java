package stockCompare;

import java.awt.Color;
import java.awt.Dimension;
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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
/**
 * The AddStockView class is a frame that allows the user to add stocks and stock information to the "ListOFStocks.txt" text file
 * @author Thomas Zeller
 * 
 */
public class AddStockView extends JFrame implements ActionListener{
	
	//Set Frame width and Height 
	final int FRAME_WIDTH = 1000;
	final int FRAME_HEIGHT = 300;
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
	
	/**
	 * Constructs and adds all components to Frame. The main components are the button panel, statistic panel, and the main panel.
	 */
	public AddStockView() {
		
		//Button Panel for back clear and add buttons
		JPanel buttonPanel = new JPanel();
		back = button("Back", 100, 30);
		back.addActionListener(this);
		add = button("Add Stock", 100, 30);
		add.addActionListener(this);
		clear = button("Clear", 100, 30);
		clear.addActionListener(this);
		buttonPanel.add(back);
		buttonPanel.add(clear);
		buttonPanel.add(add);
		
		//Statistic Panel (Allows user to enter a stocks Information
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
		titleLabel = new JLabel("Enter new stock statistics:");
		labelPanel.add(titleLabel, SwingConstants.CENTER);
		mainPanel.add(labelPanel);
		mainPanel.add(statisticPanel);
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
			
			Writer writer = new Writer("ListOfStocks.txt");
			writer.writeFile(newStock);
		} catch (NumberFormatException e) {
			System.out.println("A double was not entered in JTextField");
			JOptionPane.showMessageDialog(this, "You did not enter a number in one or more text fields", "ERROR", JOptionPane.ERROR_MESSAGE);
		}  catch (FileNotFoundException e) {
			System.out.println("File not Found");
		} catch (IOException e) {
			System.out.println("Input Output Exception");
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


