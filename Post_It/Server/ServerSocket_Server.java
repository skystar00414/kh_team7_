package post_It.Server;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerSocket_Server{

    final int PORT = 9090;
    List<Socket> list = new ArrayList<>();
    ServerSocket serverSocket = null;
    Socket socket = null;

    void init(){
        try {
            //서버용 소켓 객체 하나 생성 
            serverSocket = new ServerSocket(PORT);
            //정보를 받아줄 스트림 2개 많들어 줬음

            InputStream is = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);
            
            //
            // 예시
            while (true) { 
                //접속 무한 대기
                System.out.println("[waiting...]");
                //접속시 진행
                socket = serverSocket.accept();
                //ip 화면에 뿌려주기
                InetSocketAddress isa = (InetSocketAddress) socket.getRemoteSocketAddress();
                System.out.println("[connected : " + isa.getHostName() + "]");

                //상대방에게서 명령할 숫자(byte or int), ID, PW, 올듯
                //byte가 무엇인지 확인하고 switch 만들어가지고 거기다 넣어주면 될...듯?
                int receive = ois.read();
                
                getOrder(receive); // 여기다 숫자 넣어서 원하는 명령을 해주면 됨 ex 0 회원가입 1로그인 2게시글 작성...
                
                
            }

  /*        Thread loginThread = new Thread(()->{
                try {
                    ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
                    //접속시 아이디, 비밀번호를  받아줘야 함
                    //아이디를db쪽에서 파일리더 가지고 와서 확인해주고
                    //옳으면 ok사인으로 int 1 리턴 해주면 됨
                    //그리고 로그인토큰 주면됨
                    String login_token = ois.readUTF();
                    //Login lg = new Login();
                    //lg.ERR_reason(4);
                    //if(lg == 0) >> 로크인 오케이 토큰 가져가용
                    //if( lg != 0) >> a 반환 login_CLinet에 원인 있음
                    System.out.println("로그인토큰 예시");
                    System.out.println("[ID, 날짜, 권한(어드민 2, 일반 1, 게스트0반환)]");
                    //로그인 틀리면 syso(a); << 로그인 class보면 a값 반환 하는 곳있음 ERR_reason
                    //접속 성공하면 Thread 용 list 만들어 넣어주면 되나봐용
                } catch (Exception e) {
                    
                }








            });
            loginThread.start();
        */       

        } catch (Exception e) {
            // TODO: handle exception
        }
        
        
    }//init done

    void getOrder(int order){

    }




}