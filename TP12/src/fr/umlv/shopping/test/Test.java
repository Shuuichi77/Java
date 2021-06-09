package fr.umlv.shopping.test;

import fr.umlv.shopping.Book;
import fr.umlv.shopping.PrePaid;
import fr.umlv.shopping.ShoppingCart;
import fr.umlv.shopping.VideoGame;

public class Test {
	public static void main(String[] args) {
		var cart = new ShoppingCart();
		
		/* We add 2 "L'Armée des ombres" */
		cart.add(new Book("J. Kessel", "L'Armée des ombres", 850));
		cart.add(new Book("J. Kessel", "L'Armée des ombres", 850));
		cart.add(new Book("S. Zweig", "Le joueur d'échecs", 700));
		cart.add(new Book("Lorie", "Mes Secrets", 1020));
		System.out.println(cart);
		
		/* Without the discount : 850 + 850 + 700 + 1020 = 3420 euros cents 
		 * With the discount : (850 + 850) * 0.95 + 700 + 1020 = 3335 euros cents */
		System.out.println(cart.price() + " euros cents\n");
		
		
		cart.remove(new Book("Lorie", "Mes Secrets", 1020));
		cart.add(new Book("S. de Beauvoir", "Mémoires d'une jeune fille rangée", 990));
		System.out.println(cart);
		
		/* Without the discount : 850 + 850 + 700 + 990 = 3390 euros cents 
		 * With the discount : (850 + 850) * 0.95 + 700 + 990 = 3305 euros cents */
		System.out.println(cart.price() + " euros cents\n");

		
		var zelda = new VideoGame("The legend of Zelda", VideoGame.Console.WII, 4950);
		System.out.println(zelda + ", " + zelda.price() + " euros cents");
		var pp50 = new PrePaid(5000, 2);
		System.out.println(pp50 + ", " + pp50.price() + " euros cents");
		var pp100 = new PrePaid(10000, 10);
		System.out.println(pp100 + ", " + pp100.price() + " euros cents\n");
		
		/* We add 3 zelda */
		cart.add(zelda);
		cart.add(zelda);
		cart.add(zelda);
	    cart.add(pp50);
	    cart.add(pp100);
	    System.out.println(cart);
	    
	    /* Without the discount : 850 + 850 + 700 + 990 + 4950 + 4950 + 5000 + 10000 = 33240 euros cents 
		 * With the discount : (850 + 850) * 0.95 + 700 + 990 = 32412.5 (we don't round the result) euros cents */
	    System.out.println(cart.price() + " euros cents");
	}
}

/*
Exercice 1
Question 1 :
On crée une abstract class Produit qui extends Book, VideoGame et PrePaid

NB : J'ai remis les record en classes parce que je n'arrivais pas à extends 
l'abstract class "Product" avec les records (Syntax error on token "extends", implements expected), 
et je tenais à faire une abstract classe pour la méthode toTextFormat 


Exercice 3

Question 1 :
Dans ShoppingCart, au lieu d'avoir une ArrayList, on a une Map qui associe à chaque produit une quantité.
Lorsqu'on va calculer le prix d'un panier, si quantité > 1 (la valeur de la clé associé au produit), on réduit le prix de l'article concerné

*/