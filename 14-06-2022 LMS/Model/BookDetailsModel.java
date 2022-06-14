package Model;
public class BookDetailsModel {
    private String bookName;
    private String publicationName;
    private int bookCost,bookId,bookLocation;
    private int bookCount=0;
    private static int identification =0,locationId=0;
    public void setBookId() {
        identification=identification+1;
        this.bookId = identification;
    }
    public int getBookId() {
        return this.bookId;
    }
    public void setBookLocation(){
        locationId=locationId+1;
        this.bookLocation=locationId;
    }
    public int getBookLocation(){
        return this.bookLocation;
    }
    public void setBookName(String bookName){
        this.bookName=bookName;
    }
    public String getBookName(){
        return this.bookName;
    }
    public void setPublicationName(String publicationName){
        this.publicationName=publicationName;
    }
    public String getPublicationName(){
        return this.publicationName;
    }
    public void setBookCount(){
        this.bookCount=this.bookCount+1;
    }
    public int getBookCount(){
        return this.bookCount;
    }
    public void updateBookCount(){
        this.bookCount=this.bookCount-1;
    }
    public void setBookCost(int bookCost){
        this.bookCost=bookCost;
    }
    public int getBookCost(){
        return this.bookCost;
    }
}
