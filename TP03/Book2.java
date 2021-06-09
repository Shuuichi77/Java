import java.util.Objects;
import java.lang.Override;

public class Book2 {
    private final String title;
    private final String author;

    /* Constructor */
    public Book2(String title, String author) {
        this.title = title;
        this.author = author;
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, author);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Book2)) {
            return false;
        }
        Book2 book = (Book2) o;
        return title.equals(book.title) && author.equals(book.author);
    }        


    /* Main */
    public static void main(String[] args) {
        var book1 = new Book2("Da Vinci Code", "Dan Brown");
        var book2 = new Book2("Da Vinci Code", "Dan Brown");

        System.out.println(book1.equals(book2));
    }
}

/* 
Question 1 :
Ici on compare les références de chaque attribut qui ne sont pas les mêmes dans notre cas 
puisqu'on crée des reférences différentes lors de la déclaration de book1 et book2.
*/