package DataStorage;
import Model.*;
import java.util.HashMap;
public interface DataSourceService {
    void addBookDetails(int id, BookDetailsModel bookDetailsModel);
    void removeBookDetails(int id);
    void addBookDate(int id, BookDatesModel bookDatesModel);
    void removeBookDate(int id);
    void addBookAuthors(int id,BookAuthorModel bookAuthorModel);
    void removeBookAuthors(int id);
    void addBookPages(int id, BookPagesModel bookPagesModel);
    void removeBookPages(int id);
    void addBorrower(int id, StudentAccountModel studentAccountModel);
    void removeBorrower(int id);
    HashMap<Integer, StudentAccountModel> getBorrowers();
    void addStudent(String userName,StudentAccountModel studentAccountModel);
    void addLibrarian(String userName, LibrarianAccountModel librarianAccountModel);
    void removeStudent(String userName);
    void removeLibrarian(String userName);
    boolean containsStudent(String userName);
    boolean containsLibrarian(String userName);
    LibrarianAccountModel getLibrarian(String userName);
    StudentAccountModel getStudent(String userName);
}
