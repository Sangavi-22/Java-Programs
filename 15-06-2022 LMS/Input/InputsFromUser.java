package Input;
import Validation.ValidateInput;
import java.util.Scanner;
public class InputsFromUser{
    ValidateInput validate=new ValidateInput();
    Scanner input=new Scanner(System.in);
    public int inputChoice(){
        String userInput;
        while(true) {
            System.out.println("Enter your choice:");
            userInput = input.nextLine();
            if(!(validate.validateUserChoice(userInput))) {
                System.out.println("Invalid input");
            }
            else {
                break;
            }
        }
        return Integer.parseInt(userInput);
    }
    public String inputName(){
        String name;
        while(true) {
            System.out.println("Enter your name:");
            name = input.nextLine();
            if(!(validate.validateProfileName(name))) {
                System.out.println("Invalid name");
            }
            else {
                break;
            }
        }
        return name;
    }
    public String inputEmail(){
        String email;
        while(true) {
            System.out.println("Enter your email:");
            System.out.println("If you do not have an email id enter \"-\" ");
            email = input.nextLine();
            if(!(validate.validateEmail(email))) {
                System.out.println("Invalid email");
            }
            else {
                break;
            }
        }
        return email;
    }
    public long inputMobileNum(){
        String mobileNum;
        while(true) {
            System.out.println("Enter your mobile number:");
            System.out.println("If you do not have a mobile number enter \"-\" ");
            mobileNum = input.nextLine();
            if(mobileNum.equals("-")) {
                mobileNum="0";
                break;
            }
            else if(!(validate.validateMobileNum(mobileNum))) {
                System.out.println("Invalid mobile Number");
            }
            else {
                break;
            }
        }
        return Long.parseLong(mobileNum);
    }
    public String inputUserName(){
        String userName;
        while(true) {
            System.out.println("Enter your username:");
            userName = input.nextLine();
            if(!(validate.validateUserAccountName(userName))) {
                System.out.println("Invalid username");
            }
            else {
                break;
            }
        }
        return userName;
    }

    public String inputPassword(){
        String passkey;
        while(true) {
            System.out.println("Enter your password:");
            passkey = input.nextLine();
            if(!(validate.validatePassword(passkey))) {
                System.out.println("Invalid password");
            }
            else {
                break;
            }
        }
        return passkey;
    }
    public int inputUserId(){
        String userInput;
        while(true) {
            System.out.println("Enter your userId:");
            userInput = input.nextLine();
            if(!(validate.validateUserChoice(userInput))) {
                System.out.println("Invalid user id");
            }
            else {
                break;
            }
        }
        return Integer.parseInt(userInput);
    }
    public int inputNoOfBooks(){
        String totalNumberOfBooks;
        while (true) {
            System.out.println("Enter the number of books");
            totalNumberOfBooks = input.nextLine();
            if (!(validate.validateNoOfBooks(totalNumberOfBooks))) {
                System.out.println("Invalid input");
            }
            else {
                break;
            }
        }
        return Integer.parseInt(totalNumberOfBooks);
    }
    public String inputBookName(){
        String bookName;
        while(true) {
            System.out.println("Enter the title of the book");
            bookName = input.nextLine();
            if (!(validate.validateBookName(bookName))) {
                System.out.println("Invalid title");
            } else {
                break;
            }
        }
        return bookName;
    }
    public String inputPublisherName(){
        String publisherName;
        while(true) {
            System.out.println("Enter the name of the publisher");
            publisherName = input.nextLine();
            if (!(validate.validatePublisherName(publisherName))) {
                System.out.println("Invalid publication name");
            }
            else {
                break;
            }
        }
        return publisherName;
    }
    public int inputBookCost(){
        String bookCost;
        while(true) {
            System.out.println("Enter the cost of the book");
            bookCost = input.nextLine();
            if (!(validate.validateBookCost(bookCost))) {
                System.out.println("Invalid cost value");
            } else {
                break;
            }
        }
        return Integer.parseInt(bookCost);
    }
    public int inputNoOfPages(){
        String inputPagesCount ;
        while (true) {
            System.out.println("Enter the total number of pages");
            inputPagesCount = input.nextLine();
            if (!(validate.validateNoOfPages(inputPagesCount))) {
                System.out.println("Invalid input");
            }
            else{
                break;
            }
        }
        return Integer.parseInt(inputPagesCount);
    }
    public String inputPageLayout(){
        String pageLayout ;
        while (true) {
            System.out.println("Enter the layout of the pages");
            pageLayout= input.nextLine();
            if (!(validate.validatePageLayout(pageLayout))) {
                System.out.println("Invalid layout input");
            }
            else{
                break;
            }
        }
        return pageLayout;
    }
    public int inputNoOfAuthors(){
        String noOfAuthors;
        while(true) {
            System.out.println("Enter the number of authors");
            noOfAuthors = input.nextLine();
            if (!(validate.validateNoOfAuthors(noOfAuthors))) {
                System.out.println("Invalid input");
            } else {
                break;
            }
        }
        return Integer.parseInt(noOfAuthors);
    }
    public String inputAuthorName(){
        String inputAuthorName;
        while(true) {
            System.out.println("Enter the name of the author");
            inputAuthorName = input.nextLine();
            if (!(validate.validateAuthorName(inputAuthorName))) {
                System.out.println("Invalid author name");
            } else {
                break;
            }
        }
        return inputAuthorName;
    }
    public int inputBookIdFromUser(){
        String bookIdFromUser;
        while(true) {
            System.out.println("Enter the id of the book");
            bookIdFromUser = input.nextLine();
            if (!(validate.validateBookId(bookIdFromUser))) {
                System.out.println("Invalid book id");
            }
            else {
                break;
            }
        }
        return Integer.parseInt(bookIdFromUser);
    }
    public int inputMemberType(){
        String userInput;
        while(true) {
            System.out.println("Are you a librarian:\nEnter 1 for Yes\nEnter 2 for No");
            userInput=input.nextLine();
            if(!(validate.validateUserChoice(userInput))) {
                System.out.println("Invalid user input");
            }
            else{
                break;
            }
        }
        return Integer.parseInt(userInput);
    }
    public String inputAccountId(){
        String inputAccountId;
        while(true) {
            System.out.println("Enter your bank account id");
            inputAccountId = input.nextLine();
            if(!(validate.validateAccountId(inputAccountId))) {
                System.out.println("Invalid input");
            }
            else {
                break;
            }
        }
        return inputAccountId;
    }
    public String inputGooglePayId(){
        String inputGooglePayId;
        while(true) {
            System.out.println("Enter your google pay id");
            inputGooglePayId = input.nextLine();
            if(!(validate.validateAccountId(inputGooglePayId))) {
                System.out.println("Invalid input");
            }
            else {
                break;
            }
        }
        return inputGooglePayId;
    }
}
