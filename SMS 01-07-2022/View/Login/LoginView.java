package View.Login;

import Administator.Admin;
import Administator.AdminService;
import Controller.ControlCustomer.CustomerAccountController;
import Controller.ControlCustomer.CustomerAccountControllerService;
import Controller.ControlLogin.LoginControllerService;
import Controller.ControlLogin.LoginUtility;
import Controller.ControlSeller.SellerAccountController;
import Controller.ControlSeller.SellerAccountControllerService;
import Inputs.InputsFromUser;
import Model.Customer.CustomerAccountModel;
import Model.Seller.SellerAccountModel;
import View.Customer.*;
import View.Seller.*;

public class LoginView implements LoginViewService {
    private LoginControllerService loginController=null;
    private final LoginUtility loginUtil=new LoginUtility();
    private final InputsFromUser inputsFromUser=new InputsFromUser();
    public void setLoginController(LoginControllerService loginController) {
        this.loginController = loginController;
    }
    public void homePage(){
        try {
            System.out.println();
            System.out.println("*******************************************************");
            System.out.println("!        WELCOME TO SHOPPING MANAGEMENT SYSTEM        !");
            System.out.println("*******************************************************");
            System.out.println("!               Enter 1 to SignUp                     !");
            System.out.println("!               Enter 2 to Login                      !");
            System.out.println("*******************************************************");
            System.out.println();
            int userChoice=inputsFromUser.inputChoice();
            switch(UserActions.values()[userChoice-1]) {
                case SIGN_UP:
                    System.out.println();
                    System.out.println("************************SignUp*************************");
                    System.out.println();
                    String userName=inputsFromUser.inputUserName();
                    while(loginUtil.isSellerPresentAlready(userName) || loginUtil.isCustomerPresentAlready(userName)){
                        System.out.println("User already exist!!!!");
                        userName = inputsFromUser.inputUserName();
                    }
                    String password=inputsFromUser.inputPassword();
                    AdminService admin=new Admin();
                    int userId=admin.generateId();
                    if(userId==0) {
                        System.out.println("You have provided invalid inputs. Taking you back to the main page");
                        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println("!                 Please Wait!!!!!                    !");
                        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        homePage();
                        break;
                    }
                    else{
                        System.out.println("Processing....This may take a few moments");
                    }
                    System.out.println();
                    System.out.println("*******************************************************");
                    System.out.println("!              USER ID GENERATED                      !");
                    System.out.println("*******************************************************");
                    System.out.println(" Your id is: " + userId);
                    System.out.println("*******************************************************");
                    System.out.println();
                    System.out.println("Your account has been created successfully");
                    System.out.println();
                    System.out.println("Please provide us the following details to complete your profile creation");
                    if(userId>=1 && userId<=1000){
                        loginController.setAccountName(userName);
                        SellerAccountModel sellerAccountModel=new SellerAccountModel();
                        SellerAccountViewService sellerAccountView = new SellerAccountView();
                        SellerMenuViewService sellerMenuView=new SellerMenuView();
                        PrintSellerInfoViewService printSellerInfoView=new PrintSellerInfoView();
                        SellerAccountControllerService sellerAccountController = new SellerAccountController(sellerAccountModel, sellerAccountView,sellerMenuView,printSellerInfoView);
                        sellerAccountController.setUserName(userName);
                        sellerAccountController.setPassword(password);
                        sellerAccountController.setUserId(userId);
                        sellerAccountController.setOtherInputs();
                        sellerAccountController.menu();
                    }
                    else if(userId>=1001 && userId<=2000) {
                        loginController.setAccountName(userName);
                        CustomerAccountModel customerAccountModel = new CustomerAccountModel();
                        CustomerAccountViewService customerAccountView = new CustomerAccountView();
                        CustomerMenuViewService customerMenuView=new CustomerMenuView();
                        PrintCustomerInfoViewService printCustomerInfoView=new PrintCustomerInfoView();
                        CustomerAccountControllerService customerAccountController = new CustomerAccountController(customerAccountModel, customerAccountView,customerMenuView, printCustomerInfoView);
                        customerAccountController.setUserName(userName);
                        customerAccountController.setPassword(password);
                        customerAccountController.setUserId(userId);
                        customerAccountController.setOtherInputs();
                        customerAccountController.menu();

                    }
                    else {
                        System.out.println("Invalid id. Taking you back to the main page");
                        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println("!                 Please Wait!!!!!                    !");
                        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    }
                    break;
                case LOGIN:
                    break;
                case DEFAULT:
                    break;
            }
        }
        catch(ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException(e);
        }
    }
    public void removedUser(String user) {
        if(user.equals("Customer")) {
            System.out.println("Customer Account has been removed");
        }
        else if (user.equals("Seller")) {
            System.out.println("Your Account has been removed");
            homePage();
        }
        else {
            System.out.println("Sorry! User Account does not exist");
        }
    }
    public void loggedOut(){
        System.out.println("Thank you. You have been Logged out successfully");
        homePage();
    }

}
