package Model.Seller;

import Model.UserAccountModel;

public class SellerAccountModel extends UserAccountModel {
    private int id;
    public void setUserId(int id){
        this.id=id;
    }
    public int getUserId(){
        return this.id;
    }
}
