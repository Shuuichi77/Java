public class Test {
	public static void main(String[] args) {
     	var first = args[0];
     	var second = args[1];
     	var last = args[2];
     	System.out.println(first + ' ' + second + ' ' + last);
	}
}
/* 
Question 4 :
On peut utilise '' car il n'y a qu'un seul caractère, l'espace vide.
'' s'utilise pour les caractères seuls, s'il y a plus d'1 caractère, on utilisera ""
On aperçoit qu'on crée des strings lorsqu'on print.


Question 5 :
Il n'y a pas de création de string avec StringBuilder.
On privilegiera StringBuilder si on doit concaténer de nombreuses chaines entre elles,
"+" dans le cas contraire.
Mettre un "+" dans un append n'a pas de sens puisque si on crée une variable avec StringBuilder, c'est justement pour éviter les "+".
*/
