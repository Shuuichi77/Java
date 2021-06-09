package fr.umlv.data;

import java.util.Objects;

record Link<E> (E value, Link<E> next) {
	public Link {
		Objects.requireNonNull(value);
	}
	
	@Override
	public String toString() {
		return value.toString();
	}
	
	public static void main(String[] args) {
		var link1 = new Link<Integer>(13, new Link<Integer>(144, null));
		
		System.out.println(link1);
	}
}
