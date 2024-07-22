package post_It.Server;

import java.util.Scanner;

public class Post_It_Server {

    Scanner sc = new Scanner(System.in);
    //nextLine만 사용해 주세요. nextInt 사용시 버퍼를 받아주어야 합니다.

    void init() {

        while (true) {//2, 3번 미구현
            System.out.println("1. 서버열기\n2. 유저 데이터 확인\n3. 권한 부여\n4. 종료");

            String M = sc.nextLine();

            if (M.equals("1")) {

                //서버소켓 객체 하나 제작
                ServerSocket_Server ss = new ServerSocket_Server();
                //서버 run
                ss.serverRun();

            } else if (M.equals("2")) {
                
            } else if (M.equals("3")) {

            } else if (M.equals("4")) {

                //종료
                System.out.println("[System Off]");
                System.exit(0);

            }

        }

    }

    void open_Server() {
        // run(); //ServerSocket_Server 에 Thread 만들면 run이 자동으로 만들어질것 혹은
        // 객체 하나 만들고 sss.thread.start();
    }

    void open_User_DB(){
        //db에서 유저 데이터 가져와서 화면상에 toString으로 뿌려주면 ok
    }

    // void set_User_permission //  과함 투머치함


}// Post_It_Server done
