package DataStorage;
import Model.*;
import java.util.HashMap;
public class DataSource implements DataSourceService{
    private static DataSource dataSource=null;
    private HashMap<Integer, BookDetailsModel>bookDetails=new HashMap<>();
    private HashMap<Integer,BookDatesModel>bookDates=new HashMap<>();
    private HashMap<Integer, BookPagesModel>bookPages=new HashMap<>();
    private HashMap<Integer, BookAuthorModel>bookAuthors=new HashMap<>();
    private HashMap<Integer,StudentAccountModel>borrowerDetails=new HashMap<>();
    private HashMap<String, LibrarianAccountModel>librarianDetails=new HashMap<>();
    private HashMap<String, StudentAccountModel>studentDetails=new HashMap<>();
    private DataSource(){
        //Constructor of DataStorage.DataSource class
    }
    public static DataSource getInstance(){
        if( dataSource==null) {
            dataSource =new DataSource();
        }
        return dataSource;
    }
    public void addBookDetails(int id,BookDetailsModel bookDetailsModel){
        bookDetails.put(id,bookDetailsModel);
    }
    public void removeBookDetails(int id){
        bookDetails.remove(id);
    }
    public void addBookDate(int id, BookDatesModel bookDatesModel){
        bookDates.put(id,bookDatesModel);
    }
    public void removeBookDate(int id){
        bookDates.remove(id);
    }
    public void addBookAuthors(int id,BookAuthorModel bookAuthorModel){
        bookAuthors.put(id,bookAuthorModel);
    }
    public void removeBookAuthors(int id){
        bookAuthors.remove(id);
    }
    public void addBookPages(int id,BookPagesModel bookPagesModel){
        bookPages.put(id,bookPagesModel);
    }
    public void removeBookPages(int id){
        bookPages.remove(id);
    }
    public void addBorrower(int id,StudentAccountModel studentAccountModel){
        borrowerDetails.put(id, studentAccountModel);
    }
    public void removeBorrower(int id){
        borrowerDetails.remove(id);
    }

    public HashMap<Integer, StudentAccountModel> getBorrowers() {
        return borrowerDetails;
    }
    public void addStudent(String userName,StudentAccountModel studentAccountModel){
        studentDetails.put(userName,studentAccountModel);
    }
    public void addLibrarian(String userName,LibrarianAccountModel librarianAccountModel){
        librarianDetails.put(userName,librarianAccountModel);
    }
    public void removeStudent(String userName){
        studentDetails.remove(userName);
    }
    public void removeLibrarian(String userName){
        librarianDetails.remove(userName);
    }
    public boolean containsStudent(String userName){
        return studentDetails.containsKey(userName);
    }
    public boolean containsLibrarian(String userName){
        return librarianDetails.containsKey(userName);
    }
    public LibrarianAccountModel getLibrarian(String userName){
        return librarianDetails.get(userName);
    }
    public StudentAccountModel getStudent(String userName){
        return studentDetails.get(userName);
    }

}
