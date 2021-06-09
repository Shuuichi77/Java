public class Morse {
	public static void main(String[] args) {
		/* Methode avec "+"
    	for (String arg : args) {
    		System.out.print(arg + " Stop. ");
    	} */

    	/* Methode avec StringBuilder */
    	StringBuilder builder = new StringBuilder();
    	
    	for (String arg : args) {
    		builder.append(arg).append(" Stop. ");;
    	}

    	System.out.println(builder.toString());
    }
}

/*
Question 2 :
java.lang.StringBuilder permet de concaténer des chaînes de caractère dans une variable de type "StringBuilder".
Sa méthode renvoie un objet de type "StringBuilder" afin de pouvoir faire plusieurs "append" sur une même ligne de code, sans avoir à retourner à la ligne.


Question 3 :
Avec StringBuilder, on aura de meilleures performances si on doit concaténer une très grande quantité de chaînes de caractères.
Le codage est également plus court et conçit.
*/
