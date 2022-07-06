package View.Shop;

public interface ShopViewService {
    boolean confirmAddProduct();
    boolean confirmDeleteProduct();
    void printOrders(int orderId);
    void printUserName(String userName);

}
