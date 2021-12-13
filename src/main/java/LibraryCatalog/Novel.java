package LibraryCatalog;;

public class Novel extends Book  {

    String novelType;

    public Novel(String novelType, String name, int numOfPages) {
        super(name, numOfPages);
        this.novelType = novelType;
    }

    public String getNovelType() {
        return novelType;
    }
}
