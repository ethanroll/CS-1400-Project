import java.util.Scanner;

public class RestaurantCheckManager {
	
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		boolean quit = false;
		String quitPrompt;
		double saleAmount;
		double tipAmount;
		double totalAmount;
		double totalSaleAmount = 0;
		double totalTipAmount = 0;
		int numberOfChecks = 0;
		
		while (!quit) {
			System.out.println("Total sale amount:");
			saleAmount = scnr.nextDouble();
			System.out.println("Tip amount:");
			tipAmount = scnr.nextDouble();
			System.out.println("Total amount:");
			totalAmount = scnr.nextDouble();
			
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
			quitPrompt = scnr.next();
			if (quitPrompt.equals("y"))
				quit = true;
		}
		scnr.close();
		System.out.println("The total sale amount is: " + totalSaleAmount);
		System.out.println("The total pooled tip amount is: " + totalTipAmount);
	}
}
