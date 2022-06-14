package View.ViewLibrarian;
import Controller.ControlLibrarian.LibrarianAccountControllerService;
import Input.InputsFromUser;

public class LibrarianAccountView implements LibrarianAccountViewService {
    private String name,email;
    long mobileNum;
    private final InputsFromUser inputsFromUser=new InputsFromUser();
    public void inputLibrarianDetails(LibrarianAccountControllerService librarianAccountController) {
         name = inputsFromUser.inputName();
         email = inputsFromUser.inputEmail();
         mobileNum = inputsFromUser.inputMobileNum();
         passDetailsToController(librarianAccountController);
    }
    public void passDetailsToController(LibrarianAccountControllerService librarianAccountController){
        librarianAccountController.setName(name);
        librarianAccountController.setEmail(email);
        librarianAccountController.setMobileNum(mobileNum);
    }


}
