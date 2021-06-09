package fr.umlv.data.main;

import fr.umlv.data.LinkedLink;

public class Main {
	public static void main(String[] args) {
		var links = new LinkedLink<String>(null);
		
		links.add("ca va");
		links.add("Bonjour");
		
		System.out.println(links.contains("test"));
		System.out.println(links.contains(1));
		System.out.println(links.contains("ca va"));
		
		System.out.println(links);
	}
}


/*
Exercice 1

1/ Le record est de visibilité package (donc rien devant record) : 
on veut qu'il soit visible seulement par les classes dans le même paquet (ici LinkedLink)

2/ Si on est dans le dossier du projet (TP9) :
java --enable-preview --source 15 src/fr/umlv/data/Link.java


Exercice 2

1/ Si l'indice est invalide, on throw un IllegalArgumentException : l'argument index 
qui a été fournit à la fonction n'est pas valide

4/ On est obligé de cast pour utiliser la méthode .length() : cette méthode
ne fonctionne que sur un String, or à la base on a un Object et pas un String
On n'aime pas les cast parce qu'on peut les oublier/se tromper, ils ne sont pas pratiques

Exercice 3

1/ Les types paramétrés permettent de vérifier les contraintes sur
les types dès la compilation, plutôt qu'à l'exécution.
Ainsi, si on met un objet dans un LinkedLink qui n'est pas du même type, on aura une erreur dès la compilation.
De plus, ça nous permet de réutiliser la classe pour n'importe quel type qu'on veut.

4/ Si contains ne prennait pas un Object,
ça ne marcherait pas sur les supertypes, qui contiennent pourtant le bon type

Exemple :
var list = new LinkedList<Cat>();
list.add(new Cat("myCat");
Animal animal = new Cat("myCat");
System.out.println(list.contains(animal);

On aurait faux si contains ne prennait pas un object
*/