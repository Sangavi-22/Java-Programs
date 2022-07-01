package FileOperations;

import Model.MobilePhone.PhoneManufacturerModel;
import java.util.HashMap;

public interface PhoneManufacturerFileHandlerService {
    void writePhoneManufacturerDetails(int productId, String manufacturerName);
    void removePhoneManufacturer(int id);
    HashMap<Integer,PhoneManufacturerModel> readPhoneManufacturerDetails();
}
