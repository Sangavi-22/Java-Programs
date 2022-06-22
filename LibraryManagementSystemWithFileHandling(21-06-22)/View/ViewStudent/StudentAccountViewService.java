package View.ViewStudent;
import Controller.ControlStudent.StudentAccountControllerService;
public interface StudentAccountViewService {
    void inputStudentDetails(StudentAccountControllerService studentAccountController);
    void passDetailsToController(StudentAccountControllerService studentAccountController);
}
