package Controller.ControlLibrary;
import Model.*;
import View.ViewLibrary.LibraryViewService;
import java.util.ArrayList;
import java.util.HashMap;
import Controller.ControlBook.BookControllerService;
import Controller.ControlBook.BookUtility;
import Controller.ControlStudent.StudentAccountControllerService;
import Controller.ControlStudent.StudentAccountUtility;
import DataStorage.DataSource;
import DataStorage.DataSourceService;
public class LibraryController implements LibraryControllerService {
    private final LibraryModel model;
    private final LibraryViewService view;
    private final BookUtility bookUtil =BookUtility.getInstance();
    private final DataSourceService dataSource=DataSource.getInstance();
    private final StudentAccountUtility studentUtil=StudentAccountUtility.getInstance();
    private HashMap<Integer, BookControllerService>libraryBooksController;
    private  HashMap<Integer, StudentAccountModel>borrowerDetails;
    public LibraryController(LibraryModel model,LibraryViewService view){
        this.model=model;
        this.view=view;
    }
    public void setPhoneNum(){
        this.model.setPhoneNum();
    }
    public String getPhoneNum(){
        return this.model.getPhoneNum();
    }
    public void updateView(){
        view.printPhoneNum(this.getPhoneNum());
    }
    public void viewBooks(){
        libraryBooksController= bookUtil.getBookControllers();
        for(int key:libraryBooksController.keySet()) {
            libraryBooksController.get(key).updateView();
        }
    }
    public void addBook(int bookId, BookControllerService bookController, BookDetailsModel bookDetailsModel, BookDatesModel bookDatesModel,
                        BookPagesModel bookPagesModel,BookAuthorModel bookAuthorModel){
        bookUtil.addBookController(bookId,bookController);
        dataSource.addBookDetails(bookId,bookDetailsModel);
        dataSource.addBookDate(bookId,bookDatesModel);
        dataSource.addBookAuthors(bookId,bookAuthorModel);
        dataSource.addBookPages(bookId,bookPagesModel);
    }
    public boolean incrementBookCount(int id){
        boolean found;
        if (this.containsId(id)) {
            BookControllerService bookController = this.returnObject(id);
            bookController.setBookCount();
            bookController.updateView();
            found=true;
        }
        else{
            found=false;
        }
        return found;
    }
    public boolean removeBook(int id){
        boolean removed;
        if (this.containsId(id)) {
            bookUtil.removeBookController(id);
            dataSource.removeBookDetails(id);
            dataSource.removeBookDate(id);
            dataSource.removeBookAuthors(id);
            dataSource.removeBookPages(id);
            removed=true;
        }
        else {
            removed = false;
        }
       return removed;
    }
    public boolean searchBookByItsId(int id){
        boolean found;
        if (this.containsId(id)) {
            BookControllerService bookController = this.returnObject(id);
            bookController.updateView();
            found=true;
        }
        else{
            found=false;
        }
        return found;
    }
    public int searchBookByItsName(String book) {
        int found = 0;
        libraryBooksController = bookUtil.getBookControllers();
        for (int key : libraryBooksController.keySet()) {
            if (libraryBooksController.get(key).getBookName().toLowerCase().contains(book.toLowerCase())) {
                libraryBooksController.get(key).updateView();
                found++;
            } else {
                continue;
            }
        }
        return found;
    }
    public int searchBookByItsAuthorName(String authorName){
        int found=0;
        libraryBooksController= bookUtil.getBookControllers();
        for(int key:libraryBooksController.keySet()) {
            ArrayList<String> AuthorsName = libraryBooksController.get(key).getAuthorsName();
            for (String name : AuthorsName) {
                if (name.toLowerCase().contains(authorName.toLowerCase())) {
                    System.out.println(authorName);
                    libraryBooksController.get(key).updateView();
                    found++;
                    break;
                }
            }
        }
        return found;
    }
    public boolean borrowBook(int id, StudentAccountModel studentAccountModel){
        boolean found;
        libraryBooksController= bookUtil.getBookControllers();
        if(libraryBooksController.containsKey(id) && libraryBooksController.get(id).getBookCount()>0) {
            libraryBooksController.get(id).updateBookCount();
            libraryBooksController.get(id).setBookIssueDate();
            libraryBooksController.get(id).setBookDueDate();
            libraryBooksController.get(id).updateView();
            libraryBooksController.get(id).printDates();
            dataSource.addBorrower(id,studentAccountModel);
            StudentAccountControllerService studentAccountController=studentUtil.getStudentController(studentAccountModel);
            studentAccountController.setBorrowedCount();
            found=true;
        }
        else {
            found=false;
        }
        return found;
    }
    public int viewBorrowedBooks(StudentAccountModel studentAccountModel) {
        borrowerDetails = dataSource.getBorrowers();
        libraryBooksController =bookUtil.getBookControllers();
        int found=0;
        for (int key : borrowerDetails.keySet()) {
            if (borrowerDetails.get(key).equals(studentAccountModel)) {
                libraryBooksController.get(key).updateView();
                libraryBooksController.get(key).printDates();
                found++;
            }
            else{
                continue;
            }
        }
        return found;
    }
    public void viewBorrowers(){
        borrowerDetails=dataSource.getBorrowers();
        libraryBooksController= bookUtil.getBookControllers();
        for(int key:borrowerDetails.keySet()){
            libraryBooksController.get(key).updateView();
            libraryBooksController.get(key).printDates();
            StudentAccountControllerService studentAccountController=studentUtil.getStudentController(borrowerDetails.get(key));
            studentAccountController.updateView();
        }
    }
    public long returnBook(int id)
    {
        long difference;
        libraryBooksController= bookUtil.getBookControllers();
        borrowerDetails=dataSource.getBorrowers();
        if(libraryBooksController.containsKey(id)) {
            borrowerDetails.get(id).updateBorrowedCount();
            libraryBooksController.get(id).setBookCount();
            libraryBooksController.get(id).setBookReturnedDateForReturn();
            difference=libraryBooksController.get(id).hasExpired();
            dataSource.removeBorrower(id);
        }
        else{
            difference=0;
        }
        return difference;
    }
    public boolean renewBook(int id) {
        boolean renewed=false;
        libraryBooksController= bookUtil.getBookControllers();
        borrowerDetails=dataSource.getBorrowers();
        if (libraryBooksController.containsKey(id) && borrowerDetails.containsKey(id)) {
            libraryBooksController.get(id).setBookReturnedDateForRenew();
            if (libraryBooksController.get(id).hasExpired()<0) {
                libraryBooksController.get(id).setBookIssueDate();
                libraryBooksController.get(id).setBookDueDate();
                libraryBooksController.get(id).printDates();
            }
            renewed=true;
        }
        else{
            renewed=false;
        }
        return renewed;
    }
    public boolean containsId(int id){
        return bookUtil.containsBookController(id);
    }
    public BookControllerService returnObject(int id){
        return bookUtil.getBookController(id);
    }
}