package shoppingcart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class ShoppingCart {

	private static ArrayList<Double> costOfItem = new ArrayList<>();
	private static ArrayList<String> userItems = new ArrayList<>();
	private static Scanner scnr;
	private static Map<String, Double> items = new TreeMap<>();
	private static Map<Integer, String> menu = new HashMap<>();

	public static void main(String[] args) {
		scnr = new Scanner(System.in);
		fillItemsMap();
		orderingLoop();
		userList();

		int maxPriceIndex = findMaxPriceIndex(costOfItem);
		int minPriceIndex = findMinPriceIndex(costOfItem);

		System.out.printf("The average price per item in this order was $%-20.2f\n", averagePrice(costOfItem));

		System.out.println("The most expensive item bought was " + userItems.get(maxPriceIndex)
				+ " and that item costs $" + costOfItem.get(maxPriceIndex));

		System.out.println("The least expensive item bought was " + userItems.get(minPriceIndex)
				+ " and that item costs $" + costOfItem.get(minPriceIndex));

		scnr.close();
	}

	private static void fillItemsMap() {
		items.put("apples", .99);
		items.put("oranges", .89);
		items.put("blackberries", 1.99);
		items.put("strawberries", 2.99);
		items.put("bananas", .59);
		items.put("dragonfruit", 2.50);
		items.put("melons", 1.99);
		items.put("grapes", 2.99);
		items.put("lemons", .79);
		items.put("cherries", 1.29);

		int i = 0;

		for (String itemName : items.keySet()) {
			menu.put(i, itemName);
			i++;
		}
	}

	private static void printMenu() {
		System.out.printf("%-20s %-20s %-20s\n", "Item Number", "Item", "Price");
		System.out.println("====================================================");
		for (Map.Entry<Integer, String> entry : menu.entrySet()) {
			int itemNumber = entry.getKey();
			String itemName = entry.getValue();
			double itemPrice = items.get(itemName);

			System.out.printf("%-20d %-20s $%-20.2f\n", itemNumber, itemName, itemPrice);
		}
	}

	private static void orderingLoop() {
		boolean shopping = true;
		while (shopping) {
			printMenu();
			System.out.println();
			System.out.print("What item would you like to order? ");

			try {
				int itemNumber = scnr.nextInt();
				scnr.nextLine();
				if (menu.containsKey(itemNumber)) {
					String itemName = menu.get(itemNumber);
					System.out.println("Adding " + itemName + " to cart at $" + items.get(itemName));
					System.out.println();
					userItems.add(itemName);
					costOfItem.add(items.get(itemName));

					System.out.print("Would you like to order anything else? (y/n) ");
					String answer = scnr.nextLine();
					System.out.println();
					if (!answer.equals("y")) {
						shopping = false;
					}
				} else {
					System.out.println("Sorry, we don't have those. Please try again.");
					System.out.println();
				}
			} catch (InputMismatchException e) {
				scnr.nextLine();
				System.out.println("Sorry, that is an invalid input. Please enter a number.");
				System.out.println();
			}
		}
	}

	private static int findMaxPriceIndex(ArrayList<Double> costOfItem) {
		int maxIndex = 0;
		for (int i = 0; i < costOfItem.size(); i++) {
			if (costOfItem.get(i) > costOfItem.get(maxIndex)) {
				maxIndex = i;
			}
		}
		return maxIndex;
	}

	private static int findMinPriceIndex(ArrayList<Double> costOfItem) {
		int minIndex = 0;
		for (int i = 0; i < costOfItem.size(); i++) {
			if (costOfItem.get(i) < costOfItem.get(minIndex)) {
				minIndex = i;
			}
		}
		return minIndex;
	}

	private static double averagePrice(ArrayList<Double> costOfItem) {
		double total = 0;
		for (int i = 0; i < costOfItem.size(); i++) {
			total += costOfItem.get(i);
		}
		return total / costOfItem.size();
	}

	private static void userList() {
		System.out.println("Thanks for your order!");
		System.out.println("Here's what you got:");
		for (int i = 0; i < userItems.size(); i++) {
			System.out.printf("%-20s $%-20.2f\n", userItems.get(i), costOfItem.get(i));
		}
	}

}
