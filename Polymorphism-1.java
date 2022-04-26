//This is an example of dynamic polymorphism which is shown with method overriding concept 
import java.util.Scanner; 
class User{   
    String username,password,name,new_username,new_password; 
    long mobile;
    void display(){ 
       System.out.println("Welcome to ABC Website"); 
    }
} 
class Previoususer extends User{ 
    
    Previoususer(String email, String id){  //constructor
        username=email; 
        password=id; 
    } 
    @Override
    void display(){ 
        System.out.println("Verification completed"); 
    }
} 
class Newuser extends User{ 
    Newuser(String nm,String email,String new_id,long mob){ //constructor
        name=nm; 
        new_username=email; 
        new_password=new_id;  
        mobile=mob;
    }  
    @Override
    void display(){  
        super.display();//super keyword is used to invoke the super class overriden method
        System.out.println("Your account has been created"); 
    }
}  
class Main{ 
    public static void main(String[] args){  
        int choice; 
        long mob; 
        String name,email,new_id;
        Scanner sc=new Scanner(System.in); 
        System.out.println("Please enter your choice:\n1.New User\n2.Previoususer"); 
        choice=sc.nextInt(); 
        switch(choice){ 
            case 1:   
                System.out.println("Enter your name");  
                sc.nextLine();
                name=sc.nextLine(); 
                System.out.println("Enter your email"); 
                email=sc.nextLine();  
                System.out.println("Enter your password. Note:Password should be alphanumeric and alteast 8 characters long");  
                new_id=sc.nextLine(); 
                System.out.println("Enter your mobile number"); 
                mob=Long.parseLong(sc.nextLine()); 
                Newuser nu=new Newuser(name,email,new_id,mob);  
                nu.display(); 
                break;
            case 2: 
                System.out.println("Enter your email");
                email=sc.nextLine(); 
                sc.nextLine();
                System.out.println("Enter your password."); 
                new_id=sc.nextLine(); 
                Previoususer pu=new Previoususer(email,new_id);   
                pu.display();
                break; 
        } 
     } 
}
