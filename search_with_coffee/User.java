package search_with_coffee;


public class User extends DataBase {

    String ID;
    String passward;

    public User(String ID, String PW) {
        
    }
    public User(){


    }
    void signin(String ID, String passward){
        this.ID = ID;
        this.passward = passward;
    }

}
