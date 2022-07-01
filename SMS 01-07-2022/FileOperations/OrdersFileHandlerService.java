package FileOperations;

import Model.ShoppingCart.ShoppingCartModel;
import java.util.ArrayList;

public interface OrdersFileHandlerService {
    void writeOrders(int orderId,int productId,int quantity);
    void writeOrderedUsers(int orderNum, String userName);
    ArrayList<Integer> readUserOrderId(String userName);
    ShoppingCartModel readProducts(int orderId);
    ArrayList<Integer> readAllOrderFromFile();
}
