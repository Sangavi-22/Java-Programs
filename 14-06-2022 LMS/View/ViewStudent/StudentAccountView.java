package View.ViewStudent;
import Controller.ControlLibrary.LibraryController;
import Controller.ControlLibrary.LibraryControllerService;
import Controller.ControlStudent.StudentAccountControllerService;
import Input.InputsFromUser;
import Model.LibraryModel;
import View.ViewLibrary.LibraryView;
import View.ViewLibrary.LibraryViewService;

public class StudentAccountView implements StudentAccountViewService {
    private String name,email;
    long mobileNum;
    private long fine=0;
    private StudentAccountControllerService studentAccountController;
    private final LibraryModel libraryModel=new LibraryModel();
    private final LibraryViewService libraryView=new LibraryView();
    private final LibraryControllerService libraryController=new LibraryController(libraryModel,libraryView);
    private final InputsFromUser inputsFromUser=new InputsFromUser();
    public void inputStudentDetails(StudentAccountControllerService studentAccountController) {
        name = inputsFromUser.inputName();
        email = inputsFromUser.inputEmail();
        mobileNum = inputsFromUser.inputMobileNum();
        passDetailsToController(studentAccountController);
    }
    public void passDetailsToController(StudentAccountControllerService studentAccountController){
        studentAccountController.setName(name);
        studentAccountController.setEmail(email);
        studentAccountController.setMobileNum(mobileNum);
    }


}
