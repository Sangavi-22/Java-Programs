package Validation;

public class ValidateInput {
    public boolean validateUserChoice(String userInput) {
        return userInput.matches("\\d+");
    }
    public boolean validateUserAccountName(String inputUserName){
        return inputUserName.matches("[a-zA-Z@*.!~()&#$%^_']{5,20}+");
    }
    public boolean validatePassword(String passkey){
        return passkey.matches("[a-z A-Z\\d+.~!@#$%^&*()_-]{8,20}+");
    }
    public boolean validateProfileName(String inputProfileName){
        return inputProfileName.matches("[a-z A-Z.']+|[a-z A-Z.']+\\s[a-z A-Z.']+");
    }
    public boolean validateEmail(String inputEmail){
        return inputEmail.matches("^([a-zA-Z\\d+_.-]+@[a-zA-Z\\d.-]+\\.[a-zA-Z\\d.-]+)|-$");
    }
    public boolean validateMobileNum(String inputMobileNum){
        return inputMobileNum.matches("([\\d+]{10})|-");
    }
    public boolean validateAddress(String inputAddress){
        return inputAddress.matches("[a-z A-Z\\d -/,.()#*]+");
    }
    public boolean validateModelName(String inputModelName){
        return inputModelName.matches("");
    }
    public boolean validateManufacturerName(String inputManufacturerName){
        return inputManufacturerName.matches("");
    }
    public boolean validateWidthOfPhone(String inputWidth){
        return inputWidth.matches("");
    }
    public boolean validateHeightOfPhone(String inputHeight){
        return inputHeight.matches("");
    }
    public boolean validateWeightOfPhone(String inputWeight){
        return inputWeight.matches("");
    }
    public boolean validateDisplaySize(String inputDisplaySize){
        return inputDisplaySize.matches("");
    }
    public boolean validateBatteryCapacity(String inputBatteryCapacity){
        return inputBatteryCapacity.matches("");
    }
    public boolean validatePrimaryCamera(String inputPrimaryCamera){
        return inputPrimaryCamera.matches("");
    }
    public boolean validateSecondaryCamera(String inputSecondaryCamera){
        return inputSecondaryCamera.matches("");
    }
    public boolean validateOperatingSystem(String inputOperatingSystem){
        return inputOperatingSystem.matches("");
    }
    public boolean validateProcessorType(String inputProcessorType){
        return inputProcessorType.matches("");
    }
    public boolean validateNoOfProducts(String inputNoOfProducts){
        return inputNoOfProducts.matches("\\d+");
    }
    public boolean validateProductId(String inputProductId){
        return inputProductId.matches("\\d+");
    }
    public boolean validateProductCount(String inputProductCount){
        return inputProductCount.matches("\\d+");
    }
    public boolean validateInput(String inputValue) {
        return inputValue.matches("[a-z A-Z\\d.#%@!^&*(),?/;:'{}\\s+]+");
    }
    public boolean validatePrice(String inputValue) {
        return inputValue.matches("\\d+");
    }
    public boolean validateAccountId(String inputAccountId) {
        return inputAccountId.matches("[a-z A-Z\\d+]+");
    }
}
