package fr.umlv.monopoly;

public class Hotel implements Building {
	private final int rooms;
	private double efficiency;
	
	public Hotel(int rooms, double efficiency) {
		if (rooms < 0) {
			throw new IllegalArgumentException("rooms < 0");
		}
		
		if (efficiency < 0 || efficiency > 1) {
			throw new IllegalArgumentException("efficiency < 0 || efficiency > 1");
		}
		
		this.rooms = rooms;
		this.efficiency = efficiency;
	}
	
	@Override
	public double efficiency() {
		return efficiency;
	}
	
	@Override
	public String toString() {
		return "Hotel " + rooms + " rooms " + efficiency;
	}
	
	@Override
	public int buildingPrice() {
		return (int) (100 * rooms * efficiency);
	}
}
