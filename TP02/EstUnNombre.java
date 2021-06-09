import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class EstUnNombre {
	public static void main(String[] args) {
		/* Le "-?" permet de reconnaître les nombres négatifs (optionnel ?)
		 * J'ai fait en sorte de na pas prendre en compte les nombres tels que "010", 
		   avec des 0 inutiles devant (je ne sais pas si c'était vraiment demandé) */
		String regExpNumber = "0|(-?[1-9][0-9]*)", regExpLettersAndNumber = "[a-zA-Z]*(0|(-?[1-9][0-9]*))"; 
		Pattern removeLetters = Pattern.compile("[a-zA-Z]*"); 

		for (String arg : args) {
			/* "regExpNumber" va reconnaître les chaînes qui ne sont consituées que de chiffres
			 * "regExpLettersAndNumber" va reconnaître les chaînes qui ont des lettres, puis que des chiffres 
			 * Ces chaînes seront ensuite substituer à l'aide de "removeLetters", qui va substituer toutes les lettres par le vide (donc comme si on les effaçait)
			 * Les chaînes qui ne contenaient que des chiffres n'ont rien de remplacé */
			if (Pattern.matches(regExpNumber, arg) || Pattern.matches(regExpLettersAndNumber, arg)) {
				System.out.println(removeLetters.matcher(arg).replaceAll("")); 
			}
		}
	}
}

/*
Question 1 :
java.util.regex.Pattern et sa méthode compile permettent de créer une expression régulière à partir du type Pattern et de la méthode compile.
java.util.regex.Matcher permet d'interpréter un Pattern, par exemple en vérifiant qu'une expression respecte un Pattern donné.
*/