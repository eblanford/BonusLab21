
/*
 * Emily Blanford
 * 2/25/2018
 * Bonus Lab 21: Make a shopping list application which uses collections to store your items
 */

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Scanner;

public class BonusLab21 {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		String keepGoing = "y";
		ArrayList<String> orderedItem = new ArrayList<String>();
		ArrayList<Double> orderedPrice = new ArrayList<Double>();

		// Creates hashtable with items
		Hashtable<String, Double> menu = new Hashtable<String, Double>();
		menu.put("apple", 0.99);
		menu.put("banana", 0.59);
		menu.put("cauliflower", 1.59);
		menu.put("dragonfruit", 2.19);
		menu.put("elderberry", 1.79);
		menu.put("figs", 2.09);
		menu.put("grapefruit", 1.99);
		menu.put("honeydew", 3.49);

		System.out.println("Welcome to Guenther's Market!\n");

		// Loop through menu until user wants to exit
		do {
			printMenu(menu);
			System.out.print("\nWhat item would you like to order? ");
			String item = scan.next();

			if (menu.containsKey(item)) {
				orderedItem.add(item);
				orderedPrice.add(menu.get(item));
				System.out.println("Adding " + item + " to cart at $" + menu.get(item));
			} else {
				System.out.println("Sorry, we don't have those. Please try again\n");
				continue;
			}

			keepGoing = Validator.getString(scan, "\nWould you like to order anything else? (y/n) ", "y", "n");
		} while (keepGoing.equalsIgnoreCase("y"));

		printCart(orderedItem, orderedPrice);

	}

	// method to display menu
	public static void printMenu(Hashtable menu) {
		Enumeration<String> names = menu.keys();
		System.out.printf("%-20s %-10s\n", "Item", "Price");
		System.out.println("=====================================");
		while (names.hasMoreElements()) {
			String key = (String) names.nextElement();
			System.out.printf("%-20s $%-10.2f\n", key, menu.get(key));
		}
	}

	public static void printCart(ArrayList<String> item, ArrayList<Double> price) {
		System.out.println("\nThanks for your order!");
		System.out.println("Here's what you got:");
		for (int i = 0; i < item.size(); i++) {
			System.out.printf("%-20s $%-10.2f\n", item.get(i), price.get(i));
		}
		System.out.printf("Average price per item in order was $%.2f\n", getAverage(price));
		System.out.printf("The most expensive item ordered was %s at $%.2f\n", item.get(getHighest(item, price)),
				price.get(getHighest(item, price)));
		System.out.printf("The least expensive item ordered was %s at $%.2f\n", item.get(getLowest(item, price)),
				price.get(getLowest(item, price)));
	}

	public static double getAverage(ArrayList<Double> price) {
		double sum = 0.0;
		for (int i = 0; i < price.size(); i++) {
			sum += price.get(i);
		}
		return sum / price.size();
	}

	public static int getHighest(ArrayList<String> item, ArrayList<Double> price) {
		int j = 0;
		for (int i = 1; i < price.size(); i++) {
			if (price.get(i) > price.get(j)) {
				j = i;
			}
		}
		return j;
	}

	public static int getLowest(ArrayList<String> item, ArrayList<Double> price) {
		int j = 0;
		for (int i = 1; i < price.size(); i++) {
			if (price.get(i) < price.get(j)) {
				j = i;
			}
		}
		return j;
	}
}
