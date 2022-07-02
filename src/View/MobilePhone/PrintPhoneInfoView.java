package View.MobilePhone;

public class PrintPhoneInfoView implements PrintPhoneInfoViewService{
    public void printPhoneBasicInfo(int productId,String name){
        System.out.println("Phone Id: "+productId);
        System.out.println("Model Name: "+name);
    }
    public void printPhoneDetails(int productId,String modelName,String manufacturerName,int width,int height,int weight,double displaySize,String batteryCapacity,String primaryCamera,String secondaryCamera,String operatingSystem,String processorType){
        System.out.println();
        System.out.println("*******************************************************");
        System.out.println("!                    PHONE DETAILS                    !");
        System.out.println("*******************************************************");
        System.out.println("Phone Id: "+productId);
        System.out.println("ModelName: "+modelName);
        System.out.println("ManufacturerName: "+manufacturerName);
        System.out.println("Width: "+width);
        System.out.println("Height: "+height);
        System.out.println("Weight: "+weight);
        System.out.println("DisplaySize: "+displaySize);
        System.out.println("Battery Capacity: "+batteryCapacity);
        System.out.println("Primary Camera: "+primaryCamera);
        System.out.println("Secondary Camera: "+secondaryCamera);
        System.out.println("Operating System: "+operatingSystem);
        System.out.println("Processor Type: "+processorType);
    }
    public void printQuantityDetails(int quantity){
        System.out.println("Stock Available: "+quantity);
        System.out.println("*******************************************************");
        System.out.println();
    }
    public void printOrderedQuantityDetails(int orderedQuantity){
        System.out.println("Ordered Quantity: "+orderedQuantity);
        System.out.println("*******************************************************");
        System.out.println();
    }
}
