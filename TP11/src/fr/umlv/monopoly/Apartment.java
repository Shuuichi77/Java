package fr.umlv.monopoly;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Apartment implements Building {
	private final int area;
	private final List<String> listPerson;
	
	public Apartment(int area, List<String> listPerson) {
		if (area < 0) {
			throw new IllegalArgumentException("area < 0");
		}
		
		Objects.requireNonNull(listPerson);
		if (listPerson.isEmpty()) {
			throw new IllegalArgumentException("listPerson is empty");
		}
		
		this.area = area;
		this.listPerson = List.copyOf(listPerson);
	}
	
	@Override
	public double efficiency() {
		return listPerson.size() > 1 ? 1.0 : 0.5;
	}

	@Override
	public String toString() {
		return "Apartment " + area + " m2 with " + listPerson.stream().collect(Collectors.joining(", ")) + " " + efficiency();
	}

	@Override
	public int buildingPrice() {
		return listPerson.size() * 20;
	}
}
