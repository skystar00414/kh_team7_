package teamPlayClient.socketClient;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import teamPlayClient.Main.MainClient;
import teamPlayClient.controller.PrintClientController;



public class Client {

	private Scanner scan = new Scanner(System.in); //쓸거같지는 않음
	private int PORT = 9001;
	private String IP = "127.0.0.1";
		

	@SuppressWarnings("resource")
	public String ClientRun(String order, String data) {
		//$login , qwer1234@@qwer1234
		Socket socket = null;            //Server와 통신하기 위한 Socket
	    BufferedReader in = null;        //Server로부터 데이터를 읽어들이기 위한 입력스트림
	    BufferedReader inkeyboard = null;//키보드로부터 읽어들이기 위한 입력스트림
	    PrintWriter out = null;          //서버로 내보내기 위한 출력 스트림
	
		//서버에서 받아용!
		InputStream is = null;
		ObjectInputStream ois = null;// 이걸 사용해용
		
		//서버에 줘용!
		OutputStream os = null;
		ObjectOutputStream oos = null;// 이걸 사용해용
		
		try {
			System.out.println("1");
			socket = new Socket(IP, PORT); //서버 연결
			System.out.println("1-1");
			
//			is = socket.getInputStream();
//			ois = new ObjectInputStream(is);
//			
//			os = socket.getOutputStream();
//			oos = new ObjectOutputStream(os);

			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			inkeyboard = new BufferedReader(new InputStreamReader(System.in));
            out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
			
			System.out.println("2");
			System.out.println(order +"@@"+ data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			switch (order) {

			case "$login":
				System.out.println("3");
				//id@@pw
//				oos.writeUTF("$login");
				out.println("$login");
//				oos.writeUTF(data);
				out.println(data);
				out.flush();
				System.out.println(data);
				System.out.println("4");
//				String idToken = ois.readUTF();
				String idToken = in.readLine();
				//$spread@@wrongLogin < 실패시
				System.out.println(idToken);
				String arrLogin[] = idToken.split("@@");
								
				if (arrLogin[1].equals("wrongLogin")) {
					PrintClientController.printWrongMSG();
					return ""; // 불안함
				}
				
				MainClient mc = new MainClient();
				mc.setLoginToken(idToken);
				return idToken;// 불안함
			
			case "$join":
				//
				System.out.println("join debug 1");
//				oos.writeUTF(data);
				out.println("$join");
				out.println(data);
				out.flush();
				System.out.println("join debug 2");
//				String joinToken = ois.readUTF();
				String joinToken = in.readLine();
				
				//
				System.out.println("join debug 3");
				String arrJoin[] = joinToken.split("@@");
				if (arrJoin[1].equals("Joinsuccess")) {
					System.out.println("[회원가입 성공]");
				} else {
					System.out.println("[회원가입 실패]");
				}
				
				break;
				
			case "$borderListSelect":
//				oos.writeUTF(data);
				out.println(data);
				
				
				break;
				
			default:
				break;
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			try {
//                oos.close();
//                ois.close();
//                is.close();
//                os.close();
                socket.close();
               
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		
		
		
		}
		
		return null;
	}	
	
	
	
}
