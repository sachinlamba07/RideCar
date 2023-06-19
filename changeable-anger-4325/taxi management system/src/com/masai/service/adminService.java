package com.masai.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.masai.entities.*;

public class adminService {

	public String addDriver(driver driv, Map<Integer, driver> d1) {
		// TODO Auto-generated method stub
		//System.out.println(d1);
		d1.put(driv.getId(),driv);
		return "Driver Onboarded Sucessfully";
	}

	public void viewDriver(Map<Integer, driver> driv) {
		// TODO Auto-generated method stub
		if (driv != null && driv.size() > 0) {
			for (Map.Entry<Integer, driver> me : driv.entrySet()) {
				System.out.println(me.getValue());
			}

		} else {
			System.out.println("Drivers List is Empty");
		}
	}

	public void deleteDriver(int id, Map<Integer, driver> driv) {
		// TODO Auto-generated method stub
		if (driv != null && driv.size() > 0) {

			if (driv.containsKey(id)) {
				driv.remove(id);
				System.out.println("Driver Data deleted successfully");

			} else {
				System.out.println("Driver data not found");
			}

		} else {
			System.out.println("Product list is empty");
		}
	}

	public void updateDriverData(int id,driver d1, Map<Integer, driver> driv) {
		// TODO Auto-generated method stub
		
		if (driv != null && driv.size() > 0) {

			if (driv.containsKey(id)) {
				driv.put(id, d1);
				System.out.println("Driver has successfully updated");
			} else {
				System.out.println("Driver not found");
			}

		} else {
			System.out.println("Driver list is empty");
		}
		
		
	}

	public void viewPassenger(Map<String, passenger> passe) {
		// TODO Auto-generated method stub
		if (passe != null && passe.size() > 0) {
			for (Map.Entry<String, passenger> me : passe.entrySet()) {
				System.out.println(me.getValue());
			}

		} else {
			System.out.println("Passenger List is Empty");
		}
	}

	public void updateStatus(int id, String typeAvb, Map<Integer, driver> driv) {
		// TODO Auto-generated method stub
		if (driv.containsKey(id)) {
			driv.get(id).setTypeAvb(typeAvb);
			System.out.println("Driver status has been successfully updated");
		} else {
			System.out.println("Driver not found");
		}
	}

	public static ArrayList<String> findOnline(ArrayList<String> onlinedvr, Map<Integer, driver> driv) {
		// TODO Auto-generated method stub
		if (driv != null && driv.size() > 0) {
			for (Map.Entry<Integer, driver> me : driv.entrySet()) {
				
				if(driv.get(me.getKey()).getTypeAvb().equals("Online"))
				{
					//System.out.println(me.getValue());
					onlinedvr.add(driv.get(me.getKey()).getId()+"-->"+driv.get(me.getKey()).getName()+"-->"+driv.get(me.getKey()).getTypeCar());
				}
				
			}
			return onlinedvr;

		} else {
			System.out.println("Drivers List is Empty");
		}
		return null;
	}

	public List<RideData> filterDataById(int id, List<RideData> rideData) {
		// TODO Auto-generated method stub
		List<RideData> adf =new ArrayList<>();
		for(int i=0;i<rideData.size();i++)
		{
			if(rideData.get(i).getDriverId()==id)
			{
				adf.add(rideData.get(i));
			}
		}
		return adf;
	}

	public List<RideData> filterDataByName(String id, List<RideData> rideData) {
		// TODO Auto-generated method stub
		List<RideData> adf =new ArrayList<>();
		for(int i=0;i<rideData.size();i++)
		{
			if(rideData.get(i).getPassengername().equals(id))
			{
				adf.add(rideData.get(i));
			}
		}
		return adf;
	}
	
	
	
	


	
	
}
