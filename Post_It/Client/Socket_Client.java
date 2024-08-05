package post_It.Client;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Socket_Client { // Thread 만들어서 서버랑 통신해주세용 파일요구할 때 고때만 접속하도록

    int PORT = 9000;
    String IP = "127.0.0.1";

    public void ClientRun(int a, String order ,String data){
        //int a는 인풋, 아웃풋 여부, date는 만약 보낸다면, 보낼 data or 받을 data
        //order는 서버에게 줄 명령어가 담겨져있음
        // login / join / writing / delete / refresh / catch

        /**
         * 즉 in out이 되려면
         * 로그인 일때
         * Socket_Client sc = new Socket_Client();
         * ClientRun(1, "login", "id,pw") ;
         * ClientRun(2, "", "") ;
         * 연속으로 하거나 아니면 여기 로그인 메소드에 
         * 스스로를 불러주거나 해주세용
         * 
         */

        Socket socket = null;


        //출력
        ObjectOutputStream oos = null;
        OutputStream os = null;

        //입력
        ObjectInputStream ois = null;
        InputStream is = null;
        
        
        try {
            
            socket = new Socket(IP, PORT); //소켓 연결
            
            //서버 리턴 
            is = socket.getInputStream();
            ois = new ObjectInputStream(is);

            //서버로 전송
            os = socket.getOutputStream();
            oos = new ObjectOutputStream(os);


            
            
            
            
            
            /**
             * 회원가입, 로그인은
             * cutter : , 사용하고
             * 나머지는 
             * cutter : :$$: 사용합니다.
             */
            
            switch (a) {
                case 1://인풋(수신) data가 값이 없어야함
                order = ois.readUTF(); //if 로그인 한 후 값을 리턴 받을 때 : return_login
                data = ois.readUTF();//String 값임
                
                if (order.equals("return_login")) {
                    //리턴 로그인 이면





                }



                    break;
                case 2://아웃풋(송신)
                oos.writeObject(order);
                oos.writeObject(data);









                
                    break;
            
                default:
                    break;
            }



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                oos.close();
                ois.close();
                is.close();
                os.close();
                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        




    }
}
