package FileOperations;

import Model.Seller.SellerAccountModel;
import java.util.HashMap;

public interface SellerDetailsFileHandlerService {
    void writeSellerDetails(int userId,String userName,String password,String name,String email,long mobileNum);
    HashMap<String, SellerAccountModel> readSellers();
    int readLastSellerId();
    void removeSellerDetailsFromFile(String userName);


}
