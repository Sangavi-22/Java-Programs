package Model;

public class LibrarianAccountModel extends UserAccountModel {
    private int id;
    public void setId(int id){
        this.id=id;
    }
    public int getId(){
        return this.id;
    }
}
