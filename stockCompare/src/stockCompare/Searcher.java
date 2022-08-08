package stockCompare;
/**
 * Allows searching by stock's Symbol or by stock's industry by using the LinearSymbolSearch()
 * method or the linearIndustrySearch() method
 * @author Thomas Zeller
 */
public class Searcher {
	
	private Stock stock;
	private StockList stockList;
	/**
	 * Uses a simple linear search algorithm and searches for Symbol
	 * @param pSym
	 * @param pStockList
	 * @return desired stock
	 */
	public Stock linearSymbolSearch(String pSym, StockList pStockList ) {
		stockList = pStockList;
		for (int i = 0; i < pStockList.getList().size(); i++) {
			if (stockList.getList().get(i).getSymbol().equals(pSym)) {
				stock = stockList.getList().get(i);
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
	public StockList linearIndustrySearch(String pIndustry, StockList pStockList) {
		StockList industryList = new StockList("pIndustry");
		for (int i = 0; i < pStockList.getList().size(); i++) {
			if (pIndustry.equals(pStockList.getList().get(i).getIndustry())) {
				industryList.getList().add(pStockList.getList().get(i));
			}
		}
			return industryList;
	}
}
