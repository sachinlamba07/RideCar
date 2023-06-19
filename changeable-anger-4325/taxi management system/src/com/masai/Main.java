package com.masai;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.masai.entities.RideData;
import com.masai.entities.driver;
import com.masai.entities.idGenrator;
import com.masai.entities.passenger;
import com.masai.service.adminService;
import com.masai.service.customerService;
import com.masai.utility.fileExist;
import com.masai.utility.invalidDetailException;



public class Main {

	public static void adminFunction(Scanner sc,Map<Integer,driver> driv,Map<String,passenger> passe, List<RideData> rideData) throws invalidDetailException {
		
		
		
		adminService adServ=new adminService();
		
		int choice=0;
		
		try {
			
			System.out.println("Press 1 Add the Driver");
			System.out.println("Press 2 Update Driver Details");
			System.out.println("Press 3 Remove the Driver");
			System.out.println("Press 4 View All Drivers Data");
			System.out.println("Press 5 View all ride bookings made by passengers");
			System.out.println("Press 6 View All Passenger Data");
			System.out.println("Press 7 Filter Rides on basis of DriverId or PassengerName");
			System.out.println("Press 8 to log out");
			choice = sc.nextInt();
			
			switch (choice) {
			case 1:
				String added = adminAddDriver(sc, driv, adServ);
				System.out.println(added);
				adminFunction(sc,driv,passe, rideData);
				
				break;
			case 2:
				adminUpdateDriver(sc,driv,adServ);
				adminFunction(sc,driv,passe, rideData);
				break;
			case 3:
				adminDeleteDriver(sc,driv,adServ);
				adminFunction(sc,driv,passe, rideData);
				
				break;
			case 4:
				viewAllDrivers(driv,adServ);
				adminFunction(sc,driv,passe, rideData);
				break;
			case 5:
				if(rideData.size()>0) {
					System.out.println(rideData);
				}
				else
				{
					System.out.println("No data Available");
				}
				
				adminFunction(sc,driv,passe, rideData);
				break;
			case 6:
				viewAllPassenger(passe,adServ);
				adminFunction(sc,driv,passe, rideData);
				break;
			case 7:
				filterData(sc,rideData,driv,passe);
				break;
				
				
			case 8:
				
				System.out.println("Admin has Sucessfully Logout ");
				main(null);
				break;
			default:
				System.out.println("Invalid choice");
				adminFunction(sc, driv, passe, rideData);
			}
			
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
	}


	private static void filterData(Scanner sc, List<RideData> rideData, Map<Integer, driver> driv, Map<String, passenger> passe) throws invalidDetailException {
		// TODO Auto-generated method stub
		System.out.println("Press 1 Filter by Driver Id\nPress 2 Filter by PassengerName");
		int choice=0;
		choice=sc.nextInt();
		if(choice==1)
		{
			System.out.println("Enter Driver Id");
			int id=sc.nextInt();
			List<RideData> dvFilter =new ArrayList<>();
			adminService ad=new adminService();
			dvFilter=ad.filterDataById(id,rideData);
			if(dvFilter.size()>0)
			{
				System.out.println(dvFilter);
				adminFunction(sc, driv, passe, rideData);
			}
			else
			{
				System.out.println("No Data Found");
				adminFunction(sc, driv, passe, rideData);
			}
		}
		else if(choice==2){
			System.out.println("Enter Passenger UserName");
			String id=sc.next();
			List<RideData> dvFilter =new ArrayList<>();
			adminService ad=new adminService();
			dvFilter=ad.filterDataByName(id,rideData);
			if(dvFilter.size()>0)
			{
				System.out.println(dvFilter);
			}
			else
			{
				System.out.println("No Data Found");
			}
		}
		else
		{
			System.out.println("Invalid Choice");
			adminFunction(sc, driv, passe, rideData);
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
	
	private static void viewAllPassenger(Map<String, passenger> passe, adminService adServ) {
		// TODO Auto-generated method stub
		adServ.viewPassenger(passe);
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
		
		Map<Integer, driver> driv = loadDriverData();
		
		Map<String,passenger> passe=fileExist.passengerFile();
		List<RideData> rideData = fileExist.transactionFile();
		System.out.println("Welcome to Cab Management System");
		   driv = deserializeDriverData();
		try {
			int choice=0;
			System.out.println("Please Enter your Prefrence");
			System.out.println("1.--> Admin Login");
			System.out.println("2.--> Driver Login");
			System.out.println("3.--> Customer SignUp ");
			System.out.println("4.--> Customer Login ");
			System.out.println("0.--> Exit");
			
			 choice=sc.nextInt();
			
			if(choice==1) {
				//System.out.println("admin");
				adminLogin(sc);
				adminFunction(sc,driv,passe,rideData);
			}
			else if(choice==2)
			{
				if(loginDriver(sc,driv,passe))
				{
					driverFunction(sc,driv,passe,rideData);
				}
				else
				{
					main(args);
				}
				
			}
			else if(choice==3)
			{
				customerSignUp(sc,passe);
				 serializePassengerData(passe);
				main(args);
			}
			else if(choice==4)
			{
				String us=null;
				if(cusLogin(sc,passe,us))
				{
					customerLogin(sc,passe,driv,us,rideData);
				}
				
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
				serializeDriverData(driv);
				ObjectOutputStream coos = new ObjectOutputStream(new FileOutputStream("passenger.ser"));
				coos.writeObject(passe);
				ObjectOutputStream toos = new ObjectOutputStream(new FileOutputStream("Ride.ser"));
				toos.writeObject(rideData);
			//	System.out.println("serialized..........");
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println(e.getMessage());
			}
		}

	}
	private static Map<Integer, driver> deserializeDriverData() {
	    try {
	        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("driver.ser"));
	        Map<Integer, driver> driv = (Map<Integer, driver>) ois.readObject();
	        ois.close();
	        return driv;
	    } catch (Exception e) {
	        System.out.println("Error occurred while deserializing driver data: " + e.getMessage());
	        return new HashMap<>();
	    }
	}
	private static void serializePassengerData(Map<String, passenger> passe) {
	    try {
	        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("passenger.ser"));
	        oos.writeObject(passe);
	        oos.close();
	        System.out.println("Passenger data serialized and stored successfully.");
	    } catch (IOException e) {
	        System.out.println("Error occurred while serializing passenger data: " + e.getMessage());
	    }
	}

	private static Map<Integer, driver> loadDriverData() {
        Map<Integer, driver> driv = null;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("driver.ser"))) {
            driv = (Map<Integer, driver>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error occurred while loading driver data: " + e.getMessage());
            driv = new HashMap<>(); // Create a new empty map if loading fails
        }
        return driv;
    }

