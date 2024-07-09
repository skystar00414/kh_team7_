package post_It.Client;

public class Main_Cilent {

    public static void main(String[] args) {

        

        Login_Client LC = new Login_Client();

        String ID = LC.init();
        
        if (ID.equals("Err")) {
            System.out.println("아이디 오류");
            System.exit(0);
        }

        
        
        System.out.println("end");
    }
}
