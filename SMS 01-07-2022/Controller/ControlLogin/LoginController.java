package Controller.ControlLogin;

import DataStorage.DataSource;
import DataStorage.DataSourceService;
import Model.LoginModel;
import View.Login.LoginViewService;

public class LoginController implements LoginControllerService {
    private final LoginModel loginModel;
    private final LoginViewService loginView;
    private final DataSourceService dataSource= DataSource.getInstance();

    public LoginController(LoginModel loginModel, LoginViewService loginView) {
        this.loginModel = loginModel;
        this.loginView = loginView;
    }
    public void goToHomePage(){
        this.loginView.homePage();
    }
    public void passLoginController(){
        this.loginView.setLoginController(this);
    }
    public void setAccountName(String accountName){
        this.loginModel.setAccountName(accountName);
    }
    public String getAccountName(){
        return this.loginModel.getAccountName();
    }
    public void removeUser(String user) {
        if(dataSource.containsSeller(user) && this.loginModel.getAccountName().equals(user)) {
            //dataSource.removeSeller(user);
            this.loginView.removedUser("Seller");
        }
        else if(dataSource.containsCustomer(user)){
            //dataSource.removeCustomer(user);
            this.loginView.removedUser("Customer");
        }
        else{
            this.loginView.removedUser("No User");
        }
    }
    public void logOut() {
        loginView.loggedOut();
    }
}
