import java.util.Scanner; 
class Members{ 
    String name,department,role,qualification,designation,major_subject,roll_no;
    int id,standard;  
    void getdata(String member_name,String dep, String job,int id_no){  
        name=member_name; 
        department=dep; 
        role=job;
        id=id_no;  
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
            System.out.println("Major Subject: "+major_subject); 
            System.out.println("Roll Number: "+roll_no); 
            System.out.println("Standard: "+standard); 
        } 
    }
}
class Teacher extends Members{ 
    void getdata1(String degree, String des){ 
        qualification=degree; 
        designation=des; 
    } 
} 
class Students extends Members{ 
    void getdata2(String subject, String r_no,int stnd){  
        major_subject=subject; 
        roll_no=r_no;   
        standard=stnd;
        
    } 
} 
class Main{ 
    public static void main(String[] args){ 
        String name,department,role,qualification,designation,subject,roll_no,choice; 
        int id,stnd;
        Teacher teach=new Teacher(); 
        Students std=new Students();  
        Scanner sc=new Scanner(System.in); 
        System.out.println("Members Details"); 
        System.out.println("Enter the following details:\n1.Name\n2.Department\n3.role\n4.id"); 
        name=sc.nextLine();
        department=sc.nextLine(); 
        role=sc.nextLine(); 
        id=sc.nextInt();
        sc.nextLine();
        if (role.equals("Teacher")){ 
            teach.getdata(name,department,role,id); 
            System.out.println("Hello Professor! Greetings. Please enter the following details to proceed further"); 
            System.out.println("Enter your qualification, designation");
            qualification=sc.nextLine(); 
            designation=sc.nextLine(); 
            teach.getdata1(qualification,designation); 
            teach.display(); 
        } 
        else{
            std.getdata(name,department,role,id);
            System.out.println("Welcome. Please enter the following details to proceed further"); 
            System.out.println("Enter your major subject, roll number, standard");
            subject=sc.nextLine(); 
            roll_no=sc.nextLine();
            stnd=sc.nextInt(); 
            std.getdata2(subject,roll_no,stnd); 
            std.display(); 
        } 
        System.out.println("Acknowledge that you have verified the details by typing Yes"); 
        choice=sc.nextLine(); 
        if(choice.equals("Yes")){ 
            System.out.println("Thankyou"); 
        } 
        
    } 
    }