package search_with_coffee;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Login {
    BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    List<User> list = new ArrayList<User>();
    String ID = "";
    String PW = "";

    public String init() {

        while (true) {

            try {
                System.out.println("1. Login\n2. Signin\n3. Exit");
                String M = bf.readLine();

                if (M.equals("1")) {
                    login();

                }

                else if (M.equals("2")) {
                    signin();
                }

                else if (M.equals("3"))
                    break;
            } catch (IOException e) {
                System.out.println("정상적인 입력이 아닙니다.");
                e.printStackTrace();
            }

        }

        return ID;
    }

    void login() throws IOException {
        System.out.println("[로그인]");
        System.out.print("*아이디 입력 : ");
        ID = bf.readLine();
        System.out.print("*비밀번호 입력 : ");
        PW = bf.readLine();

        int deny = deny(ID, PW);
        if (deny!=0) return;

        if (ID.equals(list.get(deny))) {
            
        }
    }

    void signin() throws IOException {
        System.out.println("[회원가입]");
        System.out.print("*아이디 입력 : ");
        ID = bf.readLine();
        System.out.print("*비밀번호 입력 : ");
        PW = bf.readLine();

        int deny = deny(ID, PW);
        if (deny!=0) return;
        
        //User생성 > list에 추가
        User user = new User();
        user.signin(ID, PW);
        list.add(user);

        //list 저장
        DataBase db = new DataBase();
        db.save("Login.txt", list);
        
    }

    int deny(String ID, String PW){//예외걸러주는 쪽
        int a = 0;
        DataBase db = new DataBase();
        list = (List<User>)db.load("Login.txt");
        //미완

        return login_Err(a);
    }

    int login_Err(int a){
        //1 중복된 아이디, 2 예외문자가 포함된 아이디 3입력값이 없는아이디, 4. 너무 길거나 짧은 아이디 >> 회원가입 쪽
        //5 없는 아이디. 6. 틀린 비밀번호. 
        switch (a) {
            case 1:
            System.out.println("입력값이 없습니다.");
            return a;
            case 2:
            System.out.println("예외 문자가 포함된 아이디 입니다.");
            return a;
            case 3:
            sameID();
            System.out.println("중복된 아이디 입니다.");
            return a;
            case 4:
            System.out.println("너무 길거나 짧은 아이디 입니다.");
            return a;
            //로그인 쪽
            case 5:
            System.out.println("없는 아이디 입니다.");
            return a;
            case 6:
            System.out.println("틀린 비밀번호 입니다.");
            return a;
            default:
        }
        return 0;

    }

    int sameID(){
        for (int i = 0; i < list.size(); i++) {

            if(list.get(i).ID.equals(ID)){
                return 1;
            }

        }
        return 0;
    }

}
