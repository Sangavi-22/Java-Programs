package Admin;

import DataStorage.DataSource;
import DataStorage.DataSourceService;

public class AdminControls implements  AdminControlsService{
    private final DataSourceService dataSource= DataSource.getInstance();
    public void removeSellers(String userName){
        dataSource.removeSeller(userName);
    }
    public void removeCustomers(String userName){
        dataSource.removeCustomer(userName);
    }
}
