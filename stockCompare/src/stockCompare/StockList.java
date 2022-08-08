package stockCompare;

import java.util.ArrayList;
/**
 * This class is an ArrayList with a String instance variable (a name for the list)
 * @author thomaszeller
 *
 */
public class StockList {
	// Instance Variables
	private String name; // Name of the list of Stocks
	private ArrayList<Stock> stockList;
	/**
	 * Constructor for StockList
	 * @param pName
	 */
	public StockList(String pName) {
		
		name = pName;
		stockList = new ArrayList<Stock>();
	}
	/**
	 * getter for name
	 * @return name
	 */
	public String getName() {
		return name;
	}
	/**
	 * getter for ArrayList
	 * @return stockList
	 */
	public ArrayList<Stock> getList() {
		return stockList;
	}
}
