package View.ViewStudent;

import Controller.ControlStudent.StudentAccountControllerService;

public interface StudentMenuViewService {
    void setController(StudentAccountControllerService studentAccountController);
    void studentMenu();
}
