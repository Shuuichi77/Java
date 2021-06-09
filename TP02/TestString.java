public class TestString {
	public static void main(String[] args) {
		/* Question 1 */
    	var s = "toto";
      	System.out.println(s.length());

      	/* Question 2 */
      	var s1 = "toto";
       	var s2 = s1;
       	var s3 = new String(s1);

      	System.out.println(s1 == s2);
      	System.out.println(s1 == s3);

      	/* Question 3 */
        var s4 = "toto";
       	var s5 = new String(s4);

       	System.out.println(s4.equals(s5));

       	/* Question 4 */
       	var s6 = "toto";
      	var s7 = "toto";

      	System.out.println(s6 == s7);

      	/* Question 6 */
      	var s8 = "hello";
      	s8.toUpperCase();
      	System.out.println(s8);
    }
}

/*
Question 1 :
Le type "s" est un string. 
Le compialteur détecte que "s" est un string, et il sait qu'il existe la méthode "length" sur le type string


Question 2 :
On obtient : true false
Lorsqu'on utilise "==" pour comparer 2 strings, "==" retourne "true" seulement si les références sont identiques.
s1 et s2 ont la même référence, ils pointent sur le même objet, puisqu'à sa création, on a mis à s2 la même référence qu'à s1. On a donc "true" pour s1 == s2.
Mais lors de la création de s3, on a créé une nouvelle référence. On a donc "false" pour s1 == s3.


Question 3 :
On utilise la méthode "equals".


Question 4 : ("String Interning")
On obtient : true
Suite à la création de s6, JVM crée la référence vers le string "toto" car elle n'a pas encore été créée.
Lors de la création de s7, JVM détecte que la référence vers le string "toto" existe déjà.
Ainsi, s6 et s7 ont donc la même référence.


Question 5 :
java.lang.String n'est pas mutable par sécurité, par exemple empêcher de pouvoir modifier le string s'il s'agit d'une donnée importante.
On a également l'existence du String pool qui est permis grâce à l'immuabilité des strings, ce qui améliore les performances en économisant de la mémoire.


Question 6 :
La méthode "toUpperCase" renvoie le string en majuscule, mais ne modifie pas la valeur du string en lui-même.
*/

