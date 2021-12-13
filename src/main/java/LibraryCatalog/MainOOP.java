package LibraryCatalog;


public class MainOOP {

    public static void main(String[] args) {


        LibraryCatalog list = new LibraryCatalog();


        Book Novel1 = new Novel("Mystery", "DaVinci Code", 360);
        Book Novel2 = new Novel("Love", "A night in Paris", 235);
        Book Album1 = new Album('A', "Top French Castles", 847);
        Book Album2 = new Album('C', "The history of Formula 1 in pictures", 560);

        list.addBook(Novel1);
        list.addBook(Novel2);
        list.addBook(Album1);
        list.listCatalog(list);
        list.addBook(Album2);

        System.out.println("\n" + "Now removing and listing new catalog:");
        list.removeBook((Novel2));
        list.listCatalog(list);


    }
}
