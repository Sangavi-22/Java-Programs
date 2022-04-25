import java.util.*; //importing built-in package
abstract class Student{  
    String Name, Department, Section; 
    char Attendance; 
    int Total=0;
    private int dep_id= 1;
    /* The above variable is accessible only in this class and 
    is an example of data hiding using modifiers*/
    int Reg_num; 
    void getdata(String n, int roll_no){ 
        Name=n; 
        Reg_num=roll_no;
        /* These two methods exhibit method overloading and 
        is an example for polymorphism*/ 
    } 
    void getdata(String dep, String sec, char Atd){  
        Department=dep; 
        Section=sec;   
        Attendance=Atd; 
    }    
    void sample(){ 
        System.out.println("Please verify the students details, move to the next step");//Method overriding
    }
    abstract void display();
    /* It is an Abstract Method whose declaration is in Super Class and 
    definition is in Sub class*/
} 
class Marks extends Student{ 
    void getMarks(int Marks[]){ 
        for(int i: Marks){ 
            Total+=i; 
        }
    }   
    void sample(){  
        super.sample();
        System.out.println("Loading Students Marks\nEnter the marks: "); 
    } 
    void display(){ 
        System.out.println("The Total Marks of the student is: "+Total); 
    }
}  
class Attendance extends Student{  
    //extends keyword denotes inheritance
    void display(){   
        System.out.println("*****************Displaying Student Details****************");  
        System.out.println("Name: "+ Name); 
        System.out.println("Register Number: "+Reg_num); 
        System.out.println("Department: "+Department+" Sec: "+Section); 
        if(Attendance=='P'){ 
            System.out.println(Name+" is Present"); 
        } 
        else{ 
            System.out.println(Name+" is Absent"); 
        } 
        System.out.println("***********************************************************");
    } 
    //The above method define the abstract method declared in the super class
}
public class Main
{
	public static void main(String[] args) {  
	    int Register_Num,N; 
	    String Name, Department, Sec;  
	    char Attendance;
	    Scanner sc=new Scanner(System.in);//Used to get the input from the user
	    Attendance obj1=new Attendance(); 
	    Marks obj2=new Marks();
	    System.out.println("**************Student Database**************");
	    System.out.println("Enter the following details :\n 1.Name\n 2.Register Number\n 3.Department\n 4.Sec\n 5.Attendance as 'A' or 'P'");
	    Name=sc.next(); 
	    Register_Num=sc.nextInt(); 
	    Department=sc.next(); 
	    Sec=sc.next(); 
	    Attendance=sc.next().charAt(0);
		obj1.getdata(Name, Register_Num); 
		obj1.getdata(Department,Sec,Attendance);
		obj1.display();
		obj2.sample();
		N=sc.nextInt();
		int[] Marks=new int[N];
		for(int i=0;i<N;i++){ 
		    Marks[i]=sc.nextInt(); 
		}
		obj2.getMarks(Marks);
		obj2.display(); 
	}
}
