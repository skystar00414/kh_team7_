package post_It.Client;

import java.io.Serializable;

public class User_Client implements Serializable {

    private static final long serialVersionUID = 920806160129L;

    private String id;
    private String pw;

    public User_Client(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }

    public User_Client() {
        
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPw() {
        return pw;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

}
