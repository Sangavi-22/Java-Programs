import java.util.Scanner; 
class Member{ 
    String memberName,departmentName,memberRole,qualification,designationName,majorSubject,rollNumber;
    int idNum,standard;  
    void setMemberData(String name,String department, String role,int id){  
        memberName=name; 
        departmentName=department; 
        memberRole=role;
        idNum=id;  
    }
    void displayMemberData(){ 
        System.out.println("Please verify all the details: "); 
        System.out.println("Displaying Members details: ");  
        System.out.println("Name: "+memberName);
        System.out.println("Department: "+departmentName); 
        System.out.println("Role: "+memberRole); 
        System.out.println("Id: "+idNum);
        if (memberRole.equals("Teacher")){ 
            System.out.println("Qualification: "+qualification); 
            System.out.println("Designation: "+designationName); 
        } 
        else{ 
            System.out.println("Major Subject: "+majorSubject); 
            System.out.println("Roll Number: "+rollNumber); 
            System.out.println("Standard: "+standard); 
        } 
    }
}
class Teacher extends Member{ 
    void setTeacherData(String degree, String designation){ 
        qualification=degree; 
        designationName=designation; 
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
        String name,department,role,qualification,designation,subject,rollNo,userResponse; 
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
            teacher.displayMemberData(); 
        } 
        else{
            student.setMemberData(name,department,role,id);
            System.out.println("Welcome. Please enter the following details to proceed further"); 
            System.out.println("Enter your major subject, roll number, standard");
            subject=sc.nextLine(); 
            rollNo=sc.nextLine();
            standardStudying=sc.nextInt(); 
            student.setStudentData(subject,rollNo,standardStudying); 
            student.displayMemberData(); 
        } 
        System.out.println("Acknowledge that you have verified the details by typing Yes"); 
        userResponse=sc.nextLine(); 
        if(userResponse.equals("Yes")){ 
            System.out.println("Thankyou"); 
        } 
        
    } 
}
