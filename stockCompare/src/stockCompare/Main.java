package stockCompare;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * The Main class contains the main and run() methods.
 * @author Thomas Zeller
 * 
 */
public class Main {
	
	private ArrayList<Stock> mainList;
	/**
	 * constructs new Main object and calls .run on that object
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Main main = new Main();
		main.run();
	}
	/**
	 * run constructs a View object so the GUI can be viewed
	 */
	public void run() { 
		setList();
		View view = new View(this);
	}
	
	public ArrayList<Stock> getMainList() {
		return mainList;
	}
	
	public void setList() {
		Reader readMain = new Reader();
		try {
			readMain.readFile("listOfStocks.txt");
			readMain.closeReader();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} finally {
			readMain.closeReader();
		}
		mainList = readMain.getMainList();
		Sorter.sort(mainList, 0, mainList.size() - 1, Sorter.BY_NAME);
	}

}
