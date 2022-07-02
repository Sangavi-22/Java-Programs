package FileOperations;

import Model.Customer.CustomerAccountModel;
import java.io.*;
import java.util.HashMap;

public class CustomerDetailsFileHandler implements CustomerDetailsFileHandlerService {
    File CustomerDetails= new File("/Users/sangavi-pt5468/Desktop/ShoppingManagementSystem/CustomerDetails.txt");
    File CustomerDetailsTemp= new File("/Users/sangavi-pt5468/Desktop/ShoppingManagementSystem/CustomerDetailsTemp.txt");
    public void writeCustomerDetails(int userId,String userName,String password,String name,String email,long mobileNum, String address){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(CustomerDetails,true));
            bw.write("CustomerId:"+userId+", userName:"+userName+", password:"+password+" ,name:"+name+", email:"+email+", mobileNum:"+mobileNum+", address:" +
                    address+"\n");
            bw.flush();
            bw.close();
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    public HashMap<String, CustomerAccountModel> readCustomers(){
        HashMap<String, CustomerAccountModel>customers= new HashMap<>();
        String sentence;
        try{
            String[] words;
            BufferedReader br=new BufferedReader(new FileReader(CustomerDetails));
            while(br.ready()) {
                sentence=br.readLine();
                words=sentence.split(", ");
                CustomerAccountModel customerAccountModel=new CustomerAccountModel();
                customerAccountModel.setUserId(Integer.parseInt(words[0].split(":")[1]));
                customerAccountModel.setUserName(words[1].split(":")[1]);
                customerAccountModel.setPassword(words[2].split(":")[1]);
                customerAccountModel.setName(words[3].split(":")[1]);
                customerAccountModel.setEmail(words[4].split(":")[1]);
                customerAccountModel.setMobileNum(Long.parseLong(words[5].split(":")[1]));
                customerAccountModel.setShippingAddress(words[6].split(":")[1]);
                customers.put(customerAccountModel.getUserName(),customerAccountModel);
            }
            br.close();
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            return customers;
        }
    }
    public int readLastCustomerId(){
        String sentence;
        int id=0;
        try{
            String[] words={};
            BufferedReader br=new BufferedReader(new FileReader(CustomerDetails));
            while(br.ready()) {
                sentence=br.readLine();
                words=sentence.split(", ");
            }
            id=Integer.parseInt(words[0].split(":")[1]);
            br.close();
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
        finally {
            return id;
        }
    }
    public void removeCustomerDetailsFromFile(String userName){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(CustomerDetails));
            BufferedWriter writer = new BufferedWriter(new FileWriter(CustomerDetailsTemp,true));
            String lineToRemove=", userName:"+userName;
            String currentLine;
            while((currentLine = reader.readLine()) != null) {
                String trimmedLine = currentLine.trim();
                if(trimmedLine.contains(lineToRemove))
                    continue;
                writer.write(currentLine + System.getProperty("line.separator"));
            }
            writer.flush();
            writer.close();
            reader.close();
            CustomerDetailsTemp.renameTo(CustomerDetails);
            PrintWriter wr = new PrintWriter(CustomerDetailsTemp);
            wr.print("");
            wr.flush();
            wr.close();
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
