package View.MobilePhone;

import Controller.ControlMobilePhone.PhoneControllerService;
import Inputs.InputsFromUser;

public class PhoneView implements PhoneViewService{
    private String modelName,batteryCapacity,primaryCamera,secondaryCamera,manufacturerName,operatingSystem,processorType;
    private double displaySize;
    private int width,height,weight,price;
    private final InputsFromUser inputsFromUser=new InputsFromUser();
    public void inputPhoneDetails(PhoneControllerService phoneController){
        modelName=inputsFromUser.inputModelName();
        manufacturerName=inputsFromUser.inputManufacturer();
        width= inputsFromUser.inputWidth();
        height= inputsFromUser.inputHeight();
        weight=inputsFromUser.inputWeight();
        displaySize=inputsFromUser.inputDisplaySize();
        batteryCapacity=inputsFromUser.inputBatteryCapacity();
        primaryCamera=inputsFromUser.inputPrimaryCamera();
        secondaryCamera=inputsFromUser.inputSecondaryCamera();
        operatingSystem=inputsFromUser.inputOperatingSystem();
        processorType=inputsFromUser.inputProcessorType();
        price=inputsFromUser.inputPrice();

    }
    public void passPhoneDetailsToController(PhoneControllerService phoneController){
        phoneController.setModelName(modelName);
        phoneController.setManufacturerName(manufacturerName);
        phoneController.setWidth(width);
        phoneController.setHeight(height);
        phoneController.setWeight(weight);
        phoneController.setDisplaySize(displaySize);
        phoneController.setBatteryCapacity( batteryCapacity);
        phoneController.setPrimaryCamera(primaryCamera);
        phoneController.setSecondaryCamera(secondaryCamera);
        phoneController.setOperatingSystem(operatingSystem);
        phoneController.setProcessorType(processorType);
        phoneController.setPrice(price);

    }
}
