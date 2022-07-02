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
    private LoginControllerService loginController;
    private final LoginUtility loginUtil=new LoginUtility();
    private final InputsFromUser inputsFromUser=new InputsFromUser();
    public void setLoginController(LoginControllerService loginController) {
        this.loginController = loginController;
    }
    public void homePage(){
        try {
            System.out.println();
            System.out.println("*******************************************************");
            System.out.println("!            WELCOME TO MOBILE GALAXY STORE           !");
            System.out.println("*******************************************************");
            System.out.println("!                 Enter 1 to SignUp                   !");
            System.out.println("!                 Enter 2 to Login                    !");
            System.out.println("*******************************************************");
            System.out.println();
            int userChoice=inputsFromUser.inputChoice();
            switch(UserActions.values()[userChoice-1]) {
                case SIGN_UP:
                    System.out.println();
                    System.out.println("*******************************************************");
                    System.out.println("                        SIGN UP                        ");
                    System.out.println("*******************************************************");
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
                        System.out.println("Unauthorized user. Taking you back to the main page");
                        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println("!                 Please Wait!!!!!                    !");
                        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        homePage();
                        break;
                    }
                    else{
                        System.out.println("Processing....This may take a few moments");
                        if(userId>=1 && userId<=1000){
                            System.out.println("Your account has been created successfully");
                            System.out.println();
                            System.out.println("*******************************************************");
                            System.out.println("!              USER ID GENERATED                      !");
                            System.out.println("*******************************************************");
                            System.out.println(" Your id is: " + userId);
                            System.out.println("*******************************************************");
                            System.out.println();
                            System.out.println("Please provide us a little more details to complete your profile");
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
                            System.out.println("Your account has been created successfully");
                            System.out.println();
                            System.out.println("*******************************************************");
                            System.out.println("!              USER ID GENERATED                      !");
                            System.out.println("*******************************************************");
                            System.out.println(" Your id is: " + userId);
                            System.out.println("*******************************************************");
                            System.out.println();
                            System.out.println("Please provide us a little more details to complete your profile");
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
                            System.out.println(" Some problem occurred. Could not create your account");
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("! Please wait while we take you back to the main page !");
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        }
                    }
                    homePage();
                    break;
                case LOGIN:
                    System.out.println();
                    System.out.println("*******************************************************");
                    System.out.println("                        LOGIN IN                       ");
                    System.out.println("*******************************************************");
                    System.out.println();
                    userName=inputsFromUser.inputUserName();
                    password=inputsFromUser.inputPassword();
                    if(loginUtil.isSellerPresentAlready(userName) && loginUtil.passwordMatchesForSeller(userName,password)){
                        SellerAccountModel sellerAccountModel= loginUtil.getSellerModel(userName);
                        SellerAccountViewService sellerAccountView = new SellerAccountView();
                        SellerMenuViewService sellerMenuView=new SellerMenuView();
                        PrintSellerInfoViewService printSellerInfoView=new PrintSellerInfoView();
                        SellerAccountControllerService sellerAccountController=new SellerAccountController(sellerAccountModel, sellerAccountView,sellerMenuView,printSellerInfoView);
                        sellerAccountController.menu();
                    }
                    if(loginUtil.isCustomerPresentAlready(userName) && loginUtil.passwordMatchesForCustomer(userName,password)){
                        CustomerAccountModel customerAccountModel = loginUtil.getCustomerModel(userName);
                        CustomerAccountViewService customerAccountView = new CustomerAccountView();
                        CustomerMenuViewService customerMenuView=new CustomerMenuView();
                        PrintCustomerInfoViewService printCustomerInfoView=new PrintCustomerInfoView();
                        CustomerAccountControllerService customerAccountController = new CustomerAccountController(customerAccountModel, customerAccountView,customerMenuView, printCustomerInfoView);
                        customerAccountController.menu();
                    }
                    homePage();
                    break;
                case DEFAULT:
                    System.out.println(" Some problem occurred !!!");
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println("! Please wait while we take you back to the main page !");
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    homePage();
                    break;
            }
        }
        catch(ArrayIndexOutOfBoundsException e) {
            System.out.println(" Some problem occurred !!!");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("! Please wait while we take you back to the main page !");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            homePage();
        }
    }
    public void removedUser(String inputValue) {
        if(inputValue.equals("Customer")) {
            System.out.println("Customer Account has been removed");
        }
        else if (inputValue.equals("Seller")) {
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
