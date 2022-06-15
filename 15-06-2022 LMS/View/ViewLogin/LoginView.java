package View.ViewLogin;
import Controller.ControlLogin.LoginUtility;
import Model.*;
import Administator.Admin;
import Input.InputsFromUser;
import View.ViewLibrarian.*;
import View.ViewLibrary.LibraryView;
import View.ViewLibrary.LibraryViewService;
import View.ViewStudent.*;
import Administator.AdminService;
import Controller.ControlLibrarian.LibrarianAccountController;
import Controller.ControlLibrarian.LibrarianAccountControllerService;
import Controller.ControlLibrary.LibraryController;
import Controller.ControlLibrary.LibraryControllerService;
import Controller.ControlLogin.LoginControllerService;
import Controller.ControlStudent.StudentAccountController;
import Controller.ControlStudent.StudentAccountControllerService;
public class LoginView implements LoginViewService {
    private  LoginControllerService loginController;
    private final InputsFromUser inputsFromUser = new InputsFromUser();
    private final LibraryModel libraryModel=new LibraryModel();
    private final LibraryViewService libraryView=new LibraryView();
    private final LoginUtility loginUtil=new LoginUtility();
    private final LibraryControllerService libraryController=new LibraryController(libraryModel,libraryView);
    public void setLoginController(LoginControllerService loginController) {
        this.loginController = loginController;
    }
    public void homePage() {
        libraryController.setPhoneNum();
        try{
            String userName;
            System.out.println();
            System.out.println("*******************************************************");
            System.out.println("!        WELCOME TO LIBRARY MANAGEMENT SYSTEM         !");
            System.out.println("*******************************************************");
            System.out.println("!               Enter 1 to Signup                     !");
            System.out.println("!               Enter 2 to Login                      !");
            System.out.println("*******************************************************");
            System.out.println();
            int userChoice = inputsFromUser.inputChoice();
            switch (AccountActions.values()[userChoice-1]) {
                case Signup:
                    System.out.println("Please enter your details to create a new account.");
                    AdminService admin=new Admin();
                    int userId = admin.generateId();
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
                    System.out.println("Your id is: " + userId);
                    System.out.println("*******************************************************");
                    System.out.println();

                    userName = inputsFromUser.inputUserName();
                    while(loginUtil.containsLibrarianAccount(userName)|| loginUtil.containsStudentAccount(userName)) {
                        System.out.println("User Already Exist. Please reenter your details");
                        userName = inputsFromUser.inputUserName();
                    }
                    System.out.println("Note: Your password should contain uppercase,lowercase letters,digits and should be of length 8");
                    String password = inputsFromUser.inputPassword();
                    if (userId >= 1 && userId <= 1000) {
                        System.out.println("Your account has been created successfully");
                        //loginController.setAccountName(userName);
                        LibrarianAccountModel librarianAccountModel = new LibrarianAccountModel();
                        LibrarianAccountViewService librarianAccountView = new LibrarianAccountView();
                        LibrarianMenuViewService librarianMenuView=new LibrarianMenuView();
                        PrintLibrarianInfoViewService printLibrarianInfoView=new PrintLibrarianInfoView();
                        LibrarianAccountControllerService librarianAccountController = new LibrarianAccountController(librarianAccountModel, librarianAccountView,librarianMenuView,printLibrarianInfoView);
                        librarianAccountController.setInputs(userName,password,userId);
                        librarianAccountController.menu();
                    }
                    else if (userId >= 1001 && userId <= 2000) {
                        System.out.println("Your account has been created successfully");
                        //loginController.setAccountName(userName);
                        StudentAccountModel studentAccountModel = new StudentAccountModel();
                        StudentAccountViewService studentAccountView = new StudentAccountView();
                        StudentMenuViewService studentMenuView=new StudentMenuView();
                        PrintStudentInfoViewService printStudentInfoView=new PrintStudentInfoView();
                        StudentAccountControllerService studentAccountController = new StudentAccountController(studentAccountModel, studentAccountView,studentMenuView, printStudentInfoView);
                        studentAccountController.setInputs(userName,password,userId);
                        studentAccountController.menu();
                    }
                    else{
                        System.out.println("Invalid id. Taking you back to the main page");
                        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println("!                 Please Wait!!!!!                    !");
                        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    }
                    homePage();
                    break;
                case Login:
                    System.out.println("Please enter your details to login to your account");
                    userName = inputsFromUser.inputUserName();
                    password = inputsFromUser.inputPassword();
                    //loginController.setAccountName(userName);
                    if(loginUtil.containsLibrarianAccount(userName) && loginUtil.getLibrarianAccountController(loginUtil.getLibrarianModel(userName)).getPassword().equals(password)) {
                        LibrarianAccountControllerService librarianAccountController=loginUtil.getLibrarianAccountController(loginUtil.getLibrarianModel(userName));
                        librarianAccountController.menu();
                    }
                    else if(loginUtil.containsStudentAccount(userName)&& loginUtil.getStudentAccountController(loginUtil.getStudentModel(userName)).getPassword().equals(password)) {
                        StudentAccountControllerService studentAccountController =loginUtil.getStudentAccountController(loginUtil.getStudentModel(userName));
                        studentAccountController.menu();
                    }
                    else if(loginUtil.containsLibrarianAccount(userName) && (!loginUtil.getLibrarianAccountController(loginUtil.getLibrarianModel(userName)).getPassword().equals(password))) {
                        System.out.println("Do you want to change your password\n1.Yes\n2.No");
                        int userInput = inputsFromUser.inputChoice();
                        switch (userInput){
                            case 1:
                                System.out.println("Please enter your userId");
                                userId=inputsFromUser.inputUserId();
                                if(loginUtil.getLibrarianAccountController(loginUtil.getLibrarianModel(userName)).getLibrarianId()==userId) {
                                    password = inputsFromUser.inputPassword();
                                    loginUtil.getLibrarianAccountController(loginUtil.getLibrarianModel(userName)).setPassword(password);
                                }
                                else{
                                  System.out.println("The password for this account cannot be changed");
                                }
                                break;
                            case 2:
                                System.out.println("Taking you back to the main page......");
                                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                                System.out.println("!                 Please Wait!!!!!                    !");
                                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                                break;
                        }
                    }
                    else if(loginUtil.containsStudentAccount(userName) && (!loginUtil.getStudentAccountController(loginUtil.getStudentModel(userName)).getPassword().equals(password))) {
                        System.out.println("Do you want to change your password\n1.Yes\n2.No");
                        int userInput = inputsFromUser.inputChoice();
                        switch (userInput){
                            case 1:
                                System.out.println("Please enter your userId");
                                userId=inputsFromUser.inputUserId();
                                if(loginUtil.getStudentAccountController(loginUtil.getStudentModel(userName)).getStudentId()==userId) {
                                    password = inputsFromUser.inputPassword();
                                    loginUtil.getStudentAccountController(loginUtil.getStudentModel(userName)).setPassword(password);
                                }
                                else{
                                    System.out.println("The password for this account cannot be changed");
                                }
                                break;
                            case 2:
                                System.out.println("Taking you back to the main page......");
                                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                                System.out.println("!                 Please Wait!!!!!                    !");
                                System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                                break;
                        }
                    }
                    else
                    {
                        System.out.println("Sorry! User Account does not exist");
                        System.out.println("Taking you back to the main page......");
                        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                        System.out.println("!                 Please Wait!!!!!                    !");
                        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    }
                    homePage();
                    break;

                case Default:
                    System.out.println("Invalid choice. Taking you back to the main page");
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println("!                 Please Wait!!!!!                    !");
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    homePage();
                    break;
            }
        }
        catch(ArrayIndexOutOfBoundsException | NumberFormatException exception) {
            System.out.println("Error.Please provide the input again");
            System.out.println("Taking you back to the main page......");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("!                 Please Wait!!!!!                    !");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            homePage();
        }
    }
    public void removedUser(String user) {
        if(user.equals("Student")) {
            System.out.println("Student Account has been removed");
        }
        else if (user.equals("Librarian")) {
            System.out.println("Your Account has been removed");
            homePage();
            //Librarian can only remove either his account or a student's account
        }
        else {
            System.out.println("Sorry! User Account does not exist");
        }
    }
    public void loggedOut(){
        System.out.println("Thank you for using our library. You have been Logged out successfully");
        homePage();
    }
}