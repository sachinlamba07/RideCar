package com.masai.entities;

import java.io.Serializable;

public class RideData implements Serializable{

	
	int rideID;
	int driverId;
	String passengername;
	String pickupLoc;
	String dropLoc;
	int amount;
	
	
	public RideData(int generateId, int driverid2, String us, String pick, String drop, int i) {
		// TODO Auto-generated constructor stub
		this.rideID=generateId;
		this.driverId=driverid2;
		this.passengername=us;
		this.pickupLoc=pick;
		this.dropLoc=drop;
		this.amount=i;
	}


	@Override
	public String toString() {
		return "RideData [rideID=" + rideID + ", driverId=" + driverId + ", passengername=" + passengername
				+ ", pickupLoc=" + pickupLoc + ", dropLoc=" + dropLoc + ", amount=" + amount + "]";
	}


	public int getRideID() {
		return rideID;
	}


	public void setRideID(int rideID) {
		this.rideID = rideID;
	}


	public int getDriverId() {
		return driverId;
	}


	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}


	public String getPassengername() {
		return passengername;
	}


	public void setPassengername(String passengername) {
		this.passengername = passengername;
	}


	public String getPickupLoc() {
		return pickupLoc;
	}


	public void setPickupLoc(String pickupLoc) {
		this.pickupLoc = pickupLoc;
	}


	public String getDropLoc() {
		return dropLoc;
	}


	public void setDropLoc(String dropLoc) {
		this.dropLoc = dropLoc;
	}


	public int getAmount() {
		return amount;
	}


	public void setAmount(int amount) {
		this.amount = amount;
	}
	
}
