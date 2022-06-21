package View.ViewStudent;
import Input.InputsFromUser;
import Controller.ControlStudent.StudentAccountControllerService;
public class StudentAccountView implements StudentAccountViewService {
    private String name,email;
    private long mobileNum;
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
