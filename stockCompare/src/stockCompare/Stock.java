package stockCompare;
/**
 * This Stock class contains instance variables. Some are entered by the user in the AddStockView and others are calculated based upon user inputs.
 * This class has getter and setter methods for all of these instance variables. It also has compareTo(), and toString() methods.
 * @author Thomas Zeller
 *
 */
public class Stock {
	
	//Stock name and symbol
	private String name;
	private String symbol;
	private String industry;
	
	//List of stock Statistics
	private double peRatio; // used for value
	private double marketCap; // used for safety
	private double value; // used for rating
	private double rating;
	private double stockPrice;// used for one year growth rate
	private double safety; // used for rating
	private double yearBeginningPrice; // used for one year growth rate
	private double dividend; // used for one year growth rate
	private double oneYearGrowthRate; // used for value

	/**
	 * Stock constructor with basic statistics parameters (Used for testing)
	 * @param pName
	 * @param pSymbol
	 * @param pMarketCap
	 * @param pStockPrice
	 */
	public Stock (String pName, String pSymbol, double pMarketCap, double  pStockPrice) {
		name = pName;
		symbol = pSymbol;
		marketCap = pMarketCap;
		stockPrice = pStockPrice;
	}
	/**
	 * Stock constructor with needed parameters (The statistics entered by the user)
	 * @param pName
	 * @param pSymbol
	 * @param pIndustry
	 * @param pPrice
	 * @param pPERatio
	 * @param pMarketCap
	 * @param pYearStart
	 * @param pDividend
	 */
	public Stock (String pName, String pSymbol, String pIndustry, double pPrice, double pPERatio, double pMarketCap, double pYearStart, double pDividend) {
		name = pName;
		symbol = pSymbol;
		industry = pIndustry;
		stockPrice = pPrice;
		peRatio = pPERatio;
		marketCap = pMarketCap;
		yearBeginningPrice = pYearStart;
		dividend = pDividend;
		
	}
	/**
	 * used if the user searches for a stock that does not exist
	 * @param pSymbol
	 */
	public Stock (String pSymbol) {
		symbol = pSymbol;
		
	}
	/**
	 * Getter and Setter methods for Name ,Symbol, and Industry (String data types)
	 * @param setName
	 */
	public void setName(String setName) {
		name = setName;
	}
	public String getName() {
		return name;
	}
	public void setSymbol(String pSymbol) {
		symbol = pSymbol;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setIndustry(String pIndustry) {
		industry = pIndustry;
	}
	public String getIndustry() {
		return industry;
	}
	
	/**
	 * Getter and Setter methods for stock statistics
	 * @param pPERatio
	 */
	public void setPEratio(double pPERatio) {
		peRatio = pPERatio;
	}
	public double getPERatio() {
		return peRatio;
	}
	public void setMarketCap(double pMarketCap) {
		marketCap = pMarketCap;
	}
	public double getMarketCap() {
		return marketCap;
	}
	public void setStockPrice(double pStockPrice) {
		stockPrice = pStockPrice;
	}
	public double getStockPrice() {
		return stockPrice;
	}
	public void setBeginningPrice(double p1yPrice) {
		yearBeginningPrice = p1yPrice;
	}
	public double getBeginningPrice() {
		return yearBeginningPrice;
	}
	public void setDividend(double pDividend) {
		dividend = pDividend;
	}
	public double getDividend() {
		return dividend;
	}
	
	/**
	 * //Calculates the one year growth rate and returns it
	 * @return oneYearGrowthRate
	 */
	public double getOneYearGrowthRate() {
		oneYearGrowthRate = (stockPrice - yearBeginningPrice) / yearBeginningPrice * 100 + 100;
		return oneYearGrowthRate;
	}
	/**
	 * calculates the value of the stock and returns it
	 * @return value
	 */
	public double getValue() {
		value = (this.getOneYearGrowthRate() - peRatio) + 50;
		return value;
	}
	
	/**
	 * Calculates the Safety of the stock and Returns it
	 * @return safety
	 */
	public double getSafety() {
		if (marketCap > 1000) {
			safety = 100;
		} else if (marketCap > 500) {
			safety = 95;
		} else if (marketCap > 10) {
			safety = 90;
		} else if (marketCap > 2) {
			safety = 65;
		} else if (marketCap > .3) {
			safety = 30;
		} else {
			safety = 10;
		}
		return safety;
	}
	/**
	 * Calculates the stocks overall rating and returns it
	 * @return rating
	 */
	public double getRating() { // Calculates a stocks rating and returns it
		rating = (this.getValue() + this.getSafety()) / 2;
		return rating;
	}

	/**
	 * toString method used for writing to the Text File
	 */
	@Override
	public String toString() {
		return this.getName() + " " + this.getSymbol() + " " + this.getIndustry() + " " + this.getStockPrice() + " " + this.getPERatio() + " " + this.getMarketCap() + " " + this.getBeginningPrice() + " " + this.getDividend();
	}
	
	/**
	 * Compares based off of a stocks rating
	 * @param pStock
	 * @return 1 if stock is larger than pStock, 0 if they are equal, and -1 if it is less
	 */
	public int compareTo(Stock pStock) {
		if (this.getRating() > pStock.getRating()) {
			return 1;
		} else if (this.getRating() < pStock.getRating()) {
			return -1;
		} else {
			return 0;
		}
	}


}
