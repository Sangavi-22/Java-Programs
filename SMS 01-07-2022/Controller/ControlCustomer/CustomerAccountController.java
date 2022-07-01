package Controller.ControlCustomer;

import DataStorage.DataSource;
import DataStorage.DataSourceService;
import Model.Customer.CustomerAccountModel;
import Payment.PaymentOperation;
import View.Customer.CustomerAccountViewService;
import View.Customer.CustomerMenuViewService;
import View.Customer.PrintCustomerInfoViewService;

public class CustomerAccountController implements CustomerAccountControllerService{
    private final CustomerAccountModel model;
    private final CustomerAccountViewService view;
    private final CustomerMenuViewService customerMenuView;
    private final PrintCustomerInfoViewService printCustomerInfoView;
    private final static DataSourceService dataSource= DataSource.getInstance();
    public CustomerAccountController(CustomerAccountModel model, CustomerAccountViewService view,CustomerMenuViewService customerMenuView,PrintCustomerInfoViewService printCustomerInfoView){
        this.model=model;
        this.view=view;
        this.customerMenuView=customerMenuView;
        this.printCustomerInfoView=printCustomerInfoView;
    }
    public void setOtherInputs(){
        this.view.inputCustomerDetails(this);
        this.addCustomerToDataSource();
    }
    public void addCustomerToDataSource() {
        dataSource.addCustomer(this.model);
    }
    public void menu(){
        this.customerMenuView.setController(this);
        this.customerMenuView.customerMenu();
    }
    public void setName(String name){
        this.model.setName(name);
    }
    public String getName(){
        return this.model.getName();
    }
    public void setEmail(String email){
        this.model.setEmail(email);
    }
    public String getEmail(){
        return this.model.getEmail();
    }
    public void setMobileNum(long mobileNum){
        this.model.setMobileNum(mobileNum);
    }
    public long getMobileNum(){
        return this.model.getMobileNum();
    }
    public void setUserName(String userName){
        this.model.setUserName(userName);
    }
    public String getUserName(){
        return this.model.getUserName();
    }
    public void setPassword(String password){
        this.model.setPassword(password);
    }
    public String getPassword(){
        return this.model.getPassword();
    }
    public void setUserId(int id){
        this.model.setUserId(id);
    }
    public int getUserId(){
        return this.model.getUserId();
    }
    public void setShippingAddress(String address){
        this.model.setShippingAddress(address);
    }
    public String getShippingAddress(){
        return this.model.getShippingAddress();
    }
    public void payFineThroughCard(PaymentOperation payment, String cardNumber){
        payment.transferAmount(cardNumber);
    }
    public void payFineThroughGooglePay(PaymentOperation payment,String googlePayId){
        payment.transferAmount(googlePayId);
    }
    public void updateView(){
        printCustomerInfoView.printCustomerDetails(this.getUserId(),this.getName(),this.getEmail(),this.getMobileNum(),this.getShippingAddress());
    }
}
