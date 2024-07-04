package search_with_coffee;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class DataBase implements Serializable {
    private static final long serialVersionUID = 123546820763181265L;

    

    // String File = "";

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
