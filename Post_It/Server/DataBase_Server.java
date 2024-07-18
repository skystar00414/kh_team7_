package post_It.Server;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataBase_Server implements Serializable {

    private static final long serialVersionUID = 4890831236169L;

   List<User_Server> Userlist;
    //save();

    //load();

    public List<User_Server> getUserlist() {
        return Userlist;
    }

    public void setUserlist(List<User_Server> userlist) {
        Userlist = userlist;
    }

    @SuppressWarnings({ "resource", "unchecked" })
    public void get_User_Data_To_File(){
        //

        String file = "User_DB.txt";//파일주소
        List<User_Server> list = null;

        FileInputStream fis = null;
        ObjectInputStream ois = null;


        try {
            list = new ArrayList<User_Server>();//초기화, 형식 정해주기
            fis = new FileInputStream(file);//파일주소에서 파일 가져오기
            ois = new ObjectInputStream(fis);
            
            list = (List<User_Server>) ois.readObject();//불러오기(오브젝트 읽기)
            System.out.println("[List load ... OK]");
            
            Userlist = list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                //닫아주기
                ois.close();
                fis.close();
                // return list; // 리스트 리턴해주기 + 만약 사용 한다면
            } catch (Exception e) {
                e.printStackTrace();
            }


        }

    }//get_User_Data done

    //set_User_Date_To_File() do

    
    //check_User_Date(String id, Stirng pw) return number 
}
