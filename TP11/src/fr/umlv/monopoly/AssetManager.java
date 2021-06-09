package fr.umlv.monopoly;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AssetManager {
	private List<Building> buildings = new ArrayList<>();
	
	public void add(Building building) {
		Objects.requireNonNull(building);
		
		buildings.add(building);
	}
	
	public int profitPerNight() {
		return buildings.stream()
						.mapToInt(Building::buildingPrice)
						.sum();
	}
	
	@Override
	public String toString() {
		return buildings.stream()
						.map(Building::toString)
						.collect(Collectors.joining("\n"));
	}
	
	public List<String> lowestEfficiency(double efficiency) {
		return buildings.stream()
						.filter(e -> e.efficiency() <= efficiency)
						.map(Building::toString)
						.collect(Collectors.toList());
	}
	
	public void remove(double efficiency) {
		buildings.removeIf(e -> e.efficiency() <= efficiency);
	}
}
