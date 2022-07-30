package DataStorage;

import Model.Customer.CustomerAccountModel;
import Model.MobilePhone.*;
import Model.Seller.SellerAccountModel;
import Model.ShoppingCart.ShoppingCartModel;
import java.util.ArrayList;
import java.util.HashMap;

public interface DataSourceService {
    void addSeller(SellerAccountModel sellerAccountModel);
    HashMap<String, SellerAccountModel> getSellers();
    boolean containsSeller(String userName);
    int getIdForSeller();
    void addCustomer(CustomerAccountModel customerAccountModel);
    HashMap<String, CustomerAccountModel> getCustomers();
    boolean containsCustomer(String userName);
    int getIdForCustomer();
    void addPhoneToShop(int productId,int quantity);
    HashMap<Integer,Integer>getProducts();
    boolean containsPhone(int id);
    void updatePhoneQuantity(int id, int count);
    void addPhoneDetails(int productId, PhoneDetailsModel phoneDetailsModel);
    void addPhoneCamera(int productId, PhoneCameraModel phoneCameraModel);
    void addPhoneDimensions(int productId, PhoneDimensionsModel phoneDimensionsModel);
    void addPhoneManufacturer(int productId, PhoneManufacturerModel phoneManufacturerModel);
    void addPhoneProcessor(int productId, PhoneProcessorModel phoneProcessorModel);
    int getLastPhoneId();
    void removePhone(int id);
    int getPhoneId(String modelName);
    ArrayList<Integer> searchPhoneFromFile(String input);
    PhoneDetailsModel getParticularPhone(int id);
    PhoneManufacturerModel getParticularPhoneWithManufacturer(int id);
    PhoneDimensionsModel getParticularPhoneWithDimensions(int id);
    PhoneCameraModel getParticularPhoneWithCamera(int id);
    PhoneProcessorModel getParticularPhoneWithProcessor(int id);
    boolean containsCart(String userName);
    HashMap<Integer, Integer> getCart(String userName);
    void addToStockNillList(int id);
    void writeToOrdersList(String userName, int orderNum, int productId, int quantity);
    void writeToBillFile(int orderNum, int totalAmount);
    ArrayList<Integer> readParticularOrder(String userName);
    ShoppingCartModel getProductsOfThatOrder(int orderId);
    ArrayList<Integer> readAllOrders();
    ArrayList<Integer> readStockNilProducts();
    void writeToWishList(String userName);
    void removeOrderFromCart(String userName);
}