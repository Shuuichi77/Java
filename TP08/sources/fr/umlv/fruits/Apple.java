package fr.umlv.fruits;

import java.util.Objects;

public record Apple(int weight, AppleKind type) implements Fruit {
	public Apple {
		if (weight < 1) {
			throw new IllegalArgumentException("weight < 1");
		}
		Objects.requireNonNull(type);
	}
	
	@Override
	public double price() {
		return weight / 2.0;
	}
	
	@Override
	public String toString() {
		return type + " " + weight + " g";
	}
}
