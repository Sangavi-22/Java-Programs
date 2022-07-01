package View.MobilePhone;

public interface PrintPhoneInfoViewService {
    void printPhoneBasicInfo(int productId,String name);
    void printPhoneDetails(int productId, String modelName, String manufacturerName, int width, int height, int weight, double displaySize, String batteryCapacity, String primaryCamera, String secondaryCamera, String operatingSystem, String processorType);
    void printQuantityDetails(int quantity);
    void printOrderedQuantityDetails(int orderedQuantity);
}
