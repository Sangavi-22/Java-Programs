package View.ViewLogin;
import Administator.AdminService;
import Controller.ControlLibrarian.LibrarianAccountController;
import Controller.ControlLibrarian.LibrarianAccountControllerService;
import Controller.ControlLibrarian.LibrarianAccountUtility;
import Controller.ControlLibrary.LibraryController;
import Controller.ControlLibrary.LibraryControllerService;
import Controller.ControlStudent.StudentAccountController;
import Controller.ControlStudent.StudentAccountControllerService;
import Controller.ControlStudent.StudentAccountUtility;
import DataStorage.DataSource;
import DataStorage.DataSourceService;
import Model.*;
import Administator.Admin;
import Input.InputsFromUser;
import View.ViewLibrarian.*;
import View.ViewLibrary.LibraryView;
import View.ViewLibrary.LibraryViewService;
import View.ViewStudent.*;

public class LoginView implements LoginViewService {
    private static String accountName;
    private final InputsFromUser inputsFromUser = new InputsFromUser();
    private final static DataSourceService dataSource= DataSource.getInstance();
    private final LibrarianAccountUtility librarianUtil=LibrarianAccountUtility.getInstance();
    private final StudentAccountUtility studentUtil=StudentAccountUtility.getInstance();
    private final LibraryModel libraryModel=new LibraryModel();
    private final LibraryViewService libraryView=new LibraryView();
    private final LibraryControllerService libraryController=new LibraryController(libraryModel,libraryView);
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
                    System.out.println("Creating new Account");
                    AdminService admin=new Admin();
                    int userId = admin.generateId();
                    if(userId==0) {
                        System.out.println("Invalid inputs");
                        homePage();
                        break;
                    }
                    else{
                        System.out.println("Processing.Please Wait!");
                    }
                    System.out.println();
                    System.out.println("*******************************************************");
                    System.out.println("!              USER ID GENERATED                      !");
                    System.out.println("*******************************************************");
                    System.out.println("Your id is: " + userId);
                    System.out.println("*******************************************************");
                    System.out.println();

                    userName = inputsFromUser.inputUserName();
                    while(dataSource.containsLibrarian(userName)|| dataSource.containsStudent(userName)) {
                        System.out.println("User Already Exist. Reenter your details");
                        userName = inputsFromUser.inputUserName();
                    }
                    System.out.println("Note: Your password should contain uppercase,lowercase letters,digits and should be of length 8");
                    String password = inputsFromUser.inputPassword();
                    if (userId >= 1 && userId <= 1000) {
                        System.out.println("Your account has been created successfully");
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
                        StudentAccountModel studentAccountModel = new StudentAccountModel();
                        StudentAccountViewService studentAccountView = new StudentAccountView();
                        StudentMenuViewService studentMenuView=new StudentMenuView();
                        PrintStudentInfoViewService printStudentInfoView=new PrintStudentInfoView();
                        StudentAccountControllerService studentAccountController = new StudentAccountController(studentAccountModel, studentAccountView,studentMenuView, printStudentInfoView);
                        studentAccountController.setInputs(userName,password,userId);
                        studentAccountController.menu();
                    }
                    else{
                        System.out.println("Invalid id");
                    }
                    homePage();
                    break;
                case Login:
                    System.out.println("Please enter your details to login to your account");
                    userName = inputsFromUser.inputUserName();
                    password = inputsFromUser.inputPassword();
                    LoginView.accountName = userName;
                    if(dataSource.containsLibrarian(userName) && librarianUtil.getLibrarianController(dataSource.getLibrarian(userName)).getPassword().equals(password)) {
                        LibrarianAccountControllerService librarianAccountController=librarianUtil.getLibrarianController(dataSource.getLibrarian(userName));
                        librarianAccountController.menu();
                    }
                    else if(dataSource.containsStudent(userName)&& studentUtil.getStudentController(dataSource.getStudent(userName)).getPassword().equals(password)) {
                        StudentAccountControllerService studentAccountController =studentUtil.getStudentController(dataSource.getStudent(userName));
                        studentAccountController.menu();
                    }
                    else {
                        System.out.println("Invalid user");
                    }
                    homePage();
                    break;
                case Default:
                    System.out.println("Invalid choice");
                    homePage();
                    break;
            }
        }
        catch(ArrayIndexOutOfBoundsException exception) {
            System.out.println("Error.Please provide correct input");
            homePage();
        }

    }
    public void removedUser(String user) {
        if(user.equals("No User")) {
            System.out.println("User not found");
        }
        else if (user.equals(LoginView.accountName)) {
            System.out.println("Your Account have been removed");
            System.exit(0);
        }
        else {
            System.out.println("Account Removed");
        }
    }
    public void loggedOut(){
        System.out.println("Thank you for using our library. You have been Logged out successfully");
        homePage();
    }
}