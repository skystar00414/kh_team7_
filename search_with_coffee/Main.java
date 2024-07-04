package search_with_coffee;

public class Main {

    public static void main(String[] args) {

        Login lg = new Login();

        String login_Success_Take_ID = lg.init();

        if (login_Success_Take_ID.equals("")) System.exit(0);

        Front_Com fc = new Front_Com();

        fc.init(login_Success_Take_ID);

        System.out.println("Exit");

    }
}
