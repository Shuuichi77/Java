package fr.umlv.fruits;

public interface Fruit {
	/**
	 * Return the fruit's price
	 * @return The fruit's price
	 */
	double price();
	
	/* NB : we return a double and not an integer in case an apple's weight is odd */
}
