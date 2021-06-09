package fr.umlv.fruits;

import java.util.Objects;
import java.util.HashMap;

public class Basket {
	HashMap<Fruit, Integer> basket = new HashMap<Fruit, Integer>();
	
	/**
	 * Add 1 fruit in the basket
	 * @param fruit The fruit we are adding
	 */
	public void add(Fruit fruit) {
		Objects.requireNonNull(fruit);
		basket.put(fruit, 1);
	}
	
	/**
	 * Add several fruit of a same kind in the basket
	 * @param fruit The fruit we are adding
	 * @param quantity How many fruit we add in the basket
	 */
	public void add(Fruit fruit, int quantity) {
		Objects.requireNonNull(fruit);
		if (quantity < 0) {
			/* It could have been "< 1" since we have the other add method which add 1 fruit, 
			 * but it's not that bad even if it's ".add(fruit, 1)" */
			throw new IllegalArgumentException("quantity < 0"); 
		}
		
		basket.put(fruit, quantity);
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		double basketPrice = 0;
		/* NB : we return a double and not an integer in case an apple's weight is odd, 
		   even though the exercise return an integer in the example */
		/* We don't add/use a price() method because in this case, 
		   we would iterate basket 2 times :
		  	- the first time is now, in the toString method
		  	- the second time in the price() method
		   Since we are already iterating basket, we calculate the price at the same time
		 * (We could still add the method and don't use it, but we didn't need it in the exercise)
		 */
		
		for (var fruit: basket.entrySet()) {
			builder.append(fruit.getKey() + " x " + fruit.getValue() + "\n");
			basketPrice += fruit.getKey().price() * fruit.getValue();
		}
		
		/* To print a rounded price, in case the exercise need it to be rounded */
		if (basketPrice % 1 == 0) {
			int roundedBasketPrice = (int) basketPrice;
			return builder.append("price: " + roundedBasketPrice).toString();
		}
		
		return builder.append("price: " + basketPrice).toString();
	}
}
