package fr.umlv.shopping;

import java.util.ArrayList;
import java.util.Objects;

/* J'ai remis les record en classes parce que je n'arrivais pas à extends 
 * l'abstract class Product avec les records, et je tenais à faire une abstract classe
 * pour la méthode toTextFormat (Syntax error on token "extends", implements expected)
 */
public final class Book extends Product {
	private final String author;
	private final String title;
	private final int price;

	public Book(String author, String title, int price) {
		if (price < 0) {
			throw new IllegalArgumentException("price < 0");
		}
		
		this.author = Objects.requireNonNull(author);
		this.title = Objects.requireNonNull(title);
		this.price = price;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Book)) {
			return false;
		}
		
		Book other = (Book) o;
		return other.author.equals(this.author) && 
				other.title.equals(this.title) &&
				other.price == this.price;
	}
	
	@Override
	public int hashCode() {
		return author.hashCode() + title.hashCode() + price;
	}
	
	@Override
	public String toString() {
		return title + ", de " + author;
	}
	
	@Override
	public int price() {
		return price;
	}
	
	@Override
	public String type() {
		return SaverLoader.BOOK_TYPE;
	}
	
	@Override
	public ArrayList<String> fields() {
		ArrayList<String> fields = new ArrayList<>();
		
		fields.add(String.valueOf(price));
		fields.add(title);
		fields.add(author);

		return fields;
	}
}
