package fr.umlv.fight;
import java.util.Random;

public class Fighter extends Robot {
	private Random random;
	
	/* Constructeur */
	Fighter (String nom, int graine) {
		super(nom);
		random = new Random(graine);
	}

	
	/* On Override la méthode rollDice() pour faire en sorte qu'un Fighter ait 50% de chance d'atteindre sa cible */
	@Override
	boolean rollDice() {		
		return random.nextBoolean();
	}
	
	@Override
	public String toString() {
		return "Fighter " + this.getNom();
	}
}

/*
Exercice 2

1/ Un générateur pseudo aléatoire est un algorithme qui génère une suite de nombre en fonction de la valeur de la graine qui lui est fourni.
L'algorithme est crée de sorte que les nombres générés par la suite soient très distincts des uns des autres.
Cependant, si on fournit la même graine, on aura toujours la même suite de nombre : d'où le "pseudo aléatoire"

3/ Pour ne pas permettre de modifier les champs de l'extérieur, on veut que ce soit private 

5/ Ce n'est pas une bonne idée de copier coller puisque dans le cas où on modifie une méthode,
il faut toujours modifier l'autre également : pour peu qu'on n'oublie, ça ne fonctionnera pas.

6/ La méthode doit être "default" pour être visible par les classes du même paquet

9/ On illustre le sous-typage avec les classes Robot et Fighter :
Fighter reprend la classe Robot, et réutiliser ce que fait Robot, comme la méthode "fire"

Le polymorphisme quant à lui peut être illustrer avec la méthode rollDice : 
selon la classe de l'objet (Robot ou Fighter), on fait appel à une méthode différente, qui ont donc des résultats et procédé différents
*/