package Controller.ControlLibrary;
import Model.*;
import Controller.ControlBook.BookControllerService;
public interface LibraryControllerService {
    void setPhoneNum();
    String getPhoneNum();
    void updateView();
    void viewBooks();
    void addBook(int bookId, BookControllerService bookController, BookDetailsModel bookDetailsModel, BookDatesModel bookDatesModel,
                 BookPagesModel bookPagesModel, BookAuthorModel bookAuthorModel);
    boolean incrementBookCount(int id);
    boolean removeBook(int id);
    boolean searchBookByItsId(int id);
    int searchBookByItsName(String book);
    int searchBookByItsAuthorName(String authorName);
    boolean borrowBook(int id, StudentAccountModel studentAccountModel);
    int viewBorrowedBooks(StudentAccountModel studentAccountModel);
    void viewBorrowers();
    long returnBook(int id);
    boolean renewBook(int id);
}
