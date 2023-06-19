package com.masai.entities;

public class idGenrator {

	
		public static int generateId() {

			// random function generates value between 0.0000111 to 0.9999

			return (int) (Math.random() * 1000000);
		}
}
