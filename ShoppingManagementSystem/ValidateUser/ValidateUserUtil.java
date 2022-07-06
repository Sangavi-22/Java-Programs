package ValidateUser;

import DataStorage.DataSource;
import DataStorage.DataSourceService;
import Inputs.InputsFromUser;

public class ValidateUserUtil {
    private final DataSourceService dataSource= DataSource.getInstance();
    private final InputsFromUser inputsFromUser = new InputsFromUser();
    public int generateId(String input) {
        int memberType, id;
        if(input.equals("guest")) {
            id =dataSource.getIdForCustomer()+1;
        }
        else {
            memberType = inputsFromUser.inputMemberType();
            if (memberType == 1) {
                id =dataSource.getIdForSeller()+1;
            } else if (memberType == 2) {
                id =dataSource.getIdForCustomer()+1;
            } else {
                id =0;
            }
        }
        return id;
    }
    public boolean validateEmployee(String employmentNum){
        return dataSource.containsEmployeeNum(employmentNum);
    }
}
