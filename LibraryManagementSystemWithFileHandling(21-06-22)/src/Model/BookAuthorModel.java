package Model;

import java.util.ArrayList;

public class BookAuthorModel {
    private final ArrayList<String> authorsNames=new java.util.ArrayList<>();
    public void addAuthorName(String authorName){
        this.authorsNames.add(authorName);
    }
    public ArrayList<String> getAuthorsName(){
        return this.authorsNames;
    }

}
