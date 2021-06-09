package fr.umlv.shopping;

import java.util.ArrayList;

sealed abstract class Product permits Book, VideoGame, PrePaid {
	abstract public int price();
	
	abstract public String type();
	
	abstract public ArrayList<String> fields();
	
	public String toTextFormat() {
		var builder = new StringBuilder();
		
		/* First, we add the type of the product (the uppercase letter) */
		builder.append(this.type());
		
		/* Then for each field of the product, we first add the separator, then the value of the field */
		ArrayList<String> fields = this.fields();
		for (String field : fields) {
			builder.append(SaverLoader.SEPARATOR).append(field);
		}
		
		return builder.toString();
	}
}
