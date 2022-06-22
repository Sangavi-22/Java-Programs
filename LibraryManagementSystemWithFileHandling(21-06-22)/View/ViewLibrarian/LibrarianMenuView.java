package View.ViewLibrarian;
import Model.*;
import View.*;
import View.ViewBook.BookView;
import View.ViewBook.BookViewService;
import View.ViewBook.PrintBookInfoView;
import View.ViewBook.PrintBookInfoViewService;
import View.ViewLibrary.LibraryView;
import View.ViewLibrary.LibraryViewService;
import View.ViewLogin.LoginView;
import View.ViewLogin.LoginViewService;
import Controller.ControlBook.BookController;
import Controller.ControlBook.BookControllerService;
import Controller.ControlLibrarian.LibrarianAccountControllerService;
import Controller.ControlLibrary.LibraryController;
import Controller.ControlLibrary.LibraryControllerService;
import Controller.ControlLogin.LoginController;
import Controller.ControlLogin.LoginControllerService;
import Input.InputsFromUser;
public class LibrarianMenuView implements LibrarianMenuViewService {
    private LibrarianAccountControllerService librarianAccountController;
    private final LibraryModel libraryModel=new LibraryModel();
    private final LibraryViewService libraryView=new LibraryView();
    private final LibraryControllerService libraryController=new LibraryController(libraryModel,libraryView);
    private final InputsFromUser inputsFromUser=new InputsFromUser();
    public void setController(LibrarianAccountControllerService librarianAccountController){
        this.librarianAccountController=librarianAccountController;
    }
    public void librarianMenu() {
        try {
            System.out.println();
            System.out.println("*******************************************************");
            System.out.println("!                  WELCOME LIBRARIAN                  !");
            System.out.println("*******************************************************");
            System.out.println("!                Enter 1 to view Books                !");
            System.out.println("!                Enter 2 to add Books                 !");
            System.out.println("!                Enter 3 to remove Books              !");
            System.out.println("!                Enter 4 to search Books              !");
            System.out.println("!                Enter 5 to view Borrowers            !");
            System.out.println("!                Enter 6 to view Profile              !");
            System.out.println("!                Enter 7 to remove Account            !");
            System.out.println("!                Enter 8 to Logout                    !");
            System.out.println("*******************************************************");
            System.out.println();
            int userChoice = inputsFromUser.inputChoice();
            BookControllerService bookController;
            switch (LibrarianActions.values()[userChoice - 1]) {
                case ViewBooks:
                    System.out.println();
                    System.out.println("#######################################################");
                    System.out.println("!                     VIEW BOOKS                      !");
                    System.out.println("#######################################################");
                    libraryController.viewBooks();
                    System.out.println("################### END OF VIEW BOOKS #################");
                    System.out.println();
                    librarianMenu();
                    break;
                case AddBooks:
                    System.out.println();
                    System.out.println("#######################################################");
                    System.out.println("!                     ADD BOOKS                       !");
                    System.out.println("#######################################################");
                    int noOfBooks = inputsFromUser.inputNoOfBooks();
                    while (noOfBooks > 0) {
                        System.out.println("Are you going to add a new book:\n1.Yes\n2.No");
                        int userInput = inputsFromUser.inputChoice();
                        if (userInput == 1) {
                            BookDetailsModel bookDetailsModel = new BookDetailsModel();
                            BookDatesModel bookDateModel = new BookDatesModel();
                            BookPagesModel bookPagesModel = new BookPagesModel();
                            BookAuthorModel bookAuthorModel = new BookAuthorModel();
                            BookViewService bookView = new BookView();
                            PrintBookInfoViewService printBookInfoView=new PrintBookInfoView();
                            bookController = new BookController(bookDetailsModel, bookDateModel, bookPagesModel, bookAuthorModel, bookView,printBookInfoView);
                            bookController.setInputs();
                            libraryController.addBook(bookController.getBookId(), bookController, bookDetailsModel, bookDateModel, bookPagesModel, bookAuthorModel);
                            System.out.println("Book added successfully");
                        }
                        else if(userInput==2) {
                            int id = inputsFromUser.inputBookIdFromUser();
                            if (libraryController.incrementBookCount(id)) {
                                System.out.println("Book added successfully");
                            }
                            else {
                                System.out.println("Sorry!!! The Book does not exist");
                            }
                        }
                        else{
                            System.out.println("Please provide a valid input");
                        }
                        noOfBooks--;
                    }
                    System.out.println("################### END OF ADD BOOKS ##################");
                    System.out.println();
                    librarianMenu();
                    break;
                case RemoveBooks:
                    System.out.println();
                    System.out.println("#######################################################");
                    System.out.println("!                    REMOVE BOOKS                     !");
                    System.out.println("#######################################################");
                    noOfBooks = inputsFromUser.inputNoOfBooks();
                    while (noOfBooks > 0) {
                        int id = inputsFromUser.inputBookIdFromUser();
                        if(libraryController.removeBook(id)) {
                            System.out.println("Book have been removed");
                        }
                        else {
                            System.out.println("Sorry!!! The Book does not exist");
                        }
                        noOfBooks--;
                    }
                    System.out.println("################### END OF REMOVE BOOKS ###############");
                    System.out.println();
                    librarianMenu();
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
                            int id = inputsFromUser.inputBookIdFromUser();
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
                    librarianMenu();
                    break;
                case ViewBorrowers:
                    System.out.println();
                    System.out.println("#######################################################");
                    System.out.println("!           VIEW BOOK BORROWERS DETAILS               !");
                    System.out.println("#######################################################");
                    libraryController.viewBorrowers();
                    System.out.println("########################## END ########################");
                    System.out.println();
                    librarianMenu();
                    break;
                case ViewProfile:
                    librarianAccountController.updateView();
                    librarianMenu();
                    break;
                case RemoveAccount:
                    System.out.println("Enter the username and userId to remove");
                    String userName = inputsFromUser.inputUserName();
                    LoginViewService loginView = new LoginView();
                    LoginModel loginModel = new LoginModel();
                    LoginControllerService loginController = new LoginController(loginModel,loginView);
                    loginController.removeUser(userName);
                    librarianMenu();
                    break;
                case LogOut:
                    loginView = new LoginView();
                    loginModel = new LoginModel();
                    loginController = new LoginController(loginModel, loginView);
                    loginController.logOut();
                    break;
                case Default:
                    System.out.println("Invalid choice");
                    System.out.println("Taking you back to the menu......");
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    System.out.println("!                 Please Wait!!!!!                    !");
                    System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
                    librarianMenu();
                    break;
            }
        }
        catch(ArrayIndexOutOfBoundsException | NumberFormatException exception) {
            System.out.println("Error.Please provide correct input");
            System.out.println("Taking you back to the menu......");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            System.out.println("!                 Please Wait!!!!!                    !");
            System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
            librarianMenu();
        }
    }
}
