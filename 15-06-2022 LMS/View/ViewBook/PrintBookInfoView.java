package View.ViewBook;
import java.time.LocalDate;
import java.util.ArrayList;

public class PrintBookInfoView implements PrintBookInfoViewService{
    public void printBookDetails(int bookId, String bookName, String publisher, int bookCost, int pagesCount, String layout, ArrayList<String> authorsName, int bookLocation){
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
    public void printDates(LocalDate issueDate, LocalDate dueDate, int bookId){
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
