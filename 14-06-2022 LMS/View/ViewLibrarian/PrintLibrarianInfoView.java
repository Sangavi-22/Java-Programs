package View.ViewLibrarian;

public class PrintLibrarianInfoView implements PrintLibrarianInfoViewService {
    public void printLibrarianDetails(String name,String email,long mobileNum,int id){
        System.out.println();
        System.out.println("*******************************************************");
        System.out.println("!                  LIBRARIAN DETAILS                  !");
        System.out.println("*******************************************************");
        System.out.println("Name: "+name);
        System.out.println("Email: "+email);
        System.out.println("Mobile: "+mobileNum);
        System.out.println("Librarian Id: "+id);
        System.out.println("*******************************************************");
        System.out.println();
    }
}
