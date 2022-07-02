package DataStorage;

import FileOperations.*;
import Model.Customer.CustomerAccountModel;
import Model.MobilePhone.*;
import Model.Seller.SellerAccountModel;
import Model.ShoppingCart.ShoppingCartModel;
import java.util.ArrayList;
import java.util.HashMap;

public class DataSource implements DataSourceService {
    private static DataSource dataSource = null;
    private final SellerDetailsFileHandlerService sellerDetailsFile = new SellerDetailsFileHandler();
    private final CustomerDetailsFileHandlerService customerDetailsFile = new CustomerDetailsFileHandler();
    private final PhoneDetailsFileHandlerService phoneDetailsFile=new PhoneDetailsFileHandler();
    private final PhoneCameraFileHandlerService phoneCameraFile=new PhoneCameraFileHandler();
    private final PhoneDimensionsFileHandlerService phoneDimensionsFile=new PhoneDimensionsFileHandler();
    private final PhoneManufacturerFileHandlerService phoneManufacturerFile=new PhoneManufacturerFileHandler();
    private final PhoneProcessorFileHandlerService phoneProcessorFile=new PhoneProcessorFileHandler();
    private final ShopFileHandlerService shopFile=new ShopFileHandler();
    private final ShoppingCartFileHandlerService shoppingCartFile=new ShoppingCartFileHandler();
    private final StockNotAvailableFileHandlerService stockNotAvailableFile=new StockNotAvailableFileHandler();
    private final OrdersFileHandlerService ordersFile=new OrdersFileHandler();
    private final BillAmountFileHandlerService billFile=new BillAmountFileHandler();
    private HashMap<String, SellerAccountModel> sellers = new HashMap<>();
    private HashMap<String, CustomerAccountModel> customers = new HashMap<>();
    private HashMap<Integer,Integer>shopProductsMap=new HashMap<>();
    private HashMap<Integer,PhoneDetailsModel>phoneDetailsMap=new HashMap<>();
    private HashMap<Integer,PhoneCameraModel>phoneCameraMap=new HashMap<>();
    private HashMap<Integer,PhoneDimensionsModel>phoneDimensionsMap=new HashMap<>();
    private HashMap<Integer,PhoneManufacturerModel>phoneManufacturerMap=new HashMap<>();
    private HashMap<Integer,PhoneProcessorModel>phoneProcessorMap=new HashMap<>();
    private DataSource() {
        //constructor
    }
    public static DataSource getInstance() {
        if (dataSource == null) {
            dataSource = new DataSource();
        }
        return dataSource;
    }
    public void addSeller(SellerAccountModel sellerAccountModel) {
        sellerDetailsFile.writeSellerDetails(sellerAccountModel.getUserId(), sellerAccountModel.getUserName(), sellerAccountModel.getPassword(), sellerAccountModel.getName(), sellerAccountModel.getEmail(), sellerAccountModel.getMobileNum());
        sellers.put(sellerAccountModel.getUserName(), sellerAccountModel);
    }
    public HashMap<String, SellerAccountModel> getSellers() {
        if (sellers.size() == 0) {
            sellers = sellerDetailsFile.readSellers();
        }
        return sellers;
    }
    public boolean containsSeller(String userName) {
        boolean flag;
        flag = getSellers().containsKey(userName);
        return flag;
    }
    public int getIdForSeller() {
        return sellerDetailsFile.readLastSellerId();
    }
    public boolean passwordOfSellerMatches(String userName, String password){
        return getSellers().containsKey(userName) && getSellers().get(userName).getPassword().equals(password);
    }
    public SellerAccountModel getSeller(String userName){
        return getSellers().get(userName);
    }
    public void addCustomer(CustomerAccountModel customerAccountModel) {
        customerDetailsFile.writeCustomerDetails(customerAccountModel.getUserId(), customerAccountModel.getUserName(), customerAccountModel.getPassword(), customerAccountModel.getName(), customerAccountModel.getEmail(), customerAccountModel.getMobileNum(), customerAccountModel.getShippingAddress());
        customers.put(customerAccountModel.getUserName(), customerAccountModel);
    }
    public HashMap<String, CustomerAccountModel> getCustomers() {
        if (customers.size() == 0) {
            customers = customerDetailsFile.readCustomers();
        }
        return customers;
    }

