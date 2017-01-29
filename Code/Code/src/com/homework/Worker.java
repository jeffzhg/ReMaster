package com.homework;
import java.util.Scanner;

public class Worker {
	private static Scanner inputScanner;
	private static String inputText;
		
	public Worker() {
		super();
		inputScanner = new Scanner(System.in);
		// TODO Auto-generated constructor stub
	}

	public Worker(Scanner inputScanner) {
		super();
		this.inputScanner = inputScanner;
	}

	public static void work(){
		try {
			System.out.println("Ready to work!");	
			doSearchWork();		
		} catch (Exception ex) {
			System.out.println("Catch all is a false sense of security.");
		}
		
		
	}

	public static void defineWord(String query) throws InterruptedException {
		
		try {

			String definition = "";
			Dictionary dt = new Dictionary();
			definition = dt.findDefinitionInDict(inputText);

			if ("" == definition) {
				System.out.println("Dictionary isn't large enough, can't find definition for: " + inputText);
			} else {
				System.out.println("Summarizing meaning for " + inputText);
				for (int i = 3; i > 0; i--) {
					System.out.println(i);
					Thread.sleep(1000);
				}
				System.out.println(query + " means the following:");
				System.out.println(definition);
			}
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
			System.out.println("Sorry I was interrupted, where were we?");
		}
		
		
	}
	
	
	public static void doSearchWork() throws InterruptedException {
		
		try {

			while (true) {
				
				System.out.println("Give me a word, I can find it for you.");
				inputText = inputScanner.nextLine().toString();
				System.out.println("Searching, sit tight...");
				
				for (int i = 3; i > 0 ; i--) {
					Thread.sleep(1000);
					System.out.println(i);
				}
				
				WordBag wb = new WordBag();
				long location = wb.findWordInMyBag(inputText);
	
				if (-1 == location) {
					System.out.println("Didn't find the word" + inputText);
				} else {
					System.out.println("Found it!");
					System.out.println(inputText + " was hiding at line #" + location);
					defineWord(inputText);
					break;
				}
			}
			
		} catch (InterruptedException ex) {
			Thread.currentThread().interrupt();
			System.out.println("Sorry I was interrupted, where were we?");
		}
		
		
	}
	
}
