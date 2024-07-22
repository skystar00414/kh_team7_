package post_It.Server;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class ServerSocket_Server {

    // ServerSocket(int port) : 지정된 포트 번호에 바인딩(고정된) 소켓 만듬 (연결 대기열 최대 50)
    // ServerSocket(int port, int backlog): 지정된 포트 번호에 바인드 된 서버 소켓을 만들고 대기중인 최대 연결
    // 수를 backlog 매개변수로 지정
    // (*필요한 내용) = 클라에서 제작해야할 부분이 있을 수 있음
    // 참고예제
    // https://hunit.tistory.com/256?source=post_page-----d5b5a27a50a0--------------------------------
    List<User_Server> list;

    public void serverRun() {

        int PORT = 9000;
        ServerSocket server = null;
        Socket socket = null;

        ObjectInputStream ois = null;
        InputStream is = null;
        // socket.getInputStream();
        // new ObjectInputStream(is);

        try {
            
            server = new ServerSocket(PORT);

            while (true) {

                // 접속대기 & 접속 후 연결 확인 메시지
                System.out.println("[Server Waiting]");
                socket = server.accept();//대기해야함
                System.out.println("[" + socket.getInetAddress() + " : User Connected]");

                // 클라이언트가 준 값 받을 준비
                is = socket.getInputStream();
                ois = new ObjectInputStream(is);

                // 값을 받아줌
                String client_order = ois.readUTF(); // 명령어 get
                String data = ois.readUTF(); // 값이 담겨져 있음

                // 값을 분류해줌
                switch (client_order) {
                    case "login":
                        login(socket, data);
                        break;
                    case "join":
                        join(socket, data);
                        break;
                    case "writing":
                        post_board_writing(socket, data);// 글쓰기
                        break;
                    case "delete":
                        post_board_delete(socket, data);// 글 삭제
                        break;
                    case "refresh":
                        post_board_refresh(socket, data);// 새로고침
                        break;
                    case "catch":
                        post_board_catch(socket, data);//게시글 내용 보기
                        break;
                }
                ois.close();
                is.close();
                socket.close();
            }
        } catch (Exception e) {

        } finally {
            try {
                ois.close();
                is.close();
                server.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void post_board_catch(Socket socket, String data) {

    }

    private void post_board_delete(Socket socket, String data) {

    }

    private void post_board_refresh(Socket socket, String data) {

    }

    private void post_board_writing(Socket socket, String data) {

    }

    //오류체크안해봄 + 완료
    private void join(Socket socket, String data) {

        // data에 대한 형식은 클라이언트에서 확인하고 가져와야합니다
        // data = [아이디,비밀번호,이메일,이름,생년월일] 형태로 받아오자 + 혹은가입일자?(*)
        String arr[] = data.split(",");
        String input_id = arr[0];
        String input_pw = arr[1];

        //아래 3개는 일단 사용하지 않습니다.
        String input_email = arr[2];
        String input_name = arr[3];
        String input_birthday = arr[4];

        // get 유저 정보
        DataBase_Server ds = new DataBase_Server();//create db object
        ds.get_User_Data_To_File();//load
        list = ds.getUserlist();//get

        int flag = 0;
        //회원가입이니까 중복되는 아이디가 있는지 확인 + 이상이 있으면 flag = 1
        // 0 = go(이상없음) / 1 = stop(중복)
        flag = check_User_Date(1, input_id, input_pw); //id 중복확인

        if (flag == 0) {//0 즉 중복이 아니면
            //User 객체하나 제작 + list에 추가
            list.add(new User_Server(input_id, input_pw));

            //ds.setUserlist(list); //list 를 디비에 저장
            ds.setUserlist(list);

            // ds.set_User_Date_To_File(); //list를 file로 저장
            ds.set_User_Date_To_File();


        }
        //null 대신 초기값 N + 아이디 중복이 아닌 오류 체크
        //클라에서는 '오류입니다. 다시한번 시도해 주세요.' 문구를 띄워주자
        String output_join_result = "N"; 

        if (flag == 1) {
            output_join_result = "F"; //아이디 중복으로 인한 실패시
        } else if (flag == 0) {
            output_join_result = "T"; //아이디 이상없음
        }

        // 파일 보낼 준비 (*회원가입 결과만)
        OutputStream os = null;
        ObjectOutputStream oos = null;

        // socket.getOutputStream();
        // new ObjectOutputStream(os);

        try {

            os = socket.getOutputStream();
            oos = new ObjectOutputStream(os);
            //N : 오류, F : 아이디 중복 T : 회원가입 성공
            oos.writeObject(output_join_result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
                os.close();
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        

        // ds.set_User_Date_To_File(); //list를 file로 저장
    }

    private void login(Socket socket, String data) {

        // data = [아이디,비밀번호] 형태로 받아오자
        String arr[] = data.split(",");
        String input_id = arr[0];
        String input_pw = arr[1];

        // get 유저 정보
        DataBase_Server ds = new DataBase_Server();
        ds.get_User_Data_To_File();// 파일 가져왔어용
        list = ds.getUserlist();// db에서 가져왔어용

        int login_result = 0;
        // 정보 확인 + 로그인 클레스를 만드는게 좋을까용?
        switch (login_result) {
            case 1:
                login_result++;
                if (input_id.equals("") || check_User_Date(1, input_id, input_pw) == 0) {
                    System.out.println("없는 아이디");
                    break;
                }
            case 2:
                login_result++;
                if (input_pw.equals("") || check_User_Date(0, input_id, input_pw) == 0) {
                    System.out.println("없는 비밀번호");
                    break;
                }
            default:
                break;
        }
        
        String output_login_result = login_result + "";

        // 파일 보낼 준비 (*로그인 결과만)
        OutputStream os = null;
        ObjectOutputStream oos = null;

        // socket.getOutputStream();
        // new ObjectOutputStream(os);

        try {
            os = socket.getOutputStream();
            oos = new ObjectOutputStream(os);

            //로그인결과를 String 값으로 전달
            oos.writeObject(output_login_result);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
                os.close();
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int check_User_Date(int a, String id, String pw){
        /**
         * 0 : id, pw check
         * 1 : id check
         * 2 : pw check // 미완
         */
        if (a==0) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).getID().equals(id) && list.get(i).getPW().equals(pw)) {
                    return 1;//id, pw가 동일하다면
                }
            }
        }

        if (a==1) {
            for (int index = 0; index < list.size(); index++) {
    
                if (list.get(index).getID().equals(id)) {
                    return 1;//동일하다면, 즉 있는 아이디라면
                }
            }
        }



        return 0;//없으면
    }

}