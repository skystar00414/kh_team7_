package teamPlay.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;

public class PrintController {

	public static void initMenu() {
		
		
		printBar();
		System.out.println("1. 서버 오픈\n2. 서버 종료\n3. 관리자 매뉴\n4. 종료");
		printBar();
		
	}	
	
	
	public static void printBar() {
		System.out.println("--- --- --- --- ---");
	}


	public static void GobackMSG() {
		System.out.println("-돌아가기-");
		
	}


	public static void wrongInput() {
		System.out.println("잘못된 입력입니다.");
		
	}


	public static void initAdminMenu() {
		printBar();
		System.out.println("1. 공지사항 작성\n2. 게시글 삭제\n3. 회원 밴\n4. 뒤로가기\n5. 디버그창");
		printBar();
		
	}


	public static String printNowDayandTime() {
		LocalDate nowDay = LocalDate.now();
		DateTimeFormatter dayformatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		LocalDateTime nowTime = LocalDateTime.now();
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH-mm-ss");
		
		String nowDayString = nowDay.format(dayformatter);
		String nowTimeString = nowTime.format(timeFormatter);
	
		
		return nowDayString + "/" + nowTimeString;
	}


	public static void printDebugInit() {
		printBar();
		System.out.println("check = 계정 유무 체크\nlogin = 로그인 가능 여부 확인\njoin = 유저정보 등록 확인\nexit = 뒤로가기\nfailed = 아이디 실패시 failed 증가 확인");
		System.out.println("Succeed = 로그인 성공해서 실패 카운트 초기화\nborder_plus = 보더에 카테고리 추가\nborder_select = 보더 카테고리 선택\nborder_update = 보더랑 세부정보 수정\nborder_delete = 보더에 카테고리 제거");
//		System.out.println("");
		printBar();
		
		
	}
	
	
}
