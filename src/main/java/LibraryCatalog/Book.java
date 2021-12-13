package LibraryCatalog;;


public class Book {

    String name;
    int numOfPages;


    public Book(String name, int numOfPages) {
        this.name = name;
        this.numOfPages = numOfPages;
    }

    public String getName() {
        return name;
    }

    public int getNumOfPages() {
        return numOfPages;
    }

}
