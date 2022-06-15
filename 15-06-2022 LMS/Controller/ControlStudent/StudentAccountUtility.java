package Controller.ControlStudent;
import Model.StudentAccountModel;

import java.util.HashMap;

public class StudentAccountUtility {
    private static StudentAccountUtility studentUtil =null;
    private final HashMap<StudentAccountModel, StudentAccountControllerService> studentController=new HashMap<>();
    private StudentAccountUtility(){
        //constructor
    }
    public static StudentAccountUtility getInstance(){
        if(studentUtil==null) {
            studentUtil=new StudentAccountUtility();
        }
        return studentUtil;
    }
    public void addStudentController(StudentAccountModel studentAccountModel, StudentAccountController studentAccountController){
        studentController.put(studentAccountModel,studentAccountController);
    }
    public StudentAccountControllerService getStudentController(StudentAccountModel studentAccountModel){
        return studentController.get(studentAccountModel);
    }
}
