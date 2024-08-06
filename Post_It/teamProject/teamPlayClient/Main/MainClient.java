package teamPlayClient.Main;

import java.util.Scanner;

import teamPlayClient.controller.PrintClientController;
import teamPlayClient.socketClient.Client;

public class MainClient {

	private Scanner scan = new Scanner(System.in);
	private String LoginToken = "";
	private Client client = new Client();
	private String border = "";
	private String category = "";

	void init() {

		int flag = 0;
		String M = "0";

		while (flag == 0) {

			PrintClientController.printMainMenu();

			M = scan.nextLine();

			runInit(M);

			if (LoginToken.length() != 0) {
				PrintClientController.printLoginSuccess();
				flag = 1;
				break;
			}

			if (M.equals("exit") || M.equals("0")) {
				PrintClientController.printExit();
				break;
			}
		} // login$join phase

		while (flag == 1) {

			PrintClientController.printAfterLoginMenu(border, category);

			M = scan.nextLine();

			runAfterLogin(M);

			if (M.equals("exit") || M.equals("0")) {
				PrintClientController.printExit();
				break;
			}

		} // post phase

	}

	private void runAfterLogin(String M) {
		if (M.equals("1")) {
			System.out.println("*--- 보드 선택 ---*");
			border = client.ClientRun("$borderListSelect", "getList");
			
		} else if (M.equals("2")) {
			System.out.println("*--- 카테고리 선택 ---*");
			if (border.length() == 0) {
				System.out.println("[Border is null]");
				return;
			}
			category = client.ClientRun("$categoryListSelect", border);

		} else if (M.equals("3")) {
			System.out.println("*--- 게시글 보기 ---*");
			if (border.length() == 0 || category.length() == 0) {
				System.out.println("[Border or Category is null]");
				return;
			}
			client.ClientRun("$showPostList", category);
		} else if (M.equals("4")) {
			System.out.println("*--- 게시글 선택 ---*");
			if (border.length() == 0 || category.length() == 0) {
				System.out.println("[Border or Category is null]");
				return;
			}
			System.out.println("* --- 게시글 번호");
			PrintClientController.printBar();
			PrintClientController.printRequestAnswer();
			String postNumber = scan.nextLine();
			
			try {
				int test = Integer.parseInt(postNumber);
			} catch (Exception e) {
				PrintClientController.printWrongMSG();
				return;
			}

			client.ClientRun("$postDetail", postNumber);
		} else if (M.equals("5")) {
			System.out.println("*--- 게시글 작성 ---*");
			//객체로 만들어서 주어야 좋은데 수정하기에 시간이 없음 test 요구됨
			if (border.length() == 0 || category.length() == 0) {
				System.out.println("[Border or Category is null]");
				return;
			}			
			String getid[] = LoginToken.split("@@");
			String idString = getid[0];
			
			PrintClientController.printBar();
			System.out.println("* --- 제목을 적어보세요 !");
			String insertPostTitle = ""; 
			insertPostTitle = scan.nextLine();
			
			PrintClientController.printBar();
			System.out.println("* --- 내용을 적어보세요 !");
			String insertPostContent = "";
			insertPostContent =	scan.nextLine();
			
			if (insertPostTitle.length() == 0 || insertPostContent.length() == 0) {
				System.out.println("[Title or Content is null]");
				return;
			}
			
			String postInsertData = border+"@@"+category+"@@"+idString+"@@"+insertPostTitle+"@@"+insertPostContent;
			
			
			client.ClientRun("$postInsert", postInsertData);
		} else if (M.equals("6")) {
			System.out.println("*--- 게시글 삭제 ---*");

		} else if (M.equals("7")) {
			System.out.println("*--- 게시글 수정 ---*");

		} else if (M.equals("8")) {
			System.out.println("*--- 댓글 작성 ---*");

		} else if (M.equals("9")) {
			System.out.println("*--- 댓글 삭제 ---*");
			
		} else if (M.equals("0") || M.equals("exit")) {
			// do noting
		} else {
			PrintClientController.printWrongMSG();
		}

	}

	private void runInit(String M) {

		if (M.equals("1")) {
			loginInit();
		} else if (M.equals("2")) {
			joinInit();
		} else if (M.equals("0") || M.equals("exit")) {
			// do noting
		} else {
			PrintClientController.printWrongMSG();
		}

	}

	private void loginInit() {
		System.out.println("Login");

		System.out.println("ID : ");
		PrintClientController.printRequestAnswer();
		String idtmp = scan.nextLine();

		System.out.println("PW : ");
		PrintClientController.printRequestAnswer();
		String pwtmp = scan.nextLine();

		LoginToken = client.ClientRun("$login", idtmp + "@@" + pwtmp);

		// id@@day now();
	}

	private void joinInit() {
		// id@@pw@@email@@gender("남"||"여")@@birthday@@phone@@email@@au_key("user")<<고정
		System.out.println("Join");

		System.out.println("ID : ");
		PrintClientController.printRequestAnswer();
		String idtmp = scan.nextLine();

		System.out.println("PW : ");
		PrintClientController.printRequestAnswer();
		String pwtmp = scan.nextLine();

		System.out.println("gender : 남/여 중 택 1");
		PrintClientController.printRequestAnswer();
		String gendertmp = scan.nextLine();
		System.out.println(gendertmp);
		if (!gendertmp.equals("남") && !gendertmp.equals("여")) {
			PrintClientController.printWrongMSG();
			return;
		}

		System.out.println("birthday : (1999-01-01) 형식으로 중간에 '-' 를 넣어주세요");
		PrintClientController.printRequestAnswer();
		String birthdaytmp = scan.nextLine();

		if (birthdaytmp.length() != 10) {
			PrintClientController.printWrongMSG();
			return;
		}

		System.out.println("phone : (010-1234-1234) 형식으로 중간에 '-' 를 넣어주세요");
		PrintClientController.printRequestAnswer();
		String phonetmp = scan.nextLine();

		if (phonetmp.length() != 13) {
			PrintClientController.printWrongMSG();
			return;
		}

		System.out.println("email : ");
		PrintClientController.printRequestAnswer();
		String emailtmp = scan.nextLine();

		String authoritytmp = "user";

		// user = new PrivacyVO(id, pw, gender, birthday, phone, email, authority);
		String sumData = idtmp + "@@" + pwtmp + "@@" + gendertmp + "@@" + birthdaytmp + "@@" + phonetmp + "@@"
				+ emailtmp + "@@" + authoritytmp;
		client.ClientRun("$join", sumData);

	}

	public String getLoginToken() {
		return LoginToken;
	}

	public void setLoginToken(String loginToken) {
		LoginToken = loginToken;
	}

	public String getBorder() {
		return border;
	}

	public void setBorder(String border) {
		this.border = border;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
