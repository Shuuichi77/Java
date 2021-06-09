package fr.umlv.exos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class UpperCase {
	private static List<String> upperCase(List<String> words) {	
		Objects.requireNonNull(words);
		var uppercases = new ArrayList<String>();
		
		for (String string : words) {
			uppercases.add(string.toUpperCase());
		}
		
		return uppercases;
	}
	
	
	private static List<String> upperCase2(List<String> words) {
		Objects.requireNonNull(words);
		var uppercases = new ArrayList<String>();
	  
		words.stream()
				.map(s -> s.toUpperCase())
				.forEach(s -> uppercases.add(s));
	  
		return uppercases;
	}
	
	
	private static List<String> upperCase3(List<String> words) {
		Objects.requireNonNull(words);
		var uppercases = new ArrayList<String>();
	      
		words.stream()
				.map(String::toUpperCase)
				.forEach(uppercases::add);
	      
		return uppercases;
	}
	
	
	private static List<String> upperCase4(List<String> words) { 
		Objects.requireNonNull(words);
		return words.stream()
					.map(String::toUpperCase)
		      		.collect(Collectors.toList());
	}
	
	
	public static void main(String[] args) {
		var list = List.of("hello", "world", "hello", "lambda");
		
		System.out.println(upperCase(list));  // [HELLO, WORLD, HELLO, LAMBDA]
		System.out.println(upperCase2(list));
		System.out.println(upperCase3(list));
		System.out.println(upperCase4(list));
	}
}

/* 
Exercice 2

2/ cf upperCase2
*/
