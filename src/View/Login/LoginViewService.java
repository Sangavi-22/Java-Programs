package View.Login;

import Controller.ControlLogin.LoginControllerService;
public interface LoginViewService {
    void setLoginController(LoginControllerService loginController);
    void homePage();
    void removedUser(String user);
    void loggedOut();
}
