package post_It.Server;


import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerSocket_Server{

    //ServerSocket(int port) : 지정된 포트 번호에 바인딩(고정된) 소켓 만듬 (연결 대기열 최대 50)
    //ServerSocket(int port, int backlog): 지정된 포트 번호에 바인드 된 서버 소켓을 만들고 대기중인 최대 연결 수를 backlog 매개변수로 지정
    //참고예제
    //https://hunit.tistory.com/256?source=post_page-----d5b5a27a50a0--------------------------------

    public void serverRun(){

        int PORT = 9000;
        ServerSocket server = null;
        Socket socket = null;
    
        ObjectInputStream ois = null;
        InputStream is = null;
        //socket.getInputStream();
        //new ObjectInputStream(is);
        


        try {
            server = new ServerSocket(PORT);
            
            while (true) {

                //접속대기 & 접속 후 연결 확인 메시지
                System.out.println("[Server Waiting]");
                socket =  server.accept();
                System.out.println("["+socket.getInetAddress() + " : User Connected]");

                //클라이언트가 준 값 받을 준비
                is = socket.getInputStream();
                ois = new ObjectInputStream(is);

                //값을 받아줌 
                String client_order = ois.readUTF(); // 명령이 담겨져 있음 0 = 로그인 1 회원가입 2... 뭐 등등
                String data =  ois.readUTF(); // 값이 담겨져 있음

                int do_order = Integer.parseInt(client_order);
                //값을 분류해줌
                switch (do_order) {
                    case 1:
                        login(socket ,data);
                        break;
                
                    default:
                        break;
                }

            }
        } catch (Exception e) {
            
        }finally{
            try {
                ois.close();
                is.close();
                server.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    private void login(Socket socket, String data) {

        //파일 보낼 준비
		OutputStream os = null;
		ObjectOutputStream oos = null;

        // socket.getOutputStream();
        // new ObjectOutputStream(os);

        try {
            
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


    


}