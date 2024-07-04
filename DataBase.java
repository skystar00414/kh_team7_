package search_with_coffee;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class DataBase implements Serializable {
    private static final long serialVersionUID = 123546820763181265L;

    

    // String File = ""; // 파일이름은 받아오는 걸로 하고 오브잭트를 받으면 해당 오브잭트를 저장하게 하면 됨
    //다양한 객체가 온다 생각하고 다양한 save_list, save_file, save_User... 로 만들어도 될듯 싶음
    //작성 다시하셈 다 지우고

    void DateBase() {

    }

    public void save(String file, Object obj1) {
        try (FileOutputStream fos = new FileOutputStream(file);
                ObjectOutputStream oos = new ObjectOutputStream(fos);) {
                    oos.writeObject(obj1);
        } catch (Exception e) {
            System.out.println("[Save ERR]");
        }
    }

    public Object load(String file) {
        try (FileInputStream fis = new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);){
                return ois.readObject();
        } catch (Exception e) {
            System.out.println("[Load ERR]");
            return null;
        }
    }

}
