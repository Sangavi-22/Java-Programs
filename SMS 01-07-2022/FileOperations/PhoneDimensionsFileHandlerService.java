package FileOperations;

import Model.MobilePhone.PhoneDimensionsModel;
import java.util.HashMap;

public interface PhoneDimensionsFileHandlerService {
    void writePhoneDimensions(int productId, int width, int height, int weight);
    void removePhoneDimensions(int id);
    HashMap<Integer, PhoneDimensionsModel> readPhoneDimensionsDetails();
}
