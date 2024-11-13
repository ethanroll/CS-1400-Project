import java.util.Scanner;

public class RestaurantCheckManager {
	/**
	 * Start by creating a class called RestaurantCheckManager
	 * Write a loop that keeps running until the manager the manager asks to terminate
	 * Inside the loop, prompt the manager to enter the sale amount
	 * Then prompt the manager to enter the tip amount
	 * Finally, prompt the manager to enter the total amount
	 * Calculate the total sale amount, the total tip amount, and the number of checks
	 * Pay attention to the edge cases listed above
	 * Ask the manager if they want to stop
	 * If they type ‘y’ or ‘Y’, terminate the loop
	 * Else continue
	 * After that the program should display the total sale amount and the total tip amount
	 */
	
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		boolean quit = false;
		double saleAmount;
		double tipAmount;
		double totalAmount;
		double totalSaleAmount = 0;
		double totalTipAmount = 0;
		int numberOfChecks = 0;
		
		while (true) {
			System.out.println("Total sale amount:");
			saleAmount = scnr.nextDouble();
			System.out.println("Tip amount:");
			tipAmount = scnr.nextDouble();
			System.out.println("Total amount:");
			totalAmount = scnr.nextDouble();
			//FIXME: should also account for InputMismatchException
			
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
			
			//FIXME: add quit function + final amounts
			
		}
	}
}
