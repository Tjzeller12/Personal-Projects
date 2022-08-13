package stockCompare;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * Has two methods writeFile() (writes stock information on to a text file), closeFile() (closes the Writer)
 * @author Thomas Zeller
 *
 */
public class Writer extends PrintWriter{
	
	private Stock addedStock;
	
	public Writer( String pFileName) throws FileNotFoundException, IOException {
		
		super(new FileWriter(new File(pFileName), false));	
	}
	
	/**
	 * writes a stock to the text file
	 * @param pStock
	 */
	public void writeFile(ArrayList<Stock> stocks) {
		for (Stock stock : stocks) {
			println(stock);
		}
		super.close();
	}
	
	public void updateFile(Stock pStock, String pFileName) throws FileNotFoundException {
	}
	
	/**
	 * closes the PrintWriter(Writer)
	 */
	public void closeFile() {
		super.close();
	}
}
