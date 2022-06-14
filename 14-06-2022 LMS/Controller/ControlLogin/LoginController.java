package Controller.ControlLogin;
import DataStorage.DataSource;
import DataStorage.DataSourceService;
import Model.LoginModel;
import View.ViewLogin.LoginViewService;

public class LoginController implements LoginControllerService {
    private final LoginModel loginModel;
    private final LoginViewService loginView;
    private DataSourceService dataSource= DataSource.getInstance();
    public LoginController(LoginModel loginModel, LoginViewService loginView) {
        this.loginModel = loginModel;
        this.loginView = loginView;
    }
    public void goToHomePage(){
        loginView.homePage();
    }
    public void removeUser(String user) {
        if(dataSource.containsLibrarian(user)) {
            dataSource.removeLibrarian(user);
            this.loginView.removedUser(user);
        }
        else if(dataSource.containsStudent(user)){
            dataSource.removeStudent(user);
            this.loginView.removedUser(user);
        }
        else{
            this.loginView.removedUser("No User");
        }

    }
    public void setLoginAccess(){
        this.loginModel.setLoginAccess();
    }
    public void logOut() {
        loginView.loggedOut();
    }
}
