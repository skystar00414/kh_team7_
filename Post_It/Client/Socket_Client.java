package post_It.Client;

import java.util.ArrayList;
import java.util.List;

public class Socket_Client { // Thread 만들어서 서버랑 통신해주세용 파일요구할 때 고때만 접속하도록

    int PORT;
    int IP;


    public Socket_Client() {

    }

    @SuppressWarnings("rawtypes")
    public List Load_User_List(String file) {
        List<User_Client> list = new ArrayList<User_Client>();
        return list;
    }

}
