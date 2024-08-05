package teamPlayClient.socketClient;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;



public class Client {
	
	private Scanner scan = new Scanner(System.in); //쓸거같지는 않음
	private int PORT = 9001;
	private String IP = "127.0.0.1";
		
	
	
	@SuppressWarnings("resource")
	public String ClientRun(String order, String data) {
		
		Socket socket = null;
		
		//서버에서 받아용!
		InputStream is = null;
		ObjectInputStream ois = null;// 이걸 사용해용
		
		//서버에 줘용!
		OutputStream os = null;
		ObjectOutputStream oos = null;// 이걸 사용해용
		
		try {
			
			socket = new Socket(IP, PORT); //서버 연결
			
			// 인풋!
			is = socket.getInputStream();
			ois = new ObjectInputStream(is);
			
			// 아웃풋!
			os = socket.getOutputStream();
			oos = new ObjectOutputStream(os);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			switch (order) {
			case "$login":
				//id@@pw
				oos.writeUTF(data);
				
				String idToken = ois.readUTF();
				//$spread@@wrongLogin < 실패시
				String arrLogin[] = idToken.split("@@");
								
				if (arrLogin[1].equals("wrongLogin")) {
					return ""; // 불안함
				}
				
				return idToken;// 불안함
			
			case "$join":
				//
				oos.writeUTF(data);
				
				String joinToken = ois.readUTF();
				
				//
				String arrJoin[] = joinToken.split("@@");
				if (arrJoin[1].equals("Joinsuccess")) {
					System.out.println("[회원가입 성공]");
				} else {
					System.out.println("[회원가입 실패]");
				}
				
				break;
				
			case "$borderListSelect":
				oos.writeUTF(data);
				
				
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
		
		return null;
		
	}	
	
	
	
}
