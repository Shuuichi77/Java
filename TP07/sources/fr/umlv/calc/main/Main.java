package fr.umlv.calc.main;

import fr.umlv.calc.Expr;

public class Main {
	public static void main(String[] args) {
		Expr expression = new Expr.BinOp.Add(new Expr.Value(2), new Expr.Value(3));
        Expr expression2 = new Expr.BinOp.Sub(new Expr.BinOp.Add(new Expr.Value(2), new Expr.Value(3)), new Expr.Value(4));
        
        System.out.println(expression);
        System.out.println(expression2);
        
        System.out.println(expression.eval());
        System.out.println(expression2.eval());
	}	
}

/*
Exercice 1

1/ On a un premier construccteur qui déclare des champs comme null si non déclarés, 
mais un autre qui requireNonNull ces mêmes champs

2/ On représente un "ou" entre plusieurs types avec une interface
OpOrValue mélange tous ses types selon les champs déclarés, 
alors qu'avec une interface on va mieux les distinguer, on a une meilleure modularité

5/ Les sous-classes de l'interface Expr ne contenant que très peu de lignes de code, il est pertinent de les ajouter
directement dans l'interface directement, ce qui nous permet de sealed sans rien permits

9/ On utilise "parse(Iterator<String> scanner)"

11/ On utilise une abstract class puisque Add et Sub sont totalement identique,
à l'exception de l'opérateur utilisé.
Dans les méthodes de BinOp, on devra juste faire attention à l'opérateur à utiliser :
on ajoute donc un champ opérateur en plus de left et right.
Dans Add et Sub, on définit ensuite l'opérateur.

12/ cf Expr.java

*/