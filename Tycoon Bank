import javax.swing.JOptionPane;
public class TycoonBank{

	private double balance;
	
	private int LemonadeStands = 0;
	private int Stores = 0;
	private int Hotels = 0;
	
	private int days = 0;
	
	public TycoonBank(double balance) {
		this.balance = balance;
	}
	public int getDays() {
		if(LemonadeStands >= 1) {
			days++;
			}
		return days;
	}
	public double getProfit() {
		double LemonadeStandsProfit = 1.50 * LemonadeStands;
		double StoresProfit = 50.99 * Stores;
		double HotelsProfit = 250.01 * Hotels;
		double profit = LemonadeStandsProfit + StoresProfit + HotelsProfit;
		return profit;
	}
	public void newBalance() {
		balance = balance + getProfit();
	}
	public double getBalance() {
		return balance;
	}
	public double buyLemonadeStands() {
		if(balance >= getLemonadeStandsCost()) {
		balance = balance - getLemonadeStandsCost();
		LemonadeStands++;
		}
		else 
		{
		JOptionPane.showMessageDialog(null, "Sorry you don't have sufficient funds Lemonade Stand");	
		}
		return LemonadeStands;
	}
	public double buyStores() {
		if(balance >= getStoresCost()) {
		balance = balance - getStoresCost();
		Stores++;
		}
		else 
		{
		JOptionPane.showMessageDialog(null, "Sorry you don't have sufficient funds for a Store");	
		}
		return Stores;
	}
	public double buyHotels() {
		if(balance >= getHotelsCost()) {
		balance = balance - getHotelsCost();
		Hotels++;
		}
		else 
		{
		JOptionPane.showMessageDialog(null, "Sorry you don't have sufficient funds for a Hotel");	
		}
		return Hotels;
	}
	public double getLemonadeStandsCost() {
		double LemonadeStandsCost = 10 * Math.pow(1.1, LemonadeStands);
		return LemonadeStandsCost;
	}
	public double getStoresCost() {
		double StoresCost = 2000 * Math.pow(1.1, Stores);
		return StoresCost;
	}
	public double getHotelsCost() {
		double HotelsCost = 10_000 * Math.pow(1.1, Hotels);
		return HotelsCost;
	}
	public int getLemonadeStandsAmount() {
		return LemonadeStands;
	}
	public int getStoresAmount() {
		return Stores;
	}
	public int getHotelsAmount() {
		return Hotels;
	}
}
