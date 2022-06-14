package View.ViewStudent;

public class PrintStudentInfoView implements PrintStudentInfoViewService {
    public void printStudentDetails(String name,String email,long mobileNum,int id){
        System.out.println();
        System.out.println("*******************************************************");
        System.out.println("!                  STUDENT DETAILS                    !");
        System.out.println("*******************************************************");
        System.out.println("Name: " +name);
        System.out.println("Email: " +email);
        System.out.println("Mobile: " +mobileNum);
        System.out.println("Student Id: " +id);
        System.out.println("*******************************************************");
        System.out.println();
    }
}
