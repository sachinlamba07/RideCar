package com.masai.entities;

import java.io.Serializable;
import java.util.Objects;

public class driver implements Serializable{

	private int id;
	private String name;
	private int regNo;
	private String typeCar;
	private String typeAvb;
	public driver(int generateId, String name, String typeCar, int regNo, String typeAvb) {
		// TODO Auto-generated constructor stub
		this.name=name;
		this.regNo=regNo;
		this.typeCar=typeCar;
		this.typeAvb=typeAvb;
		this.id=generateId;
	}



//	public driver() {
//		super();
//	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTypeCar() {
		return typeCar;
	}

	public void setTypeCar(String qty) {
		this.typeCar = qty;
	}

	public String getTypeAvb() {
		return typeAvb;
	}

	public void setTypeAvb(String type) {
		this.typeAvb = type;
	}

	public int getRegNo() {
		return regNo;
	}

	public void setRegNo(int no) {
		this.regNo = no;
	}

	@Override
	public String toString() {
		return "Driver [id=" + id + ", name=" + name + ", CarType=" + typeCar + ", RegistrationNo=" + regNo + ", Status=" + typeAvb
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(typeCar, id, name, regNo, typeAvb);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		driver other = (driver) obj;
		return Objects.equals(typeCar, other.typeCar) && id == other.id && Objects.equals(name, other.name);
	}
	
}
