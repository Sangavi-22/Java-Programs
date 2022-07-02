package Administator;

import DataStorage.DataSource;
import DataStorage.DataSourceService;
import Inputs.InputsFromUser;

public class Admin implements AdminService {
    private final DataSourceService dataSource= DataSource.getInstance();
    private final InputsFromUser inputsFromUser = new InputsFromUser();
    public int generateId() {
        int memberType = inputsFromUser.inputMemberType();
        int id;
        if (memberType == 1) {
            id =dataSource.getIdForSeller()+1;
        } else if (memberType == 2) {
            id =dataSource.getIdForCustomer()+1;
        } else {
            id =0;
        }
        return id;
    }
}
