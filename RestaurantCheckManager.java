import java.util.Scanner;

public class RestaurantCheckManager {
	
	public static double getAmount(Scanner scnr) {
		String inputLine;
		inputLine = scnr.nextLine();
		if (inputLine.isBlank())
			return 0;
		try {
			return Double.valueOf(inputLine);
		}
		catch (NumberFormatException e) { //if the input cannot be converted to a double
			System.out.println("Input must be a number");
			return getAmount(scnr); //allows user to try again
		}
	}
	
	public static boolean ifQuitting(Scanner scnr) {
		String quitPrompt = scnr.next();
		scnr.nextLine(); //clears newline character
		if (quitPrompt.equalsIgnoreCase("y"))
			return true;
		if (quitPrompt.equalsIgnoreCase("n"))
			return false;
		System.out.println("Input must be \"y\" or \"n\"");
		return ifQuitting(scnr); //allows user to try again
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
			
			totalSaleAmount += saleAmount;
			totalTipAmount += tipAmount;
			numberOfChecks++;
			
			System.out.println("Check count: " + numberOfChecks);
			System.out.println("Total sale so far: " + totalSaleAmount);
			System.out.println("Total pooled tip so far: " + totalTipAmount);
			
			System.out.println("Do you want to stop (y,n):");
			quit = ifQuitting(scnr);	
		}
		scnr.close();
		System.out.printf("The total sale amount is: $%.2f\n", totalSaleAmount);
		System.out.printf("The total pooled tip amount is: $%.2f\n", totalTipAmount);
		System.out.println();
		printTipSplits(totalTipAmount);
	}
	
	public static void printTipSplits(double totalTipAmount) {
		final double PERCENT_SERVER = 0.70;
		final int NUM_SERVERS = 3;
		final double PERCENT_CHEF = 0.04;
		final double PERCENT_SOUS_CHEF = 0.03;
		final double PERCENT_KITCHEN_AID = 0.03;
		//for the sake of simplicity, host/hostess has been simplified to just host
		final double PERCENT_HOST = 0.10; 
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
