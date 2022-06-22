package Model;
public class UserAccountModel {
    private String name;
    private String email;
    private long mobileNum;
    private String userName;
    private String password;
    private int id;
    public void setName(String name){
        this.name=name;
    }
    public String getName(){
        return this.name;
    }
    public void setEmail(String email){
        this.email=email;
    }
    public String getEmail(){
        return this.email;
    }
    public void setMobileNum(long mobileNum){
        this.mobileNum=mobileNum;
    }
    public long getMobileNum(){
        return this.mobileNum;
    }
    public void setUserName(String userName){
        this.userName=userName;
    }
    public String getUserName(){
        return this.userName;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public String getPassword(){
        return this.password;
    }
    public void setId(int id){
        this.id=id;
    }
    public int getId(){
        return this.id;
    }
}
