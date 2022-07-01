package Controller.ControlCart;

import java.util.HashMap;

public interface ShoppingCartControllerService {
    void addToCart(int productId, int orderedQuantity);
    HashMap<Integer,Integer> getProductsFromCart();
    void printBill(int totalAmount);
}
