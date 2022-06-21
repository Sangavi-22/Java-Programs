package View.ViewBook;
import Controller.ControlBook.BookControllerService;
public interface BookViewService {
    void inputBookDetails(BookControllerService bookController);
    void passDetailsToController(BookControllerService bookController);
}
