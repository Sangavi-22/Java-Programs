package Controller.ControlLibrarian;
import Model.LibrarianAccountModel;
import java.util.HashMap;
public class LibrarianAccountUtility {
    private static LibrarianAccountUtility librarianUtil =null;
    private final HashMap<LibrarianAccountModel, LibrarianAccountControllerService> librarianController=new HashMap<>();
    private LibrarianAccountUtility(){
        //constructor
    }
    public static LibrarianAccountUtility getInstance(){
        if(librarianUtil==null) {
            librarianUtil=new LibrarianAccountUtility();
        }
        return librarianUtil;
    }
    public void addLibrarianController(LibrarianAccountModel librarianAccountModel, LibrarianAccountController librarianAccountController){
        librarianController.put(librarianAccountModel,librarianAccountController);
    }
    public LibrarianAccountControllerService getLibrarianController(LibrarianAccountModel librarianAccountModel){
        return librarianController.get(librarianAccountModel);
    }
}