    private static void serializeDriverData(Map<Integer, driver> driv) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("driver.ser"))) {
            oos.writeObject(driv);
            System.out.println("Driver data serialized and stored successfully.");
        } catch (IOException e) {
            System.out.println("Error occurred while serializing driver data: " + e.getMessage());
        }
    }
	private static void driverFunction(Scanner sc, Map<Integer, driver> driv, Map<String, passenger> passe, List<RideData> rideData) throws invalidDetailException {
		// TODO Auto-generated method stub
		System.out.println("Press 1  Update Personal Details");
		System.out.println("Press 2  Update Status");
		System.out.println("Press 3  View Ride History");
		System.out.println("Press 4  Accept or Decline Ride Request");
		System.out.println("Press 5  LogOut");
		int choice=sc.nextInt();
		switch (choice) {
		case 1:
			adminService adServ=new adminService();
			adminUpdateDriver(sc,driv,adServ);
			driverFunction(sc, driv, passe, rideData);
			break;
		case 2:
			updateStatus(sc,driv);
			driverFunction(sc, driv, passe, rideData);
			break;
		case 3:
			rideHistory(sc,rideData,driv,passe);
			driverFunction(sc, driv, passe, rideData);
			
			break;
		case 4:
			acceptRequest(sc,passe,driv,rideData);
			driverFunction(sc, driv, passe, rideData);
			break;
		case 5:
			System.out.println("Logout Sucessfull");
			String[] agr = null;
			main(agr);
			break;
		}
		
	}

	private static void acceptRequest(Scanner sc, Map<String, passenger> passe, Map<Integer, driver> driv,
			List<RideData> rideData) throws invalidDetailException {
		// TODO Auto-generated method stub
		int choice=0;
		choice=sc.nextInt();
		System.out.println("Press 1-->Accept Booking");
		System.out.println("Press 2-->Reject Booking");
		if(choice==1)
		{
			System.out.println("Booking Accepted");
			driverFunction(sc, driv, passe, rideData);
		}
		else if(choice==2)
		{
			System.out.println("Booking Rejected");
			driverFunction(sc, driv, passe, rideData);
		}
		
	}


	private static void rideHistory(Scanner sc, List<RideData> rideData, Map<Integer, driver> driv,
			Map<String, passenger> passe) throws invalidDetailException {
		System.out.println("Enter Your Id");
		int id=sc.nextInt();
		List<RideData> dvFilter =new ArrayList<>();
		adminService ad=new adminService();
		dvFilter=ad.filterDataById(id,rideData);
		if(dvFilter.size()>0)
		{
			System.out.println(dvFilter);
			driverFunction(sc, driv, passe, rideData);
		}
		else
		{
			System.out.println("No Data Found");
			driverFunction(sc, driv, passe, rideData);
		}
		// TODO Auto-generated method stub
		
	}


	private static void updateStatus(Scanner sc, Map<Integer, driver> driv) {
		// TODO Auto-generated method stub
		System.out.println("Enter your Id");
		int id=sc.nextInt();
		int type=0;
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
		
		adminService adServ=new adminService();
		adServ.updateStatus(id,typeAvb,driv);
	
	}


	private static boolean loginDriver(Scanner sc, Map<Integer, driver> driv, Map<String, passenger> passe) {
		// TODO Auto-generated method stub
		
		System.out.println("Please Enter the id");
		int id=sc.nextInt();
		System.out.println("Please Enter the name");
		String name=sc.next();
		if(driv.containsKey(id))
		{
			if(driv.get(id).getName().equals(name))
			{
				System.out.println("Login Sucessfull");
				return true;
			}
			else
			{
				System.out.println("Wrong id or name");
			}
		}
		else
		{
			System.out.println("Data not found please Onboard first");
		}
		return false;
	}

	private static void customerLogin(Scanner sc, Map<String, passenger> passe, Map<Integer, driver> driv,String us, List<RideData> rideData) throws invalidDetailException {
		// TODO Auto-generated method stub
		//System.out.println(passe);
		
			System.out.println("Press 1-->  Book Ride");
			System.out.println("Press 2-->  Cancell Ride");
			System.out.println("Press 3-->  See List of All avilable cabs");
			System.out.println("Press 4-->  Access ride History");
			System.out.println("Press 5-->  Delete the Account");
			
			int choice=sc.nextInt();
			switch (choice) {
			case 1:
				bookRide(sc,passe,driv,us,rideData);
				break;
			case 2:
				cancelRide(sc,rideData);
				customerLogin(sc, passe, driv, us, rideData);
				break;
			case 3:
				System.out.println("List of All Avilable Drivers");
				ArrayList<String> onlinedvr=new ArrayList<>();
				ArrayList<String> list=adminService.findOnline(onlinedvr,driv);
				System.out.println(list);
				customerLogin(sc, passe, driv, us, rideData);
				
				break;
			case 4:
				viewRideHistory(sc,rideData,passe,driv);
				break;
			case 5:
				deleteAccount(sc,passe);
				String[] ags = null;
				main(ags);
				break;
			}
		}
		
		

	private static void viewRideHistory(Scanner sc, List<RideData> rideData, Map<String, passenger> passe,
			Map<Integer, driver> driv) throws invalidDetailException {
		// TODO Auto-generated method stub
		System.out.println("Enter Passenger UserName");
		String id=sc.next();
		List<RideData> dvFilter =new ArrayList<>();
		adminService ad=new adminService();
		dvFilter=ad.filterDataByName(id,rideData);
		if(dvFilter.size()>0)
		{
			System.out.println(dvFilter);
			customerLogin(sc, passe, driv, id, rideData);
		}
		else
		{
			System.out.println("No Data Found");
			customerLogin(sc, passe, driv, id, rideData);
		}
	}


	private static void cancelRide(Scanner sc, List<RideData> rideData) {
		// TODO Auto-generated method stub
		System.out.println("Enter your ride Id");
		int id=sc.nextInt();
		
		for(int i=0;i<rideData.size();i++)
		{
			if(rideData.get(i).getRideID()==id)
			{
				rideData.remove(i);
				System.out.println("Ride cancelled Sucessfully");
				
				return;
			}
		}
		System.out.println("Booking not found");
	}


	private static void bookRide(Scanner sc, Map<String, passenger> passe, Map<Integer, driver> driv,String us, List<RideData> rideData) throws invalidDetailException {
		// TODO Auto-generated method stub
		System.out.println("Select your prefered driver");
		ArrayList<String> onlinedvr=new ArrayList<>();
		ArrayList<String> list=adminService.findOnline(onlinedvr,driv);
		System.out.println(list);
		System.out.println("Enter driver id");
		int driverid=sc.nextInt();
		System.out.println("Enter pickup Location");
		String pick=sc.next();
		System.out.println("Enter Drop Location");
		String drop=sc.next();
		int rideID=idGenrator.generateId();
		RideData rd=new RideData(rideID,driverid,us,pick,drop,255);
		rideData.add(rd);
		System.out.println("Ride booked sucessfully. Your ride ID is:"+rideID);
		try {
			customerLogin(sc, passe, driv, us, rideData);
		} catch (invalidDetailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		customerLogin(sc, passe, driv, us, rideData);
	}


	private static void deleteAccount(Scanner sc, Map<String, passenger> passe) {
		// TODO Auto-generated method stub
		System.out.println("Enter your username");
		String name=sc.next();
		if (passe != null && passe.size() > 0) {

			if (passe.containsKey(name)) {
				passe.remove(name);
				System.out.println("Data deleted successfully");

			} else {
				System.out.println("Passenger data not found");
			}

		} else {
			
		}
	}


	private static boolean cusLogin(Scanner sc, Map<String, passenger> passe,String us) {
		// TODO Auto-generated method stub
		System.out.println(passe);
		System.out.println("Please Enter the Username");
		
		String username=sc.next();
		us=username;
		System.out.println("Please Enter the Password");
		String password=sc.next();
		if(passe.containsKey(username))
		{
			if(passe.get(username).getPassword().equals(password))
			{
				return true;
			}
			else
			{
				System.out.println("Wrong username or password");
			}
		}
		else
		{
			System.out.println("Data not found please signup first");
		}
		return false;
	}

	private static void customerSignUp(Scanner sc, Map<String, passenger> passe) {
		// TODO Auto-generated method stub
		System.out.println("please enter the following details to Signup");
		System.out.println("Please enter the user name");
		String name = sc.next();
		System.out.println("Enter the password");
		String pass = sc.next();
		System.out.println("Enter the City Name");
		String address = sc.next();
		System.out.println("Enter the Email id");
		String email = sc.next();
		
		passenger p1=new passenger(name,pass,address,email);
		customerService cs=new customerService();
		cs.addCustumer(p1,passe);
		System.out.println("SignIn successful");
	}

}
