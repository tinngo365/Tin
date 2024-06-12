import java.math.*;
import java.text.DecimalFormat;

import javax.swing.JButton;
import javax.swing.JLabel;

/**
 * 
 * @author Tin Ngo
 *
 */
public class CreateStore {

	/**
	 * Creates all the stores
	 */

	/**
	 * All the instance variables for each store
	 */

	private String name;
	private double price;
	private double profit;
	private int count;
	private JLabel labelCount;
	private JLabel labelPrice;
	private JButton button;
	private DecimalFormat DF = new DecimalFormat("#,###.##");

	/**
	 * 
	 * @param name   - name of the store
	 * @param price  - price of the store
	 * @param profit - the profit the store makes each store
	 * @param count  - The quantity of this store
	 * @param button - A button for this store
	 */
	public CreateStore(String name, double price, double profit, int count, JButton button) {
		this.name = name;
		this.price = price;
		this.profit = profit;
		this.count = count;
		this.button = button;
		this.labelCount = new JLabel("" + count);
		this.labelPrice = new JLabel("$" + DF.format(price));
		this.button.setName("BUY: " + name);
	}

	/**
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return price
	 */
	public double getPrice() {
		return price * Math.pow(1.1, count);
	}

	/**
	 * 
	 * @return profit
	 */
	public double getProfit() {
		return profit;
	}

	/**
	 * 
	 * @return count of store
	 */
	public int getCount() {
		return count;
	}

	/**
	 * 
	 * @return A label for the amount of stores
	 */
	public JLabel getLabelCount() {
		return labelCount;
	}

	/**
	 * 
	 * @return a label for the price of the store
	 */
	public JLabel getLabelPrice() {
		return labelPrice;
	}

	/**
	 * 
	 * @return a button for the store
	 */
	public JButton getButton() {
		return button;
	}

	/**
	 * 
	 * @return name for the button of the store
	 */
	public String getButtonName() {
		return button.getName();
	}

	/**
	 * 
	 * @return total profit for the given stores
	 */
	public double getTotalProfit() {
		return profit * count;
	}

	/**
	 * Specifically made to be a boolean because it is asking if the user can buy
	 * the store
	 * 
	 * @param balance
	 * @return return true if the store can be bought
	 */
	public boolean buyStore(double balance) {
		if (balance >= getPrice()) {
			count++;
			return true;
		} else {
			System.out.println("You don't have sufficient funds for a " + name);
		}
		return false;
	}
}
