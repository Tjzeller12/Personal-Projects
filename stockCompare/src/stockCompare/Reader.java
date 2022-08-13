package stockCompare;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Reader has three methods: readFile() (reads stocks off of text file and adds them to a list), getMainList() (returns the list created), and closeReader() (closes the scanner)
 * @author Thomas Zeller
 *
 */
public class Reader {
	
	// Stock info
	private String inName;
	private String inSymbol;
	private String inIndustry;
	private double inStockPrice;
	private double inPERatio;
	private double inMarketCap;
	private double inYearStart;
	private double inDividend;
	private Scanner scanner;
	private Stock currentStock;
	// Stock List
	private ArrayList<Stock> mainList;

	/**
	 * reads info off text files and constructs each instant interval with this information.
 	 * Then constructs a new stock using the instant variables for the new stock arguments than adds the stock to the list.
 	 * Then adds the stock to the main list until there is no more text on the text file
	 */
	public void readFile(String pFile) throws FileNotFoundException { // 
		
		mainList = new ArrayList<Stock>();
		
		scanner = new Scanner(new File(pFile));

		while(scanner.hasNext()) { 
			
			inName = scanner.next();
			
			inSymbol = scanner.next();
			
			inIndustry = scanner.next();
			
			inStockPrice = scanner.nextDouble();
			
			inPERatio = scanner.nextDouble();
			
			inMarketCap = scanner.nextDouble();
			
			inYearStart = scanner.nextDouble();
			
			inDividend = scanner.nextDouble();
			
			currentStock = new Stock(inName, inSymbol, inIndustry, inStockPrice, inPERatio, inMarketCap, inYearStart, inDividend);
			
			mainList.add(currentStock);
			
		}
		scanner.close();
	}
	
	/**
	 * returns the list
	 * @return mainList
	 */
	public ArrayList<Stock> getMainList() {
		return mainList;
	}
	
	/**
	 * closes scanner
	 */
	public void closeReader() {
		scanner.close();
	}
}
