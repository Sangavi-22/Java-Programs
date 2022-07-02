package Controller.ControlLogin;

public interface LoginControllerService {
    void passLoginController();
    void goToHomePage();
    void setAccountName(String accountName);
    String getAccountName();
    void removeUser(String userName);
    void logOut();
}
