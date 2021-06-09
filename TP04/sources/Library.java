import java.util.*;

public class Library {
	/* 
	private ArrayList<Book> books;
	
	public Library () {
		this.books = new ArrayList<Book>();
	}
	
	public void add (Book newBook) {
		Objects.requireNonNull(newBook);
		this.books.add(newBook);
	}
	
	public Book findByTitle (String title) {
		for (Book book : this.books) {
			if (book.title().equals(title)) {
				return book;
			}
		}
		return null;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		for (Book book : this.books) {
			builder.append(book.toString()).append("\n");
		}
		
		return builder.toString();
	}
	*/
	
	private LinkedHashMap<String, String> books;
	
	/* Constructeur */
	public Library () {
		this.books = new LinkedHashMap<String, String>();
	}
	
	/**
 	 * Ajoute un objet "Book" à la liste "books"
 	 *
 	 * @param newBook Nouveau objet "Book" à ajouter
 	 */
	public void add (Book newBook) {
		Objects.requireNonNull(newBook);
		this.books.put(newBook.title(), newBook.author());
	}
	
	/**
 	 * Cherche un livre dans la librarie "books" à partir d'un titre
 	 *
 	 * @param mysteryTitle Titre du livre qu'on cherche
 	 * @return Le livre "Book" si on l'a trouvé, null sinon
 	 */
	public Book findByTitle (String title) {
		String author = this.books.get(title);

		return Objects.isNull(author) ? null : new Book(title, author);
	}
	
	/**
	 * Supprime tous les livres d'un auteur dans notre Libary "books"
	 * 
	 * @param author Le nom de l'auteur dont on va supprimer les livres
	 */
	public void removeAllBooksFromAuthor(String author) {
		/*
		for (String titleKey: this.books.keySet()) {
			if (this.books.get(titleKey).equals(author)) {
				this.books.remove(titleKey);
			}
		}
		*/
		/*
		var iter = this.books.keySet().iterator();
        while (iter.hasNext()) {
            if (this.books.get(iter.next()).equals(author)) {
                iter.remove();
            }
        }
        */
		
		this.books.keySet().removeIf(titleKey -> this.books.get(titleKey).equals(author));
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		
		for (String titleKey : this.books.keySet()) {
			builder.append(findByTitle(titleKey)).append("\n");
		}
		
		return builder.toString();
	}
	
	/* Main */
	public static void main(String[] args) {
		var library = new Library();
		Book book1 = new Book("Titre1", "Auteur1"), book2 = new Book("Titre2", "Auteur2"), book3 = new Book("Titre0", "Auteur1");
		
		library.add(book1);
		library.add(book2);
		library.add(book3);
		
		System.out.println("Cherche \"Titre1\" : " + library.findByTitle("Titre1"));
		System.out.println("Cherche \"Titre3\" : " + library.findByTitle("Titre3"));
		
		System.out.println("\nPrint library :\n" + library);
		
		System.out.println("On enleve les livres de l'auteur \"Auteur1\"");
		library.removeAllBooksFromAuthor("Auteur1");
		
		System.out.println("\nPrint library : \n" + library);
	}
}


/*
Exercice 2

4/ Le compilateur utilise la méthode iterator, iterator.next(), et iterator.hasnext()

5/ findByTitle renvoie null plutôt que lever une exception puisque ce n'est pas réellement nécessaire,
on ne trouve pas un livre dans la librairie, rien de plus, pas besoin de lever une exception pour si peu.


Exercice 3

1/ La compléxité de findByTitle est O(n), n le nombre de Book dans la Librairie,
car on peut tomber sur le cas où on parcourt toute la librairie

2/ La structure donnée de HashMap est une Map 
On va utiliser une HashMap pour le champ books plutôt qu'une ArrayList
La complexité sera donc de O(1)

4/ On utilise une classe plutôt qu'un record pour ne pas y accéder depuis l'extérieur

5/ On va utiliser la méthode .keySet()

6/ On remplace HashMap par LinkedHashMap

7/ On a l'exception java.util.ConcurrentModificationException, car on ne peut pas modifier
le contenu d'une collection dans un foreach
*/
















