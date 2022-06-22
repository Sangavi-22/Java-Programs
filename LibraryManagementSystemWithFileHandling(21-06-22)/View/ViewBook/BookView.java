package View.ViewBook;
import Controller.ControlBook.BookControllerService;
import Input.InputsFromUser;
public class BookView implements BookViewService {
    private String bookName,publicationName,pageLayout;
    private int bookCost,noOfPages,userInput;
    private final InputsFromUser inputsFromUser=new InputsFromUser();
    public void inputBookDetails(BookControllerService bookController) {
        bookName = inputsFromUser.inputBookName();
        publicationName = inputsFromUser.inputPublisherName();
        bookCost = inputsFromUser.inputBookCost();
        noOfPages = inputsFromUser.inputNoOfPages();
        System.out.println("Enter the Page Layout: 1.Landscape 2.Portrait");
        userInput = inputsFromUser.inputChoice();
        while(userInput<1 && userInput>2) {
            System.out.println("Please provide a valid input");
            System.out.println("Enter the Page Layout: 1.Landscape 2.Portrait");
            userInput = inputsFromUser.inputChoice();
        }
        if(userInput==1){
            pageLayout = "Landscape";
        }
        else{
            pageLayout="Portrait";
        }
        int noOfAuthors=inputsFromUser.inputNoOfAuthors();
        while(noOfAuthors-->0){
            String authorName=inputsFromUser.inputAuthorName();
            bookController.addAuthorName(authorName);
        }
        passDetailsToController(bookController);
    }
    public void passDetailsToController(BookControllerService bookController){
        bookController.setBookName(bookName);
        bookController.setPublicationName(publicationName);
        bookController.setBookCost(bookCost);
        bookController.setBookCount();
        bookController.setNoOfPages(noOfPages);
        bookController.setPageLayout(pageLayout);
        bookController.setBookIssueDate();
        bookController.setBookDueDate();
        bookController.setBookReturnedDateForReturn();
    }
}