    public boolean containsCustomer(String userName) {
        boolean flag;
        HashMap<String, CustomerAccountModel> customersList = getCustomers();
        flag = customersList.containsKey(userName);
        return flag;
    }

    public int getIdForCustomer() {
        return customerDetailsFile.readLastCustomerId();
    }
    public boolean passwordOfCustomerMatches(String userName, String password){
        return getCustomers().containsKey(userName) && getCustomers().get(userName).getPassword().equals(password);
    }
    public CustomerAccountModel getCustomer(String userName){
        return getCustomers().get(userName);
    }
    public void addPhoneToShop(int productId,int quantity) {
        shopFile.writeProductToShop(productId,quantity);
        shopProductsMap.put(productId,quantity);
    }
    public HashMap<Integer,Integer>getPhones(){
        if (shopProductsMap.size() == 0) {
            shopProductsMap= shopFile.readProducts();
        }
        return shopProductsMap;
    }
    public boolean containsPhone(int id){
        return shopProductsMap.containsKey(id);
    }
    private int  searchFromShop(int productId) {
        int id=0;
        HashMap<Integer,Integer> products=getPhones();
        for(int key: products.keySet()) {
            if (key == productId) {
                id = productId;
                break;
            }
            else {
                continue;
            }
        }
        return id;
    }
    public void addPhoneDetails(int productId, PhoneDetailsModel phoneDetailsModel){
        phoneDetailsFile.writePhoneDetails(productId,phoneDetailsModel.getModelName(),phoneDetailsModel.getBatteryCapacity(),phoneDetailsModel.getDisplaySize(),phoneDetailsModel.getPrice());
        phoneDetailsMap.put(productId,phoneDetailsModel);
    }
    public HashMap<Integer,PhoneDetailsModel>getPhoneDetails(){
        if(phoneDetailsMap.size()==0) {
            phoneDetailsFile.readPhoneDetails();
        }
        return phoneDetailsMap;
    }
    public PhoneDetailsModel getParticularPhone(int id){
        return getPhoneDetails().get(id);
    }
    public ArrayList<Integer>searchFromPhoneDetails(String input){
        ArrayList<Integer>ids=new ArrayList<>();
        HashMap<Integer,PhoneDetailsModel> phoneDetails=getPhoneDetails();
        for(int key: phoneDetails.keySet()) {
            PhoneDetailsModel phoneDetailsModel=phoneDetails.get(key);
            if(phoneDetailsModel.getModelName().toLowerCase().contains(input.toLowerCase()) || phoneDetailsModel.getBatteryCapacity().contains(input.toLowerCase())||
            phoneDetailsModel.getDisplaySize()==Double.parseDouble(input)|| phoneDetailsModel.getProductId()==Integer.parseInt(input) ||phoneDetailsModel.getPrice()==Integer.parseInt(input)) {
                ids.add(key);
            }
            else {
                continue;
            }
        }
        return ids;
    }
    public void addPhoneCamera(int productId, PhoneCameraModel phoneCameraModel){
        phoneCameraFile.writePhoneCameraDetails(productId,phoneCameraModel.getPrimaryCamera(),phoneCameraModel.getSecondaryCamera());
        phoneCameraMap.put(productId,phoneCameraModel);
    }
    public HashMap<Integer,PhoneCameraModel>getPhoneCameraDetails(){
        if(phoneCameraMap.size()==0) {
            phoneCameraFile.readPhoneCameraDetails();
        }
        return phoneCameraMap;
    }
    public PhoneCameraModel getParticularPhoneWithCamera(int id){
        return getPhoneCameraDetails().get(id);
    }
    public ArrayList<Integer>searchFromPhoneCameraDetails(String input){
        ArrayList<Integer>ids=new ArrayList<>();
        HashMap<Integer,PhoneCameraModel> phoneCameraDetails=getPhoneCameraDetails();
        for(int key: phoneCameraDetails.keySet()) {
            PhoneCameraModel phoneCameraModel=phoneCameraDetails.get(key);
            if(phoneCameraModel.getPrimaryCamera().toLowerCase().contains(input.toLowerCase()) || phoneCameraModel.getSecondaryCamera().toLowerCase().contains(input.toLowerCase()) ) {
                ids.add(key);
            }
            else {
                continue;
            }
        }
        return ids;
    }
    public void addPhoneDimensions(int productId,PhoneDimensionsModel phoneDimensionsModel){
        phoneDimensionsFile.writePhoneDimensions(productId,phoneDimensionsModel.getWidth(),phoneDimensionsModel.getHeight(),phoneDimensionsModel.getWeight());
        phoneDimensionsMap.put(productId,phoneDimensionsModel);
    }
    public HashMap<Integer,PhoneDimensionsModel>getPhoneDimensions(){
        if(phoneDimensionsMap.size()==0) {
            phoneDimensionsFile.readPhoneDimensionsDetails();
        }
        return phoneDimensionsMap;
    }
    public PhoneDimensionsModel getParticularPhoneWithDimensions(int id){
        return getPhoneDimensions().get(id);
    }
    public ArrayList<Integer>searchFromPhoneDimensionsDetails(String input){
        ArrayList<Integer>ids=new ArrayList<>();
        HashMap<Integer,PhoneDimensionsModel> phoneDimensions=getPhoneDimensions();
        for(int key: phoneDimensions.keySet()) {
            PhoneDimensionsModel phoneDimensionsModel=phoneDimensions.get(key);
            if(phoneDimensionsModel.getWidth()==Integer.parseInt(input)|| phoneDimensionsModel.getHeight()==Integer.parseInt(input)||
            phoneDimensionsModel.getWeight()==Integer.parseInt(input)){
                ids.add(key);
            }
            else {
                continue;
            }
        }
        return ids;
    }
    public void addPhoneManufacturer(int productId,PhoneManufacturerModel phoneManufacturerModel ){
        phoneManufacturerFile.writePhoneManufacturerDetails(productId,phoneManufacturerModel.getManufacturerName());
        phoneManufacturerMap.put(productId,phoneManufacturerModel);
    }
    public HashMap<Integer,PhoneManufacturerModel>getPhoneManufacturer(){
        if(phoneManufacturerMap.size()==0) {
            phoneManufacturerFile.readPhoneManufacturerDetails();
        }
        return phoneManufacturerMap;
    }
    public PhoneManufacturerModel getParticularPhoneWithManufacturer(int id){
        return getPhoneManufacturer().get(id);
    }
    public ArrayList<Integer>searchFromPhoneManufacturer(String input){
        ArrayList<Integer>ids=new ArrayList<>();
        HashMap<Integer,PhoneManufacturerModel> phoneManufacturerDetails=getPhoneManufacturer();
        for(int key: phoneManufacturerDetails.keySet()) {
            PhoneManufacturerModel phoneManufacturerModel=phoneManufacturerDetails.get(key);
            if(phoneManufacturerModel.getManufacturerName().toLowerCase().contains(input.toLowerCase())){
                ids.add(key);
            }
            else {
                continue;
            }
        }
        return ids;
    }
    public void addPhoneProcessor(int productId,PhoneProcessorModel phoneProcessorModel){
        phoneProcessorFile.writePhoneProcessorDetails(productId,phoneProcessorModel.getProcessorType(),phoneProcessorModel.getOperatingSystem());
        phoneProcessorMap.put(productId,phoneProcessorModel);
    }
    public HashMap<Integer,PhoneProcessorModel>getPhoneProcessorDetails(){
        if(phoneProcessorMap.size()==0) {
            phoneProcessorFile.readPhoneProcessorDetails();
        }
        return phoneProcessorMap;
    }
    public PhoneProcessorModel getParticularPhoneWithProcessor(int id){
        return getPhoneProcessorDetails().get(id);
    }
    public ArrayList<Integer>searchFromPhoneProcessor(String input){
        ArrayList<Integer>ids=new ArrayList<>();
        HashMap<Integer,PhoneProcessorModel> phoneProcessor=getPhoneProcessorDetails();
        for(int key: phoneProcessor.keySet()) {
            PhoneProcessorModel phoneProcessorModel=phoneProcessor.get(key);
            if(phoneProcessorModel.getProcessorType().toLowerCase().contains(input.toLowerCase())|| phoneProcessorModel.getOperatingSystem().toLowerCase().contains(input.toLowerCase())){
                ids.add(key);
            }
            else {
                continue;
            }
        }
        return ids;
    }
    public ArrayList<Integer> searchPhoneFromFile(String input) {
        ArrayList<Integer>productIds=new ArrayList<>();
        productIds.add(searchFromShop(Integer.parseInt(input)));
        productIds.addAll(searchFromPhoneDetails(input));
        productIds.addAll(searchFromPhoneCameraDetails(input));
        productIds.addAll(searchFromPhoneDimensionsDetails(input));
        productIds.addAll(searchFromPhoneManufacturer(input));
        productIds.addAll(searchFromPhoneProcessor(input));
        return productIds;
    }
    public void updatePhoneQuantity(int id,int count){
        int quantity=shopProductsMap.get(id);
        shopProductsMap.put(id,quantity+count);
        shopFile.updateProductInShop(id,quantity+count);
    }
    public int getPhoneId(String modelName) {
        int id=0;
        for(int productId: getPhoneDetails().keySet()) {
            if(getPhoneDetails().get(productId).getModelName().equals(modelName)) {
                return id;
            }
            else {
                continue;
            }
        }
        return id;
    }
    public int getLastPhoneId(){
        return phoneDetailsFile.readLastPhoneId();
    }
    public void removePhone(int id){
        shopFile.removePhoneFromShop(id);
        shopProductsMap.remove(id);
        phoneDetailsFile.removePhoneDetails(id);
        phoneDetailsMap.remove(id);
        phoneCameraFile.removePhoneCamera(id);
        phoneCameraMap.remove(id);
        phoneDimensionsFile.removePhoneDimensions(id);
        phoneDimensionsMap.remove(id);
        phoneManufacturerFile.removePhoneManufacturer(id);
        phoneManufacturerMap.remove(id);
        phoneProcessorFile.removePhoneProcessor(id);
        phoneProcessorMap.remove(id);
    }
    public void writeToOrdersList(String userName,int orderNum,int productId,int quantity){
        ordersFile.writeOrders(orderNum,productId,quantity);
        ordersFile.writeOrderedUsers(orderNum,userName);
    }
    public void writeToBillFile(int orderNum, int totalAmount){
        billFile.writeBillAmount(orderNum,totalAmount);
    }
    public boolean containsCart(String userName){
        return shoppingCartFile.containsShoppingCart(userName);
    }
    public HashMap<Integer,Integer>getCart(String userName){
        return shoppingCartFile.getShoppingCart(userName);
    }
    public void addToStockNillList(int id){
        stockNotAvailableFile.writeToFile(id);
    }
    public ArrayList<Integer> readParticularOrder(String userName){
        return ordersFile.readUserOrderId(userName);
    }
    public ShoppingCartModel getProductsOfThatOrder(int orderId){
        return ordersFile.readProducts(orderId);
    }
    public ArrayList<Integer> readAllOrders(){
        return ordersFile.readAllOrderFromFile();
    }
    public  ArrayList<Integer> readStockNilProducts(){
        return stockNotAvailableFile.readStockNotAvailableProducts();
    }
    public void removeSeller(String userName){
        sellerDetailsFile.removeSellerDetailsFromFile(userName);
        sellers.remove(userName);
    }
    public void removeCustomer(String userName){
        customerDetailsFile.removeCustomerDetailsFromFile(userName);
        customers.remove(userName);
    }
    public int getLastOrderNum(String userName){
        return ordersFile.readLastOrderNum(userName);
    }
    public  void freeCartForUser(String userName){
        shoppingCartFile.removeFromCart(userName);
    }
    public void writeToCartFile(String userName, HashMap<Integer, Integer> productsFromCart){
        shoppingCartFile.writeToCart(userName,productsFromCart);
    }
    public  void removeFromStockNilList(int phoneId){
        stockNotAvailableFile.removePhoneId(phoneId);
    }
}
