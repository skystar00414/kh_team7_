package post_It.Server;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataBase_Server implements Serializable {

    private static final long serialVersionUID = 4890831236169L;

   List<User_Server> Userlist;
    // Userlist save(); done
    // Userlist load(); done
    
    List<Post> Postlist;
    // Postlist save(); todo
    // Postlist load(); todo 



    //list 들 getter, setter
    public List<User_Server> getUserlist() {
        return Userlist;
    }

    public List<Post> getPostlist() {
        return Postlist;
    }

    public void setPostlist(List<Post> postlist) {
        Postlist = postlist;
    }

    public void setUserlist(List<User_Server> userlist) {
        Userlist = userlist;
    }




    //Userlist 불러오기
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
            System.out.println("[User List load ... ok]");
            
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

    //파일저장기능 테스트 요구됨
    //Userlist 저장
    public void set_User_Date_To_File(){

        String file = "User_DB.txt";//파일주소
        
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);

            oos.writeObject(Userlist);
            System.out.println("[User List Save ... ok]");


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


    }// set_User_Date_To_File done
    

    
    //check_User_Date(String id, Stirng pw) return number 
    // 유저정보를 어드민이 수정관리가 필요할 때 제작
    // 1~n 번까지 쭈욱 읽어주고 번호 입력해서 수정해 주도록 제작하면 될 것 같습니다.
}
