package View.ViewLibrary;

public class LibraryView implements LibraryViewService {
    public void printPhoneNum(String phoneNum){
        System.out.println("Please contact the library management if in need of help");
        System.out.println("Library Assistance number is: "+phoneNum);
        System.out.println();
    }
}
