package post_It.Server;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataBase_Server implements Serializable {

    private static final long serialVersionUID = 4890831236169L;

    
    //save();

    //load();

    @SuppressWarnings({ "resource", "unchecked" })
    public void get_User_Data(){
        
        String file = "User_DB.txt";
        List<User_Server> list = null;
        


        FileInputStream fis = null;
        ObjectInputStream ois = null;


        try {
            list = new ArrayList<User_Server>();//초기화
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);
            
            list = (List<User_Server>) ois.readObject();//불러오기
            System.out.println("[List load ... OK]");
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //닫아주기
                ois.close();
                fis.close();
                // return list; // 리스트 리턴해주기
            } catch (Exception e) {
                e.printStackTrace();
            }


        }


    }//get_User_Data done


}
