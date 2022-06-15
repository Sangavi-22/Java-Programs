package Controller.ControlLogin;
import Controller.ControlLibrarian.LibrarianAccountControllerService;
import Controller.ControlLibrarian.LibrarianAccountUtility;
import Controller.ControlStudent.StudentAccountControllerService;
import Controller.ControlStudent.StudentAccountUtility;
import DataStorage.DataSource;
import DataStorage.DataSourceService;
import Model.LibrarianAccountModel;
import Model.StudentAccountModel;
public class LoginUtility {
    private DataSourceService dataSource= DataSource.getInstance();
    private final LibrarianAccountUtility librarianUtil=LibrarianAccountUtility.getInstance();
    private final StudentAccountUtility studentUtil=StudentAccountUtility.getInstance();
    public boolean containsLibrarianAccount(String userName){
        return dataSource.containsLibrarian(userName);
    }
    public LibrarianAccountModel getLibrarianModel(String userName){
        return dataSource.getLibrarian(userName);
    }
    public LibrarianAccountControllerService getLibrarianAccountController(LibrarianAccountModel librarianAccountModel){
        return librarianUtil.getLibrarianController(librarianAccountModel);
    }
    public boolean containsStudentAccount(String userName){
        return dataSource.containsStudent(userName);
    }
    public StudentAccountModel getStudentModel(String userName){
        return dataSource.getStudent(userName);
    }
    public StudentAccountControllerService getStudentAccountController(StudentAccountModel studentAccountModel){
        return studentUtil.getStudentController(studentAccountModel);
    }

}
