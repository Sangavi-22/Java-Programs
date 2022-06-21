package Administator;
import Input.InputsFromUser;
public class Admin implements AdminService {
    private int id = 0;
    private static int idForStudent=1000,idForLibrarian=0;
    private int memberType;
    InputsFromUser inputsFromUser = new InputsFromUser();
    public int generateId() {
        memberType = inputsFromUser.inputMemberType();
        if (memberType == 1) {
            idForLibrarian++;
            id =idForLibrarian;
        } else if (memberType == 2) {
            idForStudent++;
            id =idForStudent;
        } else {
            id=0;
        }
        return id;
    }
}