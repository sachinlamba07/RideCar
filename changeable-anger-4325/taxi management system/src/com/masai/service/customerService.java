package com.masai.service;

import java.util.*;

import com.masai.entities.passenger;

public class customerService {

	public static void addCustumer(passenger p1, Map<String, passenger> passe) {
		// TODO Auto-generated method stub
		if (passe == null) {
            passe = new LinkedHashMap<>();
        }
		if (passe.containsKey(p1.getUsername())) {
			System.out.println("Customer already exists , please login");
		} else {
			passe.put(p1.getUsername(), p1);	
		}
		System.out.println(passe);
	}

}
