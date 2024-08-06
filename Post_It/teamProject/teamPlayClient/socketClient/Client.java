package teamPlayClient.socketClient;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.List;
import java.util.Locale.Category;
import java.util.Scanner;

import teamPlay.model.vo.BorderVO;
import teamPlay.model.vo.CategoryVO;
import teamPlay.model.vo.Message;
import teamPlay.model.vo.PostitVO;
import teamPlayClient.Main.MainClient;
import teamPlayClient.controller.PrintClientController;

// 좋은방식은 아님 무엇이 있는지 따지지 않고 보낼 수 있는데 이유 없이 분류한 느낌
public class Client {
	
	public Client() {
		
	}
	
	private Scanner scan = new Scanner(System.in); //쓸거같지는 않음
	private int PORT = 9001;
	private String IP = "127.0.0.1";
	private String borderC = "";
	private String categoryC = "";

	@SuppressWarnings("resource")
	public String ClientRun(String order, String data) {
		//$login , qwer1234@@qwer1234
		Socket socket = null; 
		//서버에서 받아용!
		InputStream is = null;
		ObjectInputStream ois = null;// 이걸 사용해용
		
		//서버에 줘용!
		OutputStream os = null;
		ObjectOutputStream oos = null;// 이걸 사용해용
		
		try {
			System.out.println("[waiting...]");
			socket = new Socket(IP, PORT); //서버 연결
			System.out.println("["+socket+" : Connected !]");
			
			is = socket.getInputStream();
			ois = new ObjectInputStream(is);
			System.out.println("[ObjectInputStream . . . ready]");
			
			os = socket.getOutputStream();
			oos = new ObjectOutputStream(os);
			System.out.println("[ObjectOutputStream . . . ready]");


			System.out.println(order +"@@"+ data);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			switch (order) {

			case "$login":
				System.out.println("[Login phase start]");
				//id@@pw
				Message orderMsg = new Message("$login"); // server Order send
				oos.writeObject(orderMsg);
				System.out.println("[send order : $login]");
				
				Message LogindataMsg = new Message(data);
				oos.writeObject(LogindataMsg);
				oos.flush();
				System.out.println("[send data : "+data+"]");
				
				Message getLoginTokenMsg = (Message) ois.readObject();
				String idToken = getLoginTokenMsg.getText();
				System.out.println("[get Login Token "+idToken+"]");
				//$spread@@wrongLogin < 실패시
				String arrLogin[] = idToken.split("@@");
								
				if (arrLogin[1].equals("wrongLogin")) {
					PrintClientController.printWrongMSG();
					return "";
				}
				MainClient mc = new MainClient();
				mc.setLoginToken(idToken);
				System.out.println("[Login Phase Done]");
				return idToken;
			
			case "$join":
				
				System.out.println("[join Phase Start]");
				Message joinMsg = new Message("$join"); // server Order send
				oos.writeObject(joinMsg);
				System.out.println("[send order : $join]");
				
				Message joindataMsg = new Message(data);
				oos.writeObject(joindataMsg);
				oos.flush();
				System.out.println("[send data : "+data+"]");

				
				Message getJoinMsg = (Message) ois.readObject();
				String joinToken = getJoinMsg.getText();
				System.out.println("[get Login Token "+joinToken+"]");
				String arrJoin[] = joinToken.split("@@");
				if (arrJoin[1].equals("Joinsuccess")) {
					System.out.println("[회원가입 성공]");
				} else {
					System.out.println("[회원가입 실패]");
				}
				
				break;
				
			case "$borderListSelect":
				
				System.out.println("[get Border List Phase Start]");
				Message getBorderList = new Message("$borderListSelect");
				oos.writeObject(getBorderList);
				System.out.println("[send order : $borderListSelect]");
				
				Message getBorderListdataMsg = new Message(data);
				oos.writeObject(getBorderListdataMsg);
				oos.flush();
				System.out.println("[send data : "+data+"]");
				
				List<BorderVO> listBorderVO = (List<BorderVO>) ois.readObject();
				System.out.println(listBorderVO.getClass());
				PrintClientController.printBar();
				listBorderVO.forEach((bo)->System.out.println("* --- "+bo.getBo_title()));
				
				System.out.println("* --- 하나를 선택하세요");
				PrintClientController.printBar();
				PrintClientController.printRequestAnswer();
				String selectBorder = scan.nextLine();
				
				for (int i = 0; i < listBorderVO.size(); i++) {
					if (listBorderVO.get(i).getBo_title().equals(selectBorder)) {
						System.out.println("[get Border List Phase done]");
						borderC = selectBorder;
						return selectBorder;
					}
				}
				System.out.println("[일치하는 보드가 없습니다.]");
				System.out.println("[get Border List Phase fail]");
				break;
			
			case "$categoryListSelect":
				System.out.println("[get Category List Phase Start]");
				Message getCategoryList = new Message("$categoryListSelect");
				oos.writeObject(getCategoryList);
				System.out.println("[send order : $categoryListSelect]");
				
				Message getCategoryListdataMsg = new Message(data);
				oos.writeObject(getCategoryListdataMsg);
				oos.flush();
				System.out.println("[send data : "+data+"]");
				
				List<CategoryVO> Categorylist = (List<CategoryVO>) ois.readObject();
				
				PrintClientController.printBar();
				Categorylist.forEach((cl)->System.out.println("* --- " + cl.getCa_name()));
				System.out.println("* --- 하나를 선택하세요");
				PrintClientController.printBar();
				PrintClientController.printRequestAnswer();
				String selectCategory = scan.nextLine();
				for (int i = 0; i < Categorylist.size(); i++) {
					if (Categorylist.get(i).getCa_name().equals(selectCategory)) {
						System.out.println("[get Category List Phase done]");
						categoryC = selectCategory;
						return selectCategory;
					}
				}
				System.out.println("[일치하는 카테고리가 없습니다.]");
				System.out.println("[get Category List Phase fail]");
				break;
				
			case "$showPostList":
				System.out.println("[get Post List Phase Start]");
				Message getPostList = new Message("$showPostList");
				oos.writeObject(getPostList);
				System.out.println("[send order : $showPostList]");
				
				Message getPostListdataMsg = new Message(data);
				oos.writeObject(getPostListdataMsg);
				oos.flush();
				System.out.println("[send data : "+data+"]");
				
				List<PostitVO> postList = (List<PostitVO>)ois.readObject();
				
				PrintClientController.printBar();
				System.out.println("현재 보드 : " + borderC);
				System.out.println("현재 카테고리 : " + categoryC);
				PrintClientController.printBar();
				postList.forEach((pl)->System.out.println("번호:" +pl.getPo_number()+"|" +pl.getPo_title() +"|작성일:"+ pl.getPo_date()));
				PrintClientController.printBar();
				System.out.println("[get Post List Phase Done]");
				break;
				
			case "$postDetail":
				System.out.println("[get Post Detail Phase Start]");
				Message getPostDetail = new Message("$postDetail");
				oos.writeObject(getPostDetail);
				System.out.println("[send order : $postDetail]");
				
				Message getPostDetaildataMsg = new Message(data);
				oos.writeObject(getPostDetaildataMsg);
				oos.flush();
				System.out.println("[send data : "+data+"]");
				
				// null 체크 요망
				PostitVO post = null;
				Message postFail = null;
				try {
					post = (PostitVO)ois.readObject();	
					PrintClientController.printBar();
					System.out.println("번호:" +post.getPo_number()+"|" +post.getPo_title() +"|작성일:"+ post.getPo_date());
					System.out.println(post.getPo_content());
					PrintClientController.printBar();
					for(;;) {
						System.out.println("0. or exit. to next");
						String M = scan.nextLine();
						if (M.equals("0") || M.equals("exit")) {
							break;
						}
					}
				} catch (Exception e) {
					postFail = (Message)ois.readObject();
					System.out.println("["+postFail.getText()+"]");
					
				}
				System.out.println("[get Post Detail Phase Done]");
				break;
			
			case "$postInsert":
				System.out.println("[get Post Insert Phase Start]");
				Message setPostInsert = new Message("$postInsert");
				oos.writeObject(setPostInsert);
				System.out.println("[send order : $postInsert]");
				
				Message setPostInsertData = new Message(data);
				//border+"@@"+category+"@@"+idString+"@@"+insertPostTitle+"@@"+insertPostContent;
				oos.writeObject(setPostInsertData);
				oos.flush();
				System.out.println("[send data : "+data+"]");
				
				System.out.println("[get Post Insert Phase Done]");
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
                System.out.println("[socket closing]");
			} catch (Exception e) {
				e.printStackTrace();
				
			}
		
		
		
		}
		
		return "";
	}	
	
	
	
}
