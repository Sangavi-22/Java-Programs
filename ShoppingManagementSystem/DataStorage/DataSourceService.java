package DataStorage;

import Model.Users.CustomerAccountModel;
import Model.MobilePhone.*;
import Model.Users.SellerAccountModel;
import Model.ShoppingCart.ShoppingCartModel;

import java.util.ArrayList;
import java.util.HashMap;

public interface DataSourceService {
    boolean containsAdmin(String userName);
    boolean passwordOfAdminMatches(String userName, String password);
    boolean containsEmployeeNum(String employmentNum);
    int getIdForSeller();
    void addSeller(SellerAccountModel sellerAccountModel);
    boolean containsSeller(String userName);
    boolean passwordOfSellerMatches(String userName, String password);
    SellerAccountModel getSeller(String userName);
    HashMap<String, SellerAccountModel> getSellers();
    void updateSellerDetailsInDataSource(SellerAccountModel model);
    void removeSeller(String userName);
    int getIdForCustomer();
    void addCustomer(CustomerAccountModel customerAccountModel);
    boolean containsCustomer(String userName);
    boolean passwordOfCustomerMatches(String userName, String password);
    CustomerAccountModel getCustomer(String userName);
    HashMap<String, CustomerAccountModel> getCustomers();
    void updateCustomerDetailsInDataSource(CustomerAccountModel model);
    void removeCustomer(String userName);
    int getLastPhoneId();
    int getPhoneId(String modelName);
    void addPhoneToShop(int productId,int quantity);
    ArrayList<Integer> searchFromShop(String productId);
    boolean containsPhone(int id);
    HashMap<Integer,Integer>getPhones();
    void updatePhoneQuantity(int id, int count);
    void removePhone(int id);
    void addPhoneDetails(int productId, PhoneDetailsModel phoneDetailsModel);
    ArrayList<Integer>searchFromPhoneDetails(String input);
    PhoneDetailsModel getParticularPhone(int id);
    HashMap<Integer, PhoneDetailsModel> getPhoneDetails();
    void addPhoneCamera(int productId, PhoneCameraModel phoneCameraModel);
    ArrayList<Integer>searchFromPhoneCameraDetails(String input);
    PhoneCameraModel getParticularPhoneWithCamera(int id);
    HashMap<Integer,PhoneCameraModel>getPhoneCameraDetails();
    void addPhoneDimensions(int productId, PhoneDimensionsModel phoneDimensionsModel);
    ArrayList<Integer>searchFromPhoneDimensionsDetails(String input);
    PhoneDimensionsModel getParticularPhoneWithDimensions(int id);
    HashMap<Integer,PhoneDimensionsModel>getPhoneDimensions();
    void addPhoneManufacturer(int productId, PhoneManufacturerModel phoneManufacturerModel);
    ArrayList<Integer>searchFromPhoneManufacturer(String input);
    PhoneManufacturerModel getParticularPhoneWithManufacturer(int id);
    HashMap<Integer,PhoneManufacturerModel>getPhoneManufacturer();
    void addPhoneProcessor(int productId, PhoneProcessorModel phoneProcessorModel);
    ArrayList<Integer>searchFromPhoneProcessor(String input);
    PhoneProcessorModel getParticularPhoneWithProcessor(int id);
    HashMap<Integer,PhoneProcessorModel>getPhoneProcessorDetails();
    void addPhoneStorageCapacity(int productId, PhoneStorageCapacityModel phoneStorageCapacityModel);

    ArrayList<Integer>searchFromPhoneStorageCapacity(String input);
    PhoneStorageCapacityModel getParticularPhoneWithStorageCapacity(int productIds);
    HashMap<Integer,PhoneStorageCapacityModel>getPhoneStorageCapacityDetails();
    ArrayList<Integer> searchPhoneFromFile(String input);
    void writeToCartFile(String userName, HashMap<Integer, Integer> productsFromCart);
    boolean containsCart(String userName);
    ShoppingCartModel getCart(String userName);
    void addToStockNillList(int id);
    ArrayList<Integer> readStockNilProducts();
    void removeFromStockNilList(int phoneId);
    int getLastOrderNum();
    void writeToOrdersList(String userName, int orderNum, int productId, int quantity, String deliveryStatus);
    void removeOrder(int orderId,String userName);
    String getUserFromOrderedUsers(int orderId);
    void writeToBillFile(int orderNum, int totalAmount);
    int readFromBillFile(int orderId);
    ArrayList<Integer> readParticularOrder(String userName);
    ShoppingCartModel getProductsOfThatOrder(int orderId);
    ArrayList<Integer> readAllOrders();
    void freeCartForUser(String userName);
    HashMap<Integer, String> readOrderStatus(String userName);
    void updateOrderStatus(String userFromOrderedUsers, int orderId);



}
