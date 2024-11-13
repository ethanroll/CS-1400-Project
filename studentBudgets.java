package SRC_7;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.StringWriter;
import java.io.PrintWriter;

public class studentBudgets {
	
	public static void formattingData () {
		double[] wellBehavedData = {5.22, -10.01, 0.05, -1.01, 7.42, -10.50, 5000.10, -2010.20};
		
		for (double data : wellBehavedData) {
			if (data < 0) {
				System.out.printf("$(%7.2f)\n", Math.abs(data));
			}
			else {
				System.out.printf("$ %7.2f \n", data);
			}
		}
	}
	
	public static void inputtingData (Scanner scnr) {
		String itemName;
		double itemValue;
		StringWriter dataStream = new StringWriter();
		PrintWriter dataOSS = new PrintWriter(dataStream);
		
		while (true) {
			System.out.println("Please enter the name of an income item or expense item");
			itemName = scnr.nextLine();
			
			if (itemName.length() == 0) { //checks if itemName is empty
				break;
			}
		
			System.out.println("Please enter the value of your item");
			System.out.println("Refrain from using dollar signs, and use decimals for cents if needed");
			System.out.println("Ex: -40.40 or 0.30");
			itemValue = scnr.nextDouble();
			scnr.nextLine(); //consumes new line after nextDouble
			
			if (itemValue < 0) {
				dataOSS.printf(itemName + "\t$(%7.2f)\n", Math.abs(itemValue));
			}
			else {
				dataOSS.printf(itemName + "\t$ %7.2f \n", (itemValue));
			}
		}
		System.out.println(dataStream);
		scnr.close();
	}
	
	public static void filteringData (Scanner scnr) {
		String itemName;
		int maxNameLength = 9;
		double itemValue;
		StringWriter allData = new StringWriter();
		PrintWriter dataOSS = new PrintWriter(allData);
		StringWriter incomeStream = new StringWriter();
		PrintWriter incomeOSS = new PrintWriter(incomeStream);
		double incomeSubtotal = 0.0;
		StringWriter expenseStream = new StringWriter();
		PrintWriter expenseOSS = new PrintWriter(expenseStream);
		double expenseSubtotal = 0.0;
		double total;
		
		while (true) {
			System.out.println("Please enter the name of an income item or expense item");
			itemName = scnr.nextLine();
			
			if (itemName.length() == 0) { //checks if itemName is empty
				break;
			}
			if (itemName.length() > maxNameLength) {
				maxNameLength = itemName.length();
			}
			dataOSS.println(itemName);
		
			System.out.println("Please enter the value of your item");
			System.out.println("Refrain from using dollar signs, and use decimals for cents if needed");
			System.out.println("Ex: -40.40 or 0.30");
			
			while (true) {
				try {
					itemValue = scnr.nextDouble();
					scnr.nextLine(); //consumes new line after nextDouble
					
					if ((int)(itemValue * 100) != (itemValue * 100) && (int)(itemValue * 10) != (itemValue * 10)) { //enters if statement if input has more than two decimal points
						System.out.println("Input does not make sense as cents"); //the input has more than two decimal points
						continue;
					}
					if (itemValue >= 10000) { //enters if more than 4 digits of dollars
						System.out.println("Input is too large");
						continue;
					}
					break;
				}
				catch (InputMismatchException e) { //includes dollar signs
					System.out.println("Value must be a number.  Please try again.");
					scnr.nextLine();
				}
				catch (Exception e) {
					System.out.println("There was something wrong with your input.  Please try again.");
					scnr.nextLine();
				}
			}
			dataOSS.println(itemValue);
		}
		
		Scanner parseData = new Scanner(allData.toString());
		
		while (parseData.hasNext()) {
			itemName = parseData.nextLine();
			String itemValueString = parseData.next();
			parseData.nextLine(); //consume \n
			itemValue = Double.valueOf(itemValueString); //found on the internet
			
			if (itemValue < 0) {
				expenseOSS.printf("%-" + maxNameLength + "s", itemName);
				expenseOSS.printf("\t$(%7.2f)\n", Math.abs(itemValue));
				expenseSubtotal += itemValue;
			}
			else {
				incomeOSS.printf("%-" + maxNameLength + "s", itemName);
				incomeOSS.printf("\t$ %7.2f \n", (itemValue));
				incomeSubtotal += itemValue;
			}
		}
		parseData.close();
		
		System.out.print("Income:\n" + incomeStream);
		System.out.printf("%-" + maxNameLength + "s", "Subtotal:");
		System.out.printf("\t$ %7.2f \n", incomeSubtotal);
		System.out.println();
		
		System.out.printf("Expenses:\n" + expenseStream);
		System.out.printf("%-" + maxNameLength + "s", "Subtotal:");
		System.out.printf("\t$(%7.2f)\n", Math.abs(expenseSubtotal));
		System.out.println();
		
		System.out.printf("%-" + maxNameLength + "s\t$", "Total:");
		total = incomeSubtotal + expenseSubtotal;
		if (total < 0) {
			System.out.printf("(%7.2f)\n", Math.abs(total));
		}
		else {
			System.out.printf(" %7.2f \n", total);
		}
	}
	
	public static void main(String[] args) {
		Scanner scnr = new Scanner(System.in);
		//some methods are commented for testing purposes
		
		//formattingData();
		//inputtingData(scnr);
		filteringData(scnr);
		
		scnr.close();
	}


}
