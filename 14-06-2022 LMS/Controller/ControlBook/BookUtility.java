package Controller.ControlBook;

import java.util.HashMap;
public class BookUtility {
    private static BookUtility bookUtil =null;
    private BookUtility(){
        //constructor
    }
    public static BookUtility getInstance(){
        if(bookUtil==null) {
            bookUtil=new BookUtility();
        }
        return bookUtil;
    }
    private HashMap<Integer, BookControllerService> libraryBooksController=new HashMap<>();
    public void addBookController(int id,BookControllerService bookController) {
        libraryBooksController.put(id, bookController);
    }
    public BookControllerService getBookController(int id){
        return libraryBooksController.get(id);
    }
    public boolean containsBookController(int id){
        return libraryBooksController.containsKey(id);
    }
    public void removeBookController( int id){
        libraryBooksController.remove(id);
    }
    public HashMap<Integer,BookControllerService> getBookControllers(){
        return libraryBooksController;
    }
}
