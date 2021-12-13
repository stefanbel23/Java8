package LibraryCatalog;;

public class Album extends Book {

    char paperQuality;

    public Album(char paperQuality, String name, int numOfPages) {
        super(name, numOfPages);
        this.paperQuality = paperQuality;
    }

    public char getPaperQuality() {
        return paperQuality;
    }
}
