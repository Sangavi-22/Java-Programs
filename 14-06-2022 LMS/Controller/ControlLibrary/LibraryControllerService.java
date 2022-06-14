package Controller.ControlLibrary;
import Controller.ControlBook.BookControllerService;
import Model.*;
public interface LibraryControllerService {
    void setPhoneNum();
    String getPhoneNum();
    void updateView();
    void viewBooks();
    void addBook(int bookId, BookControllerService bookController, BookDetailsModel bookDetailsModel, BookDatesModel bookDatesModel,
                 BookPagesModel bookPagesModel, BookAuthorModel bookAuthorModel);
    void removeBook(int id);
    int searchBookByItsName(String book);
    int searchBookByItsAuthorName(String authorName);
    boolean borrowBook(int id, StudentAccountModel studentAccountModel);
    int viewBorrowedBooks(StudentAccountModel studentAccountModel);
    void viewBorrowers();
    long returnBook(int id);
    boolean renewBook(int id);
    boolean containsId(int id);
    BookControllerService returnObject(int id);
}
