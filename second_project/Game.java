package Tycoon;

import java.awt.BorderLayout;

import java.util.Timer;
import java.util.TimerTask;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;

/**
 * 
 * @author Tin Ngo
 * 
 * 
 *         COMPLETED
 *
 *         USE READ and WRITE file - Could read off NAME, Price, and Profit to
 *         construct each store
 * 
 *         * Include how much time as been played, Balance, total profits,
 *         purchase History
 * 
 *         * Make GUI user friendly and pretty C: Include PREVIOUS And next
 *         buttons
 * 
 *         * Adding new stores to the game should be very easy - Include for
 *         loops to read and include everything to make it easy - Create New
 *         Store, Button, Label, Count for each store,
 * 
 *         * Find the best store to buy (Ask a financial advisor) highest return
 *         for the price.
 */

//IDEAS
/*
 * Next Tasks:
 * 
 */
/*
 * Good use of classes USE INHERITANCE
 *
 */
/*
 * Create a memory video game - Writes the files so that data is stored and can
 * resume from the next game. Create a method to update data every second so
 * that balance is saved too
 */

/*
 * Stores should be set visible only when there is enough money to buy the
 * store, otherwise there is just a label of the store name
 */

/*
 * Sorting method to make the costs of each store in order from least to
 * greatest cost. - Could do this several ways. One way I could take whatever is
 * in the file and put it into an array and sort it. Another way I could do is
 * to put it into a sorted array then rewrite the file.
 */

public class Game implements ActionListener {

	// Index of all stores which includes their every function
	private ArrayList<CreateStore> stores = new ArrayList<CreateStore>();
	// Index of all the panels. So we can add as many stores as we want and there
	// will be next and previous buttons for all the stores.
	private ArrayList<JPanel> panels = new ArrayList<JPanel>();

	private int count = 0;
	private int time = 0;
	private int panelSelect = 0;

	private double balance = 20;
	private double totalProfits = 0;

	private JFrame frame;

	private JPanel sidePanel, bottomPanel, mainPanel, headers;

	private JLabel balanceLabel, totalProfit, totalTime;

	private JButton financialAdvisor, nextButton, previousButton;

	static final JLabel storeTitle = new JLabel("Properties");
	static final JLabel storePriceTitle = new JLabel("Price");
	static final JLabel storeCountTitle = new JLabel("Count");

	private DecimalFormat DF = new DecimalFormat("$" + "#,###.##");

	private Timer timer;

