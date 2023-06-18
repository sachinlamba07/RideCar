package com.masai.service;

import java.util.Map;
import com.masai.entities.*;

import com.masai.entities.driver;

public class adminService {

	public String addDriver(driver driv, Map<Integer, driver> d1) {
		// TODO Auto-generated method stub
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
	
	
	
	


	
	
}
