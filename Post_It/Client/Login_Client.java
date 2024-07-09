package post_It.Client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Login_Client {

    void get_User_DataBase() {

        Socket_Client getServer = new Socket_Client();

        // list = (list<User_Client>)getServer.Load_User_List("User_DB.txt"); //서버에서 가지고
        // 와야해용 리스트는 아래있어용, 아이디는 중복하면 안됨

    }

    List<User_Client> list = new ArrayList<User_Client>();
    Scanner sc = new Scanner(System.in);
    String ID = "iv", ID_TMP = "";// iv = 초기값 (initial value)
    String PW = "iv", PW_TMP = "";// iv = 초기값 (initial value)
    String RANDOM_;// 아이디 뒤에 구분기호 (, or_ or -)후에 붙여주세용 시간으로 해도됨 >>로그인 기간을설정 ex 접속하면 1시간정도 후에
                   // 로그아웃되도록 하기위해서? 시간붙여줘도 될듯
    

    String init() {// join

        get_User_DataBase(); // list 값 받아와주기

        while (true) {
            try {
                System.out.println("1. login\n2. sign up\n3. end");// menu

                String M = "0";
                Integer.parseInt(sc.nextLine());
                

                if (M.equals("1"))
                    login();
                else if (M.equals("2"))
                    signUp();
                else if (M.equals("3"))
                    break;

                if (!ID.equals("iv")) {
                    return ID;
                }

            } catch (Exception e) {
                System.out.println("잘못된 입력입니다.");
                e.printStackTrace();
            }

        }

        return "Err";
    }// init done

    void login() throws IOException {
        System.out.println("[로그인]");

        System.out.print("*아이디 입력 : ");
        ID_TMP = sc.nextLine();

        System.out.print("*비밀번호 입력 : ");
        PW_TMP = sc.nextLine();

        int deny_int = deny(4, ID_TMP, PW_TMP);

        if (deny_int != 0)
            return;

        ID = ID_TMP;
        PW = PW_TMP;

    }// login done

    void signUp() throws IOException {// 위에 ID PW 건드리지말고 TMP로 DB class 연결해서 LIST에 저장후 끝 // 확인은 LOGIN에서 확인하도록 부탁드립니다.

        System.out.println("[회원가입]");

        System.out.print("*아이디 입력 : ");
        ID_TMP = sc.nextLine();

        System.out.print("*비밀번호 입력 : ");
        PW_TMP = sc.nextLine();

        int deny_int = deny(1, ID_TMP, PW_TMP);

        if (deny_int != 0)
            return;

    }// signUp done

    int deny(int a, String ID_TMP, String PW_TMP) {
        // DB에서 리스트 가져오기

        return ERR_reason(a);
    }// deny done

    int ERR_reason(int a) {

        // 1 중복된 아이디, 2 예외문자가 포함된 아이디 3입력값이 없는아이디, 4. 너무 길거나 짧은 아이디 >> 회원가입 쪽
        // 5 없는 아이디. 6. 틀린 비밀번호.

        switch (a) {
            case 1:
                if (ID.equals("") || PW.equals("")) {
                    System.out.println("입력값이 없습니다.");
                    return 1;
                }
            case 2:
                System.out.println("예외 문자가 포함된 아이디 입니다."); // 미구현
            case 3:
                if (sameID() == 1) {
                    System.out.println("중복된 아이디 입니다.");
                    return 2;
                }
            case 4:
                if (ID.length() < 8 || 14 < ID.length()) {
                    System.out.println("아이디가 너무 길거나 짧습니다. (8~13자리)");
                    return 3;
                }

                if (PW.length() < 8 || 14 < PW.length()) {
                    System.out.println("비밀번호가 너무 길거나 짧습니다. (8~13자리)");
                    return 4;
                }

                return 0;// 1~4까지 회원가입에 넣어서 만들어주자

            // 로그인 쪽 a 를 5로 집어 넣어서 2개만 채크 해주면 되고
            case 5:
                if (ID.equals("") || sameID() == 0) {
                    System.out.println("없는 아이디 입니다.");
                    return 5;
                }

            case 6:
                if (samePW() == 0) {
                    System.out.println("틀린 비밀번호 입니다.");
                    return 6;
                }

                return 0;
        }

        return 0;

    }// ERR_reason done

    int sameID() {// 중복시 리턴 1

        for (int index = 0; index < list.size(); index++) {

            if (list.get(index).getId().equals(ID_TMP)) {
                return 1;
            }

        }

        return 0;

    }

    int samePW() {// 중복시 리턴 1

        for (int i = 0; i < list.size(); i++) {

            if (list.get(i).getId().equals(ID_TMP) && list.get(i).getPw().equals(PW_TMP)) {
                return 1;
            }
        }

        return 0;

    }

}
