package fr.umlv.shopping;

import java.util.ArrayList;

/* J'ai remis les record en classes parce que je n'arrivais pas à extends 
 * l'abstract class Product avec les records, et je tenais à faire une abstract classe
 * pour la méthode toTextFormat (Syntax error on token "extends", implements expected)
 */
public final class PrePaid extends Product {
	private final int value;
	private final int validTimeInWeek;
	
	public PrePaid(int value, int validTimeInWeek) {
		if (value < 0) {
			throw new IllegalArgumentException("value < 0");
		}
		if (validTimeInWeek < 0 || validTimeInWeek >= 52) {  // 52 weeks == 1 year
			throw new IllegalArgumentException("validTimeInWeek < 0 || validTimeInWeek >= 52");
		}
		
		this.value = value;
		this.validTimeInWeek = validTimeInWeek;
	}
	
	@Override
	public boolean equals(Object o) {
		if (!(o instanceof PrePaid)) {
			return false;
		}
		
		PrePaid other = (PrePaid) o;
		return other.value == this.value && 
				other.validTimeInWeek == this.validTimeInWeek;	
		}
	
	@Override
	public int hashCode() {
		return value + validTimeInWeek;
	}
	
	@Override
	public String toString() {
		return "Carte cadeau (%d,%02d€), durée: %s semaines)".formatted(value / 100, value % 100, validTimeInWeek);
	}
	
	public int price() {
		if (validTimeInWeek <= 2) {
			return value * 80 / 100;
		}
		return value;
	}
	
	@Override
	public String type() {
		return SaverLoader.PREPAID_TYPE;
	}
	
	@Override
	public ArrayList<String> fields() {
		ArrayList<String> fields = new ArrayList<>();
		
		fields.add(String.valueOf(price()));
		fields.add(String.valueOf(validTimeInWeek));
		
		return fields;
	}
}