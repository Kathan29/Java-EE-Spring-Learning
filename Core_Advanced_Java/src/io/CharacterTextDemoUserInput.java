package io;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class CharacterTextDemoUserInput {
	public static void main(String[] args) throws IOException {
		bufferReaderDemo();
		userInputUsingScanner();
		differentInputFromScanner();
	}

	private static void differentInputFromScanner() {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Enter int");
		int data = sc.nextInt();
		System.out.println("Entered data: "+data);
		
		System.out.println("Enter double");
		double dbData = sc.nextDouble();
		System.out.println("Entered double data: "+dbData);
		
		System.out.println("Enter long");
		long lgData = sc.nextLong();
		System.out.println("Entered long data: "+lgData);
		
		System.out.println("Enter string");
		String sData = sc.next();
		System.out.println("Entered String data: "+sData);
		
		System.out.println("Enter int or double or string");
		if(sc.hasNextInt())
			System.out.println("It is integer.");
		else if(sc.hasNextDouble())
			System.out.println("It is double");
		else if(sc.hasNext())
			System.out.println("It is string");
			
		
		sc.close();
		
	}

	private static void userInputUsingScanner() {
		Scanner sc = new Scanner(System.in);
		String data;
		System.out.println("Enter \"start\" to continue (Using Scannner): ");
		
		while(!(data=sc.nextLine()).equals("start")) {
			System.out.println("You entered: "+data+" , to stop print start.");
		}
		
		System.out.println("Start Printed successfully\n");
		sc.close();
	}

	private static void bufferReaderDemo() throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in,"UTF-8"));
		String data;
		
		System.out.println("Enter \"start\" to continue (Using BufferedReader): ");
		
		while(((data=br.readLine())!=null) && (!data.equals("start")))  {
			System.out.println("You entered: "+data+" , to stop print start.");
		}
		
		System.out.println("Start Printed successfully\n");
		
	}
}
