package teamPlay.main;


import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

import teamPlay.controller.PostitController;
import teamPlay.controller.PrintController;
import teamPlay.controller.PrivacyController;
import teamPlay.model.vo.BorderVO;
import teamPlay.model.vo.CategoryVO;
import teamPlay.model.vo.Message;
import teamPlay.model.vo.PostitVO;
import teamPlay.model.vo.PrivacyVO;

public class ServerRunner {

	private Scanner scan = new Scanner(System.in);
	private PrivacyController privacyController = new PrivacyController(scan);
	private PostitController postitController = new PostitController(scan);
	
	public ServerRunner() {
		
	}	
	
	@SuppressWarnings("resource")
	public void serverRun() {
		
		

		final int PORT = 9001;
		ServerSocket server = null;
		Socket socket = null;

		ObjectOutputStream oos = null;
		OutputStream os = null;
		System.out.println("[ObjectOutputStream ... ready]");
		
		ObjectInputStream ois = null;
		InputStream is = null;
		System.out.println("[ObjectInputStream ... ready]");

		try {
			server = new ServerSocket(PORT);
		} catch (Exception  e) {
			System.out.println("[서버 이미 대기중]");
		}
		try {
			while (true) {

				System.out.println("[Client  Waiting . . .]");
				socket = server.accept();
				System.out.println("[" + socket.getInetAddress() + " : User Connected]");
				
				os = socket.getOutputStream();
				oos = new ObjectOutputStream(os); 
				System.out.println("[ObjectOntputStream ... ok]");
				
				is = socket.getInputStream();
				ois = new ObjectInputStream(is); 
				System.out.println("[ObjectInputStream ... ok]");

				Message orderMsg = (Message)ois.readObject();
				String order = orderMsg.getText();
				System.out.println("[" + order +" : get orderMsg ... ok]");
				
				Message inputMsg = (Message)ois.readObject();
				String input = inputMsg.getText();
				System.out.println("[" + input +" : get inputData ... ok]");

				// cutter @@

				order = order.trim();

				switch (order) {
				case "$login":
					//id@@pw
					// 로그인 privacyVO 데이터 넘어옴
					PrivacyVO privacy = privacyController.loginUser(input);
					System.out.println("[id check]");
					if (privacy != null) { //로그인 성공
						String time =  PrintController.printNowDayandTime();
						System.out.println("[Success Login]");
						
						Message idTokenMsg = new Message(privacy.getPr_id()+"@@"+time);
						oos.writeObject(idTokenMsg);
						System.out.println("["+idTokenMsg.getText()+"@@"+time+" : send ]");
						
						privacyController.succeedLogin(privacy.getPr_id());
						System.out.println("[fail token reset]");
					} else {
						String failid = checkID(input);
						String arr[] = failid.split("@@");
						System.out.println("[Fail Login]");
						
						if (arr[1].equals("@@badID")) {
							privacyController.failedid(arr[0]);
						}
						Message failLoginMsg = new Message("$spread@@wrongLogin");
						oos.writeObject(failLoginMsg);
						System.out.println("[fail msg send]");
					}
					System.out.println("[Login phase done]");
					break;
				case "$join":
					System.out.println("[join phase start]");
					if (privacyController.joinUser(input)) {
						System.out.println("[Success Join]");
						Message successJoinMsg = new Message("$spread@@Joinsuccess");
						oos.writeObject(successJoinMsg);
						System.out.println("[Sending : Success Join]");

					} else {
						System.out.println("[Fail Join]");
						Message failJoinMsg = new Message("$spread@@Joinsuccess");
						oos.writeObject(failJoinMsg);
						System.out.println("[Sending : Fail Join]");
					}
					System.out.println("[Join phase done]");
					break;
				case "$borderListSelect":
					System.out.println("[send border list phase start]");
					List<BorderVO> listBorderVO = null;
					listBorderVO = postitController.borderListOutput();
					System.out.println("[get border list to DB]");
					
					oos.writeObject(listBorderVO);
					System.out.println("[sending Border List]");
					
					System.out.println("[send border list phase done]");
					break;
					
				case "$categoryListSelect":
					System.out.println("[send category list phase start]");
					List<CategoryVO> categoryList = null;
					categoryList = postitController.categoryListOutput(input);
					System.out.println("[get Category list to DB]");
					
					oos.writeObject(categoryList);
					
					break;
				case "$showPostList"://전체 글 보기
					List<PostitVO> listPostVO = null;
					listPostVO = postitController.postListOutput(input); // page 받아줘야함
					oos.writeObject(listPostVO);
					break;
				case "$postDetail":
					System.out.println("[send Post Detail phase start]");
					int postN = Integer.parseInt(input);
					PostitVO post = postitController.postitDetail(postN);
					if (post == null) {
						Message postFail = new Message("postIsNull");
						oos.writeObject(post);
						System.out.println("[Post Detail is null]");
					} else {
						oos.writeObject(post);
						System.out.println("Post Detail Sending ok");
					}
					System.out.println("[send Post Detail phase Done]");
					break;
				case "$postInsert":
					System.out.println("[send Post Insert phase start]");
					postitController.postInsert(input);
					
					System.out.println("[send Post Insert phase Done]");
					break;
				case "$postUpdate":
					break;
				case "$postDelete":
					break;
				case "$checkUser": // 로그인이 된 사람인가?
//					oos.writeUTF(checkID(input_String));
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
				
				os.close();
				oos.close();
				ois.close();
				is.close();
				socket.close();
				System.out.println("[ " + socket + " : DisConnected]");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				os.close();
				oos.close();
				is.close();
				ois.close();
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
