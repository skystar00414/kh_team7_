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

        return "";
    }

    void login() throws IOException {
        System.out.println("[로그인]");
        System.out.print("*아이디 입력 : ");
        ID = bf.readLine();
        System.out.print("*비밀번호 입력 : ");
        PW = bf.readLine();

        int deny_int = deny(5 ,ID, PW);
        if (deny_int!=0) return;

    }

    void signin() throws IOException {
        System.out.println("[회원가입]");
        System.out.print("*아이디 입력 : ");
        ID = bf.readLine();
        System.out.print("*비밀번호 입력 : ");
        PW = bf.readLine();

        int deny_int = deny(1, ID, PW);
        if (deny_int!=0) return;
        
        //User생성 > list에 추가
        User user = new User();
        user.signin(ID, PW);
        list.add(user);

        //list 저장
        DataBase db = new DataBase();
        db.save("Login.txt", list);
        
    }

    int deny(int a, String ID, String PW){//예외걸러주는 쪽
        DataBase db = new DataBase();
        list = (List<User>)db.load("Login.txt"); //유저 db 불러오기 //이게 미완
        return login_Err(a); //1~4 이용
    }

    int login_Err(int a){
        //1 중복된 아이디, 2 예외문자가 포함된 아이디 3입력값이 없는아이디, 4. 너무 길거나 짧은 아이디 >> 회원가입 쪽
        //5 없는 아이디. 6. 틀린 비밀번호. 
        switch (a) {
            case 1:
            if (ID.equals("") || PW.equals("")) {
                System.out.println("입력값이 없습니다.");
                return a;
            }
            case 2:
            System.out.println("예외 문자가 포함된 아이디 입니다.");   //미구현
            
            case 3:
            if (sameID() == 1) {
                System.out.println("중복된 아이디 입니다.");
                return a;
            }
            
            case 4:
            if (ID.length() < 8 || 14 < ID.length()) {
                System.out.println("아이디가 너무 길거나 짧습니다. (8~13자리)");
                return a;
            }
            if (PW.length() < 8 || 14 < PW.length()) {
                System.out.println("비밀번호가 너무 길거나 짧습니다. (8~13자리)");
                return a;
            }
            
            
            


            return 0;//1~4까지 회원가입에 넣어서 만들어주자 
            //로그인 쪽 a 를 5로 집어 넣어서 2개만 채크 해주면 되고 
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
            
            //모두 확인했으면 이상없음 0 리턴
            default:
            return 0;
        }
        

    }

    int sameID(){//중복시 1
        for (int i = 0; i < list.size(); i++) {

            if(list.get(i).ID.equals(ID)){
                return 1;
            }

        }
        return 0;
    }

    int samePW(){//위에 아이디가 맞다는 가정하에 아이디는 참이니까 해당 아이디의 PW도 같은지 확인
        for (int i = 0; i < list.size(); i++) {

            if(list.get(i).ID.equals(ID) && list.get(i).passward.equals(PW)){
                return 1;
            }

        }
        return 0;
    }

}
