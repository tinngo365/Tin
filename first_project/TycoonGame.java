import java.util.Scanner;
import java.math.*;
import java.util.Timer;
import java.util.TimerTask;
import java.text.DecimalFormat;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TycoonGame {
	static AtomicBoolean AB = new AtomicBoolean(false);
	DecimalFormat DF = new DecimalFormat("#.##");
	static TycoonBank Player1 = new TycoonBank(10.00);
	Timer timer;
	public TycoonGame() {	
		timer = new Timer(true);
		timer.schedule(new time(), 0, 1000);
	}
	class time extends TimerTask {
		DecimalFormat DF = new DecimalFormat("#.##");
		public void run() {
			if(AB.get() == true) {
			Player1.newBalance();
			DF.setGroupingUsed(true);
			DF.setGroupingSize(3);

			System.out.println("\n\n\n\n\nYour Businesses\n________________________"
					+ "\nLemonadeStands: " + Player1.getLemonadeStandsAmount()
					+ "\nStores: " + Player1.getStoresAmount()
					+ "\nHotels: " + Player1.getHotelsAmount());
			System.out.println("\n\nDay: " + Player1.getDays() + "\nCurrent Balance: $" + DF.format(Player1.getBalance()) + " | Current Profits: $" + DF.format(Player1.getProfit()));
			}
		}
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		DecimalFormat DF = new DecimalFormat("#.##");
		DF.setGroupingUsed(true);
		DF.setGroupingSize(3);
		JFrame parent = new JFrame();
		JButton button = new JButton();
		String[] options1 = {"BUY"};
		int choose = JOptionPane.showOptionDialog(parent, "To start your journey, buy a lemonadestand by using the BUY option", null, 
				JOptionPane.YES_OPTION, JOptionPane.PLAIN_MESSAGE, null, options1, null);
		if(choose == JOptionPane.YES_OPTION) {
		new TycoonGame();
		while(true) {
		String[] options2 = {"Lemonade Stand: $" + DF.format(Player1.getLemonadeStandsCost()), "Store: $" + DF.format(Player1.getStoresCost()), "Hotels: $" + DF.format(Player1.getHotelsCost())};
			int buy = JOptionPane.showOptionDialog(null, "What would you like to buy?", null, JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE, null, options2, null);

			if(JOptionPane.YES_OPTION == buy) {
				Player1.buyLemonadeStands();
			}
			if(JOptionPane.NO_OPTION == buy) {
				Player1.buyStores();
			}
			if(JOptionPane.CANCEL_OPTION == buy) {
				Player1.buyHotels();
			}
			AB.set(true);	
			}
		}
	}
}
