package LibraryCatalog;;

import java.util.ArrayList;
import java.util.List;

public class LibraryCatalog {

    private List<Book> catalog = new ArrayList<Book>();

    public LibraryCatalog() {

    }

    public void addBook(Book book){
        catalog.add(book);
    }

    public void removeBook(Book book){
        catalog.remove(book);
    }

    public void listCatalog(LibraryCatalog catalog){
        System.out.println(catalog.toString());
    }

    @Override
    public String toString() {
        String s = "\n" + "Book List (title and no. of pages):";
        for (Book b : catalog) {
            s = s + "\n" + b.getName() + " | " + b.getNumOfPages() + " pgs.";
        }
        return s;
    }
}
