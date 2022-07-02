package FileOperations;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class BillAmountFileHandler implements BillAmountFileHandlerService{
    File BillAmountFile=new File("/Users/sangavi-pt5468/Desktop/ShoppingManagementSystem/BillAmountFile.txt");
    public void writeBillAmount(int orderId,int billAmount) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(BillAmountFile,true));
            bw.write("orderId:"+orderId+", billAmount:"+billAmount+"\n");
            bw.flush();
            bw.close();

        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
