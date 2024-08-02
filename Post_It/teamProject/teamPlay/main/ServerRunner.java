package teamPlay.main;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;


import teamPlay.controller.PostitController;
import teamPlay.controller.PrintController;
import teamPlay.controller.PrivacyController;
import teamPlay.model.vo.PrivacyVO;

public class ServerRunner {

	private Scanner scan = new Scanner(System.in);
	private PrivacyController privacyController = new PrivacyController(scan);
	private PostitController postitController = new PostitController(scan);
	
	@SuppressWarnings("resource")
	public void serverRun() {

		final int PORT = 9001;
		ServerSocket server = null;
		Socket socket = null;

		ObjectInputStream ois = null;
		InputStream is = null;
		
		ObjectOutputStream oos = null;
		OutputStream os = null;

		try {
			server = new ServerSocket(PORT);

			while (true) {

				System.out.println("[Waiting . . .]");
				socket = server.accept();
				System.out.println("[" + socket.getInetAddress() + " : User Connected]");
				
				// 인풋스트림 (받아오는것)
				is = socket.getInputStream();
				ois = new ObjectInputStream(is); //이걸 사용하세용
				
				// 아웃풋스트림 (건내주는것)
				os = socket.getOutputStream();
				oos = new ObjectOutputStream(os); //이걸 사용하세용
				
				//받을 때 까지 기다립니다.
				String order = ois.readUTF(); // 요청 사항
				String input_String = ois.readUTF(); // 데이터 (String 값을 받습니다.)
				// cutter @@
				
				
				
				order = order.trim(); // 클라에서 String 값을 2개 줍니다 하나는 오더 하나는 데이터 입니다.

				switch (order) {
				case "$login":
					// 로그인 privacyVO 데이터 넘어옴
					PrivacyVO privacy = privacyController.loginUser(input_String);
					if (privacy != null) { //로그인 성공
						String time =  PrintController.printNowDayandTime();
						oos.writeUTF(privacy.getPr_id()+"@@"+time); //로그인 정보 (아이디) 를 보내주어야 합니다.
						privacyController.succeedLogin(privacy.getPr_id());
					} else {
						String failid = checkID(input_String);
						String arr[] = failid.split("@@");
						
						if (arr[1].equals("@@badID")) {
							privacyController.failedid(arr[0]);
						}
						
						oos.writeUTF("$spread@@wrong Login");
					}
					
					break;
				case "$join":
					if (privacyController.joinUser(input_String)) {
						oos.writeUTF("$spread@@Join success");
					} else {
						oos.writeUTF("$spread@@Join fail");
					}
					break;
				case "$borderListSelect":
					
					oos.writeUTF("borderList");// 임시
					break;
					
				case "$categoryListSelect":
					
					oos.writeUTF("categoryList");// 임시
					break;
					
				case "$postInsert":
					postitController.writePostIt(input_String);
					break;
				case "$postUpdate":
					break;
				case "$postDelete":
					break;
				case "$checkUser": // 로그인이 된 사람인가?
					oos.writeUTF(checkID(input_String));
					break;
				case "$postRead":
					break;
				case "$postGood":
					break;
				case "$postBad":
					break;
				case "$commentInsert":
					break;
				case "$commentDelete":
					break;
					
				} // switch done
				
				
				ois.close();
				is.close();
				socket.close();
				System.out.println("[DisConnected]");
			}
		} catch (Exception e) {
			e.printStackTrace();
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

	private String checkID(String input_String) {
		String idChecker[] = input_String.split("@@");
		Boolean checkid = privacyController.checkUser(idChecker[0]);
		if (checkid) {
			return idChecker[0]+"@@goodID";
		} else {
			return idChecker[0]+"@@badID";
		}
		
	}

	

}