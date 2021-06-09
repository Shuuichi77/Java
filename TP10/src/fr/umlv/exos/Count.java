package fr.umlv.exos;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.LongSupplier;
import java.util.stream.Collectors; 

public class Count {
	private static long count(List<String> list, String string) {
		Objects.requireNonNull(list);
		Objects.requireNonNull(string);
		
		long counter = 0;
		
		for (String e : list) {
			counter += (string.equals(e) ? 1 : 0);
		}
		
		return counter;
	}
	

	private static long count2(List<String> list, String string) {	
		Objects.requireNonNull(list);
		Objects.requireNonNull(string);
		
		return list.stream()
					.filter(e -> e.equals(string))
					.count();
	}
	
	
	private static long count3(List<String> list, String string) {		
		Objects.requireNonNull(list);
		Objects.requireNonNull(string);
		
		return list.stream()
					.filter(string::equals)
					.mapToLong(e -> 1L)
					.reduce(0, Long::sum);
	}
	
	
	private static void printAndTime(LongSupplier ls) {
		var start = System.nanoTime();
		var result = ls.getAsLong();
	    var end = System.nanoTime();
	    
	    System.out.println("result " + result);
	    System.out.println(" elapsed time " + (end - start));
	}
	
	
	public static void main(String[] args) {
		var list2 = new Random(0)
			        .ints(1_000_000, 0, 100)
			        .mapToObj(Integer::toString)
			        .collect(Collectors.toList());
				
		printAndTime(() -> count(list2, "33")); 	
		printAndTime(() -> count2(list2, "33")); 	
		printAndTime(() -> count3(list2, "33"));	
	}
}


/* 
Exercice 1

2/ On obtient un stream à partir d'un objet de type List en faisant list.stream()
(on peut rajouter .collect(Collectors.toList()) pour qu'elle nous renvoie tout le contenu de la liste).

Pour filtrer un stream, on utilise filter().
Pour compter le nombre d'élément on utilise count().

Dans notre Predicate<T>, T est un String.


Exercice 3

1/ On veut faire le sum de tous les string qu'on a converti en nombre 1.
Or, avec stream, on ne peut pas faire stream.sum() avec n'importe quel objet : avec des long, on peut.
(Je suppose qu'on fait mapToLong et pas mapToInt pour la suite de l'exercice : count renvoie un long,
donc pour comparer les fonctions on a besoin d'avoir un long pour count3 aussi)


Exercice 4

1/ La variable locale list2 contient une Liste de suite de 1 000 000 nombre entre 0 et 100, tous converti en String.
La graine de random étant 0, on aura donc toujours la même suite de nombre chaque fois qu'on lancera le programme.

3/ On utilise LongSupplier.

4/ En général, du plus rapide au plus lent, on a : count2, count3, count.

Dans count2, on fait un filter, count (= mapToLong + sum)
Dans count3, on fait un filter, mapToLong, reduce (+ sum)
count3 est donc plus long que count2

count n'utilise pas de stream et est plus lent que les deux autres avec son iteration.

*/
