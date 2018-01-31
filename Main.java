package edu.upenn.cis350.hwk1;

import java.io.File;
import java.util.*;

public class Main {
	
	public static String statesCSV;
	public static String tweetsFile;
	public static String logFile;
	
	
	public static void main(String[] args) {
		
		try {
			if (args.length < 3 || args.length > 3 || args == null) {
				System.out.println("Incorrect number of run-time args");
				System.exit(0);
			}
			
			tweetsFile = args[0];
			statesCSV = args[1];
			logFile = args[2];
			
			
			String time = String.valueOf(System.currentTimeMillis());
			LogFile.write(time + " Program Start Args: " + tweetsFile + " " + statesCSV + " " + logFile);
			showMenu();
		}
		catch (Exception e) {
			System.out.println("Sorry, an error has occurred. Please restart the program.");
			String time = String.valueOf(System.currentTimeMillis());
			LogFile.write(time + " Program Start Args: " + statesCSV + " " + tweetsFile + " " + logFile);
			System.exit(0);
		}
		
	}
	
	public static void showMenu() {
		System.out.println("Press 1 to rank the states by the number of tweets\n"
				+ "Press 2 to show the most popular hashtags in a given state\n"
				+ "Press 3 to show the number of tweets per hour containing a given term\n"
				+ "Press 4 to quit\n");
		getInput();
	}

	public static void getInput() {
		Calculations calc = new Calculations();
		calc.readAll();
		
		Scanner input = new Scanner(System.in);
		if (!input.hasNextInt()) {
			System.out.println("That is not an option. Choose again.");
			String time = String.valueOf(System.currentTimeMillis());
			LogFile.write(time + " User Choice: " + "INVALID");
			showMenu();
		}
		int choice = input.nextInt();
		if (choice == 1) {
			String time = String.valueOf(System.currentTimeMillis());
			LogFile.write(time + " User Choice: " + choice);
			calc.numTweetsPerState();
		}
		else if (choice == 2) {
			String time = String.valueOf(System.currentTimeMillis());
			LogFile.write(time + " User Choice: " + choice);
			calc.hashtagsByState();
		}
		else if (choice == 3) {
			String time = String.valueOf(System.currentTimeMillis());
			LogFile.write(time + " User Choice: " + choice);
			calc.tweetsPerHour();
		}
		else if (choice == 4) {
			String time = String.valueOf(System.currentTimeMillis());
			LogFile.write(time + " User Choice: " + choice);
			LogFile.write(time + " Program ended.");
			System.out.println("Exiting program.");
			System.exit(0);
		}
		else { 
			System.out.println("That is not an option. Choose again.");
			String time = String.valueOf(System.currentTimeMillis());
			LogFile.write(time + " User Choice: " + "INVALID");
			showMenu();
		}
		showMenu();
	}
}