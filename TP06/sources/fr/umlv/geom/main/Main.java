package fr.umlv.geom.main;

import fr.umlv.geom.Point;
import fr.umlv.geom.Circle;
import fr.umlv.geom.Ring;

public class Main {
	public static void main(String[] args) {
		/* Tests for both circle.contains method */
		var p1 = new Point(0, 0);
		var p2 = new Point(0, 2);
		var c1 = new Circle(p1, 2);
		
		System.out.println(c1.contains(p1)); /* True : Inside c1 */
		System.out.println(c1.contains(p2)); /* False : On the border of c1, so outsite */
		
		System.out.println("\n------------------------\n");
		/* Tests for both ring.contains method */
		var point1 = new Point(0, 0);
		var point2 = new Point(0, 2);
		var point3 = new Point(0, 5);
		var point4 = new Point(0, 7);
		var point5 = new Point(0, 10);
		var ring1 = new Ring(point1, 3, 1);
				
		System.out.println(ring1.contains(point1));  /* False : Inside the hole of ring */
		System.out.println(ring1.contains(point2)); /* True  : Inside the ring */
		System.out.println(ring1.contains(point3)); /* False : Outside the ring */
		
		var ring2 = new Ring(point1, 7, 5);
		var ring3 = new Ring(point1, 11, 9);
		System.out.println(ring1.contains(point3, ring2, ring3)); /* False : On the inner border of ring2 */
		System.out.println(ring1.contains(point4, ring2, ring3)); /* False : On the outer border of ring2 */
		System.out.println(ring1.contains(point5, ring2, ring3)); /* True  : Inside ring3 */
		
		System.out.println(point1);
		System.out.println(c1);
		System.out.println(ring1);
	}
}

/* 
Exercice 1 

1/ Le code du record ne compile pas car on ne peut pas modifier les coordonnées du point, les champs sont finaux (car record)
La méthode translate ne fonctionnera pas avec un record.

2/ Si on ne veut jamais changer les valeurs du cercle (son centre et son rayon), on utilisera un record
Mais dans le cas où on veut modifier ses champs, on utiliserait une classe.

6/ Le centre de circle et circle2 ont tous les 2 modifiés, alors qu'on avait modifié que circle2.
On doit faire en sorte que lorsqu'on déclare un circle, on crée un nouveau Point pour le center.

7/ On crée une variable temporaire qui a les mêmes coordonnées que center, et on le renvoie lui plutôt que center.

10/ Le "..." veut dire qu'on peut mettre autant d'objet "Circle" en parametre de la fonction



Exercice 2
1ere partie
1/ Il est judicieux de faire de l'héritage quand la sous-classe réutilise beaucoup de fonctionnalités (i.e méthodes, champs) de la classe mère,
et va ajouter des petites fonctionnalités par rapport à la classe mère.

3/ Si les rayons fournis ne sont pas correct (subradius < radius), on fait un throw new IllegalArgumentException

4/ Il faut Override surface puisqu'on a plus un disque, mais un anneau.

2ème partie
3/ En réutilisant des méthodes d'une classe mère, on doit parfois modifier plus que la méthode qu'on override
*/