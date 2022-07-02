package FileOperations;

import java.util.HashMap;

public interface ShoppingCartFileHandlerService {
    void writeToCart(String userName, HashMap<Integer,Integer> shoppingCart);
    boolean containsShoppingCart(String userName);
    HashMap<Integer, Integer> getShoppingCart(String userName);
    void removeFromCart(String userName);
}
