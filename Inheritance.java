import java.util.Scanner; 
class Member{ 
    String name,department,role,qualification,designation,majorSubject,rollNumber;
    int id,standard;  
    void setMemberData(String memberName,String departmentName, String memberRole,int idNum){  
        name=memberName; 
        department=departmentName; 
        role=memberRole;
        id=idNum;  
    }
    void display(){ 
        System.out.println("Please verify all the details: "); 
        System.out.println("Displaying Members details: ");  
        System.out.println("Name: "+name);
        System.out.println("Department: "+department); 
        System.out.println("Role: "+role); 
        System.out.println("Id: "+id);
        if (role.equals("Teacher")){ 
            System.out.println("Qualification: "+qualification); 
            System.out.println("Designation: "+designation); 
        } 
        else{ 
            System.out.println("Major Subject: "+majorSubject); 
            System.out.println("Roll Number: "+rollNumber); 
            System.out.println("Standard: "+standard); 
        } 
    }
}
class Teacher extends Member{ 
    void setTeacherData(String degree, String designationName){ 
        qualification=degree; 
        designation=designationName; 
    } 
} 
class Student extends Member{ 
    void setStudentData(String subject, String rollNo,int standardStudying){  
        majorSubject=subject; 
        rollNumber=rollNo;   
        standard=standardStudying;
        
    } 
} 
class Main{ 
    public static void main(String[] args){ 
        String name,department,role,qualification,designation,subject,rollNo,choice; 
        int id,standardStudying;
        Teacher teacher=new Teacher(); 
        Student student=new Student();  
        Scanner sc=new Scanner(System.in); 
        System.out.println("Members Details"); 
        System.out.println("Enter the following details:\n1.Name\n2.Department\n3.role\n4.id"); 
        name=sc.nextLine();
        department=sc.nextLine(); 
        role=sc.nextLine(); 
        id=sc.nextInt();
        sc.nextLine();
        if (role.equals("Teacher")){ 
            teacher.setMemberData(name,department,role,id); 
            System.out.println("Hello Professor! Greetings. Please enter the following details to proceed further"); 
            System.out.println("Enter your qualification, designation");
            qualification=sc.nextLine(); 
            designation=sc.nextLine(); 
            teacher.setTeacherData(qualification,designation); 
            teacher.display(); 
        } 
        else{
            student.setMemberData(name,department,role,id);
            System.out.println("Welcome. Please enter the following details to proceed further"); 
            System.out.println("Enter your major subject, roll number, standard");
            subject=sc.nextLine(); 
            rollNo=sc.nextLine();
            standardStudying=sc.nextInt(); 
            student.setStudentData(subject,rollNo,standardStudying); 
            student.display(); 
        } 
        System.out.println("Acknowledge that you have verified the details by typing Yes"); 
        choice=sc.nextLine(); 
        if(choice.equals("Yes")){ 
            System.out.println("Thankyou"); 
        } 
        
    } 
}
