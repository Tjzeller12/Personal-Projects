package stockCompare;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
/**
 * Has two methods writeFile() (writes stock information on to a text file), closeFile() (closes the Writer)
 * @author Thomas Zeller
 *
 */
public class Writer extends PrintWriter{
	
	private Stock addedStock;
	
	public Writer( String pFileName) throws FileNotFoundException, IOException{
		
		super(new FileWriter(new File(pFileName), true));	
	}
	
	/**
	 * writes a stock to the text file
	 * @param pStock
	 */
	public void writeFile(Stock pStock) {
		addedStock = pStock;
		println(addedStock);
		super.close();
	}
	
	/**
	 * closes the PrintWriter(Writer)
	 */
	public void closeFile() {
		super.close();
	}
}
