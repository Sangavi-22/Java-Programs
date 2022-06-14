package View.ViewBook;
import Controller.ControlBook.BookControllerService;
import java.time.LocalDate;
import java.util.ArrayList;

public interface BookViewService {
    void inputBookDetails(BookControllerService bookController);
    void passDetailsToController(BookControllerService bookController);
    void printBookDetails(int bookId, String bookName, String publisher, int bookCost, int pagesCount, String layout, ArrayList<String> authorsName, int bookLocation);
    void printDates(LocalDate issueDate, LocalDate dueDate, int bookId);
    void printBookId(int id);
}
