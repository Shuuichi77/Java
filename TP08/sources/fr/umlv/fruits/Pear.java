package fr.umlv.fruits;

public record Pear(int juice) implements Fruit {	
	public Pear {		
		if (juice < 0 || juice > 9) {
			throw new IllegalArgumentException("juice < 0 || juice > 9");
		}
	}
	
	@Override
	public double price() {
		return juice * 3;
	}
	
	@Override
	public String toString() {
		return "Pear " + juice + " juice factor";
	}
}