	public Game() throws IOException {

		frame = new JFrame();

		mainPanel = new JPanel();

		// information labels and button for the right side
		balanceLabel = new JLabel("Balance: " + DF.format(getBalance()));
		totalProfit = new JLabel("Total Profit: " + DF.format(getTotalProfit()));
		totalTime = new JLabel("Total Time Elapsed: " + getTime());

		financialAdvisor = new JButton("Click Here For a Financial Advisor!");
		nextButton = new JButton("Next");
		previousButton = new JButton("Previous");
		financialAdvisor.addActionListener(this);
		nextButton.addActionListener(this);
		previousButton.addActionListener(this);

		// Panel for the headers - just titles
		headers = new JPanel();
		headers.add(storeTitle);
		headers.add(storePriceTitle);
		headers.add(storeCountTitle);
		headers.setLayout(new GridLayout(0, 4));

		// Panel on the right displaying information
		sidePanel = new JPanel();
		sidePanel.add(balanceLabel);
		sidePanel.add(totalProfit);
		sidePanel.add(totalTime);
		sidePanel.add(financialAdvisor);
		sidePanel.setLayout(new GridLayout(4, 0));

		// Panel on the bottom for next and previous buttons
		bottomPanel = new JPanel();
		bottomPanel.add(previousButton);
		bottomPanel.add(nextButton);
		bottomPanel.setLayout(new GridLayout(1, 2));

		// File that has all the stores - add more if you want >:)
		File storeFile = new File("../../../eclipse-workspace/Com S 227/TycoonGameImproved/src/Tycoon/StoreType.txt");
		Scanner read = new Scanner(storeFile);

		// Reads into the file to create and add to stores into the array list
		String findName = "";
		while (read.hasNext()) {
			findName += read.next() + " ";
			if (read.hasNextDouble()) {
				JButton tempButtons = new JButton("BUY: " + findName);
				CreateStore temp = new CreateStore(findName, read.nextDouble(), read.nextDouble(), 0, tempButtons);
				temp.getButton().addActionListener(this);
				stores.add(temp);

				mainPanel.add(temp.getButton());
				mainPanel.add(temp.getLabelPrice());
				mainPanel.add(temp.getLabelCount());

				count++;
				if (count >= 5) {
					panels.add(mainPanel);
					mainPanel = new JPanel();
					count = 0;
				}
				findName = "";
			}
		}

		// adds the remaining stores into the last panel
		if (mainPanel.countComponents() > 0) {
			panels.add(mainPanel);
		}

		panels.get(panelSelect).setLayout(new GridLayout(panels.get(panelSelect).getComponentCount() / 3, 3));

		// putting all of the panels into the frame to make the GUI
		frame.add(headers, BorderLayout.NORTH);
		frame.add(panels.get(panelSelect), BorderLayout.WEST);
		frame.add(sidePanel, BorderLayout.EAST);
		frame.add(bottomPanel, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Tins Capital!");
		frame.setSize(800, 800);
		frame.setVisible(true);

		// Timer that ticks every second to start the game.
		timer = new Timer(true);
		timer.schedule(new time(), 0, 1000);
	}

	class time extends TimerTask {
		@Override
		public void run() {
			balanceLabel.setText("Balance: " + DF.format(getBalance()));
			totalTime.setText("Total Time Elapsed: " + getTime());
			balance += getTotalProfit();
			time++;
			
		}
	}

	// Method to calculate the total profit given the amount of stores you have
	public double getTotalProfit() {
		totalProfits = 0;
		for (int i = 0; i < stores.size(); i++) {
			totalProfits += stores.get(i).getTotalProfit();
		}
		return totalProfits;
	}

	// Retrieves how much money the user has
	public double getBalance() {
		return balance;
	}

	// The amount of time (in seconds) spent playing the game
	public int getTime() {
		return time;
	}

	// Refreshes the game
	public void refresh() {
		frame.setVisible(false);
		frame.setVisible(true);
	}

	// When the a button is pressed, something happens (:
	@Override
	public void actionPerformed(ActionEvent e) {
		// If the next button gets pressed, a new set of stores may be bought
		if (e.getSource() == nextButton && panelSelect != panels.size() - 1) {
			frame.remove(panels.get(panelSelect));
			panelSelect++;
			panels.get(panelSelect).setLayout(new GridLayout(panels.get(panelSelect).getComponentCount() / 3, 3));
			frame.add(panels.get(panelSelect), BorderLayout.WEST);
			refresh();
			// If the previous button gets pressed, a new set of stores may be bought
		} else if (e.getSource() == previousButton && panelSelect != 0) {
			frame.remove(panels.get(panelSelect));
			panelSelect--;
			panels.get(panelSelect).setLayout(new GridLayout(panels.get(panelSelect).getComponentCount() / 3, 3));
			frame.add(panels.get(panelSelect), BorderLayout.WEST);
			refresh();
		}
		if (e.getSource() == financialAdvisor) {
			JFrame frameFinancial = new JFrame();
			String bestBuy = "";
			double max = 0;
			for (int i = 0; i < stores.size(); i++) {
				if (max < stores.get(i).getProfit() / stores.get(i).getPrice()) {
					max = stores.get(i).getProfit() / stores.get(i).getPrice();
					bestBuy = stores.get(i).getName();
				}
			}
			JLabel labelFinancial = new JLabel(
					"Your financial advisor advises you to buy a " + bestBuy + " to maximize returns.");
			frameFinancial.add(labelFinancial);
			frameFinancial.setTitle("Tins Capital!");
			frameFinancial.setSize(700, 100);
			frameFinancial.setVisible(true);
		}
		// If the store buy button is pressed, then it buys the store, updating the
		// total profit, the amount of stores, etc.
		for (int i = 0; i < stores.size(); i++) {
			if (stores.get(i).getButtonName().equals(e.getActionCommand())) {
				if (stores.get(i).buyStore(balance)) {
					balance -= stores.get(i).getPrice();
					stores.get(i).getLabelCount().setText("" + stores.get(i).getCount());
					stores.get(i).getLabelPrice().setText(DF.format(stores.get(i).getPrice()));
					totalProfit.setText("Total Profit: " + DF.format(getTotalProfit()));
				}
			}
		}
	}

	// Main method where the game is started.
	public static void main(String[] args) throws IOException {
		// Create load and new game option GUI then put a "add store button" with
		// password. After adding new info into the store, it will sort it into the
		// store type txt with file writer and it will also update what is in the loaded
		// file to save progess.
		File f = new File("Test.txt");
		Scanner sc = new Scanner(f);
		FileWriter fw = new FileWriter(f, true);
		PrintWriter pw = new PrintWriter(fw);

		new Game();
	}
}
