package Controller.ControlShop;

import Model.MobilePhone.*;
import Model.ShoppingCart.ShoppingCartModel;
import View.MobilePhone.PhoneViewService;
import View.MobilePhone.PrintPhoneInfoViewService;

import java.util.HashMap;

public interface ShopControllerService {
    void setShopInfo();
    void addToShop(int productId, int quantity);
    HashMap<Integer, Integer> getProductsFromShop();
    void updateView();
    void setShoppingCartModel(ShoppingCartModel shoppingCartModel);
    boolean displayPhones();
    void displayPhone(int id);
    int addPhone(PhoneDetailsModel phoneDetailsModel, PhoneCameraModel phoneCameraModel, PhoneDimensionsModel phoneDimensionsModel, PhoneManufacturerModel phoneManufacturerModel, PhoneProcessorModel phoneProcessorModel,PhoneStorageCapacityModel phoneStorageCapacityModel, PhoneViewService phoneView, PrintPhoneInfoViewService printPhoneInfoView);
    int getProductIdForPhone();
    boolean searchPhone(String inputValue);
    boolean containsPhone(int id);
    boolean removePhoneWithId(int id);
    boolean removePhoneWithName(String modelName);
    boolean checkStock();
    boolean updateStock(int productId,int count);
    boolean phoneAvailable(int id, int quantity);
    void addToShoppingCart(String userName, int id, int quantity);
    boolean phoneInCartAlready(String userName, int id);
    void updateQuantityInCart(String userName,int id, int count);
    void removePhoneFromCart(String userName,int id);
    boolean displayCart(String userName);
    void calculateBill(int orderedQuantity,int price);
    int getTotalAmount();
    int addToOrders(String userName);
    void removeOrder(int orderId,String userName);
    boolean viewOrdersPlaced(String userName);
    boolean viewAllOrdersPlaced();
    boolean viewPastOrdersPlaced(String userName);
    void dispatchOrder(int orderId);
    void writeProductsFromCart(String userName);
}
