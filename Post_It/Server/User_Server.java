package post_It.Server;

public class User_Server { 

    private String ID;
    private String PW;
    //private String name;
    //private String email;
    //private String 생일, 가입일자 등등;
    
    public User_Server(){//기본 생성자

    }
    public User_Server(String ID, String PW){//생성자
        this.ID = ID;
        this.PW = PW;
    }


    //getter, setter
    public String getID() {
        return ID;
    }
    public void setID(String iD) {
        ID = iD;
    }
    public String getPW() {
        return PW;
    }
    public void setPW(String pW) {
        PW = pW;
    }

}
