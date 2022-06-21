package Model;

import DataStorage.DataSource;
import DataStorage.DataSourceService;

public class BookDetailsModel {
    private String bookName;
    private String publicationName;
    private int bookCost,bookId,bookLocation;
    private int bookCount=0;
    private static int locationId;
    private final DataSourceService dataSource= DataSource.getInstance();
    public void setBookId(int bookId) {
        this.bookId=bookId;
    }
    public int getBookId() {
        return this.bookId;
    }
    public void setBookLocation(int bookLocation){
        this.bookLocation=bookLocation;
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
