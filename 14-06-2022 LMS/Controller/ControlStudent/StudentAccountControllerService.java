package Controller.ControlStudent;

import Model.StudentAccountModel;

public interface StudentAccountControllerService {
    void setInputs(String userName,String password,int userId);
    void addStudentToDataSource();
    void menu();
    void setName(String name);
    String getName();
    void setEmail(String email);
    String getEmail();
    void setMobileNum(long mobileNum);
    long getMobileNum();
    void setUserName(String userName);
    String getUserName();
    void setPassword(String password);
    String getPassword();
    void setStudentId(int id);
    int getStudentId();
    void setBorrowedCount();
    void updateBorrowedCount();
    int getBorrowedCount();
    StudentAccountModel getStudentModel();
    void updateView();
}
