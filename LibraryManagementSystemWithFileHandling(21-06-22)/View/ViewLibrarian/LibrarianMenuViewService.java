package View.ViewLibrarian;
import Controller.ControlLibrarian.LibrarianAccountControllerService;
public interface LibrarianMenuViewService {
    void setController(LibrarianAccountControllerService librarianAccountController);
    void librarianMenu();
}
