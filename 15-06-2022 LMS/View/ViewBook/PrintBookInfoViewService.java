package View.ViewBook;
import java.time.LocalDate;
import java.util.ArrayList;
public interface PrintBookInfoViewService {
    void printBookDetails(int bookId, String bookName, String publisher, int bookCost, int pagesCount, String layout, ArrayList<String> authorsName, int bookLocation);
    void printDates(LocalDate issueDate, LocalDate dueDate, int bookId);
    void printBookId(int id);
}
