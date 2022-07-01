package FileOperations;

import Model.MobilePhone.PhoneCameraModel;
import java.util.HashMap;

public interface PhoneCameraFileHandlerService {
    void writePhoneCameraDetails(int productId, String primaryCamera, String secondaryCamera);
    void removePhoneCamera(int id);
    HashMap<Integer, PhoneCameraModel> readPhoneCameraDetails();
}
