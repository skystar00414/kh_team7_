package post_It.Server;

public class User_Server { 

    private String ID;
    private String PW;
    //private String name;
    //private String 생년월일 등등;
    
    User_Server(){//기본 생성자

    }
    User_Server(String ID, String PW){//기본 생성자
        this.ID = ID;
        this.PW = PW;
    }

}
