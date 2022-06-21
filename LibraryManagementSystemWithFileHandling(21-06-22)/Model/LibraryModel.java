package Model;

import DataStorage.DataSource;
import DataStorage.DataSourceService;
public class LibraryModel {
    private static String phoneNum;
    private static int id=0;
    private final DataSourceService dataSource=DataSource.getInstance();
    public void setPhoneNum(){
        phoneNum="044-2645-2201";
    }
    public String getPhoneNum(){
        return phoneNum;
    }
    public int getBookIdForBook(){
        if(dataSource.getLastBookId()!=0) {
            return dataSource.getLastBookId();
        }
        else {
            return id++;
        }
    }
}
