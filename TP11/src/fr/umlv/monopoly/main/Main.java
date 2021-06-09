package fr.umlv.monopoly.main;

import java.util.List;

import fr.umlv.monopoly.Apartment;
import fr.umlv.monopoly.AssetManager;
import fr.umlv.monopoly.Hotel;

public class Main {
	public static void main(String[] args) {
		var hotel = new Hotel(5, 0.75);
		var apartment = new Apartment(30, List.of("Bony", "Clyde"));
		var manager = new AssetManager();
		
		manager.add(hotel);
		manager.add(apartment);
		
		System.out.println(manager.profitPerNight());  // 415
		System.out.println(manager);
		System.out.println(manager.lowestEfficiency(0.8));  // [Hotel 5 rooms 0.75]
		
		manager.remove(0.8);
		System.out.println(manager);  
	}
}
