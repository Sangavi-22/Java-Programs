package Validation;
public class ValidateInput {
    public boolean validateUserChoice(String userInput) {
        return userInput.matches("\\d+");
    }
    public boolean validateUserAccountName(String inputUserName){
        return inputUserName.matches("[a-zA-Z@*.!()&#$%^']{5,20}+");
    }
    public boolean validatePassword(String passkey){
        return passkey.matches("[a-z A-Z \\d+ !@#$%^&*()_-]{8,20}+");
    }
    public boolean validateNoOfBooks(String totalNumberOfBooks) {
        return totalNumberOfBooks.matches("\\d+");
    }
    public boolean validateBookId(String inputBookIdFromUser){
        return inputBookIdFromUser.matches("\\d+");
    }
    public boolean validateBookName(String inputBookName) {
        return inputBookName.matches("[A-Za-z\\d+\\s-_,.;:()'\"]+");
    }
    public boolean validatePublisherName(String inputPublisherName){
        return inputPublisherName.matches("[A-Za-z\\d+\\s-_,.;:()'\"]+");
    }
    public boolean validateBookCost(String inputBookCost){
        return inputBookCost.matches("\\d+");
    }
    public boolean validateNoOfAuthors(String inputNoOfAuthor){
        return inputNoOfAuthor.matches("\\d+");
    }
    public boolean validateAuthorName(String inputAuthorName){
        return inputAuthorName.matches("[a-z A-Z.']+|[a-z A-Z.']+\\s[a-z A-Z.']+");
    }
    public boolean validateNoOfPages(String inputPagesCount){
        return inputPagesCount.matches("\\d+");
    }
    public boolean validatePageLayout(String inputPageLayout){
        return inputPageLayout.matches("[a-z A-Z]+");
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
    public boolean validateAccountId(String inputAccountId){
        return inputAccountId.matches("[a-z A-Z \\d+]+");
    }
}
