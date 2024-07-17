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
                socket = server.accept();
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
                    case "another":// 미정

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

    private void post_board_delete(Socket socket, String data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'post_board_delete'");
    }

    private void post_board_refresh(Socket socket, String data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'post_board_refresh'");
    }

    private void post_board_writing(Socket socket, String data) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'post_board_writing'");
    }

    private void join(Socket socket, String data) {

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
         * 2 : pw check >> 미완
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