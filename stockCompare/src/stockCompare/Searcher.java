package stockCompare;

import java.util.ArrayList;

/**
 * Allows searching by stock's Symbol or by stock's industry by using the LinearSymbolSearch()
 * method or the linearIndustrySearch() method
 * @author Thomas Zeller
 */
public class Searcher {
	
	private Stock stock;
	private ArrayList<Stock> stockList;
	/**
	 * Uses a simple linear search algorithm and searches for Symbol
	 * @param pSym
	 * @param pStockList
	 * @return desired stock
	 */
	public Stock linearSymbolSearch(String pSym, ArrayList<Stock> pStockList ) {
		stockList = pStockList;
		for (int i = 0; i < pStockList.size(); i++) {
			if (stockList.get(i).getSymbol().equals(pSym)) {
				stock = stockList.get(i);
				return stock;
			}
		}
		stock = new Stock("NONE");
		return stock;
		
	}
	/**
	 * Uses a simple linear search algorithm and searches for industry
	 * @param pIndustry
	 * @param pStockList
	 * @return
	 */
	public ArrayList<Stock> linearIndustrySearch(String pIndustry, ArrayList<Stock> pStockList) {
		ArrayList<Stock> industryList = new ArrayList<Stock>();
		for (int i = 0; i < pStockList.size(); i++) {
			if (pIndustry.equals(pStockList.get(i).getIndustry())) {
				industryList.add(pStockList.get(i));
			}
		}
			return industryList;
	}
	
}
