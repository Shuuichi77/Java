package fr.umlv.project;

import java.io.IOException;

public final class Property extends Word {
	/* Constructor */
	public Property(WordName wordName, int line, int column) throws IOException {
		super(wordName, line, column);
		if (!(wordName instanceof PropertyName || wordName instanceof NounName)) {
			throw new IllegalArgumentException("Property's wordName isn't a PropertyName");
		}
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Property)) {
			return false;
		}
		
		Property other = (Property) obj;
		return other.wordName() == this.wordName() && other.line() == this.line() && other.column() == this.column();
	}
	
	@Override
	public int hashCode() {
		return wordName().hashCode() + line() + column();
	}
}
