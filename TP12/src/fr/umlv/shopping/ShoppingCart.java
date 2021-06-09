package fr.umlv.shopping;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ShoppingCart {
	private final Map<Product, Integer> cart = new HashMap<>();
	private static final double discount = 0.05;
	
	public void add(Product product) {
		Objects.requireNonNull(product);
		
		/* If the product was already in the map, we are incrementing its value by 1.
		 * Otherwise, the value is 1. */
		cart.put(product, (cart.containsKey(product)) ? cart.get(product) + 1 : 1);
	}

	public void remove(Product product) {
		Objects.requireNonNull(product);
		cart.remove(product);
	}
	
	public int price() {
//		var sum = 0;
//		
//		/* For each product, if its value is more than 1, we are applying a discount */
//		for (var product : cart.entrySet()) {
//			if (product.getValue() > 1) {
//				sum += (product.getKey().price() * ((100.0 - discount) / 100)) * product.getValue();
//			}
//			
//			else {
//				sum += product.getKey().price();
//			}
//		}
//		
//		return sum;
		
		
		return cart.entrySet()
					.stream()
					.mapToInt(product -> (int) (product.getKey().price() * product.getValue() 
											   * ((product.getValue() > 1) ? (1 - discount) : 1))) // discount
					.sum();
	}
	
	@Override
	public String toString() {
		var builder = new StringBuilder();
		
		builder.append("--- Shopping cart ---\n");
		
		for (var product : cart.entrySet()) {
			builder.append("(" + product.getValue() + ") : " + product.getKey()).append("\n");
		}
		
		builder.append("---------------------");
		
		return builder.toString();
	}
}
