import java.util.Objects;
import java.lang.Override;

public record Book (String title, String author) {
	/* Exercice 1 */
	/* Compact Constructor */
	public Book {
		Objects.requireNonNull(title);
		Objects.requireNonNull(author);
	}

	/* Compact Constructor */
	public Book (String title) {
		this(title, "<no author>");
		title = Objects.requireNonNull(title);
	}

	/**
 	 * Modifie le champ "titre" d'un objet Book
 	 *
 	 * @param title Nouveau titre du Book
 	 * @return Un nouveau objet "Book" avec le même auteur, mais un nouveau titre
 	 */
	public Book withTitle(String title) {
    	var newBookTitle = new Book(title, this.author);
    	return newBookTitle;
 	}

 	/* Exercice 2 */
 	/**
 	 * Compare 2 objets Book pour voir s'ils ont le même champ "author"
 	 *
 	 * @param otherBook Le Book avec lequel on va comparer
 	 * @return True si c'est bien le même auteur, False sinon
 	 */
 	public boolean isFromTheSameAuthor(Book otherBook) {
 		return this.author.equals(otherBook.author);
 	}

 	@Override
 	public String toString() {
		return title + " by " + author;
	}

	
	/* Main */
	public static void main(String[] args) {
		/* Exercice 1 */
		var weirdBook = new Book("titreLivre");
		/* Objects.requireNonNull(weirdBook.title); */
		Objects.requireNonNull(weirdBook.author);

		System.out.println("\nExercice 1 :");
  		System.out.println(weirdBook.title + ' ' + weirdBook.author);
  		weirdBook = weirdBook.withTitle("monNouveauTitre");
  		System.out.println(weirdBook.title + ' ' + weirdBook.author);

  		/* Exercice 2 */
  		System.out.println("\nExercice 2 :");
  		var b1 = new Book("Da Java Code", "Duke Brown");
	    var b2 = b1;
	    var b3 = new Book("Da Java Code", "Duke Brown");

	    System.out.println(b1.equals(b2));
		System.out.println(b1.equals(b3));

  		var book1 = new Book("Da Vinci Code", "Dan Brown");
  		var book2 = new Book("Angels & Demons", new String("Dan Brown"));
  		System.out.println(book1.isFromTheSameAuthor(book2));
	    System.out.println(book1);
	}

}

/* 
---------- Exercice 1 ----------

Question 2 :
On print tout simplement les attributs nouvel objet "book" qu'on vient de créer dans la classe à l'aide du record

Question 3 :
On n'est plus dans la même classe, il ne peut pas accéder aux attributs (private car on est dans un record)
On peut importer la classe Book.

Question 4 :
On utilise la méthode requireNonNull qui s'utilise sur le champ de la façon suivante :
Objects.requireNonNull(weirdBook.title);
Objects.requireNonNull(weirdBook.author);

Question 7 :
Selon les attributs qu'on a fourni lors de la déclaration de l'objet, le compilateur saura quel constructeur utiliser

Question 8 :
L'attribut "titre" est final, on ne peut pas le modifier une fois qu'on lui a assigné une valeur
De la même manière que String.toUpperCase, on crée un nouveau Book qu'on return


---------- Exercice 2 ----------

Question 1 :
== compare les références, pas les valeurs.
b1 et b2 ont la même référence, car lors de sa déclaration (b2 = b1), il copie sa référence
Cependant b3 a été crée depuis une nouvelle référence (new) : même s'il a les mêmes valeurs que b1, il n'ont pas la même référence.

Question 2 :
On utilise la méthode equals :
System.out.println(b1.equals(b2));
System.out.println(b1.equals(b3));

Question 4 :
Il faut redéfinir la méthode toString

Question 6 :
En utilisant @Override, le compilateur vérifie qu'on a correctement redéfini la méthode.
Il affiche un message d'erreur dans le cas contraire
*/
