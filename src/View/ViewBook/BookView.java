package View.ViewBook;
import Controller.ControlBook.BookControllerService;
import Input.InputsFromUser;
import java.time.LocalDate;
import java.util.ArrayList;

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
        if(userInput==1){
            pageLayout = "Landscape";
        }
        else{
            pageLayout="Potrait";
        }
        int noOfAuthors=inputsFromUser.inputNoOfAuthors();
        while(noOfAuthors-->0){
            String authorName=inputsFromUser.inputAuthorName();
            bookController.addAuthorName(authorName);
        }
        passDetailsToController(bookController);
    }
    public void passDetailsToController(BookControllerService bookController){
        bookController.setBookId();
        bookController.setBookLocationId();
        bookController.setBookName(bookName);
        bookController.setPublicationName(publicationName);
        bookController.setBookCost(bookCost);
        bookController.setBookCount();
        bookController.setNoOfPages(noOfPages);
        bookController.setPageLayout(pageLayout);
    }
    public void printBookDetails(int bookId, String bookName, String publisher, int bookCost, int pagesCount, String layout, ArrayList<String>authorsName,int bookLocation){
        System.out.println();
        System.out.println("*******************************************************");
        System.out.println("!                    BOOK DETAILS                     !");
        System.out.println("*******************************************************");
        System.out.println("Book id: "+bookId);
        System.out.println("Book Name: "+bookName);
        System.out.println("Publisher Name: "+publisher);
        System.out.println("Book Cost: "+bookCost);
        System.out.println("No Of Pages: "+pagesCount);
        System.out.println("Page Layout: "+layout);
        System.out.println("Authors: ");
        int iterator=0;
        for(String authorName:authorsName) {
            iterator=iterator+1;
            System.out.println((iterator)+". "+authorName);
        }
        System.out.println("Book placed at: "+bookLocation);
        System.out.println("*******************************************************");
        System.out.println();
    }
    public void printDates(LocalDate issueDate,LocalDate dueDate,int bookId){
        System.out.println();
        System.out.println("*******************************************************");
        System.out.println("!                  ISSUE AND DUE DATE                 !");
        System.out.println("*******************************************************");
        System.out.println("The book with id "+bookId+" is issued on "+issueDate);
        System.out.println("Please return it by "+dueDate);
        System.out.println("*******************************************************");
        System.out.println();
    }
    public void printBookId(int id){
        System.out.println();
        System.out.println("######## Borrowed Book Id: "+id+" ########");
        System.out.println("Book Id: "+id);
        System.out.println();
    }
}
