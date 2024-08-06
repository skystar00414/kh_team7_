package teamPlay.main;

import java.util.Scanner;

import teamPlay.controller.PostitController;
import teamPlay.controller.PrintController;
import teamPlay.controller.PrivacyController;
import teamPlay.model.vo.PrivacyVO;


public class ProgramRunner {

	private Scanner scan = new Scanner(System.in);
	private PrivacyController privacyController = new PrivacyController(scan);
	private PostitController postitController = new PostitController(scan);
	private ServerRunner serverRunner = new ServerRunner();
	private PrivacyVO privacy = null;
	
	
	
	public void init() {
		
		
		String M = "0";
		
		
		
		while (true) {
			PrintController.initMenu();
			M = scan.nextLine();
			runtInit(M);
			
			if (M.equals("4")) {
				break;
			}
			
		}
		
		

		
	}



	private void runtInit(String M) {
		if (M.equals("1")) {
			System.out.println("[서버 오픈]");
			serverRunner.serverRun();
		} else if(M.equals("2")){
			System.out.println("[서버 종료]");
			
		} else if (M.equals("3")) {
			System.out.println("관리자 매뉴");
			adminInit();
		} else if (M.equals("4")) {
			System.out.println("종료");
		} else {
			PrintController.wrongInput();
		}
	}



	private void adminInit() {
			
		String M = "0";
		
		while (true) {
			PrintController.initAdminMenu();
			M = scan.nextLine();
			
			runInitAdminMenu(M);
			
			if (M.equals("4")) {
				break;
			}
		}
		
	}



	private void runInitAdminMenu(String M) {
		if (M.equals("1")) {
			System.out.println("[공지사항 작성]");
			
		} else if(M.equals("2")){
			System.out.println("[규정 위반 게시글 삭제]");
		} else if (M.equals("3")) {
			System.out.println("[규정 위반 회원 밴]");
		} else if (M.equals("4")) {
			PrintController.GobackMSG();
		} else if (M.equals("5")) {
			debugInit();
		} else {
			PrintController.wrongInput();
		}
	}



	private void debugInit() {
		
		String M = "0";
		
		while (true) {

			PrintController.printDebugInit();
			M = scan.nextLine();
			
			runDebugInit(M);
			
			if (M.equals("exit")) {
				break;
			}
		}
		
	}



	private void runDebugInit(String M) {
		if (M.equals("check")) {//Succeed!

			System.out.print("Check User ID>>");
			String userID = scan.nextLine();
			privacyController.checkUser(userID); // 유저체크 확인용
			
		}
		
		if (M.equals("login")) {//Succeed!
			
			System.out.print("get ID\n>>");
			String idString = scan.nextLine();
			
			System.out.print("get PW\n>>");
			String pwString = scan.nextLine();
			PrivacyVO privacy = privacyController.loginUser(idString+"@@"+pwString);
			System.out.println(privacy);
			if (privacy == null) {
				System.out.println("login Fail");
			}
		}
		
		if (M.equals("join")) {//Succeed!
			//id pw gender birth phone email aukey
			System.out.print("get ID\n>>");
			String idString = scan.nextLine();
			
			System.out.print("get pw\n>>");
			String pwString = scan.nextLine();
			
			System.out.print("get gender\n>>");
			String genderString = scan.nextLine();
			
			System.out.print("get birth-day\n>>");
			String birthString = scan.nextLine();
			
			System.out.print("get phone\n>>");
			String phoneString = scan.nextLine();
			
			System.out.print("get email\n>>");
			String emailString = scan.nextLine();
			
			System.out.print("get au_key\n>>");
			String au_keyString = "user";
			
			boolean ok = privacyController.joinUser(idString+"@@"+pwString+"@@"+genderString+"@@"+birthString+"@@"+phoneString+"@@"+emailString+"@@"+au_keyString);
			
			if (ok) {
				System.out.println("user join success");
			} else {
				System.out.println("user join fail");
			}
			
		}
		
		if (M.equals("failed")) {//Succeed!
			
			System.out.print("get ID\n>>");
			String idString = scan.nextLine(); // 실패시 객체가 아이디를 가지지 못함
			
			privacyController.failedid(idString+"@@badID");
		}
		
		if (M.equals("succeed")) { //Succeed!
			
			System.out.print("get ID\n>>");
			String idString = scan.nextLine(); // 성공시 객체가 아이디를 가지고있음
			
			privacyController.succeedLogin(idString);
		}
		
		
		if (M.equals("border_plus")) {
			
			System.out.print("select New border category\n>>");
			String newCategory = scan.nextLine();
			
			postitController.createNewBorder(newCategory);
		}
		if (M.equals("border_select")) {
			
			System.out.print("select Border Category Name\n>>");
			String Category = scan.nextLine();
			
			postitController.selectBorder(Category);
		}
		
		if (M.equals("border_update")) {
			
			System.out.print("select the border to update\n>>");
			String Category = scan.nextLine();
			
			postitController.updateBorder(Category);
		}
		
		
		
		if (M.equals("border_delete")) {
			
			System.out.print("select Border Name\n>>");
			String deleteCategory = scan.nextLine();
			
			postitController.deleteBorder(deleteCategory);
		}
		
		if (M.equals("exit")) {
			PrintController.GobackMSG();
		}
	}


	
}
