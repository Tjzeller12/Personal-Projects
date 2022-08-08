package stockCompare;
/**
 * The Main class contains the main and run() methods.
 * @author Thomas Zeller
 * 
 */
public class Main {
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
		View view = new View(this);
	}

}
