package fr.umlv.shopping;

import java.util.ArrayList;
import java.util.Objects;

/* J'ai remis les record en classes parce que je n'arrivais pas à extends 
 * l'abstract class Product avec les records, et je tenais à faire une abstract classe
 * pour la méthode toTextFormat (Syntax error on token "extends", implements expected)
 */
public final class VideoGame extends Product {
	public enum Console {  // static by default.
		PS3, PS4, XBOX, WII, DS
	}
	
	private final String title;
	private final Console console;
	private final int price;
	
	public VideoGame(String title, Console console, int price) {
		if (price < 0) {
			throw new IllegalArgumentException("price < 0");
		}
		
		this.title = Objects.requireNonNull(title);
		this.console = Objects.requireNonNull(console);
		this.price = price;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof VideoGame)) {
			return false;
		}
		
		VideoGame other = (VideoGame) o;
		return other.title.equals(this.title) && 
				other.console.equals(this.console) &&
				other.price == this.price;
	}
	
	@Override
	public int hashCode() {
		return title.hashCode() + console.hashCode() + price;
	}
	
	@Override
	public String toString() {
		return title + ", sur " + console.name();
	}
	
	@Override
	public int price() {
		return price;
	}
	
	@Override
	public String type() {
		return SaverLoader.VIDEO_GAME_TYPE;
	}
	
	@Override
	public ArrayList<String> fields() {
		ArrayList<String> fields = new ArrayList<>();
		
		fields.add(String.valueOf(price));
		fields.add(title);
		fields.add(console.toString());
		
		
		return fields;
	}
}