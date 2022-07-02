package Controller.ControlLogin;

import DataStorage.DataSource;
import DataStorage.DataSourceService;
import Model.Customer.CustomerAccountModel;
import Model.Seller.SellerAccountModel;

public class LoginUtility {
    private final DataSourceService dataSource=DataSource.getInstance();
    public boolean isSellerPresentAlready(String userName){
        return dataSource.containsSeller(userName);
    }
    public boolean passwordMatchesForSeller(String userName,String password) {
        return dataSource.passwordOfSellerMatches(userName,password);
    }
    public SellerAccountModel getSellerModel(String userName){
        return dataSource.getSeller(userName);
    }
    public boolean isCustomerPresentAlready(String userName){
        return dataSource.containsCustomer(userName);
    }
    public boolean passwordMatchesForCustomer(String userName,String password) {
        return dataSource.passwordOfCustomerMatches(userName,password);
    }
    public CustomerAccountModel getCustomerModel(String userName){
        return dataSource.getCustomer(userName);
    }


}
