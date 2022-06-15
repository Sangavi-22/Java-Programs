package View.ViewLibrarian;
import Controller.ControlLibrarian.LibrarianAccountControllerService;
public interface LibrarianAccountViewService {
    void inputLibrarianDetails(LibrarianAccountControllerService librarianAccountController);
    void passDetailsToController(LibrarianAccountControllerService librarianAccountController);
}
