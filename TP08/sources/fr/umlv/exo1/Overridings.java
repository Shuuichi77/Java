package fr.umlv.exo1;

class A {
	int x = 1;

	public int getX() {
		return x;
	}
  
	static void printX(A a) {
	    System.out.println("in A.printX");
	    System.out.println("x " + a.x);
	    System.out.println("getX() " + a.getX());
	}
  
	int m(A a) { return 1; }
}

class B extends A {
	int x = 2;

	public int getX() {
		return x;
	}
  
	static void printX(B b) {
		System.out.println("in B.printX");
		System.out.println("x " + b.x);
		System.out.println("getX() " + b.getX());
	}
  
	int m(B b) { return 2; }
}

public class Overridings {
	public static void main(String[] args) {
		A.printX(new A());     // 1
	    B.printX(new B());   // 2
	    A.printX(new B());   // 3
	    A a = new B();
	    System.out.println(a.m(a));              // 4
	}
}

/*
Exercice 1
- Avant exécution
1/ On utilise la méthode printX du type A, pas d'ambiguité :
in A.printX
x 1
getX() 1

2/ De la même façon que pour A, mais cette fois-ci le type B :
in B.printX
x 2
getX() 2

3/
in A.printX -> On utilise la méthode sur un type A
x 2			-> On cherche le .x de B
getX() 2	-> On cherche si getX est redéfini dans B, ce qui est le cas


4/ 1
On a casté le a en B
Il essaye de chercher la méthode .m(A a) dans A et B, et ne la trouve que dans A


- Après exécution
1/ et 2/ Pas de problème pour ces 2 premiers affichages

3/ 
in A.printX -> On lance la méthode sur un A, ça confirme nos attentes
x 1			-> Là par contre c'est faux. En réalité on voit le 'a' comme un A, même si on l'appelle sur un new B
getX() 2	-> Etant donné qu'on a redéfini getX dans B, on utilise bien la méthode dans B : pas de problème ici

4/ 1
Pas de problème ici aussi
*/