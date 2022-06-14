package Model;

public class StudentAccountModel extends UserAccountModel {
    private int id;
    private int borrowedCount=0;
    public void setId(int id){
        this.id=id;
    }
    public int  getId(){
        return this.id;
    }
    public void setBorrowedCount(){
        this.borrowedCount=this.borrowedCount+1;
    }
    public int getBorrowedCount(){
        return this.borrowedCount;
    }
    public void updateBorrowedCount(){
        this.borrowedCount=this.borrowedCount-1;
    }
}
