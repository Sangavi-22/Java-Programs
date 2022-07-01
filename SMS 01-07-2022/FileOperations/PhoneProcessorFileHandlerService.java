package FileOperations;

import Model.MobilePhone.PhoneProcessorModel;
import java.util.HashMap;

public interface PhoneProcessorFileHandlerService {
    void writePhoneProcessorDetails(int productId, String processorType, String operatingSystem);
    void removePhoneProcessor(int id);
    HashMap<Integer, PhoneProcessorModel> readPhoneProcessorDetails();
}
