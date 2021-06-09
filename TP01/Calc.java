import java.util.Scanner;

public class Calc { 
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in); /* Variable scanner de type Scanner */

		System.out.println("Entrer une valeur : ");
		int a = scanner.nextInt(); /* Variable a de type int */
		
		System.out.println("Entrer une seconde valeur : ");
		int b = scanner.nextInt(); /* Variable b de type int */
		
		double c = b; /* Variable c de type double, afin de pouvoir effectuer la division */

		System.out.println(a + " + " + b + " = " + (a + b));
		System.out.println(a + " - " + b + " = " + (a - b));
		System.out.println(a + " / " + b + " = " + (a / c));
		System.out.println(a + " * " + b + " = " + (a * b));
	}


	/* 
	Question 3 :
	nextInt() est une méthode et non pas une fonction, car elle dépend de l'objet

	Question 4 :
	Pour accéder à la classe java.util.scanner, on doit l'importer : d'où la 1ere ligne
	*/
}