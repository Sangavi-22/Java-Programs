import Controller.ControlLogin.LoginController;
import Controller.ControlLogin.LoginControllerService;
import Model.LoginModel;
import View.Login.LoginView;
import View.Login.LoginViewService;

public class Main {
    public static void main(String[] args){
        LoginModel loginModel=new LoginModel();
        LoginViewService loginView=new LoginView();
        LoginControllerService loginController=new LoginController(loginModel,loginView);
        loginController.passLoginController();
        loginController.goToHomePage();
    }
}
