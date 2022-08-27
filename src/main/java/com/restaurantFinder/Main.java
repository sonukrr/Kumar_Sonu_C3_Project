package com.restaurantFinder;

import java.time.LocalTime;

public class Main {

	public static void main(String[] args) {
		 LocalTime openingTime = LocalTime.parse("10:30:00");
		 LocalTime closingTime = LocalTime.parse("22:00:00");
	     Restaurant restaurant = new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
	     restaurant.addToMenu("Sweet corn soup",119);
	     restaurant.addToMenu("Vegetable lasagne", 269);
	     	     

	}

}