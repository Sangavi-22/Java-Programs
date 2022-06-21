package Model;
import java.time.LocalDate;
public class BookDatesModel {
    private LocalDate issueDate;
    private LocalDate dueDate;
    private LocalDate returnedDate;
    public void setBookIssueDate(){
        this.issueDate=LocalDate.now();
    }
    public LocalDate getIssueDate(){
        return this.issueDate;
    }
    public void setBookDueDate(){
        this.dueDate=LocalDate.now().plusDays(20);
    }
    public LocalDate getBookDueDate(){
        return this.dueDate;
    }
    public void setBookReturnedDateForReturn() {
        this.returnedDate =LocalDate.now().plusDays(25);
    }
    public void setBookReturnedDateForRenew(){
        this.returnedDate=LocalDate.now().plusDays(10);
    }
    public LocalDate getBookReturnedDate(){
        return this.returnedDate;
    }
}
