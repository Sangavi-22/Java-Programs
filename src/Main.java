import Controller.ControlLogin.LoginController;
import Model.LoginModel;
import View.ViewLogin.LoginView;
public class Main{
    public static void main(String args[]){
        LoginView loginView=new LoginView();
        LoginModel loginModel=new LoginModel();
        LoginController loginController=new LoginController(loginModel, loginView);
        loginController.setLoginAccess();
        loginController.goToHomePage();
    }
}
