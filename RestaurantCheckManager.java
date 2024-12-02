package SRC_7;
import java.util.Scanner;

public class RestaurantCheckManager {
	
	public static double getAmount(Scanner scnr) {
		String inputLine = scnr.nextLine();
			//nextDouble would not allow the user to put a blank input
			//so nextLine must be used rather than nextDouble
		
		//accounting for edge cases
		if (inputLine.isBlank())
			return 0;
		try {
			double amount = Double.parseDouble(inputLine); //line that can cause error
			if (amount >= 0 && isCents(amount) && amount < Integer.MAX_VALUE)
				return amount;
			//integer minimum is not considered because amount is assumed to be positive by that point
			else if (amount < 0)
				System.out.println("Input must be positive");
			else if (!isCents(amount))
				System.out.println("Input does not make sense as cents");
			else if (amount > Integer.MAX_VALUE)
				System.out.println("Input is too large");
			return getAmount(scnr); //occurs after any else if statement
		}
		catch (NumberFormatException e) {
			System.out.println("Input must be a number");
			return getAmount(scnr); //allows user to try again
		}
	}
	
	public static boolean isCents(double amount) {
		return (amount * 100) % 1 >= 0.9999 || (amount * 100) % 1 <= 0.0001;
		//since doubles are not precise, a small margin of error has been provided
	}
	
	public static boolean isQuitting(Scanner scnr) {
		String quitPrompt = scnr.next();
		scnr.nextLine(); //clears newline character
		if (quitPrompt.equalsIgnoreCase("y"))
			return true;
		if (quitPrompt.equalsIgnoreCase("n"))
			return false;
		System.out.println("Input must be \"y\" or \"n\"");
		return isQuitting(scnr); //allows user to try again
	}
	
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		boolean quit = false;
		double saleAmount;
		double tipAmount;
		double totalAmount;
		double totalSaleAmount = 0;
		double totalTipAmount = 0;
		int numberOfChecks = 0;
		
		while (!quit) {
			System.out.println("Total sale amount:");
			saleAmount = getAmount(scnr);
			System.out.println("Tip amount:");
			tipAmount = getAmount(scnr);
			System.out.println("Total amount:");
			totalAmount = getAmount(scnr);
			
			//accounting for edge cases
			if (totalAmount < saleAmount) 
				totalAmount = saleAmount;
			if (saleAmount + tipAmount != totalAmount)
				tipAmount = totalAmount - saleAmount;
			//note that tipAmount is not changed to 0 in the first case because that would be handled by the second anyway
			
			totalSaleAmount += saleAmount;
			totalTipAmount += tipAmount;
			numberOfChecks++;
			
			System.out.println("Check count: " + numberOfChecks);
			System.out.printf("Total sale so far: $%.2f\n", totalSaleAmount);
			System.out.printf("Total pooled tip so far: $%.2f\n", totalTipAmount);
			//%.2f is used to ensure that only 2 decimal places are displayed as to make sense as cents
			
			System.out.println("Do you want to stop (y,n):");
			quit = isQuitting(scnr);
		}
		scnr.close();
		System.out.printf("The total sale amount is: $%.2f\n", totalSaleAmount);
		System.out.printf("The total pooled tip amount is: $%.2f\n", totalTipAmount);
		System.out.println();
		printTipSplits(totalTipAmount);
	}
	
	public static void printTipSplits(double totalTipAmount) {
		final double PERCENT_SERVER = 0.60;
		final int NUM_SERVERS = 3;
		final double PERCENT_CHEF = 0.08;
		final double PERCENT_SOUS_CHEF = 0.06;
		final double PERCENT_KITCHEN_AID = 0.06;
		final double PERCENT_HOST = 0.10; 
		//for the sake of simplicity, host/hostess has been shortened to just host
		final double PERCENT_BUSSER = 0.10;
		
		double tipPerServer = totalTipAmount * PERCENT_SERVER / NUM_SERVERS;
		System.out.printf("$%.2f goes to each server\n", tipPerServer);
		
		double tipChef = totalTipAmount * PERCENT_CHEF;
		System.out.printf("$%.2f goes to the chef\n", tipChef);
		
		double tipSousChef = totalTipAmount * PERCENT_SOUS_CHEF;
		System.out.printf("$%.2f goes to the sous-chef\n", tipSousChef);
		
		double tipKitchenAid = totalTipAmount * PERCENT_KITCHEN_AID;
		System.out.printf("$%.2f goes to the kitchen aid\n", tipKitchenAid);
		
		double tipHost = totalTipAmount * PERCENT_HOST;
		System.out.printf("$%.2f goes to the host/hostess\n", tipHost);
		
		double tipBusser = totalTipAmount * PERCENT_BUSSER;
		System.out.printf("$%.2f goes to the busser\n", tipBusser);
	}
}
