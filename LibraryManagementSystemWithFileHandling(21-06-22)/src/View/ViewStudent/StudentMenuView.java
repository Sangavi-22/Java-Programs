package View.ViewStudent;
import DataStorage.DataSource;
import DataStorage.DataSourceService;
import Model.LibraryModel;
import Model.LoginModel;
import Payment.Card;
import Payment.GooglePay;
import Payment.PaymentOperation;
import Payment.PaymentType;
import View.SearchingOptions;
import View.ViewLibrary.LibraryView;
import View.ViewLibrary.LibraryViewService;
import View.ViewLogin.LoginView;
import View.ViewLogin.LoginViewService;
import Controller.ControlBook.BookControllerService;
import Controller.ControlLibrary.LibraryController;
import Controller.ControlLibrary.LibraryControllerService;
import Controller.ControlLogin.LoginController;
import Controller.ControlLogin.LoginControllerService;
import Controller.ControlStudent.StudentAccountControllerService;
import Input.InputsFromUser;

import javax.xml.crypto.Data;

public class StudentMenuView implements StudentMenuViewService {
    private long fine=0;
    private StudentAccountControllerService studentAccountController;
    private final LibraryModel libraryModel=new LibraryModel();
    private final LibraryViewService libraryView=new LibraryView();
    private final LibraryControllerService libraryController=new LibraryController(libraryModel,libraryView);
    private final InputsFromUser inputsFromUser=new InputsFromUser();
    private final DataSourceService dataSource= DataSource.getInstance();
    public void setController(StudentAccountControllerService studentAccountController){
        this.studentAccountController=studentAccountController;
    }
    public void studentMenu() {
        try {
            long fine = 0;
            System.out.println();
            System.out.println("*******************************************************");
            System.out.println("!                  WELCOME STUDENT                    !");
            System.out.println("*******************************************************");
            System.out.println("!                Enter 1 to view Books                !");
            System.out.println("!                Enter 2 to borrow Books              !");
            System.out.println("!                Enter 3 to return Books              !");
            System.out.println("!                Enter 4 to renew Books               !");
            System.out.println("!                Enter 5 to search Books              !");
            System.out.println("!                Enter 6 to view Borrowed Books       !");
            System.out.println("!                Enter 7 to pay Fine                  !");
            System.out.println("!                Enter 8 to view Profile              !");
            System.out.println("!                Enter 9 to Logout                    !");
            System.out.println("!             Enter 10 to View Library HelpLine       !");
            System.out.println("*******************************************************");
            System.out.println();
            int userChoice =inputsFromUser.inputChoice();
            BookControllerService bookController;
            switch (StudentActions.values()[userChoice-1]) {
                case ViewBooks:
                    System.out.println();
                    System.out.println("#######################################################");
                    System.out.println("!                     VIEW BOOKS                      !");
                    System.out.println("#######################################################");
                    libraryController.viewBooks();
                    System.out.println("################### END OF VIEW BOOKS #################");
                    System.out.println();
                    studentMenu();
                    break;
                case BorrowBooks:
                    if(dataSource.getBorrowedCount(studentAccountController.getStudentModel())>5) {
                        System.out.println("You have exceeded the maximum limit of books that can be borrowed.");
                    }
                    else {
                        System.out.println();
                        System.out.println("#######################################################");
                        System.out.println("!                   BORROW BOOKS                      !");
                        System.out.println("#######################################################");
                        int id = inputsFromUser.inputBookIdFromUser();
                        boolean found=libraryController.borrowBook(id,studentAccountController.getStudentModel());
                        if(found) {
                            System.out.println("Thank you please return the book on time");
                        }
                        else{
                            System.out.println("Book unavailable. Please try after some time.");
                        }
                        System.out.println("################### END OF BORROW BOOKS ###############");
                        System.out.println();
                    }
                    studentMenu();
                    break;
                case ReturnBooks:
                    System.out.println();
                    System.out.println("#######################################################");
                    System.out.println("!                   RETURN BOOKS                      !");
                    System.out.println("#######################################################");
                    int id=inputsFromUser.inputBookIdFromUser();
                    this.fine=libraryController.returnBook(id)*10;
                    if(this.fine<0) {
                        System.out.println("You have to pay a fine of Rs."+(this.fine*(-1)));
                    }
                    else {
                        System.out.println("Book does not belong to this library");
                    }
                    System.out.println("################### END OF RETURN BOOKS ###############");
                    System.out.println();
                    studentMenu();
                    break;
                case RenewBooks:
                    System.out.println();
                    System.out.println("#######################################################");
                    System.out.println("!                    RENEW BOOKS                      !");
                    System.out.println("#######################################################");
                    id=inputsFromUser.inputBookIdFromUser();
                    boolean renewed=libraryController.renewBook(id);
                    if(renewed==true) {
                        System.out.println("Book Renewed");
                    }
                    else {
                        System.out.println("Sorry!!! Book cannot be renewed. You have to return the book.");
                    }
                    System.out.println("################### END OF RENEW BOOKS ################");
                    System.out.println();
                    studentMenu();
                    break;
                case SearchBooks:
                    System.out.println("1.Search book by id\n2.Search book by name\n3.Search book by author name");
                    int userChoiceForSearching = inputsFromUser.inputChoice();
                    switch (SearchingOptions.values()[userChoiceForSearching - 1]) {
                        case SearchById:
                            System.out.println();
                            System.out.println("#######################################################");
                            System.out.println("!                    SEARCH BOOKS                     !");
                            System.out.println("#######################################################");
                            System.out.println();
                            id = inputsFromUser.inputBookIdFromUser();
                            if (libraryController.searchBookByItsId(id)){
                                System.out.println("End of BookDetails");
                            }
                            else {
                                System.out.println("Sorry!!! The Book does not exist");
                            }
                            System.out.println("################### END OF SEARCH BOOKS ###############");
                            System.out.println();
                            break;
                        case SearchByName:
                            System.out.println();
                            System.out.println("#######################################################");
                            System.out.println("!                    SEARCH BOOKS                     !");
                            System.out.println("#######################################################");
                            System.out.println();
                            String bookName = inputsFromUser.inputBookName();
                            if (libraryController.searchBookByItsName(bookName) > 0) {
                                System.out.println("End of BookDetails");
                            } else {
                                System.out.println("Sorry!!! The Book does not exist");
                            }
                            System.out.println("################### END OF SEARCH BOOKS ###############");
                            System.out.println();
                            break;
                        case SearchByAuthor:
                            System.out.println();
                            System.out.println("#######################################################");
                            System.out.println("!                    SEARCH BOOKS                     !");
                            System.out.println("#######################################################");
                            System.out.println();
                            String authorName = inputsFromUser.inputAuthorName();
                            if (libraryController.searchBookByItsAuthorName(authorName) > 0) {
                                System.out.println("End of BookDetails");
                            } else {
                                System.out.println("Sorry!!! The Book does not exist");
                            }
                            libraryController.searchBookByItsAuthorName(authorName);
                            System.out.println("################### END OF SEARCH BOOKS ###############");
                            break;
                        case Default:
                            System.out.println("Insufficient details to search.");
                            System.out.println("Taking you back to the menu......");
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            System.out.println("!                 Please Wait!!!!!                    !");
                            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                            break;
                    }
                    studentMenu();
                    break;
                case ViewBorrowedBooks:
                    System.out.println();
                    System.out.println("#######################################################");
                    System.out.println("!                  BORROWED BOOKS                     !");
                    System.out.println("#######################################################");
                    System.out.println();
                    if(libraryController.viewBorrowedBooks(studentAccountController.getStudentModel())>0) {
                        System.out.println("You have borrowed the above listed books till now");
                    }
                    else{
                        System.out.println("You haven't borrowed any books");
                    }
                    System.out.println("############## END OF BORROWED BOOKS LIST #############");
                    studentMenu();
                    break;
                case PayFine:
                    if(this.fine<0) {
                        System.out.println("1. Pay using card\n2. Pay using googlePay");
                        PaymentOperation payment;
                        int choice = inputsFromUser.inputChoice();
                        switch (PaymentType.values()[choice-1]) {
                            case Card:
                                String cardNumber=inputsFromUser.inputAccountId();
                                payment = new Card();
                                studentAccountController.payFineThroughCard(payment,cardNumber);
                                this.fine=0;
                                break;
                            case GooglePay:
                                String googlePayId = inputsFromUser.inputGooglePayId();
                                payment= new GooglePay();
                                studentAccountController.payFineThroughGooglePay(payment,googlePayId);
                                this.fine=0;
                                break;
                            case Default:
                                System.out.println("Please pay the fine soon");
                        }
                    }
                    else{
                        System.out.println("No fine due");
                    }
                    studentMenu();
                    break;
                case ViewProfile:
                    studentAccountController.updateView();
                    studentMenu();
                    break;
                case LogOut:
                    LoginViewService loginView=new LoginView();
                    LoginModel loginModel=new LoginModel();
                    LoginControllerService loginController=new LoginController(loginModel,loginView);
                    loginController.logOut();
                    break;
                case ViewHelpLine:
                    libraryController.updateView();
                    studentMenu();
                    break;
                case Default:
                    System.out.println("Invalid choice");
                    System.out.println("Taking you back to the menu......");
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println("!                 Please Wait!!!!!                    !");
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    studentMenu();
                    break;
            }
        }
        catch(ArrayIndexOutOfBoundsException | NumberFormatException exception) {
            System.out.println("Error.Please provide correct input");
            System.out.println("Taking you back to the menu......");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("!                 Please Wait!!!!!                    !");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            studentMenu();
        }
    }
}
