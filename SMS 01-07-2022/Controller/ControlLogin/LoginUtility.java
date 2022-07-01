package Controller.ControlLogin;

import DataStorage.DataSource;
import DataStorage.DataSourceService;

public class LoginUtility {
    private final DataSourceService dataSource=DataSource.getInstance();
    public boolean isSellerPresentAlready(String userName){
        return dataSource.containsSeller(userName);
    }
    public boolean isCustomerPresentAlready(String userName){
        return dataSource.containsCustomer(userName);
    }
}
