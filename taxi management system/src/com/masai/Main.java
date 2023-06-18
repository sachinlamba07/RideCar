package com.masai;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import java.util.Scanner;

import com.masai.entities.driver;
import com.masai.entities.idGenrator;
import com.masai.entities.passenger;
import com.masai.service.adminService;
import com.masai.utility.fileExist;
import com.masai.utility.invalidDetailException;



public class Main {

	public static void adminFunction(Scanner sc,Map<Integer,driver> driv,Map<String,passenger> passe) throws invalidDetailException {
		
		
		
		adminService adServ=new adminService();
		
		int choice=0;
		
		try {
			
			System.out.println("Press 1 Add the Driver");
			System.out.println("Press 2 Update Driver Details");
			System.out.println("Press 3 Remove the Driver");
			System.out.println("Press 4 View All Passengers");
			System.out.println("Press 5 Filter Ride by Driver");
			System.out.println("Press 6 to log out");
			choice = sc.nextInt();
			
			switch (choice) {
			case 1:
				String added = adminAddDriver(sc, driv, adServ);
				System.out.println(added);
				adminFunction(sc,driv,passe);
				
				break;
			case 2:
				adminUpdateDriver(sc,driv,adServ);
				adminFunction(sc,driv,passe);
				break;
			case 3:
				adminDeleteDriver(sc,driv,adServ);
				adminFunction(sc,driv,passe);
				
				break;
			case 4:
				viewAllDrivers(driv,adServ);
				adminFunction(sc,driv,passe);
				break;
			case 5:
				

				break;
			case 6:
				System.out.println("Admin has Sucessfully Logout ");
				break;
				
			default:
				System.out.println("Invalid choice");
			}
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}
	
	private static void adminUpdateDriver(Scanner sc, Map<Integer, driver> driv, adminService adServ) {
		// TODO Auto-generated method stub
		System.out.println("Enter id of Driver which is to be updated");
		int id=sc.nextInt();
		System.out.println("Enter Updated Details");
		System.out.println("Enter the Driver name");
		String name=sc.next();
		System.out.println("Select the Vechicle type");
		System.out.println("Press 1 SUV");
		System.out.println("Press 2 Sedan");
		System.out.println("Press 3 Prime Sedan");
		System.out.println("Press 4 Macro");
		System.out.println("Press 5 Mini");
		int type=sc.nextInt();
		String typeCar=null;
		switch (type) {
		case 1:
			typeCar="SUV";
			break;
		case 2:
			typeCar="Sedan";
			break;
		case 3:
			typeCar="Prime Sedan";			
			break;
		case 4:
			typeCar="Macro";		
			break;
		case 5:	
			typeCar="Micro";
			break;		
		}
		System.out.println("Enter Registraion Number of 5 digits");
		int regNo=sc.nextInt();
		System.out.println("Availblity Status---> 1. Online  2. Ofline");
		 type=sc.nextInt();
		String typeAvb=null;
		switch (type) {
		case 1:
			typeAvb="Online";
			break;
		case 2:
			typeAvb="Ofline";
			break;
		}
		driver d1 = new driver(id, name, typeCar, regNo, typeAvb);

		adServ.updateDriverData(id,d1, driv);
		//return str;
	}

	private static void adminDeleteDriver(Scanner sc, Map<Integer, driver> driv, adminService adServ) {
		// TODO Auto-generated method stub
		System.out.println("Enter id of Driver");
		int id=sc.nextInt();
		
		adServ.deleteDriver(id,driv);
		
	}

	private static void viewAllDrivers(Map<Integer, driver> driv, adminService adServ) {
		// TODO Auto-generated method stub
		adServ.viewDriver(driv);
	}

	private static String adminAddDriver(Scanner sc, Map<Integer, driver> d1, adminService adServ) {
		
		System.out.println("Enter the Driver name");
		String name=sc.next();
		System.out.println("Select the Vechicle type");
		System.out.println("Press 1 SUV");
		System.out.println("Press 2 Sedan");
		System.out.println("Press 3 Prime Sedan");
		System.out.println("Press 4 Macro");
		System.out.println("Press 5 Mini");
		int type=sc.nextInt();
		String typeCar=null;
		switch (type) {
		case 1:
			typeCar="SUV";
			break;
		case 2:
			typeCar="Sedan";
			break;
		case 3:
			typeCar="Prime Sedan";			
			break;
		case 4:
			typeCar="Macro";		
			break;
		case 5:	
			typeCar="Micro";
			break;		
		}
		System.out.println("Enter Registraion Number of 5 digits");
		int regNo=sc.nextInt();
		System.out.println("Availblity Status---> 1. Online  2. Ofline");
		 type=sc.nextInt();
		String typeAvb=null;
		switch (type) {
		case 1:
			typeAvb="Online";
			break;
		case 2:
			typeAvb="Ofline";
			break;
		}
		
		driver driv = new driver(idGenrator.generateId(), name, typeCar, regNo, typeAvb);

		String str = adServ.addDriver(driv, d1);
		return str;
	}

	public static void adminLogin(Scanner sc) throws invalidDetailException {
		System.out.println("Admin Login Page\n");
		System.out.println("Enter the username");
		String user=sc.next();
		System.out.println("Enter the password");
		int pass=sc.nextInt();
		if(user.equals("admin") && pass==1234)
		{
			System.out.println("Successfully Login");
		}
		else
		{
			throw new invalidDetailException("Invalid Admin Credentials");
			//System.out.println("Invalid Admin Credentials");
		}
		
	}
	
	
	public static void main(String[] args) throws invalidDetailException {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		
		Map<Integer,driver> driv=fileExist.driverFile();
		Map<String,passenger> passe=fileExist.passengerFile();
		
		System.out.println("Welcome to Cab Management System");
			
		try {
			int choice=0;
			System.out.println("Please Enter your Prefrence");
			System.out.println("1.--> Admin");
			System.out.println("2.--> Driver");
			System.out.println("3.--> Customer");
			System.out.println("0.--> Exit");
			
			 choice=sc.nextInt();
			
			if(choice==1) {
				//System.out.println("admin");
				adminLogin(sc);
				adminFunction(sc,driv,passe);
			}
			else if(choice==2)
			{
				System.out.println("driver");
			}
			else if(choice==3)
			{
				System.out.println("custumer");
			}
			else if(choice==0)
			{
				System.out.println("Sucessfully Exited from the System");
			}
			else
			{
				System.out.println("Invalid entry Please enter correct number");
			}
			
		} finally {
			// TODO: handle finally clause
			try {
				ObjectOutputStream poos = new ObjectOutputStream(new FileOutputStream("driver.ser"));
				poos.writeObject(driv);
				ObjectOutputStream coos = new ObjectOutputStream(new FileOutputStream("passenger.ser"));
				coos.writeObject(passe);

			//	System.out.println("serialized..........");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
		}
		
//		finally {
//			// serialization (finally always executed)
//			
//		}
	}

}
